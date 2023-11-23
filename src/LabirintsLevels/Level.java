package LabirintsLevels;

import java.util.List;

public class Level {
    protected String[] labyrinthFrames;
    protected String[] SuccessPath;
    private String FailedArt = " __      ______   _____ ______   _____  ______ _____  _____  ______ _    _ \n" +
            " \\ \\    / / __ \\ / ____|  ____| |  __ \\|  ____|  __ \\|  __ \\|  ____| |  | |\n" +
            "  \\ \\  / / |  | | |    | |__    | |__) | |__  | |__) | |  | | |__  | |  | |\n" +
            "   \\ \\/ /| |  | | |    |  __|   |  ___/|  __| |  _  /| |  | |  __| | |  | |\n" +
            "    \\  / | |__| | |____| |____  | |    | |____| | \\ \\| |__| | |____| |__| |\n" +
            "     \\/   \\____/ \\_____|______| |_|    |______|_|  \\_\\_____/|______|\\____/ \n";
    private String SuccessArt = " __      ______   _____ ______  __      ________ _   _  _____ ______ _    _ \n" +
            " \\ \\    / / __ \\ / ____|  ____| \\ \\    / /  ____| \\ | |/ ____|  ____| |  | |\n" +
            "  \\ \\  / / |  | | |    | |__     \\ \\  / /| |__  |  \\| | |    | |__  | |  | |\n" +
            "   \\ \\/ /| |  | | |    |  __|     \\ \\/ / |  __| | . ` | |    |  __| | |  | |\n" +
            "    \\  / | |__| | |____| |____     \\  /  | |____| |\\  | |____| |____| |__| |\n" +
            "     \\/   \\____/ \\_____|______|     \\/   |______|_| \\_|\\_____|______|\\____/ \n";


    private String FinalArt = "  ______ _____ __  __   _____  ______        _  ____   _____  ____    _____        _____            ____  ______ _   _  _____ \n" +
            " |  ____|_   _|  \\/  | |  __ \\|  ____|      | |/ __ \\ / ____|/ __ \\  |  __ \\ /\\   |  __ \\     /\\   |  _ \\|  ____| \\ | |/ ____|\n" +
            " | |__    | | | \\  / | | |  | | |__         | | |  | | |  __| |  | | | |__) /  \\  | |__) |   /  \\  | |_) | |__  |  \\| | (___  \n" +
            " |  __|   | | | |\\/| | | |  | |  __|    _   | | |  | | | |_ | |  | | |  ___/ /\\ \\ |  _  /   / /\\ \\ |  _ <|  __| | . ` |\\___ \\ \n" +
            " | |     _| |_| |  | | | |__| | |____  | |__| | |__| | |__| | |__| | | |  / ____ \\| | \\ \\  / ____ \\| |_) | |____| |\\  |____) |\n" +
            " |_|    |_____|_|  |_| |_____/|______|  \\____/ \\____/ \\_____|\\____/  |_| /_/    \\_\\_|  \\_\\/_/    \\_\\____/|______|_| \\_|_____/ \n";

    private String RestartArt = "  _____  ______ _____ _   _ _____ _____ _____          _   _ _____   ____         _  ____   _____  ____  \n" +
            " |  __ \\|  ____|_   _| \\ | |_   _/ ____|_   _|   /\\   | \\ | |  __ \\ / __ \\       | |/ __ \\ / ____|/ __ \\ \n" +
            " | |__) | |__    | | |  \\| | | || |      | |    /  \\  |  \\| | |  | | |  | |      | | |  | | |  __| |  | |\n" +
            " |  _  /|  __|   | | | . ` | | || |      | |   / /\\ \\ | . ` | |  | | |  | |  _   | | |  | | | |_ | |  | |\n" +
            " | | \\ \\| |____ _| |_| |\\  |_| || |____ _| |_ / ____ \\| |\\  | |__| | |__| | | |__| | |__| | |__| | |__| |\n" +
            " |_|  \\_\\______|_____|_| \\_|_____\\_____|_____/_/    \\_\\_| \\_|_____/ \\____/   \\____/ \\____/ \\_____|\\____/ \n";

    public void awaitPrintTime(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void printLevel() {
        for (String row : labyrinthFrames) {
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +row+"\n\n\n\n");
            awaitPrintTime();
        }
    }

    public void printFailedArt() {
        System.out.println("\n\n\n\n\n\n"+FailedArt+"\n\n\n\n\n\n");
        awaitPrintTime();
    }

    public void printSuccessArt() {
        System.out.println("\n\n\n\n\n\n"+SuccessArt+"\n\n\n\n\n\n");
        awaitPrintTime();
    }

    public void printFinalArt() {
        System.out.println("\n\n\n\n\n\n"+FinalArt+"\n\n\n\n\n\n");
        awaitPrintTime();
    }

    public void printRestartArt() {
        System.out.println("\n\n\n\n\n\n"+RestartArt+"\n\n\n\n\n\n");
        awaitPrintTime();
    }

    public boolean sendPath(List<String> clientPath) {
        if(clientPath.size() != SuccessPath.length) {
            System.out.print(clientPath.size() + " " + SuccessPath.length);
            System.out.println("\nWrong size");
            return false;
        }
        else {
            for (int i = 0; i < clientPath.size(); i++) {
                if (!clientPath.get(i).toUpperCase().equals(SuccessPath[i])) {
                    System.out.println("Wrong path");
                    return false;
                }
            }
            return true;
        }
    }

    public String[] getLabyrinthFrames() {
        return labyrinthFrames;
    }

    public void setLabyrinthFrames(String[] labyrinthFrames) {
        this.labyrinthFrames = labyrinthFrames;
    }

    public String getFailedArt() {
        return FailedArt;
    }

    public void setFailedArt(String failedArt) {
        FailedArt = failedArt;
    }

    public String getSuccessArt() {
        return SuccessArt;
    }

    public void setSuccessArt(String successArt) {
        SuccessArt = successArt;
    }

    public String[] getSuccessPath() {
        return SuccessPath;
    }

    public void setSuccessPath(String[] successPath) {
        SuccessPath = successPath;
    }
}
