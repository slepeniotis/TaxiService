package TaxiService;

import java.util.Vector;
import java.util.Date;
import TaxiService.Koursa;
import TaxiService.Evaluation;



/**
 * 
 * @author nyxteridas
 * το ID πρέπει να είναι autoincrement
 * αλλαγή των vectors σε λίστες
 * dateofbirth μόνο ως ημερομηνία
 * expiritydate μόνο μήνας χρόνος
 *
 */
public class Customer {
	private int id;
	private String name;
	private String surname;
	private String sex;
	private String username;
	private String password;
	private Date dateOfBirth;
	private String address;
	private String city;
	private int tk;
	private String creditCardType;
	private int creditCardNumber;
	private Date expirityDate;
	private int ccv;
	public Vector<Koursa> unnamed_Koursa = new Vector<Koursa>();
	public Vector<Evaluation> unnamed_Evaluation = new Vector<Evaluation>();

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
		return this.tk;
	}

	public void setTk(int tk) {
		this.tk = tk;
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

	public Customer(int id, String name, String surname, String sex, Date dateOfBirth, String address, String city, int tk, String creditCardType, int creditCardNumber, Date expirityDate, int ccv) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.city = city;
		this.tk = tk;
		this.creditCardType = creditCardType;
		this.creditCardNumber = creditCardNumber;
		this.expirityDate = expirityDate;
		this.ccv = ccv;		
	}

	public void addNewCustomer() {
		throw new UnsupportedOperationException();
	}

	public void taxiSearch() {
		throw new UnsupportedOperationException();
	}

	public void chooseTaxi() {
		throw new UnsupportedOperationException();
	}

	public void informCustomer() {
		throw new UnsupportedOperationException();
	}
}