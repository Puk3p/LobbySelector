package ro.Puk3p.lobbyselector.adapters.bungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import ro.Puk3p.lobbyselector.config.PluginConfig;
import ro.Puk3p.lobbyselector.services.VersionRoutingService;
import ro.Puk3p.lobbyselector.util.Text;

public class VersionRouteListener implements Listener {
    private final VersionRoutingService routing;
    private final PluginConfig cfg;

    public VersionRouteListener(VersionRoutingService routing, PluginConfig cfg) {
        this.routing = routing;
        this.cfg = cfg;
    }

    @EventHandler
    public void onConnect(ServerConnectEvent event) {
        if (event.getReason() != ServerConnectEvent.Reason.JOIN_PROXY) return;

        ProxiedPlayer player = event.getPlayer();
        int protocol = player.getPendingConnection().getVersion();

        VersionRoutingService.Result result = routing.decideTarget(protocol);
        String targetName = result.getServerName();

        ServerInfo target = ProxyServer.getInstance().getServerInfo(targetName);
        if (target != null) {
            event.setTarget(target);
            return;
        }


        if (cfg.isFallbackToOther()) {
            String other = targetName.equalsIgnoreCase(cfg.getModernLobby())
                    ? cfg.getLegacyLobby() : cfg.getModernLobby();

            ServerInfo alt = ProxyServer.getInstance().getServerInfo(other);
            if (alt != null) {
                event.setTarget(alt);
                return;
            }
        }

        player.disconnect(Text.color(cfg.getKickMessage()));
    }
}
