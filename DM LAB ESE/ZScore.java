import java.io.*;
import java.util.*;

class ZScore{
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

		//mean
		double sum = 0;
		for(i = 0; i < noOfPoints; i++) {
			sum += data[i];
		}

		double mean = sum / noOfPoints;

		double sqValue = 0;
        System.out.println("\n====Data====");
		for(i = 0; i < noOfPoints; i++) {
			sqValue += (data[i] - mean) * (data[i] - mean);
            System.out.print(data[i]+ "," );
		}

		double stdDev = Math.sqrt(sqValue / (noOfPoints - 1));

        System.out.printf("\nMean: %4.3f \nStandard Deviation: %4.3f", mean, stdDev);

		System.out.println("\n====Z-Score====");
		for(i = 0; i < noOfPoints; i++) {
			double z_score = (data[i] - mean) / stdDev;
			System.out.printf("%d ---> %4.3f\n", data[i], z_score);
		}
		br.close();
		
		
	}
}