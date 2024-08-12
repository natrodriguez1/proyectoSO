package ec.edu.uees.proyectoso;

import java.util.concurrent.locks.*;

public abstract class HiloControlado implements Runnable {
    private final Lock lock = new ReentrantLock();
    private final Condition pauseCondition = lock.newCondition();
    private boolean paused = false;

    @Override
    public final void run() {
//        while (true) {
            lock.lock();
            correrPedido();

            // Optional: Add sleep or break condition to prevent infinite loops
//            try {
//                Thread.sleep(1000); // Simulate some work
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
        //}
    }

    public void pause() {
        lock.lock();
        try {
            paused = true;
            while (paused){
                pauseCondition.await();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void resume() {
        lock.lock();
        try {
            paused = false;
            pauseCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public abstract void correrPedido();
}