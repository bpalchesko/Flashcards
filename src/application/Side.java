package application;

public enum Side {
	FRONT, BACK;
	
	public Side not() {
		if(this == FRONT) {
			return BACK;
		} else {
			return FRONT;
		}
	}
}
