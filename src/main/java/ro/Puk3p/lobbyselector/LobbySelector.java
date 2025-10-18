package ro.Puk3p.lobbyselector;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import ro.Puk3p.lobbyselector.adapters.bungee.BungeeLobbyResolver;
import ro.Puk3p.lobbyselector.adapters.bungee.BungeeVersionClassifier;
import ro.Puk3p.lobbyselector.adapters.bungee.VersionRouteListener;
import ro.Puk3p.lobbyselector.config.PluginConfig;
import ro.Puk3p.lobbyselector.services.VersionRoutingService;

public final class LobbySelector extends Plugin {

    @Override
    public void onEnable() {
        PluginConfig cfg = new PluginConfig(this);
        cfg.loadOrCreate();

        BungeeVersionClassifier classifier = new BungeeVersionClassifier(cfg);
        BungeeLobbyResolver resolver = new BungeeLobbyResolver(cfg);
        VersionRoutingService routing = new VersionRoutingService(classifier, resolver);

        ProxyServer.getInstance().getPluginManager()
                .registerListener(this, new VersionRouteListener(routing, cfg));

        getLogger().info("[LobbySelector] Enabled. modernMinProtocol=" + cfg.getModernMinProtocol()
                + " legacy=" + cfg.getLegacyLobby() + " modern=" + cfg.getModernLobby());
    }

    @Override
    public void onDisable() {
        // nimic special
    }
}
