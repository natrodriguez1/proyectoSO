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
    private int nroItemIncio;
    private int nroItemFinal;
    private Casillero[] casilleros = new Casillero[12];
    
    public Pasillo(int posicionEnX, int nroPasillo, Casillero[] casilleros){
        this.posicionEnX = posicionEnX;
        this.nroPasillo = nroPasillo;
        this.casilleros = casilleros;
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

    public int getNroItemIncio() {
        return nroItemIncio;
    }
    
    public void setNroItemIncio(int nroItemInicio) {
        this.nroItemIncio = nroItemInicio;
    }
    
    public int getNroItemFinal() {
        return nroItemFinal;
    }
    
    public void setNroItemFinal(int nroItemFinal) {
        this.nroItemFinal = nroItemFinal;
    }
    
            
    
}
