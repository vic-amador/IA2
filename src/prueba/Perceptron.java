import java.util.ArrayList;
import java.util.Random;

public class Perceptron {
    double w1, w2, theta;
    double learnRate;

    double error = 0;
    int iteration = 0;

    double m, b;
    
    public Perceptron(){
        Random rand = new Random();
        theta = rand.nextDouble(); 
        w1 = 0;
        w2 = 0;
        learnRate = 0;
    }
    
    public void setW1(double var1){
        this.w1 = var1;
    }
    
    public void setW2(double var2){
        this.w2 = var2;
    }
    
    public void setLearnRate(double var){
        this.learnRate = var;
    }

    public float evaluate(DataPoint point) {
        double y = point.x * w1 + point.y * w2 - theta;
        return y >= 0 ? 1 : 0;
    }

    public void nextIteration(ArrayList<DataPoint> points) {
        this.error = 0;
        for (DataPoint point : points) {
            float y = evaluate(point);
            if (y != point.value) {
                double error = point.value - y;
                w1 += learnRate * error * point.x;
                w2 += learnRate * error * point.y;
                theta -= learnRate * error;
                this.error += Math.abs(error);
            }
        }
        m = -w1 / w2;
        b = theta / w2;
        iteration++;
    }

    public boolean hasLearned() {
        return error == 0.0 && iteration > 0;
    }

    public float getSlope(float x) {
        return (float)(m * x + b);
    }

    public double getTheta() { return theta; }
    public double getWeight1() { return w1; }
    public double getWeight2() { return w1; }

    public int getIterations() {
        return iteration;
    }
}
