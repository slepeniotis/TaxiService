package taxi.resource;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import taxi.model.Customer;

@XmlRootElement
public class LoginInfo {

		private String username;

		private String password;	
		
		private String userType;
		
		private double lat;
		
		private double lon;
	
	public LoginInfo() {

	}

	public LoginInfo(String username, String password, double lat, double lon, String userType) {
		super();
		this.username = username;
		this.password = password;
		this.lat = lat;
		this.lon = lon;
		this.userType = userType;

	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public double getLat() {
		return lat;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public double getLon() {
		return lon;
	}
	
	public void setLon(double lon) {
		this.lon = lon;
	}
}

