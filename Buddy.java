public class Buddy implements Memoria{

    int memorySize;
    Node root;
    Node current;
    
    private static class Node{

        Node left;
        Node right;
        int size;

        private Node(){
        }

        private Node(int size){
            this.size = size;
        }

    }

    public Buddy(int memorySize){
        this.memorySize = memorySize;
        this.root = new Node(memorySize);
        this.current = root;
    }

    @Override
    public void showMemoria() {

    }

    @Override
    public void in(String processo, Integer espaco) {

        if(this.memorySize + espaco > memorySize ){
            System.out.println("ESPAÇO INSUFICIENTE DE MEMÓRIA");
        }

        if(espaco < current.size){
            current.left = new Node(current.size/2);
            current.right = new Node(current.size/2);
            current = current.left;
        }




    }

    @Override
    public void out(String processo) {

    }
}
