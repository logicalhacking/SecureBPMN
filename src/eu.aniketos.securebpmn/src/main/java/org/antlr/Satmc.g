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

grammar Satmc;

// This grammar supports only SATMC output of version 3.2.8 or higher.

tokens {
	WARNING='% WARNING:';
	ERROR='% ERROR:';
	INPUT='INPUT';
	SUMMARY='SUMMARY';
	GOAL='GOAL:';
	DETAILS='DETAILS';
	VERSION='BACKEND SATMC VERSION';
	COMMENTS='COMMENTS';
	STAT='STATISTICS';
	TRACE='TRACE:';
	CLAUSES='CLAUSES:';
	RULES='RULES:';
	CFS='CLOSED_FINAL_STATE:';
	O_PARENTHESIS='(';
	C_PARENTHESIS=')';
	O_BRACKET='[';
	C_BRACKET=']';
	O_BRACES='{';
	C_BRACES='}';
	COMMA=',';
	PERCENT='% ';
}

@header {
package org.antlr.satmc;

import com.sap.research.st.aniketos.securebpmn.satmc.*;
}

@lexer::header {package org.antlr.satmc;}

output returns [SatmcMessage message]	
    : 	NEWLINE* ( NEWLINE section_warning )* ( error 	{$message = new SatmcMessage(Summary.ERROR, null, null, null);}
    					      | s=result	{$message = $s.message;}
    					      )  NEWLINE* EOF
    |
    ;
    
error
    : NEWLINE section_error
    ;
    
result returns [SatmcMessage message]
    : line_input NEWLINE ls=line_summary NEWLINE NEWLINE? ( lg=line_goal NEWLINE NEWLINE )? section_details NEWLINE NEWLINE line_version NEWLINE NEWLINE section_comments? section_stat st=section_trace? lc=line_cfs?
    {$message = new SatmcMessage($ls.res, $lg.res, $st.res, $lc.res);}
    ;
    
section_warning
    :	WARNING ( ( ~NEWLINE )+ NEWLINE )* NEWLINE
    ;
    
section_error
    :	ERROR ( ( ~NEWLINE )+ NEWLINE )* NEWLINE NEWLINE ( PERCENT ( ( ~NEWLINE )+ NEWLINE )* NEWLINE )?
    ;

line_input
    :	INPUT WS target WS?
    ;
    
line_summary returns [Summary res]
    :	SUMMARY WS sr=summary_result WS? {if ("ATTACK_FOUND".equals($sr.text)) {
    						$res = Summary.ATTACK_FOUND;
    					  } else if ("NO_ATTACK_FOUND".equals($sr.text)) {
    					  	$res = Summary.NO_ATTACK_FOUND;
    					  } else if ("INCONCLUSIVE".equals($sr.text)) {
    					  	$res = Summary.INCONCLUSIVE;
    					  } else {
    					  	$res = Summary.UNKNOWN;
    					  }
    					 }
    ;
    
line_goal returns [SatmcFunction res]
    :	WS? GOAL WS f=function {$res = $f.res;} WS?
    ;

section_details
    :	line_details ( NEWLINE line_detail )*
    ;

line_details
    :	DETAILS WS?
    ;

line_detail
    :	WS? CONSTANT WS?
    ;

line_version
    :	VERSION WS VERSION_NR
    ;
    
section_comments
    :	COMMENTS WS? NEWLINE ( ( ~NEWLINE )+ NEWLINE )* NEWLINE
    ;
    
section_stat
    :	STAT WS ( ( ~NEWLINE )+ NEWLINE )* NEWLINE
    ;
    
section_trace returns [List<SatmcTraceStep> res]
    :	TRACE { $res = new ArrayList<SatmcTraceStep>(); } NEWLINE 
	    	( NUMBER {boolean rulesPresent = false;} 
	    	  NEWLINE lc=line_clauses NEWLINE ( lr=line_rules {rulesPresent=true;} 
	    	  NEWLINE )? 
	    	{if (rulesPresent) 
	    		$res.add(new SatmcTraceStep($lc.res, $lr.res)); 
	    	else 
	    		$res.add(new SatmcTraceStep($lc.res, new ArrayList<SatmcFunction>()));} 
	    	)+
    ;
    
line_clauses returns [List<SatmcFunction> res]
    :	WS? CLAUSES fs=function_set { $res = $fs.res; }
    ;
    
line_rules returns [List<SatmcFunction> res]
    :	WS? RULES WS ( f=function { $res = new ArrayList<SatmcFunction>(); $res.add($f.res);}
    		     | fs=function_set { $res = $fs.res; }
    		     | fn=fname { $res = new ArrayList<SatmcFunction>(); 
    		                          $res.add(new SatmcFunction($fn.text, new ArrayList<SatmcFact>()));})
    ;
    
line_cfs returns [List<SatmcFunction> res]
    :	CFS NEWLINE fs=function_set { $res = $fs.res; }
    ;

target
    :	FILE
    ;

summary_result
    :	CONSTANT
    ;
    
function_set returns [List<SatmcFunction> res]
    :	O_BRACES WS { $res = new ArrayList<SatmcFunction>();
    		    } 
    		    ( f1=function { $res.add($f1.res);
    			            }
    			            ( COMMA fn=function { $res.add($fn.res); } )* )? WS? C_BRACES
    ;
    
function returns [SatmcFunction res]
    :	fn=fname { $res = new SatmcFunction($fn.text, new ArrayList<SatmcFact>());
    		 }
    		 O_PARENTHESIS  fv=fvar { $res.args.add($fv.res);
    		 			}
    		 			( COMMA fvl=fvar { $res.args.add($fvl.res);
    		 			  		 }
    		 			  		 )* C_PARENTHESIS
    ;
    
var
    :	FTEXT
    |	NUMBER
    ;
    
fname
    :	FTEXT
    ;
    
fvar returns [SatmcFact res]
    :	r1=var {$res = new SatmcVar($r1.text);}
    |	r2=function {$res = $r2.res;}
    ;

VERSION_NR
    : '0'..'9' '.' '0'..'9' '.' '0'..'9' ( 'a'..'z' | 'A'..'Z' | '0'..'9' | '_' | O_PARENTHESIS | C_PARENTHESIS )*
    ;

CONSTANT
    : ( 'A'..'Z' | '_' )+
    ;
    
NUMBER
   : ( '0'..'9' )+
   ;
    
FTEXT
    : ( 'a'..'z' | 'A'..'Z' | NUMBER |'_' )+
    ;
    
FILE
    : ( 'a'..'z' | 'A'..'Z' | NUMBER | '.' )+
    ;

NEWLINE
    : '\r'? '\n'
    ;

WS
    : ( ' ' | '\t' )+
    ;
