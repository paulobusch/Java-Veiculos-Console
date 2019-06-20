public class Teste{
    public static void main(String arg[]){
        Leitura l = new Leitura();
        Passeio p = new Passeio();
        Carga c = new Carga();
        BDVeiculos db = new BDVeiculos();

        int opc = 0;
        do{
            do{
                opc = opcaoMenu();

                if(opc < 1 || opc > 9){
                    Runtime.getRuntime().exec("cls");
                    System.out.println("Op��o inv�lida!");
                }
            }while(opc < 1 || opc > 9);

            Runtime.getRuntime().exec("cls");
            funcaoMenu(opc, db);

        }while(opc != 9);
    }

    //================== MENU ===================
    private static int opcaoMenu(){
        System.out.printf("Sistema de Gest�o de Ve�culos - Menu Inicial\n\n");
        System.out.println("1)_ Cadastrar Ve�culo de Passeio");
        System.out.println("2)_ Cadastrar Ve�culo de Carga");
        System.out.println("3)_ Imprimir Todos os Ve�culos de Passeio");
        System.out.println("4)_ Imprimir Todos os Ve�culos de Carga");
        System.out.println("5)_ Imprimir Ve�culo de Passeio pela Placa");
        System.out.println("6)_ Imprimir Ve�culo de Carga pela Placa");
        System.out.println("7)_ Alterar dados do Ve�culo de Passeio pela Placa");
        System.out.println("8)_ Alterar dados do Ve�culo de Carga pela Placa");
        System.out.println("9)_ Sair do Sistema");

        try{
            return Integer.parseInt(l.entraDados("Op��o: "));
        }catch(NumberFormatException ex){                
            Runtime.getRuntime().exec("cls");
            System.out.println("Op��o inv�lida!");
        }

        return 0;
    }
    private static void funcaoMenu(int opc, BDVeiculos db){
        switch(opc){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                System.out.println("Sa�da do sistema!");
                break;
        }
    }

    //================== CADASTROS ===================
    public static Passeio obterPasseio(){
        Passeio passeio = cadVeiculo();

        return passeio;
    }
    public static Carga obterCarga(){
        Carga carga = cadVeiculo();

        return carga;
    }

    //================== IMPRIMIR ===================
    private static void printVeiculo(Veiculo veiculo){

    }
    private static void printPasseio(Passeio passeio){

        printVeiculo(passeio);
    }
    private static void printCarga(Carga carga){

        printVeiculo(carga);
    }
}