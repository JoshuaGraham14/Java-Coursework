import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

class Dataset
{
    ArrayList<Double> data = new ArrayList<Double>();

    public Dataset(String filename) throws IOException
    {
        try(Scanner input = new Scanner(Paths.get(filename)))
        {
            while (input.hasNextLine())
            {
                String line = input.nextLine();
                data.add(Double.parseDouble(line));
            }
        }
    }

    public Integer size()
    {
        return data.size();
    }

    public Double meanValue()
    {
        Double t = 0.0;
        for(int i=0; i<size(); i++)
        {
            t+=i;
        }
        
        return t/size();
    }

}