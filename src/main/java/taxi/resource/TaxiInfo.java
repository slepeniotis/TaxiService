package taxi.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import taxi.model.Taxi;

/**
 * The TaxiInfo class implements the xml representation of a taxi and taxi driver, needed from RegistrationResource
 * <p>
 * Every TaxiInfo has:
 * <ul>
 * <li>name, 
 * <li>surname,
 * <li>sex,
 * <li>username,
 * <li>password,
 * <li>dateOfBirth,
 * <li>address,
 * <li>city,
 * <li>zipCode,
 * <li>email,
 * <li>creditCardType,
 * <li>creditCardNumber,
 * <li>expiryDate,
 * <li>ccv,
 * <li>carModel
 * <li>carType
 * <li>licensePlate
 * <li>carModelDate 
 * <li>locationLat,
 * <li>locationLon,
 * </ul>
 * <p>
 * @author  Team 4
 * @since   Academic Year 2016-2017 
 */
@XmlRootElement
public class TaxiInfo {

	private String name;

	private String surname;

	private String sex;

	private String username;

	private String password;

	private Date dateOfBirth;

	private String address;

	private String city;

	private int zipCode;

	private String email;

	private String creditCardType;

	private String creditCardNumber;

	private String expiryDate;

	private String ccv;	

	private String carModel;

	private String carType;

	private String licensePlate;

	private String carModelDate;

	private double locationLat;

	private double locationLon;

	/**
	 * Default constructor
	 * 
	 * @return TaxiInfo
	 */
	public TaxiInfo(){}

