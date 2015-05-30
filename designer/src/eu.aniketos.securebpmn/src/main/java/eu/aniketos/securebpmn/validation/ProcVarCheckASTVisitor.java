/* Copyright 2012-2015 SAP SE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.aniketos.securebpmn.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.StringLiteral;

/**
 * Eclipse Java AST visitor class for checking if a certain process variable is
 * accessed.
 *
 *
 */
public class ProcVarCheckASTVisitor extends ASTVisitor {

    private List<String> delegateExecVars;
    private List<Integer> locations;
    private String procVar;
    private boolean importFound;

    /**
     * Default constructor.
     *
     * @param procVar
     *            The name of the process variable that should be checked.
     * @param locations
     *            A List where the locations of the accesses will be written to.
     */
    public ProcVarCheckASTVisitor(String procVar, List<Integer> locations) {
        this.delegateExecVars = new ArrayList<String>();
        this.locations = locations;
        this.procVar = procVar;
        this.importFound = false;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.
     * ImportDeclaration)
     */
    @Override
    public boolean visit(ImportDeclaration node) {

        String imp = node.getName().toString();

        if (imp.equals("org.activiti.engine.delegate.DelegateExecution")
                || imp.equals("org.activiti.engine.delegate.*")
                || imp.equals("org.activiti.engine.*")
                || imp.equals("org.activiti.*"))
            importFound = true;

        return super.visit(node);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.
     * SingleVariableDeclaration)
     */
    @Override
    public boolean visit(SingleVariableDeclaration node) {

        if (importFound
                && node.getType().toString().equals("DelegateExecution")) {
            delegateExecVars.add(node.getName().toString());
        }

        return super.visit(node);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.
     * MethodInvocation)
     */
    @Override
    public boolean visit(MethodInvocation node) {

        String methodName = node.getName().toString();

        if (methodName.equals("getVariable")
                || methodName.equals("setVariable")) {
            // check if expression is of class
            // org.activiti.engine.delegate.DelegateExecution
            Expression expr = node.getExpression();
            if (expr instanceof SimpleName) {
                if (!delegateExecVars.contains(((SimpleName) expr).toString()))
                    return super.visit(node);
            }

            // check if argument matches procVar
            if (node.arguments().size() > 0) {
                Object arg = node.arguments().get(0);
                if (arg instanceof StringLiteral) {
                    if (arg.toString().equals("\"" + procVar + "\"")) {
                        locations.add(node.getStartPosition());
                        return false;
                    }
                }
            }
        }
        return super.visit(node);
    }

}
