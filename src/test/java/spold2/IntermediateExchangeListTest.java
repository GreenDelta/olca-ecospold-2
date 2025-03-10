package spold2;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class IntermediateExchangeListTest {

	private IntermediateExchangeList list;

	@Before
	public void setup() throws IOException  {
		var file= "intermediate_exchanges.xml";
		try (var stream = getClass().getResourceAsStream(file)) {
			list = IO.read(stream, IntermediateExchangeList.class);
		}
	}

	@Test
	public void testSize() {
		assertEquals(2, list.exchanges.size());
	}

	@Test
	public void testProperties() {
		var priceProp = "27a84e12-9c5d-46b3-8061-f3142d97518c";
		for (var e : list.exchanges) {
			assertFalse(e.properties.isEmpty());
			boolean priceFound = false;
			for (var p : e.properties) {
				if (priceProp.equals(p.id)) {
					priceFound = true;
					break;
				}
			}
			assertTrue(priceFound);
		}
	}
}
