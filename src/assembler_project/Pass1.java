package assembler_project;


import java.util.ArrayList;
import java.util.TreeMap;

public class Pass1 
{
    private final Files file = new Files();
    private final TreeMap < String ,String > symtable = new TreeMap <>();
    
    private final ArrayList <ProgramBlocs> Bloc;
    private final ArrayList <Integer> BlocNumber;
    private ArrayList <Integer> nextUseDest ;
    private ArrayList<String> Bloclength;
    private final Directives directive = new Directives();
    private Expressions E = new Expressions();
    private ArrayList <String> line;
    
    private final ArrayList < Boolean> absolue;
    private final ArrayList <Literals> literal;
    private final ArrayList <Literals> tempLiteral;
    private final ArrayList <Literals> FinalLiteral;
    private int HexLength;
    private String HexValue;
    
    private final ArrayList <String> LOCCTR;
    
    private ArrayList <String> Label;
    private ArrayList <String> Opcode;
    private ArrayList <String> Operand;
    private ArrayList <String> Comment;
    
    
    
    private  ArrayList <String> tempLabel;
    private ArrayList <String> tempOpcode;
    private  ArrayList <String> tempOperand;
    private  ArrayList <String> tempComment;
    private ArrayList <String> tempLine;
     private ArrayList <String> tempFlagErrors;
    private ArrayList <String> FlagErrors;
    private boolean startError;
    private boolean addressError;
    private boolean FileFound;
    private String LabelError;
    
    private int decimal;
    
    private boolean useORG;
    private String nextDest;
    private int nextDestDecimal;
    private String startingAddress;
    private String tempLOCCTR;
    private String ProgLength;
    private int CodeLength;
    
    private String ProgName;
    
    public Pass1() {
     
       startingAddress = "0000";
       tempLOCCTR = "0000";
       ProgName = "";
       ProgLength = "";
       
       decimal=0;
       useORG = false;
       
       Bloc = new ArrayList<>();
       BlocNumber = new ArrayList <>();
       nextUseDest = new ArrayList <>();
       Bloclength = new ArrayList<>();
       absolue = new ArrayList<>();
       tempLine = new ArrayList <>();
       line = new ArrayList <>();
      
       Label = new ArrayList <>();
       Opcode = new ArrayList <>();
       Operand = new ArrayList <>();
       Comment = new ArrayList <>();
       
       LOCCTR = new ArrayList<>();
       
       tempLabel = new ArrayList <>();
       tempOpcode = new ArrayList <>();
       tempOperand = new ArrayList <>();
       tempComment = new ArrayList <>();
       tempFlagErrors = new ArrayList<>();
       FinalLiteral = new ArrayList<>();
       
       literal = new ArrayList<>();
       tempLiteral = new ArrayList<>();
       HexValue = "";
       
       FlagErrors = new ArrayList <>();
       startError = false;
       addressError = false;
       
       Files file = new Files();
       try {
       file.openFileR("SRCFILE.txt");
       file.readFromFile();
       file.closeFileR();   
       FileFound = true;
       line = file.getLines();
       }
       catch(Exception e) { FileFound = false; System.out.println("ERROR!!\nFile not found\n\n"); }
       
       CodeLength = line.size();
       
       for (int i = 0; i < CodeLength ; i++) 
       {
            FlagErrors.add("");
       }
    }
    
