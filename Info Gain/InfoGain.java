import java.util.*;
import java.io.*;

class InfoGain {

	static void readData(BufferedReader br, ArrayList<ArrayList<String>> data) throws Exception {
		String[] col = br.readLine().split(" ");
		ArrayList<String> column = new ArrayList<>();

		for(String item : col) {
			column.add(item);
		}
		data.add(column);

		ArrayList<String> row;

		while(true) {
			try{
				col = br.readLine().split(" ");
			} catch(Exception e){ break;}
			
			row  = new ArrayList<>();
			for(String item : col) {
				row.add(item);
			}
			data.add(row);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("C://Users/it/Desktop/InfoGainData.txt"));

		ArrayList<ArrayList<String>> data = new ArrayList<>();

		readData(br, data);
		for(ArrayList<String> d : data)
		System.out.println(d);
	}
}