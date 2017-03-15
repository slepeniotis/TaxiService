package taxi.resource;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import taxi.model.Customer;
import taxi.utils.AESEncrypt;

@XmlRootElement
public class CustomerInfo {

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
		
		private String password;

		private double locationLat;

		private double locationLon;

		
	
	public CustomerInfo() {

	}

	public CustomerInfo(long id, String name, String surname, String sex, String username, String password, Date dateOfBirth, 
			String address, String city, int zipCode, String email, String creditCardType, String creditCardNumber, String expiryDate, 
			String ccv, double locationLat, double locationLon) {
		this(name, surname, sex, username, password, dateOfBirth, address, city, zipCode, email, 
				creditCardType, creditCardNumber, expiryDate, ccv, locationLat, locationLon);
		this.id = id;

	}

	public CustomerInfo(String name, String surname, String sex, String username, String password,
			Date dateOfBirth, String address, String city, int zipCode, String email, 
			String creditCardType, String creditCardNumber, String expiryDate, String ccv,
			double locationLat, double locationLon) {
		super();
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
		this.locationLat = locationLat;
		this.locationLon = locationLon;
	}

	public CustomerInfo(Customer c) {
		id = c.getId();
		username = c.getUsername();
		try {
			password = AESEncrypt.decrypt(c.getPassword());
		}
		catch (Exception e){
			password = " ";
		}
		email = c.getEmail();
		creditCardType = c.getCreditCardType();
		creditCardNumber = c.getCreditCardNumber();
		expiryDate = c.getExpiryDate();
		ccv = c.getCcv();	
		name = c.getName();
		surname = c.getSurname();
		sex = c.getSex();	
		dateOfBirth = c.getDateOfBirth();
		address = c.getAddress();
		city = c.getCity();
		zipCode = c.getZipCode();	
		locationLat = c.getLocationLat();
		locationLon = c.getLocationLon();
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCcv() {
		return ccv;
	}

	public void setCcv(String ccv) {
		this.ccv = ccv;
	}

	public long getId() {
		return id;
	}
		
	public double getLocationLat() {
		return locationLat;
	}

	public void setLocationLat(double locationLat) {
		this.locationLat = locationLat;
	}

	public double getLocationLon() {
		return locationLon;
	}

	public void setLocationLon(double locationLon) {
		this.locationLon = locationLon;
	}

	public String getPassword() {
		return password;
	}

	public static CustomerInfo wrap(Customer c) {
		return new CustomerInfo(c);
	}

	public Customer getCustomer(EntityManager em) {

		Customer c = null;

		if (id != 0) {
			c = em.find(Customer.class, (long)id);
		}
		else{
			return null;
		}
		
		return c;
	}
}
