package months;

import java.util.Map;

public enum Months3 {
	JAN(Map.of("en", "January", "hu", "január", "xyz", "j"))
	;

	// dictionary
	// associative array
	Map<String, String> names;

	Months3(Map<String, String> names) {
		this.names = names;
	}

//	String getEnName() { return names.get("en"); }
//	String getHuName() { return names.get("hu"); }

	String getName(String lang) { return names.get(lang); }
}
