public class Blancanieves implements Runnable {

    private String nombre;
    public Casa casa;

    public Blancanieves(String nombre, Casa casa) {
        this.nombre = nombre;
        this.casa = casa;
    }
    //¿Qué debe poder hacer blancanieves?
    /*
    * Si un enanito le notificó a Blancanieves que esta sentado,este estará esperando y debe
    * indicarle blancanieves, despertarlo para que coma.
    * "Por su parte Blancanieves, cuando no tiene ningún enanito pendiente de servirle,
    *  se va a pasear con su amigo el Príncipe." Esto querría decir que cuando estén todos libres
    * Blancanieves debe estar esperando con un wait.
    *
    *
    * O sea:
    * enanito despierta
    * consulta si hay silla
    * si hay silla, cambia el booleano a 1, ocupado
    * notifica a blancanieves y se pone a esperar
    * blancanieves estaba durmiendo con un wait, recibe el notify del enanito, se despierta
    * despierta al hilo que le despertó con un notify, se duerme
    * el enanito que se despertó imprime que comió, pone la silla en cero para indicar que esta libre y
    * finaliza su ejecucion
    * Esto garantizaría que blancanieves este "paseando con el principe" hasta que le notifiquen.
    *
    *
    *
    * OPCIONAL: tener una lista de enanitos.
    * */
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

     while (true) {
         //while(casa.HayEnanitosEsperandoComer()) {
         //    AtenderEnanito();
         //}
         if(casa.HayEnanitosEsperandoComer()){
             AtenderEnanito();
         }
         else {
             MeVoyAPasear();
         }
       }
    }
    public synchronized void AtenderEnanito(){
        System.out.println("Blancanieves se despertó del hechizo de sueño, va a atender a un enanito...");
        casa.DevolverPrimerEnanito().DespertateYaTeDieronLaComida(); //acá no puede ser un notify desde blancanieves debe ser de la clase de enanito que es la que tiene acceso a su monitor;
    }
    public synchronized void MeVoyAPasear(){

            try {
                this.wait(); //no hay enanitos, me voy a pasear.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

    }
    public synchronized void BlancanievesDespierta(){
        //tienen que llamarlo los enanitos cuando se sientan
        this.notify();
    }
}
