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
        Passeio passeio = new Passeio();
        Carga carga = new Carga();
        switch(opc){
            //================== Cadastrar Veículo de Passeio ====================
            case 1:
                Passeio passeioInsert = new Passeio();
                boolean passeioNovo = true;
                boolean passeioAtualizar = false;
                do{
                    passeioAtualizar = false;
                    try{
                        if(passeioNovo)
                            passeioInsert = obterPasseio(leitura, true);
                        db.Insert(passeioInsert);
                    }catch(VeicExistException ex){
                        System.out.println("Já existe um veículo com a mesma placa");
                        String sob = leitura.entraDados("\nDeseja sobrescrever o veículo? [s / n] \n");
                        passeioAtualizar = (sob.charAt(0) == 's' || sob.charAt(0) == 'S');
                        if(passeioAtualizar){
                            passeioInsert.setPlaca(tryGetPlaca(passeioInsert, leitura));
                            passeioNovo = false;
                            continue;
                        }
                    }finally{
                        String cad = leitura.entraDados("Deseja cadastrar um novo veículo de passeio? [s / n] ");
                        passeioNovo = (cad.charAt(0) == 's' || cad.charAt(0) == 'S');
                    }
                }while(passeioAtualizar || passeioNovo);
                break;

            //================== Cadastrar Veículo de Carga ======================
            case 2:
                Carga cargaInsert = new Carga();
                boolean cargaNovo = true;
                boolean cargaAtualizar = false;
                do{
                    cargaAtualizar = false;
                    try{
                        if(cargaNovo)
                            cargaInsert = obterCarga(leitura, true);
                        db.Insert(cargaInsert);
                    }catch(VeicExistException ex){
                        System.out.println("Já existe um veículo com a mesma placa");
                        String sob = leitura.entraDados("\nDeseja sobrescrever o veículo? [s / n] \n");
                        cargaAtualizar = (sob.charAt(0) == 's' || sob.charAt(0) == 'S');
                        if(cargaAtualizar){
                            cargaInsert.setPlaca(tryGetPlaca(cargaInsert, leitura));
                            cargaNovo = false;
                            continue;
                        }
                    }finally{                        
                        String cad = leitura.entraDados("Deseja cadastrar um novo veículo de carga? [s / n] ");
                        cargaNovo = (cad.charAt(0) == 's' || cad.charAt(0) == 'S');
                    }
                }while(cargaAtualizar || cargaNovo);
                break;
            
            //=============== Imprimir Todos os Veículos de Passeio =============
            case 3:
                System.out.printf("\n\nLISTA DE VEÍCULOS DE PASSEIO\n");  
                if(db.getLstPasseio().size() > 0){
                    for(int i = 0; i < db.getLstPasseio().size(); i++){
                        printPasseio(db.getLstPasseio().get(i));
                        System.out.printf("\n");
                    }
                }else{
                    System.out.println("Nenhum veículo de passeio cadastrado");
                }
                break;

            //============= Imprimir Todos os Veículos de Carga ==================
            case 4:
                System.out.printf("\n\nLISTA DE VEÍCULOS DE CARGA\n");    
                if(db.getLstCarga().size() > 0){
                    for(int i = 0; i < db.getLstCarga().size(); i++){
                        printCarga(db.getLstCarga().get(i));
                        System.out.printf("\n");
                    }
                }else{
                    System.out.println("Nenhum veículo de carga cadastrado");
                }
                break;
            
            //============= Imprimir Veículo de Passeio pela Placa ===============
            case 5:
                passeio.setPlaca(tryGetPlaca(passeio, leitura));

                if(db.Query(passeio) != null){                 
                    System.out.printf("\n");
                    printPasseio(db.Query(passeio));
                }else
                    System.out.printf("\nNenhum veículo de passeio encontrado\n");
                break;

            //============= Imprimir Veículo de Passeio pela Placa ===============
            case 6:
                carga.setPlaca(tryGetPlaca(carga, leitura));

                if(db.Query(carga) != null){         
                    System.out.printf("\n");
                    printCarga(db.Query(carga));
                }else
                    System.out.printf("\nNenhum veículo de carga encontrado\n");
                break;

            //========= Alterar dados do Veículo de Passeio pela Placa ============
            case 7:
                passeio.setPlaca(tryGetPlaca(passeio, leitura));

                if(db.Query(passeio) != null){                 
                    System.out.printf("\n");
                    printPasseio(db.Query(passeio));
                    db.Update(obterPasseio(leitura, false));
                }else
                    System.out.printf("\nNenhum veículo de passeio encontrado\n");

                break;
            
            //========= Alterar dados do Veículo de Carga pela Placa ==============
            case 8:                
                carga.setPlaca(tryGetPlaca(carga, leitura));

                if(db.Query(carga) != null){                 
                    System.out.printf("\n");
                    printCarga(db.Query(carga));
                    db.Update(obterCarga(leitura, false));
                }else
                    System.out.printf("\nNenhum veículo de carga encontrado\n");
                break;

            //=========================== Sair do Sistema =========================
            case 9:
                System.out.println("Saída do sistema!");
                break;
        }
    }

    //================== CADASTROS ===================
    private static Veiculo obterVeiculo(Veiculo veiculo, Leitura leitura, boolean cadPlaca){
        boolean placaValida = false;
        
        //Veículo
        if(cadPlaca)
            veiculo.setPlaca(tryGetPlaca(veiculo, leitura));
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
    private static Passeio obterPasseio(Leitura leitura, boolean cadPlaca){
        Passeio passeio = new Passeio();

        System.out.printf("\nCADASTRO DE VEÍCULO DE PASSEIO\n");     
        passeio = (Passeio)obterVeiculo(passeio, leitura, cadPlaca);
        passeio.setQtdPassageiro(tryGetInt(leitura, "Passageiros: "));
        
        return passeio;
    }
    private static Carga obterCarga(Leitura leitura, boolean cadPlaca){
        Carga carga = new Carga();

        System.out.printf("\nCADASTRO DE VEÍCULO DE CARGA\n"); 
        carga = (Carga)obterVeiculo(carga, leitura, cadPlaca);
        carga.setTara(tryGetInt(leitura, "Tara: "));
        carga.setCargaMax(tryGetInt(leitura, "Carga máxima: "));

        return carga;
    }

    //================== IMPRIMIR ===================
    private static void printVeiculo(Veiculo veiculo){
        //Veículo
        System.out.println("Placa: " + veiculo.getPlaca());
        System.out.println("Marca: " + veiculo.getMarca());
        System.out.println("Modelo: " + veiculo.getModelo());
        System.out.println("Cor: " + veiculo.getCor());
        System.out.println("Quantidade de rodas: " + veiculo.getQtdRodas());
        System.out.printf("Velocidade máxima: %d km/h" + veiculo.getVelocMax());
        
        //Motor
        System.out.println("MOTOR - Quantidade piestões: " + veiculo.getMotor().getQtdPistoes());
        System.out.println("MOTOR - Potência: " + veiculo.getMotor().getPotencia());
    }
    private static void printPasseio(Passeio passeio){
        printVeiculo(passeio);        
        System.out.println("Quantidade de passageiros: " + passeio.getQtdPassageiro());
    }
    private static void printCarga(Carga carga){
        printVeiculo(carga);
        System.out.println("Tara: " + carga.getTara());
        System.out.println("Carga Máxima: " + carga.getCargaMax());
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
    private static String tryGetPlaca(Veiculo veiculo, Leitura leitura){
        boolean placaValida = false;
        do{
            veiculo.setPlaca(leitura.entraDados("Placa: "));
            placaValida = veiculo.getTstPlaca().certificPlaca(veiculo.getPlaca());
            if(!placaValida)         
                System.out.println("Placa inválida!");
        }while(!placaValida);

        return veiculo.getPlaca();
    }
}