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

        if(equatorialRadius <= 0)
        {
            System.out.println("Error: equatorial radius must be larger than 0");
            System.exit(1);
        }
        if(polarRadius <= 0)
        {
            System.out.println("Error: polar radius must be larger than 0");
            System.exit(1);
        }
        if(polarRadius > equatorialRadius)
        {
            System.out.println("Error: equatorial radius must be larger than polar radius");
            System.exit(1);
        }
        double eccentricity = Math.sqrt(1-(Math.pow(polarRadius, 2)/Math.pow(equatorialRadius, 2)));
        double volume = (4*Math.PI*Math.pow(equatorialRadius, 2)*polarRadius)/3;

        System.out.printf("Eccentricity = %.3f\n", eccentricity);
        System.out.printf("Volume = %g cubic km\n", volume);
    }
}