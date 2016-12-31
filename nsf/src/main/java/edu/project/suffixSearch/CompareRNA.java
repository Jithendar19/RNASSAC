/**
 * Compiled using NetBeans IDE 8.1
 * @author Jithendar Anandan
 * NSA Project
 * Texas A&M University - Commerce
 * RNA Comparison
**/

package edu.project.suffixSearch;
import java.util.Arrays;

public class CompareRNA {
    
    //Reading the File Names of RNAs to be be compared 
    public String[] GetCompareRNANames(String CompareRNAString, int NumOfCompareRNAs, String[] CompareRNANames) {
        
        for(int i = 1; i <= NumOfCompareRNAs; i++)
        { 
           CompareRNANames[i] = CompareRNAString.split(",")[i-1];
          // System.out.println("CompareRNANames :" +CompareRNANames[i]);
        }
        return CompareRNANames;
    }
    // Finding the File Numbers of the RNAs to be compared
        public int[] GetCompareRNAs(String[] CompareRNANames, int[] CompareRNAs, int NumOfCompareRNAs, String[] RNANames, int FileNumber) {
        
        for(int i = 1; i <= NumOfCompareRNAs; i++)
        {
            for (int j = 1; j <= FileNumber; j++)
           {
               if(CompareRNANames[i].equals(RNANames[j]))
               {
                   CompareRNAs[i] = j;
                 //  System.out.println("CompareRNANums :" +CompareRNAs[i]);
                   break;
               }
           }
        }
        return CompareRNAs;
    }
     //Reading the RNAs to be compared in New Format   
        public int[][] ConvertCompareRNAs (int[] ComapareRNANums, int[][] CompareRNAs, int NumOfCompareRNAs, int CompareWithNucleotide, String ConcatenatedFileName, int[][] InputRNA, int[][] InputRNAStructure) {
            
            //If comparison is only with structure
            if(CompareWithNucleotide == 0)
            {
                int j,k;
                for(int i = 1; i <= NumOfCompareRNAs; i++)
                {
                    k = (ComapareRNANums[i]) - 1;
                    for(j =0; InputRNAStructure[k][j] != -99999; j++)
                    {
                     CompareRNAs[i][j] = InputRNAStructure[k][j];
                    // System.out.print(CompareRNAs[i][j] +" ");
                    }
                   // System.out.println();
                    CompareRNAs[i][j] = -99999;
                }   
            }
            
            //If comparison is with both nucleotide and structure
            else
            {
                int j,k;
                for(int i = 1; i <= NumOfCompareRNAs; i++)
                {
                    k = (ComapareRNANums[i]) - 1;
                    for(j =0; InputRNA[k][j] != -99999; j++)
                    {
                        CompareRNAs[i][j] = InputRNA[k][j];
                      //  System.out.print(CompareRNAs[i][j] +" ");
                    }
                   // System.out.println("J: " +j);
                    CompareRNAs[i][j] = -99999;
                }
            }
            return CompareRNAs;
        }
        
        // Finding the length of each RNA to be compared
        public int[] FindLengthOfComapreRNAs (int[][] CompareRNAs, int NumOfCompareRNAs, int[] LengthOfCompareRNAs){
            for(int i = 1; i <= NumOfCompareRNAs; i++)
            {
                int j;
                for(j = 0; CompareRNAs[i][j] != -99999; j++);
                LengthOfCompareRNAs[i] =  j;
               // System.out.println("Length of Compare RNAs :" +LengthOfCompareRNAs[i]);
            }
            return LengthOfCompareRNAs;
        }
        
        //Finding the file number of RNA with the smallest length of all Compare RNAs
        public int FindSmallestRNA (int SmallestRNAIndex, int[] LengthOfCompareRNAs, int NumOfCompareRNAs) {
            
            SmallestRNAIndex = 1;
            
            for (int i = 2; i <= NumOfCompareRNAs; i++)
            {
                if(LengthOfCompareRNAs[i] < SmallestRNAIndex)
                    SmallestRNAIndex = i;
            }
          //  System.out.println("Index of Smallest RNA  :" +SmallestRNAIndex);
            return SmallestRNAIndex;
        }
        
