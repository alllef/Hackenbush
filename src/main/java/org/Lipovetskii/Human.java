package org.Lipovetskii;

import java.util.ArrayList;

public class Human {


    public static boolean makeMove(double x, double y) {

        for (Rib rib : HackenBashGraph.currGraph.ribsList) {
            if (rib.color == Rib.Color.blue && rib.function(x, y)) {

                ArrayList<Rib> removedRibs = HackenBashGraph.currGraph.remove(rib);


                for (Rib tmp : removedRibs) {
                    HackenBashGraph.canvas.drawRib(tmp, "white");
                    HackenBashGraph.currGraph.remove(tmp);
                }
                HackenBashGraph.currGraph.recalculateColors();
                return true;
            }

        }
        return false;
    }
}
