package Program;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//Bertindak sebagai input & output CSV
public class Connection {
	BufferedReader csvScanner;
	BufferedWriter csvWriter;
	File Database;
	
	public Connection(File Database) {
		try {
			this.Database = Database;
			csvScanner = new BufferedReader(new FileReader(Database));
		} catch (FileNotFoundException e) {
			System.out.println("File not Found!\n");
		}
	}
	
	public String readCSV() {
		String csvLine = null;
		try {
			if ((csvLine = csvScanner.readLine()) != null) {
			    return csvLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return csvLine;
	}
	
	public boolean writeCSV(String rowData) {
		try {
			csvWriter = new BufferedWriter(new FileWriter(this.Database, true));
			csvWriter.write(rowData);
			csvWriter.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void CloseAll() {
		try {
			csvScanner.close();
			csvWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
