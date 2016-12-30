package ThesisProject;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class read {
	
	 public String [] array=new String[100];
	 
	 public void readFile() throws IOException {

	            // FileReader reads text files in the default encoding.
		 	Scanner sc = null;
		 	  FileInputStream inputStream = null;
		 	  
		 	  
		 	  //String st=new String("(~"");
		 	  
		 	  
				inputStream = new FileInputStream("/opt/NSF_Data_Files/NewPatternsFile.txt");
	            sc = new Scanner(inputStream, "UTF-8");
	            for(int j=0;j<100;j++)
	            {	
	            	
	            	array[j] = sc.nextLine();
	                System.out.println(array[j]);
	            }   
	
	            // Always close files.
	            sc.close();         
	         
	    }
}

