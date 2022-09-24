import java.util.*;
import java.io.*;

class PlayGame {
	int day;
	String outLook, temp, wind;
	boolean play;
	PlayGame(String day, String outLook, String temp, String wind, String play) {
		this.day = Integer.parseInt(day.substring(1));
		this.outLook = outLook;
		this.temp = temp;
		this.wind = wind;
		if(play.equalsIgnoreCase("YES"))
			this.play = true;
	}
	public String toString() {
		return day + " " + outLook ;
	}
}

class InfoGain {

	static void readData(BufferedReader br, ArrayList<PlayGame> data) throws Exception {
		String[] col = br.readLine().split(" ");

		while(true) {
			try{
				col = br.readLine().split(" ");
				PlayGame temp = new PlayGame(col[0], col[1], col[2], col[3], col[4]);
				data.add(temp);
			} catch(Exception e){break;}
			
			
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader("C://Users/it/Desktop/InfoGain/InfoGainData.txt"));
		}
		catch(Exception e) {
			System.out.println("File Not Found");
			return;
		}

		ArrayList<PlayGame> data = new ArrayList<>();

		readData(br, data);
		for(PlayGame d : data)
			System.out.println(d.toString());

		int posR = 0, negR = 0;
		HashMap<String, Integer> arr = new HashMap<>();

		for(int i = 0; i < data.size(); i++){
		    if(data.get(i).play)
		    {   
		        posR++;
		    }else if(!data.get(i).play){
		        negR++;
		    }

		    if(!(arr.containsKey(data.get(i).wind))){
		        arr.put(data.get(i).wind, 0);
		    }
		    arr.put(data.get(i).wind, arr.get(data.get(i).wind) + 1);
		}

		HashMap<String, double[]> arr2 = new HashMap<>();

		for(int i = 0; i < data.size() ;i++){

		    if(!(arr2.containsKey(data.get(i).wind))){
		        arr2.put(data.get(i).wind, new double[3]);
		    }

		    boolean res = data.get(i).play;
		    if(res){
		        arr2.get(data.get(i).wind)[0]++;
		    }
		    else
		        arr2.get(data.get(i).wind)[1]++;
		}

		int totRecord = data.size();
		double entropy = -((1.0 * posR / totRecord) * (Math.log(1.0 * posR / totRecord) / Math.log(2))) - 
						  ((1.0 * negR / totRecord) * (Math.log(1.0 * negR / totRecord) / Math.log(2)));


		for(Map.Entry<String, double[]> entry : arr2.entrySet()){
		    double[] array = entry.getValue();

		    double posRes = array[0], negRes = array[1];
		    double totRes = posRes + negRes;

		    double ent = -(((posRes / totRes) * (Math.log(posRes / totRes) / Math.log(2) )) + 
		    			  ((negRes / totRes) * (Math.log(negRes / totRes) / Math.log(2))));
		    System.out.println(ent);
		    array[2] = ent;
		}


		var ent_sum = 0;

		for(Map.Entry<String, double[]> entry : arr2.entrySet()){
			double[] array = entry.getValue();
		    
		    double rec = array[0] + array[1];
		    System.out.println(" " + ent_sum + array[0] + array[1]+rec);
		    ent_sum += (rec / totRecord) * (array[2]);
		    
		}

		double infogain = entropy - ent_sum;
		System.out.println("Info Gain(S, Wind) : " + infogain);
	}
}