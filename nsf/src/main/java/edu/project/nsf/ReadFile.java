package edu.project.nsf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//import org.apache.logging.log4j.*;

public class ReadFile {
	//final static Logger Log = LogManager.getLogger(HomeController.class.getName());
	public String read(String name) {

		// The name of the file to open.
		String fileName = "/opt/NSF_Data_Files/" + name;

		// This will reference one line at a time
		String line = null;
		String text = "";

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {

				text += line + "<br/>";
				// System.out.println(line);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();

		}
		return text;
	}
}