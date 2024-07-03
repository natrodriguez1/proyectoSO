package ec.edu.uees.proyectoso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class UI extends JFrame {
    
    private Pedido[] listaPedidos = new Pedido[100];
    Almacen almacen = new Almacen();

    UI(){
        almacen.setPreferredSize(new Dimension(1000, 500));

        setTitle("proyecto :)");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel cPanel = new JPanel();
        cPanel.setLayout(new BorderLayout());
        setContentPane(cPanel);
        setResizable(false);

        //norte
        JPanel northPanel = new JPanel();
        northPanel.setBorder(new EmptyBorder(20,20,20,20));
        JLabel label = new JLabel("número de pedidos:");
        label.setHorizontalAlignment(SwingConstants.LEFT);
        JTextField inputPedidos = new JTextField(10);
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
        JButton ejecutar = new JButton("ejecutar");
        JButton parar = new JButton("parar");
        JButton finalizar = new JButton("finalizar");
        
        ejecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputPedidos.getText();

                if(input.equals("") || Integer.parseInt(input)<= 0){
                    JOptionPane.showMessageDialog(null, "solicitar por lo menos 1 pedido");
                    inputPedidos.setText("");
                }else{
                    for(int i = 0; i < Integer.parseInt(input); i++){
                        Pedido p = new Pedido();
                        listaPedidos[i] = p;
                    }
                }
            }
        });
        
        northPanel.add(label, BorderLayout.WEST);
        northPanel.add(inputPedidos, BorderLayout.CENTER);
        northPanel.add(ejecutar);
        northPanel.add(parar);
        northPanel.add(finalizar);
        add(northPanel, BorderLayout.NORTH);

        //centro
        JPanel centerPanel = new JPanel();
        almacen.setBorder(new BorderUIResource.LineBorderUIResource(Color.BLACK));
        centerPanel.add(almacen);
        centerPanel.setBorder(new EmptyBorder(0,50,0,50));
        add(centerPanel, BorderLayout.CENTER);

        //sur
        JPanel southPanel = new JPanel();
        southPanel.setBorder(new EmptyBorder(20,20,20,20));

        JLabel pedidoLbl = new JLabel("pedido:");
        JLabel pedido = new JLabel("__________");

        JLabel distanciaLbl = new JLabel("distancia total: ");
        JLabel distancia = new JLabel("__________");

        southPanel.add(pedidoLbl);
        southPanel.add(pedido);
        southPanel.add(distanciaLbl);
        southPanel.add(distancia);
        add(southPanel, BorderLayout.SOUTH);


        setLocationRelativeTo(null);
        
        //System.out.println(almacen.getListaCasilleros()[0].getNroPasillo());
    }

    public Pedido[] getListaPedidos() {
        return listaPedidos;
    }
    
    
}
