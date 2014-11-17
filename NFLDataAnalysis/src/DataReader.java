import java.util.ArrayList;
import java.util.List;

/**
 * Class used to parse data originally loaded from .csv files. In particular, 
 * the class takes in the array data of win percentages and games, and turns it 
 * into a list of NFLTeams. 
 * 
 * @author Davis Treybig
 *
 */
public class DataReader {
	//integer representing the total number of columns in the wins[] and games[] arrays
	public static final int totalColumnsPerSeason = 17;
	
	//Percentage chance to win for games which dont exist in a given week but are on the spreadsheet
	//(Used to not have blank space in the percentage section)
	public static final double noGamePercentage = -1;
	
	//List of all teams. These are crafted from the input .csv data
	private List<NFLTeam> teams = new ArrayList<NFLTeam>();
	
	//integer representing the current week of the season (specifically, representing
	// the position of the first game that has not been played in the wins[] arrays for each team)
	private int currentWeek; 
	
	public DataReader(List<String[]> wins, List<String[]> games){
		parseTeams(wins, games);
	}
	
	/**
	 * Parses all teams specified in the loaded CSV files into objects
	 */
	private void parseTeams(List<String[]> wins, List<String[]> games){
		this.currentWeek = findCurrentWeek(wins);
		for(int i=0; i<wins.size(); i++){
			parseIndividualTeam(wins.get(i), games, i);
		}
		SeasonSimulator simulator = new SeasonSimulator(teams);
		simulator.simulate();
	}
	
	/**
	 * Finds the current week of the NFL season. The integer returned is
	 * the position of the first week of games which have not been played.
	 */
	private int findCurrentWeek(List<String[]> wins){
		int earliestColumn = Integer.MAX_VALUE;
		for(String[] s: wins){
			//Don't include i=0 as it is just team names
			for(int i=1; i<s.length; i++){
				// Slightly hard coded. 
				if(s[i].contains(".")){
					if(i<earliestColumn){
						earliestColumn = i;
					}
				}
			}
		}
		System.out.println("Current Week: " + earliestColumn);
		return earliestColumn;
	}
	
	/**
	 * Parses an individual team's data from .csv data into an NFLTeam
	 * object. 
	 * @param wins CSV containing the team's existing wins and losses and future
	 * probabilities
	 * @param games CSV containing what team each game is against
	 * @param teamRow team currently being analyzed in the lists
	 */
	private void parseIndividualTeam(String[] wins, List<String[]> games, int teamRow){
		String teamName = wins[0];
		List<String> record = new ArrayList<String>();
		List<Double> percentages = new ArrayList<Double>();
		
		//Start at i=1 because first column is team names
		for(int i=1; i<wins.length; i++){
			//If less than current week, we are looking at the team's record
			if(i < currentWeek){
				record.add(wins[i]);			
			}
			else{
				Double percentWin;
				if(wins[i].equals("")){
					percentWin = noGamePercentage;
				}
				else{
					percentWin = Double.parseDouble(wins[i]);
				}
				percentages.add(percentWin);		
			}
		}
		List<String> teamsToPlay = loadTeamsToPlay(games, teamRow);
		NFLTeam team = new NFLTeam(teamName, record, percentages, teamsToPlay);
		teams.add(team);
	}
	
	/**
	 * Parses the games array for a given team
	 * @param games Array of games data from Games.csv
	 * @param teamrow Row of team currently being analyzed
	 * @return Returns a list of the names of the teams the current
	 * team will play each week for the season (for the FULL season, not the remainder)
	 */
	private List<String> loadTeamsToPlay(List<String[]> games, int teamrow){
		List<String> teamsToPlay = new ArrayList<String>();
		
		String[] teamRow = games.get(teamrow);
		for(int i=1; i<teamRow.length; i++){
			if(teamRow[i].equals("")){
				teamsToPlay.add("");
				continue;
			}
			
			int gameNumber = Integer.parseInt(teamRow[i]);
			for(int j=0; j<games.size(); j++){
				if(j != teamrow){
					if(!games.get(j)[i].equals("")){
						if(Integer.parseInt(games.get(j)[i]) == gameNumber){
							String teamPlayedThatWeek = games.get(j)[0];
							teamsToPlay.add(teamPlayedThatWeek);
						}
					}		
				}	
			}
		}
		
		return teamsToPlay;
	}
}
