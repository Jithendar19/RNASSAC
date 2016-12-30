/**
 * Compiled using NetBeans IDE 8.0.2
 * @author Eric Fry
 * NSA Project
 * Texas A&M University - Commerce
 * Commerce, TX
**/
package edu.project.suffixSearch;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class WriteAppendedFile {
    
    private Scanner fileReader;
    private String newFile,
            appendFileName,
            line, 
            eofMarker = "*EOF" + "\n",
            commentsString, 
            linesString;
                    
    private ArrayList<String> lines = new ArrayList<String>(), 
            comments = new ArrayList<String>();
    
    //Constructor Function
    public WriteAppendedFile(String newFile, int Nucleotide){
        this.newFile = newFile;
        OpenSubmittedFile();
        ReadNewlySubmittedFile();
        AppendFile(Nucleotide);
        CloseInputFile();
    }
    
    //Open file that was just converted to new format
    private void OpenSubmittedFile() {
        try {
            fileReader = new Scanner (new FileReader(newFile));
        }
        catch(Exception e) {
            System.out.println("The input file \"" + newFile + "\" was "
                    + "not found");
            System.out.println(e.toString());
        }
    } 
    
    //Store and process new format file.
    private void ReadNewlySubmittedFile(){
         while(fileReader.hasNextLine()) {
            line = fileReader.nextLine();
            
            //Stores lines of comments into an ArrayList of strings
            if (line.startsWith("#")) {
                comments.add(line + "\n");
            }
            //Stores end of file marker into a string
            else if (line.startsWith("*")) {
                eofMarker = line + "\n";
            } 
            //Stores lines of RNA into an ArrayList of strings
            else {
                lines.add(line + "\n");
            } 
        }
       
        //Convert comments ArrayList to a single string.
        StringBuilder builder = new StringBuilder();
        for (String value : comments) {
            builder.append(value);
        }
        commentsString = builder.toString();
        
        //Convert lines of RNA ArrayList to a single string.
        StringBuilder builder2 = new StringBuilder();
        for (String value : lines) {
            builder2.append(value);
        }
        linesString = builder2.toString();  
    }
    
    //Appends Master file named: "NewFormatConcatenated.txt"
    private void AppendFile(int Nucleotide){
        BufferedWriter bw = null;
 
        try {
            // APPEND MODE SET HERE
            if (Nucleotide == 1)
                appendFileName = "NewFormatConcatenatedNucleotide.txt";
            else
                appendFileName = "NewFormatConcatenatedStructure.txt";
            bw = new BufferedWriter(new FileWriter(appendFileName, true));
            bw.write(commentsString);
            bw.write(linesString + eofMarker);
            bw.flush();
        } catch (IOException ioe) {
        } 

        
//        try (PrintWriter writeToFile = new PrintWriter(new File(appendNameAndPath))) {
//            writeToFile.print(commentsString);
//            writeToFile.print(linesString + eofMarker);
//            System.out.println("The newly converted file has been named: " +
//                appendNameAndPath);            
//        } 
//        catch (FileNotFoundException ex) {
//            System.out.println("The file: \"" + appendFileName + "\" was "
//                    + "not found");
//            System.out.println(ex.toString());
//        }
//        
        
    }
    
    //Close newly converted file - appended file is left open.
    private void CloseInputFile() {
        if (fileReader != null)
            fileReader.close();  
    }
    
}//End Of Class
