import java.util.ArrayList;
public class Block
{
    ArrayList<String> array = new ArrayList<String>();
    private Individual individual = new Individual("","","","",0,array, "0", "");
    private String prevHash = "";
    private String timeStamp = "";
    private String difficulty = "";
    private int nonce = 0;
    private int index = 0;
    
    public Block(Individual Indvidual, String PrevHash, String TimeStamp, int Index, int Nonce, String Difficulty)
    {
        individual = Indvidual;
        prevHash = PrevHash;
        timeStamp = TimeStamp;
        nonce = Nonce;
        index = Index;
        difficulty = Difficulty;
    }
    public Individual getIndividual()
    {
        return individual;
    }
    public String getPrevHash()
    {
        return prevHash;
    }
    public String getTimeStamp()
    {
        return timeStamp;
    }
    public int getNonce()
    {
        return nonce;
    }
    public int getIndex()
    {
        return index;
    }
    public String getDifficulty()
    {
        return difficulty;
    }
}
