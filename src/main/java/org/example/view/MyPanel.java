package org.example.view;

import org.example.controller.Controller;

import org.example.controller.action.ActionDraw;
import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;


public class MyPanel extends JPanel implements Observer {
    private Controller controller;

    public MyPanel(Controller controller) {
        this.controller = controller;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                controller.mousePressed(arg0.getPoint());
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent arg0) {
                controller.mouseDragged(arg0.getPoint());
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(MyShape shape: Model.myShapes){
            shape.draw(g2);
        }
//        controller.draw(g2);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

}
