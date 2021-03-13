import java.util.ArrayList;
public class Individual
{
    //Basic data component of the blocks
    private String hashedName = "";
    private String currentJob = "";
    private String licensing = "";
    private String currentState = "";
    private int age = 0;
    private String ssn = "";
    private String salt = "";
    private ArrayList<String> misdemeanours = new ArrayList<String>();
    public Individual(String name, String CurrentJob, String Licensing, String CurrentState, int Age, ArrayList<String> Misdemeanours, String SSN, String Salt)
    {
        salt = Salt;
        currentJob = CurrentJob;
        licensing = Licensing;
        currentState = CurrentState;
        age = Age;
        misdemeanours = Misdemeanours;
        //System.out.println("SSN" + SSN);
        //System.out.println("name" + name);
        if((SSN.length() < 30 || name.length() < 30) && !name.contains("genesis"))
        {
            // System.out.println("yodel");
            ssn = new Hash(SSN + salt).getHash();
            // System.out.println(SSN);
            hashedName = new Hash(name + salt).getHash();
        }
        else
        {
            ssn = SSN;
            hashedName = name;
        }
    }
    public String getHashedName()
    {
        return hashedName;
    }
    public String getCurrentJob()
    {
        return currentJob;
    }
    public String getSalt()
    {
        return salt;
    }
    public String getLicensing()
    {
        return licensing;
    }
    public String getCurrentState()
    {
        return currentState;
    }
    public int getAge()
    {
        return age;
    }
    public ArrayList<String> getMisde()
    {
        return misdemeanours;
    }
    public String getSSN()
    {
        return ssn;
    }
    public String getMisedemeanours()
    {
        String misdemeans = "";
        for(int i = 0; i < misdemeanours.size(); i++)
        {
            if(i == misdemeanours.size() - 1)
            {
                misdemeans = misdemeans + misdemeanours.get(i);
            }
            else if(i < misdemeanours.size() - 1)
            {
                misdemeans = misdemeans + misdemeanours.get(i) + ", ";
            }
            else
            {
                misdemeans = misdemeans + misdemeanours.get(i);
            }
        }
        return misdemeans;
    }
    public void setHashedName(String HashedName)
    {
         hashedName = HashedName;
    }
    public void getCurrentJob(String CurrentJob)
    {
        currentJob = CurrentJob;
    }
    public void getLicensing(String Licensing)
    {
         licensing = Licensing;
    }
    public void getCurrentState(String CurrentState)
    {
         currentState = CurrentState;
    }
    public void getAge(int Age)
    {
        age = Age;
    }
    public String toString()
    {
        //Puts everything in a format which can be decoded when read from a file
        String str = getHashedName()+"&&"+getCurrentJob()+"&&"+getLicensing()+"&&"+
        getCurrentState()+"&&"+getAge()+"&&"+getMisedemeanours() + "&&" + getSSN() + "&&" + getSalt() + "&&";
        return str;
    }
    public String formatedString()
    {
        //This is the string the user will see when getting a block
        String str = "";
        if(getMisedemeanours().length() == 0)
        {
             str = "Hashed Name: " + getHashedName() + "\n" +
                   "Current Job: " + getCurrentJob() + "\n" +
                   "Licensing: " + getLicensing() + "\n" +
                   "Age: " + getAge() + "\n" +
                   "State of employment: " + getCurrentState() + "\n" +
                   "Misdemeanours: " + "none";
        }
        else
        {
             str = "Hashed Name: " + getHashedName() + "\n" +
                   "Current Job: " + getCurrentJob() + "\n" +
                   "Licensing: " + getLicensing() + "\n" +
                   "Age: " + getAge() + "\n" +
                   "State of employment: " + getCurrentState() + "\n" +
                   "Misdemeanours: " + getMisedemeanours();
        }
        return str;
    }
}
    
    
    

