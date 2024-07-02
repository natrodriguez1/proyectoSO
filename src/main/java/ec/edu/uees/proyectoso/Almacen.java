package ec.edu.uees.proyectoso;

import javax.swing.*;
import java.awt.*;

public class Almacen extends JPanel{
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int squareSize = 50; // Tamaño de cada cuadrado en píxeles

        int[] casilleros = {0,3,4,7,8,11,12,15,16,19};



        /*for (int i = 1; i <= 20; i++) {
            g.drawLine(i * squareSize, 0, i * squareSize, 200);
        }

        for (int i = 1; i <= 10; i++) {
            g.drawLine(0, i * squareSize, 400, i * squareSize);
        }*/

        for(int casillero : casilleros){
            g.drawRect(casillero*squareSize, 2*squareSize, squareSize, squareSize);
            g.drawRect(casillero*squareSize, 3*squareSize, squareSize, squareSize);
            g.drawRect(casillero*squareSize, 4*squareSize, squareSize, squareSize);
            g.drawRect(casillero*squareSize, 5*squareSize, squareSize, squareSize);
            g.drawRect(casillero*squareSize, 6*squareSize, squareSize, squareSize);
            g.drawRect(casillero*squareSize, 7*squareSize, squareSize, squareSize);
        }


        //depot
        g.drawRect(60, 450, 80, squareSize);
    }
}
