package assembler_project;

import java.util.ArrayList;
import java.util.Stack;

public class Expressions {
    
    private Stack sign = new Stack();
    private Stack term = new Stack();
    
    public Expressions() {}
    
    public boolean isExpression(String expression) {
        if(expression.isEmpty()) return false;
        String[] terms = expression.split("[+*-/]");
        String[] signs = expression.split("[^(+*-/)]");
        
        for (String term : terms) {
            if (term.isEmpty() ) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isSymbol(String expression)
    {
        char [] letters = expression.toCharArray();
        for(char c : letters)
        {
            if(! Character.isLetter(c))
                return false;
        }
        return true;
    }
    
    public boolean isNumeric(String expression) {
        try {
            expression = expression.trim();
            Integer.parseInt(expression);
            return true;
        }catch(Exception e) {
            return false;
        }
    }
    
    public boolean validExpression(String arg , boolean absolue)
    {
        
        boolean v = false;
        int psign=0 , nsign = 0;
        ArrayList <String> num = new ArrayList<>();
            String[] terms = arg.split("[+*-/]");
            String[] signs = arg.split("[^(+*-/)]");
            for(String s :signs)
            {
                sign.push(s);
                System.out.println(sign.peek());
                if(s.equals("+")) psign++;
                else if(s.equals("-")) nsign++;
            }
            for (String t :terms)
            {
                term.push(t);
                if(isNumeric(t)) num.add(t);
                
            }
            if(terms.length%2 == 0 && (psign == nsign || psign+1 == nsign) && psign+nsign ==signs.length && num.isEmpty()) v= true;
            
            if(terms.length == num.size()) v = true;
            
            if(psign+nsign != signs.length && absolue ==true ) v =  true;
            
        return v;
    }
}
