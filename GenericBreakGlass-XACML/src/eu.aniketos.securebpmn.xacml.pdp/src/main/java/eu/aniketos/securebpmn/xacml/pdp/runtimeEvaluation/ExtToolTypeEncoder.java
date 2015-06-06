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

package eu.aniketos.securebpmn.xacml.pdp.runtimeEvaluation;

import java.io.PrintStream;

import com.sun.xacml.AbstractPolicy;
import com.sun.xacml.attr.AttributeDesignator;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.BagAttribute;
import com.sun.xacml.combine.CombiningAlgorithm;
import com.sun.xacml.cond.Apply;
import com.sun.xacml.cond.Function;

/**
 *
 * This interface is used to encode different XAML type according to the
 * used backend tool
 *
 */
public interface ExtToolTypeEncoder {
    public String getFunctionEnc(Function func);

    public String getBagEnc(BagAttribute bag);

    public String getAttrValueEnc(AttributeValue val);

    public String getAttrDesignatorEnc(AttributeDesignator attr, AttributeValue attrVal);

    public String getDecision(int decision);

    public String getCombiningAlg(CombiningAlgorithm alg);

    public String getAND();

    public String getOR();

    public void printApply(Apply apply, PrintStream out);

    public void setKnownAttrStore(KnownAttrStore attrReslv);

    public String getPolicy(AbstractPolicy policy);

}