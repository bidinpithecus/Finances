package presentation;

import java.awt.*;

public enum MyFonts {
	H1("Consolas", Font.BOLD, 18),
	H2("Consolas", Font.PLAIN, 14),
	H3("Consolas", Font.PLAIN, 12),
	;

	private final String font;
	private final int type;
	private final int size;
	MyFonts(String font, int type, int size) {
		this.font = font;
		this.type = type;
		this.size = size;
	}

	Font getFont() {
		return new Font(font, type, size);
	}
}
