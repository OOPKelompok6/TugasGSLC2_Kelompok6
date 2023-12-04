package Models;

public class User extends Model {
	public String nim, name;
	public User(String nim, String name, int teamID) {
		super(teamID);
		this.nim = nim;
		this.name = name;
	}
}
