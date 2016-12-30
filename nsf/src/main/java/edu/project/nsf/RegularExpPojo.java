package edu.project.nsf;



public class RegularExpPojo {
	
	private String Text;
	private String path;
	private String pattern;
	private int [][] length;
	private int noOfMatches;
	private boolean foundOnFile;
	
	
	
	
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public int[][] getLength() {
		return length;
	}
	public void setLength(int[][] length) {
		this.length = length;
	}
	public int getNoOfMatches() {
		return noOfMatches;
	}
	public void setNoOfMatches(int noOfMatches) {
		this.noOfMatches = noOfMatches;
	}
	public boolean isFoundOnFile() {
		return foundOnFile;
	}
	public void setFoundOnFile(boolean foundOnFile) {
		this.foundOnFile = foundOnFile;
	}

	
	
	

}
