import LabirintsLevels.*;
import configs.GlobalVariables;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LabirintoClientSocket {
    public static void main(String[] args) throws IOException {
        System.out.println("O jogo irá começar em 5 segundos\n" +
                "Regras do Jogo: você deve chegar ao final do labirinto. Para isso, você deve digitar\n" +
                "W para cima,\n" +
                "S para baixo,\n" +
                "A para esquerda,\n" +
                "D para direita.\n" +
                "O labirinto é composto por 5 níveis; cada nível tem um caminho correto. Se você errar o caminho,\n" +
                "você será direcionado para o início do labirinto. Se você chegar ao final do labirinto, você ganha o jogo.\n" +
                "O boneco irá se mover somente após o envio do caminho a ser percorrido.\n" +
                "Boa sorte!\n");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int requestedLevel = 1;

        while (true) {
            LevelOne levelOne = new LevelOne();
            LevelTwo levelTwo = new LevelTwo();
            LevelThree levelThree = new LevelThree();
            LevelFour levelFour = new LevelFour();

            List<Level> levels = new ArrayList<>();
            levels.add(levelOne);
            levels.add(levelTwo);
            levels.add(levelThree);
            levels.add(levelFour);

            Socket clientSocket = new Socket(GlobalVariables.HOST, GlobalVariables.CLIENT_PORT);

            DataInputStream inbound = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outbound = new DataOutputStream(clientSocket.getOutputStream());

            Level currentLevel = levels.get(requestedLevel <= levels.size() ? requestedLevel -1 : 0) ;

            Scanner scanner = new Scanner(System.in);
            String userInput = null;
            outbound.writeInt(requestedLevel);
            System.out.println(currentLevel.getLabyrinthFrames()[0]);
            System.out.println("Digite seus movimentos (W/A/S/D): ");

            userInput = scanner.nextLine();

            outbound.writeUTF(userInput);

            requestedLevel = inbound.readInt();
            boolean isLevelCompleted = inbound.readBoolean();

            if (requestedLevel > levels.size()) {
                currentLevel.printLevel();
                currentLevel.printSuccessArt();
                currentLevel.printFinalArt();
                currentLevel.printRestartArt();
                System.out.println("Você completou todos os níveis. Deseja sair? (S para sair, qualquer outra tecla para continuar): ");
                userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("S")) {
                    System.out.println("Obrigado por jogar! Até logo!");

                    inbound.close();
                    outbound.close();
                    clientSocket.close();
                    System.exit(0);
                } else {
                    requestedLevel = 1;
                }
            }
            else if (isLevelCompleted) {
                currentLevel.printLevel();
                currentLevel.printSuccessArt();
            }
            else {
                currentLevel.printFailedArt();
            }
        }
    }
}
