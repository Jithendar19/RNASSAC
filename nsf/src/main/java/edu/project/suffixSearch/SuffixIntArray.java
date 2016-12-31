/**
 * Compiled using NetBeans IDE 8.1
 * @author Jithendar Anandan
 * NSA Project
 * Texas A&M University - Commerce
 * Implementing Suffix Array for signed integers
**/

package edu.project.suffixSearch;

public class SuffixIntArray {

        private int lengthOfEachRNA[]= new int[5000];
        private int SuffixArrayIndexIterator = 0;
        private int TempArray[][] = new int[3000000][3];
        private String outputString = "#" ;
        
        
        /* A dupilcate copy of the RNA pattern is created to work on it 
        instead of disturbing the actual inut array */
        
        /* Function that creates the Suffix Array, sorts the array and does binary search */
        public int[][] createSuffixArray(int[][] inputRNA,int FileNumber, int[][] Suffix, int Nucleotide)
        {           
            //Display  RNA array and calculating the length of each array
         //  System.out.println("Input RNA Array : ");
            for(int x = 0; x < FileNumber ; x++)
            {
                int y;
                for (y=0; inputRNA[x][y] != -99999; y++)
                {
                 //  System.out.print(inputRNA[x][y]+" ");
                } 
                lengthOfEachRNA[x] = y-1;
              //  System.out.println();
            } 
            //Display Length of each file
           /* System.out.println();
            for (int x = 0; x < NoOfFiles ; x++)
            {
                System.out.print("Length of File "+  (x+1) );
                System.out.print(": " +lengthOfEachRNA[x] +"  " );
            } */
           
            //Creating the suffix array
            int j=0,k=0,t=1;
            System.out.println("Creating Suffix Array.....");
            for (int x =0 ; x < FileNumber ; x++ )
            {
                k =0;t=1;
                while(k < lengthOfEachRNA[x] )
                {
                    j = 0;k++;
                    Suffix[SuffixArrayIndexIterator][j++] = inputRNA[x][0];
                    Suffix[SuffixArrayIndexIterator][j++] = k;
                    Suffix[SuffixArrayIndexIterator++][j] = inputRNA[x][t++];
                    
                }
            }  
            
           // System.out.println("SuffixArrayIndexIterator : "+SuffixArrayIndexIterator);
             //Display unsorted suffix array
           /* for(int x =0; x < SuffixArrayIndexIterator ; x++)
            {
                for(int y = 0; y < 3; y++)
                {
                    System.out.print(Suffix[x][y]+ " ");
                }
                System.out.println();
            }  */
          
            //Using Merge-sort to sort the Suffix Array
            doMergeSort(0, SuffixArrayIndexIterator - 1,inputRNA,Suffix );
            //Display sorted Suffix Array
            System.out.println("Sorted Suffix Array :");
           /* for(int x =0; x < SuffixArrayIndexIterator ; x++)
            {
                for(int y = 0; y < 3; y++)
                {
                    System.out.print(Suffix[x][y]+ " ");
                }
                System.out.println();
            } */
        //Writes Suffix Array into Suffix file
       /* CreateSuffixFile suffixFile = new CreateSuffixFile();
        if (Nucleotide == 1)
            suffixFile.CreateOutputFile(Suffix,SuffixArrayIndexIterator,"SuffixFileNucleotide.txt");
        else
            suffixFile.CreateOutputFile(Suffix,SuffixArrayIndexIterator,"SuffixFileStructure.txt"); */
            return Suffix;
        }
        
        public int getSuffixArrayIndexIterator()
        {
            return SuffixArrayIndexIterator;
        }
        
        private void doMergeSort(int lowerIndex, int higherIndex, int[][] inputRNA, int[][] Suffix) {
        if (lowerIndex < higherIndex) {
            int middle = (higherIndex + lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle,inputRNA,Suffix);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex,inputRNA,Suffix);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex,inputRNA,Suffix);
        }
    }
        
        private void mergeParts(int lowerIndex, int middle, int higherIndex, int[][] inputRNA, int[][] Suffix) {
                for (int i = lowerIndex; i <= higherIndex; i++) {
                    int Counter = 0;
                       while(Counter < 3)
                       {
                          TempArray[i][Counter] = Suffix[i][Counter]; 
                          Counter++;
                       }
        } 
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        int Greater;
        while (i <= middle && j <= higherIndex) {
            Greater = Compare (i,j,inputRNA,Suffix);
            if (Greater == 0) {
                CopyArray(k,i,Suffix);
                i++;
            } else {
                CopyArray(k,j,Suffix);
                j++;
            }
            k++;
        }
        while (i <= middle) {
            CopyArray(k,i,Suffix);
            k++;
            i++;
        }
        while (j <= higherIndex) {
            CopyArray(k,j,Suffix);
            k++;
            j++;
        } 
    }
        
        private int Compare(int i, int j, int[][] inputRNA,int[][] Suffix) {
              int Greater = -1;
                        int m = TempArray[i][0];
                        int n = TempArray[j][0];
                        int u = TempArray[i][1];
                        int v = TempArray[j][1];
                        while(inputRNA[m-1][u] != -99999 || inputRNA[n-1][v] != -99999)
                        {
                            if(inputRNA[m-1][u] > inputRNA[n-1][v])
                            {
                                Greater = 1;
                                break;
                            } 
                            if(inputRNA[m-1][u] < inputRNA[n-1][v])
                            {
                                Greater = 0;
                                break;
                            }
                            u++;v++;
                        } 
                        return Greater;
                    }    
            
                private void CopyArray(int k, int x,int[][] Suffix) {
                       int Counter = 0;
                       while(Counter < 3)
                       {
                           Suffix[k][Counter] = TempArray[x][Counter];
                           Counter++;
                       } 
                } 
               
