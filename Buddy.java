public class Buddy implements Memoria{

    int memorySize;
    Node root;
    Node current;
    
    private static class Node{

        Node left;
        Node right;
        Node parent;
        String processo;
        int size;

        private Node(){
        }

        private Node(int size){
            this.size = size;
        }

        public boolean isEmpty(){
            return this.size == 0;
        }

        public boolean canCollapse(){
            return isEmpty() && left.isEmpty() && right.isEmpty();
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

        if(espaco > current.size){
            


        }else if(espaco == current.size){

            current.processo = processo;
            current.size = espaco;

            if(current == current.parent.left){
                current = current.parent.right;
            }else if(current == current.parent.right){
                
            }

        }else if (espaco < current.size){



        }

    }

    public void split(Integer espaco, Node splitCurrent){

        if(espaco < splitCurrent.size/2){

            this.current.left = new Node();
            this.current.right = new Node();

        }

    }

    @Override
    public void out(String processo) {

    }
}
