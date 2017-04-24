package DTO;

public class SingleCodeDTO {
	private String pinKode;
	private String systemNummer;
	private String position;

	public SingleCodeDTO(String pinKode, String systemNummer) {
		this.pinKode = pinKode;
		this.systemNummer = systemNummer;
		this.position = "";
	}

	public SingleCodeDTO(String pinKode, String systemNummer, String position) {
		this.pinKode = pinKode;
		this.systemNummer = systemNummer;
		this.position = position;
	}

	public String getPinKode() {
		return pinKode;
	}

	public void setPinKode(String pinKode) {
		this.pinKode = pinKode;
	}

	public String getSystemNummer() {
		return systemNummer;
	}

	public void setSystemNummer(String systemNummer) {
		this.systemNummer = systemNummer;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
