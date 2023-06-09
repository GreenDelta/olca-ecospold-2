package spold2;

import jakarta.xml.bind.JAXB;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.StringWriter;

import static org.junit.Assert.*;

@RunWith(Theories.class)
public class JaxbTest {

	@DataPoints
	public static String[] dataSets = { "sample_ecospold2.xml",
			"sample_child_ecospold2.xml" };

	@Theory
	public void testDataSetNotNull(String file) {
		DataSet dataSet = read(file);
		assertNotNull(dataSet);
	}

	@Theory
	public void testActivity(String file) {
		DataSet dataSet = read(file);
		Activity a = dataSet.description.activity;
		assertEquals("08a78e38-fdbe-4ea8-869f-7735b41ecf85", a.id);
		assertEquals("Sample", a.name);
		assertEquals("synonym 1", a.synonyms.get(0));
		assertEquals("synonym 2", a.synonyms.get(1));
		assertEquals("included activities start",
				a.includedActivitiesStart);
		assertEquals("included activities end",
				a.includedActivitiesEnd);
		assertEquals("tag1", a.tags.get(0));
		assertEquals("tag2", a.tags.get(1));
		assertEquals("allocation comment",
				RichText.join(a.allocationComment));
		assertEquals("sample comment 2",
				a.generalComment.texts.get(1).value);
	}

	@Theory
	public void testClassification(String file) {
		DataSet dataSet = read(file);
		Classification c = dataSet.description.classifications.get(1);
		assertEquals("359b3be8-bc56-4abf-a04b-294115165214", c.id);
		assertEquals("EcoSpold01Categories", c.system);
		assertEquals("agricultural means of production/mineral fertiliser", c.value);
	}

	private DataSet read(String file) {
		var stream = getClass().getResourceAsStream(file);
		assertNotNull(stream);
		EcoSpold2 spold = JAXB.unmarshal(stream, EcoSpold2.class);
		StringWriter xml = new StringWriter();
		JAXB.marshal(spold, xml);
		return spold.dataSet != null ? spold.dataSet : spold.childDataSet;
	}
}
