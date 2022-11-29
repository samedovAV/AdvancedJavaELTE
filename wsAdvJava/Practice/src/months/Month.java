package months;

public enum Month {
	JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP;
	
	String getEngName(/* Month this, */) {
		switch (this) {
		case JAN: return "January";
		case FEB: return "February";
		case MAR: return "March";
		default:  return "totally fake return forced on me by Java";
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + "="
				+ getEngName();
	}
}
