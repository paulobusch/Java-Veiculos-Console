import java.io.IOException;

public class Teste{
    public static void main(String arg[]){
        Leitura leitura = new Leitura();
        BDVeiculos db = new BDVeiculos();

        int opc = 0;
        do{
            opc = opcaoMenu(leitura);
            funcaoMenu(opc, leitura, db);
        }while(opc != 9);
    }

    //================== MENU ===================
    private static int opcaoMenu(Leitura leitura){            
        System.out.printf("\n\n\nSistema de Gestão de Veículos - Menu Inicial\n\n");
        System.out.println("1)_ Cadastrar Veículo de Passeio");
        System.out.println("2)_ Cadastrar Veículo de Carga");
        System.out.println("3)_ Imprimir Todos os Veículos de Passeio");
        System.out.println("4)_ Imprimir Todos os Veículos de Carga");
        System.out.println("5)_ Imprimir Veículo de Passeio pela Placa");
        System.out.println("6)_ Imprimir Veículo de Carga pela Placa");
        System.out.println("7)_ Alterar dados do Veículo de Passeio pela Placa");
        System.out.println("8)_ Alterar dados do Veículo de Carga pela Placa");
        System.out.println("9)_ Sair do Sistema");

        int opc = 0;
        do{
            try{
                opc = Integer.parseInt(leitura.entraDados("\nOpção: "));
            }catch(NumberFormatException ex){
                System.out.println("Você deve digitar um número!");
                continue;
            }

            if(opc < 1 || opc > 9){
                System.out.println("Opção inválida!");
            }
        }while(opc < 1 || opc > 9);
        return opc;
    }
    private static void funcaoMenu(int opc, Leitura leitura, BDVeiculos db){
        switch(opc){
            case 1:
                db.Insert(obterPasseio(leitura));
                break;
            case 2:
                db.Insert(obterCarga(leitura));
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
                System.out.println("Saída do sistema!");
                break;
        }
    }

    //================== CADASTROS ===================
    private static Veiculo obterVeiculo(Veiculo veiculo, Leitura leitura){
        boolean placaValida = false;
        
        //Veículo
        do{
            veiculo.setPlaca(leitura.entraDados("Placa: "));
            placaValida = veiculo.getTstPlaca().certificPlaca(veiculo.getPlaca());
            if(!placaValida)         
                System.out.println("Placa inválida!");
        }while(!placaValida);

        veiculo.setMarca(leitura.entraDados("Marca: "));
        veiculo.setModelo(leitura.entraDados("Modelo: "));
        veiculo.setCor(leitura.entraDados("Cor: "));
        veiculo.setQtdRodas(tryGetInt(leitura, "Quantidade de rodas: "));
        veiculo.setVelocMax(tryGetInt(leitura, "Velocidade máxima: "));

        //Motor
        veiculo.getMotor().setQtdPistoes(tryGetInt(leitura, "MOTOR - Quantidade piestões: "));
        veiculo.getMotor().setPotencia(tryGetInt(leitura, "MOTOR - Potência: "));

        return veiculo;
    }
    private static Passeio obterPasseio(Leitura leitura){
        Passeio passeio = new Passeio();

        System.out.printf("\nCADASTRO DE VEÍCULO DE PASSEIO\n");     
        passeio = (Passeio)obterVeiculo(passeio, leitura);
        passeio.setQtdPassageiro(tryGetInt(leitura, "Passageiros: "));
        
        return passeio;
    }
    private static Carga obterCarga(Leitura leitura){
        Carga carga = new Carga();

        System.out.printf("\nCADASTRO DE VEÍCULO DE CARGA\n"); 
        carga = (Carga)obterVeiculo(carga, leitura);
        carga.setTara(tryGetInt(leitura, "Tara: "));
        carga.setCargaMax(tryGetInt(leitura, "Carga máxima: "));

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

    //================== ENTRADA ===================
    private static int tryGetInt(Leitura leitura, String rotulo){
        int ret = 0;
        boolean valido;

        do{
            try{
                ret = Integer.parseInt(leitura.entraDados(rotulo));
                valido = true;
            }catch(NumberFormatException ex){    
                valido = false;
                System.out.println("Opção inválida!");
            }
        }while(!valido);

        return ret;
    }
}