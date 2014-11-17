import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Loader for the CSV data for the NFL thus far in the current season. 
 * In particular, loads a "Wins.csv" file which contains wins/losses
 * which have already ocurred as well as computed probabilities for
 * future games. Also loads a "Games.csv" file containing information
 * regarding who plays who. See the sample .csvs contained in the project
 * for the format of these files. 
 * 
 * @author Davis Treybig
 *
 */
public class CSVLoader {
	public static final String winCSV = "Wins.csv";
	public static final String gameCSV = "Games.csv";
	
	/**
	 * Loads a .csv file and returns a list of the array
	 * data of the .csv. Each array is row of the .csv file
	 * @param filename Name of the file to be loaded
	 */
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
