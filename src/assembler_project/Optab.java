package assembler_project;
import java.util.*;

public final class Optab 
{
    public static final TreeMap <String , String> optable = new TreeMap<>();
    static{
        optable.put("ADD","18");
        optable.put("AND","40");
        optable.put("COMP","28");
        optable.put("DIV","24");
        optable.put("J","3C");
        optable.put("JEQ","30");
        optable.put("JGT","34");
        optable.put("JLT","38");
        optable.put("JSUB","48");
        optable.put("LDA","00");
        optable.put("LDCH","50");
        optable.put("LDL","08");
        optable.put("LDX","04");
        optable.put("MUL","20");
        optable.put("OR","44");
        optable.put("RD","D8");
        optable.put("RSUB","4C");
        optable.put("STA","0C");
        optable.put("STCH","54");
        optable.put("STL","14");
        optable.put("STSW","E8");
        optable.put("STX","10");
        optable.put("SUB","1C");
        optable.put("TIX","B8");
        optable.put("TD","E0");
        optable.put("WD","DC");
        
    }
}
