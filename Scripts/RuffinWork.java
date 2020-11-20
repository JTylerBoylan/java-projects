package tyler.pack;

import java.text.DecimalFormat;

public class RuffinWork {

	static int[] x = {0,1,2,3,4,5,6,7};
	static int[] y = {19,39,52,57,68,41,27,17};
	//
	static DecimalFormat df = new DecimalFormat("#.####");
 	public static void main(String[] args) {
 		double mean = getMean(x, getPx(x,y));
 		System.out.println("x, y, P(x), xP(x), x - u, (x-u)^2, P(x)(x-u)^2");
		for(int i = 0; i < x.length; i++) {
			System.out.println(x[i] + ", " + y[i] + ", " + df.format(getPx(x,y)[i]) + ", " + df.format((getPx(x,y)[i]*x[i])) + ", " + df.format((x[i] - mean)) + ", " + df.format(Math.pow(x[i] - mean, 2)) + ", " + df.format((getPx(x,y)[i] * Math.pow(x[i]-mean, 2))));
		}
		double[] l6 = new double[x.length];
		for (int i = 0; i < x.length; i++) {
			l6[i] = getPx(x,y)[i] * Math.pow(x[i] - mean, 2);
		}
		System.out.println("Mean : " + df.format(getMean(x, getPx(x,y))));
		System.out.println("Variance: " + df.format(sumDouble(l6)));
		System.out.println("Standard Dev.: " + df.format(Math.sqrt(sumDouble(l6))));
		System.out.println("The average x per y was " + df.format(getMean(x,getPx(x,y))) + " with a standard deviation of " + df.format(Math.sqrt(sumDouble(l6))));
	}
	private static int sumInt(int[] nums) {
		int sum = 0;
		for (int i: nums)
			sum += i;
		return sum;
	}
	private static double sumDouble(double[] nums) {
		double sum = 0.0;
		for (double d: nums)
			sum += d;
		return sum;
	}
	private static double[] getPx(int[] x, int[] y) {
		double[] Px = new double[x.length];
		for (int i = 0; i < x.length; i++) {
			Px[i] = (double) y[i]/sumInt(y);
		}
		return Px;
	}
	private static double getMean(int[] x, double[] Px) {
		double mean = 0;
		for (int i = 0; i < x.length; i++)
			mean += x[i] * Px[i];
		return mean;
	}
}
