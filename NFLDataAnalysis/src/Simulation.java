import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data structure representing a full simulation of many seasons. 
 * Used to compute aggregate data regarding the simulated seasons. 
 * @author Davis Treybig
 *
 */
public class Simulation {
	//List of simulated seasons
	private List<Season> simulatedSeasons = new ArrayList<Season>();
	
	//Win distribution for each team. Specifically, maps team names to a list of their win
	//numbers for each simulated season
	private Map<String, List<Integer>> winDistribution = new HashMap<String, List<Integer>>();
	//Maps team names to their percent chance to get in the playoffs based on the
	//all the season simulations
	private Map<String, Double> playoffPercentage = new HashMap<String, Double>();
	
	public Simulation(List<Season> seasons){
		this.simulatedSeasons = seasons;
	}
	
	public void outputData(){
		for(Season s : simulatedSeasons){
			for(NFLTeam t: s.getTeams()){
				// Calculate win distributions
				if(winDistribution.containsKey(t.getTeamName())){
					winDistribution.get(t.getTeamName()).add(t.getWins());
				}
				else{
					List<Integer> wins = new ArrayList<Integer>();
					wins.add(t.getWins());
					winDistribution.put(t.getTeamName(), wins);
				}
				
				// Calculate playoff percentages
				if(s.isInPlayoffs(t.getTeamName())){
					if(playoffPercentage.containsKey(t.getTeamName())){
						playoffPercentage.put(t.getTeamName(), playoffPercentage.get(t.getTeamName()) + 1.0/simulatedSeasons.size());
					}
					else{
						playoffPercentage.put(t.getTeamName(), 1.0/simulatedSeasons.size());
					}
				}
				
			}
		}
		
		writeCSV();
	}
	
	private void writeCSV(){
		FileWriter writer;
		try {
			writer = new FileWriter("testOutput.csv");
			writer.append("Team Name,");
			writer.append("Playoff Percentage\n");
			for(String s: winDistribution.keySet()){
				writer.append(s);
				writer.append(",");
				if(!playoffPercentage.containsKey(s)){
					writer.append("0,");
				}
				else{
					writer.append(String.valueOf(playoffPercentage.get(s))+",");
				}		
				for(Integer i : winDistribution.get(s)){
					writer.append(i+",");
				}
				writer.append('\n');
			}
			writer.flush();
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
}
