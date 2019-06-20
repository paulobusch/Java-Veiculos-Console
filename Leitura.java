import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Leitura {
    public String entraDados(String rotulo){
        String ret = "";
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(reader);

        System.out.println(rotulo);
        try{
            ret = buffer.readLine();
        }catch(IOException ex){
            System.out.println("Erro: Problema na entrada");
        }

        return ret;
    }
}