        //Finding the lenght of the Smallest RNA
        public int FindLengthOfSmallestRNA (int SmallestRNAIndex, int LengthOfSmallestRNA, int[] LengthOfCompareRNAs){
             
            LengthOfSmallestRNA = LengthOfCompareRNAs[SmallestRNAIndex];
            System.out.println("Length of Smallest RNA  :" +LengthOfSmallestRNA);
            return LengthOfSmallestRNA;
        }
        
        //Reading the pattern of the smallest RNA
        public int[][] SearchCompareRNA (int[][] SearchCompareRNA, int[][] CompareRNA, int SmallestRNAIndex){
            int j;
            
            System.out.println("Findig Smallest RNA for comparison");
            for(j = 0; CompareRNA[SmallestRNAIndex][j] != -99999; j++)
            {
                SearchCompareRNA[0][j] = CompareRNA[SmallestRNAIndex][j];
                System.out.print(SearchCompareRNA[0][j] +" ");
            }
            System.out.println();
            SearchCompareRNA[0][j] = -99999;
            return SearchCompareRNA;
        }
        
        //Comparing all the RNAs to find the Maximum longest common pattern in the RNAs to be compared. Returns the Optimal Length.
        public int Compare (int[][] SearchCompareRNA, int LengthOfSmallestRNA, int[] CompareRNANums, int CompareWithNucleotide, int[][] SuffixNucleotide, int[][] SuffixStructure, int[][] InputRNAStructure, int[][] InputRNANucleotide, int SuffixArrayIndexIteratorNucleotide, int SuffixArrayIndexIteratorStructure, int[] MatchingRNAs, int NumOfCompareRNAs)
        {
        	
        	System.out.println("Finding the optimal legnth");
            int[][] SearchSuffix = new int[4500][4500]; int s;
            for(s = 0; s < LengthOfSmallestRNA; s++)
            {
                SearchSuffix[0][s] = SearchCompareRNA[0][s];
            } 
            SearchSuffix[0][s] = -99999;
            //Display SearchSuffix 
            System.out.println("SearchSuffix :" ); 
            for(int t = 0; t < LengthOfSmallestRNA; t++)
            {
                System.out.print(SearchSuffix[0][t] + " "); 
            } 
            int  leftRange = 0, 
            		rightRange = LengthOfSmallestRNA,
            		p = 0,
            		n;
            int SearchLength = (leftRange + rightRange) / 2;
            
            //Assigning the SearchLength initially 
            if(((leftRange + rightRange) / 2) > 20)
            {
                n = 1; //Number of suffixes in the smallest RNA to be searched
                SearchLength = (leftRange + rightRange) / 2;
            }
            else if(LengthOfSmallestRNA > 20 )
            {
                n = 0;
                SearchLength = LengthOfSmallestRNA;
            }
            else //There is no common patterns among the RNAs
            {
                n = - 1;
                System.out.println("No common pattern ");
                SearchLength = 0;
                return - 1;
            }
            boolean Present = true;
            System.out.println("\nInitial search length : " + SearchLength);
            
            do
            { 
              //  System.out.println("SearchLength: "+SearchLength);
                //Copying all suffixes of the smallest RNA of 'SearchLength'
                for(p = 0; p <= (LengthOfSmallestRNA - SearchLength); p++)
                {
                    SearchSuffix[p][0] =  p;
                    int q;
                    for(q = 1 ; q <= SearchLength  ; q++)
                    {
                        SearchSuffix[p][q] =  SearchCompareRNA[0][(q+p)];
                        //    System.out.print(SearchSuffix[p][q] + " ");
                    }
                    //System.out.println();
                    SearchSuffix[p][q] = -99999;
                }
              //  SuffixIntArray cmpsearch = new SuffixIntArray();
                BinarySearch cmpsearch = new BinarySearch();
                
                //Comparing each suffix to find the optimal length
                System.out.println("Comparing each suffix to find the optimal length");
                for(int x = 0; x <= p; x++) {                  
                    String SearchString = "";
                    System.out.println("\n*****************Suffix #: " + x + "******************");
                    if(CompareWithNucleotide == 1) {
                    	System.out.println("Compare with Nucleotide = " + CompareWithNucleotide);
                        if (x == 105) {
                        	System.out.println(x);
                        }
                    	SearchString = cmpsearch.search(SearchSuffix, SearchLength, x, SuffixNucleotide, InputRNANucleotide, SuffixArrayIndexIteratorNucleotide);
                    }
                    else {
                    	System.out.println("Compare with Nucleotide = " + CompareWithNucleotide);
                       SearchString =  cmpsearch.search(SearchSuffix, SearchLength, x, SuffixStructure, InputRNAStructure, SuffixArrayIndexIteratorStructure);
                    }
                    
                    System.out.println("SearchString: " + SearchString +"\nX: " + x + "\nSearchLength: " + SearchLength + "\nP: " +p);      
                       
                    int NoOfMatches = Integer.valueOf(SearchString.substring(SearchString.indexOf("*") + 1, SearchString.indexOf("$")));
                    
                    System.out.println("Compare: " + "NoOfMatches: " +NoOfMatches);
                    
                    for (int i = 0; (i < (NoOfMatches*2)); i=i+2) {
                        int k = Integer.valueOf(SearchString.split("#")[i]);
                        System.out.println("K: " +k); 
                        MatchingRNAs[k] = 1;
                        System.out.println("End of for Loop");
                    }
                    System.out.println("Present = " + Present);
                    Present = true;
                    for(int i = 1; i <= NumOfCompareRNAs; i++) {
                        if(MatchingRNAs[CompareRNANums[i]] != 1) {
                            Present = false;
                            System.out.println("Not present :" + CompareRNANums[i] + " ");
                            break;
                        }
                    }
                    System.out.println("Present = " + Present);
                    Arrays.fill(MatchingRNAs, 0);       
                    if(Present == true)
                    {
                        System.out.println("Present in all Compare RNAs. Searchlength :" + SearchLength);
                        break;
                    }
                    System.out.println("*****************End of Suffix #: " + x + "******************\n");
                }//End of For Loop
                
                if(Present)
                {
                    leftRange = SearchLength;
                    System.out.println("Present. Length :" +SearchLength );
                }
                else
                {
                    rightRange = SearchLength; 
                    System.out.println("Not Present. Length :" +SearchLength );
                }
           
                System.out.println("Left Range :" +leftRange );
                System.out.println("Right Range :" +rightRange ); 
                
               if((SearchLength > 40))
                    SearchLength = (leftRange + rightRange) / 2;
               else if ((SearchLength >= 20 && SearchLength <=40))
               {
                   if(!Present)
                        SearchLength--;
                   else
                   {
                       SearchLength = (leftRange + rightRange) / 2;
                   }
               }
            } while (((SearchLength > 40) || (SearchLength >= 20 && SearchLength <=40)) && ((rightRange - leftRange) != 1));
            System.out.println("End of Comaring Function");
            if(SearchLength < 20)
                SearchLength = -1;
           System.out.println("Optimal Length : " + SearchLength);
           return SearchLength;
        }
        
