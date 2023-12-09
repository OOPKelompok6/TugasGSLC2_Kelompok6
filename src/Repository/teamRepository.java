package Repository;


import java.io.File;
import java.util.ArrayList;
import Models.*;
import Program.*;

public class teamRepository implements Repository {
	private String userPath;
	
	public teamRepository() {
		userPath = "user.csv";
	}
	
	public String[] Insert(String[] insertArrStr, Connection csvScanner) {
		int curId = 0;
		
		while(csvScanner.readCSV() != null) {curId++;}
		String finalTeam = Integer.toString(curId).concat(",").concat(insertArrStr[0]).concat("\n");
		if(csvScanner.writeCSV(finalTeam)) {
			return insertArrStr;
		}
		return null;
	}
	
	public ArrayList<Model> Find(String col, String[] condition, boolean join, String table, Connection csvScanner) {
		if(Validate(col, condition, join, table, csvScanner)) {
			System.out.println("Error input conditions unmatched!");
			return null;
		}
		boolean isData = false;
		ArrayList <Model>Holder = new ArrayList();
		String curRow; String[] Packager = null;
		
		if(col != null) {
			if(join) {
				while(true) {
					curRow = csvScanner.readCSV();
					if(curRow == null) {
						break;
					}
					Packager = curRow.split(",");
					
					if((Packager[0].equals(condition[2])) || (Packager[1].equals(condition[2]))) {
						break;
					}
				}
				Connection csvManager = new Connection(new File(userPath));
				String []packagerJoin;
				
				System.out.println("NIM,Name,ID Team,Team Name");
				while(true) {
					curRow = csvManager.readCSV();
					if(curRow == null) {
						break;
					}
					
					packagerJoin = curRow.split(",");
					if(packagerJoin[2].equals(Packager[0])) {
						isData = true;
						curRow = curRow.concat(",").concat(Packager[1]);
						Holder.add((Model) new User(packagerJoin[0], packagerJoin[1], Integer.parseInt(packagerJoin[2])));
						System.out.println(curRow);
					}
				}
			}
			else {
				while(true) {
					curRow = csvScanner.readCSV();
					if(curRow == null) {
						break;
					}
					Packager = curRow.split(",");
					
					if((Packager[0].equals(condition[2]) || Packager[1].equals(condition[2])) == true) {
						System.out.println(curRow);
						
						isData = true;
						if(isData) {
							Holder.add((Model) new Team(Packager[1], Integer.parseInt(Packager[0])));
						}
					}
				}
			}
		}
		else {
			if(join) {
				ArrayList <Team>tempTeam = new ArrayList();
				while(true) {
					curRow = csvScanner.readCSV();
					if(curRow == null) {
						break;
					}
					Packager = curRow.split(",");
					
					if(isData) {
						tempTeam.add(new Team(Packager[1], Integer.parseInt(Packager[0])));
					}
					isData = true;
				}
				Connection csvManager = new Connection(new File(userPath));
	
				csvManager.readCSV();
				System.out.println("NIM,Name,ID Team,Team Name");
				while(true) {
					curRow = csvManager.readCSV();
					if(curRow == null) {
						break;
					}
					Packager = curRow.split(",");
					
					isData = true;
					if(isData) {
						Holder.add((Model) new User(Packager[0], Packager[1], Integer.parseInt(Packager[2])));
					}
					curRow = curRow.concat(",").concat(tempTeam.get(Integer.parseInt(Packager[2]) - 1).teamName);
					System.out.println(curRow);
				}
			}
			else {
				
				while(true) {
					curRow = csvScanner.readCSV();
					if(curRow == null) {
						break;
					}
					Packager = curRow.split(",");
					System.out.println(curRow);
					
					if(isData) {
						Holder.add((Model) new Team(Packager[1], Integer.parseInt(Packager[0])));
					}
					isData = true;
				}
			}
		}
		
		if(isData) {
			return Holder;
		}
		return null;
	}
	
	public Model FindOne(String col, String[] condition, boolean join, String table, Connection csvScanner) {
		if(Validate(col, condition, join, table, csvScanner)) {
			System.out.println("Error input conditions unmatched!");
			return null;
		}
		boolean isData = false;
		Model Holder = null;
		String curRow; String[] Packager = null;
		
		if(col != null) {
			if(join) {
				while(true) {
					curRow = csvScanner.readCSV();
					if(curRow == null) {
						break;
					}
					Packager = curRow.split(",");
					
					if((Packager[0].equals(condition[2])) || (Packager[1].equals(condition[2]))) {
						break;
					}
				}
				Connection csvManager = new Connection(new File(userPath));
				String []packagerJoin;
				
				System.out.println("NIM,Name,ID Team,Team Name");
				while(true) {
					curRow = csvManager.readCSV();
					if(curRow == null) {
						break;
					}
					
					packagerJoin = curRow.split(",");
					if(packagerJoin[2].equals(Packager[0])) {
						isData = true;
						curRow = curRow.concat(",").concat(Packager[1]);
						Holder = (Model) new User(packagerJoin[0], packagerJoin[1], Integer.parseInt(packagerJoin[2]));
						System.out.println(curRow);
					}
				}
			}
			else {
				while(true) {
					curRow = csvScanner.readCSV();
					if(curRow == null) {
						break;
					}
					Packager = curRow.split(",");
					
					if((Packager[0].equals(condition[2]) || Packager[1].equals(condition[2])) == true) {
						System.out.println(curRow);
						
						isData = true;
						if(isData) {
							Holder = (Model) new Team(Packager[1], Integer.parseInt(Packager[0]));
						}
					}
				}
			}
		}
		else {
			if(join) {
				ArrayList <Team>tempTeam = new ArrayList();
				while(true) {
					curRow = csvScanner.readCSV();
					if(curRow == null) {
						break;
					}
					Packager = curRow.split(",");
					
					if(isData) {
						tempTeam.add(new Team(Packager[1], Integer.parseInt(Packager[0])));
					}
					isData = true;
				}
				Connection csvManager = new Connection(new File(userPath));
	
				csvManager.readCSV();
				System.out.println("NIM,Name,ID Team,Team Name");
				while(true) {
					curRow = csvManager.readCSV();
					if(curRow == null) {
						break;
					}
					Packager = curRow.split(",");
					
					isData = true;
					if(isData) {
						Holder = (Model) new User(Packager[0], Packager[1], Integer.parseInt(Packager[2]));
					}
					curRow = curRow.concat(",").concat(tempTeam.get(Integer.parseInt(Packager[2]) - 1).teamName);
					System.out.println(curRow);
				}
			}
			else {
				
				while(true) {
					curRow = csvScanner.readCSV();
					if(curRow == null) {
						break;
					}
					Packager = curRow.split(",");
					System.out.println(curRow);
					
					if(isData) {
						Holder = (Model) new Team(Packager[1], Integer.parseInt(Packager[0]));
					}
					isData = true;
				}
			}
		}
		
		if(isData) {
			return Holder;
		}
		return null;
	}

	public boolean Validate(String col, String[] condition, boolean join, String table, Connection csvScanner) {
		if((col != null) && (condition == null)) {return true;}
		if((col == null) && (condition != null)) {return true;}
		if((join) && (table == null)) {return true;}
		if((!join) && (table != null)) {return true;}
		if(csvScanner == null) {return true;}
		if((col != null) && (((col.equals("id")) || (col.equals("Nama"))) == false)) {return true;}
		if((col != null) && (table != null) && ((col.length() == 0) || (table.length() == 0))) {return true;}
		if ((table != null) && (join) && (!(table.equals("User")))) {return true;}
		
		return false;
	}
}
