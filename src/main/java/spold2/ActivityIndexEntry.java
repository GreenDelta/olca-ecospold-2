package spold2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class ActivityIndexEntry {

	@XmlAttribute
	public String id;

	@XmlAttribute
	public String activityNameId;

	@XmlAttribute
	public String geographyId;

	@XmlAttribute
	public Date startDate;

	@XmlAttribute
	public Date endDate;

	@XmlAttribute
	public String systemModelId;

}
