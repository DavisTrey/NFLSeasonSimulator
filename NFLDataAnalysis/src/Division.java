import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Data structure used to represent a Division in the NFL. 
 * @author Davis Treybig
 *
 */
public enum Division {
	AFCEast (Arrays.asList("New England Patriots", "Buffalo Bills", "Miami Dolphins", "New York Jets")),
	AFCNorth (Arrays.asList("Pittsburgh Steelers", "Cleveland Browns", "Cincinnati Bengals", "Baltimore Ravens")),
	AFCSouth (Arrays.asList("Indianapolis Colts", "Houston Texans", "Tennessee Titans", "Jacksonville Jaguars")),
	AFCWest (Arrays.asList("Denver Broncos", "Kansas City Chiefs", "San Diego Chargers", "Oakland Raiders")),
	NFCEast (Arrays.asList("Philadelphia Eagles", "Dallas Cowboys", "New York Giants", "Washington Redskins")),
	NFCNorth (Arrays.asList("Detroit Lions", "Green Bay Packers", "Minnesota Vikings", "Chicago Bears")),
	NFCSouth (Arrays.asList("New Orleans Saints", "Carolina Panthers", "Atlanta Falcons", "Tampa Bay Buccaneers")),
	NFCWest (Arrays.asList("Arizona Cardinals", "Seattle Seahawks", "San Francisco 49ers", "St. Louis Rams"));
	
	private List<String> teams = new ArrayList<String>();
	public List<String> getTeams() {
		return teams;
	}
	public void setTeams(List<String> teams) {
		this.teams = teams;
	}
	private Division(List<String> teams){
		this.teams = teams;
	}
}
