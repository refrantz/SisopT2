import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Gerenciamento {

    public static void main(String args[]){
        try {
            BufferedReader br = new BufferedReader(new FileReader("exemplo.txt"));
            String linha = br.readLine().strip().toUpperCase();
            String command = "";
            char process = ' ';
            String space = "";

            while(linha != null){

                if(linha.substring(0, 3).equals("OUT")){
                    command = "OUT";
                    process = linha.charAt(4);
                    space = "none";
                }else{
                    command = "IN";
                    process = linha.charAt(3);
                    space = linha.substring(6, 8);
                }

                System.out.println("comando: " + command + " | processo: " + process + " | espaco: " + space);

                linha = br.readLine();
                if(linha != null){
                    linha = linha.strip().toUpperCase();
                }             
            }
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}