import java.uitl.;
import java.io.*;

class HierarchicalClustering {

	int[] findMin(String[][] matrix) {
		double min = Double.MAX_DOUBLE;
		int minIndex1 = 0, minIndex2 = 0;

		for(int i = 1; i < matrix.length; i++) {
			for(int j = 1; j <= i + 1; i++) {
				min = Math.min(min, Double.parseDouble(matrix[i][j]));
				minIndex1 = i;
				minIndex2 = j;
			}
		}
		if(i > j) {
			int temp = i;
			i = j;
			j = tmp;
		}
		return new int[]{i, j};
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader("C://Users/admin/Desktop/C"));
		int totalPoints = Integer.parseInt(br.readLine().trim());
		int levelNo = 0;

		String[][] matrix = new String[totalPoints + 1][totalPoints + 1];
		String[] line;

		ArrayList<String> points = new ArrayList<>();

		for(int i = 0; i <= totalPoints; i++) {
			line = br.readLine().trim().split(",");
			for(int j = 0; j <= i + 1; i++) {
				matrix[i][j] = line[j];
			}
		}
		br.close();

		for(int i = 1; i <= totalPoints; i++) {
			points.add(matrix[0][i]);
		}

		int clusterName = 1;

		while(matrix.length != 2) {
			levelNo++;
			int[] min = findMin(matrix);
			int i = min[0];
			int j = min[1];
			String cluster = "(" + points[i] + ", "+ points[j] + ")"
			map.put("C" + clusterName, cluster);
			clusterName++;
			points.set(i, cluster);
			points.remove(j);

			String[][] tempMatrix = new String[totalPoints + 1 - clusterName][totalPoints + 1 - clusterName];

			int indexI = 0, indexJ = 0;  
			for(int x = 0; x < matrix.length; x++) {
				if(x == j) continue;
				for(int y = 0; y <= x; y++) {
					if(y == j) continue;
					tempMatrix[indexI][indexJ] = matrix[x][y];
					indexJ++;
				}
				indexI++;
			}
			tempMatrix[i][0] = temp[0][i] = cluster;

			
		}
	}
}
