package org.Lipovetskii;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HackenBashGraph {

    static BoostedCanvas canvas = new BoostedCanvas(Variables.CANVAS_WIDTH, Variables.CANVAS_HEIGHT);
    static HackenBashGraph currGraph = new HackenBashGraph();
    static ArrayList<Integer> groundDots = new ArrayList<>();

    static {
        groundDots.add(0);
        generateFromFile();

    }

    ArrayList<Rib> ribsList = new ArrayList<>();
    int redColor = 0;
    int blueColor = 0;

    static void generateFromFile() {
        File file = new File("Graph.txt");
        int dotsNumber = 0;

        try {

            FileReader reader = new FileReader(file);
            Scanner scan = new Scanner(reader);
            dotsNumber = Integer.parseInt(scan.nextLine());


            while (scan.hasNextLine()) {
                String tmp = scan.nextLine();
                String[] stringNumbers = tmp.split(" ");
                Rib rib = new Rib(Integer.parseInt(stringNumbers[0]), Integer.parseInt(stringNumbers[1]), Rib.Color.values()[Integer.parseInt(stringNumbers[2])]);
                currGraph.ribsList.add(rib);

            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        currGraph.recalculateColors();
        Dot.generateDots(dotsNumber);

        for (Rib rib : currGraph.ribsList) {
            canvas.drawRib(rib, rib.color.name());
        }

    }

    ArrayList<Rib> remove(Rib ribToRemove) {

        Queue<Integer> queue = new LinkedList<>();
        HashMap<Rib, Boolean> isUsed = new HashMap<>();
        ArrayList<Rib> removedRibs = new ArrayList<>();

        removedRibs.add(ribToRemove);
        ribsList.remove(ribToRemove);



        for (Integer dot : groundDots) {
            queue.add(dot);

            while (!queue.isEmpty()) {


                for (Rib rib : ribsList) {
                    if (isUsed.get(rib) == null) {
                        if (rib.firstDot == queue.peek()) {

                            queue.add(rib.secondDot);
                            isUsed.put(rib, true);
                        }

                        if (rib.secondDot == queue.peek()) {
                            queue.add(rib.firstDot);
                            isUsed.put(rib, true);
                        }

                    }

                }
                queue.remove();
            }

        }


        for (Rib rib : ribsList) {

            if (isUsed.get(rib) == null) {
                removedRibs.add(rib);


            }

        }

        return removedRibs;
    }

    void removeFallenRibs() {
        Queue<Integer> queue = new LinkedList<>();
        HashMap<Rib, Boolean> isUsed = new HashMap<>();
        ArrayList<Rib> removedRibs = new ArrayList<>();


        for (Integer dot : groundDots) {
            queue.add(dot);

            while (!queue.isEmpty()) {


                for (Rib rib : ribsList) {
                    if (isUsed.get(rib) == null) {
                        if (rib.firstDot == queue.peek()) {

                            queue.add(rib.secondDot);
                            isUsed.put(rib, true);
                        }

                        if (rib.secondDot == queue.peek()) {
                            queue.add(rib.firstDot);
                            isUsed.put(rib, true);
                        }

                    }

                }
                queue.remove();
            }

            recalculateColors();
        }


        for (Rib rib : ribsList) {

            if (isUsed.get(rib) == null) {
                removedRibs.add(rib);
            }

        }

        for (Rib rib : removedRibs) {
            ribsList.remove(rib);
        }
        recalculateColors();
    }

    void recalculateColors() {
        redColor = 0;
        blueColor = 0;

        for (Rib rib : ribsList) {
            if (rib.color == Rib.Color.red) redColor++;
            else blueColor++;
        }

    }

    boolean isGameOver() {
        return redColor == 0 || blueColor == 0;
    }

    public static void main(String[] args) {
        HackenBashGraph testGraph = HackenBashGraph.currGraph;
        testGraph.ribsList.remove(0);

    }
}
