package subway;

import java.util.ArrayList;
import java.util.List;

public class Station {
	
	public String sname;
	String[] trans = new String[3];
	public String line; 
	
	public void setSname(String sname) {
		this.sname = sname;
	}	
	public String[] getStationrans() {
		return trans;
	}
	public void setStationrans(String[] trans) {
		this.trans = trans;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getSname() {
		return sname;
	}
	
}