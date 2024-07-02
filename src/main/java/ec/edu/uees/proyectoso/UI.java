package ec.edu.uees.proyectoso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        inputPedidos.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });
        JButton aceptar = new JButton("generar");
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputPedidos.getText();
                int numPedidos = Integer.parseInt(input);

                if(numPedidos>10){
                    JOptionPane.showMessageDialog(null, "solicitar max. 10 pedidos");
                    inputPedidos.setText("");
                }
            }
        });
        northPanel.add(label, BorderLayout.WEST);
        northPanel.add(inputPedidos, BorderLayout.CENTER);
        northPanel.add(aceptar);
        add(northPanel, BorderLayout.NORTH);

        add(almacen, BorderLayout.CENTER);

        setVisible(true);
        setLocationRelativeTo(null);
    }
}
