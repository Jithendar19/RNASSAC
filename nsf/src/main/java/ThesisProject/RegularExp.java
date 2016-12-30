/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThesisProject;

/**
 *
 * @author fatma124
 */
import dk.brics.automaton.Automaton;
import dk.brics.automaton.AutomatonMatcher;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;
import edu.project.nsf.RegularExpPojo;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.StringReader;
//import java.nio.MappedByteBuffer;
//import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
//import java.util.Scanner;
//import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
//import javax.swing.text.JTextComponent;
////////////////////////////////////////////////////////////////////////////////

//import org.apache.tomcat.util.http.fileupload.IOUtils;


public class RegularExp  {
	
	private static RegularExpPojo pojo=new RegularExpPojo();

    private RegExp_Info info;
    private Automaton auto;
    private BufferedReader br;
    private FileReader reader;
//  arraylists for the words of the document.
    private Document doc; //to save the contents of the txt file inside
    private ArrayList<String> lines = new ArrayList(); //saves all the line of the document
    private String text;
//  Data members for the index table
    private ArrayList<Integer> General_subsets;
    private ArrayList<String> Wordsets=new ArrayList();
    private ArrayList<Integer> Locationsets=new ArrayList();
    private ReadWriteToList RWtoList= new ReadWriteToList();
    private int fileWords=0;
    private int listWords=0;
    private boolean empty_list=true;
    private Date d1=null,d2=null;
    private String method="";
    //logFile
    private File logFile = null;
    FileInputStream inputStream = null;
    Scanner sc = null;

    
    
