package spold2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "validIntermediateExchanges")
public class IntermediateExchangeList extends MasterDataList {

	@XmlElement(name = "intermediateExchange")
	public final List<IntermediateExchange> exchanges = new ArrayList<>();

	public static IntermediateExchangeList readFrom(InputStream stream) {
		return stream != null
			? IO.read(stream, IntermediateExchangeList.class)
			: new IntermediateExchangeList();
	}
}
