package spold2;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ExtractProductPropertiesExample {

	public static void main(String[] args) {

		var propList = IO.read(
			new File("target/Properties.xml"), PropertyList.class);
		var exchList = IO.read(
			new File("target/IntermediateExchanges.xml"),
			IntermediateExchangeList.class);

		var props = propList.properties.stream()
			.collect(Collectors.toMap(p -> p.masterId, p -> p, (a, b) -> a));

		var csv = new StringBuilder(
			"exchange id,exchange name,property id,property name,amount,unit\n");
		for (var e : exchList.exchanges) {
			for (var p : e.properties) {
				var prop = props.get(p.id);
				if (prop == null) {
					continue;
				}
				csv.append('"')
					.append(e.id).append('"').append(',')
					.append('"').append(e.name).append('"').append(',')
					.append('"').append(p.id).append('"').append(',')
					.append('"').append(prop.name).append('"').append(',')
					.append(p.amount).append(",")
					.append('"').append(prop.unit != null ? prop.unit : "").append('"')
					.append('\n');
			}
		}

		try (var writer = new FileWriter(
			"target/exchange_properties.csv", StandardCharsets.UTF_8)) {
			writer.write(csv.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
