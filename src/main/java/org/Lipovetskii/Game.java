package org.Lipovetskii;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;


public class Game {


    static boolean whoIsNext = true;
    static Notification turnNotify = new Notification("Click to computer turn", 30000);
    static Notification endNotify = new Notification("", 30000);
    static Button restart = new Button("Restart");
    static RadioButtonGroup<String> difficulty = new RadioButtonGroup<>();

    static {

        difficulty.setLabel("Difficulty");
        difficulty.setItems("Easy", "Medium", "Hard");
        difficulty.setValue("Easy");
        restart.addClickListener(buttonClickEvent -> {
            endNotify.close();
            HackenBashGraph.canvas.getContext().clearRect(0, 0, Variables.CANVAS_WIDTH, Variables.CANVAS_HEIGHT);
            HackenBashGraph.generateFromFile();
        });
    }

    static void makeMove(double x, double y) {
        if (HackenBashGraph.currGraph.ribsList.isEmpty()) return;
        if (whoIsNext) {
            boolean isMade = Human.makeMove(x, y);
            whoIsNext = !isMade;

            if (!whoIsNext) {

                turnNotify.setText("Click to computer turn");
                turnNotify.open();
            }

        } else {
            whoIsNext = true;
            turnNotify.close();
            Computer.makeMove();
        }
        gameOver();
    }


    static boolean gameOver() {
        boolean isOver = false;
        System.out.println("colors are " + HackenBashGraph.currGraph.redColor + " " + HackenBashGraph.currGraph.blueColor);
        if (HackenBashGraph.currGraph.redColor == 0 && HackenBashGraph.currGraph.blueColor == 0) {
            System.out.println("Draw");
            endNotify.setText("Draw");
            isOver = true;
        } else if (HackenBashGraph.currGraph.redColor == 0) {

            endNotify.setText("You win");
            System.out.println("You win");
            isOver = true;
        } else if (HackenBashGraph.currGraph.blueColor == 0) {

            endNotify.setText("You lose");
            System.out.println("You lose");
            isOver = true;
        }


        if (isOver) {
            whoIsNext = true;
            turnNotify.close();
            endNotify.open();
            HackenBashGraph.currGraph.ribsList.clear();
            return true;

        }
        return false;
    }

}

