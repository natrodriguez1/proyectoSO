/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.uees.proyectoso;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Asus
 */
public class ProyectoSO {

    public static void main(String[] args) {
        JFrame ventana = new JFrame("proyecto :)");

        Almacen almacen = new Almacen();
        almacen.setPreferredSize(new Dimension(1000, 500));

        ventana.add(almacen);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
