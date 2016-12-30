package ThesisProject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import edu.project.nsf.RegularExpPojo;

public class Matches {

	private static int[] intArray = new int[50000];
	private static String[] nucleotide = new String[50000];
	private static String[] structure = new String[50000];
	private static int[] index = new int[50000];

	public static void main(String[] args) {

		read rd = new read();
		try {
			rd.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			PrintWriter writer;
			writer = new PrintWriter("/opt/NSF_Data_Files/matches.txt", "UTF-8");

			RegularExp r = new RegularExp();

			System.out.println("Reading database ..... ");
			StringBuffer sb = new StringBuffer(r.openFile("Input RNA.txt"));

			System.out.println("Reading database successful.");

			writer.println("PatternNo,NoOfMatches,ElapsedTime(NanoSecond)");

			for (int m = 0; m < 100; m++) {
				System.out.println(m);
				if (m != 58  && m != 72 && m != 74){//(m != 58 && m != 65 && m != 66 && m != 72 && m != 74) {
					System.out.println(m);
					String output = new String();
					String pattern = rd.array[m];

					// System.out.println("database loaded into memory. starting
					// timer.....");
					long startTime = System.nanoTime();
					// System.out.println("searching for pattern");
					output = r.searchButtonActionPerformed(pattern);
					// System.out.println("pattern searched completed stopping
					// timer.");
					long endTime = System.nanoTime();
					long duration = (endTime - startTime);
					long elapsedTime = (duration);

					// System.out.println("spliting the search result");
					String[] parts = output.split("#");
					int k = Integer.parseInt(parts[parts.length - 3]);

					/*
					 * System.out.println("the loop begins now: " + k); int
					 * count=0;
					 * 
					 * for(int i=0;i<(k*2);i++) {
					 * 
					 * index[i]=Arrays.binarySearch(intArray,Integer.parseInt(
					 * parts[i]));
					 * 
					 * //System.out.println(parts[i]); if(i %
					 * 2==0){sb.insert(Integer.parseInt(parts[i])+count,
					 * "<font size=\"3\" color=\"red\">");count=count+27;} else
					 * {sb.insert(Integer.parseInt(parts[i])+count,
					 * "</font>");count=count+7;} }
					 */
					// System.out.println("loop terminated adding model
					// attributes .....");

					// System.out.println(sb);
					// System.out.println("text"+sb);
					// System.out.println("Finding matches took: " + elapsedTime
					// + " milliseconds");
					// System.out.println("noOfMatches: "+ k);

					writer.println((m+1) + "," + k + "," + elapsedTime);

				}
				
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
