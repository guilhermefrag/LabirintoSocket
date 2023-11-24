import LabirintsLevels.*;
import configs.GlobalVariables;

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
    private static int nextLevelIndex = 0;

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(GlobalVariables.SERVER_PORT, GlobalVariables.SERVER_BACKLOG);
            LevelOne levelOne = new LevelOne();
            LevelTwo levelTwo = new LevelTwo();
            LevelThree levelThree = new LevelThree();
            LevelFour levelFour = new LevelFour();

            List<Level> levels = new ArrayList<>();
            levels.add(levelOne);
            levels.add(levelTwo);
            levels.add(levelThree);
            levels.add(levelFour);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from: " + clientSocket);
                try {
                    Thread clientHandlerThread = new Thread(() -> {
                        handleClient(clientSocket, levels);
                    });
                    clientHandlerThread.start();
                } catch (Exception e) {
                    System.out.println("Thread interrupted");
                }

            }
        }catch (SocketException e) {
            System.out.println("Socket closed");
        }catch (EOFException e){
            System.out.println("Thread closed");
        }

    }

    private static void handleClient(Socket clientSocket, List<Level> levels) {
        try (DataInputStream inbound = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream outbound = new DataOutputStream(clientSocket.getOutputStream())) {

            int requestedLevel = inbound.readInt();
            if (requestedLevel == levels.size() + 1) {
                requestedLevel = 1;
            }
            System.out.println("Received level request: " + requestedLevel);

            currentLevelIndex = requestedLevel - 1;
            nextLevelIndex = requestedLevel + 1;

            Level currentLevel = levels.get(currentLevelIndex);
            while (true) {
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

                } catch (EOFException e) {
                    break;
                } catch (SocketException e) {
                    System.out.println("Socket Finalizado");
                    break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}