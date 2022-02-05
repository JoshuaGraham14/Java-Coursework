import javax.lang.model.util.ElementScanner14;

public class CheckPassword 
{
    public static boolean longEnough(String password) 
    {
        if (password.length() >= 12)
        {
            return true;
        } 
        return false;
    }

    public static boolean atLeastTwoDigits(String password) 
    {
        int numOfDigits = 0;
        for (int i = 0; i < password.length(); i++)
        {
            if (Character.isDigit(password.charAt(i)) == true)
            {
                numOfDigits++;
            }
        }

        if (numOfDigits >= 2)
        {
            return true;
        } 
        return false;
    }

    public static void main(String[] args) 
    {
        if (longEnough(args[0]) == true & atLeastTwoDigits(args[0]) == true)
        {
            System.out.println("Password is valid");
        }
        else if(args[0] == null)
        {
            System.out.println(System.err);
            System.exit(1);
        }
        else 
        {
            System.out.println("Password is not valid");
        }
    }
}