/**
 * Compiled using NetBeans IDE 8.1
 * @author Jithendar Anandan
 * NSA Project
 * Texas A&M University - Commerce
 * Processing the NewFormat Concatenated file
**/


package edu.project.suffixSearch;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SuffixFileProcess {
    
    //variables as described above
    private Scanner inputReader, 
            lineReader;
    private String inputFileName, 
            line;
    private char nucleotide; 
    private int partnerOfIndex,FileNumber=0;
    public int length,ascii,index;
    private ArrayList<String> comments = new ArrayList<String>();
    
    /*
        This function attempts to open the file.  It has 
            one varable passed to it by value, the input file name that is given 
            by the main
    */
    public void OpenInputFile(String inputFile) {
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
    
    public int[][] ReadFile(int Nucleotide, int[][] inputArray,int SearchFile) {
        int FileElement=0;
            inputArray[FileNumber][FileElement++] = FileNumber+1;
        while(inputReader.hasNextLine()) {
            line = inputReader.nextLine();
            lineReader = new Scanner(line);
            
            if (line.startsWith("#")) {
                comments.add(line + "\n");
            }
            else {
                    if(line.startsWith("*"))
                    {
                      inputArray[FileNumber++][FileElement] = -99999;
                      FileElement = 0;
                      inputArray[FileNumber][FileElement++] = FileNumber+1;
                    }
                    else
                    {
                        if(SearchFile == 1)
                            index = lineReader.nextInt();
                        
                        nucleotide = lineReader.next().charAt(0);
                        partnerOfIndex = lineReader.nextInt();
                        
                        if (partnerOfIndex != 0 && SearchFile == 1)
                            partnerOfIndex = partnerOfIndex - index;
                        
                        if (Nucleotide == 1){
                        partnerOfIndex = partnerOfIndex << 8;
                        ascii = (int) nucleotide;
                        }
                        
                        if(partnerOfIndex < 0 && Nucleotide == 1)
                            partnerOfIndex = partnerOfIndex - ascii;
                        if(partnerOfIndex >= 0 && Nucleotide == 1)
                            partnerOfIndex = partnerOfIndex + ascii;
                        
                        inputArray[FileNumber][FileElement++] = partnerOfIndex;
                    }
                    
                }
        }
        if(SearchFile == 1)
           inputArray[FileNumber][FileElement++] = -99999;
        return inputArray;
    }
    
    public int BuildIntegerArray(int[][] inputArray){
        return FileNumber;
    } 
 
    public void CloseInputFile() {
        if (inputReader != null)
            inputReader.close();  
    }
}
