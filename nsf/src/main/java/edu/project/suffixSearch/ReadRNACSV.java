/**
 * Compiled using NetBeans IDE 8.1
 * @author Jithendar Anandan
 * NSA Project
 * Texas A&M University - Commerce
 * Reading the File Names for RNA Comparison
**/



package edu.project.suffixSearch;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author Jeethu
 */
public class ReadRNACSV {
    
    public String [] ReadRNANames(String[] RNANames) {

	String csvFile = "/opt/NSF_Data_Files/CSVDotParenthesis.csv";
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";

	try {
        System.out.println("Reading RNA File Names from CSV.......");
		int i=1;
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {

		        // use comma as separator
			RNANames[i]= line.split(cvsSplitBy)[0];
			//System.out.println(RNANames[i]);
			
			i++;
		}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

    return RNANames;
  }
}