    private void setLists()
    {
        if(FileFound)
        {
            if(line.isEmpty())
                FileFound = false;
            else {
            for (String line1 : line) {
                if (line1.isEmpty()) {
                    Comment.add("");
                    Label.add("");
                    Opcode.add("");
                    Operand.add("");
                }
                else {
                    if (line1.startsWith(".")) {
                        Comment.add(line1);
                        Label.add("");
                        Opcode.add("");
                        Operand.add("");
                    } 
                else {
                     try {
                            String[] lines = line1.split("\\s+");
                            
                            if(lines[0]!=null)
                                Label.add(lines[0]);
                            else
                                Label.add("");
                     
                            if(lines[1]!=null)
                                Opcode.add(lines[1]);
                            else
                                Opcode.add("");
                            
                            if(lines[2].startsWith("=")|| lines[2].startsWith("C'") || lines[2].startsWith("c'") || lines[2].startsWith("X'") || lines[2].startsWith("x'"))
                            {
                                String op ="";
                                for (int j = 2; j < lines.length ; j++)
                                { if(lines[j].startsWith("."))
                                    break;
                                  else
                                    op += lines[j] + " ";
                                }
                                Operand.add(op);
                            }
                            else 
                                if(lines.length==3)
                                { Operand.add(lines[2]); }
                            
                            else { Operand.add(""); }    
                            Comment.add("");
                            
                     } catch(Exception e)
                            {
                            Operand.add("");
                            Comment.add("");
                            }}
                }
            }
            }        
            }
    }
    
    private void CheckErrors()
    {
        StringBuilder error = new StringBuilder("");
        
        int j=0 , k = CodeLength - 1;
        
        while (! "".equals(Comment.get(j)) || Opcode.get(j).equals("")) { j++; }
        
        if(!Opcode.get(j).equalsIgnoreCase("start"))
            {
            FlagErrors.add(j,".\n**** missing or misplaced start statement");
            startError = true;
            ProgName = "DEFAULT";
            }
        else
        {
        if(Operand.get(j).isEmpty()&& Label.get(j)!="")
            {
            FlagErrors.add(j,".\n**** missing or misplaced operand in instruction");
            startError = true;
            ProgName = Label.get(j);
            
            }
        else
        if(Label.get(j).equals("") && Operand.get(j)!="")
            {
            FlagErrors.add(j,".\n**** missing or misplaced Program name");
            startingAddress = Operand.get(j);
            ProgName = "DEFAULT";
            
            }
        else
            {
            ProgName = Label.get(j);
            startingAddress = Operand.get(j);
            if(Opcode.contains("use")|| Opcode.contains("USE")){ startingAddress = "0000"; }
            else {startingAddress = Operand.get(j);}
            }
        }
        for (int i = 0; i < CodeLength ; i++) 
        {
            if(Comment.get(i).equals("")) {
            if(line.get(i).isEmpty())
                FlagErrors.add(i,"**** unrecognized operation code\n**** missing or misplaced operand in instruction");
            else {
            if(Opcode.get(i).equalsIgnoreCase("start")&& j!=i)
                error.append(".\n****duplicate or misplaced start statement");
            
            if(Operand.get(i).isEmpty() && !( Opcode.get(i).equalsIgnoreCase("rsub") || Opcode.get(i).equalsIgnoreCase("LTORG")|| Opcode.get(i).equalsIgnoreCase("ORG")) )
                error.append(".\n**** missing or misplaced operand in instruction");
            
            if(!Operand.get(i).isEmpty() && ( Opcode.get(i).equalsIgnoreCase("rsub") || Opcode.get(i).equalsIgnoreCase("LTORG")) )
                error.append(".\n**** illegal operand in this statement");
            
            if(! Optab.optable.containsKey(Opcode.get(i).toUpperCase()) && ! directive.isDirectives(Opcode.get(i).toUpperCase()) )//
                error.append(".\n**** unreconized operation code");
            
            if(ErrorFormat(Operand.get(i)))
                {
                error.append(".\n*** illegal format in Operand");
                addressError = true;    
                }
            if(directive.getNumeric(Opcode.get(i))&& ! isNumeric(Operand.get(i)))
                {
                error.append(".\n*** illegal operand in this statement");
                addressError = true;
                }
           FlagErrors.add(i,error.toString());
           error.delete(0, error.length());
            }
            }
           
        }
            while(!Comment.get(k).equals("")){ k--; }
            if(!Opcode.get(k).equalsIgnoreCase("end"))
                FlagErrors.add(k,".\n**** missing end statement");
            else {
                if(!Label.get(j).equals(Operand.get(k)) && !Label.get(j+1).equals(Operand.get(k)))
                    FlagErrors.add(k,".\n**** undefined symbol in operand");
                else if(Operand.get(k).equals(""))
                FlagErrors.add(k,".\n**** missing or misplaced operand in end statement");
                }
    }

