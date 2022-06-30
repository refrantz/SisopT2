import java.util.ArrayList;
import java.util.List;

public class ParticaoVariavel implements Memoria{
    static Integer memorySize;
    static Integer allocationPolicy;
    static Character[] memory;

    public ParticaoVariavel(Integer memorySize, int allocationPolicy){
        this.memorySize = memorySize;
        this.allocationPolicy = allocationPolicy;
        this.memory = new Character[memorySize];
    }

    @Override
    public void showMemoria() {
        ArrayList<Integer> espacosLivres = new ArrayList<Integer>();

        int acumulador = 0;
        for(int i = 0; i<memory.length;i++){
            if(memory[i] != null) {
                if(acumulador != 0) {
                    espacosLivres.add(acumulador);
                    acumulador = 0;
                }
            } else {
                acumulador++;
                if(i == (memory.length-1)) {
                    espacosLivres.add(acumulador);
                    acumulador = 0;
                }
            }
        }
        String posicoesLivre = "|";
        for (Integer espacoLivre: espacosLivres) {
            posicoesLivre = posicoesLivre + (" " + espacoLivre + " |");
        }
        System.out.println(posicoesLivre);
    }

    @Override
    public void in(String processo, Integer espaco) {
        int espacosLivresMelhorOpcao = 0;
        int posicaoInicialMelhorOpcao = 0;
        int contadorEspacosLivre = 0;
        int posicaoInicialAtual = 0;

        for (int i = 0; i < this.memorySize; i++) {
            Character posicaoMemoria = this.memory[i];
            if(posicaoMemoria == null && i < (this.memorySize-1)) {
                if(contadorEspacosLivre == 0)
                    posicaoInicialAtual = i;
                contadorEspacosLivre++;
            } else {
                if(posicaoMemoria == null)
                    contadorEspacosLivre++;
                if(this.allocationPolicy == 1) {
                    if(contadorEspacosLivre >= espaco) {
                        if(espacosLivresMelhorOpcao == 0) {
                            espacosLivresMelhorOpcao = contadorEspacosLivre;
                            posicaoInicialMelhorOpcao = posicaoInicialAtual;
                        } else if(contadorEspacosLivre - espaco < espacosLivresMelhorOpcao - espaco) {
                            espacosLivresMelhorOpcao = contadorEspacosLivre;
                            posicaoInicialMelhorOpcao = posicaoInicialAtual;
                        }
                    }
                } else if(this.allocationPolicy == 2) {
                    if(contadorEspacosLivre >= espaco) {
                        if(espacosLivresMelhorOpcao == 0) {
                            espacosLivresMelhorOpcao = contadorEspacosLivre;
                            posicaoInicialMelhorOpcao = posicaoInicialAtual;
                        } else if(contadorEspacosLivre - espaco > espacosLivresMelhorOpcao - espaco) {
                            espacosLivresMelhorOpcao = contadorEspacosLivre;
                            posicaoInicialMelhorOpcao = posicaoInicialAtual;
                        }
                    }
                }
                posicaoInicialAtual = 0;
                contadorEspacosLivre = 0;
            }
        }
        if(espacosLivresMelhorOpcao != 0) {
            for (int i = posicaoInicialMelhorOpcao; i < (posicaoInicialMelhorOpcao+espaco); i++) {
                this.memory[i] = processo.charAt(0);
            }
        } else {
            System.out.println("\"ESPAÇO INSUFICIENTE DE MEMÓRIA\"");
        }
    }

    @Override
    public void out(String processo) {
        Character charProcesso = processo.charAt(0);
        for (int i = 0; i < this.memorySize; i++) {
            if(this.memory[i] == charProcesso) {
                this.memory[i] = null;
            }
        }
    }
}
