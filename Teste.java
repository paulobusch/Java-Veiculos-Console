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
        System.out.printf("\n\n\nSistema de Gest�o de Ve�culos - Menu Inicial\n\n");
        System.out.println("1)_ Cadastrar Ve�culo de Passeio");
        System.out.println("2)_ Cadastrar Ve�culo de Carga");
        System.out.println("3)_ Imprimir Todos os Ve�culos de Passeio");
        System.out.println("4)_ Imprimir Todos os Ve�culos de Carga");
        System.out.println("5)_ Imprimir Ve�culo de Passeio pela Placa");
        System.out.println("6)_ Imprimir Ve�culo de Carga pela Placa");
        System.out.println("7)_ Alterar dados do Ve�culo de Passeio pela Placa");
        System.out.println("8)_ Alterar dados do Ve�culo de Carga pela Placa");
        System.out.println("9)_ Sair do Sistema");

        int opc = 0;
        do{
            try{
                opc = Integer.parseInt(leitura.entraDados("\nOp��o: "));
            }catch(NumberFormatException ex){
                System.out.println("Voc� deve digitar um n�mero!");
                continue;
            }

            if(opc < 1 || opc > 9){
                System.out.println("Op��o inv�lida!");
            }
        }while(opc < 1 || opc > 9);
        return opc;
    }
    private static void funcaoMenu(int opc, Leitura leitura, BDVeiculos db){
        boolean placaValida = false;
        switch(opc){
            case 1:
                db.Insert(obterPasseio(leitura));
                break;
            case 2:
                db.Insert(obterCarga(leitura));
                break;
            case 3:
                System.out.printf("\n\nLISTA DE VE�CULOS DE PASSEIO\n");  
                if(db.getLstPasseio().size() > 0){
                    for(int i = 0; i < db.getLstPasseio().size(); i++){
                        printPasseio(db.getLstPasseio().get(i));
                        System.out.printf("\n");
                    }
                }else{
                    System.out.println("Nenhum ve�culo de passeio cadastrado");
                }
                break;
            case 4:
                System.out.printf("\n\nLISTA DE VE�CULOS DE CARGA\n");    
                if(db.getLstCarga().size() > 0){
                    for(int i = 0; i < db.getLstCarga().size(); i++){
                        printCarga(db.getLstCarga().get(i));
                        System.out.printf("\n");
                    }
                }else{
                    System.out.println("Nenhum ve�culo de carga cadastrado");
                }
                break;
            case 5:
                Passeio passeio = new Passeio();
                passeio.setPlaca(tryGetPlaca(passeio, leitura));

                if(db.Query(passeio) != null){                 
                    System.out.printf("\n");
                    printPasseio(db.Query(passeio));
                }else
                    System.out.printf("\nNenhum ve�culo de passeio encontrado\n");
                break;
            case 6:
                Carga carga = new Carga();
                carga.setPlaca(tryGetPlaca(carga, leitura));

                if(db.Query(carga) != null){         
                    System.out.printf("\n");
                    printCarga(db.Query(carga));
                }else
                    System.out.printf("\nNenhum ve�culo de passeio encontrado\n");
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
    private static Veiculo obterVeiculo(Veiculo veiculo, Leitura leitura){
        boolean placaValida = false;
        
        //Ve�culo
        veiculo.setPlaca(tryGetPlaca(veiculo, leitura));
        veiculo.setMarca(leitura.entraDados("Marca: "));
        veiculo.setModelo(leitura.entraDados("Modelo: "));
        veiculo.setCor(leitura.entraDados("Cor: "));
        veiculo.setQtdRodas(tryGetInt(leitura, "Quantidade de rodas: "));
        veiculo.setVelocMax(tryGetInt(leitura, "Velocidade m�xima: "));

        //Motor
        veiculo.getMotor().setQtdPistoes(tryGetInt(leitura, "MOTOR - Quantidade piest�es: "));
        veiculo.getMotor().setPotencia(tryGetInt(leitura, "MOTOR - Pot�ncia: "));

        return veiculo;
    }
    private static Passeio obterPasseio(Leitura leitura){
        Passeio passeio = new Passeio();

        System.out.printf("\nCADASTRO DE VE�CULO DE PASSEIO\n");     
        passeio = (Passeio)obterVeiculo(passeio, leitura);
        passeio.setQtdPassageiro(tryGetInt(leitura, "Passageiros: "));
        
        return passeio;
    }
    private static Carga obterCarga(Leitura leitura){
        Carga carga = new Carga();

        System.out.printf("\nCADASTRO DE VE�CULO DE CARGA\n"); 
        carga = (Carga)obterVeiculo(carga, leitura);
        carga.setTara(tryGetInt(leitura, "Tara: "));
        carga.setCargaMax(tryGetInt(leitura, "Carga m�xima: "));

        return carga;
    }

    //================== IMPRIMIR ===================
    private static void printVeiculo(Veiculo veiculo){
        //Ve�culo
        System.out.println("Placa: " + veiculo.getPlaca());
        System.out.println("Marca: " + veiculo.getMarca());
        System.out.println("Modelo: " + veiculo.getModelo());
        System.out.println("Cor: " + veiculo.getCor());
        System.out.println("Quantidade de rodas: " + veiculo.getQtdRodas());
        System.out.println("Velocidade m�xima: " + veiculo.getVelocMax());
        
        //Motor
        System.out.println("MOTOR - Quantidade piest�es: " + veiculo.getMotor().getQtdPistoes());
        System.out.println("MOTOR - Pot�ncia: " + veiculo.getMotor().getPotencia());
    }
    private static void printPasseio(Passeio passeio){
        printVeiculo(passeio);        
        System.out.println("Quantidade de passageiros: " + passeio.getQtdPassageiro());
    }
    private static void printCarga(Carga carga){
        printVeiculo(carga);
        System.out.println("Tara: " + carga.getTara());
        System.out.println("Carga M�xima: " + carga.getCargaMax());
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
                System.out.println("Op��o inv�lida!");
            }
        }while(!valido);

        return ret;
    }
    private static String tryGetPlaca(Veiculo veiculo, Leitura leitura){
        boolean placaValida = false;
        do{
            veiculo.setPlaca(leitura.entraDados("Placa: "));
            placaValida = veiculo.getTstPlaca().certificPlaca(veiculo.getPlaca());
            if(!placaValida)         
                System.out.println("Placa inv�lida!");
        }while(!placaValida);

        return veiculo.getPlaca();
    }
}