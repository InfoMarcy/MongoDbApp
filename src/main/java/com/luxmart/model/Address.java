package com.luxmart.model;

public class Address {

	private String streetNumber;
	private String streetName;
	private String apartmentNumber;
	private String colonia;
	private String delegacion;
	private String state;
	private String country;
	private String postalCode;

	protected Address() {
	}

	public Address(String streetNumber, String streetName, String apartmentNumber, String colonia, String delegacion,
			String state, String country, String postalCode) {
		super();
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.apartmentNumber = apartmentNumber;
		this.colonia = colonia;
		this.delegacion = delegacion;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getApartmentNumber() {
		return apartmentNumber;
	}

	public String getColonia() {
		return colonia;
	}

	public String getDelegacion() {
		return delegacion;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getPostalCode() {
		return postalCode;
	}

}
