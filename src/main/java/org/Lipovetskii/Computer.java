package org.Lipovetskii;

import java.util.ArrayList;

public class Computer {
    static void makeMove(){

      StateTree tree = new StateTree();

     if(Game.difficulty.getValue().equals("Easy")) tree.makeStatesTree(1);
     else if(Game.difficulty.getValue().equals("Medium")) tree.makeStatesTree(3);
     else tree.makeStatesTree(5);

      tree.minMaxAlphaBetaAlgorithm();
        ArrayList<Rib> removedRibs = HackenBashGraph.currGraph.remove(tree.ribToRemove());


        for (Rib tmp : removedRibs) {
            HackenBashGraph.canvas.drawRib(tmp, "white");
            HackenBashGraph.currGraph.remove(tmp);
        }

        HackenBashGraph.currGraph.recalculateColors();

    }
}
