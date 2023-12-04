package Models;

public class Team extends Model {
	public String teamName;
	public Team(String teamName, int teamID) {
		super(teamID);
		this.teamName = teamName;
	}
}
