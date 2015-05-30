// $ANTLR 3.4 Satmc.g 2012-01-23 13:53:57

package org.antlr.satmc;

import eu.aniketos.securebpmn.satmc.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class SatmcParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CFS", "CLAUSES", "COMMA", "COMMENTS", "CONSTANT", "C_BRACES", "C_BRACKET", "C_PARENTHESIS", "DETAILS", "ERROR", "FILE", "FTEXT", "GOAL", "INPUT", "NEWLINE", "NUMBER", "O_BRACES", "O_BRACKET", "O_PARENTHESIS", "PERCENT", "RULES", "STAT", "SUMMARY", "TRACE", "VERSION", "VERSION_NR", "WARNING", "WS"
    };

    public static final int EOF=-1;
    public static final int CFS=4;
    public static final int CLAUSES=5;
    public static final int COMMA=6;
    public static final int COMMENTS=7;
    public static final int CONSTANT=8;
    public static final int C_BRACES=9;
    public static final int C_BRACKET=10;
    public static final int C_PARENTHESIS=11;
    public static final int DETAILS=12;
    public static final int ERROR=13;
    public static final int FILE=14;
    public static final int FTEXT=15;
    public static final int GOAL=16;
    public static final int INPUT=17;
    public static final int NEWLINE=18;
    public static final int NUMBER=19;
    public static final int O_BRACES=20;
    public static final int O_BRACKET=21;
    public static final int O_PARENTHESIS=22;
    public static final int PERCENT=23;
    public static final int RULES=24;
    public static final int STAT=25;
    public static final int SUMMARY=26;
    public static final int TRACE=27;
    public static final int VERSION=28;
    public static final int VERSION_NR=29;
    public static final int WARNING=30;
    public static final int WS=31;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public SatmcParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public SatmcParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return SatmcParser.tokenNames; }
    public String getGrammarFileName() { return "Satmc.g"; }



    // $ANTLR start "output"
    // Satmc.g:37:1: output returns [SatmcMessage message] : ( ( NEWLINE )* ( NEWLINE section_warning )* ( error |s= result ) ( NEWLINE )* EOF |);
    public final SatmcMessage output() throws RecognitionException {
        SatmcMessage message = null;


        SatmcMessage s =null;


        try {
            // Satmc.g:38:5: ( ( NEWLINE )* ( NEWLINE section_warning )* ( error |s= result ) ( NEWLINE )* EOF |)
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0 >= INPUT && LA5_0 <= NEWLINE)) ) {
                alt5=1;
            }
            else if ( (LA5_0==EOF) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // Satmc.g:38:8: ( NEWLINE )* ( NEWLINE section_warning )* ( error |s= result ) ( NEWLINE )* EOF
                    {
                    // Satmc.g:38:8: ( NEWLINE )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==NEWLINE) ) {
                            int LA1_1 = input.LA(2);

                            if ( ((LA1_1 >= INPUT && LA1_1 <= NEWLINE)) ) {
                                alt1=1;
                            }


                        }


                        switch (alt1) {
                    	case 1 :
                    	    // Satmc.g:38:8: NEWLINE
                    	    {
                    	    match(input,NEWLINE,FOLLOW_NEWLINE_in_output168); 

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    // Satmc.g:38:17: ( NEWLINE section_warning )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==NEWLINE) ) {
                            int LA2_1 = input.LA(2);

                            if ( (LA2_1==WARNING) ) {
                                alt2=1;
                            }


                        }


                        switch (alt2) {
                    	case 1 :
                    	    // Satmc.g:38:19: NEWLINE section_warning
                    	    {
                    	    match(input,NEWLINE,FOLLOW_NEWLINE_in_output173); 

                    	    pushFollow(FOLLOW_section_warning_in_output175);
                    	    section_warning();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    // Satmc.g:38:46: ( error |s= result )
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==NEWLINE) ) {
                        alt3=1;
                    }
                    else if ( (LA3_0==INPUT) ) {
                        alt3=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 0, input);

                        throw nvae;

                    }
                    switch (alt3) {
                        case 1 :
                            // Satmc.g:38:48: error
                            {
                            pushFollow(FOLLOW_error_in_output182);
                            error();

                            state._fsp--;


                            message = new SatmcMessage(Summary.ERROR, null, null, null);

                            }
                            break;
                        case 2 :
                            // Satmc.g:39:18: s= result
                            {
                            pushFollow(FOLLOW_result_in_output206);
                            s=result();

                            state._fsp--;


                            message = s;

                            }
                            break;

                    }


                    // Satmc.g:40:19: ( NEWLINE )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==NEWLINE) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // Satmc.g:40:19: NEWLINE
                    	    {
                    	    match(input,NEWLINE,FOLLOW_NEWLINE_in_output228); 

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    match(input,EOF,FOLLOW_EOF_in_output231); 

                    }
                    break;
                case 2 :
                    // Satmc.g:42:5: 
                    {
                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return message;
    }
    // $ANTLR end "output"



    // $ANTLR start "error"
    // Satmc.g:44:1: error : NEWLINE section_error ;
    public final void error() throws RecognitionException {
        try {
            // Satmc.g:45:5: ( NEWLINE section_error )
            // Satmc.g:45:7: NEWLINE section_error
            {
            match(input,NEWLINE,FOLLOW_NEWLINE_in_error258); 

            pushFollow(FOLLOW_section_error_in_error260);
            section_error();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "error"



    // $ANTLR start "result"
    // Satmc.g:48:1: result returns [SatmcMessage message] : line_input NEWLINE ls= line_summary NEWLINE ( NEWLINE )? (lg= line_goal NEWLINE NEWLINE )? section_details NEWLINE NEWLINE line_version NEWLINE NEWLINE ( section_comments )? section_stat (st= section_trace )? (lc= line_cfs )? ;
    public final SatmcMessage result() throws RecognitionException {
        SatmcMessage message = null;


        Summary ls =null;

        SatmcFunction lg =null;

        List<SatmcTraceStep> st =null;

        List<SatmcFunction> lc =null;


        try {
            // Satmc.g:49:5: ( line_input NEWLINE ls= line_summary NEWLINE ( NEWLINE )? (lg= line_goal NEWLINE NEWLINE )? section_details NEWLINE NEWLINE line_version NEWLINE NEWLINE ( section_comments )? section_stat (st= section_trace )? (lc= line_cfs )? )
            // Satmc.g:49:7: line_input NEWLINE ls= line_summary NEWLINE ( NEWLINE )? (lg= line_goal NEWLINE NEWLINE )? section_details NEWLINE NEWLINE line_version NEWLINE NEWLINE ( section_comments )? section_stat (st= section_trace )? (lc= line_cfs )?
            {
            pushFollow(FOLLOW_line_input_in_result285);
            line_input();

            state._fsp--;


            match(input,NEWLINE,FOLLOW_NEWLINE_in_result287); 

            pushFollow(FOLLOW_line_summary_in_result291);
            ls=line_summary();

            state._fsp--;


            match(input,NEWLINE,FOLLOW_NEWLINE_in_result293); 

            // Satmc.g:49:50: ( NEWLINE )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==NEWLINE) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // Satmc.g:49:50: NEWLINE
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_result295); 

                    }
                    break;

            }


            // Satmc.g:49:59: (lg= line_goal NEWLINE NEWLINE )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==GOAL||LA7_0==WS) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // Satmc.g:49:61: lg= line_goal NEWLINE NEWLINE
                    {
                    pushFollow(FOLLOW_line_goal_in_result302);
                    lg=line_goal();

                    state._fsp--;


                    match(input,NEWLINE,FOLLOW_NEWLINE_in_result304); 

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_result306); 

                    }
                    break;

            }


            pushFollow(FOLLOW_section_details_in_result311);
            section_details();

            state._fsp--;


            match(input,NEWLINE,FOLLOW_NEWLINE_in_result313); 

            match(input,NEWLINE,FOLLOW_NEWLINE_in_result315); 

            pushFollow(FOLLOW_line_version_in_result317);
            line_version();

            state._fsp--;


            match(input,NEWLINE,FOLLOW_NEWLINE_in_result319); 

            match(input,NEWLINE,FOLLOW_NEWLINE_in_result321); 

            // Satmc.g:49:154: ( section_comments )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==COMMENTS) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // Satmc.g:49:154: section_comments
                    {
                    pushFollow(FOLLOW_section_comments_in_result323);
                    section_comments();

                    state._fsp--;


                    }
                    break;

            }


            pushFollow(FOLLOW_section_stat_in_result326);
            section_stat();

            state._fsp--;


            // Satmc.g:49:187: (st= section_trace )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==TRACE) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // Satmc.g:49:187: st= section_trace
                    {
                    pushFollow(FOLLOW_section_trace_in_result330);
                    st=section_trace();

                    state._fsp--;


                    }
                    break;

            }


            // Satmc.g:49:205: (lc= line_cfs )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==CFS) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // Satmc.g:49:205: lc= line_cfs
                    {
                    pushFollow(FOLLOW_line_cfs_in_result335);
                    lc=line_cfs();

                    state._fsp--;


                    }
                    break;

            }


            message = new SatmcMessage(ls, lg, st, lc);

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return message;
    }
    // $ANTLR end "result"



    // $ANTLR start "section_warning"
    // Satmc.g:53:1: section_warning : WARNING ( (~ NEWLINE )+ NEWLINE )* NEWLINE ;
    public final void section_warning() throws RecognitionException {
        try {
            // Satmc.g:54:5: ( WARNING ( (~ NEWLINE )+ NEWLINE )* NEWLINE )
            // Satmc.g:54:7: WARNING ( (~ NEWLINE )+ NEWLINE )* NEWLINE
            {
            match(input,WARNING,FOLLOW_WARNING_in_section_warning363); 

            // Satmc.g:54:15: ( (~ NEWLINE )+ NEWLINE )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0 >= CFS && LA12_0 <= INPUT)||(LA12_0 >= NUMBER && LA12_0 <= WS)) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // Satmc.g:54:17: (~ NEWLINE )+ NEWLINE
            	    {
            	    // Satmc.g:54:17: (~ NEWLINE )+
            	    int cnt11=0;
            	    loop11:
            	    do {
            	        int alt11=2;
            	        int LA11_0 = input.LA(1);

            	        if ( ((LA11_0 >= CFS && LA11_0 <= INPUT)||(LA11_0 >= NUMBER && LA11_0 <= WS)) ) {
            	            alt11=1;
            	        }


            	        switch (alt11) {
            	    	case 1 :
            	    	    // Satmc.g:
            	    	    {
            	    	    if ( (input.LA(1) >= CFS && input.LA(1) <= INPUT)||(input.LA(1) >= NUMBER && input.LA(1) <= WS) ) {
            	    	        input.consume();
            	    	        state.errorRecovery=false;
            	    	    }
            	    	    else {
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        throw mse;
            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt11 >= 1 ) break loop11;
            	                EarlyExitException eee =
            	                    new EarlyExitException(11, input);
            	                throw eee;
            	        }
            	        cnt11++;
            	    } while (true);


            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_section_warning375); 

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            match(input,NEWLINE,FOLLOW_NEWLINE_in_section_warning380); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "section_warning"



    // $ANTLR start "section_error"
    // Satmc.g:57:1: section_error : ERROR ( (~ NEWLINE )+ NEWLINE )* NEWLINE NEWLINE ( PERCENT ( (~ NEWLINE )+ NEWLINE )* NEWLINE )? ;
    public final void section_error() throws RecognitionException {
        try {
            // Satmc.g:58:5: ( ERROR ( (~ NEWLINE )+ NEWLINE )* NEWLINE NEWLINE ( PERCENT ( (~ NEWLINE )+ NEWLINE )* NEWLINE )? )
            // Satmc.g:58:7: ERROR ( (~ NEWLINE )+ NEWLINE )* NEWLINE NEWLINE ( PERCENT ( (~ NEWLINE )+ NEWLINE )* NEWLINE )?
            {
            match(input,ERROR,FOLLOW_ERROR_in_section_error401); 

            // Satmc.g:58:13: ( (~ NEWLINE )+ NEWLINE )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0 >= CFS && LA14_0 <= INPUT)||(LA14_0 >= NUMBER && LA14_0 <= WS)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // Satmc.g:58:15: (~ NEWLINE )+ NEWLINE
            	    {
            	    // Satmc.g:58:15: (~ NEWLINE )+
            	    int cnt13=0;
            	    loop13:
            	    do {
            	        int alt13=2;
            	        int LA13_0 = input.LA(1);

            	        if ( ((LA13_0 >= CFS && LA13_0 <= INPUT)||(LA13_0 >= NUMBER && LA13_0 <= WS)) ) {
            	            alt13=1;
            	        }


            	        switch (alt13) {
            	    	case 1 :
            	    	    // Satmc.g:
            	    	    {
            	    	    if ( (input.LA(1) >= CFS && input.LA(1) <= INPUT)||(input.LA(1) >= NUMBER && input.LA(1) <= WS) ) {
            	    	        input.consume();
            	    	        state.errorRecovery=false;
            	    	    }
            	    	    else {
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        throw mse;
            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt13 >= 1 ) break loop13;
            	                EarlyExitException eee =
            	                    new EarlyExitException(13, input);
            	                throw eee;
            	        }
            	        cnt13++;
            	    } while (true);


            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_section_error413); 

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            match(input,NEWLINE,FOLLOW_NEWLINE_in_section_error418); 

            match(input,NEWLINE,FOLLOW_NEWLINE_in_section_error420); 

            // Satmc.g:58:56: ( PERCENT ( (~ NEWLINE )+ NEWLINE )* NEWLINE )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==PERCENT) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // Satmc.g:58:58: PERCENT ( (~ NEWLINE )+ NEWLINE )* NEWLINE
                    {
                    match(input,PERCENT,FOLLOW_PERCENT_in_section_error424); 

                    // Satmc.g:58:66: ( (~ NEWLINE )+ NEWLINE )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( ((LA16_0 >= CFS && LA16_0 <= INPUT)||(LA16_0 >= NUMBER && LA16_0 <= WS)) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // Satmc.g:58:68: (~ NEWLINE )+ NEWLINE
                    	    {
                    	    // Satmc.g:58:68: (~ NEWLINE )+
                    	    int cnt15=0;
                    	    loop15:
                    	    do {
                    	        int alt15=2;
                    	        int LA15_0 = input.LA(1);

                    	        if ( ((LA15_0 >= CFS && LA15_0 <= INPUT)||(LA15_0 >= NUMBER && LA15_0 <= WS)) ) {
                    	            alt15=1;
                    	        }


                    	        switch (alt15) {
                    	    	case 1 :
                    	    	    // Satmc.g:
                    	    	    {
                    	    	    if ( (input.LA(1) >= CFS && input.LA(1) <= INPUT)||(input.LA(1) >= NUMBER && input.LA(1) <= WS) ) {
                    	    	        input.consume();
                    	    	        state.errorRecovery=false;
                    	    	    }
                    	    	    else {
                    	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	    	        throw mse;
                    	    	    }


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    if ( cnt15 >= 1 ) break loop15;
                    	                EarlyExitException eee =
                    	                    new EarlyExitException(15, input);
                    	                throw eee;
                    	        }
                    	        cnt15++;
                    	    } while (true);


                    	    match(input,NEWLINE,FOLLOW_NEWLINE_in_section_error436); 

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);


                    match(input,NEWLINE,FOLLOW_NEWLINE_in_section_error441); 

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "section_error"



    // $ANTLR start "line_input"
    // Satmc.g:61:1: line_input : INPUT WS target ( WS )? ;
    public final void line_input() throws RecognitionException {
        try {
            // Satmc.g:62:5: ( INPUT WS target ( WS )? )
            // Satmc.g:62:7: INPUT WS target ( WS )?
            {
            match(input,INPUT,FOLLOW_INPUT_in_line_input461); 

            match(input,WS,FOLLOW_WS_in_line_input463); 

            pushFollow(FOLLOW_target_in_line_input465);
            target();

            state._fsp--;


            // Satmc.g:62:23: ( WS )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==WS) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // Satmc.g:62:23: WS
                    {
                    match(input,WS,FOLLOW_WS_in_line_input467); 

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "line_input"



    // $ANTLR start "line_summary"
    // Satmc.g:65:1: line_summary returns [Summary res] : SUMMARY WS sr= summary_result ( WS )? ;
    public final Summary line_summary() throws RecognitionException {
        Summary res = null;


        SatmcParser.summary_result_return sr =null;


        try {
            // Satmc.g:66:5: ( SUMMARY WS sr= summary_result ( WS )? )
            // Satmc.g:66:7: SUMMARY WS sr= summary_result ( WS )?
            {
            match(input,SUMMARY,FOLLOW_SUMMARY_in_line_summary493); 

            match(input,WS,FOLLOW_WS_in_line_summary495); 

            pushFollow(FOLLOW_summary_result_in_line_summary499);
            sr=summary_result();

            state._fsp--;


            // Satmc.g:66:36: ( WS )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==WS) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // Satmc.g:66:36: WS
                    {
                    match(input,WS,FOLLOW_WS_in_line_summary501); 

                    }
                    break;

            }


            if ("ATTACK_FOUND".equals((sr!=null?input.toString(sr.start,sr.stop):null))) {
                						res = Summary.ATTACK_FOUND;
                					  } else if ("NO_ATTACK_FOUND".equals((sr!=null?input.toString(sr.start,sr.stop):null))) {
                					  	res = Summary.NO_ATTACK_FOUND;
                					  } else if ("INCONCLUSIVE".equals((sr!=null?input.toString(sr.start,sr.stop):null))) {
                					  	res = Summary.INCONCLUSIVE;
                					  } else {
                					  	res = Summary.UNKNOWN;
                					  }
                					 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return res;
    }
    // $ANTLR end "line_summary"



    // $ANTLR start "line_goal"
    // Satmc.g:78:1: line_goal returns [SatmcFunction res] : ( WS )? GOAL WS f= function ( WS )? ;
    public final SatmcFunction line_goal() throws RecognitionException {
        SatmcFunction res = null;


        SatmcFunction f =null;


        try {
            // Satmc.g:79:5: ( ( WS )? GOAL WS f= function ( WS )? )
            // Satmc.g:79:7: ( WS )? GOAL WS f= function ( WS )?
            {
            // Satmc.g:79:7: ( WS )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==WS) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // Satmc.g:79:7: WS
                    {
                    match(input,WS,FOLLOW_WS_in_line_goal529); 

                    }
                    break;

            }


            match(input,GOAL,FOLLOW_GOAL_in_line_goal532); 

            match(input,WS,FOLLOW_WS_in_line_goal534); 

            pushFollow(FOLLOW_function_in_line_goal538);
            f=function();

            state._fsp--;


            res = f;

            // Satmc.g:79:47: ( WS )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==WS) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // Satmc.g:79:47: WS
                    {
                    match(input,WS,FOLLOW_WS_in_line_goal542); 

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return res;
    }
    // $ANTLR end "line_goal"



    // $ANTLR start "section_details"
    // Satmc.g:82:1: section_details : line_details ( NEWLINE line_detail )* ;
    public final void section_details() throws RecognitionException {
        try {
            // Satmc.g:83:5: ( line_details ( NEWLINE line_detail )* )
            // Satmc.g:83:7: line_details ( NEWLINE line_detail )*
            {
            pushFollow(FOLLOW_line_details_in_section_details560);
            line_details();

            state._fsp--;


            // Satmc.g:83:20: ( NEWLINE line_detail )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==NEWLINE) ) {
                    int LA22_1 = input.LA(2);

                    if ( (LA22_1==CONSTANT||LA22_1==WS) ) {
                        alt22=1;
                    }


                }


                switch (alt22) {
            	case 1 :
            	    // Satmc.g:83:22: NEWLINE line_detail
            	    {
            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_section_details564); 

            	    pushFollow(FOLLOW_line_detail_in_section_details566);
            	    line_detail();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "section_details"



    // $ANTLR start "line_details"
    // Satmc.g:86:1: line_details : DETAILS ( WS )? ;
    public final void line_details() throws RecognitionException {
        try {
            // Satmc.g:87:5: ( DETAILS ( WS )? )
            // Satmc.g:87:7: DETAILS ( WS )?
            {
            match(input,DETAILS,FOLLOW_DETAILS_in_line_details586); 

            // Satmc.g:87:15: ( WS )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==WS) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // Satmc.g:87:15: WS
                    {
                    match(input,WS,FOLLOW_WS_in_line_details588); 

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "line_details"



    // $ANTLR start "line_detail"
    // Satmc.g:90:1: line_detail : ( WS )? CONSTANT ( WS )? ;
    public final void line_detail() throws RecognitionException {
        try {
            // Satmc.g:91:5: ( ( WS )? CONSTANT ( WS )? )
            // Satmc.g:91:7: ( WS )? CONSTANT ( WS )?
            {
            // Satmc.g:91:7: ( WS )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==WS) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // Satmc.g:91:7: WS
                    {
                    match(input,WS,FOLLOW_WS_in_line_detail606); 

                    }
                    break;

            }


            match(input,CONSTANT,FOLLOW_CONSTANT_in_line_detail609); 

            // Satmc.g:91:20: ( WS )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==WS) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // Satmc.g:91:20: WS
                    {
                    match(input,WS,FOLLOW_WS_in_line_detail611); 

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "line_detail"



    // $ANTLR start "line_version"
    // Satmc.g:94:1: line_version : VERSION WS VERSION_NR ;
    public final void line_version() throws RecognitionException {
        try {
            // Satmc.g:95:5: ( VERSION WS VERSION_NR )
            // Satmc.g:95:7: VERSION WS VERSION_NR
            {
            match(input,VERSION,FOLLOW_VERSION_in_line_version629); 

            match(input,WS,FOLLOW_WS_in_line_version631); 

            match(input,VERSION_NR,FOLLOW_VERSION_NR_in_line_version633); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "line_version"



    // $ANTLR start "section_comments"
    // Satmc.g:98:1: section_comments : COMMENTS ( WS )? NEWLINE ( (~ NEWLINE )+ NEWLINE )* NEWLINE ;
    public final void section_comments() throws RecognitionException {
        try {
            // Satmc.g:99:5: ( COMMENTS ( WS )? NEWLINE ( (~ NEWLINE )+ NEWLINE )* NEWLINE )
            // Satmc.g:99:7: COMMENTS ( WS )? NEWLINE ( (~ NEWLINE )+ NEWLINE )* NEWLINE
            {
            match(input,COMMENTS,FOLLOW_COMMENTS_in_section_comments654); 

            // Satmc.g:99:16: ( WS )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==WS) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // Satmc.g:99:16: WS
                    {
                    match(input,WS,FOLLOW_WS_in_section_comments656); 

                    }
                    break;

            }


            match(input,NEWLINE,FOLLOW_NEWLINE_in_section_comments659); 

            // Satmc.g:99:28: ( (~ NEWLINE )+ NEWLINE )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( ((LA28_0 >= CFS && LA28_0 <= INPUT)||(LA28_0 >= NUMBER && LA28_0 <= WS)) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // Satmc.g:99:30: (~ NEWLINE )+ NEWLINE
            	    {
            	    // Satmc.g:99:30: (~ NEWLINE )+
            	    int cnt27=0;
            	    loop27:
            	    do {
            	        int alt27=2;
            	        int LA27_0 = input.LA(1);

            	        if ( ((LA27_0 >= CFS && LA27_0 <= INPUT)||(LA27_0 >= NUMBER && LA27_0 <= WS)) ) {
            	            alt27=1;
            	        }


            	        switch (alt27) {
            	    	case 1 :
            	    	    // Satmc.g:
            	    	    {
            	    	    if ( (input.LA(1) >= CFS && input.LA(1) <= INPUT)||(input.LA(1) >= NUMBER && input.LA(1) <= WS) ) {
            	    	        input.consume();
            	    	        state.errorRecovery=false;
            	    	    }
            	    	    else {
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        throw mse;
            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt27 >= 1 ) break loop27;
            	                EarlyExitException eee =
            	                    new EarlyExitException(27, input);
            	                throw eee;
            	        }
            	        cnt27++;
            	    } while (true);


            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_section_comments671); 

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);


            match(input,NEWLINE,FOLLOW_NEWLINE_in_section_comments676); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "section_comments"



    // $ANTLR start "section_stat"
    // Satmc.g:102:1: section_stat : STAT WS ( (~ NEWLINE )+ NEWLINE )* NEWLINE ;
    public final void section_stat() throws RecognitionException {
        try {
            // Satmc.g:103:5: ( STAT WS ( (~ NEWLINE )+ NEWLINE )* NEWLINE )
            // Satmc.g:103:7: STAT WS ( (~ NEWLINE )+ NEWLINE )* NEWLINE
            {
            match(input,STAT,FOLLOW_STAT_in_section_stat697); 

            match(input,WS,FOLLOW_WS_in_section_stat699); 

            // Satmc.g:103:15: ( (~ NEWLINE )+ NEWLINE )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( ((LA30_0 >= CFS && LA30_0 <= INPUT)||(LA30_0 >= NUMBER && LA30_0 <= WS)) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // Satmc.g:103:17: (~ NEWLINE )+ NEWLINE
            	    {
            	    // Satmc.g:103:17: (~ NEWLINE )+
            	    int cnt29=0;
            	    loop29:
            	    do {
            	        int alt29=2;
            	        int LA29_0 = input.LA(1);

            	        if ( ((LA29_0 >= CFS && LA29_0 <= INPUT)||(LA29_0 >= NUMBER && LA29_0 <= WS)) ) {
            	            alt29=1;
            	        }


            	        switch (alt29) {
            	    	case 1 :
            	    	    // Satmc.g:
            	    	    {
            	    	    if ( (input.LA(1) >= CFS && input.LA(1) <= INPUT)||(input.LA(1) >= NUMBER && input.LA(1) <= WS) ) {
            	    	        input.consume();
            	    	        state.errorRecovery=false;
            	    	    }
            	    	    else {
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        throw mse;
            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt29 >= 1 ) break loop29;
            	                EarlyExitException eee =
            	                    new EarlyExitException(29, input);
            	                throw eee;
            	        }
            	        cnt29++;
            	    } while (true);


            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_section_stat711); 

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            match(input,NEWLINE,FOLLOW_NEWLINE_in_section_stat716); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "section_stat"



    // $ANTLR start "section_trace"
    // Satmc.g:106:1: section_trace returns [List<SatmcTraceStep> res] : TRACE NEWLINE ( NUMBER NEWLINE lc= line_clauses NEWLINE (lr= line_rules NEWLINE )? )+ ;
    public final List<SatmcTraceStep> section_trace() throws RecognitionException {
        List<SatmcTraceStep> res = null;


        List<SatmcFunction> lc =null;

        List<SatmcFunction> lr =null;


        try {
            // Satmc.g:107:5: ( TRACE NEWLINE ( NUMBER NEWLINE lc= line_clauses NEWLINE (lr= line_rules NEWLINE )? )+ )
            // Satmc.g:107:7: TRACE NEWLINE ( NUMBER NEWLINE lc= line_clauses NEWLINE (lr= line_rules NEWLINE )? )+
            {
            match(input,TRACE,FOLLOW_TRACE_in_section_trace741); 

             res = new ArrayList<SatmcTraceStep>(); 

            match(input,NEWLINE,FOLLOW_NEWLINE_in_section_trace745); 

            // Satmc.g:108:7: ( NUMBER NEWLINE lc= line_clauses NEWLINE (lr= line_rules NEWLINE )? )+
            int cnt32=0;
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==NUMBER) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // Satmc.g:108:9: NUMBER NEWLINE lc= line_clauses NEWLINE (lr= line_rules NEWLINE )?
            	    {
            	    match(input,NUMBER,FOLLOW_NUMBER_in_section_trace756); 

            	    boolean rulesPresent = false;

            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_section_trace769); 

            	    pushFollow(FOLLOW_line_clauses_in_section_trace773);
            	    lc=line_clauses();

            	    state._fsp--;


            	    match(input,NEWLINE,FOLLOW_NEWLINE_in_section_trace775); 

            	    // Satmc.g:109:41: (lr= line_rules NEWLINE )?
            	    int alt31=2;
            	    int LA31_0 = input.LA(1);

            	    if ( (LA31_0==RULES||LA31_0==WS) ) {
            	        alt31=1;
            	    }
            	    switch (alt31) {
            	        case 1 :
            	            // Satmc.g:109:43: lr= line_rules NEWLINE
            	            {
            	            pushFollow(FOLLOW_line_rules_in_section_trace781);
            	            lr=line_rules();

            	            state._fsp--;


            	            rulesPresent=true;

            	            match(input,NEWLINE,FOLLOW_NEWLINE_in_section_trace794); 

            	            }
            	            break;

            	    }


            	    if (rulesPresent) 
            	    	    		res.add(new SatmcTraceStep(lc, lr)); 
            	    	    	else 
            	    	    		res.add(new SatmcTraceStep(lc, new ArrayList<SatmcFunction>()));

            	    }
            	    break;

            	default :
            	    if ( cnt32 >= 1 ) break loop32;
                        EarlyExitException eee =
                            new EarlyExitException(32, input);
                        throw eee;
                }
                cnt32++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return res;
    }
    // $ANTLR end "section_trace"



    // $ANTLR start "line_clauses"
    // Satmc.g:118:1: line_clauses returns [List<SatmcFunction> res] : ( WS )? CLAUSES fs= function_set ;
    public final List<SatmcFunction> line_clauses() throws RecognitionException {
        List<SatmcFunction> res = null;


        List<SatmcFunction> fs =null;


        try {
            // Satmc.g:119:5: ( ( WS )? CLAUSES fs= function_set )
            // Satmc.g:119:7: ( WS )? CLAUSES fs= function_set
            {
            // Satmc.g:119:7: ( WS )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==WS) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // Satmc.g:119:7: WS
                    {
                    match(input,WS,FOLLOW_WS_in_line_clauses841); 

                    }
                    break;

            }


            match(input,CLAUSES,FOLLOW_CLAUSES_in_line_clauses844); 

            pushFollow(FOLLOW_function_set_in_line_clauses848);
            fs=function_set();

            state._fsp--;


             res = fs; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return res;
    }
    // $ANTLR end "line_clauses"



    // $ANTLR start "line_rules"
    // Satmc.g:122:1: line_rules returns [List<SatmcFunction> res] : ( WS )? RULES WS (f= function |fs= function_set |fn= fname ) ;
    public final List<SatmcFunction> line_rules() throws RecognitionException {
        List<SatmcFunction> res = null;


        SatmcFunction f =null;

        List<SatmcFunction> fs =null;

        SatmcParser.fname_return fn =null;


        try {
            // Satmc.g:123:5: ( ( WS )? RULES WS (f= function |fs= function_set |fn= fname ) )
            // Satmc.g:123:7: ( WS )? RULES WS (f= function |fs= function_set |fn= fname )
            {
            // Satmc.g:123:7: ( WS )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==WS) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // Satmc.g:123:7: WS
                    {
                    match(input,WS,FOLLOW_WS_in_line_rules875); 

                    }
                    break;

            }


            match(input,RULES,FOLLOW_RULES_in_line_rules878); 

            match(input,WS,FOLLOW_WS_in_line_rules880); 

            // Satmc.g:123:20: (f= function |fs= function_set |fn= fname )
            int alt35=3;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==FTEXT) ) {
                int LA35_1 = input.LA(2);

                if ( (LA35_1==O_PARENTHESIS) ) {
                    alt35=1;
                }
                else if ( (LA35_1==NEWLINE) ) {
                    alt35=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 35, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA35_0==O_BRACES) ) {
                alt35=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;

            }
            switch (alt35) {
                case 1 :
                    // Satmc.g:123:22: f= function
                    {
                    pushFollow(FOLLOW_function_in_line_rules886);
                    f=function();

                    state._fsp--;


                     res = new ArrayList<SatmcFunction>(); res.add(f);

                    }
                    break;
                case 2 :
                    // Satmc.g:124:14: fs= function_set
                    {
                    pushFollow(FOLLOW_function_set_in_line_rules905);
                    fs=function_set();

                    state._fsp--;


                     res = fs; 

                    }
                    break;
                case 3 :
                    // Satmc.g:125:14: fn= fname
                    {
                    pushFollow(FOLLOW_fname_in_line_rules924);
                    fn=fname();

                    state._fsp--;


                     res = new ArrayList<SatmcFunction>(); 
                        		                          res.add(new SatmcFunction((fn!=null?input.toString(fn.start,fn.stop):null), new ArrayList<SatmcFact>()));

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return res;
    }
    // $ANTLR end "line_rules"



    // $ANTLR start "line_cfs"
    // Satmc.g:129:1: line_cfs returns [List<SatmcFunction> res] : CFS NEWLINE fs= function_set ;
    public final List<SatmcFunction> line_cfs() throws RecognitionException {
        List<SatmcFunction> res = null;


        List<SatmcFunction> fs =null;


        try {
            // Satmc.g:130:5: ( CFS NEWLINE fs= function_set )
            // Satmc.g:130:7: CFS NEWLINE fs= function_set
            {
            match(input,CFS,FOLLOW_CFS_in_line_cfs952); 

            match(input,NEWLINE,FOLLOW_NEWLINE_in_line_cfs954); 

            pushFollow(FOLLOW_function_set_in_line_cfs958);
            fs=function_set();

            state._fsp--;


             res = fs; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return res;
    }
    // $ANTLR end "line_cfs"



    // $ANTLR start "target"
    // Satmc.g:133:1: target : FILE ;
    public final void target() throws RecognitionException {
        try {
            // Satmc.g:134:5: ( FILE )
            // Satmc.g:134:7: FILE
            {
            match(input,FILE,FOLLOW_FILE_in_target977); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "target"


    public static class summary_result_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "summary_result"
    // Satmc.g:137:1: summary_result : CONSTANT ;
    public final SatmcParser.summary_result_return summary_result() throws RecognitionException {
        SatmcParser.summary_result_return retval = new SatmcParser.summary_result_return();
        retval.start = input.LT(1);


        try {
            // Satmc.g:138:5: ( CONSTANT )
            // Satmc.g:138:7: CONSTANT
            {
            match(input,CONSTANT,FOLLOW_CONSTANT_in_summary_result994); 

            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "summary_result"



    // $ANTLR start "function_set"
    // Satmc.g:141:1: function_set returns [List<SatmcFunction> res] : O_BRACES WS (f1= function ( COMMA fn= function )* )? ( WS )? C_BRACES ;
    public final List<SatmcFunction> function_set() throws RecognitionException {
        List<SatmcFunction> res = null;


        SatmcFunction f1 =null;

        SatmcFunction fn =null;


        try {
            // Satmc.g:142:5: ( O_BRACES WS (f1= function ( COMMA fn= function )* )? ( WS )? C_BRACES )
            // Satmc.g:142:7: O_BRACES WS (f1= function ( COMMA fn= function )* )? ( WS )? C_BRACES
            {
            match(input,O_BRACES,FOLLOW_O_BRACES_in_function_set1019); 

            match(input,WS,FOLLOW_WS_in_function_set1021); 

             res = new ArrayList<SatmcFunction>();
                		    

            // Satmc.g:144:11: (f1= function ( COMMA fn= function )* )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==FTEXT) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // Satmc.g:144:13: f1= function ( COMMA fn= function )*
                    {
                    pushFollow(FOLLOW_function_in_function_set1040);
                    f1=function();

                    state._fsp--;


                     res.add(f1);
                        			            

                    // Satmc.g:146:20: ( COMMA fn= function )*
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==COMMA) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // Satmc.g:146:22: COMMA fn= function
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_function_set1065); 

                    	    pushFollow(FOLLOW_function_in_function_set1069);
                    	    fn=function();

                    	    state._fsp--;


                    	     res.add(fn); 

                    	    }
                    	    break;

                    	default :
                    	    break loop36;
                        }
                    } while (true);


                    }
                    break;

            }


            // Satmc.g:146:69: ( WS )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==WS) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // Satmc.g:146:69: WS
                    {
                    match(input,WS,FOLLOW_WS_in_function_set1079); 

                    }
                    break;

            }


            match(input,C_BRACES,FOLLOW_C_BRACES_in_function_set1082); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return res;
    }
    // $ANTLR end "function_set"



    // $ANTLR start "function"
    // Satmc.g:149:1: function returns [SatmcFunction res] : fn= fname O_PARENTHESIS fv= fvar ( COMMA fvl= fvar )* C_PARENTHESIS ;
    public final SatmcFunction function() throws RecognitionException {
        SatmcFunction res = null;


        SatmcParser.fname_return fn =null;

        SatmcFact fv =null;

        SatmcFact fvl =null;


        try {
            // Satmc.g:150:5: (fn= fname O_PARENTHESIS fv= fvar ( COMMA fvl= fvar )* C_PARENTHESIS )
            // Satmc.g:150:7: fn= fname O_PARENTHESIS fv= fvar ( COMMA fvl= fvar )* C_PARENTHESIS
            {
            pushFollow(FOLLOW_fname_in_function1109);
            fn=fname();

            state._fsp--;


             res = new SatmcFunction((fn!=null?input.toString(fn.start,fn.stop):null), new ArrayList<SatmcFact>());
                		 

            match(input,O_PARENTHESIS,FOLLOW_O_PARENTHESIS_in_function1120); 

            pushFollow(FOLLOW_fvar_in_function1125);
            fv=fvar();

            state._fsp--;


             res.args.add(fv);
                		 			

            // Satmc.g:154:11: ( COMMA fvl= fvar )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==COMMA) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // Satmc.g:154:13: COMMA fvl= fvar
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_function1141); 

            	    pushFollow(FOLLOW_fvar_in_function1145);
            	    fvl=fvar();

            	    state._fsp--;


            	     res.args.add(fvl);
            	        		 			  		 

            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);


            match(input,C_PARENTHESIS,FOLLOW_C_PARENTHESIS_in_function1167); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return res;
    }
    // $ANTLR end "function"


    public static class var_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "var"
    // Satmc.g:159:1: var : ( FTEXT | NUMBER );
    public final SatmcParser.var_return var() throws RecognitionException {
        SatmcParser.var_return retval = new SatmcParser.var_return();
        retval.start = input.LT(1);


        try {
            // Satmc.g:160:5: ( FTEXT | NUMBER )
            // Satmc.g:
            {
            if ( input.LA(1)==FTEXT||input.LA(1)==NUMBER ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "var"


    public static class fname_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "fname"
    // Satmc.g:164:1: fname : FTEXT ;
    public final SatmcParser.fname_return fname() throws RecognitionException {
        SatmcParser.fname_return retval = new SatmcParser.fname_return();
        retval.start = input.LT(1);


        try {
            // Satmc.g:165:5: ( FTEXT )
            // Satmc.g:165:7: FTEXT
            {
            match(input,FTEXT,FOLLOW_FTEXT_in_fname1217); 

            }

            retval.stop = input.LT(-1);


        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "fname"



    // $ANTLR start "fvar"
    // Satmc.g:168:1: fvar returns [SatmcFact res] : (r1= var |r2= function );
    public final SatmcFact fvar() throws RecognitionException {
        SatmcFact res = null;


        SatmcParser.var_return r1 =null;

        SatmcFunction r2 =null;


        try {
            // Satmc.g:169:5: (r1= var |r2= function )
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==FTEXT) ) {
                int LA40_1 = input.LA(2);

                if ( (LA40_1==COMMA||LA40_1==C_PARENTHESIS) ) {
                    alt40=1;
                }
                else if ( (LA40_1==O_PARENTHESIS) ) {
                    alt40=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 40, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA40_0==NUMBER) ) {
                alt40=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;

            }
            switch (alt40) {
                case 1 :
                    // Satmc.g:169:7: r1= var
                    {
                    pushFollow(FOLLOW_var_in_fvar1244);
                    r1=var();

                    state._fsp--;


                    res = new SatmcVar((r1!=null?input.toString(r1.start,r1.stop):null));

                    }
                    break;
                case 2 :
                    // Satmc.g:170:7: r2= function
                    {
                    pushFollow(FOLLOW_function_in_fvar1256);
                    r2=function();

                    state._fsp--;


                    res = r2;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return res;
    }
    // $ANTLR end "fvar"

    // Delegated rules


 

    public static final BitSet FOLLOW_NEWLINE_in_output168 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_NEWLINE_in_output173 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_section_warning_in_output175 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_error_in_output182 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_result_in_output206 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NEWLINE_in_output228 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_EOF_in_output231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_error258 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_section_error_in_error260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_line_input_in_result285 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NEWLINE_in_result287 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_line_summary_in_result291 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NEWLINE_in_result293 = new BitSet(new long[]{0x0000000080051000L});
    public static final BitSet FOLLOW_NEWLINE_in_result295 = new BitSet(new long[]{0x0000000080011000L});
    public static final BitSet FOLLOW_line_goal_in_result302 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NEWLINE_in_result304 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NEWLINE_in_result306 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_section_details_in_result311 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NEWLINE_in_result313 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NEWLINE_in_result315 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_line_version_in_result317 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NEWLINE_in_result319 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NEWLINE_in_result321 = new BitSet(new long[]{0x0000000002000080L});
    public static final BitSet FOLLOW_section_comments_in_result323 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_section_stat_in_result326 = new BitSet(new long[]{0x0000000008000012L});
    public static final BitSet FOLLOW_section_trace_in_result330 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_line_cfs_in_result335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WARNING_in_section_warning363 = new BitSet(new long[]{0x00000000FFFFFFF0L});
    public static final BitSet FOLLOW_NEWLINE_in_section_warning375 = new BitSet(new long[]{0x00000000FFFFFFF0L});
    public static final BitSet FOLLOW_NEWLINE_in_section_warning380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ERROR_in_section_error401 = new BitSet(new long[]{0x00000000FFFFFFF0L});
    public static final BitSet FOLLOW_NEWLINE_in_section_error413 = new BitSet(new long[]{0x00000000FFFFFFF0L});
    public static final BitSet FOLLOW_NEWLINE_in_section_error418 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NEWLINE_in_section_error420 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_PERCENT_in_section_error424 = new BitSet(new long[]{0x00000000FFFFFFF0L});
    public static final BitSet FOLLOW_NEWLINE_in_section_error436 = new BitSet(new long[]{0x00000000FFFFFFF0L});
    public static final BitSet FOLLOW_NEWLINE_in_section_error441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INPUT_in_line_input461 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_WS_in_line_input463 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_target_in_line_input465 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_WS_in_line_input467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUMMARY_in_line_summary493 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_WS_in_line_summary495 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_summary_result_in_line_summary499 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_WS_in_line_summary501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_line_goal529 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_GOAL_in_line_goal532 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_WS_in_line_goal534 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_function_in_line_goal538 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_WS_in_line_goal542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_line_details_in_section_details560 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_NEWLINE_in_section_details564 = new BitSet(new long[]{0x0000000080000100L});
    public static final BitSet FOLLOW_line_detail_in_section_details566 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_DETAILS_in_line_details586 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_WS_in_line_details588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_line_detail606 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_CONSTANT_in_line_detail609 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_WS_in_line_detail611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VERSION_in_line_version629 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_WS_in_line_version631 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_VERSION_NR_in_line_version633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMENTS_in_section_comments654 = new BitSet(new long[]{0x0000000080040000L});
    public static final BitSet FOLLOW_WS_in_section_comments656 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NEWLINE_in_section_comments659 = new BitSet(new long[]{0x00000000FFFFFFF0L});
    public static final BitSet FOLLOW_NEWLINE_in_section_comments671 = new BitSet(new long[]{0x00000000FFFFFFF0L});
    public static final BitSet FOLLOW_NEWLINE_in_section_comments676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAT_in_section_stat697 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_WS_in_section_stat699 = new BitSet(new long[]{0x00000000FFFFFFF0L});
    public static final BitSet FOLLOW_NEWLINE_in_section_stat711 = new BitSet(new long[]{0x00000000FFFFFFF0L});
    public static final BitSet FOLLOW_NEWLINE_in_section_stat716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRACE_in_section_trace741 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NEWLINE_in_section_trace745 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_NUMBER_in_section_trace756 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NEWLINE_in_section_trace769 = new BitSet(new long[]{0x0000000080000020L});
    public static final BitSet FOLLOW_line_clauses_in_section_trace773 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NEWLINE_in_section_trace775 = new BitSet(new long[]{0x0000000081080002L});
    public static final BitSet FOLLOW_line_rules_in_section_trace781 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NEWLINE_in_section_trace794 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_WS_in_line_clauses841 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_CLAUSES_in_line_clauses844 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_function_set_in_line_clauses848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WS_in_line_rules875 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RULES_in_line_rules878 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_WS_in_line_rules880 = new BitSet(new long[]{0x0000000000108000L});
    public static final BitSet FOLLOW_function_in_line_rules886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_set_in_line_rules905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fname_in_line_rules924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CFS_in_line_cfs952 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_NEWLINE_in_line_cfs954 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_function_set_in_line_cfs958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FILE_in_target977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONSTANT_in_summary_result994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_O_BRACES_in_function_set1019 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_WS_in_function_set1021 = new BitSet(new long[]{0x0000000080008200L});
    public static final BitSet FOLLOW_function_in_function_set1040 = new BitSet(new long[]{0x0000000080000240L});
    public static final BitSet FOLLOW_COMMA_in_function_set1065 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_function_in_function_set1069 = new BitSet(new long[]{0x0000000080000240L});
    public static final BitSet FOLLOW_WS_in_function_set1079 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_C_BRACES_in_function_set1082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_fname_in_function1109 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_O_PARENTHESIS_in_function1120 = new BitSet(new long[]{0x0000000000088000L});
    public static final BitSet FOLLOW_fvar_in_function1125 = new BitSet(new long[]{0x0000000000000840L});
    public static final BitSet FOLLOW_COMMA_in_function1141 = new BitSet(new long[]{0x0000000000088000L});
    public static final BitSet FOLLOW_fvar_in_function1145 = new BitSet(new long[]{0x0000000000000840L});
    public static final BitSet FOLLOW_C_PARENTHESIS_in_function1167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FTEXT_in_fname1217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_in_fvar1244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_fvar1256 = new BitSet(new long[]{0x0000000000000002L});

}
