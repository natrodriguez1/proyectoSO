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
        Almacen almacen = new Almacen();

        JFrame ventana = new UI(almacen);

    }
}
