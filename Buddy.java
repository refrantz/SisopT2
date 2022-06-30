public class Buddy implements Memoria{

    int memorySize;
    Node root;
    Node current;
    
    private static class Node{

        Node left;
        Node right;
        Node parent;
        String processo;
        int processSize;
        int size;
        boolean hasChildren;

        private Node(){
        }

        private Node(int size){
            this.size = size;
        }

        public boolean isEmpty(){
            return this.processSize == 0;
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

        if (current == null){
            System.out.println("TODAS PARTICOES PREENCHIDAS");
            
        }else{

            if(espaco > current.size){

                current = findNextCurrent(root, espaco);
                if(current == null){
                    System.out.println("ESPAÇO INSUFICIENTE DE MEMÓRIA");
                }else{
                    in(processo, espaco);
                }
    
            }else if(espaco == current.size){
    
                current.processo = processo;
                current.processSize = espaco;
    
            }else if(espaco < current.size){
    
                current = split(espaco, current);
                current.processo = processo;
                current.processSize = espaco;
    
            }
    
            current = findNextCurrent(root);

        }
    }

    public Node split(Integer espaco, Node splitCurrent){

        if(espaco <= splitCurrent.size/2){

            this.current.left = new Node(splitCurrent.size/2);
            this.current.right = new Node(splitCurrent.size/2);

            this.current.hasChildren = true;

            this.current = this.current.left;

            return split(espaco, this.current);

        }else{
            return splitCurrent;
        }

    }

    public Node findNextCurrent(Node nextCurr){

        Node retorno;

        if(nextCurr.isEmpty()){
            return nextCurr;
        }else if(nextCurr.hasChildren){
            retorno = findNextCurrent(nextCurr.left);
            if(retorno != null){
                return retorno;
            }else{
                return findNextCurrent(nextCurr.right);
            }
        }else{
            return null;
        }

    }

    public Node findNextCurrent(Node nextCurr, int espaco){

        Node retorno;

        if(nextCurr.isEmpty() && nextCurr.size >= espaco){
            return nextCurr;
        }else if(nextCurr.hasChildren){
            retorno = findNextCurrent(nextCurr.left);
            if(retorno != null){
                return retorno;
            }else{
                return findNextCurrent(nextCurr.right);
            }
        }else{
            return null;
        }

    }


    @Override
    public void out(String processo) {

    }
}
