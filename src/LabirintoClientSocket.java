import LabirintsLevels.Level;
import LabirintsLevels.LevelOne;
import LabirintsLevels.LevelTwo;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LabirintoClientSocket {
    public static void main(String[] args) throws IOException {
        LevelOne levelOne = new LevelOne();
        LevelTwo levelTwo = new LevelTwo();

        List<Level> levels = new ArrayList<>();
        levels.add(levelOne);
        levels.add(levelTwo);

        System.out.println("O jogo irá começar em 10 segundos" +
                "Regras do Jogo: você deve chegar ao final do labirinto. Para isso, você deve digitar\n" +
                "W para cima,\n" +
                "S para baixo,\n" +
                "A para esquerda,\n" +
                "D para direita.\n" +
                "O labirinto é composto por 5 níveis; cada nível tem um caminho correto. Se você errar o caminho,\n" +
                "você será direcionado para o início do labirinto. Se você chegar ao final do labirinto, você ganha o jogo.\n" +
                "O boneco irá se mover somente após o envio do caminho a ser percorrido.\n" +
                "Boa sorte!\n");

        Socket clientSocket = new Socket(GlobalsVariables.HOST, GlobalsVariables.PORT);

        DataInputStream inbound = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream outbound = new DataOutputStream(clientSocket.getOutputStream());

        Scanner scanner = new Scanner(System.in);
        int selectedLevel = 1;
        while (true) {

            outbound.writeInt(selectedLevel);
            String responseMessage = inbound.readUTF();
            Level level = levels.get(selectedLevel - 1);
            System.out.println(responseMessage);
            System.out.println(level.getLabyrinthFrames()[0]);
            System.out.println("Enter your moves (W/A/S/D, separated by commas): ");
            String userInput = scanner.nextLine().trim();

            List<String> userMoves = new ArrayList<>();
            for (String move : userInput.split(",")) {
                userMoves.add(move.trim().toUpperCase());
            }

            for (String move : userMoves) {
                outbound.writeUTF(move);
            }

            selectedLevel = inbound.readInt();

            if (selectedLevel < 0) {
                level.printFinalArt();
            }
            boolean nextLevel = inbound.readBoolean();
            if (nextLevel) {
                level.printSuccessArt();
            }
            else {
                level.printFailedArt();
            }
            System.out.println("Server Move: " + selectedLevel + " " + nextLevel);

        }
    }
}