package taxi.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author nyxteridas
 * το ID πρέπει να είναι autoincrement
 * 
 * carmodeldate μόνο ημερομηνία
 *  
 *
 */

public class Taxi {
	private int id;
	private String carModel;
	private String carType;
	private String licensePlate;
	private Date carModelDate;
	private String location;
	private List<Request> accepts = new ArrayList<Request>();

	public Taxi(int id, String carModel, String carType, String licensePlate, Date carModelDate, String location) {
		this.id = id;
		this.carModel = carModel;
		this.carType = carType;
		this.licensePlate = licensePlate;
		this.carModelDate = carModelDate;
		this.location = location;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCarModel() {
		return this.carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarType() {
		return this.carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getLicensePlate() {
		return this.licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public Date getCarModelDate() {
		return this.carModelDate;
	}
	
	public void setCarModelDate(Date carModelDate) {
		this.carModelDate = carModelDate;
	}
	
	public List<Request> getRequest() {
		return this.accepts;
	}
	
	public void addRequest(Request req) {
		this.accepts.add(req);
	}
	
}