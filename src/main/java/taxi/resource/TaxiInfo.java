package taxi.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import taxi.model.Customer;
import taxi.model.Taxi;

@XmlRootElement
public class TaxiInfo {

	private String name;

	private String surname;

	private String sex;

	private String username;

	private String password;

	private Date dateOfBirth;

	private String address;

	private String city;

	private int zipCode;

	private String email;

	private String creditCardType;

	private String creditCardNumber;

	private String expiryDate;

	private String ccv;	

	private String carModel;

	private String carType;

	private String licensePlate;

	private String carModelDate;

	private double locationLat;

	private double locationLon;

	TaxiInfo(){}

	public TaxiInfo(String name, String surname, String sex, String username, String password,
			Date dateOfBirth, String address, String city, int zipCode, String email, 
			String creditCardType, String creditCardNumber, String expiryDate, String ccv, 
			String carModel, String carType, String licensePlate, String carModelDate, double locationLat, double locationLon) {
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
		this.carModel = carModel;
		this.carType = carType;
		this.licensePlate = licensePlate;
		this.carModelDate = carModelDate;
		this.locationLat = locationLat;
		this.locationLon = locationLon;
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

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getCarModelDate() {
		return carModelDate;
	}

	public void setCarModelDate(String carModelDate) {
		this.carModelDate = carModelDate;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public static List<TaxiInfo> wrapTaxi(List<Taxi> taxi) {
		List<TaxiInfo> taxiInfo = new ArrayList<>(0);

		for (Taxi t : taxi) {
			TaxiInfo temp = new TaxiInfo();
			temp.setCarModel(t.getCarModel());
			temp.setCarModelDate(t.getCarModelDate());
			temp.setCarType(t.getCarType());
			temp.setLicensePlate(t.getLicensePlate());
			temp.setLocationLat(t.getLocationLat());
			temp.setLocationLon(t.getLocationLon());

			taxiInfo.add(temp);
		}

		return taxiInfo;
	}

}
