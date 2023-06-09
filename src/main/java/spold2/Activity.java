package spold2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Activity {

	@XmlAttribute
	public String id;

	@XmlAttribute
	public String activityNameId;

	@XmlAttribute
	public String activityNameContextId;

	@XmlAttribute
	public String parentActivityId;

	@XmlAttribute
	public String parentActivityContextId;

	@XmlAttribute
	public Integer inheritanceDepth;

	@XmlAttribute
	public int type;

	@XmlAttribute
	public int specialActivityType;

	@XmlAttribute
	public Integer energyValues;

	@XmlAttribute
	public String masterAllocationPropertyId;

	@XmlAttribute
	public String masterAllocationPropertyContextId;

	@XmlElement(name = "activityName")
	public String name;

	@XmlElement(name = "synonym")
	public final List<String> synonyms = new ArrayList<String>();

	public String includedActivitiesStart;

	public String includedActivitiesEnd;

	public RichText allocationComment;

	public RichText generalComment;

	@XmlElement(name = "tag")
	public final List<String> tags = new ArrayList<String>();

}
