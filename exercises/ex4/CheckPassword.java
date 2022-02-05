import javax.lang.model.util.ElementScanner14;

public class CheckPassword 
{
    public static boolean longEnough(String password) 
    {
        
        return true;
    }

    public static boolean atLeastTwoDigits(String password) 
    {
        return true;
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