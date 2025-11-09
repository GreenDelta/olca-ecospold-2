package spold2;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class IOTest {

	private final List<File> tempFiles = new ArrayList<>();

	@After
	public void cleanup() throws IOException {
		for (var file : tempFiles) {
			Files.delete(file.toPath());
		}
	}

	@Test
	public void testDataSetIO() throws IOException {
		var stream = IOTest.class.getResourceAsStream("sample_ecospold2.xml");
		try (stream) {
			var ds = EcoSpold2.read(stream).activity();
			assertEquals("Sample", ds.description.activity.name);

			var temp = createTempFile("_es2_test_file.xml");
			EcoSpold2.write(ds, temp);

			var copy = EcoSpold2.read(temp).activity();
			Assert.assertEquals("Sample", copy.description.activity.name);
		}
	}

	@Test
	public void testPersonListIO() throws IOException {
		var stream = IOTest.class.getResourceAsStream("Persons.xml");
		try (stream) {
			var list = IO.read(stream, PersonList.class);
			assertEquals("Person Name", list.persons.get(0).name);

			var temp = createTempFile("_es2_test_persons_file.xml");
			IO.write(list, temp);

			var copy = IO.read(temp, PersonList.class);
			assertEquals("Person Name", copy.persons.get(0).name);
		}
	}

	@Test
	public void testSourceListIO() throws IOException {
		var stream = IOTest.class.getResourceAsStream("Sources.xml");
		try (stream) {
			var list = IO.read(stream, SourceList.class);
			assertEquals("title", list.sources.get(0).title);

			var temp = createTempFile("_es2_test_sources_file.xml");
			IO.write(list, temp);

			var copy = IO.read(temp, SourceList.class);
			assertEquals("title", copy.sources.get(0).title);
		}
	}

	private File createTempFile(String suffix) {
		try {
			File tempFile = Files.createTempFile("test", suffix).toFile();
			tempFiles.add(tempFile);
			return tempFile;
		} catch (Exception e) {
			throw new RuntimeException("Failed to create temp file", e);
		}
	}

}
