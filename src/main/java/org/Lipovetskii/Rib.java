package org.Lipovetskii;

public class Rib {

    public enum Color {red, blue}

    int firstDot;
    int secondDot;
    Color color;

    Rib(int firstDot, int secondDot, Color color) {
        this.firstDot = firstDot;
        this.secondDot = secondDot;
        this.color = color;
    }

    boolean function(double x, double y) {

        if ((x >= Dot.dotList.get(firstDot).x && x <= Dot.dotList.get(secondDot).x) || (x <= Dot.dotList.get(firstDot).x && x >= Dot.dotList.get(secondDot).x)) {
            if ((y >= Dot.dotList.get(firstDot).y && y <= Dot.dotList.get(secondDot).y) || (y <= Dot.dotList.get(firstDot).y && y >= Dot.dotList.get(secondDot).y)) {
                double left = (x - Dot.dotList.get(firstDot).x) / (Dot.dotList.get(secondDot).x - Dot.dotList.get(firstDot).x);
                double right = (y - Dot.dotList.get(firstDot).y) / (Dot.dotList.get(secondDot).y - Dot.dotList.get(firstDot).y);
                return (left-right>-0.2&&left-right<0.2);
            }
        }
        return false;
    }

}