//Binary Search to find the no. of matches 
/*public String search(int[][] Search, int length, int n,int[][] Suffix, int[][] inputRNA,int SuffixArrayIndexIterator)
{
	int Greater , NoOfMatches = 0; outputString = "#";
	int l = 0, r = SuffixArrayIndexIterator;
        while ( l <= r)
        {
          int keymid = l + (r - l)/2;
          Greater = CompareSearchRNA(keymid,n,Search,length,Suffix, inputRNA);
          if (Greater == -1)
          {
            NoOfMatches = 1;
            int small = searchSmallestRange(Search,l,keymid,length,n,Suffix,inputRNA);
            int large = searchLargestRange(Search,keymid,r,length,n,Suffix,inputRNA);
            outputString = GenerateOutputString(small,large,Suffix,length);
            if(small != large)
                NoOfMatches = (large - small)+1;
            outputString = outputString + "*" + NoOfMatches + "$";
            /* System.out.println("Largest Match  :" +large);
            System.out.println("Smallest Match  :" +small);
            System.out.println("Number of matches : " +NoOfMatches); */
           // System.out.println("outputString1 : " +outputString);
         /*   return outputString;
          } 
          if (Greater == 1)
            l = keymid + 1;
          else
            r = keymid - 1;
        }
      // System.out.println("No matches were found ");
     // System.out.println("outputString2 : " +outputString);
      outputString = outputString + "*" + NoOfMatches + "$";
      return outputString;
}

    //Binary Search to find the smallest range
    public int searchSmallestRange(int[][] Search, int left, int right, int length, int n, int[][] Suffix, int[][] inputRNA)
    {
            //keymid is the the first found instance of the searching pattern and it becomes the upper bound when we search for the smallest range.
            //In this function the range we are searching will never be greater than search pattern
            int l = left, r = right,Greater,mid;
            while ( l <= r)
            {
               mid = l + (r - l)/2;
               Greater = CompareSearchRNA(mid,n,Search,length,Suffix,inputRNA);
               if (Greater == -1)
                   {
                     int equal;
                     //The only instance we know if we reached the smallest range is to compare the previous pattern in the range with the search pattern & see if it       
                      // differs else continue the search
                     equal = CompareSearchRNA(mid-1,n,Search,length,Suffix,inputRNA);
                     if(equal == -1)
                       r = mid - 1;
                     else
                         return mid;
                   }
               else if (Greater == 1) 
                    l = mid + 1;                  
            }
            return 0;
    }

    //Binary Search to find the largest range
    public int searchLargestRange(int[][] Search, int left, int right, int length, int n,int[][] Suffix, int[][] inputRNA)
    {
            //keymid is the the first found instance of the searching pattern and it becomes the lowerbound bound when we search for the largest range.
            //In this function the range we are searching will never be smaller than search pattern
            int l = left, r = right,Greater;
            while ( l <= r)
            {
               int mid = l + (r - l)/2;
               Greater = CompareSearchRNA(mid,n,Search,length,Suffix, inputRNA);

               if (Greater == -1)
                   {
                     int equal;
                     //The only instance we know if we reached the smallest range is to compare the next pattern in the range with the search pattern & see if it           
                     // differs else continue the search
                     equal = CompareSearchRNA(mid+1,n,Search,length,Suffix, inputRNA);
                     if(equal == -1)
                       l = mid + 1;
                     else
                       return mid;
                   }
               else if (Greater == 0) 
                    r = mid - 1;                  
            }
            return 0;
    }

    //Functions returns 1 if the search pattern is greater, 0 , if it is smaller, -1 if equal
    public int CompareSearchRNA(int mid, int n, int[][] searchRNA, int length,int[][] Suffix, int[][] inputRNA)
    {
            int u = 1; int Greater = -1;
            int m = Suffix[mid][0];
            int v = Suffix[mid][1];
            while(u <= length)
            {
                    if(searchRNA[n][u] > inputRNA[m-1][v])
                    {   
                            Greater = 1;
                            break; 
                    } 
                    if(searchRNA[n][u] < inputRNA[m-1][v])
                    {
                            Greater = 0;
                            break;
                    }
                    u++;v++;
            } 
            return Greater;
    }

    private String GenerateOutputString (int small, int large,int[][] Suffix, int length)
    {
        String output = "";
        for(int i=small; i <= large; i++)
        {
            output = output + Suffix[i][0] + "#" + Suffix[i][1] + "#" ;
        }
        output = output + length;
        return output;
    } */
}