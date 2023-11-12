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

    private String StartArt = " _______ _______ _______ _______ _______ _______ _______ _______ _______ \n" +
            "(  ____ (  ____ (  ____ (  ____ (  ____ (  ____ (  ____ (  ____ (  ____ \\\n" +
            " ______       _____ ______   __ \n" +
            " |  ____/\\    / ____|  ____| /_ |\n" +
            " | |__ /  \\  | (___ | |__     | |\n" +
            " |  __/ /\\ \\  \\___ \\|  __|    | |\n" +
            " | | / ____ \\ ____) | |____   | |\n" +
            " |_|/_/    \\_\\_____/|______|  |_|";

    private String FinalArt = "__      ______   _____ ______  __      ________ _   _  _____ ______ _    _ \n" +
            " \\ \\    / / __ \\ / ____|  ____| \\ \\    / /  ____| \\ | |/ ____|  ____| |  | |\n" +
            "  \\ \\  / / |  | | |    | |__     \\ \\  / /| |__  |  \\| | |    | |__  | |  | |\n" +
            "   \\ \\/ /| |  | | |    |  __|     \\ \\/ / |  __| | . ` | |    |  __| | |  | |\n" +
            "    \\  / | |__| | |____| |____     \\  /  | |____| |\\  | |____| |____| |__| |\n" +
            "     \\/   \\____/ \\_____|______|     \\/   |______|_| \\_|\\_____|______|\\____/ \n";
    public void printLevel() {
        for (String row : labyrinthFrames) {
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +row+"\n\n\n\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printFailedArt() {
        System.out.println("\n\n\n\n\n\n"+FailedArt+"\n\n\n\n\n\n");
    }

    public void printSuccessArt() {
        System.out.println("\n\n\n\n\n\n"+SuccessArt+"\n\n\n\n\n\n");
    }

    public void printFinalArt() {
        System.out.println("\n\n\n\n\n\n"+FinalArt+"\n\n\n\n\n\n");
    }

    public boolean sendPath(List<String> clientPath) {
        if(clientPath.size() != SuccessPath.length - 1) {
            System.out.print(clientPath.size() + " " + SuccessPath.length);
            System.out.println("\nWrong path size");
            return false;
        }
        else {
            for (int i = 1; i < clientPath.size(); i++) {
                if (!clientPath.get(i - 1).equals(SuccessPath[i])) {
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
