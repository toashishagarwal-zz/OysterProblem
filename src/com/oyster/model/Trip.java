package com.oyster.model;

public class Trip {
	private Mode mode;
	private String source;
	private String destination;
	private Double charge;
	
	public Trip() {
	}

	public Trip(Mode mode, String source, String destination, Double charge) {
		super();
		this.mode = mode;
		this.source = source;
		this.destination = destination;
		this.charge = charge;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}
}
