package ro.Puk3p.lobbyselector.config;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class PluginConfig {
    private final Plugin plugin;
    private String legacyLobby;
    private String modernLobby;
    private int modernMinProtocol;
    private boolean fallbackToOther;
    private String kickMessage;

    public PluginConfig(Plugin plugin) {
        this.plugin = plugin;
    }

    public void loadOrCreate() {
        try {
            File folder = plugin.getDataFolder();
            if (!folder.exists()) folder.mkdirs();

            File file = new File(folder, "config.yml");
            if (!file.exists()) {
                try {
                    Files.copy(plugin.getResourceAsStream("config.yml"), file.toPath());
                } catch (IOException e) {
                    plugin.getLogger().warning("Nu am putut salva config.yml: " + e.getMessage());
                }
            }

            Configuration cfg = ConfigurationProvider.getProvider(YamlConfiguration.class)
                    .load(file);
            this.legacyLobby       = cfg.getString(ConfigKeys.LEGACY_LOBBY, "lobby_1_8");
            this.modernLobby       = cfg.getString(ConfigKeys.MODERN_LOBBY, "lobby_1_16");
            this.modernMinProtocol = cfg.getInt(ConfigKeys.MODERN_MIN_PROTOCOL, 735);
            this.fallbackToOther   = cfg.getBoolean(ConfigKeys.FALLBACK_TO_OTHER, true);
            this.kickMessage       = cfg.getString(ConfigKeys.KICK_MESSAGE,
                    "&cNu s-a putut gasi un lobby disponibil. Incearca din nou!");

        } catch (IOException exception) {
            plugin.getLogger().severe("Eroare la incarcarea config.yml: " + exception.getMessage());
            this.legacyLobby = "lobby_1_8";
            this.modernLobby = "lobby_1_16";
            this.modernMinProtocol = 735;
            this.fallbackToOther = true;
            this.kickMessage = "&cNu s-a putut gasi un lobby disponibil. Incearcă din nou!";
        }
    }


    public String getLegacyLobby() { return legacyLobby; }
    public String getModernLobby() { return modernLobby; }
    public int getModernMinProtocol() { return modernMinProtocol; }
    public boolean isFallbackToOther() { return fallbackToOther; }
    public String getKickMessage() { return kickMessage; }
}
