public class Carga extends Veiculo implements Calc{
    private int tara;
    private int cargaMax;

    public Carga(){
        tara = 0;
        cargaMax = 0;
    }
    public Carga(
        int tara,
        int cargaMax
    ){
        this.tara = tara;
        this.cargaMax = cargaMax;
    }

    public int getTara(){
        return tara;
    }
    public void setTara(int tara){
        this.tara = tara;
    }
    public int getCargaMax(){
        return cargaMax;
    }
    public void setCargaMax(int cargaMax){
        this.cargaMax = cargaMax;
    }

    public int calcular(){
        return 0;
    }
}