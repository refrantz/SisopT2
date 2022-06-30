import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Gerenciamento {

    public static int memorySize;
    public static int partitionSize; // apenas para o metodo 1;
    public static int allocationPolicy; // 1 = Best-Fit, 2 = Worst-Fit
    public static int allocationMethod; // 1 = Partição fixa, 2 = Partição variavel, 3 = Buddy

    public static Memoria memory;

    public static void main(String args[]){
        getInput();
        if(allocationMethod == 1){
            memory = new ParticaoFixa(memorySize,partitionSize);
        }
        // Só uma sugestao de como a gente pode fazer pra deixar generico
        if(allocationMethod == 2){
            memory = new ParticaoVariavel(memorySize,allocationPolicy);
        }
        if(allocationMethod == 3){
            memory = new Buddy(memorySize);
        }


        //lendo arquivo - talvez de pra colocar isso em um metodo(?)
        try {
            BufferedReader br = new BufferedReader(new FileReader("exemplo.txt"));
            String linha = br.readLine().strip().toUpperCase();
            String command = "";
            char process = ' ';
            String space = "";

            System.out.println("Pressione enter para continuar a execução passo por passo");

            while(linha != null){

                if(linha.substring(0, 3).equals("OUT")){
                    command = "OUT";
                    process = linha.charAt(4);
                    space = "OUT";
                    memory.showMemoria();
                    
                    memory.out(Character.toString(process)); //linha que pode ser comum a todos os metodos
                }else{
                    command = "IN";
                    process = linha.charAt(3);
                    space = linha.substring(6, linha.indexOf(")"));
                    memory.showMemoria();
                    System.out.println("comando: " + command + " | processo: " + process + " | espaco: " + space);
                    memory.in(Character.toString(process), Integer.parseInt(space)); //linha que pode ser comum a todos os metodos
                }

                System.in.read(); // PRESSIONE ENTER PARA VER A PROXIMA EXECUÇÃO E A SUA MEMORIA RESULTANTE

                linha = br.readLine();
                if(linha != null){
                    linha = linha.strip().toUpperCase();
                }             
            }
            memory.showMemoria();
            br.close();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void getInput() {
        //talvez precise criar alguma validação pra pegar apenas valores com potencia de dois
        Scanner input = new Scanner(System.in);
        System.out.println("Qual o tamanho da memoria?");
        memorySize = input.nextInt();

        System.out.println("Estrategia de alocação: Digite (1) para partições fixas de mesmo tamanho, " +
                "(2) para partições variaveis e " +
                "(3) para partições definidas com o sistema buddy");
        allocationMethod = input.nextInt();

        if(allocationMethod == 1){
            System.out.println("Qual o tamanho da partição?");
            partitionSize = input.nextInt();
        }else if(allocationMethod == 2) {
            System.out.println("Qual a politica de alocação: Digite (1) para Best-Fit e (2) para Worst-Fit?");
            allocationPolicy = input.nextInt();
        }
        //buddy method nao precisa de nenhum outro input
    }
}