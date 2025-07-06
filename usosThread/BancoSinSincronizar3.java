package usosThread;

public class BancoSinSincronizar3 {
    public static void main (String [] args){

    }
}

class Banco{

    public Banco(){
        cuentas=new double [100]; //CON ESTO CREO LAS 100 CUENTAS CORRIENTES DEL BANCO

        for (int i=0; i<cuentas.length; i++){
            cuentas[i]=2000; //CON ESTO, A CADA CUENTA LE PUSE DOS MIL EUROS
        }
    }

    public void transferencia (int cuenta_origen, int cuenta_destino, double cantidad){
        if (cuentas[cuenta_origen]<cantidad){  //EVACUA QUE EL SALDO NO ES INFERIOR A LA TRANSFERENCIA
            return;
        }
        System.out.println(Thread.currentThread());  //IMPRIMIMOS EN CONSOLA EL HILO

        cuentas[cuenta_origen]-=cantidad; //CON ESTO DESCONTAMOS LA TRANSFERENCIA

        System.out.printf("%10.2f de %d para %d", cantidad , cuenta_origen , cuenta_destino);

        cuentas[cuenta_destino]+=cantidad; //LE SUMA LA TRANSFERENCIA A LA CUENTA QUE NECESITO

        System.out.printf("Saldo total: %10.2f%n ", getSaldoTotal());
         
    }

    public double getSaldoTotal(){

        double suma_cuentas=0;
        for(double a: cuentas){
            suma_cuentas+=a;
        }
        return suma_cuentas;
    }
    private final double [] cuentas;

}
