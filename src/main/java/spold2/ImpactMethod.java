package spold2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImpactMethod {

	@XmlAttribute
	public String id;

	@XmlElement(name = "name")
	public String name;

	@XmlElement(name = "category")
	public final List<ImpactCategory> categories = new ArrayList<>();
}
