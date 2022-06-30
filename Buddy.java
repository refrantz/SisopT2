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

        private Node(int size){
            this.processo = "";
            this.processSize = 0;
            this.size = size;
        }

        public boolean isEmpty(){
            return this.processSize == 0 && (this.left == null || this.left.isEmpty()) && (this.right == null || this.right.isEmpty());
        }

        public boolean canCollapse(){
            if(this.hasChildren){
                return this.left.isEmpty() && this.right.isEmpty();
            }else{
                return true;
            }
        }

        public void collapse(){
            this.processo = "";
            this.processSize = 0;
            this.hasChildren = false;
            this.left = null;
            this.right = null;
        }

    }

    public Buddy(int memorySize){
        this.memorySize = memorySize;
        this.root = new Node(memorySize);
        this.current = root;
    }

    @Override
    public void showMemoria() {

        int internalFrag = root.size - root.processSize;

        if(root.isEmpty()){
            System.out.print("| " + root.size + " |");
        }else if(!root.hasChildren && internalFrag > 0){
            System.out.print("| " + internalFrag + " |");
        }else{
            showMemoria(root.left);
            showMemoria(root.right);
        }

    }

    public void showMemoria(Node next) {

        int internalFrag = next.size - next.processSize;

        if(next.isEmpty()){
            System.out.print("| " + next.size + " |");
        }else if(!next.hasChildren && internalFrag > 0){
            System.out.print("| " + internalFrag + " |");
        }else{
            showMemoria(next.left);
            showMemoria(next.right);
        }

    }

    @Override
    public void in(String processo, Integer espaco) {

        current = findNextCurrent(root);

        if(espaco > memorySize){
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
                    inWithoutCurrent(processo, espaco);
                }
    
            }else if(espaco == current.size){
    
                current.processo = processo;
                current.processSize = espaco;
    
            }else if(espaco < current.size){
    
                current = split(espaco, current);
                current.processo = processo;
                current.processSize = espaco;
    
            }

        }
    }

    public void inWithoutCurrent(String processo, Integer espaco) {

        if(espaco == current.size){

            current.processo = processo;
            current.processSize = espaco;
    
        }else if(espaco < current.size){

            current = split(espaco, current);
            current.processo = processo;
            current.processSize = espaco;

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
            retorno = findNextCurrent(nextCurr.left, espaco);
            if(retorno != null){
                return retorno;
            }else{
                return findNextCurrent(nextCurr.right, espaco);
            }
        }else{
            return null;
        }

    }

    public Node findTarget(Node nextCurr, String target){

        Node retorno;

        if(nextCurr.processo.equals(target)){
            return nextCurr;
        }else if(nextCurr.hasChildren){
            retorno = findTarget(nextCurr.left, target);
            if(retorno != null){
                return retorno;
            }else{
                return findTarget(nextCurr.right, target);
            }
        }else{
            return null;
        }

    }

    
    public void findCollapse(Node nextCurr){

        if(nextCurr.isEmpty()){
            nextCurr.collapse();
        }else if(nextCurr.hasChildren){
            findCollapse(nextCurr.left);
            findCollapse(nextCurr.right);
        }

        if(nextCurr.isEmpty()){
            nextCurr.collapse();
        }
    }

    @Override
    public void out(String processo) {

        Node alvo = findTarget(root, processo);

        if(alvo == null){
            System.out.println("PROCESSO NAO ENCONTRADO");
        }else{
            alvo.processSize = 0;
            alvo.processo = "";
            findCollapse(root);
        }
       

    }
}