	/**
	 * The main constructor
	 * 
	 * @param name
	 * @param surname
	 * @param sex
	 * @param username
	 * @param password
	 * @param dateOfBirth
	 * @param address
	 * @param city
	 * @param zipCode
	 * @param email
	 * @param creditCardType
	 * @param creditCardNumber
	 * @param expiryDate
	 * @param ccv
	 * @param carModel
	 * @param carType
	 * @param licensePlate
	 * @param carModelDate
	 * @param locationLat
	 * @param locationLon
	 * 
	 * @return TaxiInfo
	 */
	public TaxiInfo(String name, String surname, String sex, String username, String password,
			Date dateOfBirth, String address, String city, int zipCode, String email, 
			String creditCardType, String creditCardNumber, String expiryDate, String ccv, 
			String carModel, String carType, String licensePlate, String carModelDate, double locationLat, double locationLon) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.creditCardType = creditCardType;
		this.creditCardNumber = creditCardNumber;
		this.expiryDate = expiryDate;
		this.ccv = ccv;	
		this.name = name;
		this.surname = surname;
		this.sex = sex;	
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;	
		this.carModel = carModel;
		this.carType = carType;
		this.licensePlate = licensePlate;
		this.carModelDate = carModelDate;
		this.locationLat = locationLat;
		this.locationLon = locationLon;
	}

	/**<h2>getName method</h2>
	 * @return TaxiInfo's name
	 */
	public String getName() {
		return name;
	}

	/**<h2>setName method</h2>
	 * this method sets the given String to the attribute name of the caller object.<p>
	 * @param name type String
	 * @return void
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**<h2>getSurname method</h2>
	 * @return TaxiInfo's surname
	 */
	public String getSurname() {
		return surname;
	}

	/**<h2>setSurname method</h2>
	 * this method sets the given String to the attribute surname of the caller object.<p>
	 * @param surname type String
	 * @return void
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**<h2>getSex method</h2>
	 * @return TaxiInfo's sex
	 */
	public String getSex() {
		return sex;
	}

	/**<h2>setSex method</h2>
	 * this method sets the given String to the attribute sex of the caller object.<p>
	 * @param sex type String
	 * @return void
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**<h2>getUsername method</h2>
	 * @return TaxiInfo's username
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

	/**<h2>getDateOfBirth method</h2>
	 * @return TaxiInfo's dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**<h2>setDateOfBirth method</h2>
	 * this method sets the given Date to the attribute dateOfBirth of the caller object.<p>
	 * @param dateOfBirth type Date
	 * @return void
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**<h2>getAddress method</h2>
	 * @return TaxiInfo's address
	 */
	public String getAddress() {
		return address;
	}

	/**<h2>setAddress method</h2>
	 * this method sets the given String to the attribute address of the caller object.<p>
	 * @param address type String
	 * @return void
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**<h2>getCity method</h2>
	 * @return TaxiInfo's city
	 */
	public String getCity() {
		return city;
	}

	/**<h2>setCity method</h2>
	 * this method sets the given String to the attribute city of the caller object.<p>
	 * @param city type String
	 * @return void
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**<h2>getZipCode method</h2>
	 * @return TaxiInfo's zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**<h2>setZipCode method</h2>
	 * this method sets the given integer to the attribute zipCode of the caller object.<p>
	 * @param zipCode type int
	 * @return void
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	/**<h2>getEmail method</h2>
	 * @return TaxiInfo's email
	 */
	public String getEmail() {
		return email;
	}

	/**<h2>setEmail method</h2>
	 * this method sets the given String to the attribute email of the caller object.<p>
	 * @param email type String
	 * @return void
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**<h2>getCreditCardType method</h2>
	 * @return TaxiInfo's creditCardType
	 */
	public String getCreditCardType() {
		return creditCardType;
	}

	/**<h2>setCreditCardType method</h2>
	 * this method sets the given String to the attribute creditCardType of the caller object.<p>
	 * @param creditCardType type String
	 * @return void
	 */
	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	/**<h2>getCreditCardNumber method</h2>
	 * @return TaxiInfo's creditCardNumber
	 */
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	/**<h2>setCreditCardNumber method</h2>
	 * this method sets the given String to the attribute creditCardNumber of the caller object.<p>
	 * @param creditCardNumber type String
	 * @return void
	 */
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	/**<h2>getExpiryDate method</h2>
	 * @return TaxiInfo's expiryDate
	 */
	public String getExpiryDate() {
		return expiryDate;
	}

	/**<h2>setExpiryDate method</h2>
	 * this method sets the given String to the attribute expiryDate of the caller object.<p>
	 * @param expiryDate type String
	 * @return void
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**<h2>getCcv method</h2>
	 * @return TaxiInfo's ccv
	 */
	public String getCcv() {
		return ccv;
	}

	/**<h2>setCcv method</h2>
	 * this method sets the given String to the attribute ccv of the caller object.<p>
	 * @param ccv type String
	 * @return void
	 */
	public void setCcv(String ccv) {
		this.ccv = ccv;
	}

	/**<h2>getCarModel method</h2>
	 * @return TaxiInfo's carModel
	 */
	public String getCarModel() {
		return carModel;
	}

	/**<h2>setCarModel method</h2>
	 * this method sets the given String to the attribute carModel of the caller object.<p>
	 * @param carModel type String
	 * @return void
	 */
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	/**<h2>getCarType method</h2>
	 * @return TaxiInfo's carType
	 */
	public String getCarType() {
		return carType;
	}

	/**<h2>setCarType method</h2>
	 * this method sets the given String to the attribute carType of the caller object.<p>
	 * @param carType type String
	 * @return void
	 */
	public void setCarType(String carType) {
		this.carType = carType;
	}

	/**<h2>getLicensePlate method</h2>
	 * @return TaxiInfo's licensePlate
	 */
	public String getLicensePlate() {
		return licensePlate;
	}

	/**<h2>setLicensePlate method</h2>
	 * this method sets the given String to the attribute licensePlate of the caller object.<p>
	 * @param licensePlate type String
	 * @return void
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	/**<h2>getCarModelDate method</h2>
	 * @return TaxiInfo's carModelDate
	 */
	public String getCarModelDate() {
		return carModelDate;
	}

	/**<h2>setCarModelDate method</h2>
	 * this method sets the given String to the attribute carModelDate of the caller object.<p>
	 * @param carModelDate type String
	 * @return void
	 */
	public void setCarModelDate(String carModelDate) {
		this.carModelDate = carModelDate;
	}

	/**<h2>getLocationLat method</h2>
	 * @return TaxiInfo's locationLat
	 */
	public double getLocationLat() {
		return locationLat;
	}

	/**<h2>setLocationLat method</h2>
	 * this method sets the given double to the attribute locationLat of the caller object.<p>
	 * @param locationLat type double
	 * @return void
	 */
	public void setLocationLat(double locationLat) {
		this.locationLat = locationLat;
	}

	/**<h2>getLocationLon method</h2>
	 * @return TaxiInfo's locationLon
	 */
	public double getLocationLon() {
		return locationLon;
	}

	/**<h2>setLocationLon method</h2>
	 * this method sets the given double to the attribute locationLon of the caller object.<p>
	 * @param locationLon type double
	 * @return void
	 */
	public void setLocationLon(double locationLon) {
		this.locationLon = locationLon;
	}

	/**<h2>getPassword method</h2>
	 * @return TaxiInfo's password
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

	/**wrap method
	 * This method is used to transform a list of Taxis to a list of TaxiInfos<p>
	 * 
	 * @param taxi type List<Taxi>
	 * @return List<TaxiInfo>
	 */
	public static List<TaxiInfo> wrapTaxi(List<Taxi> taxi) {
		List<TaxiInfo> taxiInfo = new ArrayList<>(0);

		for (Taxi t : taxi) {
			TaxiInfo temp = new TaxiInfo();
			temp.setCarModel(t.getCarModel());
			temp.setCarModelDate(t.getCarModelDate());
			temp.setCarType(t.getCarType());
			temp.setLicensePlate(t.getLicensePlate());
			temp.setLocationLat(t.getLocationLat());
			temp.setLocationLon(t.getLocationLon());

			taxiInfo.add(temp);
		}

		return taxiInfo;
	}

}
