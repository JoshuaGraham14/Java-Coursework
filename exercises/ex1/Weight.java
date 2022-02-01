import java.util.Scanner;

public class Weight 
{
    public static void main(String[] args) 
    {
        System.out.print("Enter weight in kilograms: ");
        Scanner input = new Scanner(System.in);
        double weightKilograms = input.nextDouble();
        input.close();

        double weightOunces = weightKilograms * 35.274;
        int weightPounds = (int) Math.floor(weightOunces/16);
        weightOunces = weightOunces - 16*weightPounds;
        
        System.out.printf("Equivalent imperial weight is %d lb %.1f oz\n", weightPounds, weightOunces);
    }
}
