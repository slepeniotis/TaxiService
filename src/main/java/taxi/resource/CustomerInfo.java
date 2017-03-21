package taxi.resource;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;
import taxi.model.Customer;
import taxi.utils.AESEncrypt;

/**
 * The CustomerInfo class implements the xml representation of a customer, needed from EditAccountResource and RegistrationResource
 * <p>
 * Every CustomerInfo has:
 * <ul>
 * <li>id, 
 * <li>name, 
 * <li>surname,
 * <li>sex,
 * <li>username,
 * <li>password,
 * <li>dateOfBirth,
 * <li>locationLat,
 * <li>locationLon,
 * <li>address,
 * <li>city,
 * <li>zipCode,
 * <li>email,
 * <li>creditCardType,
 * <li>creditCardNumber,
 * <li>expiryDate,
 * <li>ccv
 * </ul>
 * <p>
 * @author  Team 4
 * @since   Academic Year 2016-2017 
 */
@XmlRootElement
public class CustomerInfo {

	private long id;

	private String name;

	private String surname;

	private String sex;

	private String username;

	private Date dateOfBirth;

	private String address;

	private String city;

	private int zipCode;

	private String email;

	private String creditCardType;

	private String creditCardNumber;

	private String expiryDate;

	private String ccv;	

	private String password;

	private double locationLat;

	private double locationLon;


	/**
	 * Default constructor
	 * 
	 * @return CustomerInfo
	 */
	public CustomerInfo() {}

	/**This constructor is used when the id of the object is known
	 * 
	 * @param id type long
	 * @param name type String
	 * @param surname type String
	 * @param sex type String
	 * @param username type String
	 * @param password type String
	 * @param dateOfBirth type Date
	 * @param address type String
	 * @param city type String
	 * @param zipCode type int
	 * @param email type String
	 * @param creditCardType type String
	 * @param creditCardNumber type String
	 * @param expiryDate type String
	 * @param ccv type String
	 * @param locationLat type double
	 * @param locationLon type double
	 * 
	 * @return CustomerInfo
	 */
	public CustomerInfo(long id, String name, String surname, String sex, String username, String password, Date dateOfBirth, 
			String address, String city, int zipCode, String email, String creditCardType, String creditCardNumber, String expiryDate, 
			String ccv, double locationLat, double locationLon) {
		this(name, surname, sex, username, password, dateOfBirth, address, city, zipCode, email, 
				creditCardType, creditCardNumber, expiryDate, ccv, locationLat, locationLon);
		this.id = id;

	}

	/**This constructor is used when the id in unknown
	 * 
	 * @param name type String
	 * @param surname type String
	 * @param sex type String
	 * @param username type String
	 * @param password type String
	 * @param dateOfBirth type Date
	 * @param address type String
	 * @param city type String
	 * @param zipCode type int
	 * @param email type String
	 * @param creditCardType type String
	 * @param creditCardNumber type String
	 * @param expiryDate type String
	 * @param ccv type String
	 * @param locationLat type double
	 * @param locationLon type double
	 * 
	 * @return CustomerInfo
	 */
	public CustomerInfo(String name, String surname, String sex, String username, String password,
			Date dateOfBirth, String address, String city, int zipCode, String email, 
			String creditCardType, String creditCardNumber, String expiryDate, String ccv,
			double locationLat, double locationLon) {
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
		this.locationLat = locationLat;
		this.locationLon = locationLon;
	}

	/**This constructor creates a CustomerInfo object from a Customer object
	 * 
	 * @param c type Customer
	 * 
	 * @return CustomerInfo
	 */
	public CustomerInfo(Customer c) {
		id = c.getId();
		username = c.getUsername();
		try {
			password = AESEncrypt.decrypt(c.getPassword());
		}
		catch (Exception e){
			password = " ";
		}
		email = c.getEmail();
		creditCardType = c.getCreditCardType();
		creditCardNumber = c.getCreditCardNumber();
		expiryDate = c.getExpiryDate();
		ccv = c.getCcv();	
		name = c.getName();
		surname = c.getSurname();
		sex = c.getSex();	
		dateOfBirth = c.getDateOfBirth();
		address = c.getAddress();
		city = c.getCity();
		zipCode = c.getZipCode();	
		locationLat = c.getLocationLat();
		locationLon = c.getLocationLon();
	}


	/**<h2>getName method</h2>
	 * @return CustomerInfo's name
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
	 * @return CustomerInfo's surname
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
	 * @return CustomerInfo's sex
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
	 * @return CustomerInfo's username
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
	 * @return CustomerInfo's date of birth
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
	 * @return CustomerInfo's address
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
	 * @return CustomerInfo's city
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
	 * @return CustomerInfo's zip code
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**<h2>setZipCode method</h2>
	 * this method sets the given String to the attribute zipCode of the caller object.<p>
	 * @param zipCode type integer
	 * @return void
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	/**<h2>getEmail method</h2>
	 * @return CustomerInfo's email
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
	 * @return CustomerInfo's credit card type
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
	 * @return CustomerInfo's credit card number
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
	 * @return CustomerInfo's expiry date
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
	 * @return CustomerInfo's ccv
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

	/**<h2>getId method</h2>
	 * @return CustomerInfo's ID
	 */
	public long getId() {
		return id;
	}

	/**<h2>setId method</h2>
	 * this method sets the given long to the attribute id of the caller object.<p>
	 * @param id type long
	 * @return void
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**<h2>getLocationLat method</h2>
	 * @return CustomerInfo's latitude
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
	 * @return CustomerInfo's longtitude
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
	 * @return CustomerInfo's password
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
	 * This method is used to transform a Customer object to CustomerInfo object<p>
	 * 
	 * @param c type Customer
	 * @return CustomerInfo
	 */
	public static CustomerInfo wrap(Customer c) {
		return new CustomerInfo(c);
	}

	/**getCustomer method
	 * This method is used for getting a Customer object from a CustomerInfo object<p>
	 * 
	 * @param em type EntityManager
	 * @return Customer
	 */
	public Customer getCustomer(EntityManager em) {

		Customer c = null;

		if (id != 0) {
			c = em.find(Customer.class, (long)id);
		}
		else{
			return null;
		}

		return c;
	}
}
