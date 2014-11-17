import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Data structure for a conference in the NFL. Represented as an enum type
 * as every team is either in the NFC or AFC
 * @author Davis Treybig
 *
 */
public enum Conference {
	AFC(Arrays.asList(Division.AFCWest, Division.AFCNorth, Division.AFCSouth, Division.AFCEast)),
	NFC(Arrays.asList(Division.NFCWest, Division.NFCNorth, Division.NFCSouth, Division.NFCEast));
	
	private List<Division> divisions = new ArrayList<Division>();
	
	private Conference(List<Division> divisions){
		this.divisions = divisions;
	}
	
	public List<Division> getDivisions() {
		return divisions;
	}
	
	public void setDivisions(List<Division> divisions) {
		this.divisions = divisions;
	}
	
	/**
	 * Returns all teams in the conference
	 */
	public List<String> getTeams(){
		List<String> teams = new ArrayList<String>();
		for(Division d : divisions){
			teams.addAll(d.getTeams());
		}
		return teams;
	}

}
