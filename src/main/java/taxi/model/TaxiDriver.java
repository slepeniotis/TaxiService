package taxi.model;

//imports for using persistence, date
import java.util.Date;
import javax.persistence.*;

import taxi.utils.AESEncrypt;

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
	
	@Column(name = "creditCardType", length = 10, nullable = false)
	private String creditCardType;
	
	@Column(name = "creditCardNumber", length = 16, nullable = false)
	private String creditCardNumber;
	
	@Column(name = "expiryDate", length = 5, nullable = false)
	private String expiryDate;
	
	@Column(name = "ccv", length = 3, nullable = false)
	private String ccv;	
	
	//Each Taxi driver can have only one Taxi connected with him
	@OneToOne
	@JoinColumn(name="taxiID")
	private Taxi owns;

	//constructors for Customer
	public TaxiDriver(){}
	public TaxiDriver(String name, String surname,String sex, String username, String password, Date dateOfBirth, 
			String address, String city, int zipCode, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv, Taxi owns) {
		
		if (validate(name, surname, sex, username, password, dateOfBirth, address, city, zipCode, creditCardType, 
				creditCardNumber, expiryDate, ccv)){
			
		
		//ID is auto generated, so no need to include it here
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.username = username;
		try {
			this.password = AESEncrypt.encrypt(password); 
		}
		catch (Exception e){
        	System.out.println(e.getStackTrace());
        }
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.creditCardType = creditCardType;
		this.creditCardNumber = creditCardNumber;
		this.expiryDate = expiryDate;
		this.ccv = ccv;
		this.owns = owns; 
		}
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

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getCreditCardNumber() {
		return this.creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCcv() {
		return this.ccv;
	}

	public void setCcv(String ccv) {
		this.ccv = ccv;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		try {
			this.password = AESEncrypt.encrypt(password); 
		}
		catch (Exception e){
        	System.out.println(e.getStackTrace());
        }
	}
	
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	//operation methods
	public boolean validate(String name, String surname, String sex, String username, String password, 
			Date dateOfBirth, String address, String city, int zipCode, String creditCardType, 
			String creditCardNumber, String expiryDate, String ccv) {
		
		
		String month = expiryDate.substring(0, 1);
		String year = expiryDate.substring(3, 4);
		expiryDate = month + "/" + year;
		
		return true;
	}
	
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