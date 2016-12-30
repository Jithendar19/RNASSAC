/**
 * Compiled using NetBeans IDE 8.1
 * @author Jithendar Anandan
 * NSA Project
 * Texas A&M University - Commerce
 * Creates Suffix file in the directory which will be read from to search the user pattern
**/
package edu.project.suffixSearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateSuffixFile {
        private int SuffixArrayIndexIterator = 0;
        private Scanner inputReader;
        
    public void CreateOutputFile(int[][] suffix, int SuffixArrayIndexIterator, String FileName) {
        try (PrintWriter writeToFile = new PrintWriter(new File(FileName))) {
            for(int i = 0; i < SuffixArrayIndexIterator-1 ; i++)
            {
                int k;
                for (k=0; k < 3 ; k++)
                {
                    writeToFile.print(suffix[i][k]+" ");
                } 
               writeToFile.print("\n");
            } 
            System.out.println("The Suffix file has been named : " +FileName);            
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(FileProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void OpenFile(String FileName) {
        try {
            
            inputReader = new Scanner (new FileReader(FileName));
        }
        catch(Exception e) {
            System.out.println("The input file \"" + FileName + "\" was "
                    + "not found");
            System.out.println(e.toString());
        }
    } 
    
    public int[][] ReadFile(int[][] Suffix) {
        int j=0;
        while(inputReader.hasNextInt()) {
            if(j > 2)
            {
                j = 0; SuffixArrayIndexIterator++;
            }
            Suffix[SuffixArrayIndexIterator][j++] = inputReader.nextInt();
        }
         //Display sorted suffix array
        /*    System.out.println("Sorted Suffix Array :");
            for(int x =0; x < SuffixArrayIndexIterator ; x++)
            {
                for(int y = 0; y < 3; y++)
                {
                    System.out.print(Suffix[x][y]+ " ");
                }
                System.out.println();
            } */
        return Suffix;
    }
    
    public int SuffixArrayIndexIterator()
    {
        return SuffixArrayIndexIterator;
    }
    public void CloseFile() {
        if (inputReader != null)
            inputReader.close();  
    } 
    
}
