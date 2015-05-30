// $ANTLR 3.4 RoleDef.g 2012-03-27 12:55:40

package org.antlr.roledef;

import eu.aniketos.securebpmn.roles.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class RoleDefParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "COLON", "COMMA", "COMMENT", "GEQ", "STR", "WS"
    };

    public static final int EOF=-1;
    public static final int COLON=4;
    public static final int COMMA=5;
    public static final int COMMENT=6;
    public static final int GEQ=7;
    public static final int STR=8;
    public static final int WS=9;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public RoleDefParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public RoleDefParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return RoleDefParser.tokenNames; }
    public String getGrammarFileName() { return "RoleDef.g"; }



    // $ANTLR start "file"
    // RoleDef.g:17:1: file returns [List<RoleDefLine> lines] : (ldef= line_def |lrel= line_rel )* EOF ;
    public final List<RoleDefLine> file() throws RecognitionException {
        List<RoleDefLine> lines = null;


        RoleDef ldef =null;

        RoleRel lrel =null;


        try {
            // RoleDef.g:18:5: ( (ldef= line_def |lrel= line_rel )* EOF )
            // RoleDef.g:18:7: (ldef= line_def |lrel= line_rel )* EOF
            {
            lines = new ArrayList<RoleDefLine>();

            // RoleDef.g:19:7: (ldef= line_def |lrel= line_rel )*
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==STR) ) {
                    int LA1_2 = input.LA(2);

                    if ( (LA1_2==COLON) ) {
                        alt1=1;
                    }
                    else if ( (LA1_2==GEQ) ) {
                        alt1=2;
                    }


                }


                switch (alt1) {
            	case 1 :
            	    // RoleDef.g:19:8: ldef= line_def
            	    {
            	    pushFollow(FOLLOW_line_def_in_file68);
            	    ldef=line_def();

            	    state._fsp--;


            	    lines.add(ldef);

            	    }
            	    break;
            	case 2 :
            	    // RoleDef.g:19:49: lrel= line_rel
            	    {
            	    pushFollow(FOLLOW_line_rel_in_file76);
            	    lrel=line_rel();

            	    state._fsp--;


            	    lines.add(lrel);

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            match(input,EOF,FOLLOW_EOF_in_file83); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return lines;
    }
    // $ANTLR end "file"



    // $ANTLR start "line_def"
    // RoleDef.g:22:1: line_def returns [RoleDef def] : name= string COLON fm= string ( COMMA mm= string )* ;
    public final RoleDef line_def() throws RecognitionException {
        RoleDef def = null;


        RoleDefParser.string_return name =null;

        RoleDefParser.string_return fm =null;

        RoleDefParser.string_return mm =null;


        try {
            // RoleDef.g:23:5: (name= string COLON fm= string ( COMMA mm= string )* )
            // RoleDef.g:23:7: name= string COLON fm= string ( COMMA mm= string )*
            {
            pushFollow(FOLLOW_string_in_line_def106);
            name=string();

            state._fsp--;


            def = new RoleDef((name!=null?input.toString(name.start,name.stop):null));

            match(input,COLON,FOLLOW_COLON_in_line_def110); 

            pushFollow(FOLLOW_string_in_line_def114);
            fm=string();

            state._fsp--;


            def.addMember((fm!=null?input.toString(fm.start,fm.stop):null));

            // RoleDef.g:23:97: ( COMMA mm= string )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==COMMA) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // RoleDef.g:23:98: COMMA mm= string
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_line_def119); 

            	    pushFollow(FOLLOW_string_in_line_def123);
            	    mm=string();

            	    state._fsp--;


            	    def.addMember((mm!=null?input.toString(mm.start,mm.stop):null));

            	    }
            	    break;

            	default :
            	    break loop2;
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
        return def;
    }
    // $ANTLR end "line_def"



    // $ANTLR start "line_rel"
    // RoleDef.g:26:1: line_rel returns [RoleRel rel] : sup= string GEQ sub= string ;
    public final RoleRel line_rel() throws RecognitionException {
        RoleRel rel = null;


        RoleDefParser.string_return sup =null;

        RoleDefParser.string_return sub =null;


        try {
            // RoleDef.g:27:5: (sup= string GEQ sub= string )
            // RoleDef.g:27:7: sup= string GEQ sub= string
            {
            pushFollow(FOLLOW_string_in_line_rel152);
            sup=string();

            state._fsp--;


            match(input,GEQ,FOLLOW_GEQ_in_line_rel154); 

            pushFollow(FOLLOW_string_in_line_rel158);
            sub=string();

            state._fsp--;


            rel = new RoleRel((sup!=null?input.toString(sup.start,sup.stop):null), (sub!=null?input.toString(sub.start,sub.stop):null));

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return rel;
    }
    // $ANTLR end "line_rel"


    public static class string_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "string"
    // RoleDef.g:30:1: string : STR ;
    public final RoleDefParser.string_return string() throws RecognitionException {
        RoleDefParser.string_return retval = new RoleDefParser.string_return();
        retval.start = input.LT(1);


        try {
            // RoleDef.g:31:5: ( STR )
            // RoleDef.g:31:7: STR
            {
            match(input,STR,FOLLOW_STR_in_string181); 

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
    // $ANTLR end "string"

    // Delegated rules


 

    public static final BitSet FOLLOW_line_def_in_file68 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_line_rel_in_file76 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_EOF_in_file83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_line_def106 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_COLON_in_line_def110 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_string_in_line_def114 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_COMMA_in_line_def119 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_string_in_line_def123 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_string_in_line_rel152 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_GEQ_in_line_rel154 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_string_in_line_rel158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STR_in_string181 = new BitSet(new long[]{0x0000000000000002L});

}
