import java.util.List;

public class ParticaoFixa implements Memoria{
    List<String> memoriaPartiçãoFixa;
    Integer memorySize;
    Integer partitionSize;

    public ParticaoFixa(Integer memorySize, Integer partitionSize){
        this.partitionSize = partitionSize;
        this.memorySize = memorySize;
    }

    @Override
    public void showMemoria() {
    }

    @Override
    public void in(String processo, Integer espaço) {

    }

    @Override
    public void out(String processo) {

    }
}
