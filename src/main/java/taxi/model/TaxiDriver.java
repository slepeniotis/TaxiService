package taxi.model;

//imports for using persistence, date
import java.util.Date;
import javax.persistence.*;
import taxi.utils.AESEncrypt;


/**
* The Customer TaxiDriver implements all taxi drivers using the taxi service.
* <p>
* Every taxi driver has only one taxi.
* It also has:
* <ul>
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
*  
* @author  Team 4
* @since   Academic Year 2016-2017 
*/

//Declaring table id DB with name TaxiDriver
@Entity
@Table(name = "TaxiDriver")
public class TaxiDriver {

	//Declaring Primary surrogate Key as autoincrement
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	//Declaring columns with specific maximum length of characters and NULL/NOT NULL 
	@Column(name = "name", length = 30, nullable = false)
	private String name;

	@Column(name = "surname", length = 30, nullable = false)
	private String surname;

	@Column(name = "sex", length = 20, nullable = false)
	private String sex;

	@Column(name = "username", length = 30, nullable = false)
	private String username;

	@Column(name = "password", length = 256, nullable = false)
	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name = "dateOfBirth", nullable = false)
	private Date dateOfBirth;

	@Column(name = "address", length = 100, nullable = false)
	private String address;

	@Column(name = "city", length = 50, nullable = false)
	private String city;

	@Column(name = "zipCode", length = 5, nullable = false)
	private int zipCode;

	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@Column(name = "creditCardType", length = 10, nullable = false)
	private String creditCardType;

	@Column(name = "creditCardNumber", length = 16, nullable = false)
	private String creditCardNumber;

	@Column(name = "expiryDate", length = 5, nullable = false)
	private String expiryDate;

	@Column(name = "ccv", length = 3, nullable = false)
	private String ccv;	

	/* Each Taxi driver can have only one Taxi connected with him
	 * fetch type EAGER does fetch the taxi object
	 * cascade types used here, enable persist merge and remove for the taxi, in case taxi driver is persisted, merged or removed.
	 */
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="taxiID")
	private Taxi owns;

	/**<h2>The default constructor</h2>
	 * @return an empty TaxiDriver object
	 */
	public TaxiDriver(){}
	
	/**<h2>The main constructor</h2>
	 * @param name name of the taxi driver, type String
	 * @param surname surname of the taxi driver, type String
	 * @param sex sex of the taxi driver, type String
	 * @param username username of the taxi driver, type String
	 * @param password password of the taxi driver, type String
	 * @param dateOfBirth date of birth of the taxi driver, type Date
	 * @param locationLat latitude of the taxi driver, type double
	 * @param locationLon longtitude of the taxi driver, type double
	 * @param address address of the taxi driver, type String
	 * @param city city of the taxi driver, type String
	 * @param zipCode zip code of the taxi driver, type integer
	 * @param email email of the taxi driver, type String
	 * @param creditCardType credit card type of taxi driver's credit card, type String
	 * @param creditCardNumber credit card number of taxi driver's credit card, type String
	 * @param expiryDate expiry date of taxi driver's credit card, type String
	 * @param ccv ccv of taxi driver's credit card, type String
	 * @param taxi taxi of the taxi driver, type Taxi
	 * @return a TaxiDriver object
	 */
	public TaxiDriver(String name, String surname, String sex, String username, String password, Date dateOfBirth, 
			String address, String city, int zipCode, String email, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv, Taxi owns) {


		//ID is auto generated, so no need to include it here
		//List of requests is empty when a new user is signed up
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
		this.owns = owns;		
	}

	/**<h2>getId method</h2>
	 * @return taxi driver's ID
	 */
	public long getId() {
		return this.id;
	}

	/**<h2>getName method</h2>
	 * @return taxi driver's name
	 */
	public String getName() {
		return this.name;
	}

	/**<h2>setName method</h2>
	 * this method sets the given String to the attribute name of the caller object.
	 * @param name type String
	 * @return void
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**<h2>getSurname method</h2>
	 * @return taxi driver's surname
	 */
	public String getSurname() {
		return this.surname;
	}

	/**<h2>setSurname method</h2>
	 * this method sets the given String to the attribute surname of the caller object.
	 * @param surname type String
	 * @return void
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**<h2>getCreditCardType method</h2>
	 * @return taxi driver's creditCardType
	 */
	public String getCreditCardType() {
		return this.creditCardType;
	}
	
	/**<h2>setCreditCardType method</h2>
	 * this method sets the given String to the attribute creditCardType of the caller object.
	 * @param creditCardType type String
	 * @return void
	 */
	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	/**<h2>getCreditCardNumber method</h2>
	 * @return taxi driver's creditCardNumber
	 */
	public String getCreditCardNumber() {
		return this.creditCardNumber;
	}

	/**<h2>setCreditCardNumber method</h2>
	 * this method sets the given String to the attribute creditCardNumber of the caller object.
	 * @param creditCardNumber type String
	 * @return void
	 */
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}	
	
	/**<h2>getExpiryDate method</h2>
	 * @return taxi driver's expiryDate
	 */
	public String getExpiryDate() {
		return this.expiryDate;
	}
	
	/**<h2>setExpiryDate method</h2>
	 * this method sets the given String to the attribute expiryDate of the caller object.
	 * @param expiryDate type String
	 * @return void
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;		
	}

	/**<h2>getCcv method</h2>
	 * @return taxi driver's ccv
	 */
	public String getCcv() {
		return this.ccv;
	}
	
	/**<h2>setCcv method</h2>
	 * this method sets the given String to the attribute ccv of the caller object.
	 * @param ccv type String
	 * @return void
	 */
	public void setCcv(String ccv) {
		this.ccv = ccv;
	}

	/**<h2>getSex method</h2>
	 * @return taxi driver's sex
	 */
	public String getSex() {
		return this.sex;
	}

	/**<h2>setSex method</h2>
	 * this method sets the given String to the attribute sex of the caller object.
	 * @param sex type String
	 * @return void
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**<h2>getAddress method</h2>
	 * @return taxi driver's address
	 */
	public String getAddress() {
		return this.address;
	}

	/**<h2>setAddress method</h2>
	 * this method sets the given String to the attribute address of the caller object.
	 * @param address type String
	 * @return void
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**<h2>getCity method</h2>
	 * @return taxi driver's city
	 */
	public String getCity() {
		return this.city;
	}

	/**<h2>setCity method</h2>
	 * this method sets the given String to the attribute city of the caller object.
	 * @param city type String
	 * @return void
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**<h2>getZipCode method</h2>
	 * @return taxi driver's zipCode
	 */
	public int getZipCode() {
		return this.zipCode;
	}

	/**<h2>setZipCode method</h2>
	 * this method sets the given integer to the attribute zipCode of the caller object.
	 * @param zipCode type int
	 * @return void
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	/**<h2>getEmail method</h2>
	 * @return taxi driver's email
	 */
	public String getEmail() {
		return email;
	}
	
	/**<h2>setEmail method</h2>
	 * this method sets the given String to the attribute email of the caller object.
	 * @param email type String
	 * @return void
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**<h2>getUsername method</h2>
	 * @return taxi driver's username
	 */
	public String getUsername() {
		return this.username;
	}

	/**<h2>getPassword method</h2>
	 * @return taxi driver's password
	 */
	public String getPassword() {
		return this.password;
	}

	/**<h2>setPassword method</h2>
	 * this method sets the given String to the attribute password of the caller object.
	 * Before setting it, it uses encrypt method of class AESEncrypt, in order to encrypt it.
	 * @param password type String
	 * @return void
	 */
	public void setPassword(String password) {
		try {
			this.password = AESEncrypt.encrypt(password); 
		}
		catch (Exception e){
			//in case an exception occurs, the password is not changed
			System.out.println(e.getStackTrace());				
		}		
	}

	/**<h2>getDateOfBirth method</h2>
	 * @return taxi driver's dateOfBirth
	 */
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	/**<h2>setDateOfBirth method</h2>
	 * this method sets the given Date to the attribute dateOfBirth of the caller object.
	 * @param dateOfBirth type Date
	 * @return void
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;		
	}

	/**<h2>getOwns method</h2>
	 * @return taxi driver's owns
	 */
	public Taxi getOwns() {
		return owns;
	}
	
	/**<h2>setOwns method</h2>
	 * this method sets the given Taxi to the attribute owns of the caller object.
	 * @param owns type Taxi
	 * @return void
	 */
	public void setOwns(Taxi owns) {
		this.owns = owns;		
	}

	/**<h2>informTaxiDriver method</h2>
	 * @param message a string with a message needs to be sent to taxi driver
	 * @return void
	 */
	public void informTaxiDriver(String message) {
		System.out.println(message);
	}

	/**<h2>toString method</h2>
	 * @return a string including all the information of a TaxiDriver object
	 */
	@Override
	public String toString() {
		return this.id + " " + this.name + " " + this.surname + " " + this.sex + " " + this.username + " " + this.password + " " + this.dateOfBirth + "\n " + 
				this.address + " " + this.city + " " + this.zipCode + " "  + this.email + "\n " + this.creditCardType + " " + this.creditCardNumber + " " + 
				this.expiryDate + " " + this.ccv + " \n(" + this.owns.toString() + ")";
	}	
}