package Program;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import Repository.*;

public class Main {

	public static void main(String[] args) {
		int Condition = -1, subState = -1;
		Scanner NewScan = new Scanner(System.in);
		String userPath = System.getProperty("user.dir").replace("\\", "\\\\").concat("\\\\src\\\\user.csv");
		String teamPath = System.getProperty("user.dir").replace("\\", "\\\\").concat("\\\\src\\\\teams.csv");
		
		while(Condition != 3) {
			System.out.printf("Database Hackathon 2023\n======================\n1. Insert Data\n2. Show\n3. Exit\nChoose: ");
			Condition = NewScan.nextInt();
			NewScan.nextLine();
			
			if(Condition == 1) {
				System.out.printf("Which table to insert? 1. User 2. Team\nChoose: ");
				subState = NewScan.nextInt();
				NewScan.nextLine();
				
				if(subState == 1) {
					Connection csvManager = new Connection(new File(userPath));
					userRepository userInserter = new userRepository();
					String[] User = new String[3];
					
					System.out.printf("add name: ");
					User[0] = NewScan.nextLine();
					System.out.printf("add nim: ");
					User[1] = NewScan.nextLine();
					System.out.printf("add team: ");
					User[2] = NewScan.nextLine();
					userInserter.Insert(User, csvManager);
				}
				else if(subState == 2) {
					Connection csvManager = new Connection(new File(teamPath));
					teamRepository teamInserter = new teamRepository();
					String[] Team = new String[1];
					
					System.out.printf("add team: ");
					Team[0] = NewScan.nextLine();
					teamInserter.Insert(Team, csvManager);
				}
			}
			else if(Condition == 2) {
				System.out.println("Which table to show? 1. User, 2. Team\nChoose: ");
				subState = NewScan.nextInt();
				NewScan.nextLine();
				System.out.println("Want to filter by condition? 1. yes, 2. no\nChoose: ");
				int subsubState = NewScan.nextInt();
				NewScan.nextLine();
				
				if(subState == 1) {
					if(subsubState == 1) {
						System.out.println("Add Condition Separate by semicolon [column;=;condition (column = NIM or Name or ID Team)]: ");
						String colState = NewScan.nextLine();
						String []Packager = colState.split(";");
						System.out.println("Would you like join table with User[y/n (Case Sensitive)]: ");
						String joinState = NewScan.nextLine();
						
						userRepository UserShowConditional = new userRepository();
						Connection csvManager = new Connection(new File(userPath));
						
						if(joinState.equals("y")) {
							UserShowConditional.Find(Packager[0], Packager, true, "Team", csvManager);
							System.out.println("\n");
						}
						else if(joinState.equals("n")) {
							UserShowConditional.Find(Packager[0], Packager, false, null, csvManager);
							System.out.println("\n");
						}
					}
					else {
						String joinState = "";
						System.out.println("Would you like join table with User[y/n (Case Sensitive)]: ");
						joinState = NewScan.nextLine();
						userRepository UserShowUnconditional = new userRepository();
						Connection csvManager = new Connection(new File(userPath));
						
						if(joinState.equals("y")) {
							UserShowUnconditional.Find(null, null, true, "Team", csvManager);
							System.out.println("\n");
						}
						else if(joinState.equals("n")) {
							UserShowUnconditional.Find(null, null, false, null, csvManager);
							System.out.println("\n");
						}
					}
				}
				else if(subState == 2) {
					if(subsubState == 1) {
						System.out.println("Add Condition Separate by semicolon [column;=;condition (column = id or Nama)]: ");
						String colState = NewScan.nextLine();
						String []Packager = colState.split(";");
						System.out.println("Would you like join table with User[y/n (Case Sensitive)]: ");
						String joinState = NewScan.nextLine();
						
						teamRepository TeamShowConditional = new teamRepository();
						Connection csvManager = new Connection(new File(teamPath));
						
						if(joinState.equals("y")) {
							TeamShowConditional.Find(Packager[0], Packager, true, "User", csvManager);
							System.out.println("\n");
						}
						else if(joinState.equals("n")) {
							TeamShowConditional.Find(Packager[0], Packager, false, null, csvManager);
							System.out.println("\n");
						}
					}
					else {
						String joinState = "";
						System.out.println("Would you like join table with User[y/n (Case Sensitive)]: ");
						joinState = NewScan.nextLine();
						teamRepository TeamShowUnconditional = new teamRepository();
						Connection csvManager = new Connection(new File(teamPath));
						
						if(joinState.equals("y")) {
							TeamShowUnconditional.Find(null, null, true, "User", csvManager);
							System.out.println("\n");
						}
						else if(joinState.equals("n")) {
							TeamShowUnconditional.Find(null, null, false, null, csvManager);
							System.out.println("\n");
						}
					}
				}
				
			}
		}
		NewScan.close();
	}
}
