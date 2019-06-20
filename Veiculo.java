public abstract class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private int qtdRodas;
    private int velocMax;
    private Motor motor;
    private TstPlaca tstPlaca;    

    public abstract int calcVel(); 

    public Veiculo(){
        placa = "";
        marca = "";
        modelo = "";
        cor = "";
        qtdRodas = 0;
        velocMax = 0;
        motor = new Motor();
        tstPlaca = new TstPlaca();
    }
    public Veiculo(
        String placa,
        String marca,
        String modelo,
        String cor,
        int qtdRodas,
        int velocMax,
        Motor motor,
        TstPlaca tstPlaca 
    ){
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.qtdRodas = qtdRodas;
        this.velocMax = velocMax;
        this.motor = motor;
        this.tstPlaca = tstPlaca;
    }

    public final void setPlaca(String placa){
        this.placa = placa;
    }
    public final String getPlaca(){
        return placa;
    }
    public final void setMarca(String marca){
        this.marca = marca;
    }
    public final String getMarca(){
        return marca;
    }
    public final void setModelo(String modelo){
        this.modelo = modelo;
    }
    public final String getModelo(){
        return modelo;
    }
    public final void setCor(String cor){
        this.cor = cor;
    }
    public final String getCor(){
        return cor;
    }
    public final void setQtdRodas(int qtdRodas){
        this.qtdRodas = qtdRodas;
    }
    public final int getQtdRodas(){
        return qtdRodas;
    }
    public final void setVelocMax(int velocMax) throws VelocException{
        if(velocMax < 100 || velocMax > 250)
            throw new VelocException();

        this.velocMax = velocMax;
    }
    public final int getVelocMax(){
        return velocMax;
    }
    public final void setMotor(Motor motor){
        this.motor = motor;
    }
    public final Motor getMotor(){
        return motor;
    }
    public final void setTstPlaca(TstPlaca tstPlaca){
        this.tstPlaca = tstPlaca;
    }
    public final TstPlaca getTstPlaca(){
        return tstPlaca;
    }
}