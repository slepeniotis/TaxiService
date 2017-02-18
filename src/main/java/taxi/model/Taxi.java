package taxi.model;

import java.util.List;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author nyxteridas
 * 
 * carmodeldate μόνο ημερομηνία
 *  
 *
 */

@Entity
@Table(name = "Taxi")
public class Taxi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "carModel", length = 10, nullable = false)
	private String carModel;
	
	@Column(name = "carType", length = 10, nullable = false)
	private String carType;
	
	@Column(name = "licensePlate", length = 7, nullable = false)
	private String licensePlate;
	
	@Column(name = "carModelDate", length = 10, nullable = false)
	private Date carModelDate;
	
	@Column(name = "location", length = 30, nullable = false)
	private String location;
	
	@OneToMany(mappedBy="Taxi")
	private List<Request> accepts = new ArrayList<Request>();
	
	public Taxi(){}
	public Taxi(String carModel, String carType, String licensePlate, Date carModelDate, String location) {
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