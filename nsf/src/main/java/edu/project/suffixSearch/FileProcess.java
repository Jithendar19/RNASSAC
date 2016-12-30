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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;


public class FileProcess {
    private Scanner in = new Scanner(System.in), 
            inputReader, 
            lineReader;
    private String inputFileName, 
            outputFileName,
            line, 
            nucleotide, 
            commentsString, 
            linesString;
    private int index, 
            partnerOfIndex;
    public int length;
    public int[] integerAsArray, nucleotideAsIntegerArray;
                    
    private ArrayList<String> lines = new ArrayList<String>(), 
            comments = new ArrayList<String>();
    public ArrayList<Integer> integerArray = new ArrayList<Integer>();
    
//constructor function
    public FileProcess(String inputFile) {
        OpenInputFile(inputFile);
        ReadFile();
        CloseInputFile();
        CreateOutputFile();   
    }
    /*
        This function attempts to open the file given by the main class. It has 
            one varable passed to it by value, the input file name that is given 
            by the main
    */
    private void OpenInputFile(String inputFile) {
        inputFileName = inputFile;
        try {
            
            inputReader = new Scanner (new FileReader(inputFileName));
        }
        catch(Exception e) {
            System.out.println("The input file \"" + inputFileName + "\" was "
                    + "not found");
            System.out.println(e.toString());
        }
    } 
    
    private void ReadFile() {
        while(inputReader.hasNextLine()) {
            line = inputReader.nextLine();
            lineReader = new Scanner(line);
            
            if (line.startsWith("#")) {
                comments.add(line + "\n");
            }
            else {
                index = lineReader.nextInt();
                nucleotide = lineReader.next();
                partnerOfIndex = lineReader.nextInt();

                if (partnerOfIndex != 0)
                    partnerOfIndex = partnerOfIndex - index;

                lines.add(nucleotide + " " + partnerOfIndex + "\n");
                integerArray.add(partnerOfIndex);
            } 
            
        }
       
        StringBuilder builder = new StringBuilder();
        for (String value : comments) {
            builder.append(value);
        }
        commentsString = builder.toString();
        
        StringBuilder builder2 = new StringBuilder();
        for (String value : lines) {
            builder2.append(value);
        }
        linesString = builder2.toString(); 
        
    }
    
    public int[] BuildIntegerArray(){
        length = integerArray.size();
        integerAsArray = new int[length +1];
        Iterator <Integer> iterator = integerArray.iterator();
        for (int i = 0; i < length; i++) {
            integerAsArray[i] = iterator.next();
        }
        integerAsArray[length] = -99999;
        return integerAsArray;
    }
    
    private void CloseInputFile() {
        if (inputReader != null)
            inputReader.close();  
    }
    
    private void CreateOutputFile() {
        outputFileName = "new"+ inputFileName;
        
        try (PrintWriter writeToFile = new PrintWriter(new File(outputFileName))) {
            writeToFile.print(commentsString);
            writeToFile.print(linesString + "*Eof: " + outputFileName);
            System.out.println("The newly converted file has been named: " +
                outputFileName);            
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(FileProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String OutputFileName(){
        return outputFileName;
    }
}