    public String highlight_info(int i,String pattern) throws BadLocationException{
      //  removehighlight();
       // Highlighter hilite=jTextArea1.getHighlighter();
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("");
    	
//        
    	System.out.println("highLight_info was called.");
        for (String st : info.getWords()){
            int pos=0;
      
            while((pos=text.indexOf(st,pos))>=0){
                sb.append(pos+"#");
                sb.append(pos+st.length()+"#");
                pos+=st.length();
            }
        }
        fileWords=0;
        listWords=0;
        if(i==1){
            fileWords=info.getFileWordsNum();
            System.out.println("fileWords"+fileWords);
//            listWords=0;
        }
        else if(i==2){
//            fileWords=0;
            listWords=info.getFileWordsNum()+info.getListWordsNum();
            sb.append(listWords+"#");
            String Display="";
                for(String l:info.getWords())
                    Display=Display+l+"\n";
                System.out.println("Display: "+Display);
            
            //ListWords.setVisible(true);
        }
        else if(i==3){
            fileWords=info.getFileWordsNum();
            listWords=info.getListWordsNum();
            System.out.println("fileWords:"+fileWords);
            System.out.println("listWords:" + listWords);
        }
        int a=fileWords+listWords;
        sb.append(a+"#");
        sb.append(fileWords+"#");
        sb.append(listWords+"#");
       
        String output = sb.toString();
        
        //System.out.println(output);
        return output;
    }
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
    public void collectInfo_FromFile(RegExp pattern,boolean sub) throws IOException{
//       
//   	
            ArrayList<String> word=new ArrayList<>();//words match the RE.
            ArrayList<Integer> locations = new ArrayList<>();//locations of words match the RE.
            ArrayList<String> checked_words = new ArrayList<>(); //saves the words that has been checked before
            //if General_subsets are not empty, then set checked_words to all words for RegExp in the General_subsets
            
            System.out.println("collectInfo_FromFile was called.");
            
            if(!info.getSubsets().isEmpty()){
              for(String w:info.getWords())
            	  
                    checked_words.add(w);

            }
//       
            
            auto=pattern.toAutomaton();
            String token;
            int totalWords=0;// the total number of words matched
            RunAutomaton runAuto=new RunAutomaton(auto);// a transition table is created which makes the run method faster in return of a higher memory usage
            AutomatonMatcher autoMatch=runAuto.newMatcher(text);
            while(autoMatch.find()){
            	
                token=autoMatch.group();
                if(!checked_words.contains(token)&&!word.contains(token)){
                    //checked_words.add(token);
                    word.add(token) ;//words match the RE. The word is stored only one time
                 }   
                if(!checked_words.contains(token)){
                    locations.add(autoMatch.start()) ;
                    totalWords+=1;  
                }
                
            }
            

            System.out.println("wordTextField.setText"+(Integer.toString(totalWords)));

            if(info.getSubsets().isEmpty()){

                info.setLocations(locations);
                info.setFileWordsNum(totalWords);
                info.setWords(word);
                String Display="";
                for(String l:info.getWords())
                    Display=Display+l+"\n";
                System.out.println("FileWords.setText: "+(Display));
                

            }    
            else{
                info.setTag(1);
                info.setFileWordsNum(totalWords);
                info.setLocations(locations);
                String Display="";
                for(String l:info.getWords())
                    Display=Display+l+"\n";
                System.out.println("ListWords.setText: "+(Display));
                Display="";
                for(String l:word)
                    Display=Display+l+"\n";
                System.out.println("FileWords.setText"+(Display));
                word.addAll(info.getWords());
                info.setWords(word);
            }
               
            
    }
////////////////////////////////////////////////////////////////////////////////
    public String search(RegExp pattern,String patt) throws FileNotFoundException, BadLocationException, IOException, ClassNotFoundException{
    	System.out.println("search is called here");
    	
    	String output="";
    	
    	method="";
        // clear all the lists
        //ListWords.setText("");
        //FileWords.setText("");
        auto=pattern.toAutomaton();
//      check if the list(/the list file) is empty(true: collect the RegExp_Info from the Txt file and save it in to the RegExp_List. false:check for equvalence and subsets.)
//        
        if(empty_list){
           System.out.println("enter search:empty list:");
            //collect info from txt file, show them in the text , and save the info into the data member "info"
            General_subsets=new ArrayList();
            info=new RegExp_Info();
            info.setRegExp(pattern);
            info.setListWordsNum(0);
            info.setTag(0);
            info.setSubsets(General_subsets );
            method+=" FILE,";

            collectInfo_FromFile(pattern,false);

            output=highlight_info(1,patt);

            //add the info of the RE to the list
            RWtoList.addToReg_List(info); 
  
            empty_list=false;

        }
        else{// if one or more REs are stored in the list(index table), then check for equivalence and subsets before accesing the Txt file.
           
            if(RWtoList.checkEquivalence(pattern)){
                info=RWtoList.getReg_info();
                
                method+=" EQU,";

                output=highlight_info(2,patt);
            }
            else
            {
                
                General_subsets=new ArrayList();
                Wordsets=new ArrayList();
                Locationsets=new ArrayList();
            
                int all_found=RWtoList.checkSubset(pattern);
                if(all_found!=0){
                    auto= RWtoList.auto;
             
                    //save subsets words
                    info=RWtoList.getReg_info();
                    method+=" SUB,";
               
                    if(all_found==1){
                        method+=" FILE,";
                        collectInfo_FromFile(pattern,true);
                    }
                        
                   
                    output=highlight_info(3,patt);
                   
                    RWtoList.addToReg_List(info);

                }
                else //search the txt file for the info
                {

                    General_subsets=new ArrayList();

                    General_subsets=new ArrayList();
                    info=new RegExp_Info();
                    info.setRegExp(pattern);
                    info.setListWordsNum(0);
                    info.setTag(0);
                    info.setSubsets(General_subsets );
                    method+=" FILE,";
                    collectInfo_FromFile(pattern,false);

                    output=highlight_info(1,patt);
                    RWtoList.addToReg_List(info);

                }
            }

        }
        
        System.out.println(output);
		return output;
 
       
            
        
    }
    //////////////////////////////////////////////////////////////////////////
//    public void display_list(){
//        //display the contents of the list. just for checking purposes
//        for (RegExp_Info st : RWtoList.getReg_List()) 
//           {
//               //display infomation for all the regular expressions stored in the Reg_List
//               //first the Reg Exp
//               System.out.println("RegExp is:");
//               System.out.println(st.getRegExp());
//               //second the words
//               System.out.println("words are:");
//               for (String w : st.getWords())
//                   System.out.println(w);
////               //third their locations
//               System.out.println("locations are:");
//               for (int l : st.getLocations())
//                   System.out.println(l);
//               //fourth number of words
//               System.out.println("Number of words is:");
//               System.out.println(st.getWords());
//               //fifth 
//               System.out.println("tag is:");
//               System.out.println(st.getTag());
//               //subsets
//               System.out.println("subsets are:");
//               if((st.getSubsets()!=null))
//                 if(!(st.getSubsets().isEmpty()))
//                    for (int l : st.getSubsets())
//                   System.out.println(l);
//           }
//    }
//////////////////////////////////////////////////////////////////////////////    

    public String openFile(String filename) {//GEN-FIRST:event_openButtonActionPerformed
        // TODO add your handling code here:
        String file="";
    	
        
        file=filename;
        
        System.out.println("opening file"+ file);
        
        System.out.println("openButtonActionPerformed is called here");
        
        try{
        	
        	inputStream = new FileInputStream(file);
            sc = new Scanner(inputStream, "UTF-8");
            //Scanner sc=new Scanner(file);
            //reader=new FileReader(file);//sc.next());
            
            
           // br =new BufferedReader(reader);
            
            System.out.println("openedfile: "+ file);
            //jTextArea1.read(br, null);
            //jTextArea1.requestFocus();
            //doc=jTextArea1.getDocument();
            
            text=""; int count = 0;
            while(sc.hasNextLine()){
            	text+=sc.nextLine()+"<br/>";
            	//System.out.println(count++);
            }
//            while(br.ready()){
//            	
//            	text+=br.readLine()+"<br/>";
//            	
//            }
//            
            
//            br.close();
            
            empty_list=RWtoList.checkEmpty();
            if(empty_list)
                System.out.println("The List Is Empty");
            else 
            	System.out.println("The List Is Not Empty");
        }
        catch(IOException | ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,e);
            
        }
		return text;
       
    }//GEN-LAST:event_openButtonActionPerformed
