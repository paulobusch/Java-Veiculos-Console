public class Passeio extends Veiculo implements Calc{
    private int qtdPassageiro;

    public Passeio(){
        qtdPassageiro = 0;
    }
    public Passeio(int qtdPassageiro){
        this.qtdPassageiro = qtdPassageiro;
    }    

    public void setQtdPassageiro(int qtdPassageiro){
        this.qtdPassageiro = qtdPassageiro;
    }
    public int getQtdPassageiro(){
        return qtdPassageiro;
    }

    public int calcular(){
        return 0;
    }

    public void calcVel(){
        
    }
}