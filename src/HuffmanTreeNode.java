
public class HuffmanTreeNode implements Comparable<HuffmanTreeNode>{
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HuffmanTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(HuffmanTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public HuffmanTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(HuffmanTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content = null;
    private int weight;
    private String code = "";
    private HuffmanTreeNode leftChild = null;
    private HuffmanTreeNode rightChild = null;


    public HuffmanTreeNode(int weight){
        this.weight = weight;
    }

    //Returns:a negative integer, zero, or a positive integer as this object is
    //less than, equal to, or greater than the specified object.
    public int compareTo(HuffmanTreeNode o) {
        if(weight<o.getWeight()){
            return -1;
        }else if(weight > o.getWeight()){
            return 1;
        }else{
            return 0;
        }
    }
}
