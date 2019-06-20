public final class Carga extends Veiculo implements Calc{
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

    public final int getTara(){
        return tara;
    }
    public final void setTara(int tara){
        this.tara = tara;
    }
    public final int getCargaMax(){
        return cargaMax;
    }
    public final void setCargaMax(int cargaMax){
        this.cargaMax = cargaMax;
    }

    public final int calcular(){
        return super.getQtdRodas() + super.getVelocMax() + tara + cargaMax;
    }

    public final int calcVel(){
        return super.getVelocMax() * 100000;
    }
}