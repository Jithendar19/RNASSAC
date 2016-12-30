/*
 * Compiled using NetBeans IDE 8.1
 * @author Jithendar Anandan
 * NSA Project
 * Texas A&M University - Commerce
 * A Class contains all the RNA variables and Setters and getters

 */
package edu.project.suffixSearch;

public class RNAVariables {
    
    //Variables created for Search 
    private int[][] InputRNA = new int[5000][4500];
    private int SuffixStructure[][] = new int[3000000][3];
    private int SuffixNucleotide[][] = new int[3000000][3];
    private int[][] SearchRNA = new int[100][400];
    private int[] LengthOfEachRNA= new int[5000];
    private int  Submit, Nucleotide,NoOfSearchPatterns,FileNumber,SuffixArrayIndexIteratorStructure,SuffixArrayIndexIteratorNucleotide;
    private String SearchFileName,ConcatenatedFileName;
    private String OutputString,InputFileName;
    
    //Variables created for Comparison 
    private String CompareRNAString = "";
    private int[] MatchingRNAs = new int[5000];
    private int[] CompareRNANums = new int[51];
    private int[] LengthOfCompareRNAs = new int[51];
    private int SmallestRNAIndex, CompareWithNucleotide = 1,NumOfCompareRNAs = 8, LengthOfSmallestRNA, NoOfMatches,OptimalLength;
    private String[] RNANames = new String [4667];
    private String[] CompareRNANames = new String[51];
    private int[][] CompareRNAs = new int[51][4500];
    private int[][] InputRNAStructure = new int[5000][4500];
    private int[][] SearchCompareRNA = new int[1][4500];
    private int [][] OptimalPattern = new int[1][4500];
    private String CompareOutputString ="";
    
    //Getters for Search
    
    public int[][] getInputRNANucleotide(){
        return InputRNA;
    }
    
    public int[][] getSuffixStructure(){
    return SuffixStructure;
    }
    
    public int[][] getSuffixNucleotide(){
    return SuffixNucleotide;
    }
    
    public int[][] getSearchRNA(){
    return SearchRNA;
    }
    
    public int getLengthOfEachRNA(int x){
        int y;
        for (y=0; this.SearchRNA[x][y] != -99999; y++)
        {
        //    System.out.print(searchRNA[x][y]+" ");
        } 
        this.LengthOfEachRNA[x] = y-1;
       // System.out.println();
        return this.LengthOfEachRNA[x];
    }
   
  
    public int getSubmit(){
    return Submit;
    }
    
    public int getNucleotide(){
    return Nucleotide;
    }
    
    public int getNoOfSearchPatterns(){
    return NoOfSearchPatterns;
    }
    
    public int getFileNumber(){
    return FileNumber;
    }
    
    public int getSuffixArrayIndexIteratorStructure(){
    return SuffixArrayIndexIteratorStructure;
    }
    
    public int getSuffixArrayIndexIteratorNucleotide(){
    return SuffixArrayIndexIteratorNucleotide;
    }
    
    public String getSearchFileName(){
    return SearchFileName;
    }
    
    public String getConcatenatedFileName(){
    return ConcatenatedFileName;
    }
    
    public String getOutputString(){
    return OutputString;
    }
    
    public String getInputFileName(){
    return InputFileName;
    }
    
    //Getters for RNA comparison
    
   public String[] getRNANames(){
    return RNANames;
    }
   
   public String getCompareRNAString(){
    return CompareRNAString;
    }
   
   public String[] getCompareRNANames(){
    return CompareRNANames;
    }
   
   public int getNumOfCompareRNAs(){
    return NumOfCompareRNAs;
    }
   
    public int[] getCompareRNANums(){
    return CompareRNANums;
    }
   
    public int[][] getCompareRNAs(){
    return CompareRNAs;
    }
    
    public int getCompareWithNucleotide(){
    return CompareWithNucleotide;
    }
    
    public int[][] getInputRNAStructure(){
        return InputRNAStructure;
    }
    
    public int[] getLengthOfCompareRNAs(){
        return LengthOfCompareRNAs;
    }
    
    public int getSmallestRNAIndex(){
    return SmallestRNAIndex;
    }
    
    public int[] getMatchingRNAs(){
    return MatchingRNAs;
    }
    
    public int getLengthOfSmallestRNA(){
    return LengthOfSmallestRNA;
    }
    
    public int[][] getSearchCompareRNA(){
    return SearchCompareRNA;
    }

    public int getNoOfMatches(){
    return NoOfMatches;
    }
    
    public int getOptimalLength(){
    return OptimalLength;
    }
    
    public int[][] getOptimalPattern(){
        return OptimalPattern;
    }
    
    public String getCompareOutputString (){
        return CompareOutputString;
    }

    //Setters for Search
    
    public void setInputRNANucleotide(int[][] InputRNA ){
    
        //Deep copy of InputRNA Nucleotide
        for(int i = 0; i < FileNumber; i++)
        {
            //Replace 4667 by FileNumber in the end
           for (int j = 0; InputRNA[i][j] != -99999 ; j++)
           {
                    this.InputRNA[i][j] = InputRNA[i][j];
           }
        }


    //this.InputRNA = InputRNA ;
    }
    
