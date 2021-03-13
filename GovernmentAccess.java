import java.util.ArrayList;
import java.util.Scanner;
public class GovernmentAccess extends BasicUser
{
    //Kind of like an admin user
    private Account act = new Account("", "", "", "", "true");
    private BlockChain blockChain = new BlockChain("0");
    private ArrayList<Account> accounts = new ArrayList<Account>();
    public GovernmentAccess(Account Act, ArrayList<Account> Accounts, BlockChain BlockChain)
    {
        super(Act);
        blockChain = BlockChain;
        accounts = Accounts;
        act = Act;
    }
    public boolean grantApproval(String userName)
    {
        //gives regular users permission to access blocks
        userName = userName.toLowerCase().trim();
        for(int i = 0; i< accounts.size(); i++)
        {
            if(accounts.get(i).getUsername().equals(userName))
            {
                accounts.get(i).setApproval("true");
                System.out.println("Approval has been granted to " + userName);
                return true;
            }
        }
        return false;
    }
    public Individual modifyData(Block userBlock, String username, String SSN)
    {
            //Loads in the previous individual and modifies that data. Will ultimately create a new block with that data
            Individual individ = userBlock.getIndividual();
            String name = username.toLowerCase().trim();
            String employment = individ.getCurrentJob();
            String license = individ.getLicensing();
            int age = individ.getAge();
            String currentState = individ.getCurrentState();
            String hashSSN = SSN;
            ArrayList<String> misdemeanours = individ.getMisde();
            boolean cont = false;
            while(!cont)
           {
                System.out.println("What would you like to do?");
                System.out.println("Press 1 to change the name");
                System.out.println("Press 2 to change the job");
                System.out.println("Press 3 to change the licensing");
                System.out.println("Press 4 to change the age");
                System.out.println("Press 5 to change the current state");
                System.out.println("Press 6 to change the charges");
                System.out.println("Press 7 when you're done modifying things");
                String userInput = "";
                boolean goOn = false;
                while(!goOn)
                {
                    Scanner sc = new Scanner(System.in);
                    userInput = sc.nextLine();
            
                    if(userInput.length() == 1)
                    {
                        int ascii = (int) userInput.charAt(0);
                        if(ascii > 48 && ascii < 56)
                        {
                            goOn = true;
                        }
                        else 
                        {
                            System.out.println("Please enter an integer between 1 and 7");
                        }
                    }
                    else 
                    {
                        System.out.println("Please enter an integer between 1 and 7");
                    }
                }
                switch (userInput)
                {
                    case "1":
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Please enter the individual's first name");
                    name = controlInput(sc1.nextLine());
                    break;
                
                    case "2":
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("Please enter the individual's current employment");
                    employment = controlInput(sc2.nextLine());
                    break;
                
                    case "3":
                    Scanner sc3 = new Scanner(System.in);
                    System.out.println("Please enter the individual's working license. Write N/A if not applicable");
                    license = controlInput(sc3.nextLine());
                    break;
                
                    case "4":
                    Scanner sc4 = new Scanner(System.in);
                    System.out.println("Please enter the individual's age");
                    try{
                         age = Integer.parseInt(sc4.nextLine());
                    }
                    catch(Exception e){
                         System.out.println("Please enter an integer");
                         age = Integer.parseInt(sc4.nextLine());
                    }
                    
                    break;
                
                    case "5":
                    Scanner sc5 = new Scanner(System.in);
                    System.out.println("Please enter the individual's state in which they are employed");
                    currentState = controlInput(sc5.nextLine());
                    break;
                
                    case "6":
                    Scanner sc6 = new Scanner(System.in);
                    System.out.println("Press 1 if the individual has any criminal history or anything else to continue");
                    String UserInput = sc6.nextLine();
                    while(UserInput.equals("1"))
                    {
                        Scanner sc7 = new Scanner(System.in);
                        System.out.println("Please enter a misdemeanour");
                        String misde = controlInput(sc7.nextLine());
                        misdemeanours.add(misde);
                        System.out.println("Press 1 to add more or anything else to exit");
                        UserInput = sc7.nextLine();
                    }
                break;
                
                case "7":
                cont = true;
                break;
            }
        }
        String salt = "" + Math.random() + System.currentTimeMillis();
        //System.out.println(salt);
        return new Individual(name,employment,license,currentState,age, misdemeanours, hashSSN, salt);
    }
    private String controlInput(String str)
    {
        //Basically says you can't put & in your string and makes everything lower case
        while(str.contains("&") || str.contains(" "))
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Your string contains illegal characters");
            System.out.println("Please do not include spaces or '&' or other special characters");
            str = sc.nextLine();
        }
        str = str.toLowerCase().trim().replaceAll(" ", "");
        return str;
    }
    public Individual createIndividual()
    {
        //Creates an individual
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Please enter the individual's first name");
        String name = controlInput(sc1.nextLine());
            
        System.out.println("Please enter the individual's current employment");
        String employment = controlInput(sc1.nextLine());
        
        System.out.println("Please enter the individual's license. Write N/A if not applicable");
        String license = controlInput(sc1.nextLine());
        
        System.out.println("Please enter the individual's age");
        int age = 0;
        try{
             age = Integer.parseInt(sc1.nextLine());
        }
        catch(Exception e){
            System.out.println("Please enter an integer");
            age = Integer.parseInt(sc1.nextLine());
        }
        
        System.out.println("Please enter the individual's state in which they are employed");
        String currentState = controlInput(sc1.nextLine());
        
        System.out.println("Please enter the individual's social security number");
        String SSN = controlInput(sc1.nextLine());
        
        System.out.println("Press 1 if the individual has any criminal history or anything else to continue");
        ArrayList<String> misdemeanours = new ArrayList<String>();
        String userInput = sc1.nextLine();
        while(userInput.equals("1"))
        {
              Scanner sc2 = new Scanner(System.in);
              System.out.println("Please enter a misdemeanour");
              String misde = controlInput(sc2.nextLine());
              misdemeanours.add(misde);
              System.out.println("Press 1 to add more or anything else to exit");
              userInput = sc2.nextLine();
        }
        
        String salt = "" + Math.random() + System.currentTimeMillis();
        Individual individ = new Individual(name, employment, license, currentState, age, misdemeanours, SSN, salt);
        return individ;
    }
    
}
