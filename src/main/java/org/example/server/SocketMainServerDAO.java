package org.example.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketMainServerDAO implements MainServerDAO{

    private  ServerSocket serverSocket;
    private final static int port = 4444;
    private Map<Integer,ClientSession> playerToSocketMap = new HashMap<>();

    public SocketMainServerDAO() {
    }
    @Override
    public void start() {
        try {
            this.serverSocket = new ServerSocket();

            while (true){
                Socket clientSocket = serverSocket.accept();

                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

                Long playerId = objectInputStream.readLong();
                ClientSession clientSession = new ClientSession(clientSocket,objectInputStream,objectOutputStream,playerId);
                playerToSocketMap.put(playerId.intValue(),clientSession);
                processClient(clientSession);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void processClient(ClientSession clientSession) {

    }

    public class ClientSession {
        private Socket clientSocket;
        private ObjectInputStream objectInputStream;
        private ObjectOutputStream objectOutputStream;
        private Long playerId;
        public ClientSession() {
        }
        public ClientSession(Socket clientSocket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream, Long playerId) {
            this.clientSocket = clientSocket;
            this.objectInputStream = objectInputStream;
            this.objectOutputStream = objectOutputStream;
            this.playerId = playerId;
        }
    }





}
