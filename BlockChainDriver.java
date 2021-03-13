        import java.util.Scanner;
        import java.util.ArrayList;
        import java.io.*;
        public class BlockChainDriver
        {
            //Runs the program and deals with basic functioning
            public static void main()
            {
                //Starts by accepting a starting difficulty
                 System.out.println("Welcome to the block chain");
                 
                 System.out.println();
                 System.out.println();
                 System.out.println("The purpose of this block chain is to provide" +
                 " a secure database to review potential job candidates");
                 System.out.println("If you don't have an account, you can create one, and after being approved by an" +
                 " a block chain admin, you can locate individuals for hire");
                 System.out.println();
                 System.out.println();
                    
                 System.out.println("Start by entering a starting difficulty which is a number of between 1 and 3.");
                 System.out.println("This will be the number of 0's at the start of each block's hash");
                 Scanner sc4a = new Scanner(System.in);
                 String difficulty = sc4a.nextLine();
                 boolean cont12 = false;
                 while(!cont12)
                 {
                     if(difficulty.length() > 1 || ((int) difficulty.charAt(0) < 49 || (int) difficulty.charAt(0) > 51))
                     {
                         System.out.println("Enter a number between 1 and 3");
                         Scanner sc4b = new Scanner(System.in);
                         difficulty = sc4b.nextLine();
                    }
                    else
                    {
                        cont12 = true;
                    }
                }
                String Difficulty = "";
                for(int i = 0; i < Integer.parseInt(difficulty); i++)
                {
                    Difficulty = Difficulty + "0";
                }
                //Initiate program and creates block chain
                BlockChain blockChain = new BlockChain(Difficulty);
                try{
                    blockChain.initiateProgram();
                }
                catch(Exception e)
                {
                   System.out.println("The program has been unalterably corrupted: All blocks will be deleted");
                   System.out.println("If you want to see the reMine method, please change less of the structural aspects of the block");
                   try{
                       blockChain.deleteBlocks(0);
                    }
                    catch(Exception d)
                    {
                        System.out.println(d);
                    }
                    
                }
                while(true)
                {
                    ArrayList<Account> accounts = new ArrayList<Account>();
                    try{
                        //creates set of accounts and stores them in the arrayList
                        initiateProgram(accounts);
                    }
                    catch (Exception e)
                    {
                        System.out.println("1" + e);
                    }
                    System.out.println("What would you like to do?");
                    System.out.println("Press 1 to create a new account");
                    System.out.println("Press 2 to login to an existing account");
                    System.out.println("Press 3 to exit the program");
                    String userInput = "";
                    boolean goOn = false;
                    while(!goOn)
                    {
                        Scanner sc = new Scanner(System.in);
                        userInput = sc.nextLine();
                    
                        if(userInput.length() == 1)
                        {
                            int ascii = (int) userInput.charAt(0);
                            if(ascii > 48 && ascii < 52)
                            {
                                goOn = true;
                            }
                            else 
                            {
                                System.out.println("Please enter an integer between 1 and 3");
                            }
                        }
                        else 
                        {
                            System.out.println("Please enter an integer between 1 and 3");
                        }
                    }
                    switch (userInput)
                    {
                        case "1":
                        //Attempts to create accounts and saves it to the file
                        try{
                            createAccount(accounts);
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }
                        try{
                            saveProgram(accounts);
                        }
                        catch (Exception e)
                        {
                            System.out.println(e);
                        } 
                        break;
                  
                  
                        case "2":
                        //Logs in the an account and creates a user object depending on whether you are admin
                        boolean contin = false;
                        boolean admin = false;
                        String input = "1";
                        Account act1 = new Account("", "","","", "false");
                        Account act = new Account("", "", "", "", "false");
                        while(!contin)
                        {
                            if(input.equals("1"))
                        {
                            try{
                                act = logIn(accounts);
                            }
                            catch(Exception e)
                            {
                                System.out.println(e); 
                            }
                            if(act != null)
                            {
                                act1= act;
                                contin = true;
                            }
                            else
                            {
                                System.out.println("Press 1 to try logging in again or 0 to exit");
                                Scanner sc2 = new Scanner(System.in);
                                input = sc2.nextLine();
                                if (input.equals("0"))
                                {
                                    try{
                                        closeProgram(accounts);
                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println(e);
                                    } 
                                }
                            }
                        }
                        else if (input.equals("0"))
                        {
                            try{
                                closeProgram(accounts);
                            }
                            catch (Exception e)
                            {
                                System.out.println(e);
                            } 
                        }
                        else
                        {
                            System.out.println("Press 1 to try logging in again or 0 to exit");
                            Scanner sc1 = new Scanner(System.in);
                            input = sc1.nextLine();
                        }
                }
                if(act1.getAdmin().equals("Admin"))
                {
                    try{
                        //If the account is admin then it goes to the admin access
                        adminAccess(act1, accounts, blockChain);
                    }
                    catch(Exception e)
                    {
                        System.out.println("You have encountered an unexpected error"); 
                    }
                }
                else
                {
                    try{
                        //Initiates user access
                        userAccess(act1, accounts, blockChain);
                    }
                    catch(Exception e)
                    {
                        System.out.println("You have encountered an unexpected error");
                    }
                } 
                break;
          
                case "3":
                //Closes program and saves all changes
                try{
                    closeProgram(accounts);
                }
                catch (Exception e)
                {
                    System.out.println(e);
                } 
             }
        }
    }
    public static void userAccess(Account act, ArrayList<Account> accounts, BlockChain blockChain) throws Exception
    {
        //Basic user functioning of program
        boolean cont = false;
        while(!cont)
        {
            BasicUser basicUser = new BasicUser(act);
            System.out.println("What would you like to do?");
            System.out.println("Press 1 to display the information about an individual");
            System.out.println("Press 2 to change your password");
            System.out.println("Press 3 to validate the chain");
            System.out.println("Press 4 to log out");
            System.out.println("Press 5 to exit");
            String userInput = "";
            boolean goOn = false;
            while(!goOn)
            {
                Scanner sc = new Scanner(System.in);
                userInput = sc.nextLine();
            
                if(userInput.length() == 1)
                {
                    int ascii = (int) userInput.charAt(0);
                    if(ascii > 48 && ascii < 54)
                    {
                        goOn = true;
                    }
                    else 
                    {
                        System.out.println("Please enter an integer between 1 and 5");
                    }
                }
                else 
                {
                    System.out.println("Please enter an integer between 1 and 5");
                }
            }
            switch (userInput)
            {
                case "1":
                String username = "";
                String identifier = "";
                //Only works if account has been granted approval by an admin account
                if(act.getApproval().equals("true"))
                {
                    boolean cont123 = false;
                    System.out.println("Your account has been approved for this function");
                    
                    while(!cont123)
                    {
                       Scanner sc = new Scanner(System.in);
                       System.out.println("Please enter the first name of the user who's block you want to locate");
                       username = sc.nextLine();
                       System.out.println("Please enter the user's social security number");
                       identifier = sc.nextLine();
                       //retrieves a block
                        Block userBlock = blockChain.getBlock(username, identifier);
                        if(userBlock != null)
                        {
                            System.out.println("You have succesfully located " + username + "'s block");
                            System.out.println("Here is the block's data:" + "\n" + userBlock.getIndividual().formatedString());
                            cont123 = true;
                        }
                        else
                        {
                            System.out.println("The individual you have entered doesn't exist");
                            System.out.println("Press 0 to exit or anything else to try again");
                            Scanner sc1 = new Scanner(System.in);
                            userInput = sc.nextLine();
                            if(userInput.equals("0"))
                            {
                                break;
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("Your account still needs approval. Contact an administrator to get approval");
                    break;
                }
                break;
                
                case "2":
                //Changes password
                System.out.println("What would you like your new password to be?");
                System.out.println("A password requries:");
                System.out.println("A capital letter");
                System.out.println("A special character");
                System.out.println("A minimum of 8 characters");
                Scanner sc3 = new Scanner(System.in);
                String password = sc3.nextLine();
                boolean goAhead = false;
                while(!goAhead)
                {
                    //if password exists
                    if(createPassword(password))
                    {
                        //Actually changes password
                        basicUser.changeMyPassword(password);
                        goAhead = true;
                    }
                    else
                    {
                        System.out.println("Please enter another password: ");
                        Scanner sc3a = new Scanner(System.in);
                        password = sc3a.nextLine();
                    }
                }
                try{
                    saveProgram(accounts);
                }
                catch(Exception e){
                    System.out.println(e);
                }
                break;
            
            case "3":
            //Validates chain. If the user's chain is not valid, then access is revoked
            int index = -1;
            if((index = blockChain.validateChain()) > -1)
            {
                blockChain.validateOptions(index);
                System.out.println("Your access has been revoked");
                act.setApproval("false");
            } 
            else
            {
                System.out.println("You have succesfully validated the chain");
            }
             try{
                    blockChain.saveProgram();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            break;
            
            case "4":
            //Logs out
            cont = true;
            break;
            
            case "5":
            //Closes and saves program
            try{
                closeProgram(accounts);
            }
            catch (Exception e)
            {
                System.out.println(e);
            } 
        }
        }
    }
    public static void adminAccess(Account act, ArrayList<Account> accounts, BlockChain blockChain) throws Exception
    {
        //Admin/Government functions
        boolean cont = false;
        while(!cont)
        {
            GovernmentAccess govAccess = new GovernmentAccess(act, accounts, blockChain);
            System.out.println("What would you like to do?");
            System.out.println("Press 1 to modify an individual");
            System.out.println("Press 2 to create an individual");
            System.out.println("Press 3 to validate the chain");
            System.out.println("Press 4 to change your password");
            System.out.println("Press 5 to grant another user access to the block chain");
            System.out.println("Press 6 to change difficulty");
            System.out.println("Press 7 to log out");
            System.out.println("Press 8 to exit");
            String userInput = "";
            boolean goOn = false;
            while(!goOn)
            {
                Scanner sc = new Scanner(System.in);
                userInput = sc.nextLine();
            
                if(userInput.length() == 1)
                {
                    int ascii = (int) userInput.charAt(0);
                    if(ascii > 48 && ascii < 57)
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
                String identifier = "";
                //Admin accounts automatically have approval
                if(act.getApproval().equals("true"))
                {
                    boolean cont1 = false;
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Please enter the first name of the user who's block you want to locate");
                    String username = sc.nextLine();
                    System.out.println("Please enter the individual's social security number");
                    identifier = sc.nextLine();
                    while(!cont1)
                    {
                        Block userBlock = blockChain.getBlock(username, identifier);
                        if(userBlock != null)
                        {
                            System.out.println("You have succesfully located " + username + "'s block");
                            System.out.println("Here is the block's data:" + "\n" + userBlock.getIndividual().formatedString());
                            System.out.println("Press 1 to modify this individual's data or anyting else to exit");
                            Scanner sc2 = new Scanner(System.in);
                            String UserInput = sc2.nextLine();
                            if(UserInput.equals("1"))
                            {
                                //Creates a new block and adds it to the chain
                                blockChain.produceBlocks(govAccess.modifyData(userBlock, username, identifier));
                                cont1 = true;
                            }
                            else
                            {
                                break;
                            }
                        }
                        else
                        {
                            System.out.println("The individual you have entered doesn't exist");
                            System.out.println("Press 0 to exit or anything else to try again");
                            Scanner sc1 = new Scanner(System.in);
                            userInput = sc.nextLine();
                            if(userInput.equals("0"))
                            {
                                break;
                            }
                            else {
                                System.out.println("Please enter the individuals name");
                                username = sc1.nextLine();
                                System.out.println("Please enter the individual's social security number");
                                identifier = sc1.nextLine();
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("Your account still needs approval");
                    break;
                }
                 try{
                    blockChain.saveProgram();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                break;
                
                case "2":
                //Creates a new individual and adds it to the chain
                blockChain.produceBlocks(govAccess.createIndividual());
                try{
                    blockChain.saveProgram();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                break;
                
                case "3":
                //Checks if chain is valid
                int index = -1;
                if((index = blockChain.validateChain()) > -1)
                {
                    blockChain.validateOptions(index);
                } 
                else
                {
                    System.out.println("You have succesfully validated the chain");
                }
                try{
                    blockChain.saveProgram();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                break;
                case "4":
                //Changes password
                System.out.println("What would you like your new password to be?");
                System.out.println("A password requries:");
                System.out.println("A capital letter");
                System.out.println("A special character");
                System.out.println("A minimum of 8 characters");
                Scanner sc3 = new Scanner(System.in);
                String password = sc3.nextLine();
                boolean goAhead = false;
                while(!goAhead)
                {
                    //if password exists
                    if(createPassword(password))
                    {
                        //Actually changes password
                        govAccess.changeMyPassword(password);
                        goAhead = true;
                    }
                    else
                    {
                        System.out.println("Please enter another password: ");
                        Scanner sc3a = new Scanner(System.in);
                        password = sc3a.nextLine();
                    }
                }
                try{
                    saveProgram(accounts);
                }
                catch(Exception e){
                    System.out.println(e);
                }
                break;
                
            case "5":
                //Grants access to regular users. 
                System.out.println("Please enter the name of the person you would like to grant access to the block chain");
                Scanner sc3a = new Scanner(System.in);
                String username = sc3a.nextLine();
                while(!(govAccess.grantApproval(username)))
                {
                    System.out.println("The username does not exist. Press 1 to exit or enter another username to try again.");
                    Scanner sc3d = new Scanner(System.in);
                    username = sc3d.nextLine();
                    if(username.equals("1"))
                    {
                        break;
                    }
                }
                try{
                    saveProgram(accounts);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                break;
         
            case "6":
            //Changes difficulty
            System.out.println("What would you like the new difficulty to be. Enter the number of leading zeros between 1 and 3.");
            Scanner sc4a = new Scanner(System.in);
            String zeros = sc4a.nextLine();
            boolean cont12 = false;
            while(!cont12)
            {
                if(zeros.length() > 1 || ((int) zeros.charAt(0) < 49 || (int) zeros.charAt(0) > 51))
                {
                    System.out.println("Enter a number between 1 and 3");
                    Scanner sc4b = new Scanner(System.in);
                    zeros = sc4b.nextLine();
                }
                else
                {
                    String Difficulty = ""; 
                    for(int i = 0; i < Integer.parseInt(zeros); i++)
                    {
                         Difficulty += "0";
                    }
                    blockChain.changeDifficulty(Difficulty);
                    cont12 = true;
                }
            }
            try{
                    blockChain.saveProgram();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            break;
            
            case "7":
            //Logs out
            cont = true;
            break;
            
            case "8":
            //Closes and saves program
            try{
                closeProgram(accounts);
            }
            catch (Exception e)
            {
                System.out.println(e);
            } 
        }
        }
    }
    public static Account logIn(ArrayList<Account> accounts) throws Exception
    {
       //Log in system. Returns the logged in account
       Scanner sc = new Scanner(System.in);
       System.out.println("Please enter your username");
       String username = sc.nextLine();
       
       username = username.toLowerCase();
       System.out.println("Please enter your password");
       String password = sc.nextLine();
       //Checks if the account exists and return account
       Account act = checkUsername(username, accounts);
       
       if(act != null)
       {
           //Checks password
           if(checkPassword(password, act))
           {
               System.out.println("You have succesfully logged in as a " + act.getAdmin());
               return act;
           }
       }
       System.out.println("Either your username or password is incorrect");
       return null;
   }
     public static Account checkUsername(String username, ArrayList<Account> accounts)
   {
       //Pretty self explanatory. Runs through arraylist and checks if username is there
       if(username.contains(" "))
       {
           return null;
       }
       for(int i = 0; i < accounts.size(); i++)
       {
           if(username.equals(accounts.get(i).getUsername()))
           {
               return accounts.get(i);
           }
       }
       return null;
   }
   public static Boolean checkPassword(String password, Account act)
   {
       //Adds salt to password and tests if it equals the given hash
       String hashPassword = password + act.getSalt();
       Hash hash1234 = new Hash(hashPassword);
       if(hash1234.getHash().equals(act.getHash()))
       {
           return true;
       }
       return false;
   }
   public static Boolean createPassword(String password)
   {
       //Checks if a password matches conditions
       if(password.length() < 8)
       {
           System.out.println("Your password is too short");
           return false;
       }
       int counterCaps = 0;
       int counterNonLetters = 0;
       for(int i = 0; i < password.length(); i++)
       {
           char character = password.charAt(i);
           int ascii = (int) character;
           if(ascii < 33 || ascii == 34 || ascii == 92)
           {
               //I could have added more unacceptable characters but I think you get the gist
               System.out.println("You have unacceptable characters in your password");
               return false;
           }
           if(ascii > 64 && ascii < 91)
           {
               //Make sure there is a capital
               counterCaps++;
           }
           if(!Character.isLetter(character))
           {
               //Make sure there is a nonletter too
               counterNonLetters++;
           }
        }
       if(counterCaps == 0)
       {
           System.out.println("You have no capital letters");
           return false;
       }
       if(counterNonLetters == 0)
       {
           System.out.println("You have no special characters");
           return false;
       }
       return true;
   }
   public static void createAccount(ArrayList<Account> accounts) throws Exception
   {
       //Creates an account and adds it to arrayList of accounts
       Boolean admin = false;
       Scanner sc = new Scanner(System.in);
       System.out.println("Press 1 if you are an admin or anything else if you are a regular user");
       String isAdmin = sc.nextLine();
       //check if you are admin
       //Password 12345
       String hash1 = "5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5";
       if(isAdmin.equals("1"))
       {
           for(int i = 3; i > -1; i--)
           {
                Scanner sc1 = new Scanner(System.in); 
                System.out.println("Please enter the 5 digit admin code");
                String code = new Hash(sc1.nextLine()).getHash();
                if(code.equals(hash1))
                {
                    admin = true;
                    i = -50;
                }
                else
                {
                    System.out.println("Your code was incorrect");
                    System.out.println("You have " + i + " attempts remaining");
                }
           }
           if(!admin)
           {
               System.out.println("Your attempt to create an account was unsuccesful");
               return;
           }
       }
       System.out.println("Please enter a username");
       String username = sc.nextLine();
       while(username.contains(" "))
       {
           Scanner sc2 = new Scanner(System.in);
           System.out.println("Please enter a username without spaces");
           username = sc2.nextLine();
       }
       
       username = username.toLowerCase();
       username = username.trim();
       boolean goAhead = false;
       while(!goAhead)
       {
           //if username is not already taken 
           if(checkUsername(username, accounts) == null)
            {
                goAhead = true;
            }
            else
            {
                System.out.println("This username was already taken");
                System.out.println("Please enter another: ");
                Scanner sc3 = new Scanner(System.in);
                username = sc3.nextLine();
                username = username.toLowerCase();
                username = username.trim();
            }
       }
       System.out.println("Please enter a password: ");
       System.out.println("A password requries:");
       System.out.println("A capital letter");
       System.out.println("A special character");
       System.out.println("A minimum of 8 characters");
       String password = sc.nextLine();
       goAhead = false;
       while(!goAhead)
       {
           //If the password matches standards 
           if(createPassword(password))
            {
                goAhead = true;
            }
            else
            {
                System.out.println("Please enter another password: ");
                password = sc.nextLine();
            }
       }
       //Creates salt and adds to password
       String salt = generateSalt();
       Hash hash156 = new Hash(password.trim() + salt);
       String hash = hash156.getHash();
       //Creates a random average
       int average = (int) (1 + Math.random() * 10000);
       //Creates the account and adds it to arraylist
       if(admin)
       {
           accounts.add(new Account(username, hash, salt, "Admin", "true"));
       }
       else
       {
           accounts.add(new Account(username, hash, salt, "User", "false"));
        }
        System.out.println("You have succesfully created an account");
        try{
                saveProgram(accounts);
            }
            catch(Exception e){
                System.out.println(e);
            }
   }
   public static String generateSalt()
   {
       //Pretty simple but effective salt. No one can ever guess this number output and it will likely never be the same
       return "" + Math.random() + System.currentTimeMillis();
   }
   public static void initiateProgram(ArrayList<Account> accounts) throws Exception
   {
       //Translates data from files into an arraylist of accounts
       FileReader reading = new FileReader("passwords.txt");
       BufferedReader br = new BufferedReader(reading);
       String currentLine = null;
       currentLine = br.readLine();
       while(currentLine != null)
       {
           String[] lines = currentLine.split(" ", 5);
           //Splits line into its components and adds them into an account
           accounts.add(new Account(lines[0].trim(), lines[1].trim(), lines[2].trim(), lines[3].trim(), lines[4].trim()));
           currentLine = br.readLine();
       } 
   }
   public static void saveProgram(ArrayList<Account> accounts) throws Exception
   {
       //Write account information to file in specified order
       File newestFile = new File("passwords.txt");
       FileWriter writing = new FileWriter(newestFile);
       BufferedWriter bw = new BufferedWriter(writing);
       for(int i = 0; i < accounts.size(); i++)
       {
           bw.write(accounts.get(i).getUsername() + " ");
           bw.write(accounts.get(i).getHash() + " ");
           bw.write(accounts.get(i).getSalt() + " ");
           bw.write(accounts.get(i).getAdmin() + " ");
           bw.write(accounts.get(i).getApproval() + "");
           bw.newLine();
       }
       bw.close();
   }
   public static void closeProgram(ArrayList<Account> accounts) throws Exception
   {
       //Basically saves program and exits
       saveProgram(accounts);
       System.exit(0);
    }
}


