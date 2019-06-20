public class Motor {
    private int qtdPistoes;
    private int potencia;

    public Motor(){
        qtdPistoes = 0;
        potencia = 0;
    }
    public Motor(
        int qtdPistoes,
        int potencia
    ){
        this.qtdPistoes = qtdPistoes;
        this.potencia = potencia;
    }

    public void setQtdPistoes(int qtdPistoes){
        this.qtdPistoes = qtdPistoes;
    }
    public int getQtdPistoes(){
        return qtdPistoes;
    }
    public void setPotencia(int potencia){
        this.potencia = potencia;
    }
    public int getPotencia(){
        return potencia;
    }
}