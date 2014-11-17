import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Data structure representing a full simulated NFL season. Contains data on the wins and losses
 * for all teams, and from that data determines playoff placements. 
 * @author Davis Treybig
 *
 */
public class Season {
	
	//List of teams for the current simulated season. Since this list of teams
	//assumes a simulated season, all teams have wins/losses assigned for the full
	//season based on probabilities. 
	private List<NFLTeam> teams = new ArrayList<NFLTeam>();
	
	//List of teams which made the playoffs through division placement
	private List<NFLTeam> divisionPlayoffPlacement = new ArrayList<NFLTeam>();
	
	//List of teams which made the playoffs through conference placement
	private List<NFLTeam> conferencePlayoffPlacement = new ArrayList<NFLTeam>();


	public Season(List<NFLTeam> teamsInSeason){
		this.teams = teamsInSeason;
		Collections.sort(teams);
		findPlayoffSeats();	//This can only be called after Collections.sort(teams)
	}
	
	/**
	 * Returns true if the specified team made the playoffs. Returns
	 * false otherwise
	 */
	public boolean isInPlayoffs(String teamName){
		for(NFLTeam t : divisionPlayoffPlacement){
			if(t.getTeamName().equals(teamName)){
				return true;
			}
		}
		for(NFLTeam t : conferencePlayoffPlacement){
			if(t.getTeamName().equals(teamName)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Computes which teams made the playoffs. Adds these teams
	 * to the class variable playoff lists. 
	 */
	private void findPlayoffSeats(){
		Conference NFC = Conference.NFC;
		Conference AFC = Conference.AFC;
		for(Division d: NFC.getDivisions()){
			divisionPlayoffPlacement.add(findDivisionWinner(d));
		}
		for(Division d: AFC.getDivisions()){
			divisionPlayoffPlacement.add(findDivisionWinner(d));
		}
		
		
		//Adding conference winners. Note: this works because
		//the list of teams is sorted by wins from largest to
		//smallest
		int NFCTeams = 0;
		int AFCTeams = 0;
		for(NFLTeam t : teams){
			if(NFCTeams < 2 && NFC.getTeams().contains(t.getTeamName())){
				if(!divisionPlayoffPlacement.contains(t)){
					NFCTeams++;
					conferencePlayoffPlacement.add(t);
				}
			}
			else if(AFCTeams < 2 && AFC.getTeams().contains(t.getTeamName())){
				if(!divisionPlayoffPlacement.contains(t)){
					AFCTeams++;
					conferencePlayoffPlacement.add(t);
				}
			}
		}
	}
	

	
	/**
	 * Finds the team in the division with the highest wins
	 * 
	 * TODO: TIEBREAKERS****
	 */
	private NFLTeam findDivisionWinner(Division d){
		List<String> teamsInDivision = d.getTeams();
		int currentWins = 0;
		NFLTeam currentTeam = null;
		for(String s : teamsInDivision){
			for(NFLTeam team : teams){
				if(team.getTeamName().equals(s)){
					if(team.getWins() > currentWins){
						currentTeam = team;
						currentWins = team.getWins();
					}
				}
			}
		}
		
		return currentTeam;
	}
	
	
	public List<NFLTeam> getDivisionPlayoffPlacement() {
		return divisionPlayoffPlacement;
	}


	public List<NFLTeam> getConferencePlayoffPlacement() {
		return conferencePlayoffPlacement;
	}

	public List<NFLTeam> getTeams() {
		return teams;
	}

	public void setTeams(List<NFLTeam> teams) {
		this.teams = teams;
	}

}
