package spold2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class Time {

	@XmlAttribute(name = "startDate")
	public Date start;

	@XmlAttribute(name = "endDate")
	public Date end;

	@XmlAttribute(name = "isDataValidForEntirePeriod")
	public boolean dataValid;

	public RichText comment;

}
