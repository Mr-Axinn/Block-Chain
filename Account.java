public class Account
{
    //The base unit of my program. Has all necessary information for an account
    private String username = "";
    private String salt = "";
    private String hash = "";
    private String admin = "";
    private String approved = "false"; 
    public Account(String Username, String Hash, String Salt, String Admin, String Approved)
    {
        username = Username;
        salt = Salt;
        hash = Hash;
        admin = Admin;
        approved = Approved;
    }
    public String getUsername()
    {
        //Obvious
        return username;
    }
    public String getHash()
    {
        //Obvious
        return hash;
    }
    public String getSalt()
    {
       //Obvious
        return salt;
    }
    public String getAdmin()
    {
        //Obvious
        return admin;
    }
    public String getApproval()
    {
        return approved;
    }
    public String changePassword(String newPassword)
    {
        //Adds salt to userInput and hashes it
        Hash hash123 = new Hash(newPassword + this.generateSalt());
        hash = hash123.getHash();
        return hash;
    }
    public void setApproval(String Approved)
    {
        //Can only be accessed by an Admin
        approved = Approved;
    }
     public String generateSalt()
     {
       //Pretty simple but effective salt. No one can ever guess this number output and it will likely never be the same
       salt = "" + Math.random() + System.currentTimeMillis();
       return salt;
    }
    
}
