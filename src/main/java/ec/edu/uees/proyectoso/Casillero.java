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
    private int tamañoCasillero = 50;
    private int nroItem;
    private int nroPosEnXPasillo;
    private boolean itemSolicitado = false;
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

    
    public int getNroPosEnXPasillo() {
        return nroPosEnXPasillo;
    }

    public void setNroPosEnXPasillo(int nroPasillo) {
        this.nroPosEnXPasillo = nroPasillo;
    }
    
    public boolean isItemSolicitado() {
        return itemSolicitado;
    }

    public void setItemSolicitado(boolean itemSolicitado) {
        this.itemSolicitado = itemSolicitado;
    }

    public int getTamañoCasillero() {
        return tamañoCasillero;
    }

    public Item getItem() {
        return item;
    }
    
    
    
    
}
