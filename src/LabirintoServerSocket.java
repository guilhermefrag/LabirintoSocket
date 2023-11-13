import LabirintsLevels.Level;
import LabirintsLevels.LevelOne;
import LabirintsLevels.LevelTwo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class LabirintoServerSocket {
    private static int currentLevelIndex = 0;
    private static int nextLevelIndex = 0;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080, 5);
        LevelOne levelOne = new LevelOne();
        LevelTwo levelTwo = new LevelTwo();

        List<Level> levels = new ArrayList<>();
        levels.add(levelOne);
        levels.add(levelTwo);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Accepted connection from: " + clientSocket);

            Thread clientHandlerThread = new Thread(() -> {
                handleClient(clientSocket, levels);
            });
            clientHandlerThread.start();
        }
    }

    private static void handleClient(Socket clientSocket, List<Level> levels) {
        try (DataInputStream inbound = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream outbound = new DataOutputStream(clientSocket.getOutputStream())) {

            int requestedLevel = inbound.readInt();
            System.out.println("Received level request: " + requestedLevel);

            currentLevelIndex = requestedLevel - 1;
            nextLevelIndex = requestedLevel + 1;

            Level currentLevel = levels.get(currentLevelIndex);
            while(true) {
                try {
                    String moves = inbound.readUTF();

                    String[] correctMoves = currentLevel.getSuccessPath();
                    List<String> clientMoves = new ArrayList<>();

                    for (Character move : moves.toCharArray()) {
                        clientMoves.add(move.toString());
                    }
                    if (currentLevel.sendPath(clientMoves)) {
                        outbound.writeInt(nextLevelIndex);
                        outbound.writeBoolean(true);
                        currentLevelIndex++;

                    } else {
                        outbound.writeInt(currentLevelIndex + 1);
                        outbound.writeBoolean(false);
                        currentLevelIndex = 0;
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}