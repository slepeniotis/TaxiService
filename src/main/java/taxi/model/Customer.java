package taxi.model;

//imports for using persistence, List, ArrayList, date
import java.util.List;
import javax.persistence.*;

import taxi.utils.AESEncrypt;

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
	
	@Column(name = "password", length = 30, nullable = false)
	private String password;
	
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
	
	@Column(name = "creditCardNumber", length = 14, nullable = false)
	private int creditCardNumber;
	
	@Column(name = "expirityDate", length = 7, nullable = false)
	private Date expirityDate;
	
	@Column(name = "ccv", length = 3, nullable = false)
	private int ccv;	
	
	//every customer has a list of requests done, since he can make several requests
	@OneToMany(mappedBy="customer")
	private List<Request> req = new ArrayList<Request>();
	
	//constructors for Customer
	public Customer(){}
	public Customer(String name, String surname, String sex, String username, String password, Date dateOfBirth, String address, String city, int tk, String creditCardType, int creditCardNumber, Date expirityDate, int ccv) {
		
		//ID is auto generated, so no need to include it here
		//List of requests is empty when a new user is signed up
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
		this.zipCode = tk;
		this.creditCardType = creditCardType;
		this.creditCardNumber = creditCardNumber;
		this.expirityDate = expirityDate;
		this.ccv = ccv;		
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

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getTk() {
		return this.zipCode;
	}

	public void setTk(int tk) {
		this.zipCode = tk;
	}

	public String getCreditCardType() {
		return this.creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	public int getCreditCardNumber() {
		return this.creditCardNumber;
	}

	public void setCreditCardNumber(int creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public Date getExpirityDate() {
		return this.expirityDate;
	}

	public void setExpirityDate(Date expirityDate) {
		this.expirityDate = expirityDate;
	}

	public int getCcv() {
		return this.ccv;
	}

	public void setCcv(int ccv) {
		this.ccv = ccv;
	}
	
	public List<Request> getRequest() {
		return this.req;
	}

	public void addRequest(Request req) {
		this.req.add(req);
	}
	
	//operation methods
	public void addNewCustomer() {
		
	}

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
        		this.address + " " + this.city + " " + this.zipCode + " " + this.creditCardType + " " + this.creditCardNumber + " " + 
        		this.expirityDate + " " + this.ccv;
        
        for(Request r : req) {
            temp += " (" + r.toString() + ")";
        }  
        
        return temp;
    }	
}