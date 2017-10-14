package assembler_project;

import java.util.ArrayList;
import java.util.TreeMap;

public class Hexadecimal {
private int number;
private String result;
private final ArrayList <String> bits;

    public Hexadecimal() {
    number=0;
    result=null;
    bits = new ArrayList <>();
    }

    private static final TreeMap <String , String> hex = new TreeMap<>();
    static{
        hex.put("0000","0");
        hex.put("0001","1");
        hex.put("0010","2");
        hex.put("0011","3");
        hex.put("0100","4");
        hex.put("0101","5");
        hex.put("0110","6");
        hex.put("0111","7");
        hex.put("1000","8");
        hex.put("1001","9");
        hex.put("1010","A");
        hex.put("1011","B");
        hex.put("1100","C");
        hex.put("1101","D");
        hex.put("1110","E");
        hex.put("1111","F");
    }
    
    
    
    private static final TreeMap <String , String> bin = new TreeMap<>();
    static{
        bin.put("0","0000");
        bin.put("1","0001");
        bin.put("2","0010");
        bin.put("3","0011");
        bin.put("4","0100");
        bin.put("5","0101");
        bin.put("6","0110");
        bin.put("7","0111");
        bin.put("8","1000");
        bin.put("9","1001");
       }
    
    
    public String toHex(String bin){
        
        StringBuilder Hex = new StringBuilder("");        
        StringBuilder ret = new StringBuilder(bin);
        ret.reverse();
        while ( ret.length() != 16 )  ret.append("0");
	bin = ret.reverse().toString();
	
        for (int i = 0; i <16; i++) 
                {
                bits.add(bin.substring(0,4));
                bits.add(bin.substring(4,8));
                bits.add(bin.substring(8,12));
                bits.add(bin.substring(12,16));
                }
         Hex.append(hex.get(bits.get(0)));
         Hex.append(hex.get(bits.get(1)));
         Hex.append(hex.get(bits.get(2)));
         Hex.append(hex.get(bits.get(3)));
         
         result = Hex.toString();
         return result;   
    }
    public String toBin(int num)
    {
        StringBuilder Bin = new StringBuilder("");        
        StringBuilder ret = new StringBuilder(Integer.toString(num));
        ret.reverse();
        while ( ret.length() != 4 )  ret.append("0");
	ret.reverse().toString();
        
         Bin.append(bin.get(ret.charAt(0)+""));
         Bin.append(bin.get(ret.charAt(1)+""));
         Bin.append(bin.get(ret.charAt(2)+""));
         Bin.append(bin.get(ret.charAt(3)+""));
         
         result = Bin.toString();
         return result;
    }
    /*
    public String addBinary(String a , int number2) {
         
        for (int i = 0; i < 4; i++) {
            
        }
        String b =Integer.toBinaryString(number2);
        
        if (a==null ||a.length()==0){
            return b;
        }
        
        if (b==null || b.length()==0){
            return a;
        }
        
       StringBuilder sb=new StringBuilder();
       
        
        int lastA=a.length()-1;
        int lastB=b.length()-1;
        int carry=0;
        
        
        while (lastA>=0 ||lastB>=0 ||carry>0){
            int num1=lastA>=0?a.charAt(lastA--)-'0':0;
            int num2=lastB>=0?b.charAt(lastB--)-'0':0;
            int current=(num1+num2+carry)%2;
            carry=(num1+num2+carry)/2;
            
            sb.insert(0, current);
            
            
        }
        
        return sb.toString();
    }
*/
    
    public int getNumber() {
        return number;
    }

    public String getResult() {
        return result;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setResult(String result) {
        this.result = result;
    }
    

}
   