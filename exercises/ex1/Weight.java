import java.util.Scanner;

public class Weight 
{
    public static void main(String[] args) 
    {
        System.out.print("Enter weight in kilograms: ");
        Scanner input = new Scanner(System.in);
        double weightKilograms = input.nextDouble();
        input.close();
    }
}
