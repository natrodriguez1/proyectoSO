/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.uees.proyectoso;

/**
 *
 * @author Asus
 */
public class Pasillo {
    private int x;
    private int y1 = 9;
    private int y2 = 1;
    private int nroPasillo;
    private int min;
    private int max;
    private Casillero[] casilleros = new Casillero[12];
    
    public Pasillo(int posicionEnX, int nroPasillo, Casillero[] casilleros){
        this.x = posicionEnX;
        this.nroPasillo = nroPasillo;
        this.casilleros = casilleros;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getNroPasillo() {
        return nroPasillo;
    }

    public void setNroPasillo(int nroPasillo) {
        this.nroPasillo = nroPasillo;
    }
    
    

    public Casillero[] getCasilleros() {
        return casilleros;
    }

    public void setCasilleros(Casillero[] casilleros) {
        this.casilleros = casilleros;
    }

    public int getMin() {
        return min;
    }
    
    public void setMin(int nroItemInicio) {
        this.min = nroItemInicio;
    }
    
    public int getMax() {
        return max;
    }
    
    public void setMax(int max) {
        this.max = max;
    }
    
            
    
}
