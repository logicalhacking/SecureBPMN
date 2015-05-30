// $ANTLR 3.4 Satmc.g 2012-01-23 13:53:58
package org.antlr.satmc;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class SatmcLexer extends Lexer {
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
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public SatmcLexer() {} 
    public SatmcLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public SatmcLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "Satmc.g"; }

    // $ANTLR start "CFS"
    public final void mCFS() throws RecognitionException {
        try {
            int _type = CFS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:4:5: ( 'CLOSED_FINAL_STATE:' )
            // Satmc.g:4:7: 'CLOSED_FINAL_STATE:'
            {
            match("CLOSED_FINAL_STATE:"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CFS"

    // $ANTLR start "CLAUSES"
    public final void mCLAUSES() throws RecognitionException {
        try {
            int _type = CLAUSES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:5:9: ( 'CLAUSES:' )
            // Satmc.g:5:11: 'CLAUSES:'
            {
            match("CLAUSES:"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CLAUSES"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:6:7: ( ',' )
            // Satmc.g:6:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "COMMENTS"
    public final void mCOMMENTS() throws RecognitionException {
        try {
            int _type = COMMENTS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:7:10: ( 'COMMENTS' )
            // Satmc.g:7:12: 'COMMENTS'
            {
            match("COMMENTS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENTS"

    // $ANTLR start "C_BRACES"
    public final void mC_BRACES() throws RecognitionException {
        try {
            int _type = C_BRACES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:8:10: ( '}' )
            // Satmc.g:8:12: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "C_BRACES"

    // $ANTLR start "C_BRACKET"
    public final void mC_BRACKET() throws RecognitionException {
        try {
            int _type = C_BRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:9:11: ( ']' )
            // Satmc.g:9:13: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "C_BRACKET"

    // $ANTLR start "C_PARENTHESIS"
    public final void mC_PARENTHESIS() throws RecognitionException {
        try {
            int _type = C_PARENTHESIS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:10:15: ( ')' )
            // Satmc.g:10:17: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "C_PARENTHESIS"

    // $ANTLR start "DETAILS"
    public final void mDETAILS() throws RecognitionException {
        try {
            int _type = DETAILS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:11:9: ( 'DETAILS' )
            // Satmc.g:11:11: 'DETAILS'
            {
            match("DETAILS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DETAILS"

    // $ANTLR start "ERROR"
    public final void mERROR() throws RecognitionException {
        try {
            int _type = ERROR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:12:7: ( '% ERROR:' )
            // Satmc.g:12:9: '% ERROR:'
            {
            match("% ERROR:"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ERROR"

    // $ANTLR start "GOAL"
    public final void mGOAL() throws RecognitionException {
        try {
            int _type = GOAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:13:6: ( 'GOAL:' )
            // Satmc.g:13:8: 'GOAL:'
            {
            match("GOAL:"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GOAL"

    // $ANTLR start "INPUT"
    public final void mINPUT() throws RecognitionException {
        try {
            int _type = INPUT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:14:7: ( 'INPUT' )
            // Satmc.g:14:9: 'INPUT'
            {
            match("INPUT"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INPUT"

    // $ANTLR start "O_BRACES"
    public final void mO_BRACES() throws RecognitionException {
        try {
            int _type = O_BRACES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:15:10: ( '{' )
            // Satmc.g:15:12: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "O_BRACES"

    // $ANTLR start "O_BRACKET"
    public final void mO_BRACKET() throws RecognitionException {
        try {
            int _type = O_BRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:16:11: ( '[' )
            // Satmc.g:16:13: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "O_BRACKET"

    // $ANTLR start "O_PARENTHESIS"
    public final void mO_PARENTHESIS() throws RecognitionException {
        try {
            int _type = O_PARENTHESIS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:17:15: ( '(' )
            // Satmc.g:17:17: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "O_PARENTHESIS"

    // $ANTLR start "PERCENT"
    public final void mPERCENT() throws RecognitionException {
        try {
            int _type = PERCENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:18:9: ( '% ' )
            // Satmc.g:18:11: '% '
            {
            match("% "); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PERCENT"

    // $ANTLR start "RULES"
    public final void mRULES() throws RecognitionException {
        try {
            int _type = RULES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:19:7: ( 'RULES:' )
            // Satmc.g:19:9: 'RULES:'
            {
            match("RULES:"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RULES"

    // $ANTLR start "STAT"
    public final void mSTAT() throws RecognitionException {
        try {
            int _type = STAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:20:6: ( 'STATISTICS' )
            // Satmc.g:20:8: 'STATISTICS'
            {
            match("STATISTICS"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STAT"

    // $ANTLR start "SUMMARY"
    public final void mSUMMARY() throws RecognitionException {
        try {
            int _type = SUMMARY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:21:9: ( 'SUMMARY' )
            // Satmc.g:21:11: 'SUMMARY'
            {
            match("SUMMARY"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SUMMARY"

    // $ANTLR start "TRACE"
    public final void mTRACE() throws RecognitionException {
        try {
            int _type = TRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:22:7: ( 'TRACE:' )
            // Satmc.g:22:9: 'TRACE:'
            {
            match("TRACE:"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TRACE"

    // $ANTLR start "VERSION"
    public final void mVERSION() throws RecognitionException {
        try {
            int _type = VERSION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:23:9: ( 'BACKEND SATMC VERSION' )
            // Satmc.g:23:11: 'BACKEND SATMC VERSION'
            {
            match("BACKEND SATMC VERSION"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VERSION"

    // $ANTLR start "WARNING"
    public final void mWARNING() throws RecognitionException {
        try {
            int _type = WARNING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:24:9: ( '% WARNING:' )
            // Satmc.g:24:11: '% WARNING:'
            {
            match("% WARNING:"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WARNING"

    // $ANTLR start "VERSION_NR"
    public final void mVERSION_NR() throws RecognitionException {
        try {
            int _type = VERSION_NR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:174:5: ( '0' .. '9' '.' '0' .. '9' '.' '0' .. '9' ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | O_PARENTHESIS | C_PARENTHESIS )* )
            // Satmc.g:174:7: '0' .. '9' '.' '0' .. '9' '.' '0' .. '9' ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | O_PARENTHESIS | C_PARENTHESIS )*
            {
            matchRange('0','9'); 

            match('.'); 

            matchRange('0','9'); 

            match('.'); 

            matchRange('0','9'); 

            // Satmc.g:174:42: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | O_PARENTHESIS | C_PARENTHESIS )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '(' && LA1_0 <= ')')||(LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Satmc.g:
            	    {
            	    if ( (input.LA(1) >= '(' && input.LA(1) <= ')')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VERSION_NR"

    // $ANTLR start "CONSTANT"
    public final void mCONSTANT() throws RecognitionException {
        try {
            int _type = CONSTANT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:178:5: ( ( 'A' .. 'Z' | '_' )+ )
            // Satmc.g:178:7: ( 'A' .. 'Z' | '_' )+
            {
            // Satmc.g:178:7: ( 'A' .. 'Z' | '_' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_') ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Satmc.g:
            	    {
            	    if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CONSTANT"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:182:4: ( ( '0' .. '9' )+ )
            // Satmc.g:182:6: ( '0' .. '9' )+
            {
            // Satmc.g:182:6: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // Satmc.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "FTEXT"
    public final void mFTEXT() throws RecognitionException {
        try {
            int _type = FTEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:186:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | NUMBER | '_' )+ )
            // Satmc.g:186:7: ( 'a' .. 'z' | 'A' .. 'Z' | NUMBER | '_' )+
            {
            // Satmc.g:186:7: ( 'a' .. 'z' | 'A' .. 'Z' | NUMBER | '_' )+
            int cnt4=0;
            loop4:
            do {
                int alt4=5;
                switch ( input.LA(1) ) {
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    {
                    alt4=1;
                    }
                    break;
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    {
                    alt4=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    {
                    alt4=3;
                    }
                    break;
                case '_':
                    {
                    alt4=4;
                    }
                    break;

                }

                switch (alt4) {
            	case 1 :
            	    // Satmc.g:186:9: 'a' .. 'z'
            	    {
            	    matchRange('a','z'); 

            	    }
            	    break;
            	case 2 :
            	    // Satmc.g:186:20: 'A' .. 'Z'
            	    {
            	    matchRange('A','Z'); 

            	    }
            	    break;
            	case 3 :
            	    // Satmc.g:186:31: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;
            	case 4 :
            	    // Satmc.g:186:39: '_'
            	    {
            	    match('_'); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FTEXT"

    // $ANTLR start "FILE"
    public final void mFILE() throws RecognitionException {
        try {
            int _type = FILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:190:5: ( ( 'a' .. 'z' | 'A' .. 'Z' | NUMBER | '.' )+ )
            // Satmc.g:190:7: ( 'a' .. 'z' | 'A' .. 'Z' | NUMBER | '.' )+
            {
            // Satmc.g:190:7: ( 'a' .. 'z' | 'A' .. 'Z' | NUMBER | '.' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=5;
                switch ( input.LA(1) ) {
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    {
                    alt5=1;
                    }
                    break;
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    {
                    alt5=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    {
                    alt5=3;
                    }
                    break;
                case '.':
                    {
                    alt5=4;
                    }
                    break;

                }

                switch (alt5) {
            	case 1 :
            	    // Satmc.g:190:9: 'a' .. 'z'
            	    {
            	    matchRange('a','z'); 

            	    }
            	    break;
            	case 2 :
            	    // Satmc.g:190:20: 'A' .. 'Z'
            	    {
            	    matchRange('A','Z'); 

            	    }
            	    break;
            	case 3 :
            	    // Satmc.g:190:31: NUMBER
            	    {
            	    mNUMBER(); 


            	    }
            	    break;
            	case 4 :
            	    // Satmc.g:190:40: '.'
            	    {
            	    match('.'); 

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FILE"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:194:5: ( ( '\\r' )? '\\n' )
            // Satmc.g:194:7: ( '\\r' )? '\\n'
            {
            // Satmc.g:194:7: ( '\\r' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\r') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // Satmc.g:194:7: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Satmc.g:198:5: ( ( ' ' | '\\t' )+ )
            // Satmc.g:198:7: ( ' ' | '\\t' )+
            {
            // Satmc.g:198:7: ( ' ' | '\\t' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='\t'||LA7_0==' ') ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // Satmc.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // Satmc.g:1:8: ( CFS | CLAUSES | COMMA | COMMENTS | C_BRACES | C_BRACKET | C_PARENTHESIS | DETAILS | ERROR | GOAL | INPUT | O_BRACES | O_BRACKET | O_PARENTHESIS | PERCENT | RULES | STAT | SUMMARY | TRACE | VERSION | WARNING | VERSION_NR | CONSTANT | NUMBER | FTEXT | FILE | NEWLINE | WS )
        int alt8=28;
        alt8 = dfa8.predict(input);
        switch (alt8) {
            case 1 :
                // Satmc.g:1:10: CFS
                {
                mCFS(); 


                }
                break;
            case 2 :
                // Satmc.g:1:14: CLAUSES
                {
                mCLAUSES(); 


                }
                break;
            case 3 :
                // Satmc.g:1:22: COMMA
                {
                mCOMMA(); 


                }
                break;
            case 4 :
                // Satmc.g:1:28: COMMENTS
                {
                mCOMMENTS(); 


                }
                break;
            case 5 :
                // Satmc.g:1:37: C_BRACES
                {
                mC_BRACES(); 


                }
                break;
            case 6 :
                // Satmc.g:1:46: C_BRACKET
                {
                mC_BRACKET(); 


                }
                break;
            case 7 :
                // Satmc.g:1:56: C_PARENTHESIS
                {
                mC_PARENTHESIS(); 


                }
                break;
            case 8 :
                // Satmc.g:1:70: DETAILS
                {
                mDETAILS(); 


                }
                break;
            case 9 :
                // Satmc.g:1:78: ERROR
                {
                mERROR(); 


                }
                break;
            case 10 :
                // Satmc.g:1:84: GOAL
                {
                mGOAL(); 


                }
                break;
            case 11 :
                // Satmc.g:1:89: INPUT
                {
                mINPUT(); 


                }
                break;
            case 12 :
                // Satmc.g:1:95: O_BRACES
                {
                mO_BRACES(); 


                }
                break;
            case 13 :
                // Satmc.g:1:104: O_BRACKET
                {
                mO_BRACKET(); 


                }
                break;
            case 14 :
                // Satmc.g:1:114: O_PARENTHESIS
                {
                mO_PARENTHESIS(); 


                }
                break;
            case 15 :
                // Satmc.g:1:128: PERCENT
                {
                mPERCENT(); 


                }
                break;
            case 16 :
                // Satmc.g:1:136: RULES
                {
                mRULES(); 


                }
                break;
            case 17 :
                // Satmc.g:1:142: STAT
                {
                mSTAT(); 


                }
                break;
            case 18 :
                // Satmc.g:1:147: SUMMARY
                {
                mSUMMARY(); 


                }
                break;
            case 19 :
                // Satmc.g:1:155: TRACE
                {
                mTRACE(); 


                }
                break;
            case 20 :
                // Satmc.g:1:161: VERSION
                {
                mVERSION(); 


                }
                break;
            case 21 :
                // Satmc.g:1:169: WARNING
                {
                mWARNING(); 


                }
                break;
            case 22 :
                // Satmc.g:1:177: VERSION_NR
                {
                mVERSION_NR(); 


                }
                break;
            case 23 :
                // Satmc.g:1:188: CONSTANT
                {
                mCONSTANT(); 


                }
                break;
            case 24 :
                // Satmc.g:1:197: NUMBER
                {
                mNUMBER(); 


                }
                break;
            case 25 :
                // Satmc.g:1:204: FTEXT
                {
                mFTEXT(); 


                }
                break;
            case 26 :
                // Satmc.g:1:210: FILE
                {
                mFILE(); 


                }
                break;
            case 27 :
                // Satmc.g:1:215: NEWLINE
                {
                mNEWLINE(); 


                }
                break;
            case 28 :
                // Satmc.g:1:223: WS
                {
                mWS(); 


                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA8_eotS =
        "\1\uffff\1\32\4\uffff\1\32\1\uffff\2\32\3\uffff\4\32\1\46\1\32\1"+
        "\51\1\32\3\uffff\2\32\1\uffff\1\51\1\32\1\61\7\32\1\25\1\uffff\1"+
        "\46\1\51\1\uffff\5\32\3\uffff\7\32\1\25\13\32\1\25\4\32\1\uffff"+
        "\1\126\5\32\1\135\4\32\2\uffff\2\32\1\uffff\1\32\1\135\1\uffff\2"+
        "\135\3\32\1\152\1\32\1\154\2\32\1\uffff\1\157\1\uffff\1\32\2\uffff"+
        "\1\32\1\uffff\2\32\1\164\1\32\1\uffff\7\32\1\uffff";
    static final String DFA8_eofS =
        "\175\uffff";
    static final String DFA8_minS =
        "\1\11\1\56\4\uffff\1\56\1\40\2\56\3\uffff\7\56\1\60\3\uffff\2\56"+
        "\1\uffff\2\56\1\105\7\56\1\60\1\uffff\2\56\1\uffff\1\60\4\56\3\uffff"+
        "\23\56\1\60\4\56\1\uffff\13\56\2\uffff\2\56\1\uffff\2\56\1\uffff"+
        "\2\56\1\60\5\56\1\40\1\60\1\uffff\1\56\1\uffff\1\56\2\uffff\1\60"+
        "\1\uffff\1\56\1\60\1\56\1\60\1\uffff\7\60\1\uffff";
    static final String DFA8_maxS =
        "\1\175\1\172\4\uffff\1\172\1\40\2\172\3\uffff\10\172\3\uffff\2\172"+
        "\1\uffff\2\172\1\127\7\172\1\71\1\uffff\2\172\1\uffff\5\172\3\uffff"+
        "\7\172\1\56\13\172\1\71\4\172\1\uffff\13\172\2\uffff\2\172\1\uffff"+
        "\2\172\1\uffff\12\172\1\uffff\1\172\1\uffff\1\172\2\uffff\1\172"+
        "\1\uffff\4\172\1\uffff\7\172\1\uffff";
    static final String DFA8_acceptS =
        "\2\uffff\1\3\1\5\1\6\1\7\4\uffff\1\14\1\15\1\16\10\uffff\1\32\1"+
        "\33\1\34\2\uffff\1\27\13\uffff\1\30\2\uffff\1\31\5\uffff\1\11\1"+
        "\25\1\17\30\uffff\1\12\13\uffff\1\13\1\20\2\uffff\1\23\2\uffff\1"+
        "\26\12\uffff\1\2\1\uffff\1\10\1\uffff\1\22\1\24\1\uffff\1\4\4\uffff"+
        "\1\21\7\uffff\1\1";
    static final String DFA8_specialS =
        "\175\uffff}>";
    static final String[] DFA8_transitionS = {
            "\1\27\1\26\2\uffff\1\26\22\uffff\1\27\4\uffff\1\7\2\uffff\1"+
            "\14\1\5\2\uffff\1\2\1\uffff\1\25\1\uffff\12\21\7\uffff\1\22"+
            "\1\20\1\1\1\6\2\22\1\10\1\22\1\11\10\22\1\15\1\16\1\17\6\22"+
            "\1\13\1\uffff\1\4\1\uffff\1\24\1\uffff\32\23\1\12\1\uffff\1"+
            "\3",
            "\1\25\1\uffff\12\33\7\uffff\13\22\1\30\2\22\1\31\13\22\4\uffff"+
            "\1\24\1\uffff\32\23",
            "",
            "",
            "",
            "",
            "\1\25\1\uffff\12\33\7\uffff\4\22\1\34\25\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\35",
            "\1\25\1\uffff\12\33\7\uffff\16\22\1\36\13\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\15\22\1\37\14\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "",
            "",
            "",
            "\1\25\1\uffff\12\33\7\uffff\24\22\1\40\5\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\23\22\1\41\1\42\5\22\4\uffff\1"+
            "\24\1\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\21\22\1\43\10\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\1\44\31\22\4\uffff\1\24\1\uffff"+
            "\32\23",
            "\1\45\1\uffff\12\47\7\uffff\32\50\4\uffff\1\51\1\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\32\22\4\uffff\1\24\1\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\32\50\6\uffff\32\23",
            "\12\51\7\uffff\32\52\4\uffff\1\24\1\uffff\32\51",
            "",
            "",
            "",
            "\1\25\1\uffff\12\33\7\uffff\1\54\15\22\1\53\13\22\4\uffff\1"+
            "\24\1\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\14\22\1\55\15\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "",
            "\1\25\1\uffff\12\33\7\uffff\32\50\6\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\23\22\1\56\6\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\57\21\uffff\1\60",
            "\1\25\1\uffff\12\33\7\uffff\1\62\31\22\4\uffff\1\24\1\uffff"+
            "\32\23",
            "\1\25\1\uffff\12\33\7\uffff\17\22\1\63\12\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\13\22\1\64\16\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\1\65\31\22\4\uffff\1\24\1\uffff"+
            "\32\23",
            "\1\25\1\uffff\12\33\7\uffff\14\22\1\66\15\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\1\67\31\22\4\uffff\1\24\1\uffff"+
            "\32\23",
            "\1\25\1\uffff\12\33\7\uffff\2\22\1\70\27\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\12\71",
            "",
            "\1\25\1\uffff\12\47\7\uffff\32\50\4\uffff\1\51\1\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\32\50\6\uffff\32\23",
            "",
            "\12\51\7\uffff\32\52\4\uffff\1\24\1\uffff\32\51",
            "\1\25\1\uffff\12\33\7\uffff\22\22\1\72\7\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\24\22\1\73\5\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\14\22\1\74\15\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\1\75\31\22\4\uffff\1\24\1\uffff"+
            "\32\23",
            "",
            "",
            "",
            "\1\25\1\uffff\12\33\7\uffff\13\22\1\76\16\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\24\22\1\77\5\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\4\22\1\100\25\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\23\22\1\101\6\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\14\22\1\102\15\22\4\uffff\1\24"+
            "\1\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\2\22\1\103\27\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\12\22\1\104\17\22\4\uffff\1\24"+
            "\1\uffff\32\23",
            "\1\105",
            "\1\25\1\uffff\12\33\7\uffff\4\22\1\106\25\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\22\22\1\107\7\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\4\22\1\110\25\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\10\22\1\111\21\22\4\uffff\1\24"+
            "\1\uffff\32\23",
            "\1\25\1\uffff\12\33\1\112\6\uffff\32\22\4\uffff\1\24\1\uffff"+
            "\32\23",
            "\1\25\1\uffff\12\33\7\uffff\23\22\1\113\6\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\22\22\1\114\7\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\10\22\1\115\21\22\4\uffff\1\24"+
            "\1\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\1\116\31\22\4\uffff\1\24\1\uffff"+
            "\32\23",
            "\1\25\1\uffff\12\33\7\uffff\4\22\1\117\25\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\4\22\1\120\25\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\12\121",
            "\1\25\1\uffff\12\33\7\uffff\3\22\1\122\26\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\4\22\1\123\25\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\15\22\1\124\14\22\4\uffff\1\24"+
            "\1\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\13\22\1\125\16\22\4\uffff\1\24"+
            "\1\uffff\32\23",
            "",
            "\1\25\1\uffff\12\33\7\uffff\32\22\4\uffff\1\24\1\uffff\32\23",
            "\1\25\1\uffff\12\33\1\127\6\uffff\32\22\4\uffff\1\24\1\uffff"+
            "\32\23",
            "\1\25\1\uffff\12\33\7\uffff\22\22\1\130\7\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\21\22\1\131\10\22\4\uffff\1\24"+
            "\1\uffff\32\23",
            "\1\25\1\uffff\12\33\1\132\6\uffff\32\22\4\uffff\1\24\1\uffff"+
            "\32\23",
            "\1\25\1\uffff\12\33\7\uffff\15\22\1\133\14\22\4\uffff\1\24"+
            "\1\uffff\32\23",
            "\1\25\1\uffff\12\137\7\uffff\32\136\6\uffff\32\134",
            "\1\25\1\uffff\12\33\7\uffff\32\22\4\uffff\1\140\1\uffff\32"+
            "\23",
            "\1\25\1\uffff\12\33\7\uffff\22\22\1\141\7\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\23\22\1\142\6\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\22\22\1\143\7\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "",
            "",
            "\1\25\1\uffff\12\33\7\uffff\23\22\1\144\6\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\30\22\1\145\1\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "",
            "\1\25\1\uffff\12\33\7\uffff\3\22\1\146\26\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\137\7\uffff\32\136\6\uffff\32\134",
            "",
            "\1\25\1\uffff\12\137\7\uffff\32\136\6\uffff\32\134",
            "\1\25\1\uffff\12\137\7\uffff\32\136\6\uffff\32\134",
            "\12\51\7\uffff\5\52\1\147\24\52\4\uffff\1\24\1\uffff\32\51",
            "\1\25\1\uffff\12\33\1\150\6\uffff\32\22\4\uffff\1\24\1\uffff"+
            "\32\23",
            "\1\25\1\uffff\12\33\7\uffff\22\22\1\151\7\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\32\22\4\uffff\1\24\1\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\10\22\1\153\21\22\4\uffff\1\24"+
            "\1\uffff\32\23",
            "\1\25\1\uffff\12\33\7\uffff\32\22\4\uffff\1\24\1\uffff\32\23",
            "\1\155\15\uffff\1\25\1\uffff\12\33\7\uffff\32\22\4\uffff\1"+
            "\24\1\uffff\32\23",
            "\12\51\7\uffff\10\52\1\156\21\52\4\uffff\1\24\1\uffff\32\51",
            "",
            "\1\25\1\uffff\12\33\7\uffff\32\22\4\uffff\1\24\1\uffff\32\23",
            "",
            "\1\25\1\uffff\12\33\7\uffff\2\22\1\160\27\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "",
            "",
            "\12\51\7\uffff\15\52\1\161\14\52\4\uffff\1\24\1\uffff\32\51",
            "",
            "\1\25\1\uffff\12\33\7\uffff\22\22\1\162\7\22\4\uffff\1\24\1"+
            "\uffff\32\23",
            "\12\51\7\uffff\1\163\31\52\4\uffff\1\24\1\uffff\32\51",
            "\1\25\1\uffff\12\33\7\uffff\32\22\4\uffff\1\24\1\uffff\32\23",
            "\12\51\7\uffff\13\52\1\165\16\52\4\uffff\1\24\1\uffff\32\51",
            "",
            "\12\51\7\uffff\32\52\4\uffff\1\166\1\uffff\32\51",
            "\12\51\7\uffff\22\52\1\167\7\52\4\uffff\1\24\1\uffff\32\51",
            "\12\51\7\uffff\23\52\1\170\6\52\4\uffff\1\24\1\uffff\32\51",
            "\12\51\7\uffff\1\171\31\52\4\uffff\1\24\1\uffff\32\51",
            "\12\51\7\uffff\23\52\1\172\6\52\4\uffff\1\24\1\uffff\32\51",
            "\12\51\7\uffff\4\52\1\173\25\52\4\uffff\1\24\1\uffff\32\51",
            "\12\51\1\174\6\uffff\32\52\4\uffff\1\24\1\uffff\32\51",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( CFS | CLAUSES | COMMA | COMMENTS | C_BRACES | C_BRACKET | C_PARENTHESIS | DETAILS | ERROR | GOAL | INPUT | O_BRACES | O_BRACKET | O_PARENTHESIS | PERCENT | RULES | STAT | SUMMARY | TRACE | VERSION | WARNING | VERSION_NR | CONSTANT | NUMBER | FTEXT | FILE | NEWLINE | WS );";
        }
    }
 

}
