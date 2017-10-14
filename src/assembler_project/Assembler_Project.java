package assembler_project;

import java.io.IOException;
import java.util.ArrayList;


public class Assembler_Project {

  public static void main(String[] args) throws IOException {
    Pass1 pass1 = new Pass1();
    pass1.createIntFile();
    Pass2 pass2 = new Pass2();
    pass2.setLists();
    pass2.CreateLisFile();
    pass2.CreateObjectCode();
    ArrayList <Literals> l = pass1.getFinalLiteral();
    ArrayList <ProgramBlocs> b = pass1.getBloc();
      System.out.println("\nLiterals Pool");
    for(Literals Lit : l) System.out.println(Lit.getName() +"\t" + Lit.getHexValue() + "\t" +Lit.getAddress() +"\t" +Lit.getLength());
      System.out.println("\nProgramBlocs\n");
    for(ProgramBlocs bl : b) System.out.println(bl.getBlockName()+"\t"+bl.getBlockNumber()+"\t" +bl.getLength()+"\t"+bl.getAddress());
    
  }
}