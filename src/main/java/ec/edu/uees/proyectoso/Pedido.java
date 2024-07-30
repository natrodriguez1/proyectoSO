package ec.edu.uees.proyectoso;

import java.util.*;

public class Pedido {
    
    private int numItems;
    private int numTotalItems = 60; //se puede ajustar numero de items
    private Random rnd = new Random();
    private Item[] items;
    private Almacen almacen;
    private Pasillo[] listaPasillos;
    private Pasillo[] pasillosRecorrer = new Pasillo[12];
    
    public Pedido(Almacen almacen){
        this.almacen = almacen;
        this.listaPasillos = almacen.getListaPasillos();
        numItems = rnd.nextInt(5,10);
        items = new Item[numItems];
        int nombreItem;
        for(int i = 0; i<numItems; i++){
            nombreItem = rnd.nextInt(1,numTotalItems);
            items[i] = new Item(nombreItem);
        }
        System.out.println(Arrays.toString(items));
    }

    public Item[] getItems() {
        return items;
    }

    public void buscarPedido(){
        int c = 0;
        Arrays.sort(items);
        for(Item item: items){
            for(Pasillo pasillo: this.listaPasillos){
                if(item.getNum() >= pasillo.getMin() && item.getNum() <= pasillo.getMax()){
                    if(c == 0){
                        pasillosRecorrer[0] = pasillo;
                        c++;
                    } else if(pasillosRecorrer[c-1].getNroPasillo() != pasillo.getNroPasillo()){
                        pasillosRecorrer[c] = pasillo;
                        c++;
                    }
                }
            }
        }
        for(int i = 0; i<c; i++){
            pasillosRecorrer[i].recorrer();

        }
    }
}
