package spold2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "validSources")
public class SourceList extends MasterDataList {

	@XmlElement(name = "source")
	public final List<Source> sources = new ArrayList<>();

	public static SourceList readFrom(InputStream stream) {
		return stream != null
			? IO.read(stream, SourceList.class)
			: new SourceList();
	}
}
