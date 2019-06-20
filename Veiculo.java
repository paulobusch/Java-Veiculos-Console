public abstract class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private int qtdRodas;
    private int velocMax;
    private Motor motor;
    private TstPlaca tstPlaca;    

    public abstract void calcVel(); 

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

    public void setPlaca(String placa){
        this.placa = placa;
    }
    public String getPlaca(){
        return placa;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    public String getMarca(){
        return marca;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    public String getModelo(){
        return modelo;
    }
    public void setCor(String cor){
        this.cor = cor;
    }
    public String getCor(){
        return cor;
    }
    public void setQtdRodas(int qtdRodas){
        this.qtdRodas = qtdRodas;
    }
    public int getQtdRodas(){
        return qtdRodas;
    }
    public void setVelocMax(int velocMax){
        this.velocMax = velocMax;
    }
    public int getVelocMax(){
        return velocMax;
    }
    public void setMotor(Motor motor){
        this.motor = motor;
    }
    public Motor getMotor(){
        return motor;
    }
    public void setTstPlaca(TstPlaca tstPlaca){
        this.tstPlaca = tstPlaca;
    }
    public TstPlaca getTstPlaca(){
        return tstPlaca;
    }
}