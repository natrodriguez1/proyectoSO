package ec.edu.uees.proyectoso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UI extends JFrame {

    UI(JPanel almacen){
        almacen.setPreferredSize(new Dimension(1000, 500));

        setTitle("proyecto :)");
        setSize(1000, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel cPanel = new JPanel();
        cPanel.setLayout(new BorderLayout());
        setContentPane(cPanel);

        JPanel northPanel = new JPanel();
        northPanel.setBorder(new EmptyBorder(20,20,20,20));
        //northPanel.setLayout(new BorderLayout());
//        JLabel titulo = new JLabel("sistema de almacén");
//        titulo.setHorizontalAlignment(SwingConstants.CENTER);
//        northPanel.add(titulo, BorderLayout.NORTH);
        JLabel label = new JLabel("número de pedidos:");
        label.setHorizontalAlignment(SwingConstants.LEFT);
        JTextField inputPedidos = new JTextField(2);
        JButton aceptar = new JButton("generar");
        northPanel.add(label, BorderLayout.WEST);
        northPanel.add(inputPedidos, BorderLayout.CENTER);
        northPanel.add(aceptar);
        add(northPanel, BorderLayout.NORTH);

        add(almacen, BorderLayout.CENTER);

        setVisible(true);
        setLocationRelativeTo(null);
    }
}
