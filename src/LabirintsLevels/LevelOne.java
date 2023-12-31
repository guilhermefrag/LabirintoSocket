package LabirintsLevels;

public class LevelOne extends Level{
    public LevelOne() {
        labyrinthFrames = new String[]{
                "#######  ###########\n"+
                "#######  ###########\n"+
                "#######  ###########\n"+
                "#     #  #      #\n"+
                "#######  #      #\n"+
                "#######  #      #\n"+
                "#######  ###########\n"+
                "#######            #\n"+
                "################## #\n"+
                "################## #\n"+
                "➡                  #\n"+
                "####################\n\n",

                "#######  ###########\n"+
                "#######  ###########\n"+
                "#######  ###########\n"+
                "#     #  #      #\n"+
                "#######  #      #\n"+
                "#######  #      #\n"+
                "#######  ###########\n"+
                "#######            #\n"+
                "################## #\n"+
                "################## #\n"+
                "                 ➡ #\n"+
                "####################\n\n",

                "#######  ###########\n"+
                "#######  ###########\n"+
                "#######  ###########\n"+
                "#     #  #      #\n"+
                "#######  #      #\n"+
                "#######  #      #\n"+
                "#######  ###########\n"+
                "#######           ⬆#\n"+
                "################## #\n"+
                "################## #\n"+
                "                   #\n"+
                "####################\n\n",

                "#######  ###########\n"+
                "#######  ###########\n"+
                "#######  ###########\n"+
                "#     #  #      #\n"+
                "#######  #      #\n"+
                "#######  #      #\n"+
                "#######  ###########\n"+
                "#######⬅           #\n"+
                "################## #\n"+
                "################## #\n"+
                "                   #\n"+
                "####################\n\n",

                "####### ⬆###########\n"+
                "#######  ###########\n"+
                "#######  ###########\n"+
                "#     #  #      #\n"+
                "#######  #      #\n"+
                "#######  #      #\n"+
                "#######  ###########\n"+
                "#######            #\n"+
                "################## #\n"+
                "################## #\n"+
                "                   #\n"+
                "####################\n\n",
        };
        SuccessPath = new String[]{
                "D",
                "W",
                "A",
                "W",
        };
    }

}