        //Finding the common pattern with the Optimal length
        public int[][] FindOptimalPattern(int[][] OptimalPattern, int OptimalLength, int[][] SearchCompareRNA, int[][] SuffixNucleotide, int[][] SuffixStructure, int[][] InputRNAStructure, int[][] InputRNANucleotide, int SuffixArrayIndexIteratorNucleotide, int SuffixArrayIndexIteratorStructure, int CompareWithNucleotide, int[] CompareRNANums, int NumOfCompareRNAs, int LengthOfSmallestRNA)
        {
            //Return null if there is no common pattern
            if(OptimalLength == -1)
                return null;
            
            int[][] OptimalSuffix = new int[4500][4500]; int p,  n = 0;
            int[] MatchingRNAs = new int[5000];
            
            for(p = 0; p <= (LengthOfSmallestRNA - OptimalLength); p++)
            {
                OptimalSuffix[p][0] =  p;
                int q;
                for(q = 1 ; q <= OptimalLength ; q++)
                {
                    OptimalSuffix[p][q] =  SearchCompareRNA[0][(q+p)];
                }
                OptimalSuffix[p][q] = -99999;
            } 
            n = p;
          //  SuffixIntArray optimalsearch = new SuffixIntArray();
            BinarySearch optimalsearch = new BinarySearch();
            for(int x = 0; x <= n; x++)
            {
                String SearchString = "";
                if(CompareWithNucleotide == 1)
                   SearchString = optimalsearch.search(OptimalSuffix, OptimalLength, x, SuffixNucleotide, InputRNANucleotide, SuffixArrayIndexIteratorNucleotide);
                else
                   SearchString =  optimalsearch.search(OptimalSuffix, OptimalLength, x, SuffixStructure, InputRNAStructure, SuffixArrayIndexIteratorStructure);
                //System.out.println("SearchString :" +SearchString +"   X :  " +x+ "  OptimalLength :" +OptimalLength); 

                int NoOfMatches = Integer.valueOf(SearchString.substring(SearchString.indexOf("*") + 1, SearchString.indexOf("$")));
                   // System.out.println("NoOfMatches :" +NoOfMatches);
                    for (int i = 0; (i < (NoOfMatches*2)); i=i+2)
                    {
                        int k = Integer.valueOf(SearchString.split("#")[i]);
                        //System.out.println("K :" +k); 
                        MatchingRNAs[k] = 1;
                    }

                boolean Present = true;
                for(int i = 1; i <= NumOfCompareRNAs; i++)
                {
                    if(MatchingRNAs[CompareRNANums[i]] != 1)
                    {
                        Present = false;
                        //    System.out.print("Not present :" + CompareRNANums[i] + " ");
                        break;
                    }
                }
                Arrays.fill(MatchingRNAs, 0); 
                if(Present == true)
                {
                    int y;
                   // System.out.println("Optimal Pattern : ");
                    for(y = 0; y <= OptimalLength; y++)
                    {
                        OptimalPattern[0][y] = OptimalSuffix[x][y];
                     //   System.out.print(OptimalPattern[0][y] + " ");
                    }
                    OptimalPattern[0][y] = -99999;
                   // System.out.println();
                    break;
                }  
            }
            return OptimalPattern;
        }
        
