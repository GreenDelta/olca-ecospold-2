package spold2;

import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import java.io.File;
import java.io.InputStream;

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

	/**
	 * Reads the master data list of intermediate exchanges from the archive.
	 * Returns an empty list if the master data file could not be found.
	 */
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
