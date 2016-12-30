package edu.nsf.blast;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;

public class Blast {

	public static void main(String[] args) {
		
		ReadDatabase rd=null;
		QueryFileProcess fp=null;
		
		try {
		 rd=new ReadDatabase();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			 fp=new QueryFileProcess(200);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ProcessDatabase pd=new ProcessDatabase(rd.getSequencesArray(), rd.getSequenceDescriptors());
		
		
		
		MatchKmers mr = new MatchKmers(rd.getSequencesArray(),rd.getSequenceDescriptors(),pd.getProcessedDatabase(),fp.getQueryKmers(),pd.getKmerSize(),fp.getQuerySequence(),fp.getQueryDescription());
		
		
		mr.CreateMatches();
		mr.findDatabaseMatch();

	}

}
