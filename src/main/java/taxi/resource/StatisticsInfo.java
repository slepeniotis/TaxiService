package taxi.resource;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StatisticsInfo {

	private int numOfRoutes;

	private double totalCommisions;

	private int typeOfStats;			

	public StatisticsInfo() {

	}

	public StatisticsInfo(int numOfRoutes, int typeOfStats){
		this.numOfRoutes = numOfRoutes;
		this.typeOfStats = typeOfStats;
	}

	public StatisticsInfo(double totalCommisions, int typeOfStats) {
		this.totalCommisions = totalCommisions;
		this.typeOfStats = typeOfStats;
	}

	public int getNumOfRoutes() {
		return numOfRoutes;
	}

	public void setNumOfRoutes(int numOfRoutes) {
		this.numOfRoutes = numOfRoutes;
	}

	public double getTotalCommisions() {
		return totalCommisions;
	}

	public void setTotalCommisions(double totalCommisions) {
		this.totalCommisions = totalCommisions;
	}

	public int getTypeOfStats() {
		return typeOfStats;
	}

	public void setTypeOfStats(int typeOfStats) {
		this.typeOfStats = typeOfStats;
	}


}
