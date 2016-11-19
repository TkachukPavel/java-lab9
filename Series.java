import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by tkachukp on 17.11.16.
 */
public abstract class Series {

    private double base;
    private double increment;
    private final static int n = 20;


    public double getBase() {
        return base;
    }

    public double getIncrement() {
        return increment;
    }

    public Series(double base, double increment) {
        this.base = base;
        this.increment = increment;
    }

    public abstract double nthElement(int n);

    public double sum(){
        double res = 0;
        for (int i = 1; i <= n; i++) {
            res += nthElement(i);
        }
        return res;
    }

    public String toString (){
        ArrayList<Double> res = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            res.add(nthElement(i));
        return res.toString();
    }

    public void saveToFile(String fileName) throws IOException{
            FileWriter fileWriter = new FileWriter(fileName, false);
            String buf = this.getClass().getName() + '\n'
                         + "Base: " + base + '\n'
                         + "Increment: " + increment + '\n'
                         + "Elements: " + toString();
            fileWriter.write(buf);
            fileWriter.close();
    }
}

class Liner extends Series{
    public Liner(double base, double increment) {
        super(base, increment);
    }

    @Override
    public double nthElement(int n) {
        return super.getBase() + (super.getIncrement() * (n - 1));
    }
}

class Exponential extends Series{

    public Exponential(double base, double increment) {
        super(base, increment);
    }

    @Override
    public double nthElement(int n) {
        return super.getBase() * Math.pow(super.getIncrement(), n - 1);
    }
}
