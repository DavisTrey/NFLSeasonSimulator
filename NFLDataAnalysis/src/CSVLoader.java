import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVLoader {
	public static final String winCSV = "Wins.csv";
	public static final String gameCSV = "Games.csv";
	
	
	private List<String[]> loadFile(String filename){
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		List<String[]> listOfTeams = new ArrayList<String[]>();
		
		try {
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				String[] team = line.split(cvsSplitBy);
				listOfTeams.add(team);	
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return listOfTeams;
	}
	
	
	public static void main(String[] args){
		CSVLoader loader = new CSVLoader();
		List<String[]> NFLWinCSV = loader.loadFile(winCSV);
		List<String[]> NFLGameCSV = loader.loadFile(gameCSV);
		DataReader dataReader = new DataReader(NFLWinCSV, NFLGameCSV);
	}
	
}
