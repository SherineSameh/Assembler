package assembler_project;

import java.util.ArrayList;

public class Directives 
{

    private ArrayList <String> directives = new ArrayList<>();
    private String nextDest;
    public Directives() {
    directives.add("START");
    directives.add("END");
    directives.add("WORD");
    directives.add("BYTE");
    directives.add("RESW");
    directives.add("RESB");
    directives.add("EQU");
    directives.add("ORG");
    directives.add("LTORG");
    directives.add("USE");
    
    }
    public boolean isDirectives(String args)
    {
       return(directives.contains(args));
    }
    public boolean getNumeric(String args)
    {
        if(args.equalsIgnoreCase("resw") || args.equalsIgnoreCase("resb"))
            return true;
        return false;
    }

    public void setNextDest(String nextDest) {
        this.nextDest = nextDest;
    }

    public String getNextDest() {
        return nextDest;
    }
    
}
    

