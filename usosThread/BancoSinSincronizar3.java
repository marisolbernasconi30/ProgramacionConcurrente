package usosThread;
//import java.util.concurrent.locks.*;
import java.util.concurrent.locks.*;

public class BancoSinSincronizar3 {
    public static void main (String [] args){

        Banco b=new Banco();

        for(int i=0; i<100; i++){
            EjecucionTransferencias r= new EjecucionTransferencias(b, i, 2000);
            Thread t=new Thread(r);
            t.start();
        }
    }
}

class Banco{

    public Banco(){
        cuentas=new double [100]; //CON ESTO CREO LAS 100 CUENTAS CORRIENTES DEL BANCO

        for (int i=0; i<cuentas.length; i++){
            cuentas[i]=2000; //CON ESTO, A CADA CUENTA LE PUSE DOS MIL EUROS
        }

        saldoSuficiente = cierre_banco.newCondition();
    }

    public void transferencia (int cuenta_origen, int cuenta_destino, double cantidad) throws InterruptedException{


        cierre_banco.lock(); //BLOQUEA EL HILO
        try{

        

        while (cuentas[cuenta_origen]<cantidad){  //EVACUA QUE EL SALDO NO ES INFERIOR A LA TRANSFERENCIA
            saldoSuficiente.await(); //EL HILO SE MANTIENE EN LA ESPERA
        }
        System.out.println(Thread.currentThread());  //IMPRIMIMOS EN CONSOLA EL HILO

        cuentas[cuenta_origen]-=cantidad; //CON ESTO DESCONTAMOS LA TRANSFERENCIA

        System.out.printf("%10.2f de %d para %d", cantidad , cuenta_origen , cuenta_destino);

        cuentas[cuenta_destino]+=cantidad; //LE SUMA LA TRANSFERENCIA A LA CUENTA QUE NECESITO

        System.out.printf("Saldo total: %10.2f%n ", getSaldoTotal());

        saldoSuficiente.signalAll(); //LIBERA EL HILO

         } finally{
            
            cierre_banco.unlock(); //DESBLOQUEA EL HILO
         }

    }

    public double getSaldoTotal(){

        double suma_cuentas=0;
        for(double a: cuentas){
            suma_cuentas+=a;
        }
        return suma_cuentas;
    }
    private final double [] cuentas;
    
    private ReentrantLock cierre_banco=new ReentrantLock();

    private Condition saldoSuficiente;
}

class EjecucionTransferencias implements Runnable{

    public EjecucionTransferencias(Banco b, int de, double max){

        banco=b;
        deLaCuenta=de;
        cantidadMax=max;
    }


    public void run() {
            try {
              while(true){
            int paraLaCuenta=(int)(100*Math.random());
            double cantidad=cantidadMax*Math.random();
            banco.transferencia(deLaCuenta, paraLaCuenta, cantidad); //CULPA DE ESTE MÉTODO DABAN MAL LOS NUMEROS
            Thread.sleep((int)(Math.random()*10));
            }
            } catch (InterruptedException e) {
                
            }
        
        
    }

    private Banco banco;
    private int deLaCuenta;
    private double cantidadMax;
    
}
