/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.uees.proyectoso;

/**
 *
 * @author Asus
 */
public class Casillero {
    private int x;
    private int y;
    private int tamaño = 50;
    private int nroItem;
    private int posXPasillo;
    private Item item;
    
    public Casillero(int x, int y, int nroItem){
        this.x = x;
        this.y = y;
        this.nroItem = nroItem;
        item = new Item(nroItem);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNroItem() {
        return nroItem;
    }

    public void setNroItem(int nroItem) {
        this.nroItem = nroItem;
    }

    
    public int getPosXPasillo() {
        return posXPasillo;
    }

    public void setPosXPasillo(int nroPasillo) {
        this.posXPasillo = nroPasillo;
    }

    public int getTamaño() {
        return tamaño;
    }

    public Item getItem() {
        return item;
    }
    
    
    
    
}
