package ro.Puk3p.lobbyselector.services;

import ro.Puk3p.lobbyselector.domain.VersionDecision;
import ro.Puk3p.lobbyselector.ports.LobbyResolver;
import ro.Puk3p.lobbyselector.ports.VersionClassifier;

public class VersionRoutingService {
    private final VersionClassifier classifier;
    private final LobbyResolver resolver;

    public VersionRoutingService(VersionClassifier classifier, LobbyResolver resolver) {
        this.classifier = classifier;
        this.resolver = resolver;
    }

    public Result decideTarget(int protocolVersion) {
        VersionDecision decision = classifier.classify(protocolVersion);
        String serverName = resolver.resolveServerName(decision.getTarget());

        return new Result(serverName, decision);
    }


    public static final class Result {
        private final String serverName;
        private final VersionDecision decision;

        public Result(String serverName, VersionDecision decision) {
            this.serverName = serverName;
            this.decision = decision;
        }

        public String getServerName() { return serverName; }
        public VersionDecision getDecision() { return decision; }
    }
}
