import java.io.*;
import java.util.*;

class Normalization{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("NormalizationData.csv"));

		int noOfPoints = Integer.parseInt(br.readLine().trim().split(",")[0]);
		String[] array = br.readLine().trim().split(",");

		int[] data = new int[noOfPoints];

		int i = 0;
		for(String str : array) {
			data[i++] = Integer.parseInt(str);
		}
		Arrays.sort(data);

		int min = data[0], max = data[noOfPoints - 1];

		int newMin = 0, newMax = 0;
		br.close();
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("====Normalization====");

		System.out.print("\nMin value: ");
		newMin = Integer.parseInt(br.readLine().trim());

		System.out.print("\nMax value: ");
		newMax = Integer.parseInt(br.readLine().trim());

		double[] normData = new double[noOfPoints];

		for(i = 0; i < noOfPoints; i++) {
			double a = (1.0 * data[i] - min) / (max - min);
			double b = a * (newMax - newMin);
			normData[i] = a * b + newMin;
		}

		System.out.println("\n====Normalized Data====\n");
		for(i = 0; i < noOfPoints; i++) {
			System.out.printf("%d ---> %4.3f \n",data[i], normData[i]);
		}
	}
}