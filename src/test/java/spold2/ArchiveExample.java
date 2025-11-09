package spold2;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class ArchiveExample {

	public static void main(String[] args) {
		var path = "/home/ms/Downloads/ecoinvent 3.12_cutoff_ecoSpold02.7z";
		var start = System.nanoTime();
		System.out.println("Parse package ...");
		try (var a = Archive.open(new File(path))) {

			var count = new AtomicInteger();
			a.eachActivityDataSet(ds -> {
				var i = count.incrementAndGet();
				if (i % 1000 == 0) {
					System.out.printf("  ... parsed %d data sets%n", i);
				}
			});

			System.out.printf("%-23s | %d%n", "activities", count.get());

			int ie = a.getIntermediateExchangeList().exchanges.size();
			System.out.printf("%-23s | %d%n", "intermediate exchanges", ie);

			int ee = a.getElementaryExchangeList().exchanges.size();
			System.out.printf("%-23s | %d%n", "elementary exchanges", ee);

			int us = a.getUnitList().units.size();
			System.out.printf("%-23s | %5d%n", "units", us);

			int uc = a.getUnitConversionList().conversions.size();
			System.out.printf("%-23s | %5d%n", "unit conversions", uc);

			int ps = a.getPersonList().persons.size();
			System.out.printf("%-23s | %5d%n", "persons", ps);

			int ss = a.getSourceList().sources.size();
			System.out.printf("%-23s | %5d%n", "sources", ss);
		}

		var time = System.nanoTime() - start;
		var s = (time) / 1e9;
		System.out.printf("parsed package in %.2f seconds%n", s);
	}
}
