/**
 * Compiled using NetBeans IDE 8.1
 * @author Eric Fry and Jithendar Anandan
 * NSA Project
 * Texas A&M University - Commerce
 * Main function
**/
package edu.project.suffixSearch;

public class NSFRNA {
 
    
	public NSFRNA(RNAVariables var) {
        
        //Set Concatenated file name and Search file name        
        var.setConcatenatedFileName("/opt/NSF_Data_Files/NewFormatConcatenated.txt");         
        //var.setSearchFileName("/opt/NSF_Data_Files/searchASE.txt");
       
        //Reading InputRNA
        System.out.println("Reading Input RNA Nucleotide....  "); 
        SuffixFileProcess NewFormatarray = new SuffixFileProcess();
        NewFormatarray.OpenInputFile(var.getConcatenatedFileName());
        var.setInputRNANucleotide(NewFormatarray.ReadFile(1, var.getInputRNANucleotide(),0));
        var.setFileNumber(NewFormatarray.BuildIntegerArray(var.getInputRNANucleotide()));
        NewFormatarray.CloseInputFile(); 
        
        System.out.println("Reading Input RNA Structure ....  ");
        SuffixFileProcess NewFormatStructure= new SuffixFileProcess();
        NewFormatStructure.OpenInputFile(var.getConcatenatedFileName());
        var.setInputRNAStructure(NewFormatStructure.ReadFile(0, var.getInputRNAStructure(),0));
        var.setFileNumber(NewFormatStructure.BuildIntegerArray(var.getInputRNAStructure()));
        NewFormatStructure.CloseInputFile(); 
        
                
        //Display Input RNA
       // var.DisplayInputRNA();
       // var.DisplayInputRNAStructure();
        
        //Creating Suffix Array Nucleotide
        System.out.println("Creating Suffix Array Nucleotide ....  ");
        SuffixIntArray createSuffixNucleotide = new SuffixIntArray();
        var.setSuffixNucleotide(createSuffixNucleotide.createSuffixArray(var.getInputRNANucleotide(), var.getFileNumber(), var.getSuffixNucleotide(), 1)); 
        var.setSuffixArrayIndexIteratorNucleotide(createSuffixNucleotide.getSuffixArrayIndexIterator());
        
        //Creating Suffix Array Structure
        System.out.println("Creating Suffix Array Nucleotide ....  ");
        SuffixIntArray createSuffixStructure = new SuffixIntArray();
        var.setSuffixStructure(createSuffixStructure.createSuffixArray(var.getInputRNAStructure(), var.getFileNumber(), var.getSuffixStructure(), 0)); 
        var.setSuffixArrayIndexIteratorStructure(createSuffixStructure.getSuffixArrayIndexIterator());
      
        //Reading Suffix Array Nucleotide
     /*   System.out.println("Reading Suffix Array from file....  "); 
        CreateSuffixFile readSuffixNucleotide = new CreateSuffixFile();
        readSuffixNucleotide.OpenFile("/opt/NSF_Data_Files/SuffixFileNucleotide.txt");
        var.setSuffixNucleotide(readSuffixNucleotide.ReadFile(var.getSuffixNucleotide()));
        var.setSuffixArrayIndexIteratorNucleotide(readSuffixNucleotide.SuffixArrayIndexIterator());
        readSuffixNucleotide.CloseFile();
        
        
        //Reading Suffix Array Structure
        CreateSuffixFile readSuffixStructure = new CreateSuffixFile();
        readSuffixStructure.OpenFile("/opt/NSF_Data_Files/SuffixFileStructure.txt");
        var.setSuffixStructure(readSuffixStructure.ReadFile(var.getSuffixStructure()));
        var.setSuffixArrayIndexIteratorStructure(readSuffixStructure.SuffixArrayIndexIterator());
        readSuffixStructure.CloseFile(); */
        
//        
//        //Converting SearchRNA to new format
//        SuffixFileProcess SearchArray = new SuffixFileProcess();
//        SearchArray.OpenInputFile(var.getSearchFileName());
//        var.setSearchRNA(SearchArray.ReadFile(var.getNucleotide(), var.getSearchRNA(),1));
//        var.setNoOfSearchPatterns(SearchArray.BuildIntegerArray(var.getSearchRNA())); 
//        
//        //Reading the length of SearchRNA
//        for(int x = 0; x < var.getNoOfSearchPatterns() ; x++)
//        {
//                    var.setLengthOfEachRNA(var.getLengthOfEachRNA(x));
//        }  
//        
//        //Searching the RNA
//        System.out.println ("Searching Pattern in the database....");
//        SuffixIntArray searchOnly = new SuffixIntArray(); 
//        for(int i =0; i <= var.getNoOfSearchPatterns(); i++)
//        {
//           // long startTime = System.nanoTime();
//            if (var.getNucleotide() == 1)
//                var.setOutputString(searchOnly.search(var.getSearchRNA(),var.getLengthOfEachRNA(i),i,var.getSuffixNucleotide(),var.getInputRNANucleotide(),var.getSuffixArrayIndexIteratorNucleotide()));
//            if (var.getNucleotide() == 0)
//                var.setOutputString(searchOnly.search(var.getSearchRNA(),var.getLengthOfEachRNA(i),i,var.getSuffixStructure(),var.getInputRNANucleotide(),var.getSuffixArrayIndexIteratorStructure()));
//           // long endTime = System.nanoTime();
//           // long duration = (endTime - startTime); 
//           // System.out.print(duration);
//           System.out.println("OutputString : " +var.getOutputString());
//        }     
//        
//        
//        // RNA Comparison
//        
//        System.out.println("Comparing RNAs......");
//        ReadRNACSV rnacsv = new ReadRNACSV();
//        var.setRNANames(rnacsv.ReadRNANames(var.getRNANames())); 
//        CompareRNA rnacmp = new CompareRNA();
//        var.setCompareRNANames(rnacmp.GetCompareRNANames(var.getCompareRNAString(), var.getNumOfCompareRNAs(), var.getCompareRNANames()));
//        var.setCompareRNANums(rnacmp.GetCompareRNAs(var.getCompareRNANames(), var.getCompareRNANums(), var.getNumOfCompareRNAs(),var.getRNANames(), var.getFileNumber() ));
//        var.setCompareRNAs(rnacmp.ConvertCompareRNAs(var.getCompareRNANums(), var.getCompareRNAs(), var.getNumOfCompareRNAs(), var.getCompareWithNucleotide(), var.getConcatenatedFileName(), var.getInputRNANucleotide(),var.getInputRNAStructure()));
//       // long startTime = System.nanoTime(); // Timing the comparison method 
//        var.setLengthOfCompareRNAs(rnacmp.FindLengthOfComapreRNAs(var.getCompareRNAs(), var.getNumOfCompareRNAs(), var.getLengthOfCompareRNAs()));
//        var.setSmallestRNAIndex(rnacmp.FindSmallestRNA(var.getSmallestRNAIndex(), var.getLengthOfCompareRNAs(), var.getNumOfCompareRNAs() ));
//        var.setLengthOfSmallestRNA(rnacmp.FindLengthOfSmallestRNA(var.getSmallestRNAIndex(), var.getLengthOfSmallestRNA(), var.getLengthOfCompareRNAs()));
//        var.setSearchCompareRNA(rnacmp.SearchCompareRNA(var.getSearchCompareRNA(), var.getCompareRNAs(), var.getSmallestRNAIndex()));
//        var.setOptimalLength(rnacmp.Compare(var.getSearchCompareRNA(), var.getLengthOfSmallestRNA(), var.getCompareRNANums(), var.getCompareWithNucleotide(), var.getSuffixNucleotide(), var.getSuffixStructure(), var.getInputRNAStructure(), var.getInputRNANucleotide(),var.getSuffixArrayIndexIteratorNucleotide(),var.getSuffixArrayIndexIteratorStructure(), var.getMatchingRNAs(), var.getNumOfCompareRNAs())); 
//       /* long endTime = System.nanoTime();
//        long duration = (endTime - startTime);
//        System.out.println("Time for Comparison :" +duration); */
//        var.setOptimalPattern(rnacmp.FindOptimalPattern(var.getOptimalPattern(), var.getOptimalLength(), var.getSearchCompareRNA(), var.getSuffixNucleotide(), var.getSuffixStructure(), var.getInputRNAStructure(), var.getInputRNANucleotide(),var.getSuffixArrayIndexIteratorNucleotide(), var.getSuffixArrayIndexIteratorStructure(), var.getCompareWithNucleotide(), var.getCompareRNANums(), var.getNumOfCompareRNAs(), var.getLengthOfSmallestRNA()));
//        var.setCompareOutputString(rnacmp.GeneratesetCompareOutputString(var.getCompareOutputString(), var.getOptimalPattern(), var.getSuffixNucleotide(), var.getSuffixStructure(), var.getInputRNAStructure(), var.getInputRNANucleotide(), var.getSuffixArrayIndexIteratorNucleotide(), var.getSuffixArrayIndexIteratorStructure(), var.getOptimalLength(), var.getCompareWithNucleotide(),var.getCompareRNANums(),var.getNumOfCompareRNAs())); 
//      
//      
//        //Comparison Test class o generate comparison groups
//       /* System.out.println("Comaprison Test.....");
//        ComparisonTestClass cmpTestClass = new ComparisonTestClass();
//        cmpTestClass.GetCompareRNAs(var.getSuffixNucleotide(), var.getSuffixArrayIndexIteratorNucleotide(), var.getInputRNANucleotide(), var.getSuffixArrayIndexIteratorNucleotide());  */
    }
}