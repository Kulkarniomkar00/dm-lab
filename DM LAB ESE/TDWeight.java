import java.io.*;
import java.util.*;

class TDWeight{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("TDWeight.csv"));

		String[] column = br.readLine().trim().split(",");

        String[] str = br.readLine().trim().split(",");
        ArrayList<ArrayList<String>> data= new ArrayList<>();

        while(str != null) {
            ArrayList<String> temp = new ArrayList<>();
            for(String s: str) {
                temp.add(s);
            }
            data.add(temp);
            try{
                str = br.readLine().trim().split(",");
            }catch(NullPointerException e){break;}
        }
        int i = 0, j = 0;
        int[][] values = new int[data.size() + 1][data.get(0).size() + 1];
        for(ArrayList<String> temp: data) {
            int sum = 0;
            int k;
            
            for(k = 1; k < temp.size(); k++) {
                values[i][j] = Integer.parseInt(temp.get(k));
                sum += values[i][j];
                j++;
            }
            values[i][j] = sum;
            i++;
            j = 0;
        }

        for(i = 0; i < values.length - 1; i++) {
            int sum = 0;
            for(j = 0; j < values.length - 1; j++) {
                sum += values[j][i];
            }
            values[j][i] = sum;
        }

        int rowSum = 0, colSum = 0;
        for(i = 0; i < values.length - 1; i++) {
            rowSum += values[values.length - 1][i];
        }

        for(i = 0; i < values.length - 1; i++) {
            colSum += values[i][values.length - 1];
        }

        //total sum
        values[values.length - 1][values.length - 1] = colSum + rowSum;

        System.out.println("====Data====");

        for(i = 0; i < values.length; i++) {
            for(j = 0; j < values.length; j++) {
                System.out.print(values[i][j]+ " ");
            }
            System.out.println();
        }

        System.out.println();
        //T - D Weight

        for(i = 1; i < column.length; i++) {
            for(j = 0; j < data.size(); j++){
                double TW = (100.0 * values[i - 1][j]) / values[i - 1][data.get(0).size() - 1];
                System.out.print("T Weight of " + column[i] + " " + data.get(j).get(0) + ": ");
                System.out.printf("%4.3f\n", TW);

                double DW = (100.0 * values[i - 1][j]) / values[values.length - 1][j];
                System.out.print("D Weight of " + column[i] + " " + data.get(j).get(0) + ": ");
                System.out.printf("%4.3f\n\n", DW);

                System.out.println();
            }
        }
        br.close();
	}
}