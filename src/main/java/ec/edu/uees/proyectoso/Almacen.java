package ec.edu.uees.proyectoso;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Almacen extends JPanel{
    
    private Pasillo[] pasillos = new Pasillo[5];//Lista de los pasillos en el almacen
    
    private Casillero[] casilleros;//Inicializamos lista de casillero por pasillo
    
    //el tamaño del almacen es de 20x10, de acuerdo a nustras consideraciones, en el x las coordenadas van del 0 al 19, y en el eje y del 0 al 9
    private int[] indiceCasilleros = {0,3,4,7,8,11,12,15,16,19}; //coordenada en x donde se va a graficar cada grupo de casillero
    
    private ArrayList<Casillero> casillerosConCirculo = new ArrayList<>();
    
    private ArrayList<Pasillo> pasillosRecorrer = new ArrayList<>();
    
    private int distanciaTotal;
    
    
    public Almacen() {
        int nroItem = 0;//este contador se usa para asignar el número de item 
        int nroItemxPasillo = 0;//contador que comienza en 0 y llega a 11, indicando que hay como máximo 12 items
        int contPasillo = 0;//Contador que indica la cantidad de pasillos
        int contIndiceCasillero = 0;//este contador se usa para contar las vueltas del loop for inicial que recorre los indices de casilleros
        
       
        //se recorre la lista con las coordenadas en x donde se ubica cada conjunto de casilleros
        for (int indiceCasillero : indiceCasilleros) {
            //En total hay 10 conjuntos de casilleros, entonces por cada pasillo hay dos conjuntos de 6 casillero
            //por lo que cada que contIndiceCasillero sea par, significa que hay que inicializar una nueva lista de casilleros por pasillo
            if(contIndiceCasillero%2 == 0){
                casilleros = new Casillero[12];//lista de casilleros por pasillo
                nroItemxPasillo = 0;//se debe reiniciar el contador de nro de item por pasillo, porque se cuentan 12 items por pasillo, entonces cuando el contador del indice de casillero sea par, significa que el contador de nro de item por pasillo ya llegó a 11 
            }
            //la i en el for indica la coordenada en y donde se va a graficar cada casillero. Son 7 casilleros en total 
            for (int i = 7; i >= 2; i--) {
                //Se crea el casillero, y se ingresa la coordenada en x por el tamaño del cuadrado, y la coordenada en y por el tamaño del cuadrado
                //Como el contador del numero de item comienza en 0, para asignarlo al casillero se le suma 1, 
                Casillero casillero = new Casillero(indiceCasillero * 50,i * 50,nroItem+1);
        
                //De acuerdo al análisis de las coordenadas se obtuvo que en total hay 5 pasillos verticales, los cuales están ubicados en x en las coordenadas: 2,6,10,14,18
                //Y en ese mismo análisis obtuvimos que cuando el indice del casillero es par, la coordenada en x del pasillo correspondiente al casillero es el indice + 2
                //y que cuando el indice del casillero es impar, la coordenada en x del pasillo correspondiente es el indice - 1  
                if(indiceCasillero%2 == 0){
                    casillero.setPosXPasillo(indiceCasillero+2);
                }else{
                    casillero.setPosXPasillo(indiceCasillero-1);
                }
                
                
                casilleros[nroItemxPasillo] = casillero;//añadimos los casilleros a la lista de casilleros
                nroItem += 1;//le sumamos 1 al numero de item para asignar al siguiente casillero
                nroItemxPasillo += 1;//sumamos 1 al contador de item x pasillo
                
            }
            
            //Cuando el contador de los indices de casillero que se han recorrido es impar, significa que la lista de casillero por pasillos ya está lista
            //por lo tanto ya se la puede asignar a un pasillo, entonces se procede a crear un pasillo y se le asigna los casilleros correspondientes
            if(contIndiceCasillero%2 != 0 ){
                Pasillo pasillo = new Pasillo(casilleros[0].getPosXPasillo(),contPasillo+1, casilleros);//creamos el pasillo
                pasillos[contPasillo] = pasillo;//añadimos el pasillo a la lista de pasillos
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
        
        //Se recorre la lista de pasillo, y luego la lista de casilleros de cada pasillo, para dibujar cada uno de los casilleros 
        for(Pasillo pasillo : pasillos){
            
            for(Casillero casillero : pasillo.getCasilleros()){
                g.drawRect(casillero.getX(), casillero.getY(), casillero.getTamaño(), casillero.getTamaño());//se dibuja el casillero
                
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
                    //Se dibuja la linea horizontal que lleva del primer pasillo al siguiente pasillo
                    g.drawLine(pasillos[0].getX()*50, pasillo.getY1()*50, pasillo.getX()*50, pasillo.getY1()*50);
                    //distanciaTotal += pasillo.getX() - pasillos[0].getX();
                }else if(pasillo.getNroPasillo() == 6 && (cont-1)%2 == 0){
                    g.drawLine(pasillo.getX()*50, pasillo.getY2()*50, (pasillo.getX()*50)+5, pasillo.getY2()*50);
                    g.drawLine((pasillo.getX()*50)+5, pasillo.getY1()*50, (pasillo.getX()*50)+5, pasillo.getY2()*50);
                }else if(pasillo.getNroPasillo() == 7){
                    if((cont-2)%2 == 0){
                        g.drawLine((pasillo.getX()*50)+5, pasillo.getY1()*50, 2*50, pasillo.getY1()*50);
                    }
                    //Se dibuja la linea horizontal que lleva del ultimo pasillo al depot
                    g.drawLine((pasillo.getX()*50), pasillo.getY1()*50, 2*50, pasillo.getY1()*50);
                }else if(cont%2 == 0){
                    //Se dibuja la linea horizontal que lleva del pasillo anterior al pasillo que se esta recorriendo acutalmente pero se dibuja solo cuando el pasillo comienza en la parte inferior
                    g.drawLine(pasillosRecorrer.get(cont-1).getX()*50, pasillo.getY1()*50, pasillo.getX()*50, pasillo.getY1()*50);
                    //distanciaTotal += pasillo.getX() - pasillosRecorrer.get(cont-1).getX();
                }else if(cont%2 != 0){
                    pasillo.setY2(1);
                    //Se dibuja la linea horizontal que lleva del pasillo anterior al pasillo que se esta recorriendo acutalmente pero se dibuja solo cuando el pasillo comienza en la parte superior
                    g.drawLine(pasillosRecorrer.get(cont-1).getX()*50, pasillo.getY2()*50, pasillo.getX()*50, pasillo.getY2()*50);
                    //distanciaTotal += pasillo.getX() - pasillosRecorrer.get(cont-1).getX();
                }
            }
            
            if(pasillo.getNroPasillo() != 7 || pasillo.getNroPasillo() != 6){
                dibujarPasillo(g, pasillo);
            }
            
            cont++;
            
        }
    }

    public Pasillo[] getPasillos() {
        return pasillos;
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
        
        g.drawLine(pasillo.getX()*50, pasillo.getY1()*50, pasillo.getX()*50, pasillo.getY2()*50);
    }
    
    public void borrarCirculos() {
        casillerosConCirculo.clear();
        repaint();
    }
    
    public void borrarPasillos() {
        pasillosRecorrer.clear();
        repaint();
    }
    
    public void calcularDistancia(){
        distanciaTotal = 0;
        Pasillo primerPasillo = pasillosRecorrer.get(0);
        
        if(primerPasillo.getX() != 2){
            distanciaTotal += primerPasillo.getX() - 2;
        }
        
        for(Pasillo pasillo : pasillosRecorrer){
            
            if(pasillo.getNroPasillo() != 6 && pasillo.getNroPasillo() != 7){
                distanciaTotal += pasillo.getY1() - pasillo.getY2();
                if(!pasillo.equals(primerPasillo)){
                    distanciaTotal += pasillo.getX() - pasillosRecorrer.get(pasillosRecorrer.indexOf(pasillo)-1).getX();
                }
            }else if(pasillo.getNroPasillo() == 6 && (pasillosRecorrer.size()-2) % 2 != 0){                
                distanciaTotal += pasillo.getY1() - pasillo.getY2();
            }else if(pasillo.getNroPasillo() == 7){
                distanciaTotal += pasillo.getX() - 2;
            }
            
        }
        
        
    }

    public ArrayList<Pasillo> getPasillosRecorrer() {
        return pasillosRecorrer;
    }

    public int getDistanciaTotal() {
        return distanciaTotal;
    }

    
}
