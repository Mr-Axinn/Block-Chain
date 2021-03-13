import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
//Court cases
public class BlockChain
{
    private ArrayList<Block> blockChain = new ArrayList<Block>();
    private String difficulty;
    public BlockChain(String Difficulty)
    {
        difficulty = Difficulty;
    }
    public void produceBlocks(Individual individ) throws Exception
    {
        //Method which sets up the production of blocks, especially for the genesisBlock  
        if(blockChain.size() == 0)
        {
            ArrayList<String> a1r = new ArrayList<String>();
            Individual individual = new Individual("genesisBlock","","","",0, a1r, "0", "");
            this.mineBlock(individual, "", -1, "0");
        }
        String prevHash = getHash(blockChain.get((blockChain.size() - 1)));
        //System.out.println(prevHash);
        this.mineBlock(individ, prevHash, -1, difficulty);
    }
    public int validateChain() throws Exception
    {
        //Validates the chain
        for(int i = 0; i < blockChain.size() - 1; i++)
        {
            String hash = "";
            hash = getHash(blockChain.get(i));
            if(i == 0)
            {
                //Special condition for genesisBlock to check it with itself and blocks after it
                ArrayList<String> a1r = new ArrayList<String>();
                Individual individ1= new Individual("genesisBlock","","","", 0, a1r, "0", "");
                String firstHash = new Hash(individ1.toString() + "0" + "0" + 0 + 0 + 0).getHash();
               
                if(!firstHash.equals(blockChain.get(i).getPrevHash()))
                {
                    
                    return blockChain.get(i).getIndex();
                }
                if(!hash.equals(blockChain.get(i+1).getPrevHash()))
                {
                    
                    return blockChain.get(i).getIndex();
                }
            }
            else if(!hash.equals(blockChain.get(i+1).getPrevHash()))
            {
                    //Checks if next blocks previous hash matches
                    return blockChain.get(i).getIndex();
            }
            else if(!(hash.substring(0, blockChain.get(i).getDifficulty().length()).equals(blockChain.get(i).getDifficulty())))
            {
                //Checks if the hash is the correct difficutly. This should rarely be used
                return blockChain.get(i).getIndex(); 
            }
        }
        return -1;
    }
    private int mineBlock(Individual individ, String prevHash, int index, String Difficulty) throws Exception
    {
       //Mines blocks 
       if(blockChain.size() == 0)
       {
            //Special case for when the block chain has no blocks yet 
            String timeStamp = "" + System.currentTimeMillis();
            String PrevHash = new Hash(individ.toString() + "0" + "0" + 0 + 0 + 0).getHash();
            blockChain.add(new Block(individ, PrevHash, timeStamp, 0, 0, Difficulty));   
            return 10;    
       }
       Block prevBlock = blockChain.get(blockChain.size() - 1);
       Long nonce;
       String timeStamp = "" + System.currentTimeMillis();
       if(index == -1)
       {
           index = blockChain.size();
       }
       
       for(int i = 0; i < 100000; i++)
       {           
           //Deals with nonce creation and difficulty
           String hash = "";
           hash = new Hash(individ.toString() + prevHash + timeStamp + index + Difficulty + i).getHash();
           if(hash.substring(0,difficulty.length()).equals(Difficulty))
           {
               //Only activates when nonce makes hash match difficulty
               blockChain.add(index, new Block(individ, prevHash, timeStamp, index, i, Difficulty));
               
               return 10;
           }
           
       }
       try{
           this.saveProgram();
       }
       catch(Exception e)
       {
          System.out.println("Stop trying to break my code"); 
       }
       
       return 0;
    }
    public void initiateProgram() throws Exception
    {
       //Starts the program by entering information into blocks 
       FileReader reading = new FileReader("blockChain.txt");
       BufferedReader br = new BufferedReader(reading);
       String currentLine = null;
       currentLine = br.readLine();
       while(currentLine != null && currentLine.length()>0)
       {
           String[] lines = currentLine.split(" ", 6);
           Individual individual = createIndividual(lines[0]);      
           blockChain.add(new Block(individual, lines[1], lines[2], Integer.parseInt(lines[3]), Integer.parseInt(lines[4].trim()), lines[5].trim()));
           currentLine = br.readLine();
       } 
       int index = -1;
       if((index = this.validateChain()) > -1)
       {
           validateOptions(index);
       } 
       else
       {
           System.out.println("Your chain has been validated");
        }
    }
    public void validateOptions(int index) throws Exception
    {
          //Allows user to select options if the block chain has been compromised. Provides index of that block
          System.out.println("Part of you block chain has been compromised");
          System.out.println("Press 1 to delete the entire chain, 2 to delete the invalid blocks, or 3 to remine the chain");
          Scanner sc = new Scanner(System.in);
          boolean continu = false;
          while(!continu)
          {
              Scanner sc1 = new Scanner(System.in);
              String userInput = sc1.nextLine();
              if(userInput.equals("1"))
              {
                  this.deleteBlocks(0);
                  continu = true;
              }
              else if(userInput.equals("2"))
              {
                  this.deleteBlocks(index);
                  continu = true;
              }
              else if(userInput.equals("3"))
              {
                  this.reMineChain(index);
                  continu = true;
              }
              else
              {
                 System.out.println("Please enter a valid input");  
              }
          }
        
    }
    private Individual createIndividual(String str) throws Exception
    {
        //Initiate the creation of individuals when reading from the file
        if(str.contains("genesisBlock"))
        {
            ArrayList<String> ar = new ArrayList<String>();
            Individual individ = new Individual("genesisBlock", "","","", 0, ar, "0", "");
            return individ;
        }
        //System.out.println(str);
        String[] elements = str.split("&&",8);
        //System.out.println(elements[5]);
        String[] misdemeanours = elements[5].split(",",10);
        ArrayList<String> misde = new ArrayList<String>();
        for(int i = 0; i < misdemeanours.length; i++)
        {
            misde.add(misdemeanours[i].replace("&", ""));
        }
         //System.out.println("Here");
        Individual individ = new Individual(elements[0].replaceAll("&", "").replaceAll(" ", ""), elements[1].replaceAll("&", "").replaceAll(" ", ""),
        elements[2].replaceAll("&", "").replaceAll(" ", ""), elements[3].replaceAll("&", "").replaceAll(" ", ""), 
        Integer.parseInt(elements[4]), misde, elements[6].replaceAll("&", "").replaceAll(" ", ""), elements[7].replaceAll("&", "").replaceAll(" ", ""));
       // System.out.println(elements[7]);
        return individ;
        
    }
    public void saveProgram() throws Exception
    {
       //Saves program
       File newestFile = new File("blockChain.txt");
       FileWriter writing = new FileWriter(newestFile);
       BufferedWriter bw = new BufferedWriter(writing);
       for(int i = 0; i < blockChain.size(); i++)
       {
           bw.write(blockChain.get(i).getIndividual().toString() + " ");
           bw.write(blockChain.get(i).getPrevHash() + " ");
           bw.write(blockChain.get(i).getTimeStamp() + " ");
           bw.write(blockChain.get(i).getIndex() + " ");
           bw.write(blockChain.get(i).getNonce() + " ");
           bw.write(blockChain.get(i).getDifficulty() + "");
           bw.newLine();
       }
       bw.close();
    }
    private void closeProgram() throws Exception
    {
        //Closes program
        try{
            this.saveProgram();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        System.exit(0);
    }
    public void changeDifficulty(String Difficulty) throws Exception
    {
        //Changes difficulty. Will only accept difficulty from 1 to 3 zeros
        difficulty = Difficulty;
    }
    public void deleteBlocks(int index) throws Exception
    {
        //Deletes blocks from a specfifed index on
        index++;
        for(int i = 0; i < blockChain.size(); i++)
        {
            if(blockChain.get(i).getIndex() >= index)
            {
                blockChain.remove(i);
                i--;
            }
        }
        try{
            this.saveProgram();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    private void reMineChain(int index) throws Exception
    {
        //Remine chain from two blocks after a specified index to insure that no blocks have been modified
        ArrayList<Block> prevBlocks = new ArrayList<Block>();
        for(int i = (index + 2); i < blockChain.size(); i++)
        {
            //System.out.println("here + " + blockChain.get(i));
            prevBlocks.add(blockChain.get(i)); 
        }
        
        for(int i = index; i < blockChain.size(); i++)
        {
            //System.out.println(i);
            if(blockChain.get(i).getIndex() >= index)
            {
                blockChain.remove(i);
                i--;
            }
        }
       
        int ind = index;
        int size = 0;
        if(ind == 0)
        {
                //Special genesis block handling
                String prevHash = "";
                ArrayList<String> ar = new ArrayList<String>();
                Individual individ = new Individual("genesisBlock", "","","", 0, ar, "0","");                
                this.mineBlock(individ, prevHash, -1, difficulty);
                for(int i = 0; i < prevBlocks.size(); i++)
                {
                     ind = index + i;
                     if(i == 0)
                     {
                      
                         prevHash = getHash(blockChain.get(0));
                         
                         try{
                             this.saveProgram();
                            }
                            catch(Exception e)
                            {
                                System.out.println(e);
                            }
                         this.mineBlock(prevBlocks.get(i).getIndividual(), prevHash, -1, prevBlocks.get(i).getDifficulty());
                      
                     }
                     else
                     {
                        if(ind < blockChain.size())
                        {
                             prevHash = getHash(blockChain.get(ind));
                             this.mineBlock(prevBlocks.get(i).getIndividual(), prevHash, -1, prevBlocks.get(i).getDifficulty());
                           
                        }
                     }
                     
                }
        }
        else if (ind == 1)
        {
            //Special first block modified handling
            index = 0;
            for(int i = 0; i < prevBlocks.size(); i++)
            {
                ind = index + i;
                String prevHash = getHash(blockChain.get(ind));
                this.mineBlock(prevBlocks.get(i).getIndividual(), prevHash, -1, prevBlocks.get(i).getDifficulty());
            }
        }
        else 
        {
            //Handling everything else    
                for(int i = 0; i < prevBlocks.size(); i++)
                {
                     ind = index + i;
                     String prevHash = getHash(blockChain.get(ind-1));
                     this.mineBlock(prevBlocks.get(i).getIndividual(), prevHash, -1, prevBlocks.get(i).getDifficulty());
                }
        }
        try{
            this.saveProgram();
        }
        catch(Exception e)
        {
            System.out.println("Stop trying to break my code");
        }
    }
    public String getHash(Block prevBlock) throws Exception
    {
        //Gets the hash of a block
        Hash prevAsh = new Hash(prevBlock.getIndividual().toString() + prevBlock.getPrevHash() 
        + prevBlock.getTimeStamp() + prevBlock.getIndex() + prevBlock.getDifficulty() + prevBlock.getNonce());
        return prevAsh.getHash();
    }
    public Block getBlock(String username, String SSN) throws Exception
    {
        //Returns a block given a specified username and social security number. Will return the most 
        //recent version of the modified block with the username and social security number
        username = username.toLowerCase().trim();
        
        for(int i = (blockChain.size() - 1); i > -1 ; i--)
        {
            Individual individ = blockChain.get(i).getIndividual();
            String salt = individ.getSalt().replaceAll("&", "");
            String hash = (new Hash(username + salt).getHash());
            String hashSSN = (new Hash(SSN + salt)).getHash();
            String blockHash = individ.getHashedName();
            String blockSSN = individ.getSSN();
            if(hash.equals(blockHash) && blockSSN.equals(hashSSN))
            {
                return blockChain.get(i);
            }
        }
        return null;
    }
}
