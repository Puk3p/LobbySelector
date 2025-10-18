package ro.Puk3p.lobbyselector.util;

import net.md_5.bungee.api.ChatColor;

public final class Text {
    private Text() {}

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
