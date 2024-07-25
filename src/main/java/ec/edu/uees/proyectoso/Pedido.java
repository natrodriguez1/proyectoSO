package ec.edu.uees.proyectoso;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pedido extends Thread {
    
    private int numPedido;
    private int numItems ;
    private int numTotalItems = 60; //se puede ajustar numero de items
    private Random rnd = new Random();
    private Item[] items;
    
    
    public Pedido(int numPedido){
        this.numPedido = numPedido;
        
    }
    
    public void run(){
        System.out.println("Pedido: " + numPedido);
        generarPedido();
        
    }
    
    public void generarPedido(){
        numItems = rnd.nextInt(5,10);
        items = new Item[numItems];
        int nombreItem;
        for(int i = 0; i<numItems; i++){
            nombreItem = rnd.nextInt(1,numTotalItems);
            Item item = new Item(nombreItem);
            items[i] = item;
            System.out.println(nombreItem);
        }
        
    }

    public Item[] getItems() {
        return items;
    }


    public int getNumPedido() {
        return numPedido;
    }
    
    
    
    
}
