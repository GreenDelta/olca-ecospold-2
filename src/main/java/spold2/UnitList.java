package spold2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "validUnits")
public class UnitList extends MasterDataList {

	@XmlElement(name = "unit")
	public final List<Unit> units = new ArrayList<>();

	public static UnitList readFrom(InputStream stream) {
		return stream != null
			? IO.read(stream, UnitList.class)
			: new UnitList();
	}
}
