package edu.nsf.graph;

/**
 * Compiled using NetBeans IDE 8.1
 * @author Jithendar Anandan
 * NSA Project
 * Texas A&M University - Commerce
 * Finds the RNA number and pattern index using Binary Search
**/

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class FindRNA {
    
        //variables as described above
    private Scanner in = new Scanner(System.in), 
            inputReader, 
            lineReader;
    private String inputFileName,line,nucleotide;
    
    private int[] lenRNA = new int[5000];
    private int NoOfFiles = 0, i =0;
    private ArrayList<String> comments = new ArrayList<String>();
    
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
                lenRNA[NoOfFiles++] = 0;
                while(inputReader.hasNextLine()) {
                line = inputReader.nextLine();
                lineReader = new Scanner(line);
                if (line.startsWith("#")) {
                    comments.add(line + "\n");
                }
                else {
                        if(line.startsWith("*"))
                           lenRNA[NoOfFiles++] = i;
                         else
                         {
                              while(lineReader.hasNext())
                              {
                                nucleotide = lineReader.next();
                                if(lineReader.hasNextInt())
                                   i++;
                              }
                          }
                      }
                }
               }
        
        //Display the length of each RNA 
        public void DisplayArray() {
            System.out.println();
            for (int x = 0; x < NoOfFiles ; x++)
            {
                System.out.print("Length of File "+  (x+1) );
                System.out.print(": " +lenRNA[x] +"  " );
                System.out.println();
            } 
        }
        
        public int[] BuildIntegerArray(){
        return lenRNA;
    } 
       public int NoOfFiles(){
        return NoOfFiles;
    } 
        
      private void CloseInputFile() {
        if (inputReader != null)
            inputReader.close();  
        }
            
        public static void main(String[] args)
        {
            int[] lenRNA = new int[5000];
            int SearchIndex = 2456247, index,NoOfFiles;
            String inputFile = "NewFormatConcatenated.txt";
            FindRNA len = new FindRNA();
            len.OpenInputFile(inputFile);
            len.ReadFile();
            len.DisplayArray();
            lenRNA = len.BuildIntegerArray();
            NoOfFiles = len.NoOfFiles();
            len.CloseInputFile();     
            
            int RNANum = Arrays.binarySearch(lenRNA,0,NoOfFiles,SearchIndex);
            if (RNANum < 0)
            {
                RNANum = Math.abs(RNANum);
                RNANum = RNANum - 1;
                index = SearchIndex - lenRNA[RNANum - 1];
            }
            else
            {
                index = SearchIndex - lenRNA[RNANum];
                RNANum = RNANum + 1;
            } 
            System.out.println("Pattern found at file number : " +RNANum);
            System.out.println("Pattern found at index : " +index);
        }
    }    
