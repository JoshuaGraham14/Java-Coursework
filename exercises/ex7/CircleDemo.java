public class CircleDemo 
{
    public static void main(String[] args) 
    {
        Circle c = new Circle ();
        System.out.printf("Radius    = %.1f\n", c.getRadius());
        System.out.printf("Perimeter = %.3f\n", c.perimeter());
        System.out.printf("Area      = %.3f\n", c.area());

        System.out.println(c);
    }
}
