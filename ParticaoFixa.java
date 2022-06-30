import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParticaoFixa implements Memoria{
    static String memoriaPartiçãoFixa[];
    static Integer partitionSize;
    static Integer memorySize;

    public ParticaoFixa(Integer memorySize, Integer partitionSize) {
        this.partitionSize = partitionSize;
        this.memorySize = memorySize;
        memoriaPartiçãoFixa = new String[memorySize];
        Arrays.fill(memoriaPartiçãoFixa,"EMPTY");
    }


    public void showMemoria2() {
        System.out.println("------------------------");
        for(int i = 0; i<memoriaPartiçãoFixa.length;i++){
            System.out.println(i + "|      " + memoriaPartiçãoFixa[i] + "          |");
            System.out.println("------------------------");
        }
    }

    @Override
    public void showMemoria() {
        ArrayList<Integer> espacosLivres = new ArrayList<Integer>();
        int contador = 0;

        for(int i = 0; i < memorySize; i++){
           if (memoriaPartiçãoFixa[i].equals("EMPTY") ||  memoriaPartiçãoFixa[i].equals("X")){
               contador++;
           }else{
               if(contador != 0){
                   espacosLivres.add(contador);
               }
               contador = 0;
           }
       }
        espacosLivres.add(contador);
        showMemoria2();

        String posicoesLivre = "|";
        for (Integer espacoLivre: espacosLivres) {
            posicoesLivre = posicoesLivre + (" " + espacoLivre + " |");
        }
        System.out.println(posicoesLivre);

    }

    @Override
    public void in(String processo, Integer espaco) {
        if(espaco > partitionSize ){
            System.out.println("ESPAÇO MAIOR QUE A PARTIÇAO");
        }else{
        for(int i = 0; i < memorySize; i++){
            boolean done = false;
            if(memoriaPartiçãoFixa[i] == "EMPTY"){
                done = true;
                for(int j = 0; j<partitionSize;j++){
                    if(i+espaco > memorySize){
                        System.out.println("\"ESPAÇO INSUFICIENTE DE MEMÓRIA\"");
                        return;
                    }
                    if(j>=espaco){
                        memoriaPartiçãoFixa[i+j] = "X";
                    }else{
                        memoriaPartiçãoFixa[i+j] = processo;
                    }
                }
            }
            if(done == true){
                return;
            }
        }
        System.out.println("\"ESPAÇO INSUFICIENTE DE MEMÓRIA\"");
        }
    }

    @Override
    public void out(String processo) {
        boolean done = false;
        for(int i = 0; i < memorySize; i++){
            if(memoriaPartiçãoFixa[i].equals(processo)){
                done = true;
                for(int j = 0; j < partitionSize; j++){
                    memoriaPartiçãoFixa[i+j] = "EMPTY";
                }
            }
            if(done == true){
                return;
            }
        }
        System.out.println("Processo nao encontrado na memoria");
    }
}
