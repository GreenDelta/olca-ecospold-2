package spold2;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ExtractProductPropertiesExample {

	public static void main(String[] args) {
		var path = "/home/ms/Downloads/ecoinvent 3.12_cutoff_ecoSpold02.7z";
		try (var a = Archive.open(new File(path))) {
			var exchList = a.getIntermediateExchangeList();
			var propList = a.getPropertyList();

			var props = propList.properties.stream()
				.collect(Collectors.toMap(
					p -> p.masterId, p -> p, (pi, pj) -> pi));

			var csv = new StringBuilder(
				"exchange id,"
				+ "exchange name,"
				+ "property id,"
				+ "property name,"
				+ "amount,"
				+ "unit,"
				+ "comment\n");

			for (var e : exchList.exchanges) {
				for (var p : e.properties) {
					var prop = props.get(p.id);
					if (prop == null)
						continue;
					var unit = p.unit != null && !p.unit.isBlank()
						? p.unit
						: prop.unit;

					cons(csv, e.id, ',');
					cons(csv, e.name, ',');
					cons(csv, p.id, ',');
					cons(csv, prop.name, ',');
					csv.append(p.amount).append(',');
					cons(csv, unit, ',');
					cons(csv, p.comment, '\n');
				}
			}

			try (var writer = new FileWriter(
				"/home/ms/Downloads/exchange_properties.csv", StandardCharsets.UTF_8)) {
				writer.write(csv.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void cons(StringBuilder b, String s, char suffix) {
		b.append('"')
			.append(s != null ? s : "")
			.append('"')
			.append(suffix);
	}

}
