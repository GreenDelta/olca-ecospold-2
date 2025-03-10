package spold2;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class PropertyListTest {

	private PropertyList list;

	@Before
	public void setup() throws IOException {
		var file = "properties.xml";
		try (var stream = getClass().getResourceAsStream(file)) {
			list = IO.read(stream, PropertyList.class);
		}
	}

	@Test
	public void testSize() {
		assertEquals(3, list.properties.size());
	}

	@Test
	public void testIdIsMasterId() {
		var id = "c6b7e792-54d1-489a-b8e5-17394c281a35";
		var prop = list.properties.stream()
			.filter(p -> id.equals(p.masterId))
			.findFirst()
			.orElseThrow();
		assertEquals("mass concentration, copper", prop.name);
	}

}