    public void setSuffixStructure(int[][] SuffixStructure){
    this.SuffixStructure = SuffixStructure;
    }
    
    public void setSuffixNucleotide(int[][] SuffixNucleotide){
    this.SuffixNucleotide = SuffixNucleotide;
    }
    
    public void setSearchRNA(int[][] SearchRNA){
    this.SearchRNA = SearchRNA;
    }
    
    public void setLengthOfEachRNA(int x){
    this.LengthOfEachRNA[x] = x;
    }
    
   
    public void setSubmit(int Submit){
    this.Submit = Submit;
    }
    
    public void setNucleotide(int Nucleotide){
    this.Nucleotide = Nucleotide;
    }
    
    public void setNoOfSearchPatterns(int NoOfSearchPatterns){
    this.NoOfSearchPatterns = NoOfSearchPatterns;
    }
    
    public void setFileNumber(int FileNumber){
    this.FileNumber = FileNumber;
    }
    
    public void setSuffixArrayIndexIteratorNucleotide(int SuffixArrayIndexIteratorNucleotide){
    this.SuffixArrayIndexIteratorNucleotide = SuffixArrayIndexIteratorNucleotide;
    }
    
    public void setSuffixArrayIndexIteratorStructure(int SuffixArrayIndexIteratorStructure){
    this.SuffixArrayIndexIteratorStructure = SuffixArrayIndexIteratorStructure;
    }
    
    public void setSearchFileName(String SearchFileName){
    this.SearchFileName = SearchFileName;
    }
    
    public void setConcatenatedFileName(String ConcatenatedFileName){
    this.ConcatenatedFileName = ConcatenatedFileName;
    }
    
    public void setOutputString(String OutputString){
    this.OutputString = OutputString;
    }
    
    public void setInputFileName(String InputFileName){
    this.InputFileName = InputFileName;
    }
    
    //Setters for RNA Comparison
    
    public void setRNANames(String[] RNANames){
    this.RNANames = RNANames;
    }
    
    public void setCompareRNAString(String CompareRNAString){
    this.CompareRNAString = CompareRNAString;
    }
    
    public void setCompareRNANames(String[] CompareRNANames){
    this.CompareRNANames = CompareRNANames;
    }
    
    public void setNumOfCompareRNAs(int NumOfCompareRNAs){
    this.NumOfCompareRNAs = NumOfCompareRNAs;
    }
    
    public void setCompareRNANums(int[] CompareRNANums){
    this.CompareRNANums = CompareRNANums;
    }
    
    public void setCompareRNAs(int[][] CompareRNAs){
    this.CompareRNAs = CompareRNAs;
    }
    
    public void setCompareWithNucleotide(){
    this.CompareWithNucleotide = CompareWithNucleotide;
    }
    
    public void setInputRNAStructure (int[][] InputRNAStructure ){
    this.InputRNAStructure = InputRNAStructure ;
    }
    
    public void setLengthOfCompareRNAs (int[] LengthOfCompareRNAs ){
    this.LengthOfCompareRNAs = LengthOfCompareRNAs ;
    }
    
    public void setSmallestRNAIndex (int SmallestRNAIndex ){
    this.SmallestRNAIndex = SmallestRNAIndex;
    }
    
    public void setMatchingRNAs (int[] MatchingRNAs ){
    this.MatchingRNAs = MatchingRNAs;
    }
    
    public void setLengthOfSmallestRNA (int LengthOfSmallestRNA ){
    this.LengthOfSmallestRNA = LengthOfSmallestRNA;
    }
    
    public void setSearchCompareRNA (int[][] SearchCompareRNA ){
    this.SearchCompareRNA = SearchCompareRNA;
    }
    
    public void setNoOfMatches (int NoOfMatches ){
    this.NoOfMatches = NoOfMatches;
    }
    
    public void setOptimalLength(int OptimalLength ){
    this.OptimalLength = OptimalLength;
    }
    
    public void setCompareOutputString (String CompareOutputString){
        this.CompareOutputString = CompareOutputString;
    }
    
    public void  setOptimalPattern(int[][] OptimalPattern){
        this.OptimalPattern = OptimalPattern;
    }
    

    // Display Arrays    
    public void DisplayInputRNA() {
        System.out.println("Input RNA :");
        for(int i = 0; i < FileNumber; i++)
        {
            //Replace 4667 by FileNumber in the end
           for (int j = 0; this.InputRNA[i][j] != -99999 ; j++)
           {
                        System.out.print(this.InputRNA[i][j] +" ");
           }
           System.out.println();
        }
    }
    
        public void DisplayInputRNAStructure() {
        System.out.println("Input RNA Structure:");
        for(int i = 0; i < FileNumber; i++)
        {
            //Replace 4667 by FileNumber in the end
           for (int j = 0; InputRNAStructure[i][j] != -99999 ; j++)
           {
                 System.out.print(InputRNAStructure[i][j] +" ");
           }
           System.out.println();
        }
    }       
}