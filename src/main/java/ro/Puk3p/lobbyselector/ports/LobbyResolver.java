package ro.Puk3p.lobbyselector.ports;

import ro.Puk3p.lobbyselector.domain.TargetLobby;

public interface LobbyResolver {
    String resolveServerName(TargetLobby lobby);
}
