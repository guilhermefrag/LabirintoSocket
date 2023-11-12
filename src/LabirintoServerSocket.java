import LabirintsLevels.Level;
import LabirintsLevels.LevelOne;
import LabirintsLevels.LevelTwo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class LabirintoServerSocket {
    private static int currentLevelIndex = 0;

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

            if (requestedLevel >= 1 && requestedLevel <= levels.size()) {
                currentLevelIndex = requestedLevel - 1;
            }

            Integer nextLevelIndex = requestedLevel + 1;
            Level currentLevel = levels.get(currentLevelIndex);
            outbound.writeUTF("You are now on Level " + (currentLevelIndex + 1) + "\n");

            while (true) {
                try {
                    String moves = inbound.readUTF();
                    String[] correctMoves = currentLevel.getSuccessPath();
                    List<String> clientMoves = new ArrayList<>();

                    for (Character move : moves.toCharArray()) {
                        System.out.println("Client move: " + move);
                        clientMoves.add(move.toString());
                    }

                    System.out.println("Client moves: " + clientMoves);
                    if(currentLevel.sendPath(clientMoves)) {
                        outbound.writeInt(nextLevelIndex);
                        outbound.writeBoolean(true);
                        currentLevelIndex++;
                        if(currentLevelIndex == levels.size()) {
                            outbound.writeInt(-1);
                            outbound.writeBoolean(true);
                            break;
                        }
                    }
                    else {
                        outbound.writeInt(currentLevelIndex + 1);
                        outbound.writeBoolean(false);
                        currentLevelIndex = 0;
                    }
                } catch (EOFException | SocketException e) {
                    System.out.println("Client disconnected: " + clientSocket);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}