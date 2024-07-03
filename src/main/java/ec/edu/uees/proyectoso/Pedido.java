package ec.edu.uees.proyectoso;

import java.util.Arrays;
import java.util.Random;

public class Pedido {
    
    private int numItems;
    private int numTotalItems = 60; //se puede ajustar numero de items
    private Random rnd = new Random();
    private String[] items;
    
    public Pedido(){
        numItems = rnd.nextInt(5,10);
        items = new String[numItems];
        int nombreItem;
        for(int i = 0; i<numItems; i++){
            nombreItem = rnd.nextInt(1,numTotalItems);
            items[i] = "item "+nombreItem;
        }
        System.out.println(Arrays.toString(items));
    }

    public String[] getItems() {
        return items;
    }
    
    
}
