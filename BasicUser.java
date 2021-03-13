import java.util.ArrayList;
public class BasicUser 
{
    //Parent class of Admin. Pretty much is the driver code for the accounts
    //Block userBlock = new Block("", "", "", 0, 0, "");
    private Account myAccount = new Account("", "", "", "", "false");
    public BasicUser(Account Act)
    {
        //userBlock = UserBlock;
        myAccount = Act;
    }
    public void changeMyPassword(String password)
    {
       //Pretty obvious
        myAccount.changePassword(password);
    }
    public String getCurrentAproval()
    {
        //Pretty obvious
        return myAccount.getApproval();
    }
}
