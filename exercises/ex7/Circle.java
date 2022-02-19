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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Circle other = (Circle) obj;
        return Math.abs(other.getRadius()-radius) < 0.00005;
    }
}