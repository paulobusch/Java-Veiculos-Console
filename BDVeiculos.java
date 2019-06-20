import java.util.ArrayList;
import java.util.List;

public class BDVeiculos {
    private List<Passeio> lstPasseio;
    private List<Carga> lstCarga;

    public BDVeiculos(){
        lstPasseio = new ArrayList<Passeio>();
        lstCarga = new ArrayList<Carga>();
    }
    public void Insert(Passeio passeio){
        lstPasseio.add(passeio);
    }
    public void Insert(Carga carga){
        lstCarga.add(carga);
    }
}