        //Generating the output string for varna
        public String GeneratesetCompareOutputString (String CompareOutputString, int[][] OptimalPattern, int[][] SuffixNucleotide, int[][] SuffixStructure, int[][] InputRNAStructure, int[][] InputRNANucleotide, int SuffixArrayIndexIteratorNucleotide, int SuffixArrayIndexIteratorStructure, int OptimalLength, int CompareWithNucleotide, int[] CompareRNANums, int NumOfCompareRNAs)
        {
            //Return 'null' if there is no common pattern
            if(OptimalPattern == null)
            {
                CompareOutputString = "-1";
                return CompareOutputString;
            }
                
            
            boolean Present = false;
           // SuffixIntArray cmpstring = new SuffixIntArray();
            BinarySearch cmpstring = new BinarySearch();
            String OutputString ="";
            if(CompareWithNucleotide == 1)
                OutputString = cmpstring.search(OptimalPattern, OptimalLength, 0, SuffixNucleotide, InputRNANucleotide, SuffixArrayIndexIteratorNucleotide);
            else
                OutputString = cmpstring.search(OptimalPattern, OptimalLength, 0, SuffixStructure, InputRNAStructure, SuffixArrayIndexIteratorStructure);
            
           // System.out.println("OutputString :" +OutputString);
           int NoOfMatches = Integer.valueOf(OutputString.substring(OutputString.indexOf("*") + 1, OutputString.indexOf("$")));
          // System.out.println("NoOfMatches :" +NoOfMatches);
            int FileNumber,FileIndex ;  
            for(int i = 0; i < (NoOfMatches*2); i++)
            {
               FileNumber = Integer.valueOf(OutputString.split("#")[i++]);
               FileIndex = Integer.valueOf(OutputString.split("#")[i]);
              // System.out.println("FileNumber :" +FileNumber +" FileIndex : " +FileIndex);
              for(int  h = 1; h <= NumOfCompareRNAs; h++)
              {
                if(FileNumber == CompareRNANums[h])
                {
                    Present = true;
                    break;
                }
              }
              
              if(Present == true)
              {
                  CompareOutputString = CompareOutputString + FileNumber + "#" + FileIndex + "#";
              }
              Present = false;
            }
            CompareOutputString = CompareOutputString  + OptimalLength + "*" + NumOfCompareRNAs + "$";
            System.out.println(" Final Compare Output String :" +CompareOutputString);    
            return CompareOutputString;
        }
}
