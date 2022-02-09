public class MeanValue 
{
    public static double meanValue(double[] data) 
    {
        return 0;
    }

    public static void main(String[] args) 
    {
        if(args.length == 0)
        {
            System.err.println("Usage: java MeanValue <values...>");
            System.exit(1);
        }
        else 
        {
            double[] doubleValues = new double[args.length];
            for (int i = 0; i < args.length; i++)
            {
                doubleValues[i] = Double.parseDouble(args[i]);
            }
            double mean = meanValue(doubleValues);
            System.out.printf("Mean value = %.3f\n", mean);
        }
    }
}
