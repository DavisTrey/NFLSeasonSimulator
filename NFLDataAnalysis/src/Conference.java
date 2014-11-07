import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public enum Conference {
	AFC(Arrays.asList(Division.AFCWest, Division.AFCNorth, Division.AFCSouth, Division.AFCWest)),
	NFC(Arrays.asList(Division.NFCWest, Division.NFCNorth, Division.NFCSouth, Division.NFCWest));
	
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
	
	public List<String> getTeams(){
		List<String> teams = new ArrayList<String>();
		for(Division d : divisions){
			teams.addAll(d.getTeams());
		}
		return teams;
	}

}