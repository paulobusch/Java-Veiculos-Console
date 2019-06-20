import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

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
        Passeio passeio = new Passeio();
        Carga carga = new Carga();
        switch(opc){
            //================== Cadastrar Ve�culo de Passeio ====================
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
                        System.out.println("J� existe um ve�culo com a mesma placa");
                        String sob = leitura.entraDados("\nDeseja sobrescrever o ve�culo? [s / n] \n");
                        passeioAtualizar = (sob.charAt(0) == 's' || sob.charAt(0) == 'S');
                        if(passeioAtualizar){
                            passeioInsert.setPlaca(tryGetPlaca(passeioInsert, leitura));
                            passeioNovo = false;
                            continue;
                        }
                    }finally{
                        String cad = leitura.entraDados("Deseja cadastrar um novo ve�culo de passeio? [s / n] ");
                        passeioNovo = (cad.charAt(0) == 's' || cad.charAt(0) == 'S');
                    }
                }while(passeioAtualizar || passeioNovo);
                break;

            //================== Cadastrar Ve�culo de Carga ======================
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
                        System.out.println("J� existe um ve�culo com a mesma placa");
                        String sob = leitura.entraDados("\nDeseja sobrescrever o ve�culo? [s / n] \n");
                        cargaAtualizar = (sob.charAt(0) == 's' || sob.charAt(0) == 'S');
                        if(cargaAtualizar){
                            cargaInsert.setPlaca(tryGetPlaca(cargaInsert, leitura));
                            cargaNovo = false;
                            continue;
                        }
                    }finally{                        
                        String cad = leitura.entraDados("Deseja cadastrar um novo ve�culo de carga? [s / n] ");
                        cargaNovo = (cad.charAt(0) == 's' || cad.charAt(0) == 'S');
                    }
                }while(cargaAtualizar || cargaNovo);
                break;
            
            //=============== Imprimir Todos os Ve�culos de Passeio =============
            case 3:
                System.out.printf("\n\nLISTA DE VE�CULOS DE PASSEIO\n");  
                if(db.getLstPasseio().size() > 0){
                    printDate();
                    for(int i = 0; i < db.getLstPasseio().size(); i++){
                        printPasseio(db.getLstPasseio().get(i));
                        System.out.printf("\n");
                    }
                }else{
                    System.out.println("Nenhum ve�culo de passeio cadastrado");
                }
                break;

            //============= Imprimir Todos os Ve�culos de Carga ==================
            case 4:
                System.out.printf("\n\nLISTA DE VE�CULOS DE CARGA\n");    
                if(db.getLstCarga().size() > 0){
                    printDate();
                    for(int i = 0; i < db.getLstCarga().size(); i++){
                        printCarga(db.getLstCarga().get(i));
                        System.out.printf("\n");
                    }
                }else{
                    System.out.println("Nenhum ve�culo de carga cadastrado");
                }
                break;
            
            //============= Imprimir Ve�culo de Passeio pela Placa ===============
            case 5:
                passeio.setPlaca(tryGetPlaca(passeio, leitura));

                if(db.Query(passeio) != null){                 
                    System.out.printf("\n");
                    printDate();
                    printPasseio(db.Query(passeio));
                }else
                    System.out.printf("\nNenhum ve�culo de passeio encontrado\n");
                break;

            //============= Imprimir Ve�culo de Passeio pela Placa ===============
            case 6:
                carga.setPlaca(tryGetPlaca(carga, leitura));

                if(db.Query(carga) != null){         
                    System.out.printf("\n");
                    printCarga(db.Query(carga));
                }else
                    System.out.printf("\nNenhum ve�culo de carga encontrado\n");
                break;

            //========= Alterar dados do Ve�culo de Passeio pela Placa ============
            case 7:
                passeio.setPlaca(tryGetPlaca(passeio, leitura));

                if(db.Query(passeio) != null){                 
                    System.out.printf("\n");
                    printDate();
                    printPasseio(db.Query(passeio));
                    db.Update(obterPasseio(leitura, false));
                }else
                    System.out.printf("\nNenhum ve�culo de passeio encontrado\n");

                break;
            
            //========= Alterar dados do Ve�culo de Carga pela Placa ==============
            case 8:                
                carga.setPlaca(tryGetPlaca(carga, leitura));

                if(db.Query(carga) != null){                 
                    System.out.printf("\n");
                    printCarga(db.Query(carga));
                    db.Update(obterCarga(leitura, false));
                }else
                    System.out.printf("\nNenhum ve�culo de carga encontrado\n");
                break;

            //=========================== Sair do Sistema =========================
            case 9:
                System.out.println("Sa�da do sistema!");
                break;
        }
    }

    //================== CADASTROS ===================
    private static Passeio obterPasseio(Leitura leitura, boolean cadPlaca){
        Passeio passeio = new Passeio();

        System.out.printf("\nCADASTRO DE VE�CULO DE PASSEIO\n"); 

        //Ve�culo
        if(cadPlaca)
            passeio.setPlaca(tryGetPlaca(passeio, leitura));
        passeio.setMarca(leitura.entraDados("Marca: "));
        passeio.setModelo(leitura.entraDados("Modelo: "));
        passeio.setCor(leitura.entraDados("Cor: "));
        passeio.setQtdRodas(tryGetInt(leitura, "Quantidade de rodas: "));

        try{
            passeio.setVelocMax(tryGetInt(leitura, "Velocidade m�xima: "));
        }catch(VelocException ex){
            try{
                passeio.setVelocMax(150);
            }catch(VelocException ex1){ }
            System.out.println("A velocidade m�xima est� fora dos limites brasileiros");
        }

        //Motor
        passeio.setMotor(new Motor());
        passeio.getMotor().setQtdPistoes(tryGetInt(leitura, "MOTOR - Quantidade piest�es: "));
        passeio.getMotor().setPotencia(tryGetInt(leitura, "MOTOR - Pot�ncia: "));

        //Passeio
        passeio.setQtdPassageiro(tryGetInt(leitura, "Passageiros: "));
        
        return passeio;
    }
    private static Carga obterCarga(Leitura leitura, boolean cadPlaca){
        Carga carga = new Carga();

        System.out.printf("\nCADASTRO DE VE�CULO DE CARGA\n"); 

        //Ve�culo
        if(cadPlaca)
            carga.setPlaca(tryGetPlaca(carga, leitura));
        carga.setMarca(leitura.entraDados("Marca: "));
        carga.setModelo(leitura.entraDados("Modelo: "));
        carga.setCor(leitura.entraDados("Cor: "));
        carga.setQtdRodas(tryGetInt(leitura, "Quantidade de rodas: "));
        
        try{
            carga.setVelocMax(tryGetInt(leitura, "Velocidade m�xima: "));
        }catch(VelocException ex){
            try{
                carga.setVelocMax(120);
            }catch(VelocException ex1){ }
            System.out.println("A velocidade m�xima est� fora dos limites brasileiros");
        }

        //Motor
        carga.setMotor(new Motor());
        carga.getMotor().setQtdPistoes(tryGetInt(leitura, "MOTOR - Quantidade piest�es: "));
        carga.getMotor().setPotencia(tryGetInt(leitura, "MOTOR - Pot�ncia: "));

        //Carga
        carga.setTara(tryGetInt(leitura, "Tara: "));
        carga.setCargaMax(tryGetInt(leitura, "Carga m�xima: "));

        return carga;
    }

    //================== IMPRIMIR ===================
    private static void printVeiculo(Veiculo veiculo, String unidadeDiatancia){
        //Ve�culo
        System.out.println("Placa: " + veiculo.getPlaca());
        System.out.println("Marca: " + veiculo.getMarca());
        System.out.println("Modelo: " + veiculo.getModelo());
        System.out.println("Cor: " + veiculo.getCor());
        System.out.println("Quantidade de rodas: " + veiculo.getQtdRodas());
        System.out.printf("Velocidade m�xima: %d " + unidadeDiatancia, veiculo.calcVel());
        
        //Motor
        System.out.println("MOTOR - Quantidade piest�es: " + veiculo.getMotor().getQtdPistoes());
        System.out.println("MOTOR - Pot�ncia: " + veiculo.getMotor().getPotencia());
    }
    private static void printPasseio(Passeio passeio){
        printVeiculo(passeio, "M/h");        
        System.out.println("Quantidade de passageiros: " + passeio.getQtdPassageiro());
        System.out.println("Calcular: " + passeio.calcular());
    }
    private static void printCarga(Carga carga){
        printVeiculo(carga, "Cm/h");
        System.out.println("Tara: " + carga.getTara());
        System.out.println("Carga M�xima: " + carga.getCargaMax());
        System.out.println("Calcular: " + carga.calcular());
    }
    private static void printDate(){
        String data = "dd/MM/yyyy HH:mm";
		java.util.Date agora = new java.util.Date();;
		SimpleDateFormat formata = new SimpleDateFormat(data);
		System.out.println("Data: " + formata.format(agora));
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