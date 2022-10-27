package presentation;

public enum MyColors {
	TITLE("#383838"),
	SUBTITLE("#D9D9D9"),
	DARK_GREEN("#2BBF95"),
	LIGHT_GREEN("#28DEAA"),
	MUSTARD("#F6BA58"),
	RED("#FB7575"),
	BLUE("#40A3E5"),
	;

	private final String color;
	MyColors(String string) {
		this.color = string;
	}

	@Override
	public String toString() {
		return color;
	}
}
