public class BST implements EstruturaDeDados{

    private Node root;

    @Override
    public void insert(int key) {
        if (root == null){
            root = new Node(key);
        } else{
            insertNode(root, key);   
        }
    }

    private void insertNode(Node n, int key){
        if (key >= n.getValue()){
            //inserir na direita
            if (n.getRight() == null){
                Node newN = new Node(key);
                n.setRight(newN);
            } else {
                insertNode(n.getRight(), key);
            }
        } else {
            //inserir na esquerda
            if (n.getLeft() == null){
                Node newN = new Node(key);
                n.setLeft(newN);
            } else {
                insertNode(n.getLeft(), key);
            }
        }
    }

    @Override
    public void delete(int chave) {
        remover(chave);
    
    }

    private boolean remover (int chave){
        Node atual = this.root;
        Node paiatual = null;
        while(atual != null ){
            if (atual.getValue() == chave){
                break;
            } else if (chave < atual.getValue() ){
                paiatual = atual;
                atual= atual.getLeft();

            } else {
                paiatual = atual;
                atual= atual.getRight();
            }

        }  if ( atual != null ){
            if(atual.getRight() != null){
                Node substitiNode = atual.getRight();
                 Node substitipai = atual ;
                 while ( substitiNode.getLeft()!= null){
                    substitipai = substitiNode;
                    substitiNode = substitiNode.getLeft();
                 } if (paiatual!= null){
                    if ( atual.getValue()<paiatual.getValue()){
                        paiatual.setLeft(substitiNode);
                    }
                    else {
                        paiatual.setRight(substitiNode);
                    }
                 }
                 else {
                    this.root = substitiNode;
                 } 
                 

                 if ( substitiNode.getValue()<substitipai.getValue()){
                    substitipai.setLeft(null);
                }
                else {
                    substitipai.setRight(null);
                }

          

            } else if ( atual.getLeft() != null){
                 Node substitiNode = atual.getLeft();
                 Node substitipai = atual ;
                 while ( substitiNode.getRight()!= null){
                    substitipai = substitiNode;
                    substitiNode = substitiNode.getRight();
                
                 } 
                 if (paiatual != null ){
                    if ( atual.getValue()<paiatual.getValue()){
                        paiatual.setLeft(substitiNode);
                    }
                    else {
                        paiatual.setRight(substitiNode);
                    }

                 }
                 else {
                    this.root = substitiNode;
                 }
                 

                 if ( substitiNode.getValue()<substitipai.getValue()){
                    substitipai.setLeft(null);
                }
                else {
                    substitipai.setRight(null);
                }

            }else {
                if (paiatual!= null){
                    if ( atual.getValue()<paiatual.getValue()){
                        paiatual.setLeft(null);
                    }
                    else {
                        paiatual.setRight(null);
                    }

                } else {
                    this.root = null;
                }
               
            }
              return true ;
        }
         else {
            return false ;
         }
        

    }

    @Override
    public boolean search(int key) {
        if (root == null){
            return false;
        }
        return searchNode(root, key);
    }

    private boolean searchNode(Node n, int key){
        if (n.getValue() == key){
            return true;
        } else if (key > n.getValue()){
            if (n.getRight() == null){
                return false;
            } else {
                return searchNode(n.getRight(),key);
            }
        } else {
            if (n.getLeft() == null){
                return false;
            } else {
                return searchNode(n.getLeft(),key);
            }
        }
    }

    @Override
    public int minimum() {
        if (root == null) {
            return -1;
        } else {
            return compararMinimo(root, root.getValue());
        }
    }

    private int compararMinimo(Node no, int menor) {
        
        if (no.getLeft() != null) {
            if (no.getLeft().getValue() < menor) {
                menor = no.getLeft().getValue();
            }
            return compararMinimo(no.getLeft(), menor);
        } else {
            return menor;
        }
    }

    @Override
    public int maximum() {
        if (root == null) {
            return -1;
        } else {
            return compararMaximo(root, root.getValue());
        }
    }

    private int compararMaximo(Node no, int max) {
        if (no.getRight() != null) {
            if (no.getRight().getValue() > max) {
                max = no.getRight().getValue();
            }
            return compararMaximo(no.getRight(), max);
        } else {
            return max;
        }
    }

    @Override
    public int sucessor(int chave) {
        int[] sucessor = new int[1];
        int max = compararMaximo(root, root.getValue());
        sucessor[0] = max;

        if (chave == max) {
            return -1;
        } else {
            searchSucessor(root, chave, sucessor);
            return sucessor[0];
        }
    }

    private void searchSucessor(Node no, int key, int[] sucessor) {
        if (no != null) {
            searchSucessor(no.getLeft(), key, sucessor);
            if (no.getValue() > key && no.getValue() < sucessor[0]) {
                sucessor[0] = no.getValue();
            }
            searchSucessor(no.getRight(), key, sucessor);
        }
    }

    @Override
    public int prodessor(int chave) {
        int [] prodessor = new int [1];
        int min = compararMinimo(root, root.getValue());
        prodessor[0] = min;

        if (chave == min) {
            return -1;
        } else {
            searchProdessor(root, chave, prodessor);
            return prodessor[0];
        }
    }

    private void searchProdessor(Node no, int key, int[] prodessor) {
        if (no != null) {
            searchProdessor(no.getRight(), key, prodessor);
            if (no.getValue() < key && no.getValue() >prodessor[0]) {
                prodessor[0] = no.getValue();
            }
            searchProdessor(no.getLeft(), key, prodessor);
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();
        System.out.println(tree.search(7));
        tree.insert(4);
        tree.insert(2);
        tree.insert(5);
        tree.insert(6);
        tree.insert(10);
        tree.insert(99);
        tree.insert(98);
        tree.insert(8);
        tree.insert(1);
        System.out.println(tree.search(5));
        System.out.println(tree.search(7));
        System.out.println(tree.search(1));
        System.out.println("minimo: "+tree.minimum());
        System.out.println("maximo: "+tree.maximum());
        System.out.println("sucessor de 5 na lista: "+tree.sucessor(5));
        System.out.println("antecessor de 99 na lista: "+tree.prodessor(99));
        tree.delete(10);
        System.out.println(tree.search(10));
        tree.delete(99);
        System.out.println(tree.search(99));
    }
}

// EQUIPE:
// Ezequiel Henrique Melo do Nascimento
// Izadora Freitas Oliveira
// Pedro Henrique de Almeida Santos