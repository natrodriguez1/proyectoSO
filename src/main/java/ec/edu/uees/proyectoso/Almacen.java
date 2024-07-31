package ec.edu.uees.proyectoso;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Almacen extends JPanel{
    
    private Pasillo[] listaPasillos = new Pasillo[5];//Lista de los pasillos en el almacen
    
    private Casillero[] listaCasilleros;//Inicializamos lista de casillero por pasillo
    
    //el tamaño del almacen es de 20x10, de acuerdo a nustras consideraciones, en el x las coordenadas van del 0 al 19, y en el eje y del 0 al 9
    private int[] indiceCasilleros = {0,3,4,7,8,11,12,15,16,19}; //coordenada en x donde se va a graficar cada grupo de casillero
    
    private ArrayList<Casillero> casillerosConCirculo = new ArrayList<>();
    
    private ArrayList<Pasillo> pasillosRecorrer = new ArrayList<>();
    
    
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
                Casillero casillero = new Casillero(indiceCasillero * 50,i * 50,contNroItem+1);
        
                //De acuerdo al análisis de las coordenadas se obtuvo que en total hay 5 pasillos verticales, los cuales están ubicados en x en las coordenadas: 2,6,10,14,18
                //Y en ese mismo análisis obtuvimos que cuando el indice del casillero es par, la coordenada en x del pasillo correspondiente al casillero es el indice + 2
                //y que cuando el indice del casillero es impar, la coordenada en x del pasillo correspondiente es el indice - 1  
                if(indiceCasillero%2 == 0){
                    casillero.setNroPosEnXPasillo(indiceCasillero+2);
                }else{
                    casillero.setNroPosEnXPasillo(indiceCasillero-1);
                }
                
                
                listaCasilleros[contNroItemxPasillo] = casillero;//añadimos los casilleros a la lista de casilleros
                contNroItem += 1;//le sumamos 1 al numero de item para asignar al siguiente casillero
                contNroItemxPasillo += 1;//sumamos 1 al contador de item x pasillo
                
            }
            
            //Cuando el contador de los indices de casillero que se han recorrido es impar, significa que la lista de casillero por pasillos ya está lista
            //por lo tanto ya se la puede asignar a un pasillo, entonces se procede a crear un pasillo y se le asigna los casilleros correspondientes
            if(contIndiceCasillero%2 != 0 ){
                Pasillo pasillo = new Pasillo(listaCasilleros[0].getNroPosEnXPasillo(),contPasillo+1, listaCasilleros);//creamos el pasillo
                listaPasillos[contPasillo] = pasillo;//añadimos el pasillo a la lista de pasillos
                pasillo.setMin(pasillo.getCasilleros()[0].getNroItem()); 
                pasillo.setMax(pasillo.getCasilleros()[11].getNroItem()); 
                contPasillo += 1;//sumamos 1 al contador de pasillos
                
            }
            
            contIndiceCasillero += 1;
            
            
        }
        
        
        
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        //int ovalSize = 20; 
        //int offset = 15;
        
        //tal como hicimos arriba, usamos el indice del conjunto de casilleros y un for decremental del 7 al 2, pero ahora para dibujar los casilleros en el almacén
        /*for (int indiceCasillero : indiceCasilleros) {
            for (int i = 7; i >= 2; i--) {
                g.drawRect(indiceCasillero * squareSize, i * squareSize, squareSize, squareSize);//se dibuja el casillero
                g.fillOval(indiceCasillero * squareSize + offset, i * squareSize + offset, ovalSize, ovalSize);//se dibuja un círculo en una aproximación al centro del casillero
            }
        }*/
        
        //Se recorre la lista de pasillo, y luego la lista de casilleros de cada pasillo, para dibujar cada uno de los casilleros 
        for(Pasillo pasillo : listaPasillos){
            //dibujarPasillo(g,pasillo);
            /*
            //Pasillo vertical
            g.drawLine(pasillo.getPosicionEnX()*50, pasillo.getPosicionInicialEnY()*50, pasillo.getPosicionEnX()*50, pasillo.getPosicionFinalEnY()*50);
            //Pasillo horizontal superior
            g.drawLine(pasillo.getPosicionEnX()*50, pasillo.getPosicionFinalEnY()*50, pasillo.getPosicionEnX()+4*50, pasillo.getPosicionFinalEnY()*50);
            //Pasillo horizontal inferior
            g.drawLine(pasillo.getPosicionEnX()*50, pasillo.getPosicionInicialEnY()*50, pasillo.getPosicionEnX()+4*50, pasillo.getPosicionInicialEnY()*50);
            */
            for(Casillero casillero : pasillo.getCasilleros()){
                g.drawRect(casillero.getX(), casillero.getY(), casillero.getTamañoCasillero(), casillero.getTamañoCasillero());//se dibuja el casillero
                
            }
        }
        
        //depot
        g.fillRect(60, 450, 80, 50);
        
        //dibujar circulos en casilleros
        for (Casillero casillero : casillerosConCirculo) {
            dibujarCirculo(g, casillero);
        }
        
        //Dibujar pasillos
        int cont = 0;
        for(Pasillo pasillo : pasillosRecorrer){

            if(pasillo.getNroPasillo() != 1){
                if(cont == 0){
                    g.drawLine(listaPasillos[0].getPosicionEnX()*50, pasillo.getPosicionInicialEnY()*50, pasillo.getPosicionEnX()*50, pasillo.getPosicionInicialEnY()*50);
                }else if(cont%2 == 0){
                    g.drawLine(pasillosRecorrer.get(cont-1).getPosicionEnX()*50, pasillo.getPosicionInicialEnY()*50, pasillo.getPosicionEnX()*50, pasillo.getPosicionInicialEnY()*50);
                }else if(cont%2 != 0){
                    g.drawLine(pasillosRecorrer.get(cont-1).getPosicionEnX()*50, pasillo.getPosicionFinalEnY()*50, pasillo.getPosicionEnX()*50, pasillo.getPosicionFinalEnY()*50);
                }
            }

            dibujarPasillo(g, pasillo);


            cont++;
        }
    }

    public Pasillo[] getListaPasillos() {
        return listaPasillos;
    }
    
    public void agregarCirculo(Casillero casillero) {
        casillerosConCirculo.add(casillero);
        repaint();
    }

    private void dibujarCirculo(Graphics g, Casillero casillero) {
        int ovalSize = 20;
        int offset = 15;
        g.fillOval(casillero.getX() + offset, casillero.getY() + offset, ovalSize, ovalSize);
    }
    
    public void agregarPasillo(Pasillo pasillo, int index){
        pasillosRecorrer.add(index,pasillo);
        repaint();
    }
    
    private void dibujarPasillo(Graphics g, Pasillo pasillo){
        
        g.drawLine(pasillo.getPosicionEnX()*50, pasillo.getPosicionInicialEnY()*50, pasillo.getPosicionEnX()*50, pasillo.getPosicionFinalEnY()*50);
    }
    
    public void borrarCirculos() {
        casillerosConCirculo.clear();
        repaint();
    }
    
    public void borrarPasillos() {
        pasillosRecorrer.clear();
        repaint();
    }
    
    /*public void borrarCirculos(Casillero casillero) {
        casillerosConCirculo.remove(casillero);
        repaint();
    }*/

    public ArrayList<Pasillo> getPasillosRecorrer() {
        return pasillosRecorrer;
    }

    
    
}
