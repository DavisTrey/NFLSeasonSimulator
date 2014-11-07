import java.util.ArrayList;
import java.util.List;


public class NFLTeam implements Comparable<NFLTeam>{
	private String teamName;
	//Array of wins/losses for existing games. Can be "Win", "Loss", "Tie", or "" for no game
	private List<String> record; 
	//Array of percent chances to win for the rest of the season's games. NOTE: 
	// if there is no game that week, the percentage is -1
	private List<Double> calculatedPercentages; 
	//Array of all opponents for the season
	private List<String> opponents;
	
	public NFLTeam(String teamName, List<String> record, List<Double> calculatedPercentages, List<String> opponents){
		this.teamName = teamName;
		this.record = record;
		this.calculatedPercentages = calculatedPercentages;	
		this.opponents = opponents;
	}
	
	public NFLTeam(NFLTeam team){
		this.teamName = new String(team.teamName);
		this.record = new ArrayList<String>();
		for(String s : team.record){
			this.record.add(new String(s));
		}
		this.calculatedPercentages = new ArrayList<Double>();
		for(Double d : team.calculatedPercentages){
			this.calculatedPercentages.add(new Double(d));
		}
		this.opponents = new ArrayList<String>(team.opponents);
		for(String s : team.opponents){
			this.opponents.add(new String(s));
		}
	}
	
	public int getWins(){
		return calculateFrequencyInRecord("Win");
	}
	
	public int getLosses(){
		return calculateFrequencyInRecord("Loss");
	}
	
	public int getTies(){
		return calculateFrequencyInRecord("Tie");
	}
	
	/**
	 * Calculates frequency of a string within the record, ie "Win"
	 */
	private int calculateFrequencyInRecord(String string){
		int frequency = 0;
		for(String s: record){
			if(s.equals(string)){
				frequency++;
			}
		}
		return frequency;
		
	}
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public List<String> getRecord() {
		return record;
	}

	public void setRecord(List<String> record) {
		this.record = record;
	}

	public List<Double> getCalculatedPercentages() {
		return calculatedPercentages;
	}

	public void setCalculatedPercentages(List<Double> calculatedPercentages) {
		this.calculatedPercentages = calculatedPercentages;
	}

	public List<String> getOpponents() {
		return opponents;
	}

	public void setOpponents(List<String> teamsToPlay) {
		this.opponents = teamsToPlay;
	}

	@Override
	public int compareTo(NFLTeam o) {
		// Sort by largest wins first
		if(this.getWins() > o.getWins()){
			return -1;
		}
		else if(this.getWins() < o.getWins()){
			return 1;
		}
		else{
			return 0;
		}
	}

}
