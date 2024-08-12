package ec.edu.uees.proyectoso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UI extends JFrame {
    
    private Pedido[] listaPedidos;
    private Almacen almacen = new Almacen();
    private Pedido pedido;
    private boolean pausado;
    private JButton ejecutar;
    private Mutex m;
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
        JLabel label = new JLabel("Número de Pedidos:");
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
        
        //sur
        JPanel southPanel = new JPanel();
        southPanel.setBorder(new EmptyBorder(20,20,20,20));

        JLabel pedidoLbl = new JLabel("Pedido:");
        JLabel lblPedido = new JLabel("_________");

        JLabel distanciaLbl = new JLabel("Distancia Total: ");
        JLabel distancia = new JLabel("_________");

        southPanel.add(pedidoLbl);
        southPanel.add(lblPedido);
        southPanel.add(distanciaLbl);
        southPanel.add(distancia);
        add(southPanel, BorderLayout.SOUTH);
        
        JButton ejecutar = new JButton("Ejecutar");
        this.ejecutar = ejecutar;
        JButton parar = new JButton("Parar");
        JButton finalizar = new JButton("Finalizar");
        
        ejecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputPedidos.getText();

                if(pausado){
                    pausado = false;
                    try {
                        reanudarEjecucion();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else if(input.equals("") || Integer.parseInt(input) <= 0){
                    JOptionPane.showMessageDialog(null, "solicitar por lo menos 1 pedido");
                    inputPedidos.setText("");
                }else {
                    m = new Mutex();
                    HiloPedido hiloPedido = new HiloPedido(input, listaPedidos, lblPedido, almacen, m);
                    hiloPedido.start();
                }
            }
        });
        
        parar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                pausado = true;
                try {
                    pausarEjecucion();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            
        });

        finalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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

        setLocationRelativeTo(null);
        
    }
    private void pausarEjecucion() throws InterruptedException {
        m.lock();
        ejecutar.setEnabled(true);
    }
    private void reanudarEjecucion() throws InterruptedException {
        m.unlock();
    }

    private UI getUI(){
        return this;
    }
    
}
