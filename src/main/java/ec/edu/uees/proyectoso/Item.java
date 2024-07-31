/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.uees.proyectoso;

/**
 *
 * @author Asus
 */
public class Item implements Comparable<Item>{
    private int num;
    
    public Item(int nombreItem){
        this.num = nombreItem;
    }

    public int getNum() {
        return num;
    }

    @Override
    public int compareTo(Item o) {
        return Integer.compare(getNum(), o.getNum());
    }
    
}