    private boolean ErrorFormat(String args)
    {
            if( args.startsWith("=c'") || args.startsWith("=C'")
            ||args.startsWith("=x'")|| args.startsWith("=X'")
            ||args.startsWith("c'")||args.startsWith("C'")
            ||args.startsWith("x'")||args.startsWith("X'"))
                {
                if(!(args.endsWith("'") || args.endsWith("' ")) )
                {
                    return true;
                }
                }
    return false;
    }
    private boolean isNumeric(String args)
    {
    try {
        int temp = Integer.parseInt(args.trim());
    } catch(NumberFormatException e) 
    { return false; }
    return true;
    }

    private String CorrectFormat(int decimal) 
    {
        
            tempLOCCTR = Integer.toHexString (decimal);
            while(tempLOCCTR.length()!=4)   tempLOCCTR = "0"+ tempLOCCTR;
            return tempLOCCTR.toUpperCase();
    }
    
    private void TestAndAddLiterals()
    {       
        String temp;
        String tempHex;
        for (int i = 0; i < CodeLength ; i++) 
        {
            if (Operand.get(i).startsWith("=")) 
            {
                temp = Operand.get(i).substring(1);
                
                if(temp.startsWith("*"))
                {
                    if(temp.trim().length()==1)
                        literal.add(new Literals(Operand.get(i),"",2));
                    else
                        FlagErrors.set(i, FlagErrors.get(i)+ ".\n**** Undefined Operand"); 
                  
                }
                else if(temp.startsWith("c'") || temp.startsWith("C'"))
                {
                    if(!ErrorFormat(Operand.get(i)))
                    {
                        tempHex = generateHexValue(temp);
                        HexLength = tempHex.length()/2;
                        if(!foundLiteralsValue(tempHex))
                            literal.add(new Literals(Operand.get(i),tempHex,HexLength)); 
                        else
                            literal.add(new Literals("","",0));
                    }
                    else
                       FlagErrors.set(i, FlagErrors.get(i)+ ".\n**** Undefined Operand"); 
                }
                else if( temp.startsWith("x'") || temp.startsWith("X'"))
                {
                    if(!ErrorFormat(Operand.get(i)))
                    {
                        tempHex = generateHexValue(temp);
                        
                        String TH = "";
                        String[] t = temp.split("'");
                        for (int j = 1; j < t.length - 1; j++)  TH += t[j];
                        HexLength = TH.length()/2;
                        
                        if(!foundLiteralsValue(tempHex))
                            literal.add(new Literals(Operand.get(i),tempHex,HexLength)); 
                        else
                            literal.add(new Literals("","",0));
                    }
                    else
                       FlagErrors.set(i, FlagErrors.get(i)+ ".\n**** Undefined Operand");
                }
                else
                    if(temp.trim().equals(""))
                    {
                        literal.add(new Literals("","",0));
                        FlagErrors.set(i, FlagErrors.get(i)+".\n**** Undefined Operand" );
                    }
                else
                    if(E.isNumeric(temp))
                    {
                    
                    /// ****** LENGTH ????
                    int l = temp.length()*3;
                    tempHex = generateHexValue(temp).toUpperCase();
                    
                    if(!foundLiteralsValue(tempHex))
                        literal.add(new Literals(Operand.get(i),tempHex,3)); 
                    else
                        literal.add(new Literals("","",0));
                    
                    }
                
                
            }
            else 
                literal.add(new Literals("","",0));
            
        }
         
    }
    
    private boolean foundLiteralsValue(String args)
    {
        try {
        for (Literals l : literal) {
            
            if (args.equals(l.getHexValue())) 
            {
            return true;    
            }
        }}
        catch(Exception e){ return false; }
        
        return false;
                        
    }
    
