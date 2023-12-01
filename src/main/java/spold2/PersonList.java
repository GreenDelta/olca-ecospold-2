package spold2;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "validPersons")
public class PersonList extends MasterDataList {

	@XmlElement(name = "person")
	public final List<Person> persons = new ArrayList<>();

	public static PersonList readFrom(InputStream stream) {
		return stream != null
			? IO.read(stream, PersonList.class)
			: new PersonList();
	}

}
