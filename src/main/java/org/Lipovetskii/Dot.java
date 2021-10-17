package org.Lipovetskii;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Dot {
    static ArrayList<Dot> dotList = new ArrayList<>();
    int dotNumber;
    int x;
    int y;

    static void generateDots(int dotsNumber) {

        for (int i = 0; i < dotsNumber; i++) {
            dotList.add(new Dot(i));
        }

        generateCords();
    }

    public Dot(int dotNumber) {
        this.dotNumber = dotNumber;
    }

   static void generateCords() {

        Queue<Dot> generateQueue = new LinkedList<>();
        ArrayList<Integer> isUsed = new ArrayList<>();

        for(Integer dot:HackenBashGraph.groundDots) {
            generateQueue.add(dotList.get(dot));
            isUsed.add(dotList.get(dot).dotNumber);
            dotList.get(dot).y = Variables.CANVAS_HEIGHT;
        }

        while (!generateQueue.isEmpty()) {
            Dot tmp = generateQueue.peek();
            for (Rib rib : HackenBashGraph.currGraph.ribsList) {

                if (rib.firstDot == tmp.dotNumber && !isUsed.contains(rib.secondDot)) {
                    isUsed.add(rib.secondDot);
                    generateQueue.add(dotList.get(rib.secondDot));
                    dotList.get(rib.secondDot).y = tmp.y - 100;
                } else if (rib.secondDot == tmp.dotNumber && !isUsed.contains(rib.firstDot)) {
                    isUsed.add(rib.firstDot);
                    generateQueue.add(dotList.get(rib.firstDot));
                    dotList.get(rib.firstDot).y = tmp.y - 100;

                }

            }
            generateQueue.poll();
        }

        for (Dot dot : dotList) {
            dot.x = (int) (Math.random() * Variables.CANVAS_WIDTH);
        }

    }
}
