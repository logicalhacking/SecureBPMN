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

grammar RoleDef;

tokens {
	COLON=':';
	COMMA=',';
	GEQ='>';
}

@header {
package org.antlr.roledef;

import com.sap.research.st.aniketos.securebpmn.roles.*;
}

@lexer::header {package org.antlr.roledef;}

file  returns [List<RoleDefLine> lines]
    : {$lines = new ArrayList<RoleDefLine>();}
      (ldef=line_def {$lines.add($ldef.def);} | lrel=line_rel {$lines.add($lrel.rel);} )* EOF
    ;

line_def returns [RoleDef def]
    : name=string {$def = new RoleDef($name.text);} COLON fm=string {$def.addMember($fm.text);} (COMMA mm=string {$def.addMember($mm.text);} )*
    ;	

line_rel returns [RoleRel rel]
    : sup=string GEQ sub=string {$rel = new RoleRel($sup.text, $sub.text);}
    ;
    
string
    : STR
    ;

STR	
    :	( 'a'..'z' | 'A'..'Z' | '0'..'9' | '_' | '-' )+
    ;

COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

