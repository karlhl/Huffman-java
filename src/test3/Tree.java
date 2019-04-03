package test3;

public class Tree implements Comparable<Tree>{
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
    /**
     *ÖÐÐò±éÀú·¨
     */
    public void inDisplay(Node node){
        if (node!=null){
            inDisplay(node.leftChild);
            System.out.println(node.key+":"+node.charData);
            inDisplay(node.rightChild);
        }
    }
    public void postDisplay(Node node){
        if (node!=null){
            postDisplay(node.leftChild);
            postDisplay(node.rightChild);
            System.out.print(node.key+":"+node.charData);

        }
    }
    @Override
    public int compareTo(Tree o) {
        if (this.root.key>o.root.key){
            return 1;
        }else if (this.root.key==o.root.key){
            return 0;
        }else {
            return -1;
        }
    }
}