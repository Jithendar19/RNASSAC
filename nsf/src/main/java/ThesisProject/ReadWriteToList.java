package ThesisProject;


import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fatma124
 */
public class ReadWriteToList {

    private ArrayList<RegExp_Info> Reg_List= new ArrayList<>();
    private RegExp_Info Reg_info;
    private ArrayList<Integer> General_subsets;
    private ArrayList<String> Wordsets;
    private ArrayList<Integer>LocationSets;
    private int NumSetWords;
    public Automaton auto;
    
   
    //constructor
    ReadWriteToList() {
////          the object output stream writes the objects to the list file 
//        istream = new FileInputStream("Reg_List.txt");
//        objectOutputStream = new ObjectOutputStream(
//                                        new FileOutputStream("Reg_List.txt"));
////          the object input stream reads the objects back from the list file 
//        objectInputStream = new ObjectInputStream(
//                                        new FileInputStream("Reg_List.txt"));
       /**
        *  Reg_List: contains all regular expressions entered by user together with the locations of the words they match in the text file.
        */
        //Reg_List= new ArrayList<>();
       // wordsList= new ArrayList<>();
       // locationList= new ArrayList<>();
    }
////////////////////////////////////////////////////////////////////////////////

    public RegExp_Info getReg_info() {
        return Reg_info;
    }

    public ArrayList<String> getWordsets() {
        return Wordsets;
    }

    public ArrayList<Integer> getLocationSets() {
        return LocationSets;
    }
 
    public ArrayList<Integer> getGeneral_subsets() {
        return General_subsets;
    }

    public int getNumSetWords() {
        return NumSetWords;
    }
    
    
    public void setReg_List(ArrayList<RegExp_Info> Reg_List) {
        this.Reg_List = Reg_List;
    }

//    public List<String> getWordsList() {
//        return wordsList;
//    }
    
    ////////////////////////////////////////////////////////////////////////////
    public void addToReg_List(RegExp_Info Info) {
        this.Reg_List.add(Info);

    }
////////////////////////////////////////////////////////////////////////////////

    public ArrayList<RegExp_Info> getReg_List() {
        return Reg_List;
    }
    
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
//    check if the list stored in a file is empty
    public boolean checkEmpty() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = null;
        try{
            inputStream = new ObjectInputStream(new FileInputStream("/opt/NSF_Data_Files/RegExp_Info_List.txt"));
        }catch(IOException e){
            System.out.println("The file is Empty: " + e);
            return true;
            //System.exit(0);
        }
        System.out.println("The file is NOT Empty: " );
        //now read from the file nad check if the list is empty
        try{
            Reg_List = (ArrayList<RegExp_Info>)inputStream.readObject();
            inputStream.close();
        }catch(Exception e){
            System.out.println("There was an issue reading from the file: " + e);
            System.exit(0);
        } 
        //check empty list
        if(Reg_List.isEmpty()){System.out.println("The list is  Empty: " );  return true;}
        