//////////////////////////////////////////////////////////////////////////////////////
    private void writeToLogFile(String patt) throws IOException{
        File file = new File("logfile.txt");
        FileWriter fw= new FileWriter(file.getAbsoluteFile(),true); //true = append 
        BufferedWriter bw= new BufferedWriter(fw);
        String newline = System.getProperty("line.separator");// if file doesnt exists, then create it
	if (logFile.length()==0) {//if file doesn't exist
            //file.createNewFile();
            bw.write("RegExp.\tStart_time\tEnd_time\tSearch_time\tMethod\tW_inFile\tW_inList\t List%");
            bw.write(newline);
            bw.write("==================================================================================================================");
            bw.write(newline);
	} 
        //calculate the search time and store it to the log file
        String start = new SimpleDateFormat("hh:mm:ss:SSS").format(d1);
        String end = new SimpleDateFormat("hh:mm:ss:SSS").format(d2);
        long diff = d2.getTime() - d1.getTime();
        String searchTime = new SimpleDateFormat("mm:ss:SSS").format(diff);
        double percentage=((double)listWords/(fileWords+listWords))*100;
//        String percent=Double.toString(percentage);
        bw.write(patt+"\t"+start+"\t"+end+"\t"+searchTime+"\t"+method+"\t"+fileWords+"\t"+listWords+"\t"+String.format("%.3g%n", percentage));
        bw.write(newline);
        bw.close();         
    }
    
    ///////////////////////////////////////////////////////////////////////////////
    public String searchButtonActionPerformed(String patt) {//GEN-FIRST:event_searchButtonActionPerformed
      // try {
            // TODO add your handling code here:
           //FileReader file= new FileReader("src/main/resources/a/Regular_Expression.txt");
           // BufferedReader buf=new BufferedReader(file);
           String output = new String("");
          
          	
           // while((patt=buf.readLine())!=null){
            	
                System.out.println("RegularExpression is:"+patt);
                RegExp reg= new RegExp(patt);
                try {
                   // d1=new Date(); 
                
                   output=search(reg,patt);
                   // d2=new Date(); 
                    //logFile = new File("src/main/resources/a/logfile.txt");
                    //fw= new FileWriter(logFile.getAbsoluteFile(),true); //true = append 
                    //bw= new BufferedWriter(fw);
                   // writeToLogFile(patt);
                   // RWtoList.saveToFile();
                    
                } catch (BadLocationException | IOException | ClassNotFoundException ex) {
                    Logger.getLogger(RegularExp.class.getName()).log(Level.SEVERE, null, ex);
                }
           // }
           // buf.close();
      //  } catch (IOException ex) {
       //         Logger.getLogger(RegularExp.class.getName()).log(Level.SEVERE, null, ex);
      //      }
                System.out.println(output);
				return output;
        
    }//GEN-LAST:event_searchButtonActionPerformed

    public String SaveList() {//GEN-FIRST:event_SaveListActionPerformed
       
        RWtoList.saveToFile();
        return("The List Has Been Stored Into The File");
		
    	
        
    	
    }//GEN-LAST:event_SaveListActionPerformed

    
    /*public static void main(String args[]) throws IOException {
     
    	read rd=new read();
    	rd.readFile();
    	
    	RegularExp r = new RegularExp();
		String output=new String();
		
		StringBuffer sb = new StringBuffer(r.openFile("Input RNA.txt"));
		
		for(int m=0;m<3;m++){
		String pattern=rd.array[m];
		long startTime = System.nanoTime();	
		output=r.searchButtonActionPerformed(pattern);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		long elapsedTime = (duration/1000000);
		
		System.out.println("Finding matches took: " + elapsedTime + " milliseconds");
		
		String[] parts = output.split("#");
		int k=Integer.parseInt(parts[parts.length-3]);
		
		int count=0;
		for(int i=0;i<(k*2);i++)
		{
			
		System.out.println(parts[i]);
		if(i % 2==0){sb.insert(Integer.parseInt(parts[i])+count, "<font size=\"3\" color=\"red\">");count=count+27;}
		else {sb.insert(Integer.parseInt(parts[i])+count, "</font>");count=count+7;}
		}
		
		
		System.out.println(sb);
		System.out.println("text"+sb);
		System.out.println("Finding matches took: " + elapsedTime + " milliseconds");
		 System.out.println("noOfMatches: "+ k);
		
    	
		}*/

        	
     
       
    //}
    
    

  
}
