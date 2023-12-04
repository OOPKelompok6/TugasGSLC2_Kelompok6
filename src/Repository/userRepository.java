package Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import Models.*;
import Program.Connection;

public class userRepository implements Repository {
	private String teamPath;
	
	public userRepository() {
		teamPath = System.getProperty("user.dir").replace("\\", "\\\\").concat("\\\\Database\\\\teams.csv");
	}
	
	public String[] Insert(String[] insertArrStr, Connection csvScanner) {
		Connection csvManager = new Connection(new File(teamPath));
		int memberCounter = 0, curTeamID = -1;
		String curRow; String[] splitRow = null;
		
		while(true) {
			curRow = csvManager.readCSV();
			if(curRow == null) {
				break;
			}
			splitRow = curRow.split(",");
			if(splitRow[1].equals(insertArrStr[2])) {
				curTeamID = Integer.parseInt(splitRow[0]);
				break;
			}
		}
		
		if(curTeamID == -1) {
			curTeamID = Integer.parseInt(splitRow[0]) + 1;
			curRow = Integer.toString(Integer.parseInt(splitRow[0]) + 1).concat(",").concat(insertArrStr[2]).concat("\n");
			csvManager.writeCSV(curRow);
		}
		
		csvScanner.readCSV();
		while(true) {
			curRow = csvScanner.readCSV();
			if(curRow == null) {
				break;
			}
			splitRow = curRow.split(",");
			
			if(Integer.parseInt(splitRow[2]) == curTeamID) {
				memberCounter++;
			}
			
		}
		
		if(memberCounter == 3) {
			System.out.println("Error! team full");
		}
		else {
			curRow = insertArrStr[0].concat(",").concat(insertArrStr[1]).concat(",").concat(Integer.toString(curTeamID)).concat("\n");
			csvScanner.writeCSV(curRow);
			insertArrStr[2] = Integer.toString(curTeamID);
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
				Connection csvManager = new Connection(new File(teamPath));
				ArrayList <Team>tempTeam = new ArrayList();
				while(true) {
					curRow = csvManager.readCSV();
					if(curRow == null) {
						break;
					}
					Packager = curRow.split(",");
					
					if(isData) {
						tempTeam.add(new Team(Packager[1], Integer.parseInt(Packager[0])));
					}
					isData = true;
				}
				
				System.out.println("NIM,Name,ID Team,Team Name");
				while(true) {
					curRow = csvScanner.readCSV();
					if(curRow == null) {
						break;
					}
					Packager = curRow.split(",");
					
					if((Packager[0].equals(condition[2])) || (Packager[1].equals(condition[2])) || (Packager[2].equals(condition[2]))) {
						isData = true;
						curRow = curRow.concat(",").concat(tempTeam.get(Integer.parseInt(Packager[2]) - 1).teamName);
						Holder.add((Model) new User(Packager[0], Packager[1], Integer.parseInt(Packager[2])));
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
					
					if((condition[2].equals(Packager[0])) || (condition[2].equals(Packager[1])) || (condition[2].equals(Packager[2]))) {
						System.out.println(curRow);
						
						isData = true;
						if(isData) {
							Holder.add((Model) new User(Packager[0], Packager[1], Integer.parseInt(Packager[2])));
						}
					}
				}
			}
		}
		else {
			if(join) {
				Connection csvManager = new Connection(new File(teamPath));
				ArrayList <Team>tempTeam = new ArrayList();
				
				while(true) {
					curRow = csvManager.readCSV();
					if(curRow == null) {
						break;
					}
					Packager = curRow.split(",");
					
					if(isData) {
						tempTeam.add(new Team(Packager[1], Integer.parseInt(Packager[0])));
					}
					isData = true;
				}
				
				csvScanner.readCSV();
				System.out.println("NIM,Name,ID Team,Team Name");
				while(true) {
					curRow = csvScanner.readCSV();
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
						Holder.add((Model) new User(Packager[0], Packager[1], Integer.parseInt(Packager[2])));
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
		/*if(Validate(col, condition, join, table, csvScanner)) {
			System.out.println("Error input conditions unmatched!");
			return null;
		}*/
		boolean isData = false;
		Model Holder = null;
		String curRow; String[] Packager = null;
		
		if(col != null) {
			if(join) {
				Connection csvManager = new Connection(new File(teamPath));
				ArrayList <Team>tempTeam = new ArrayList();
				while(true) {
					curRow = csvManager.readCSV();
					if(curRow == null) {
						break;
					}
					Packager = curRow.split(",");
					
					if(isData) {
						tempTeam.add(new Team(Packager[1], Integer.parseInt(Packager[0])));
					}
					isData = true;
				}
				
				System.out.println("NIM,Name,ID Team,Team Name");
				while(true) {
					curRow = csvScanner.readCSV();
					if(curRow == null) {
						break;
					}
					Packager = curRow.split(",");
					
					if((Packager[0].equals(condition[2])) || (Packager[1].equals(condition[2])) || (Packager[2].equals(condition[2]))) {
						isData = true;
						curRow = curRow.concat(",").concat(tempTeam.get(Integer.parseInt(Packager[2]) - 1).teamName);
						Holder = (Model) new User(Packager[0], Packager[1], Integer.parseInt(Packager[2]));
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
					
					if((condition[2].equals(Packager[0])) || (condition[2].equals(Packager[1])) || (condition[2].equals(Packager[2]))) {
						System.out.println(curRow);
						
						isData = true;
						if(isData) {
							Holder = (Model) new User(Packager[0], Packager[1], Integer.parseInt(Packager[2]));
						}
					}
				}
			}
		}
		else {
			if(join) {
				Connection csvManager = new Connection(new File(teamPath));
				ArrayList <Team>tempTeam = new ArrayList();
				
				while(true) {
					curRow = csvManager.readCSV();
					if(curRow == null) {
						break;
					}
					Packager = curRow.split(",");
					
					if(isData) {
						tempTeam.add(new Team(Packager[1], Integer.parseInt(Packager[0])));
					}
					isData = true;
				}
				
				csvScanner.readCSV();
				System.out.println("NIM,Name,ID Team,Team Name");
				while(true) {
					curRow = csvScanner.readCSV();
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
						Holder = (Model) new User(Packager[0], Packager[1], Integer.parseInt(Packager[2]));
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
		if((col != null) && (((col.equals("NIM")) || (col.equals("Name")) || (col.equals("ID Team"))) == false)) {return true;}
		if((col != null) && (table != null) && ((col.length() == 0) || (table.length() == 0))) {return true;}
		if ((table != null) && (join) && (!(table.equals("Team")))) {return true;}
		
		return false;
	}
}
