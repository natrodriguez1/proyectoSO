package ec.edu.uees.proyectoso;

import java.util.Arrays;
import java.util.Random;

public class Pedido {
    int numItems;
    int numTotalItems = 60; //se puede ajustar numero de items
    Random rnd = new Random();
    String[] items;
    public Pedido(){
        numItems = rnd.nextInt(0,9);
        items = new String[numItems];
        int nombreItem;
        for(int i = 0; i<numItems; i++){
             nombreItem = rnd.nextInt(1,numTotalItems);
            items[i] = "item "+nombreItem;
        }
        System.out.println(Arrays.toString(items));
    }
}
