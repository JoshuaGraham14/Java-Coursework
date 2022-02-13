public class Circle
{
    private double radius;

    public Circle (double r)
    {
        radius = r;
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
}