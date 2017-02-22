package taxi.model;

//imports for using persistence, List, ArrayList, date
import java.util.List;
import javax.persistence.*;
import taxi.utils.AESEncrypt;
import taxi.utils.Validators;
import java.util.ArrayList;
import java.util.Date;


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
	
	@Column(name = "location", length = 30, nullable = false)
	private String location;
	
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
	
	//every customer has a list of requests done, since he can make several requests
	//fetch type lazy does not fetch all the list. fetching is done only if we ask for it
	//cascade types used here, enable persist merge and remove for the whole list, in case Customer is persisted, merged or removed.
	@OneToMany(mappedBy="customer", fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private List<Request> req = new ArrayList<Request>();
	
	//constructors for Customer
	public Customer(){}
	public Customer(String name, String surname, String sex, String username, String password, Date dateOfBirth, 
			String location, String address, String city, int zipCode, String email, String creditCardType, String creditCardNumber, 
			String expiryDate, String ccv) {
		
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
		this.location = location;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;		
		
	}
	
	//get/set methods in order to have access in private attributes
	public long getId() {
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
	
	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public boolean setPassword(String password) {
		if (Validators.validatePassword(password)) {
			try {
				this.password = AESEncrypt.encrypt(password); 
			}
			catch (Exception e){
	        	System.out.println(e.getStackTrace());
	        	return false;
	        }
			return true;
		}
		else {
			System.out.println("Invalid password");
			return false;
		}
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public int getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmail() {
		return email;
	}
	
	public boolean setEmail(String email) {
		if (Validators.validateEmail(email)) {
			this.email = email;
			return true;
		}
		else {
			System.out.println("Email already in use");
			return false;
		}
	}
	
	public String getCreditCardType() {
		return this.creditCardType;
	}

	public boolean setCreditCard(String creditCardType, String creditCardNumber, String expiryDate, String ccv) {
		if (Validators.validateCreditCard(creditCardNumber, expiryDate, ccv)){
			this.creditCardType = creditCardType;
			this.creditCardNumber = creditCardNumber;
			this.expiryDate = expiryDate;
			this.ccv = ccv;
			return true;
		}
		else {
			System.out.println("Credit Card's details are invalid");
			return false;
		}
	}

	public String getCreditCardNumber() {
		return this.creditCardNumber;
	}

	public String getExpiryDate() {
		return this.expiryDate;
	}

	public String getCcv() {
		return this.ccv;
	}

	public List<Request> getRequest() {
		return this.req;
	}

	public void addRequest(Request req) {
		this.req.add(req);
	}
	
	//operation methods
	public void taxiSearch() {
		
	}

	public void chooseTaxi() {
		
	}

	public void informCustomer() {
		
	}
	
	//override of toString method from Object
	@Override
	public String toString() {
        String temp = this.id + " " + this.name + " " + this.surname + " " + this.sex + " " + this.username + " " + this.password + " " + this.dateOfBirth + " " + 
        		this.location + " " + this.address + " " + this.city + " " + this.zipCode + " " + this.email + " " + this.creditCardType + " " + this.creditCardNumber + " " + 
        		this.expiryDate + " " + this.ccv;
        
        for(Request r : req) {
            temp += " (" + r.toString() + ")";
        }  
        
        return temp;
    }	
}