    public String generateHexValue(String args)
    {
        if (args.startsWith("c'") || args.startsWith("C'"))
        {
        HexValue = "";
        String BiteValue = "";
        String[] temp = args.split("'");
        for (int j = 1; j < temp.length - 1; j++) {
            BiteValue += temp[j];
        }
        for (int j = 0; j < BiteValue.length(); j++) {
            HexValue += Integer.toHexString((int) (BiteValue.charAt(j)));
        }
        
        while (HexValue.length() < 6) {
            HexValue = "0" + HexValue;
        }
       
        }

        else 
            if (args.startsWith("x'") || args.startsWith("X'")) 
        {
        HexValue = "";
        String[] temp = args.split("'");
        for (int j = 1; j < temp.length - 1; j++)  HexValue += temp[j];
        
        int startIdx = 0;
        for (int i = 0; i < HexValue.length(); i++) 
        {
             if(HexValue.charAt(i)!='0')
             {
                 startIdx = i;
                 break;
             }
        }
        
        HexValue = HexValue.substring(startIdx);
   
        }
        else
            if(E.isNumeric(args))
            {
                HexValue = Integer.toHexString(Integer.parseInt(args.trim()));
               
            }
        
        return HexValue;
    }
    
    private void generateLOCCTR()
    {
        TestAndAddLiterals();
        LiteralAddress();
        
        int Blocindx = 0;
        int prevBlocindx = 0;
        boolean BlocFound = false;
        boolean Absolute = false;
        decimal = Integer.parseInt(startingAddress, 16);
        tempLOCCTR = CorrectFormat(decimal);
        LOCCTR.add(tempLOCCTR);
        
        nextUseDest.add(Blocindx,decimal);
        Bloc.add(new ProgramBlocs("Default",Blocindx));
        Bloc.get(Blocindx).setAddress("0000");
        
        for (int i = 0; i < CodeLength ; i++) 
        {
            if(Operand.get(i).trim().equals("=*"))
            {
                for(Literals l : FinalLiteral)
                {
                    if(l.getName().equals(Operand.get(i))&& !l.isUsed())
                    {
                        l.resetHexValue(LOCCTR.get(i));
                        l.setLength(LOCCTR.get(i));
                        l.setUsed(true);
                        break;
                    }
                }
            }
            
             if(!Label.get(i).isEmpty() && !Label.get(i).equals("*"))
            {  
            if(!symtable.containsKey(Label.get(i)))             
                symtable.put(Label.get(i),tempLOCCTR);
            else
                FlagErrors.set(i, FlagErrors.get(i)+ ".\n**** duplicate label definition");
            }
        
            
             if(Opcode.get(i).equalsIgnoreCase("use"))
            {
                if(Operand.get(i).equals(""))
                {
                    prevBlocindx = Blocindx;
                    Blocindx = 0;
                    if(nextUseDest.get(prevBlocindx) != null){ nextUseDest.set(prevBlocindx, decimal);}
                    decimal = nextUseDest.get(Blocindx);
                    tempLOCCTR = CorrectFormat(decimal);
                    LOCCTR.set(i, tempLOCCTR);
                    
                }
                else
                    if(E.isSymbol(Operand.get(i)))
                {
                    for(ProgramBlocs B : Bloc)
                    { 
                        if(B.getBlockName().equals(Operand.get(i))) 
                        { 
                            BlocFound = true;
                            prevBlocindx = Blocindx;
                            Blocindx = B.getBlockNumber();
                            System.out.println(B.getBlockNumber());
                            break;
                        }
                        
                    }
                    if(BlocFound)
                    {
                        if(nextUseDest.get(prevBlocindx) != null){ nextUseDest.set(prevBlocindx, decimal);}
                       
                        decimal = nextUseDest.get(Blocindx);
                        tempLOCCTR = CorrectFormat(decimal);
                        LOCCTR.set(i, tempLOCCTR);
                    }
                    else
                    {
                        nextUseDest.add(Blocindx,decimal);
                        Blocindx++;
                        Bloc.add(new ProgramBlocs(Operand.get(i),(Blocindx)));
                        tempLOCCTR = "0000";
                        decimal = 0;
                        LOCCTR.set(i, tempLOCCTR);
                    
                    }
                }
                else
                   FlagErrors.set(i, FlagErrors.get(i)+ ".\n**** undefined Operand");     
                
            }
            else 
            
                if(Optab.optable.containsKey(Opcode.get(i).toUpperCase()))
                {
                decimal+=3;
                tempLOCCTR = CorrectFormat(decimal);
                }
                
            else
                 if(Opcode.get(i).equalsIgnoreCase("word"))
                 {
                     decimal+=3;
                     tempLOCCTR = CorrectFormat(decimal);   
                 }
            else
                 if(Opcode.get(i).equalsIgnoreCase("resw"))
                 {
                     
                    try {
                        decimal+=3*Integer.parseInt(Operand.get(i)) ;
                        tempLOCCTR = CorrectFormat(decimal);
                    }
                    catch(NumberFormatException nfe )
                    {
                        tempLOCCTR = CorrectFormat(decimal);
                    }
                 }
                 
             else
                 if(Opcode.get(i).equalsIgnoreCase("resb"))
                 {
                   try {
                        decimal += Integer.parseInt(Operand.get(i));
                        tempLOCCTR = CorrectFormat(decimal);
                        }
                  catch(NumberFormatException nfe)
                    {
                        tempLOCCTR = CorrectFormat(decimal);
                    }
                 }
             else
                  if(Opcode.get(i).equalsIgnoreCase("byte"))
                {
                   try {
                        if(Operand.get(i).startsWith("c")|| Operand.get(i).startsWith("C"))
                        {
                            String[] x = Operand.get(i).split("'");
                            int length = x[1].length();
                            decimal += length;
                            tempLOCCTR = CorrectFormat(decimal);
                        }
                        else
                            if(Operand.get(i).startsWith("x") || Operand.get(i).startsWith("X"))
                        {
                            decimal += (Operand.get(i).length()-3)/2;
                            tempLOCCTR = CorrectFormat(decimal);
                        }
                   }catch(Exception e)
                   {
                       tempLOCCTR = CorrectFormat(decimal);
                      
                   }
                } 
                else
                    if(Opcode.get(i).equalsIgnoreCase("start")|| Opcode.get(i).equalsIgnoreCase("end") || Opcode.get(i).equalsIgnoreCase("LTORG"))
                        {
                            tempLOCCTR = CorrectFormat(decimal);
                        }
                else
                      if(Opcode.get(i).equalsIgnoreCase("EQU"))
                      {
                          
                          nextDest = tempLOCCTR;
                          nextDestDecimal = decimal;
                          
                        if(Operand.get(i).equals("*"))
                        {   
                             symtable.put(Label.get(i),LOCCTR.get(i));
                             LOCCTR.set(i,LOCCTR.get(i));
                        }
//                        else  
//                        if(E.isExpression(Operand.get(i)))
//                            {
//                                Absolute = true;
//                            }
                        else
                            if(E.isNumeric(Operand.get(i)))
                            {
                                Absolute = true;
                                int tempValue = Integer.parseInt(Operand.get(i),16);
                                symtable.put(Label.get(i),CorrectFormat(tempValue));
                                LOCCTR.set(i,CorrectFormat(tempValue));
                                
                            }
                       else 
                            if(E.isSymbol(Operand.get(i)))
                            {
                            if(symtable.containsKey(Operand.get(i)))             
                            {
                                symtable.put(Label.get(i),symtable.get(Operand.get(i)));
                                LOCCTR.set(i,symtable.get(Operand.get(i)));
                            }
                            else
                                FlagErrors.set(i, FlagErrors.get(i) + ".\n**** EQU has forward reference restriction");
                            
                            }
                        else
                            {  FlagErrors.set(i, FlagErrors.get(i) + ".\n**** undefined Operand");}
                      
                           decimal = nextDestDecimal;
                           tempLOCCTR = nextDest;
                           
                      }
                      else 
                          if(Opcode.get(i).equalsIgnoreCase("ORG")){
            
                            if(!useORG)
                            {
                            useORG = true;
                            nextDest = tempLOCCTR;
                            nextDestDecimal = decimal;
                            if(E.isSymbol(Operand.get(i)))
                            {
                            if (symtable.containsKey(Operand.get(i)))
                            {
                            LOCCTR.set(i, symtable.get(Operand.get(i)));
                            tempLOCCTR = LOCCTR.get(i);
                            decimal = Integer.parseInt(tempLOCCTR,16);
                            }
                            else
                            {
                            decimal+=3;
                            tempLOCCTR = CorrectFormat(decimal);
                            FlagErrors.set(i, FlagErrors.get(i)+".\nUndefined Symbol");
                            }
                            }
                            else
                                if(E.isNumeric(Operand.get(i)))
                            {
                            int temp = Integer.parseInt(Operand.get(i));
                            String dest = Integer.toHexString (temp);
                            while (dest.length() != 4) { dest = "0" + dest; }
                            LOCCTR.set(i, dest);
                            tempLOCCTR = LOCCTR.get(i);
                            decimal = Integer.parseInt(tempLOCCTR,16);
                       
                            }
//                            else
//                            if(E.isExpression(Operand.get(i)))
//                            {
//                    // check if its valid then evaluated bla bla bla 
//                            }
                            
                            else
                            {
                            decimal+=3;
                            tempLOCCTR = CorrectFormat(decimal);
                            FlagErrors.set(i, FlagErrors.get(i)+".\nUndefined Symbol");
                            }
                
                            }
                            else
                            {
                            useORG = false;
                            decimal = nextDestDecimal;
                            tempLOCCTR = nextDest;
                            if(Operand.get(i).equals(""))
                                LOCCTR.set(i, nextDest);
                            
//                            else 
//                                if(E.isExpression(Operand.get(i)))
//                            {
//                            //evaluate expression set LOCTTR with it,, check error 
//                            }
                            else
                            {
                                decimal+=3;
                                tempLOCCTR = CorrectFormat(decimal);
                                FlagErrors.set(i, FlagErrors.get(i)+".\nUndefined Symbol");
                            }
                
                            }
               
                        }
                          
                    else 
                        if (Label.get(i).equals("*"))
                        {
                              for(Literals l : FinalLiteral)
                              {
                              if(Opcode.get(i).equals(l.getName()))
                              {
                                  l.setAddress(LOCCTR.get(i));
                                  decimal += l.getLength();
                                  tempLOCCTR = CorrectFormat(decimal);
                                  break;
                              }
                              }
                        }
             if(Absolute) absolue.add(true);
             else absolue.add(false);
             BlocNumber.add(Blocindx);
             LOCCTR.add(tempLOCCTR);
             Absolute = false;
            
        }
        BlocLength();
        BlocStart();

    }

