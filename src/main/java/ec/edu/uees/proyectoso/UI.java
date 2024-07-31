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
        
        //sur
        JPanel southPanel = new JPanel();
        southPanel.setBorder(new EmptyBorder(20,20,20,20));

        JLabel pedidoLbl = new JLabel("pedido:");
        JLabel lblPedido = new JLabel("__________");

        JLabel distanciaLbl = new JLabel("distancia total: ");
        JLabel distancia = new JLabel("__________");

        southPanel.add(pedidoLbl);
        southPanel.add(lblPedido);
        southPanel.add(distanciaLbl);
        southPanel.add(distancia);
        add(southPanel, BorderLayout.SOUTH);
        
        JButton ejecutar = new JButton("ejecutar");
        JButton parar = new JButton("parar");
        JButton finalizar = new JButton("finalizar");
        
        ejecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputPedidos.getText();
                //int numPedidos = Integer.parseInt(input);
                //listaPedidos = new Pedido[Integer.parseInt(input)];

                
                if(input.equals("") || Integer.parseInt(input) <= 0){
                    JOptionPane.showMessageDialog(null, "solicitar por lo menos 1 pedido");
                    inputPedidos.setText("");
                }else{
                    Thread hilo = new Thread(new Runnable(){
                        public void run(){
                            int numPedidos = Integer.parseInt(input);
                            listaPedidos = new Pedido[numPedidos];
                            for(int i = 0; i < numPedidos; i++){
                                pedido = new Pedido(i+1);
                                pedido.start();
                                listaPedidos[i] = pedido;
                                
                                try {
                                    pedido.join();
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                lblPedido.setText(" Pedido "+pedido.getNumPedido());
                                
                                Thread hiloCasilleros = new Thread(new Runnable(){
                                    public void run(){
                                        pintarCasilleros(pedido, almacen);
                                    }
                                });
                                
                                
                                Thread hiloPasillos = new Thread(new Runnable(){
                                    public void run(){
                                        pintarPasillos(pedido, almacen);
                                    }
                                });
                                
                                
                                try {
                                    hiloCasilleros.start();
                                    hiloCasilleros.join();
                                    
                                    hiloPasillos.start();
                                    hiloPasillos.join();
                                    Thread.sleep(2000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                
                                almacen.borrarCirculos();
                                almacen.borrarPasillos();
                                lblPedido.setText("__________");
                                
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        }
                    });
                    
                    hilo.start();
                    
                    
                }
                
                
                
                
            }
        });
        
        parar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
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

    
    
    
    private void pintarCasilleros(Pedido pedido, Almacen almacen) {
        
        for (int i = 0; i < pedido.getItems().length; i++) { 
            for (int j = 0; j < almacen.getListaPasillos().length; j++) {
                if (almacen.getListaPasillos()[j].getMax() >= pedido.getItems()[i].getNum()&& pedido.getItems()[i].getNum() >= almacen.getListaPasillos()[j].getMin()) { 
                    
                    for (int k = 0; k < almacen.getListaPasillos()[j].getCasilleros().length; k++) { 
                        if (pedido.getItems()[i].getNum() == almacen.getListaPasillos()[j].getCasilleros()[k].getNroItem()) { 
                            almacen.agregarCirculo(almacen.getListaPasillos()[j].getCasilleros()[k]);
                              
                        }
                    }
                }
            }
        }
        
    }
    
    private void pintarPasillos(Pedido pedido, Almacen almacen){
        
        Thread hilo = new Thread(new Runnable(){
            public void run(){
                int c = 0;
                Arrays.sort(pedido.getItems());
                for (int i = 0; i < pedido.getItems().length; i++) { 
                    for (int j = 0; j < almacen.getListaPasillos().length; j++) {
                        if (almacen.getListaPasillos()[j].getMax() >= pedido.getItems()[i].getNum()&& pedido.getItems()[i].getNum() >= almacen.getListaPasillos()[j].getMin()) { 
                            if(c == 0){
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                almacen.agregarPasillo(almacen.getListaPasillos()[j], 0);
                                c++;
                            } else if(almacen.getPasillosRecorrer().get(c-1).getNroPasillo() != almacen.getListaPasillos()[j].getNroPasillo()){
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                almacen.agregarPasillo(almacen.getListaPasillos()[j], c);
                                c++;
                            }
                            /*
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                            }*/
                        }
                    }
                }

                for(Pasillo pasillo : almacen.getPasillosRecorrer()){
                    System.out.println("Recorrer pasillo: " + pasillo.getNroPasillo());
                }
            }
        });
        
        hilo.start();
        
        try {
            hilo.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
