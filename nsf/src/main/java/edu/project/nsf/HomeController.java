package edu.project.nsf;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

//import org.apache.logging.log4j.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ThesisProject.RegularExp;
import edu.project.suffixSearch.CompareRNA;
import edu.project.suffixSearch.NSFRNA;
import edu.project.suffixSearch.RNAVariables;
import edu.project.suffixSearch.SuffixFileProcess;
import edu.project.suffixSearch.SuffixIntArray;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	//final static Logger Log = LogManager.getLogger(HomeController.class.getName());
	
	RegularExp r = new RegularExp();
	String path = "/opt/NSF_Data_Files/";
	String searchedPath = "/opt/NSF_Searched_Files/";
	String inputRNA = path + "Input RNA.txt";
	String dataBase= r.openFile(inputRNA);
	
	// ************************************************************************************************************************//
	//  object of Pojo class RnaVariables is intialized and passed as a parameter to the constructor of NSFRNA object creation.//
	// ************************************************************************************************************************//
	
	RNAVariables var= new RNAVariables();
	NSFRNA a = new NSFRNA(var);

	// **********************************************************************//
	// Loading the array with filename, nucleotide and structure into memory.//
	// **********************************************************************//
	ReadCSV readArray = new ReadCSV();
	String[][] varna = readArray.run();

	// *****************************************************************************//
	// servet returns the home page when the /nsf request is made to the controller.
	// *****************************************************************************//
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		System.out.println("IP Address: " + request.getRemoteAddr() + " has access the Home Page.");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}
	// ************************************************************************************************************//
	//once user enters the pattern file after file validation user is redirected to verna with list of matches
	// ************************************************************************************************************//
	@RequestMapping(value = "/verna", method = RequestMethod.POST)
	public String verna(Locale locale, Model model, @RequestParam("name") String name, @RequestParam("file") MultipartFile file, @RequestParam("searchType") int searchType, HttpServletRequest request) {
		System.out.println("IP Address: " + request.getRemoteAddr() + " has access SearchResults Page \"/verna\"");
		
		var.setNucleotide(searchType);
		
		/*This list hold the  info like rnaName, nucleotide, 
		starting indices of the pattern in the RNA  with each matching patterns.*/
		ArrayList<ArrayList<String>> structureList = new ArrayList<ArrayList<String>>();
		ArrayList<String> structure = new ArrayList<String>();
		ArrayList<String> fileName = new ArrayList<String>();
		ArrayList<String> nucleotide = new ArrayList<String>();
		ArrayList<String> indicesForPattern = new ArrayList<String>();
		Long startTime = (long) 0, endTime = (long) 0 , duration = (long) 0;

		// If the file is empty  then user is returned to upload page with error msg.
		System.out.println("Reading File");
		if (!file.isEmpty()) {
			try {
				System.out.println("File is not empty.");
				
				byte[] bytes = file.getBytes();
				System.out.println("File was stored as bytes.");
				
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(searchedPath + name)));
				System.out.println("BufforedOutputStream was created with the file name: " + searchedPath + name);
				
				stream.write(bytes);
				System.out.println("Submitted search file was copied and written to new file with timestamp.");
				
				stream.close();
				System.out.println(name + " has been closed.");
				
				var.setSearchFileName(searchedPath + name);
				System.out.println(name + " has been instatiated in the RNAVariables class");

				// Converting SearchRNA to new format
				System.out.println("Converting SearchRNA to new format.");
				SuffixFileProcess SearchArray = new SuffixFileProcess();
				SearchArray.OpenInputFile(var.getSearchFileName());
				var.setSearchRNA(SearchArray.ReadFile(var.getNucleotide(), var.getSearchRNA(), 1));
				var.setNoOfSearchPatterns(SearchArray.BuildIntegerArray(var.getSearchRNA()));
				
				
				// Reading the length of SearchRNA
				System.out.println("Reading the length of SeearchRNA.");
				for (int x = 0; x < var.getNoOfSearchPatterns(); x++) {
					var.setLengthOfEachRNA(var.getLengthOfEachRNA(x));
				}
				
				// Searching the RNA
				System.out.println("Searching Pattern in the database. ");
				//SuffixIntArray searchOnly = new SuffixIntArray();
				BinarySearch searchOnly = new BinarySearch();
				for (int k = 0; k <= var.getNoOfSearchPatterns(); k++) {
					
					 startTime = System.nanoTime();
					
					//Search with Nucleotide
					if (var.getNucleotide() == 1) {
						var.setOutputString(searchOnly.search(var.getSearchRNA(), var.getLengthOfEachRNA(k), k,
								var.getSuffixNucleotide(), var.getInputRNANucleotide(),
								var.getSuffixArrayIndexIteratorNucleotide()));
					}
					//Search with Structure
					if (var.getNucleotide() == 0) {
						var.setOutputString(searchOnly.search(var.getSearchRNA(), var.getLengthOfEachRNA(k), k,
								var.getSuffixStructure(), var.getInputRNAStructure(),
								var.getSuffixArrayIndexIteratorStructure()));
					}
					 endTime = System.nanoTime();
					 duration = (endTime - startTime);
					 System.out.println("Duration of search:" + duration + "(in nanoseconds).");
					 System.out.println(var.getOutputString());
				}
				
				System.out.println("Search is completed.");
				//Once the search is complete an output string is split and values are assigned to the array.
				String output = var.getOutputString();
				if (output.equals("#*0$")) {
					System.out.println("No matches found");
					model.addAttribute("msg", "No matches found. Please try another file or change the Search Type.");
					return "upload";
				}
				else {
				
					System.out.println("spliting THE search result");
					
					//System.out.println(output);
					output = output.substring(0,output.lastIndexOf("*"));
					//System.out.println(output);
					String[] parts = output.split("#");
					
	
					int noOfMatches = parts.length - 1;
	
					System.out.println("the loop begins now: " + noOfMatches / 2);
	
					for (int i = 0; i < noOfMatches; i++) {
	
						if (i % 2 == 0) {
	
							structure.add(varna[(Integer.parseInt(parts[i]))][2]);
							// structure.add("..........................................................");
							// structure.add("(((((.(((((....))))).(((((.....))))).(((((.....))))).)))))");
							// structure.add(".......((((((((......)))).((((((((......))))))))..))))....");
							//System.out.println(Integer.parseInt(parts[i]));
							
							fileName.add(varna[(Integer.parseInt(parts[i]))][0]);
	
							// fileName.add("RNA2");
							// fileName.add("RNA3");
							// fileName.add("RNA4");
	
							nucleotide.add(varna[(Integer.parseInt(parts[i]))][1]);
	
						}
	
						else {
							indicesForPattern.add(Integer.parseInt(parts[i]) + "-" + parts[parts.length -1 ]);
							//System.out.println((Integer.parseInt(parts[i])));
						}
						
					}
				
					//System.out.println(indicesForPattern);
					System.out.println("loop terminated  adding model attributes .....");
	
					model.addAttribute("msgs", "Finding matches took: " + duration + " nanoseconds");
					model.addAttribute("noOfMatches", parts.length);
					model.addAttribute("RegularExpPojo", new RegularExpPojo());
	
					System.out.println("Finding matches took: " + duration + " nanoseconds");
	
					structureList.add(0, structure);
					structureList.add(1, fileName);
					structureList.add(2, indicesForPattern);
					//System.out.println(structureList);
					model.addAttribute("nucleotide", nucleotide);
					model.addAttribute("defaultResult", structureList);
					//model.addAttribute("HighLight", indicesForPattern);
					model.addAttribute("patternLength", parts[parts.length - 1]);
					model.addAttribute("elapsedTime", duration);
					
					return "verna";
				}
			} catch (Exception e) {
				model.addAttribute("msg", "You failed to upload " + name + " => " + e.getMessage());
				return "verna";
			}

		} else {
			
			model.addAttribute("msg", "You failed to upload " + name + " because the file was empty.");
			return "upload";
		}
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String provideUploadInfo(Model model, HttpServletRequest request) {
		System.out.println("IP Address: " + request.getRemoteAddr() + " has accessed the Search Page.");
		return "upload";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(Model model, HttpServletRequest request) {
		System.out.println("IP Address: " + request.getRemoteAddr() + " has accessed the Contact Page.");
		return "contact";
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Model model, HttpServletRequest request) {
		System.out.println("IP Address: " + request.getRemoteAddr() + " has accessed the About Page.");
		return "about";
	}
	
	@RequestMapping(value = "/team", method = RequestMethod.GET)
	public String team(Model model, HttpServletRequest request) {
		System.out.println("IP Address: " + request.getRemoteAddr() + " has accessed the Team Page.");
		return "location";
	}

	@RequestMapping(value = "/uploaded", method = RequestMethod.POST)
	public String handleFileUpload(@ModelAttribute RegularExpPojo pojo, Model model, @RequestParam("name") String name,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		System.out.println("IP Address: " + request.getRemoteAddr() + " has accessed the Regular Expression Search Results Page.");
		ReadFile test = new ReadFile();

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
				stream.write(bytes);
				stream.close();

				model.addAttribute("text", dataBase);
				model.addAttribute("msg", name);
				model.addAttribute("RegularExpPojo", new RegularExpPojo());

				return "RegularExp";

			} catch (Exception e) {
				model.addAttribute("msg", "You failed to upload " + name + " => " + e.getMessage());
				model.addAttribute("RegularExpPojo", new RegularExpPojo());
				model.addAttribute("text", test.read(name));
				return "upload";
			}
		} else {
			model.addAttribute("msg", "You failed to upload " + name + " because the file was empty.");
			return "upload";
		}
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String regularExpSearch(@ModelAttribute RegularExpPojo pojo, Locale locale, Model model,
			@RequestParam("pattern") String pattern, @RequestParam("foundOnFile") boolean foundOnFile,
			@RequestParam("path") String filename, HttpServletRequest request) {
		System.out.println("IP Address: " + request.getRemoteAddr() + " has accessed the Suffix Array Search Results Page.");
		// RegularExp r = new RegularExp();
		String output = new String();

		System.out.println("Reading database ..... ");
		StringBuffer sb = new StringBuffer(dataBase);

		if (pattern == "") {
			model.addAttribute("text", sb);
			model.addAttribute("msgs", "Empty search not a valid search!");
			model.addAttribute("msg", filename);
			model.addAttribute("RegularExpPojo", new RegularExpPojo());
			return "RegularExp";
		}

		System.out.println("database loaded into memory. starting timer.....");
		long startTime = System.nanoTime();
		System.out.println("searching for pattern");

		output = r.searchButtonActionPerformed(pattern);
		System.out.println("pattern searched completed stopping timer.");
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		long elapsedTime = (duration);
		System.out.println("spliting the search result");
		String[] parts = output.split("#");
		int k = Integer.parseInt(parts[parts.length - 3]);

		System.out.println("the loop begins now: " + k);
		int count = 0;

		for (int i = 0; i < (k * 2); i++) {

			// System.out.println(parts[i]);
			if (i % 2 == 0) {
				sb.insert(Integer.parseInt(parts[i]) + count, "<font size=\"3\" color=\"red\">");
				count = count + 27;
			} else {
				sb.insert(Integer.parseInt(parts[i]) + count, "</font>");
				count = count + 7;
			}
		}

		System.out.println("loop terminated  adding model attributes .....");
		if (foundOnFile)
			model.addAttribute("status", r.SaveList());
		// System.out.println(sb);
		model.addAttribute("text", sb);
		model.addAttribute("msgs", "Finding matches took: " + elapsedTime + " nanoseconds");
		model.addAttribute("msg", filename);
		model.addAttribute("noOfMatches", k);
		model.addAttribute("pattern", pattern);
		model.addAttribute("RegularExpPojo", new RegularExpPojo());

		System.out.println("Finding matches took: " + elapsedTime + " nanoseconds");

		return "RegularExp";
	}

	@RequestMapping(value = "/addRNA", method = RequestMethod.POST)
	public String handleRNAUpload(@ModelAttribute RegularExpPojo pojo, Model model, @RequestParam("name") String name,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("/opt/NSF_Data_Files/" + name)));
				stream.write(bytes);
				stream.close();

				model.addAttribute("msg", "The submission has been added as " + name);
				model.addAttribute("RegularExpPojo", new RegularExpPojo());

				return "searchRNA";

			} catch (Exception e) {
				model.addAttribute("msg", "You failed to upload " + name + " => " + e.getMessage());
				model.addAttribute("RegularExpPojo", new RegularExpPojo());

				return "searchRNA";
			}
		} else {
			model.addAttribute("msg", "You failed to upload " + name + " because the file was empty.");
			return "uploadfile";
		}
	}

	@RequestMapping(value = "/RNASearch", method = RequestMethod.POST)
	public String SuffixSearch(@ModelAttribute RegularExpPojo pojo, Locale locale, Model model,
			@RequestParam("path") String filename, HttpServletRequest request) {

		return "RegularExp";
	}

	@RequestMapping(value = "/comparison", method = RequestMethod.GET)
	public String provideComparisonInfo(Model model, HttpServletRequest request) {
		System.out.println("IP Address: " + request.getRemoteAddr() + " has accessed the Comparison Page.");
		String[] fileNames = new String[varna.length];
		
		for (int i= 0; i<varna.length; i++){
			fileNames[i] = (varna[i][0]);
		}
		
		model.addAttribute("fileNames", varna);
		return "comparison";
	}
	
	@RequestMapping(value = "/comparisonResults", method = RequestMethod.POST)
	public String comparison(Locale locale, Model model, @RequestParam("results") String results, HttpServletRequest request) {
		System.out.println("IP Address: " + request.getRemoteAddr() + " has accessed the Comparison Results Page.");
		System.out.println("Files to be compared: " + results);
		
		int numRNAs = results.split(",").length;
		System.out.println("Number of Files to be compared: " + numRNAs);
		var.setCompareRNAString(results);
        var.setNumOfCompareRNAs(numRNAs);
       
		// RNA Comparison
      
      System.out.println("Comparing RNAs......");
      
      String[] fileNames = new String[varna.length];
		
		for (int i= 0; i<varna.length; i++){
			fileNames[i] = (varna[i][0]);
		}
		
      //ReadRNACSV rnacsv = new ReadRNACSV();
      var.setRNANames((fileNames)); 
      CompareRNA rnacmp = new CompareRNA();
      var.setCompareRNANames(rnacmp.GetCompareRNANames(var.getCompareRNAString(), var.getNumOfCompareRNAs(), var.getCompareRNANames()));
      var.setCompareRNANums(rnacmp.GetCompareRNAs(var.getCompareRNANames(), 
    		  										var.getCompareRNANums(), 
    		  										var.getNumOfCompareRNAs(),
    		  										var.getRNANames(), 
    		  										var.getFileNumber() ));
      
      var.setCompareRNAs(rnacmp.ConvertCompareRNAs(var.getCompareRNANums(), 
    		  										var.getCompareRNAs(), 
    		  										var.getNumOfCompareRNAs(), 
    		  										var.getCompareWithNucleotide(), 
    		  										var.getConcatenatedFileName(), 
    		  										var.getInputRNANucleotide(),
    		  										var.getInputRNAStructure()));
      
     // long startTime = System.nanoTime(); // Timing the comparison method 
      var.setLengthOfCompareRNAs(rnacmp.FindLengthOfComapreRNAs(var.getCompareRNAs(), 
    		  													var.getNumOfCompareRNAs(), 
    		  													var.getLengthOfCompareRNAs()));
      
      
      var.setSmallestRNAIndex(rnacmp.FindSmallestRNA(var.getSmallestRNAIndex(), 
    		  											var.getLengthOfCompareRNAs(), 
    		  											var.getNumOfCompareRNAs() ));
      
      var.setLengthOfSmallestRNA(rnacmp.FindLengthOfSmallestRNA(var.getSmallestRNAIndex(), 
    		  													var.getLengthOfSmallestRNA(), 
    		  													var.getLengthOfCompareRNAs()));
      
      var.setSearchCompareRNA(rnacmp.SearchCompareRNA(var.getSearchCompareRNA(), 
    		  											var.getCompareRNAs(), 
    		  											var.getSmallestRNAIndex()));
      
      System.out.println("Comparing Files");
      var.setOptimalLength(rnacmp.Compare(var.getSearchCompareRNA(), 
    		  								var.getLengthOfSmallestRNA(), 
    		  								var.getCompareRNANums(), 
    		  								var.getCompareWithNucleotide(), 
    		  								var.getSuffixNucleotide(), 
    		  								var.getSuffixStructure(), 
    		  								var.getInputRNAStructure(), 
    		  								var.getInputRNANucleotide(),
    		  								var.getSuffixArrayIndexIteratorNucleotide(),
    		  								var.getSuffixArrayIndexIteratorStructure(), 
    		  								var.getMatchingRNAs(), 
    		  								var.getNumOfCompareRNAs()));
      System.out.println("Compare has completed");
     /* long endTime = System.nanoTime();
      long duration = (endTime - startTime);
      System.out.println("Time for Comparison :" +duration); */
      var.setOptimalPattern(rnacmp.FindOptimalPattern(var.getOptimalPattern(), 
    		  											var.getOptimalLength(), 
    		  											var.getSearchCompareRNA(), 
    		  											var.getSuffixNucleotide(), 
    		  											var.getSuffixStructure(), 
    		  											var.getInputRNAStructure(), 
    		  											var.getInputRNANucleotide(),
    		  											var.getSuffixArrayIndexIteratorNucleotide(), 
    		  											var.getSuffixArrayIndexIteratorStructure(), 
    		  											var.getCompareWithNucleotide(), 
    		  											var.getCompareRNANums(), 
    		  											var.getNumOfCompareRNAs(), 
    		  											var.getLengthOfSmallestRNA()));
      
      var.setCompareOutputString(rnacmp.GeneratesetCompareOutputString(var.getCompareOutputString(), 
    		  															var.getOptimalPattern(), 
    		  															var.getSuffixNucleotide(), 
    		  															var.getSuffixStructure(), 
    		  															var.getInputRNAStructure(), 
    		  															var.getInputRNANucleotide(), 
    		  															var.getSuffixArrayIndexIteratorNucleotide(), 
    		  															var.getSuffixArrayIndexIteratorStructure(), 
    		  															var.getOptimalLength(), 
    		  															var.getCompareWithNucleotide(),
    		  															var.getCompareRNANums(),
    		  															var.getNumOfCompareRNAs())); 
    
  
		ArrayList<ArrayList<String>> structureList = new ArrayList<ArrayList<String>>();
		ArrayList<String> structure = new ArrayList<String>();
		ArrayList<String> fileName = new ArrayList<String>();
		ArrayList<String> nucleotide = new ArrayList<String>();
		ArrayList<Integer> indicesForPattern = new ArrayList<Integer>();
		
		String output = var.getCompareOutputString().substring(0, var.getCompareOutputString().lastIndexOf("*"));
		
		//System.out.println(output);
		output = output.substring(0,output.lastIndexOf("*"));
		//System.out.println(output);
		String[] parts = output.split("#");
		
		int noOfMatches = parts.length - 1;

		System.out.println("the loop begins now: " + noOfMatches / 2);

		for (int i = 0; i < noOfMatches; i++) {

			if (i % 2 == 0) {

				structure.add(varna[(Integer.parseInt(parts[i]))][2]);

				// structure.add("..........................................................");
				// structure.add("(((((.(((((....))))).(((((.....))))).(((((.....))))).)))))");
				// structure.add(".......((((((((......)))).((((((((......))))))))..))))....");
				//System.out.println(Integer.parseInt(parts[i]));
				
				fileName.add(varna[(Integer.parseInt(parts[i]))][0]);

				// fileName.add("RNA2");
				// fileName.add("RNA3");
				// fileName.add("RNA4");

				nucleotide.add(varna[(Integer.parseInt(parts[i]))][1]);

			}

			else {
				indicesForPattern.add(Integer.parseInt(parts[i]));
				//System.out.println((Integer.parseInt(parts[i])));
			}
			
		}
		System.out.println(indicesForPattern);
		System.out.println("loop terminated  adding model attributes .....");

		//model.addAttribute("msgs", "Finding matches took: " + elapsedTime + " nanoseconds");
		model.addAttribute("noOfMatches", parts.length);
		model.addAttribute("RegularExpPojo", new RegularExpPojo());

		//System.out.println("Finding matches took: " + elapsedTime + " nanoseconds");

		structureList.add(0, structure);
		structureList.add(1, fileName);
		model.addAttribute("nucleotide", nucleotide);
		model.addAttribute("defaultResult", structureList);
		model.addAttribute("HighLight", indicesForPattern);
		model.addAttribute("patternLength", parts[parts.length - 1]);
//		model.addAttribute("elapsedTime", elapsedTime);
		
		return "comparisonResults";
	}
}
