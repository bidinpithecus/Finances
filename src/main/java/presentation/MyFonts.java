package presentation;

import java.awt.*;

public enum MyFonts {
	H1Bold("Consolas", Font.BOLD, 18),
	H1Plain("Consolas", Font.PLAIN, 18),
	H2Plain("Consolas", Font.PLAIN, 14),
	H2Bold("Consolas", Font.BOLD, 14),
	H3Plain("Consolas", Font.PLAIN, 12),
	H3Bold("Consolas", Font.BOLD, 12),
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
