import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class HuffmanCode {
    public static HuffmanTreeNode buildHuffmanTree(List<HuffmanTreeNode> nodes){
        int n = nodes.size();
        //n-1´Î
        for(int i=1;i<n;i++){
            Collections.sort(nodes);

            HuffmanTreeNode min1 = nodes.remove(0);
            HuffmanTreeNode min2 = nodes.remove(0);
            HuffmanTreeNode newNode = new HuffmanTreeNode(min1.getWeight()+min2.getWeight());
            newNode.setLeftChild(min1);
            newNode.setRightChild(min2);
            nodes.add(newNode);
        }
        return nodes.get(0);
    }

    public static void generateHuffmanCode(HuffmanTreeNode root){
        if(root.getLeftChild() != null){
            root.getLeftChild().setCode(root.getCode() + "0");
            generateHuffmanCode(root.getLeftChild());
        }

        if(root.getRightChild() != null){
            root.getRightChild().setCode(root.getCode() + "1");
            generateHuffmanCode(root.getRightChild());
        }
    }

    public static void main(String[] args) {
        HuffmanTreeNode node1 = new HuffmanTreeNode(3);
        node1.setContent("A");
        HuffmanTreeNode node2 = new HuffmanTreeNode(1);
        node2.setContent("B");
        HuffmanTreeNode node3 = new HuffmanTreeNode(2);
        node3.setContent("C");
        HuffmanTreeNode node4 = new HuffmanTreeNode(1);
        node4.setContent("D");

        List<HuffmanTreeNode>nodes = new LinkedList<HuffmanTreeNode>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);

        List<HuffmanTreeNode>nodeCopy = new ArrayList<HuffmanTreeNode>();
        nodeCopy.addAll(nodes);


        HuffmanTreeNode root = buildHuffmanTree(nodes);
        generateHuffmanCode(root);


        for(HuffmanTreeNode node:nodeCopy){
            System.out.println(node.getContent() + "->" + node.getCode());
        }
    }
}