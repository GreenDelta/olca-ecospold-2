package spold2;

import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import java.io.File;
import java.io.InputStream;
import java.util.function.Consumer;

/**
 * Archive can directly read EcoSpold 2 data from 7z packages that are
 * distributed by the ecoinvent centre.
 */
public class Archive implements AutoCloseable {

	private final SevenZFile zip;

	private Archive(SevenZFile zip) {
		this.zip = zip;
	}

	public static Archive open(File file) {
		try {
			var zip = new SevenZFile(file);
			return new Archive(zip);
		} catch (Exception e) {
			throw err("failed to open " + file, e);
		}
	}

	@Override
	public void close() {
		try {
			zip.close();
		} catch (Exception e) {
			throw err("failed to close archive", e);
		}
	}

	public IntermediateExchangeList getIntermediateExchangeList() {
		var stream = findEntry("IntermediateExchanges.xml");
		if (stream == null)
			return new IntermediateExchangeList();
		try (stream) {
			return IntermediateExchangeList.readFrom(stream);
		} catch (Exception e) {
			throw err("failed to parse intermediate exchanges", e);
		}
	}

	public ElementaryExchangeList getElementaryExchangeList() {
		var stream = findEntry("ElementaryExchanges.xml");
		if (stream == null)
			return new ElementaryExchangeList();
		try (stream) {
			return ElementaryExchangeList.readFrom(stream);
		} catch (Exception e) {
			throw err("failed to parse elementary exchanges", e);
		}
	}

	public PersonList getPersonList() {
		var stream = findEntry("Persons.xml");
		if (stream == null)
			return new PersonList();
		try (stream) {
			return PersonList.readFrom(stream);
		} catch (Exception e) {
			throw err("failed to parse person list", e);
		}
	}

	public SourceList getSourceList() {
		var stream = findEntry("Sources.xml");
		if (stream == null)
			return new SourceList();
		try (stream) {
			return SourceList.readFrom(stream);
		} catch (Exception e) {
			throw err("failed to parse source list", e);
		}
	}

	public UnitList getUnitList() {
		var stream = findEntry("Units.xml");
		if (stream == null)
			return new UnitList();
		try (stream) {
			return UnitList.readFrom(stream);
		} catch (Exception e) {
			throw err("failed to parse unit list", e);
		}
	}

	public UnitConversionList getUnitConversionList() {
		var stream = findEntry("UnitConversions.xml");
		if (stream == null)
			return new UnitConversionList();
		try (stream) {
			return UnitConversionList.readFrom(stream);
		} catch (Exception e) {
			throw err("failed to parse unit conversion list", e);
		}
	}

	public void eachActivityDataSet(Consumer<DataSet> fn) {
		if (fn == null)
			return;
		for (var entry : zip.getEntries()) {
			var name = entry.getName();
			if (name == null || !name.endsWith(".spold"))
				continue;
			try (var stream = zip.getInputStream(entry)) {
				var datSet = EcoSpold2.read(stream).activity();
				if (datSet != null) {
					fn.accept(datSet);
				}
			} catch (Exception e) {
				throw err("failed to parse entry: " + name, e);
			}
		}
	}

	private InputStream findEntry(String name) {
		for (var entry : zip.getEntries()) {
			var n = entry.getName();
			if (n == null)
				continue;
			if (n.contains(name)) {
				try {
					return zip.getInputStream(entry);
				} catch (Exception e) {
					throw err("failed to read entry: " + n, e);
				}
			}
		}
		return null;
	}

	private static RuntimeException err(String message, Exception cause) {
		return new RuntimeException(message, cause);
	}

}
