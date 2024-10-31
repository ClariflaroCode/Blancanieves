public class Enanitos implements Runnable{
    private String name;
    public Casa casa;

    public Enanitos(String name, Casa casa) {
        this.name = name;
        this.casa = casa;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        //esto sería con un while?
        while (true) {
            if (casa.EnanitoAgregadoEsperandoComer(this)) {
                //si pude hacer esto, me agregué a esperar la comida.
                casa.BlancanievesDespertate();
                setSentado();
                System.out.print("Terminé de comer");
                casa.EliminarEnanitoDeListaDeEspera(this);
            } else {
                try {
                    Thread.currentThread().sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
    public synchronized void setSentado()  {

        //Está sentado esperando que le sirvan la comida.
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized void DespertateYaTeDieronLaComida(){
        this.notify();

    }

}
