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
    private int posicionEnX;
    private int posicionInicialEnY = 9;
    private int posicionFinalEnY = 1;
    private int nroPasillo;
    private int max;
    private int min;
    private Casillero[] casilleros = new Casillero[6];
    private Almacen almacen;
    
    public Pasillo(int posicionEnX, int nroPasillo,Casillero[] casilleros, int min, int max, Almacen a){
        this.posicionEnX = posicionEnX;
        this.nroPasillo = nroPasillo;
        this.casilleros = casilleros;
        this.min = min;
        this.max = max;
        this.almacen = a;
    }

    public int getPosicionEnX() {
        return posicionEnX;
    }

    public void setPosicionEnX(int posicionEnX) {
        this.posicionEnX = posicionEnX;
    }

    public int getPosicionInicialEnY() {
        return posicionInicialEnY;
    }

    public void setPosicionInicialEnY(int posicionInicialEnY) {
        this.posicionInicialEnY = posicionInicialEnY;
    }

    public int getPosicionFinalEnY() {
        return posicionFinalEnY;
    }

    public void setPosicionFinalEnY(int posicionFinalEnY) {
        this.posicionFinalEnY = posicionFinalEnY;
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

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
    public void recorrer(){
        System.out.println("pasillo #"+this.nroPasillo+" recorrido");
    }
}
