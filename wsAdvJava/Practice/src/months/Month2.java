package months;

public enum Month2 {
//	JAN(new String[]{}),
//	JAN(new String[]{"January", "január", "j", "j2", "j3"}),
	JAN("January", "január", "j", "j2", "j3"),
//	FEB("February", "február", "f"),
//	MAR("March", "március", "m")
	;

	String[] names;
//	String enName;
//	String huName;

//	Month2(String[] names) {
//		this.names = names;
//	}
//
	// varargs
	Month2(String... names) {
		this.names = names;
	}

	String getEnName() { return names[0]; }
	String getHuName() { return names[1]; }
}