        System.out.println("The list is NOT Empty: " ); 
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////
    //save the contents of the list in to the RegExp_Info_List.txt file
    public void saveToFile(){
        //creating the space in memory for the file
        ObjectOutputStream  outputStream = null;
 
        try{
            outputStream = new ObjectOutputStream(new FileOutputStream("/opt/NSF_Data_Files/RegExp_Info_List.txt"));
        }catch(IOException e){
            System.out.println("Could not open the file." + e);
            System.exit(0);
        }
 
        try{
            outputStream.writeObject(Reg_List);
            outputStream.close();
 
        }catch(IOException e){
            System.out.println("Writing error: " + e);
            System.exit(0);
        }
        System.out.println("The list has been written to the file.");
    }
//    //////////////////////////////////////////////////////////////////////////
//    Check if the Regular Expression is already in the list
    public boolean checkEquivalence(RegExp reg) {

        Automaton A = reg.toAutomaton();

        //read all the RegExp elements in the Reg_List one by one and determine which one is equivalent to the query. 
        for (RegExp_Info RE_info : Reg_List) {
         
//            System.out.println((RE_info.getRegExp()).toString());
            RegExp Exp=RE_info.getRegExp();
            Automaton B=Exp.toAutomaton();
            //A=B iff (A
            Automaton equiv=(A.intersection(B.complement())).union(A.complement().intersection(B));
            if(equiv.isEmpty()){
                System.out.println(reg.toString()+" is equiv. to "+Exp.toString());
                //retrieve all the information of Exp
                Reg_info=new RegExp_Info();
                Reg_info=RE_info;

//                System.out.println("end of :checkEquivalence");
                return true;
            }
        }
        return false;
    }
////////////////////////////////////////////////////////////////////////////////
    public int checkSubset(RegExp reg) {
        //is_empty=false;
        int is_subset=0;
        NumSetWords=0;
        General_subsets=new ArrayList();
        Wordsets=new ArrayList();
        LocationSets=new ArrayList();
        Automaton A = reg.toAutomaton();
//read all the RegExp elements in the Reg_List one by one and determine which one is subset of the query. 
        for (RegExp_Info RE_info : Reg_List) {
            RegExp Exp=RE_info.getRegExp();
            Automaton B=Exp.toAutomaton();
            
            if(B.subsetOf(A)){
                
                if(General_subsets.isEmpty()){
                    General_subsets.add(Reg_List.indexOf(RE_info));
                    
                   //extract the words and the locations of the subset
                    
                    for(String w:RE_info.getWords() ){
                        if(!Wordsets.contains(w)){
                            Wordsets.add(w);
                        }
                    }
//                    System.out.println("the following has been added to the list");
//                    for(String x:Wordsets ){
//                        
//                            System.out.println(x);
//                            
//                       
//                    }
                    for(Integer l:RE_info.getLocations() ){
                        if(!LocationSets.contains(l))
                            LocationSets.add(l);
                    }
                
//                    System.out.println(General_subsets.get(0));
                   // System.out.println("1: "+Exp.toString()+" is subset of "+reg.toString());
                    is_subset=1;
//                    //1)add the reg to the RexExp list
//                    RE_info.setRegExp(reg);
//                    //2)retrieve all the information of Exp
//                    info=RE_info; 
                }   
                else
                {
                    //check the general_subsets list for the subsets of B
                    if(RE_info.getTag()==1){
//                        boolean flag=false;
                        ArrayList<Integer> sets=RE_info.getSubsets();
                        //remove all the subsets of B from the general_subsets list, and then insert B instead.(together they form redundant values)
                        for (int c=0;c<sets.size();c++){
                            Integer obj=sets.get(c);
                            if(General_subsets.contains(obj)){
//                                flag=true;
                                //remove the element
                                General_subsets.remove(obj);
                            } 
                        } 
//                        if(flag){
                           General_subsets.add(Reg_List.indexOf(RE_info));
                           //extract the words and the locations of the subset
                        for(String w:RE_info.getWords() ){
                            if(!Wordsets.contains(w))
                                Wordsets.add(w);
                        }
//                        System.out.println("the following has been added to the list:::: 1");
//                    for(String x:Wordsets ){
//                        
//                            System.out.println(x);
//                            
//                       
//                    }
                        for(Integer l:RE_info.getLocations() ){
                            if(!LocationSets.contains(l))
                                LocationSets.add(l);
                        }
                         //  System.out.println("2: "+Exp.toString()+" is subset of "+reg.toString());
                           is_subset=1;
//                           //1)add the reg to the RexExp list
//                           RE_info.setRegExp(reg);
//                           //2)retrieve all the information of Exp
//                           info=RE_info; 
//                        }
                    }
                    else {
                        General_subsets.add(Reg_List.indexOf(RE_info));
                            //extract the words and the locations of the subset
                        for(String w:RE_info.getWords() ){
                            if(!Wordsets.contains(w))
                                Wordsets.add(w);
                        }
//                        System.out.println("the following has been added to the list::2");
//                    for(String x:Wordsets ){
//                        
//                            System.out.println(x);
//                            
//                       
//                    }
                        for(Integer l:RE_info.getLocations() ){
                            if(!LocationSets.contains(l))
                                LocationSets.add(l);
                        }
                       // System.out.println("3: "+Exp.toString()+" is subset of "+reg.toString());
                        is_subset=1;
//                        //1)add the reg to the RexExp list
//                        RE_info.setRegExp(reg);
//                        //2)retrieve all the information of Exp
//                        info=RE_info; 
                    }
                  
                }
               //modify A to be (A intersection of B compliment
                //A=A.intersection(B.complement());
                A=A.minus(B);
                A.minimize();
                A.removeDeadTransitions();
                //check if A now is empty, then no more seach to do,then all the words has been found, the text file is not searched.
                if(A.isEmpty()) {
                    //System.out.println("all SUB, none from the file");
                    is_subset=2;
                    //System.out.println(A);
                    break;
                }
            }
        }
        if(is_subset!=0){
            
//            System.out.println("----------------------------\nGeneral_subsets:");
//            for (int c=0;c<General_subsets.size();c++){ 
//                System.out.println("Reg Exp:");
//                System.out.println(Reg_List.get(General_subsets.get(c)).getRegExp().toString() );
//                System.out.println("Words are:");
//                for(String w:Reg_List.get(General_subsets.get(c)).getWords() )
//                {
//                    System.out.println(w);
//                }
//            }       
                    
//            //check all the general subsets of B for the duplicate values of words and locations, and eliminate duplicates
//            Wordsets=new ArrayList();
//            LocationSets=new ArrayList();
//            for (int c=0;c<General_subsets.size();c++){    
//                for(String w:Reg_List.get(General_subsets.get(c)).getWords() ){
//                    if(!Wordsets.contains(w))
//                        Wordsets.add(w);
//                }
//                for(Integer l:Reg_List.get(General_subsets.get(c)).getLocations() ){
//                    if(!LocationSets.contains(l))
//                        LocationSets.add(l);
//                }
                //number of words in the subsets.
                Reg_info=new RegExp_Info();
                Reg_info.setRegExp(reg);
                Reg_info.setTag(1);
                Reg_info.setWords(Wordsets);
                Reg_info.setLocations(LocationSets);
                Reg_info.setSubsets(General_subsets);
                Reg_info.setListWordsNum(LocationSets.size());
                //NumSetWords=LocationSets.size();
//                System.out.println("Number of Words in the subsets  =:"+Reg_info.getListWordsNum());
//                for(int s:Reg_info.getLocations())
//                    System.out.println("locations in the subsets  =:"+s);
                
//            }
            
        }
//        System.out.println("the following has been added to the list::final");
//                    for(String x:Wordsets ){
//                            System.out.println(x);
//                    }
        auto=A;
        return is_subset;
    }    
    ////////////////////////////////////////////////////////////////////////////
}
