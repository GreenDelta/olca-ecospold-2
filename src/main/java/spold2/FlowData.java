package spold2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class FlowData {

	@XmlElement(name = "intermediateExchange")
	public final List<IntermediateExchange> intermediateExchanges = new ArrayList<>();

	@XmlElement(name = "elementaryExchange")
	public final List<ElementaryExchange> elementaryExchanges = new ArrayList<>();

	@XmlElement(name = "parameter")
	public final List<Parameter> parameters = new ArrayList<>();

}
