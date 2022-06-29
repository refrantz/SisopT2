import java.util.ArrayList;
import java.util.List;

public class ParticaoVariavel implements Memoria{
    static Integer memorySize;
    static Integer allocationPolicy;

    public ParticaoVariavel(Integer memorySize, int allocationPolicy){
        this.memorySize = memorySize;
        this.allocationPolicy = allocationPolicy;
    }

    @Override
    public void showMemoria() {

    }

    @Override
    public void in(String processo, Integer espaco) {

    }

    @Override
    public void out(String processo) {

    }
}
