public class CircleDemo 
{
    public static void main(String[] args) 
    {
        Circle c = new Circle (4.5);
        System.out.printf("Radius    = %.1f\n", c.getRadius());
        System.out.printf("Perimeter = %.3f\n", c.perimeter());
        System.out.printf("Area      = %.3f\n", c.area());
    }
}
