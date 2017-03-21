package taxi.resource;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The StatisticsInfo class implements the xml representation of a statistic, needed from StatisticsResource
 * <p>
 * Every StatisticsInfo has:
 * <ul>
 * <li>numOfRoutes, 
 * <li>totalCommisions, 
 * <li>typeOfStats
 * </ul>
 * 
 * @author  Team 4
 * @since   Academic Year 2016-2017 
 */
@XmlRootElement
public class StatisticsInfo {

	private int numOfRoutes;

	private double totalCommisions;

	private int typeOfStats;			

	/**
	 * Default constructor
	 * 
	 * @return StatisticsInfo
	 */
	public StatisticsInfo() {}

	/**
	 * This constructor is used for type of statictis 2 and 3 (routes from a specific city and routes to a specific city accordingly)
	 * 
	 * @param numOfRoutes
	 * @param typeOfStats
	 * 
	 * @return StatisticsInfo
	 */
	public StatisticsInfo(int numOfRoutes, int typeOfStats){
		this.numOfRoutes = numOfRoutes;
		this.typeOfStats = typeOfStats;
	}

	/**
	 * This constructor is used for type of statictis 1 (total commissions gained within a specific date range)
	 * 
	 * @param totalCommisions
	 * @param typeOfStats
	 * 
	 * @return StatisticsInfo
	 */
	public StatisticsInfo(double totalCommisions, int typeOfStats) {
		this.totalCommisions = totalCommisions;
		this.typeOfStats = typeOfStats;
	}

	/**<h2>getNumOfRoutes method</h2>
	 * @return StatisticsInfo's numOfRoutes
	 */
	public int getNumOfRoutes() {
		return numOfRoutes;
	}

	/**<h2>setNumOfRoutes method</h2>
	 * this method sets the given integer to the attribute numOfRoutes of the caller object.<p>
	 * @param numOfRoutes type int
	 * @return void
	 */
	public void setNumOfRoutes(int numOfRoutes) {
		this.numOfRoutes = numOfRoutes;
	}

	/**<h2>getTotalCommisions method</h2>
	 * @return StatisticsInfo's totalCommisions
	 */
	public double getTotalCommisions() {
		return totalCommisions;
	}

	/**<h2>setTotalCommisions method</h2>
	 * this method sets the given double to the attribute totalCommisions of the caller object.<p>
	 * @param totalCommisions type double
	 * @return void
	 */
	public void setTotalCommisions(double totalCommisions) {
		this.totalCommisions = totalCommisions;
	}

	/**<h2>getTypeOfStats method</h2>
	 * @return StatisticsInfo's typeOfStats
	 */
	public int getTypeOfStats() {
		return typeOfStats;
	}

	/**<h2>setTypeOfStats method</h2>
	 * this method sets the given integer to the attribute typeOfStats of the caller object.<p>
	 * @param typeOfStats type int
	 * @return void
	 */
	public void setTypeOfStats(int typeOfStats) {
		this.typeOfStats = typeOfStats;
	}


}
