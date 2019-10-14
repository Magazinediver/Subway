package subway;

import java.util.ArrayList;
import java.util.List;

public class Line {
	public String lname;
	public List<Station> station = new ArrayList<Station>();
//	public String line;
	
	public List<Station> getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station.add(station);
	}

//	public void line(List<Station> station, String lname){
//		this.lname = lname;
//		this.station = station;
//	}
	
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	
}
