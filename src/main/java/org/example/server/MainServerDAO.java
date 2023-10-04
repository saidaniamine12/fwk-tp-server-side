package org.example.server;

import java.io.IOException;

public interface MainServerDAO {

    void start() throws IOException;

    void processClient(SocketMainServerDAO.ClientSession clientSession);
}
