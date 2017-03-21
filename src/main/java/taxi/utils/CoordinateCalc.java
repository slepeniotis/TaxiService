package taxi.utils;

/**The CoordinateCalc class implements the calculation of the distance between to sets of coordinates
 * 
 * @author  Team 4
 * @since   Academic Year 2016-2017 
 */

public class CoordinateCalc {
	public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
	
	public CoordinateCalc(){}
		
	/**<h2>calculateDistanceInKilometer method</h2>
	 * This method implements the calculation of the distance between to sets of coordinates<p>
	 * @param userLat origin latitude, type double
	 * @param userLng origin longtitude, type double
	 * @param venueLat destination latitude, type double
	 * @param venueLng destination longtitude, type double
	 * @return int
	 */	
	public int calculateDistanceInKilometer(double userLat, double userLng,
	  double venueLat, double venueLng) {

	    double latDistance = Math.toRadians(userLat - venueLat);
	    double lngDistance = Math.toRadians(userLng - venueLng);

	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	      + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat))
	      * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	    return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
	}
}
