package taxi.resource;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The LoginInfo class implements the xml representation of a login (for customer or taxi driver), needed from LoginResource
 * <p>
 * Every LoginInfo has:
 * <ul>
 * <li>username,
 * <li>password,
 * <li>lat,
 * <li>lon 
 * </ul>
 * <p>
 * @author  Team 4
 * @since   Academic Year 2016-2017 
 */

@XmlRootElement
public class LoginInfo {

	private String username;

	private String password;	

	private double lat;

	private double lon;

	/**
	 * Default constructor
	 * 
	 * @return LoginInfo
	 */
	public LoginInfo() {}

	/**The main constructor
	 * 
	 * @param username type String
	 * @param password type String
	 * @param lat type double
	 * @param lon type double
	 * 
	 * @return LoginInfo
	 */
	public LoginInfo(String username, String password, double lat, double lon) {
		super();
		this.username = username;
		this.password = password;
		this.lat = lat;
		this.lon = lon;

	}

	/**<h2>getUsername method</h2>
	 * @return LoginInfo's username
	 */
	public String getUsername() {
		return username;
	}

	/**<h2>setUsername method</h2>
	 * this method sets the given String to the attribute username of the caller object.<p>
	 * @param username type String
	 * @return void
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**<h2>getPassword method</h2>
	 * @return LoginInfo's password
	 */
	public String getPassword() {
		return password;
	}

	/**<h2>setPassword method</h2>
	 * this method sets the given String to the attribute password of the caller object.<p>
	 * @param password type String
	 * @return void
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**<h2>getLat method</h2>
	 * @return LoginInfo's lat
	 */
	public double getLat() {
		return lat;
	}

	/**<h2>setLat method</h2>
	 * this method sets the given double to the attribute lat of the caller object.<p>
	 * @param lat type double
	 * @return void
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**<h2>getLon method</h2>
	 * @return LoginInfo's lon
	 */
	public double getLon() {
		return lon;
	}

	/**<h2>double method</h2>
	 * this method sets the given double to the attribute lon of the caller object.<p>
	 * @param lon type double
	 * @return void
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}
}

