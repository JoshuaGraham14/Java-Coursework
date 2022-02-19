public class CircleDemo 
{
    public static void main(String[] args) 
    {
        Circle c = new Circle (10.124121);
        System.out.printf("Radius    = %.1f\n", c.getRadius());
        System.out.printf("Perimeter = %.3f\n", c.perimeter());
        System.out.printf("Area      = %.3f\n", c.area());

        Circle c2 = new Circle (10.124123);
        System.out.printf("Radius    = %.1f\n", c2.getRadius());
        System.out.printf("Perimeter = %.3f\n", c2.perimeter());
        System.out.printf("Area      = %.3f\n", c2.area());
    }
}
