/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThesisProject;

import dk.brics.automaton.RegExp;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author fatma124
 */


public class RegExp_Info implements Serializable {
    private String regExp;//to store all the equivalent REs.
    private ArrayList<String> words ;//words match the RE.
    private ArrayList<Integer> locations ;//locations of words match the RE.
    private int tag;//0= all words are are found in the Txt file, 1=part of the words are found by another RE (the subset case)pointed to by subset
    private ArrayList<Integer> subsets;//pointer to the REs that are subsets of the the current one.includes the location of the 
    private int fileWordsNum;
    private int listWordsNum;// number of words found from the subsets RE


//   (Constructor) Initialize the data members
    RegExp_Info(){
        regExp = new String();
        words = new ArrayList<String>();
        locations = new ArrayList<Integer>();
        tag=-1;//-1 = no information has been stored yet, 0= all the information have been taken from the txt file, 1= some of the information are found in the subsets
        subsets = new ArrayList<Integer>();
        fileWordsNum=0;
        listWordsNum=0;
        
    }
    ////////////////////////////////////////////////////////////////////////////
//  just get the first RE found in the array list( since all the RE stored are equivalent)  
    public RegExp getRegExp() {
        return new RegExp(regExp);
    }
//  Store the RE at the end of the RE list( the list holds all the equivalent REs).
    public void setRegExp(RegExp regExp) {
        this.regExp=regExp.toString();
//        System.out.println(" equiv. regExp has been added to the list");
    }

//    public void setRegExp(ArrayList<String> regExp) {
//        this.regExp = regExp;
//    }
 ///////////////////////////////////////////////////////////////////////////////   
    public void setWords(ArrayList<String> words) {
        //append the whole ArrayList into words
        this.words=words;
    }

    public ArrayList<String> getWords() {
        return words;
    }
////////////////////////////////////////////////////////////////////////////////
    public void setLocations(ArrayList<Integer> locations) {
        this.locations.addAll(locations);
    }

    public ArrayList<Integer> getLocations() {
        return locations;
    }
////////////////////////////////////////////////////////////////////////////////   
    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
    
////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Integer> getSubsets() {
        return subsets;
    }
    public void setSubsets(ArrayList<Integer> subsets) {
        this.subsets = subsets;
    }
//    public void setSubsets(Integer l) {
//        this.subsets.add(l);
//        
//    }
////////////////////////////////////////////////////////////////////////////////

    public int getFileWordsNum() {
        return fileWordsNum;
    }

    public void setFileWordsNum(int fileWordsNum) {
        this.fileWordsNum = fileWordsNum;
    }

////////////////////////////////////////////////////////////////////////////////    

    public int getListWordsNum() {
        return listWordsNum;
    }

    public void setListWordsNum(int listWordsNum) {
        this.listWordsNum = listWordsNum;
    }
    
}
