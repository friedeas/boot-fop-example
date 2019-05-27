package de.u808;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomModelObject {

	String codeValue;

	public CustomModelObject() {
		codeValue = null;
	}

	public CustomModelObject(final String value) {
		this.codeValue = value;
	}

	public String getCodeValue() {
		return codeValue;
	}

	@XmlElement
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

}
