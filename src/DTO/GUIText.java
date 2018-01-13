package DTO;

public class GUIText {
	private String fieldName;
	private String fieldText;
	
	public GUIText(String fieldName, String fieldText){
		this.fieldName = fieldName;
		this.fieldText = fieldText;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldText() {
		return fieldText;
	}

	public void setFieldText(String fieldText) {
		this.fieldText = fieldText;
	}

	public String toRead(){
		return fieldName+";"+fieldText;
	}
	
}
