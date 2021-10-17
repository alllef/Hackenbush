package org.Lipovetskii;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.shared.Registration;
import org.vaadin.pekkam.Canvas;
import org.vaadin.pekkam.CanvasRenderingContext2D;

public class BoostedCanvas extends Canvas implements ClickNotifier<BoostedCanvas> {



    void drawRib(Rib rib, String color){
           Dot firstDot = Dot.dotList.get(rib.firstDot);
           Dot secondDot = Dot.dotList.get(rib.secondDot);
        CanvasRenderingContext2D ctx = getContext();

        ctx.beginPath();
        ctx.setLineWidth(3);
        ctx.setStrokeStyle(color);

        ctx.moveTo(firstDot.x,firstDot.y);
        ctx.lineTo(secondDot.x,secondDot.y);
        ctx.stroke();


    }

    void removeRib(double x,double y){
        for(Rib rib:HackenBashGraph.currGraph.ribsList){
            if(rib.function(x,y)){
                drawRib(rib,"white");
                break;
            }
        }
    }

    public BoostedCanvas(int width, int height) {
        super(width, height);
         getStyle().set("border","1px solid black");
         addClickListener(event->Game.makeMove(event.getClientX(),event.getClientY()));
    }
}
