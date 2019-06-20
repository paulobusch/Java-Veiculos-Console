public class Teste{
    public static void main(String arg[]){
        Leitura l = new Leitura();
        Passeio p = new Passeio();
        Carga c = new Carga();
        BDVeiculos bd = new BDVeiculos();

        int opc = 0;
        do{
            do{
                System.out.printf("Sistema de Gestão de Veículos - Menu Inicial\n\n");
                System.out.println("1)_ Cadastrar Veículo de Passeio");
                System.out.println("2)_ Cadastrar Veículo de Carga");
                System.out.println("3)_ Imprimir Todos os Veículos de Passeio");
                System.out.println("4)_ Imprimir Todos os Veículos de Carga");
                System.out.println("5)_ Imprimir Veículo de Passeio pela Placa");
                System.out.println("6)_ Imprimir Veículo de Carga pela Placa");
                System.out.println("7)_ Alterar dados do Veículo de Passeio pela Placa");
                System.out.println("8)_ Alterar dados do Veículo de Carga pela Placa");
                System.out.println("9)_ Sair do Sistema");

                try{
                    opc = Integer.parseInt(l.entraDados("Opção: "));
                }catch(NumberFormatException ex){                
                    Runtime.getRuntime().exec("cls");
                    System.out.println("Opção inválida!");
                }

                if(opc < 1 || opc > 9){
                    Runtime.getRuntime().exec("cls");
                    System.out.println("Opção inválida!");
                }

            }while(opc < 1 || opc > 9);

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
                    Runtime.getRuntime().exec("cls");
                    System.out.println("Saída do sistema!");
                    break;
            }

        }while(opc != 9);
    }
}