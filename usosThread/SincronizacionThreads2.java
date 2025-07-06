package usosThread;

public class SincronizacionThreads2 {
    public static void main (String []args){


        HilosVarios elhilo1=new HilosVarios();
        HilosVarios2 elhilo2=new HilosVarios2(elhilo1); //el hilo2 no empieza hasta que termine el 1
        elhilo2.start();
        elhilo1.start();
        
        System.out.println("Fin de los hilos"); //ESTO CUENTA COMO HILO 3

    }
}

class HilosVarios extends Thread{ //OTRO MÉTODO PARA CREAR UN HILO
    public void run(){
        for(int i=0; i<15; i++ ){
            System.out.println("Ejecutando hilo " + getName());
        }

    }
}

class HilosVarios2 extends Thread{ //OTRO MÉTODO PARA CREAR UN HILO
    public HilosVarios2(Thread hilo){
        this.hilo=hilo;

    }
    public void run(){

        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0; i<15; i++ ){
            System.out.println("Ejecutando hilo " + getName());
        }

    }
    private Thread hilo;
}
