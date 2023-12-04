package Repository;

import java.util.ArrayList;
import Models.Model;
import Program.Connection;

public interface Repository {
	public String[] Insert(String []insertArrStr, Connection csvScanner);
	public ArrayList<Model> Find(String col, String[] condition, boolean join, String table, Connection csvScanner);
	public Model FindOne(String col, String[] condition, boolean join, String table, Connection csvScanner);
}
