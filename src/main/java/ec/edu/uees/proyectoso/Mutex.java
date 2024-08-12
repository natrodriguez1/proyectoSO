package ec.edu.uees.proyectoso;

import java.util.concurrent.atomic.AtomicBoolean;

public class Mutex {
    private AtomicBoolean lock;
    private final Object mutex;
    public Mutex() {
        lock = new AtomicBoolean(false);
        this.mutex = new Object();
    }
    private boolean recursoDisponible = true;

    public void step() {
        if (lock.get()) synchronized(mutex) {
            try {
                mutex.wait();
            } catch (InterruptedException ex) {}
        }
    }

    public void lock() throws InterruptedException {
        lock.set(true);
        synchronized(mutex) {
            mutex.wait();
        }
    }

    public void unlock() {
        lock.set(false);

        synchronized(mutex) {
            mutex.notify();
        }
    }
}
