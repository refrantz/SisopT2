import java.util.ArrayList;
import java.util.List;

public class ParticaoFixa implements Memoria{
    static List<String> memoriaPartiçãoFixa;
    static Integer partitionSize;
    static Integer memorySize;

    public ParticaoFixa(Integer memorySize, Integer partitionSize){
        this.partitionSize = partitionSize;
        this.memorySize = memorySize;
        memoriaPartiçãoFixa = new ArrayList<String>();
    }

    public class Particao{

    }

    @Override
    public void showMemoria() {

    }

    @Override
    public void in(String processo, Integer espaco) {
        //if(memoriaPartiçãoFixa.size() + espaco > memorySize ){
            //System.out.println("ESPAÇO INSUFICIENTE DE MEMÓRIA");
        //}


    }

    @Override
    public void out(String processo) {

    }
}
