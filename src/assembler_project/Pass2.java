package assembler_project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Pass2 {

    private TreeMap< String, String> symtable = new TreeMap<>();

    private Pass1 pass1 = new Pass1();
    
    private BufferedWriter write;

    private ArrayList<String> lines;

    private ArrayList<String> Errors;
    private final ArrayList<String> Label;
    private final ArrayList<String> Opcode;
    private final ArrayList<String> Operand;
    private final ArrayList<String> ObjectCode;
    private final ArrayList<String> Locctr;
    private final ArrayList<Literals> literal;
    private ArrayList<String> TextRec;
    private ArrayList <Boolean> Abs = pass1.getAbsolue();
    private ArrayList <ProgramBlocs> bloc = pass1.getBloc();
    private ArrayList <String> BlocNum ;
    
    private final ArrayList<String> LisF;

    private String OpString;
    private String SymString;
    private String ObjString;

    private String AsciiValue;
    private String BinValue;
    private Hexadecimal HexValue;
    private String FlagError;
    private int lineNum;
    private boolean FileFound;
    private Files Ifile;
    private Files Ofile;
    private int counter;

    Pass2() {
        OpString = "";
        SymString = "";
        ObjString = "";
        FlagError = "";
        AsciiValue = "";
        BinValue = "";
        HexValue = new Hexadecimal();
        counter = 0;

        symtable = new TreeMap<>();
        lineNum = 0;
        FileFound = false;
        lines = new ArrayList<>();

        LisF = new ArrayList<>();
        TextRec = new ArrayList<>();
        Label = new ArrayList<>();
        Opcode = new ArrayList<>();
        Operand = new ArrayList<>();
        ObjectCode = new ArrayList<>();
        Locctr = new ArrayList<>();
        Errors = new ArrayList<>();
        BlocNum = new ArrayList<>();
        
        pass1.createIntFile();
        literal = pass1.getFinalLiteral();

        symtable = pass1.getSymtable();

        Ifile = new Files();
        Ofile = new Files();

        try {
            Ifile.openFileR("INTFILE.txt");
            Ifile.readFromFile();
            Ifile.closeFileR();
            FileFound = true;
            lines = Ifile.getLines();
        } catch (Exception e) {
            System.out.println("ERROR!!\nFile not found\n\n");
        }
    }

    public void setLists() {
        lineNum = lines.size();
        for (int i = 0; i < lineNum; i++) {
            if (lines.get(i).startsWith(".") || lines.get(i).startsWith("*") || lines.get(i).endsWith(".")) {
                Errors.add(lines.get(i));
                Locctr.add("");
                Label.add("");
                Opcode.add("");
                Operand.add("");
                BlocNum.add("");
            } else {
                try {
                    String[] line = lines.get(i).split("\t");
                    if (line[0] == null) {
                        Locctr.add("");
                    } else {
                        Locctr.add(line[0]);
                    }
                    if(line[1] == null) {BlocNum.add("");}
                    else BlocNum.add(line[1]);
                    
                    if (line[2] == null) {
                        Label.add("");
                    } else {
                        Label.add(line[2]);
                    }

                    if (line[3] == null) {
                        Opcode.add("");
                    } else {
                        Opcode.add(line[3]);
                    }

                    if (line[4].startsWith("C'") || line[4].startsWith("c'") || line[4].startsWith("X'") || line[4].startsWith("x'")) {
                        String op = "";
                        for (int j = 4; j < line.length; j++) {
                            if (line[j].startsWith(".")) {
                                break;
                            } else {
                                op += line[j] + " ";
                            }
                        }
                        Operand.add(op);

                    } else if (line.length == 5) {
                        Operand.add(line[4]);
                    } else {
                        Operand.add("");
                    }

                    Errors.add("");

                } catch (Exception e) {
                    Errors.add("");
                    Operand.add("");
                }
            }

        }
    }

    public void CreateLisFile() throws IOException {
        String BlocStart;
        int SymT,BlocS, result;
        for (int i = 0; i < lines.size(); i++) {
            
            if(!BlocNum.get(i).equals(""))
            {
                int indx = Integer.parseInt(BlocNum.get(i),16);
                BlocStart = bloc.get(indx).getAddress();
                BlocS = Integer.parseInt(BlocStart,16);
            }
            else { BlocStart="0"; BlocS = 0; }
            
            if (!"".equals(Errors.get(i))) {
                if (Errors.get(i).endsWith(".")) {
                    String errorLine = Errors.get(i).replace(".", "");
                    LisF.add(errorLine);
                } else {
                    LisF.add(("\n" + "\t" + Errors.get(i).toUpperCase() + "\n"));
                }
                ObjectCode.add(" ");
            } else {
                if (Optab.optable.containsKey(Opcode.get(i))) {
                    if (symtable.containsKey(Operand.get(i))) 
                    {
                        OpString = Optab.optable.get(Opcode.get(i));
                       
                        SymString = symtable.get(Operand.get(i));
                        if(BlocS!=0){
                        SymT = Integer.parseInt(SymString, 16);
                        result = SymT + BlocS;
                        SymString =Integer.toHexString(result);}
                        if(SymString.equals("0")) {SymString = "0000";}
                        
                        ObjString = OpString + SymString;
                        while (ObjString.length() < 6) 
                            ObjString += " ";
                        
                        ObjectCode.add(ObjString);
                        
                        
                    } 
                    else if (Operand.get(i).endsWith(",x") || Operand.get(i).endsWith(",X")) 
                    {
                        OpString = Optab.optable.get(Opcode.get(i));

                        String[] operand = Operand.get(i).split(",");

                        if (symtable.containsKey(operand[0])) {
                            int OpDecimal = Integer.parseInt(symtable.get(operand[0]), 16);
                            BinValue = HexValue.toBin(OpDecimal);
                            if (BinValue.charAt(0) == '0') {
                                BinValue = BinValue.substring(1, BinValue.length());
                                BinValue = "1" + BinValue;
                                SymString = HexValue.toHex(BinValue);
                            }

                        } else {
                            SymString = "0000";
                            FlagError = "**** undefined operand".toUpperCase();
                        }
                        
                        if(BlocS!=0){
                        SymT = Integer.parseInt(SymString, 16);
                        result = SymT + BlocS;
                        SymString =Integer.toHexString(result); }
                        
                        if(SymString.equals("0")) {SymString = "0000";}
                        
                        ObjString = OpString + SymString;
                        
                        
                        while (ObjString.length() < 6) 
                            ObjString += " ";
                        
                        ObjectCode.add(ObjString);
                        

                    }
                    else if(Operand.get(i).startsWith("="))
                    {
                        
                        OpString = Optab.optable.get(Opcode.get(i));
                        String x = Operand.get(i).substring(1);
                        String value = pass1.generateHexValue(x);
                        for(Literals l : literal)
                        {
                            if(l.getHexValue().equals(value))
                            {
                            SymString = l.getAddress();
                            break;
                            }
                        }
                        
                         if(BlocS!=0){
                        SymT = Integer.parseInt(SymString, 16);
                        result = SymT + BlocS;
                        SymString =Integer.toHexString(result);}
                         
                        if(SymString.equals("0")) {SymString = "0000";}
                        
                        ObjString = OpString + SymString;
                        
                        
                        while (ObjString.length() < 6) 
                            ObjString += " ";
                        
                        ObjectCode.add(ObjString);
                        
                    }
                    else if (!symtable.containsKey(Operand.get(i))&& !Operand.get(i).startsWith("=") && !Label.get(i).equals("*")) {
                        OpString = Optab.optable.get(Opcode.get(i));
                        SymString = "0000";
                        
                         if(BlocS!=0){
                        SymT = Integer.parseInt(SymString, 16);
                        result = SymT + BlocS;
                        SymString =Integer.toHexString(result);}
                         
                        if(SymString.equals("0")) {SymString = "0000";}
                        ObjString = OpString + SymString;
                        
                        
                        while (ObjString.length() < 6) 
                            ObjString += " ";
                        
                        ObjectCode.add(ObjString);
                        //FlagError = "**** undefined operand".toUpperCase();
                    }

                } else if (Opcode.get(i).equalsIgnoreCase("RESW") || Opcode.get(i).equalsIgnoreCase("RESB") || Opcode.get(i).equalsIgnoreCase("start") || Opcode.get(i).equalsIgnoreCase("end")||Opcode.get(i).equalsIgnoreCase("EQU")
                        ||Opcode.get(i).equalsIgnoreCase("ORG")||Opcode.get(i).equalsIgnoreCase("LTORG")) {
                    ObjectCode.add("");
                } else if (Opcode.get(i).equalsIgnoreCase("word")) {
                    int t = Integer.parseInt(Operand.get(i));
                    ObjString = Integer.toHexString (t);
                    //ObjString = Operand.get(i);
                    while (ObjString.length() != 6) {
                        ObjString = "0" + ObjString;
                    }
                    ObjectCode.add(ObjString);
                } else if (Opcode.get(i).equalsIgnoreCase("byte")) {
                    if (Operand.get(i).startsWith("c'") || Operand.get(i).startsWith("C'")) {

                        ObjString = "";
                        String BiteValue = "";
                        String[] temp = Operand.get(i).split("'");
                        for (int j = 1; j < temp.length - 1; j++) {
                            BiteValue += temp[j];
                        }

                        for (int j = 0; j < BiteValue.length(); j++) {
                            ObjString += Integer.toHexString((int) (BiteValue.charAt(j)));
                        }

                        while (ObjString.length() < 6) {
                            ObjString = "0" + ObjString;
                        }
                        if (ObjString.length() > 6) {
                            FlagError = "**** very large Operand";
                        }
                        ObjectCode.add(ObjString.toUpperCase());

                    } else if (Operand.get(i).startsWith("x'") || Operand.get(i).startsWith("X'")) {
                        ObjString = "";
                        String[] temp = Operand.get(i).split("'");
                        for (int j = 1; j < temp.length - 1; j++) {
                            ObjString += temp[j];
                        }
                        
                        if(ObjString.equals("0")) {ObjString = "0000";}
                        
                       while (ObjString.length() < 6) {
                            ObjString += " ";
                        }
                        ObjectCode.add(ObjString);

                    }
                }
                else if (Opcode.get(i).startsWith("="))
                {
                        for(Literals l : literal)
                        {
                            if(l.getAddress().equals(Locctr.get(i)))
                            {
                            ObjString = l.getHexValue();
                            
                            break;
                            }
                        }
                        while (ObjString.length() < 6) 
                            ObjString += " ";
                        
                        
                        ObjectCode.add(ObjString);
                    
                }
                else {
                    ObjectCode.add("");
                }
            }
            
            LisF.add(Locctr.get(i) + "\t"+BlocNum.get(i)+"\t" + ObjectCode.get(i).toUpperCase() + "\t" + Label.get(i) + "\t" + Opcode.get(i) + "\t" + Operand.get(i) + "\n");
            if (FlagError != "") {
                LisF.add("\n" + "\t" + FlagError.toUpperCase() + "\n");
            }
            FlagError = "";
        }

        File Lfile = new File("LISFILE.txt");
        try {
            write = new BufferedWriter(new FileWriter(Lfile));
        } catch (IOException ex) {
            Logger.getLogger(Pass2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i=0 ; i < LisF.size();i++) 
        {
            write.write(LisF.get(i));
        }
        try 
        {
            write.close();
        } catch (IOException ex) {
            Logger.getLogger(Pass2.class.getName()).log(Level.SEVERE, null, ex);
        }
       }

    public void CreateObjectCode() {
        Ofile.openFileW("OBJFILE.txt");
        Ofile.writeToFile("H" + pass1.getProgName().toUpperCase() + "  00" + pass1.getStartingAddress().toUpperCase() + pass1.getProgramLength().toUpperCase() + "\n");

        String Ob = "";

        int i;
        for (int j = 0, k = 0; j < ObjectCode.size(); j++, k++) {
            if (ObjectCode.get(j).equals(" ")) {
                if (Ob.isEmpty()) {
                    continue;
                }
                TextRec.add(Ob);
                k = 0;
                Ob = "";
                continue;
            }
            Ob += ObjectCode.get(j);

            if (k == 9) {
                TextRec.add(Ob);
                Ob = "";
                k = 0;
            }
        }
        StringBuilder build = new StringBuilder("");
        StringBuilder awelwa7ed = new StringBuilder("");
        for ( int j  = 0 ; j < TextRec.size() ; j ++ ) {
           build.append("T");
           
           awelwa7ed.append(TextRec.get(j).substring(0, 6));
           int idx = indx(awelwa7ed.toString());
           
           build.append("00"+ Locctr.get(idx));
           
           String xxx = awelwa7ed.toString();
           
           awelwa7ed.delete(0, awelwa7ed.length());
  
           String x =Integer.toHexString(TextRec.get(j).length()/2);
           if (x.length() < 2)
               x = "0" + x;
           
           build.append(x);
           
           build.append(TextRec.get(j));
           String tempT = build.toString().replace(" ", "");
           Ofile.writeToFile(tempT.toUpperCase()+"\n");
           build.delete(0, build.length());
           awelwa7ed.delete(0, awelwa7ed.length());
       
       }
        Ofile.writeToFile("E"+"00"+pass1.getStartingAddress().toUpperCase());
        Ofile.closeFileW();
     
       
       
    }
    int indx ( String x ) {
        for ( int i = 0 ; i < ObjectCode.size() ; i ++ )
            if ( ObjectCode.get(i).equalsIgnoreCase(x) )
               return i;
            
                return -1;
    }
}


