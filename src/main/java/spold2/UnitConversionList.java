package spold2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "validUnitConversions")
public class UnitConversionList extends MasterDataList {

	@XmlElement(name = "unitConversion")
	public final List<UnitConversion> conversions = new ArrayList<>();

	public static UnitConversionList readFrom(InputStream stream) {
		return stream != null
			? IO.read(stream, UnitConversionList.class)
			: new UnitConversionList();
	}

}