    public ArrayList<Integer> getNextUseDest() {
        return nextUseDest;
    }

    
    private void BlocLength()
    {
        for (int i = 0; i < Bloc.size(); i++) { Bloclength.add("0"); }
        for (int i = 0; i < BlocNumber.size(); i++) 
        {   
            if( BlocNumber.get(i)== null ){ Bloclength.set(0, LOCCTR.get(i));}
            else { Bloclength.set(BlocNumber.get(i), LOCCTR.get(i)); }
        
        }
        for (int i = 0; i < Bloc.size(); i++) {  Bloc.get(i).setLength(Bloclength.get(i));}
        
    }
    
    private void BlocStart()
    {
        for (int i = 1; i < Bloc.size(); i++) 
        {
        int decimal1 = Integer.parseInt(Bloc.get(i-1).getAddress(), 16);
        int decimal2 = Integer.parseInt(Bloc.get(i-1).getLength(), 16);
        int d = decimal1 + decimal2;
            
        Bloc.get(i).setAddress(CorrectFormat(d));
            
        }
    }
    public ArrayList<Literals> getFinalLiteral() {
        return FinalLiteral;
    }

    public ArrayList<ProgramBlocs> getBloc() {
        return Bloc;
    }
    
    
    private void LiteralAddress()
    {
        
        for (int i = 0; i < CodeLength; i++) 
        {
            if(!(literal.get(i).getName().equals("")))
            {
               tempLiteral.add(literal.get(i));
               FinalLiteral.add(literal.get(i));
            }
            if(Opcode.get(i).equalsIgnoreCase("LTORG"))
            {
                tempLabel.add(Label.get(i));
                tempOpcode.add(Opcode.get(i));
                tempOperand.add(Operand.get(i));
                tempComment.add(Comment.get(i));
                tempFlagErrors.add(FlagErrors.get(i));
                tempLine.add(line.get(i));
                for (int j = 0; j < tempLiteral.size(); j++) {
                    tempLabel.add("*");
                    tempOpcode.add(tempLiteral.get(j).getName());
                    tempOperand.add("");
                    tempComment.add("");
                    tempFlagErrors.add("");
                    tempLine.add("");
                
                }
                tempLiteral.clear();
                
            }
            else
            {
                tempLabel.add(Label.get(i));
                tempOpcode.add(Opcode.get(i));
                tempOperand.add(Operand.get(i));
                tempComment.add(Comment.get(i));
                tempFlagErrors.add(FlagErrors.get(i));
                tempLine.add(line.get(i));
            }
        }
        if(!tempLiteral.isEmpty())
        {       for (int j = 0; j < tempLiteral.size(); j++) {
                    tempLabel.add("*");
                    tempOpcode.add(tempLiteral.get(j).getName());
                    tempOperand.add("");
                    tempComment.add("");
                    tempFlagErrors.add("");
                    tempLine.add("");
                    }
                tempLiteral.clear();
         
        }
        
        Label = tempLabel;
        Opcode = tempOpcode;
        Operand = tempOperand;
        Comment = tempComment;
        FlagErrors = tempFlagErrors;
        line = tempLine;
        CodeLength += FinalLiteral.size();
                
    }
  
