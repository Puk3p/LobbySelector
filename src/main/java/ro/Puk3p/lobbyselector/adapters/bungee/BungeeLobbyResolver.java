package ro.Puk3p.lobbyselector.adapters.bungee;

import ro.Puk3p.lobbyselector.config.PluginConfig;
import ro.Puk3p.lobbyselector.domain.TargetLobby;
import ro.Puk3p.lobbyselector.ports.LobbyResolver;

public class BungeeLobbyResolver implements LobbyResolver {
    private final PluginConfig cfg;

    public BungeeLobbyResolver(PluginConfig cfg) {
        this.cfg = cfg;
    }

    @Override
    public String resolveServerName(TargetLobby lobby) {
        switch (lobby) {
            case MODERN: return cfg.getModernLobby();
            case LEGACY:
            default: return cfg.getLegacyLobby();
        }
    }
}
