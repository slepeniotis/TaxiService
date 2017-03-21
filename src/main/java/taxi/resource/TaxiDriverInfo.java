package taxi.resource;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import taxi.model.TaxiDriver;

/**
 * The TaxiDriverInfo class implements the xml representation of a TaxiDriver, needed from EditAccountResource
 * <p>
 * Every TaxiDriverInfo has:
 * <ul>
 * <li>id, 
 * <li>name, 
 * <li>surname,
 * <li>sex,
 * <li>username,
 * <li>dateOfBirth,
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
public class TaxiDriverInfo {

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

	/**
	 * Default constructor
	 * 
	 * @return TaxiDriverInfo
	 */
	public TaxiDriverInfo() {}

	/**This constructor is used when the id of the object is known
	 * 
	 * @param id type long
	 * @param name type String
	 * @param surname type String
	 * @param sex type String
	 * @param username type String
	 * @param dateOfBirth type Date
	 * @param address type String
	 * @param city type String
	 * @param zipCode type int
	 * @param email type String
	 * @param creditCardType type String
	 * @param creditCardNumber type String
	 * @param expiryDate type String
	 * @param ccv type String
	 * 
	 * @return TaxiDriverInfo
	 */
	public TaxiDriverInfo(long id, String name, String surname, String sex, String username, Date dateOfBirth, String address, String city, 
			int zipCode, String email, String creditCardType, String creditCardNumber, String expiryDate, String ccv) {
		this(name, surname, sex, username, dateOfBirth, address, city, zipCode, email, creditCardType, creditCardNumber, expiryDate, ccv);
		this.id = id;

	}

	/**This constructor is used when the id in unknown
	 * 
	 * @param id type long
	 * @param name type String
	 * @param surname type String
	 * @param sex type String
	 * @param username type String
	 * @param dateOfBirth type Date
	 * @param address type String
	 * @param city type String
	 * @param zipCode type int
	 * @param email type String
	 * @param creditCardType type String
	 * @param creditCardNumber type String
	 * @param expiryDate type String
	 * @param ccv type String
	 * 
	 * @return TaxiDriverInfo
	 */
	public TaxiDriverInfo(String name, String surname, String sex, String username, Date dateOfBirth, String address, String city, 
			int zipCode, String email, String creditCardType, String creditCardNumber, String expiryDate, String ccv) {
		super();
		this.username = username;
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
	}

	/**This constructor creates a TaxiDriverInfo object from a TaxiDriver object
	 * 
	 * @param tx type TaxiDriver
	 * 
	 * @return TaxiDriverInfo
	 */
	public TaxiDriverInfo(TaxiDriver tx) {
		id = tx.getId();
		username = tx.getUsername();
		email = tx.getEmail();
		creditCardType = tx.getCreditCardType();
		creditCardNumber = tx.getCreditCardNumber();
		expiryDate = tx.getExpiryDate();
		ccv = tx.getCcv();	
		name = tx.getName();
		surname = tx.getSurname();
		sex = tx.getSex();	
		dateOfBirth = tx.getDateOfBirth();
		address = tx.getAddress();
		city = tx.getCity();
		zipCode = tx.getZipCode();	
	}

	/**<h2>getName method</h2>
	 * @return TaxiDriverInfo's name
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
	 * @return TaxiDriverInfo's surname
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
	 * @return TaxiDriverInfo's sex
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
	 * @return TaxiDriverInfo's username
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
	 * @return TaxiDriverInfo's dateOfBirth
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
	 * @return TaxiDriverInfo's address
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
	 * @return TaxiDriverInfo's city
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
	 * @return TaxiDriverInfo's zipCode
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
	 * @return TaxiDriverInfo's email
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
	 * @return TaxiDriverInfo's creditCardType
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
	 * @return TaxiDriverInfo's creditCardNumber
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
	 * @return TaxiDriverInfo's expiryDate
	 */
	public String getExpiryDate() {
		return expiryDate;
	}

	/**<h2>expiryDate method</h2>
	 * this method sets the given String to the attribute expiryDate of the caller object.<p>
	 * @param expiryDate type String
	 * @return void
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**<h2>getCcv method</h2>
	 * @return TaxiDriverInfo's ccv
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
	 * @return TaxiDriverInfo's id
	 */
	public long getId() {
		return id;
	}

	/**wrap method
	 * This method is used to transform a TaxiDriver object to TaxiDriverInfo object<p>
	 * 
	 * @param txdr type TaxiDriver
	 * @return TaxiDriverInfo
	 */
	public static TaxiDriverInfo wrap(TaxiDriver txdr) {
		return new TaxiDriverInfo(txdr);
	}
}
