package taxi.model;

//imports for using persistence, List, ArrayList, date
import java.util.List;
import javax.persistence.*;
import taxi.utils.AESEncrypt;
import java.util.ArrayList;
import java.util.Date;


/**
* The Customer class implements all customers using the taxi service.
* <p>
* Every customer has a list of requests done, since he can make several requests
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

//Declaring table id DB with name Customer
@Entity
@Table(name = "Customer")
public class Customer {

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

	@Column(name = "locationLat", length = 30, nullable = false)
	private double locationLat;

	@Column(name = "locationLon", length = 30, nullable = false)
	private double locationLon;

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

	/* Fetch type lazy does not fetch all the list. fetching is done only if we ask for it
	 * cascade type used here is ALL. Merge, persist, detach, remove, refresh
	 */ 
	@OneToMany(mappedBy="customer", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Request> req = new ArrayList<Request>();

	/**<h2>The default constructor</h2>
	 * @return an empty Customer object
	 */
	public Customer(){}
	
	/**<h2>The main constructor</h2>
	 * @param name name of the customer, type String
	 * @param surname surname of the customer, type String
	 * @param sex sex of the customer, type String
	 * @param username username of the customer, type String
	 * @param password password of the customer, type String
	 * @param dateOfBirth date of birth of the customer, type Date
	 * @param locationLat latitude of the customer, type double
	 * @param locationLon longtitude of the customer, type double
	 * @param address address of the customer, type String
	 * @param city city of the customer, type String
	 * @param zipCode zip code of the customer, type integer
	 * @param email email of the customer, type String
	 * @param creditCardType credit card type of customer's credit card, type String
	 * @param creditCardNumber credit card number of customer's credit card, type String
	 * @param expiryDate expiry date of customer's credit card, type String
	 * @param ccv ccv of customer's credit card, type String
	 * @return a Customer object
	 */
	public Customer(String name, String surname, String sex, String username, String password, 
			Date dateOfBirth, double locationLat, double locationLon, String address, String city, 
			int zipCode, String email, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv) {

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
		this.locationLat = locationLat;
		this.locationLon = locationLon;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;		

	}

	/**<h2>getId method</h2>
	 * @return customer's ID
	 */
	public long getId() {
		return this.id;
	}
	
	/**<h2>getName method</h2>
	 * @return customer's name
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
	 * @return customer's surname
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

	/**<h2>getUsername method</h2>
	 * @return customer's username
	 */
	public String getUsername() {
		return this.username;
	}

	/**<h2>getPassword method</h2>
	 * @return customer's password
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

	/**<h2>getSex method</h2>
	 * @return customer's sex
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

	/**<h2>getDateOfBirth method</h2>
	 * @return customer's date of birth
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

	/**<h2>getLocationLat method</h2>
	 * @return customer's latitude
	 */
	public double getLocationLat() {
		return this.locationLat;
	}

	/**<h2>setLocationLat method</h2>
	 * this method sets the given double to the attribute locationLat of the caller object.
	 * @param locationLat type double
	 * @return void
	 */
	public void setLocationLat(double locationLat) {
		this.locationLat = locationLat;
	}

	/**<h2>getLocationLon method</h2>
	 * @return customer's longtitude
	 */
	public double getLocationLon() {
		return locationLon;
	}

	/**<h2>setLocationLon method</h2>
	 * this method sets the given double to the attribute locationLon of the caller object.
	 * @param locationLon type double
	 * @return void
	 */
	public void setLocationLon(double locationLon) {
		this.locationLon = locationLon;
	}

	/**<h2>getAddress method</h2>
	 * @return customer's address
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
	 * @return customer's city
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
	 * @return customer's zip code
	 */
	public int getZipCode() {
		return this.zipCode;
	}

	/**<h2>setZipCode method</h2>
	 * this method sets the given String to the attribute zipCode of the caller object.
	 * @param zipCode type integer
	 * @return void
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	/**<h2>getEmail method</h2>
	 * @return customer's email
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

	/**<h2>getCreditCardType method</h2>
	 * @return customer's credit card type
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
	 * @return customer's credit card number
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
	 * @return customer's expiry date
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
	 * @return customer's ccv
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

	/**<h2>getRequest method</h2>
	 * @return a list with customer's Requests 
	 */
	public List<Request> getRequest() {
		return this.req;
	}

	/**<h2>addRequest method</h2>
	 * this method adds the given Request object to the list of Requests of the caller object.
	 * @param req type Request
	 * @return void
	 */
	public void addRequest(Request req) {
		this.req.add(req);
	}

	/**<h2>informCustomer method</h2>
	 * @param message a string with a message needs to be sent to customer
	 * @return void
	 */
	public void informCustomer(String message){
		System.out.println(message);
	}

	/**<h2>toString method</h2>
	 * @return a string including all the information of a Customer object
	 */
	@Override
	public String toString() {
		String temp = this.id + " " + this.name + " " + this.surname + " " + this.sex + " " + this.username + " " + this.password + " " + this.dateOfBirth + "\n " + 
				this.locationLat + " " + this.locationLon + " " + this.address + " " + this.city + " " + this.zipCode + " " + this.email + "\n " + this.creditCardType + " " + this.creditCardNumber + " " + 
				this.expiryDate + " " + this.ccv;

		for(Request r : req) {
			temp += "\n (" + r.toString() + ")";
		}  

		return temp;
	}	
}