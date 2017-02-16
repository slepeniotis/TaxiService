package TaxiService;

import java.util.Vector;
import java.util.Date;

/**
 * 
 * @author nyxteridas
 * το ID πρέπει να είναι autoincrement
 * αλλαγή των vectors σε λίστες
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
	public Vector<TaxiDriver> owns = new Vector<TaxiDriver>();
	public Vector<Koursa> unnamed_Koursa = new Vector<Koursa>();

	public Taxi(int id, String carModel, String carType, String licensePlate, Date carModelDate) {
		this.id = id;
		this.carModel = carModel;
		this.carType = carType;
		this.licensePlate = licensePlate;
		this.carModelDate = carModelDate;
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
	
	public Date getCarModelDate() {
		return this.carModelDate;
	}
	
	public void setCarModelDate(Date carModelDate) {
		this.carModelDate = carModelDate;
	}

	protected void addTaxi() {
		throw new UnsupportedOperationException();
	}
}