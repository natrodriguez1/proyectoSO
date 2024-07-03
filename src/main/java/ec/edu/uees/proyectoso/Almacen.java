package ec.edu.uees.proyectoso;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;

public class Almacen extends JPanel{
    
    private Pasillo[] listaPasillos = new Pasillo[5];//Lista de los pasillos en el almacen
    
    private Casillero[] listaCasilleros = new Casillero[12];//Inicializamos lista de casillero por pasillo
    
    //el tamaño del almacen es de 20x10, de acuerdo a nustras consideraciones, en el x las coordenadas van del 0 al 19, y en el eje y del 0 al 9
    private int[] indiceCasilleros = {0,3,4,7,8,11,12,15,16,19}; //coordenada en x donde se va a graficar cada grupo de casillero
    private int squareSize = 50; // Tamaño de cada cuadrado en píxeles  
    
    public Almacen() {
        int contNroItem = 0;//este contador se usa para asignar el número de item 
        int contNroItemxPasillo = 0;//contador que comienza en 0 y llega a 11, indicando que hay como máximo 12 items
        int contPasillo = 0;//Contador que indica la cantidad de pasillos
        int contIndiceCasillero = 0;//este contador se usa para contar las vueltas del loop for inicial que recorre los indices de casilleros
        
        //se recorre la lista con las coordenadas en x donde se ubica cada conjunto de casilleros
        for (int indiceCasillero : indiceCasilleros) {
            //En total hay 10 conjuntos de casilleros, entonces por cada pasillo hay dos conjuntos de 6 casillero
            //por lo que cada que contIndiceCasillero sea par, significa que hay que inicializar una nueva lista de casilleros por pasillo
            if(contIndiceCasillero%2 == 0){
                listaCasilleros = new Casillero[12];//lista de casilleros por pasillo
                contNroItemxPasillo = 0;//se debe reiniciar el contador de nro de item por pasillo, porque se cuentan 12 items por pasillo, entonces cuando el contador del indice de casillero sea par, significa que el contador de nro de item por pasillo ya llegó a 11 
            }
            //la i en el for indica la coordenada en y donde se va a graficar cada casillero. Son 7 casilleros en total 
            for (int i = 7; i >= 2; i--) {
                //Se crea el casillero, y se ingresa la coordenada en x por el tamaño del cuadrado, y la coordenada en y por el tamaño del cuadrado
                //Como el contador del numero de item comienza en 0, para asignarlo al casillero se le suma 1, 
                Casillero casillero = new Casillero(indiceCasillero * squareSize,i * squareSize,contNroItem+1);
        
                //De acuerdo al análisis de las coordenadas se obtuvo que en total hay 5 pasillos verticales, los cuales están ubicados en x en las coordenadas: 2,6,10,14,18
                //Y en ese mismo análisis obtuvimos que cuando el indice del casillero es par, la coordenada en x del pasillo correspondiente al casillero es el indice + 2
                //y que cuando el indice del casillero es impar, la coordenada en x del pasillo correspondiente es el indice - 1
                //También sacamos que cuando es par está a la derecha del pasillo (viendo de abajo hacia arriba),  y cuando es impar está a la izquierda del pasillo        
                if(indiceCasillero%2 == 0){
                    casillero.setNroPosEnXPasillo(indiceCasillero+2);
                    casillero.setIsRight(true);
                }else{
                    casillero.setNroPosEnXPasillo(indiceCasillero-1);
                    casillero.setIsRight(false);
                }
                
                
                listaCasilleros[contNroItemxPasillo] = casillero;//añadimos los casilleros a la lista de casilleros
                contNroItem += 1;//le sumamos 1 al numero de item para asignar al siguiente casillero
                contNroItemxPasillo += 1;//sumamos 1 al contador de item x pasillo
            }
            
            if(contIndiceCasillero%2 == 0){
                Pasillo pasillo = new Pasillo(listaCasilleros[0].getNroPosEnXPasillo(),contPasillo+1, listaCasilleros);//creamos el pasillo
                listaPasillos[contPasillo] = pasillo;//añadimos el pasillo a la lista de pasillos
                contPasillo += 1;//sumamos 1 al contador de pasillos
            }
            contIndiceCasillero += 1;
            
            
        }
        
        //Imprimimos el pasillo y sus casilleros, para verificar que está todo bien
        for(Pasillo pasillo : listaPasillos){
            System.out.println("Numero pasillo: "+pasillo.getNroPasillo());
            for (int i = 0; i < 12; i++) {
                System.out.println("Numero Item: "+pasillo.getCasilleros()[i].getNroItem());
            }
            
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int ovalSize = 20; 
        int offset = 15;
        
        //tal como hicimos arriba, usamos el indice del conjunto de casilleros y un for decremental del 7 al 2, pero ahora para dibujar los casilleros en el almacén
        for (int indiceCasillero : indiceCasilleros) {
            for (int i = 7; i >= 2; i--) {
                g.drawRect(indiceCasillero * squareSize, i * squareSize, squareSize, squareSize);//se dibuja el casillero
                g.fillOval(indiceCasillero * squareSize + offset, i * squareSize + offset, ovalSize, ovalSize);//se dibuja un círculo en una aproximación al centro del casillero
            }
        }
        
        
    }

    public Pasillo[] getListaPasillos() {
        return listaPasillos;
    }
    
    
}
