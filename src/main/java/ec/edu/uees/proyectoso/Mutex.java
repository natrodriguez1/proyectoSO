package ec.edu.uees.proyectoso;

public class Mutex {
    private Pedido hilo;
    private boolean pausado;
    public Mutex(Pedido hilo){
        this.hilo = hilo;
    }
    public void bloquear() throws InterruptedException {
        pausado = true;
       while(pausado){
           synchronized (hilo){
               hilo.wait(100);
           }
       }
    }
    public void desbloquear(){
        pausado = false;
        hilo.notifyAll();
    }
}
