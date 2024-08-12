package ec.edu.uees.proyectoso;

import javax.swing.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HiloPedido extends Thread {

    private Pedido pedido;
    private String input;
    private Pedido[] listaPedidos;
    private JLabel lblPedido;
    private Almacen almacen;
    private Mutex mutex;
    public HiloPedido(String input, Pedido[] listaPedidos, JLabel lblPedido, Almacen almacen, Mutex mutex){
        this.input = input;
        this.listaPedidos = listaPedidos;
        this.lblPedido = lblPedido;
        this.almacen = almacen;
        this.mutex = mutex;
    }
    @Override
    public void run(){
        mutex.step();
        int numPedidos = Integer.parseInt(input);
        listaPedidos = new Pedido[numPedidos];
        for(int i = 0; i < numPedidos; i++) {
            pedido = new Pedido(i + 1);
            pedido.generarPedido();
            listaPedidos[i] = pedido;

            lblPedido.setText("<html><u>Pedido " + pedido.getNumPedido() + "</u></html>");

            Thread hiloCasilleros = new Thread(new Runnable() {
                public void run() {
                    pintarCasilleros(pedido, almacen);
                }
            });
            Thread hiloPasillos = new Thread(new Runnable() {
                public void run() {
                    pintarPasillos(pedido, almacen);
                }
            });
            try {
                hiloCasilleros.start();
                hiloPasillos.start();

                hiloCasilleros.join();
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

    public void pintarPasillos(Pedido pedido, Almacen almacen){

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
                Pasillo pasillo = new Pasillo(almacen.getPasillosRecorrer().get(almacen.getPasillosRecorrer().size()-1).getX(),6, almacen.getPasillosRecorrer().get(almacen.getPasillosRecorrer().size()-1).getCasilleros());
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
