package week3;

/**
 * Created by VGN on 6/17/16.
 */
public class LinearRegression {

    public static void main(String[] args){
        double theta0 = 0;
        double theta1 =  0.5;
        for (int x=0; x<=4; x++){
            double result = (x*theta1) + theta0;
            System.out.println(result);
        }
    }
}
