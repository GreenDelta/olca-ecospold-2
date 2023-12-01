package spold2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "validElementaryExchanges")
public class ElementaryExchangeList extends MasterDataList {

	@XmlElement(name = "elementaryExchange")
	public final List<ElementaryExchange> exchanges = new ArrayList<>();

	public static ElementaryExchangeList readFrom(InputStream stream) {
		return stream != null
			? IO.read(stream, ElementaryExchangeList.class)
			: new ElementaryExchangeList();
	}
}
