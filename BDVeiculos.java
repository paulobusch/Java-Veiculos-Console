import java.util.ArrayList;
import java.util.List;

public class BDVeiculos {
    private List<Passeio> lstPasseio;
    private List<Carga> lstCarga;

    public BDVeiculos(){
        lstPasseio = new ArrayList<Passeio>();
        lstCarga = new ArrayList<Carga>();
    }
    
    //================== INSERT ===================
    public void Insert(Passeio passeio) throws VeicExistException{
        if(Query(passeio) != null)
            throw new VeicExistException();

        Carga carga = new Carga();
        carga.setPlaca(passeio.getPlaca());
        if(Query(carga) != null)
            throw new VeicExistException();

        lstPasseio.add(passeio);
    }
    public void Insert(Carga carga) throws VeicExistException{
        if(Query(carga) != null)
            throw new VeicExistException();

        Passeio passeio = new Passeio();
        passeio.setPlaca(carga.getPlaca());
        if(Query(passeio) != null)
            throw new VeicExistException();

        lstCarga.add(carga);
    }
    
    //================== UPDATE ===================
    public void Update(Passeio passeio){
        for(int i = 0; i < lstPasseio.size(); i++){
            if(passeio.getPlaca().equals(lstPasseio.get(i).getPlaca())){
                lstPasseio.set(i, passeio);
                break;
            }
        }
    }    
    public void Update(Carga carga){
        for(int i = 0; i < lstCarga.size(); i++){
            if(carga.getPlaca().equals(lstCarga.get(i).getPlaca())){
                lstCarga.set(i, carga);
                break;
            }
        }
    }
    
    //================== QUERY ===================
    public Passeio Query(Passeio passeio){
        for(int i = 0; i < lstPasseio.size(); i++){
            if(passeio.getPlaca().equals(lstPasseio.get(i).getPlaca()))
                return lstPasseio.get(i);
        }

        return null;
    }
    public Carga Query(Carga carga){        
        for(int i = 0; i < lstCarga.size(); i++){
            if(carga.getPlaca().equals(lstCarga.get(i).getPlaca()))
                return lstCarga.get(i);
        }

        return null;
    }

    //================== LISTAS ===================
    public List<Passeio> getLstPasseio(){
        return lstPasseio;
    }
    public List<Carga> getLstCarga(){
        return lstCarga;
    }
}