package taxi.model;

//imports for using persistence, date
import java.util.Date;
import javax.persistence.*;

import taxi.utils.AESEncrypt;
import taxi.utils.Validators;

//Declaring table id DB with name TaxiDriver
@Entity
@Table(name = "TaxiDriver")
public class TaxiDriver {
	
	//Declaring Primary surrogate Key as autoincrement
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
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
	
	//Each Taxi driver can have only one Taxi connected with him
	//fetch type EAGER does fetch the taxi object
	//cascade types used here, enable persist merge and remove for the taxi, in case taxi driver is persisted, merged or removed.
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="taxiID")
	private Taxi owns;

	//constructors for Customer
	public TaxiDriver(){}
	public TaxiDriver(String name, String surname,String sex, String username, String password, Date dateOfBirth, 
			String address, String city, int zipCode, String email, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv, Taxi owns) {
		
		
		//ID is auto generated, so no need to include it here
		//List of requests is empty when a new user is signed up
		
		//validations
		if (Validators.validateUsername(username))
			this.username = username;
		else
			System.out.println("Username already in use");
		
		if (Validators.validatePassword(password))
			try {
				this.password = AESEncrypt.encrypt(password); 
			}
			catch (Exception e){
	        	System.out.println(e.getStackTrace());
	        }
		else
			System.out.println("Invalid password");
		
		if (Validators.validateEmail(email))
			this.email = email;
		else
			System.out.println("Email already in use");
		
		if (Validators.validateCreditCard(creditCardNumber, expiryDate, ccv)){
			this.creditCardType = creditCardType;
			this.creditCardNumber = creditCardNumber;
			this.expiryDate = expiryDate;
			this.ccv = ccv;	
		}
		else
			System.out.println("Credit Card's details are invalid");
		
		this.name = name;
		this.surname = surname;
		this.sex = sex;	
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;	
		this.owns = owns; 
	}

	//get/set methods in order to have access in private attributes
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCreditCardType() {
		return this.creditCardType;
	}

	public String getCreditCardNumber() {
		return this.creditCardNumber;
	}

	public void setCreditCard(String creditCardType, String creditCardNumber, String expiryDate, String ccv) {
		if (Validators.validateCreditCard(creditCardNumber, expiryDate, ccv)){
			this.creditCardType = creditCardType;
			this.creditCardNumber = creditCardNumber;
			this.expiryDate = expiryDate;
			this.ccv = ccv;	
		}
		else
			System.out.println("Credit Card's details are invalid");
	}

	public String getExpiryDate() {
		return this.expiryDate;
	}

	public String getCcv() {
		return this.ccv;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getzipCode() {
		return this.zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if (Validators.validateEmail(email))
			this.email = email;
		else
			System.out.println("Email already in use");
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		if (Validators.validatePassword(password))
			try {
				this.password = AESEncrypt.encrypt(password); 
			}
			catch (Exception e){
	        	System.out.println(e.getStackTrace());
	        }
		else
			System.out.println("Invalid password");
	}
	
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	//operation methods
	public void informTaxiDriver() {
		
	}

	public void confirmRequest() {
		
	}
	
	//override of toString method from Object
		@Override
		public String toString() {
	        return this.id + " " + this.name + " " + this.surname + " " + this.sex + " " + this.username + " " + this.password + " " + this.dateOfBirth + " " + 
	        		this.address + " " + this.city + " " + this.zipCode + " " + this.creditCardType + " " + this.creditCardNumber + " " + 
	        		this.expiryDate + " " + this.ccv + " (" + this.owns.toString() + ")";
	    }	
}