    public void createIntFile()
    {
        setLists();
        CheckErrors();
        generateLOCCTR();
        
         file.openFileW("INTFILE.txt");
        if(!FileFound){ 
            file.writeToFile("ERROR!!\nSRCFILE Not Found\n");
            file.closeFileW();
        }
        else {
        for (int i = 0; i < CodeLength; i++) 
        {
            if(Comment.get(i)!="")
                file.writeToFile(Comment.get(i)+"\n");
            else{
                if (absolue.get(i).equals(true))
                    
                    file.writeToFile (LOCCTR.get(i)+"\t" +"" + "\t" + Label.get(i) + "\t" + Opcode.get(i).toUpperCase() + "\t" + Operand.get(i) + "\t" + FlagErrors.get(i) + "\n");
                else
                    file.writeToFile (LOCCTR.get(i)+"\t" +BlocNumber.get(i) + "\t" + Label.get(i) + "\t" + Opcode.get(i).toUpperCase() + "\t" + Operand.get(i) + "\t" + FlagErrors.get(i) + "\n");
                
            }
            }
        file.closeFileW();
        }
    }

    public TreeMap<String, String> getSymtable() {
        return symtable;
    }
    
    public String getProgLength() {
        return ProgLength;
    }

    public String getProgName() {
        return ProgName;
    }

    public int getCodeLength() {
        return CodeLength;
    }

    public String getStartingAddress() {
        return startingAddress;
    }

    public ArrayList<Boolean> getAbsolue() {
        return absolue;
    }
    
    
    public String getProgramLength() {
        
        int decimal1 = Integer.parseInt(LOCCTR.get(0), 16);
        int decimal2 = Integer.parseInt(LOCCTR.get(LOCCTR.size()-1), 16);
        decimal = decimal2 - decimal1;

        ProgLength = CorrectFormat(decimal);
            
        return ProgLength;
    }
    
}
