package com.mhtechin.empapp;


public class Address {
	
	private int addressId;
	private String addressline1;
	private String locality;
	private String city;
	private int pincode;
	private int empId;
	
	public Address(int addressId, String addressline1,String locality,String city,int pincode, int empId)
	{
		this.addressId = addressId;
		this.addressline1=addressline1;
		this.locality=locality;
		this.city=city;
		this.pincode=pincode;
		this.empId = empId;
	}
	
	public Address()
	{
		super();
	}
	
	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}


	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
    public String toString()
    {
	
		return addressId+","+addressline1+","+locality+","+city+","+pincode+","+empId; 
    }

}
