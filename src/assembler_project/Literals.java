package assembler_project;
public class Literals 
{
 private final String name;
 private String hexValue;
 private int length;
 private String Address;
 private boolean used;
 private String temp;
    public Literals(String name, String hexValue,int length) {
        this.name = name;
        this.hexValue = hexValue;
        this.length = length;
        
        used= false;
    }
   
    Literals() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setLength(String hexValue) {
       length =hexValue.length()/2;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isUsed() {
        return used;
    }

    public String getName() {
        return name;
    }

    public String getHexValue() {
        return hexValue;
    }

    public int getLength() {
        return length;
    }

    public String getAddress() {
        return Address;
    }

    public void resetHexValue(String hexValue)
    {
        this.hexValue = hexValue;
    }
    public void setAddress(String Address) {
        this.Address = Address;
    }
    
 
    
}

