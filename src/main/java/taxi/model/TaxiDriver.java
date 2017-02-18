package taxi.model;
import java.util.Date;

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

public class TaxiDriver {	
	private int id;
	private String name;
	private String surname;
	private String sex;
	private String username;
	private String password;
	private Date dateOfBirth;
	private String address;
	private String city;
	private int zipCode;
	private String creditCardType;
	private int creditCardNumber;
	private Date expirityDate;
	private int ccv;
	private Taxi owns;


	public TaxiDriver(int id, String name, String surname,String sex, String username, String password,Date dateOfBirth, String address, String city,int zipCode, String creditCardType, int creditCardNumber, Date expirityDate, int ccv, Taxi owns) {
		this.id = id;
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