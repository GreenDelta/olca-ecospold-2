package spold2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArchiveExample {

	public static void main(String[] args) {
		var path = "/mnt/extssd/ei-3.10/cutoff.7z";
		try (var a = Archive.open(new File(path))) {
			var list = a.getIntermediateExchangeList();

			var count = new HashMap<String, Integer>();
			var sysMap = new HashMap<String, List<Classification>>();

			for (var e : list.exchanges) {
				for (var c : e.classifications) {
					if (!count.containsKey(c.id)) {
						sysMap.computeIfAbsent(c.system, s -> new ArrayList<>()).add(c);
					}
					count.compute(c.id, (id, num) -> num == null ? 1 : num + 1);
				}
			}

			var systems = new ArrayList<>(sysMap.keySet());
			systems.sort(String::compareToIgnoreCase);
			for (var s : systems) {
				System.out.println(s);
				sysMap.get(s).stream()
					.sorted((c1, c2) -> c1.value.compareToIgnoreCase(c2.value))
					.forEach(c -> System.out.printf(
						"  - %s :: %d%n", c.value, count.get(c.id)));
			}
		}
	}
}
