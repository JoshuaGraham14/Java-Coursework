public class Circle
{
    private double radius;

    public Circle (double r)
    {
        if (r <= 0)
        {
           throw new IllegalArgumentException("Invalid radius");
        }
        radius = r;
    }

    public Circle ()
    {
        radius = 1.0;
    }

    public Double getRadius ()
    {
        return radius; 
    }

    public Double area ()
    {
        return (radius*radius)*Math.PI;
    }

    public Double perimeter ()
    {
        return 2*radius*Math.PI;
    }

    @Override
    public String toString()
    {
        return String.format("Circle(radius=%.4f)", radius);
    }
}