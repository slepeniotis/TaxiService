package taxi.model;

import java.util.Date;

import javax.persistence.*;

/**
 * 
 * @author nyxteridas
 * το ID πρέπει να είναι autoincrement
 * αλλαγή των vectors σε λίστες
 * dateofbirth μόνο ημερομηνία
 * expiritydate μόνο μήνας χρόνος
 *  
 *
 */

@Entity
@Table(name = "TaxiDriver")
public class TaxiDriver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
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
	
	@OneToOne
	@JoinColumn(name="taxiID")
	private Taxi owns;


	public TaxiDriver(){}
	public TaxiDriver(String name, String surname,String sex, String username, String password,Date dateOfBirth, String address, String city,int zipCode, String creditCardType, int creditCardNumber, Date expirityDate, int ccv, Taxi owns) {
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.creditCardType = creditCardType;
		this.creditCardNumber = creditCardNumber;
		this.expirityDate = expirityDate;
		this.ccv = ccv;
		this.owns = owns; 
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setTk(int zipCode) {
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
		this.password = password;
	}
	
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void informTaxiDriver() {
		
	}

	public void confirmCustomer() {
		
	}	
}