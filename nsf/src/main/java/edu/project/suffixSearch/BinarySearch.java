/**
 * Compiled using NetBeans IDE 8.1
 * @author Jithendar Anandan
 * NSA Project
 * Texas A&M University - Commerce
 * Binary Search function to find the substructure matches from the database 
**/
package nsfrna;

/**
 * @author Jeethu
 */
public class BinarySearch {
    
private String outputString = "#" ;
    
public String search(int[][] Search, int length, int n,int[][] Suffix, int[][] inputRNA,int SuffixArrayIndexIterator)
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
            return outputString;
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
    }
    
}
