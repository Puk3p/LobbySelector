package ro.Puk3p.lobbyselector.domain;

public class VersionDecision {
    private final TargetLobby target;
    private final String reason;

    public VersionDecision(TargetLobby target, String reason) {
        this.target = target;
        this.reason = reason;
    }

    public TargetLobby getTarget() { return target; }
    public String getReason() { return reason; }

    @Override
    public String toString() {
        return "VersionDecision { target = " + target + ", reason = ' " + reason + "'}";
    }
}
