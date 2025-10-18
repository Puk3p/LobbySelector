package ro.Puk3p.lobbyselector.adapters.bungee;

import ro.Puk3p.lobbyselector.config.PluginConfig;
import ro.Puk3p.lobbyselector.domain.TargetLobby;
import ro.Puk3p.lobbyselector.domain.VersionDecision;
import ro.Puk3p.lobbyselector.ports.VersionClassifier;

public class BungeeVersionClassifier implements VersionClassifier {
    private final PluginConfig cfg;

    public BungeeVersionClassifier(PluginConfig cfg) {
        this.cfg = cfg;
    }

    @Override
    public VersionDecision classify(int protocolVersion) {
        int minModern = cfg.getModernMinProtocol();
        if (protocolVersion >= minModern) {
            return new VersionDecision(TargetLobby.MODERN,
                    "protocol " + protocolVersion + " >= " + minModern);
        }
        return new VersionDecision(TargetLobby.LEGACY,
                "protocol " + protocolVersion + " >= " + minModern);
    }
}
