import java.util;

public class BDVeiculos {
    private List<Passeio> lstPasseio;
    private List<Carga> lstCarga;

    public BDVeiculos(){
        lstPasseio = new ArrayList<Passeio>();
        lstCarga = new ArrayList<Carga>();
    }
    public void Insert(Passeio passeio){
        lstPasseio.Add(passeio);
    }
    public void Insert(Carga carga){
        lstCarga.Add(carga);
    }
}