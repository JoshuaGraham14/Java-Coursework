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

        double eccentricity = Math.sqrt(1-(Math.pow(polarRadius, 2)/Math.pow(equatorialRadius, 2)));
        double volume = (4*Math.PI*Math.pow(equatorialRadius, 2)*polarRadius)/3;

        System.out.printf("Eccentricity = %.3f\n", eccentricity);
        System.out.printf("Volume = %g cubic km", volume);
    }
}