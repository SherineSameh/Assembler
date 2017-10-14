package assembler_project;
public class ProgramBlocs 
{
    private String BlockName;
    private String Address;
    private String Length;
    private int BlockNumber;
    
    private int decimallength;
    private String nextDestination;

    public ProgramBlocs(String BlockName, int BlockNumber) {
        this.BlockName = BlockName;
        this.BlockNumber = BlockNumber;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setLength(String Length) {
        this.Length = Length;
    }

    public String getBlockName() {
        return BlockName;
    }

    public String getAddress() {
        return Address;
    }

    public String getLength() {
        return Length;
    }

    public int getBlockNumber() {
        return BlockNumber;
    }
    
    
    
    
}
