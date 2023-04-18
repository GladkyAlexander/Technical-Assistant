package ru.greatlarder.technicalassistant.domain;

public class ScanObj {
	String ip;
	String mac;
	String manufacturer;
	
	public ScanObj(String ip, String mac, String manufacturer) {
		this.ip = ip;
		this.mac = mac;
		this.manufacturer = manufacturer;
	}
	
	public ScanObj() {
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getMac() {
		return mac;
	}
	
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	@Override
	public String toString() {
		return "ScanObj{" +
				"ip='" + ip + '\'' +
				", mac='" + mac + '\'' +
				", manufacturer='" + manufacturer + '\'' +
				'}';
	}
}
