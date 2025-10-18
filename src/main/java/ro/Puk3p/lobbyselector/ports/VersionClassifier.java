package ro.Puk3p.lobbyselector.ports;

import ro.Puk3p.lobbyselector.domain.VersionDecision;

public interface VersionClassifier {
    VersionDecision classify(int protocolVersion);
}
