package ec.edu.uees.proyectoso;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UI extends JFrame {
    
    private Pedido[] listaPedidos;
    private Almacen almacen = new Almacen();
    private Pedido pedido;


    UI(){
        String nombreArchivo = "Resultados.txt";
        File archivo = new File(nombreArchivo);
        
         // Al iniciar el programa, limpiamos el archivo
        try (FileWriter writer = new FileWriter(archivo, false)) {
            writer.write(""); // Esto limpia el contenido del archivo
        } catch (IOException e) {
            e.printStackTrace();
        }
        
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
        
        JLabel tiempoLbl = new JLabel("Tiempo: ");
        JLabel tiempo = new JLabel("_________");

        southPanel.add(pedidoLbl);
        southPanel.add(lblPedido);
        southPanel.add(distanciaLbl);
        southPanel.add(distancia);
        southPanel.add(tiempoLbl);
        southPanel.add(tiempo);
        add(southPanel, BorderLayout.SOUTH);
        
        JButton ejecutar = new JButton("Ejecutar");
        JButton parar = new JButton("Parar");
        JButton finalizar = new JButton("Finalizar");
        
        ejecutar.addActionListener(new ActionListener() {
            @Override
            public synchronized void actionPerformed(ActionEvent e) {
                
                String input = inputPedidos.getText();
                
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
                                
                                
                                lblPedido.setText("<html><u>Pedido " + pedido.getNumPedido() + "</u></html>");
                                
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
                                    
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                Thread hiloDistancia = new Thread(new Runnable(){
                                    public void run(){
                                        almacen.calcularDistancia();
                                        distancia.setText("<html><u>"+almacen.getDistanciaTotal()+" metros</u></html>");
                                        tiempo.setText("<html><u>"+almacen.getDistanciaTotal()+" segundos</u></html>");
                                    }
                                });
                                
                                Thread hiloArchivo = new Thread(new Runnable(){
                                    @Override
                                    public void run() {
                                        try (FileWriter writer = new FileWriter(archivo, true)) {
                                            writer.write("pedido p"+ pedido.getNumPedido()+ "," + almacen.getDistanciaTotal()+ ","+ almacen.getDistanciaTotal() + "\n");
                                            for (Item item : pedido.getItems()) {
                                                writer.write("item "+ item.getNum() + "\n");
                                            }
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    
                                });
                                
                                hiloDistancia.start();
                                hiloArchivo.start();
                                
                                try {
                                    hiloDistancia.join();
                                    hiloArchivo.join();
                                    Thread.sleep(8000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                
                                almacen.borrarCirculos();
                                almacen.borrarPasillos();
                                lblPedido.setText("__________");
                                distancia.setText("__________");
                                tiempo.setText("__________");
                                
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
            for (int j = 0; j < almacen.getPasillos().length; j++) {
                if (almacen.getPasillos()[j].getMax() >= pedido.getItems()[i].getNum()&& pedido.getItems()[i].getNum() >= almacen.getPasillos()[j].getMin()) { 
                    
                    for (int k = 0; k < almacen.getPasillos()[j].getCasilleros().length; k++) { 
                        if (pedido.getItems()[i].getNum() == almacen.getPasillos()[j].getCasilleros()[k].getNroItem()) { 
                            almacen.agregarCirculo(almacen.getPasillos()[j].getCasilleros()[k]);
                              
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
                    for (int j = 0; j < almacen.getPasillos().length; j++) {
                        if (almacen.getPasillos()[j].getMax() >= pedido.getItems()[i].getNum()&& pedido.getItems()[i].getNum() >= almacen.getPasillos()[j].getMin()) { 
                            if(c == 0){
                                
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                almacen.agregarPasillo(almacen.getPasillos()[j], 0);
                                c++;
                            } else if(almacen.getPasillosRecorrer().get(c-1).getNroPasillo() != almacen.getPasillos()[j].getNroPasillo()){
                                
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                almacen.agregarPasillo(almacen.getPasillos()[j], c);
                                
                                c++;
                            }
                            
                            //Se busca entrar en ultimo item para poder trazar la linea final hasta las coordenadas de este item
                            if(i == pedido.getItems().length-1){
                                int yMayor = 50000;
                                
                                for (int k = 0; k < almacen.getPasillosRecorrer().get(c-1).getCasilleros().length; k++){
                                    Casillero casillero = almacen.getPasillosRecorrer().get(c - 1).getCasilleros()[k];
                                    for (int l = 0; l < pedido.getItems().length; l++) {
                                        
                                        if (pedido.getItems()[l].getNum() == casillero.getNroItem()) {
                                            if (casillero.getY() < yMayor) {
                                                yMayor = casillero.getY();
                                            }
                                        }
                                        
                                    }
                                    
                                    if(pedido.getItems()[i].getNum() == casillero.getNroItem()){ 
                                        
                                        if (casillero.getY() <= yMayor) {
                                            almacen.getPasillosRecorrer().get(c-1).setY2(casillero.getY()/50);
                                        }else{
                                            almacen.getPasillosRecorrer().get(c-1).setY2(yMayor/50);
                                        }
                                        
                                    }
                                    
                                }
                                
                                //dibujar el regreso hacia el depot
                                Pasillo pasillo = new Pasillo(almacen.getPasillosRecorrer().get(c-1).getX(),6,almacen.getPasillosRecorrer().get(c-1).getCasilleros());
                                pasillo.setY2(almacen.getPasillosRecorrer().get(c-1).getY2());
                                pasillo.setY1(almacen.getPasillosRecorrer().get(c-1).getY1());

                                almacen.agregarPasillo(pasillo, almacen.getPasillosRecorrer().size());
                            }
                            
                        }
                        
                    }
                }
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }

                //dibujo del camino final hacia el depot
                Pasillo pasillo = new Pasillo(almacen.getPasillosRecorrer().get(almacen.getPasillosRecorrer().size()-1).getX(),7, almacen.getPasillosRecorrer().get(almacen.getPasillosRecorrer().size()-1).getCasilleros());
                pasillo.setY1(9);
                pasillo.setY2(9);
                almacen.agregarPasillo(pasillo, almacen.getPasillosRecorrer().size());

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
