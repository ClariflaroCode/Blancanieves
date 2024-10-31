import java.util.ArrayList;

public class Casa {
    public ArrayList<Boolean> sillas;
    private int cant_sillas = 4;
    private int cant_sillas_ocupadas = 0;
    public ArrayList<Enanitos> enanitosSentados;
    private Blancanieves blancanieves;

    public Casa(Blancanieves blancanieves) {
        this.sillas = new ArrayList<>();
        for (int i = 0; i < cant_sillas; i++) {
            sillas.set(i, false);
        }
        this.enanitosSentados = new ArrayList<>();
        this.blancanieves = blancanieves;

    }
    public synchronized boolean EnanitoAgregadoEsperandoComer(Enanitos enanito) {
        int cant_inicial = cant_sillas_ocupadas;
        if(cant_sillas_ocupadas < cant_sillas) {
            enanitosSentados.add(enanito);
            cant_sillas_ocupadas++;
        }

        return cant_inicial != cant_sillas_ocupadas;
    }
    public synchronized void  EliminarEnanitoDeListaDeEspera(Enanitos enanito) {
        enanitosSentados.remove(enanito);
        cant_sillas_ocupadas--;
    }
    public synchronized Enanitos DevolverPrimerEnanito(){
        return enanitosSentados.getFirst();
    }
    public boolean HayEnanitosEsperandoComer(){
        return enanitosSentados.size() > 0;
    }
    public void BlancanievesDespertate(){
        this.blancanieves.BlancanievesDespierta();
    }
}
