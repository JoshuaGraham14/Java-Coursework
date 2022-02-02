import java.util.Scanner;

public class Spheroid
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter equatorial radius in km: ");
        double equatorialRadius = input.nextDouble();
        System.out.print("Enter polar radius in km: ");
        double polarRadius = input.nextDouble();
        input.close();

    }
}