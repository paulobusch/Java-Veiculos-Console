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
    public Passeio Query(Passeio passeio){
        for(int i = 0; i < lstPasseio.size(); i++){
            if(passeio.getPlaca() == lstPasseio.get(i).getPlaca())
                return lstPasseio.get(i);
        }

        return null;
    }
    public Carga Query(Carga carga){        
        for(int i = 0; i < lstCarga.size(); i++){
            if(carga.getPlaca() == lstCarga.get(i).getPlaca())
                return lstCarga.get(i);
        }

        return null;
    }
    public List<Passeio> getLstPasseio(){
        return lstPasseio;
    }
    public List<Carga> getLstCarga(){
        return lstCarga;
    }
}