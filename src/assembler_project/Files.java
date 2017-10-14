package assembler_project;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Files {
    private Formatter outputFile;
    private String nameOfFile;
    private Scanner inputFile;
   
    private final ArrayList <String> Lines;
    
    public Files() {
       Lines = new ArrayList <>();
    }
    
    public String getNameOfFile() {
        return nameOfFile;
    }

    public void setNameOfFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }
    
    public void openFileW(String nameOfFile)
    {
       
        try {
            outputFile = new Formatter (nameOfFile);
        
        } catch (FileNotFoundException e) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, e);
        }     
    }

    
    public void writeToFile(String args)
    {
        outputFile.format("%s", args);
    }
    public void closeFileW()
    {
        outputFile.close();
    }
   
    
    public void openFileR(String nameOfFile)
    {
        try {
            
          inputFile = new Scanner(new File(nameOfFile));
          
        } catch(Exception e) { }
    }
    
    public void readFromFile ()
    {
        while(inputFile.hasNext())
        {
            String line = inputFile.nextLine();
            Lines.add(line);
        }  
    }
    
    public ArrayList<String> getLines() {
        return Lines;
    } 
   
    public void closeFileR()
    {
        inputFile.close();
    } 
}