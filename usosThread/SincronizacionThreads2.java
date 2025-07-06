package usosThread;

public class SincronizacionThreads2 {
    public static void main (String []args){


        HilosVarios elhilo1=new HilosVarios();
        HilosVarios elhilo2=new HilosVarios();
        elhilo1.start();
        try {
            elhilo1.join(); //ESTO ES LO DE SINCRONIZACION. NO HACE EL HILO 2 HASTA QUE EL 1 MUERA
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elhilo2.start();
        try {
            elhilo2.join(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
