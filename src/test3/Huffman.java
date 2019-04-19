package test3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {
	private Map<Character, Integer> map = new HashMap<>();
	private Map<Character, String> ce = new HashMap<>();
	private PriorityQueue<Tree> trees = new PriorityQueue<>();
	private String source;
	private String result;

	public void init(String source) { //统计出现字符统计其权重
		this.source = source;
		char[] chars = source.toCharArray();//将读取到的文本转为char数组
		for (char c : chars) {
			if (!map.containsKey(c)) { //该方法判断Map集合对象中是否包含指定的键名。如果Map集合中包含指定的键名，则返回true，否则返回false。
				map.put(c, 1);
			} else {
				map.put(c, map.get(c) + 1);
			}
		}
		afterInit();
	}

	private void afterInit() {
		map.forEach((c, count) -> {
			Node<Character> node = new Node<>();
			node.key = count;
			node.charData = c;

			Tree tree = new Tree();
			tree.setRoot(node);

			trees.add(tree);
		});
	}

	public void build() {
		while (this.trees.size() > 1) {
			Tree left = this.trees.poll();
			Tree right = this.trees.poll();

			Node node = new Node();
			node.key = left.getRoot().key + right.getRoot().key;
			node.leftChild = left.getRoot();
			node.rightChild = right.getRoot();

			left.setRoot(node);
			this.trees.add(left);
		}
	}

	public void encoding() {
		Tree tree = this.trees.peek();
		this.encoding(tree.getRoot(), "");
	}

	public String encodingResult() { //编码
		StringBuilder sb = new StringBuilder();
		char[] chars = source.toCharArray();
		for (char c : chars) {
			sb.append(ce.get(c) + ' ');//每输出一个编码加一个空格
		}
		return sb.toString();
	}

	private void encoding(Node<Character> node, String encoding) {
		if (node != null) {
			if (node.leftChild == null && node.rightChild == null) {
				ce.put(node.charData, encoding);
			}
			encoding(node.leftChild, encoding + "0");
			encoding(node.rightChild, encoding + "1");
		}
	}

	public void displayTree() {
		Tree tree = this.trees.peek();
		tree.inDisplay(tree.getRoot());
	}

	public void displayEncodeing() {
		ce.forEach((k, v) -> {
			System.out.println(k + ":" + v);
		});
	}

	public static void main(String[] args) {
		String source = readString();//读取文本字符串
		Huffman huffman = new Huffman();
		huffman.init(source); //统计字符以及权重
		huffman.build(); //构建Huffman树
		huffman.displayTree();
		System.out.println("-------");
		huffman.encoding();
		huffman.displayEncodeing();//输出每个key对应的value
		System.out.println(source + ":" + huffman.encodingResult());
		writeString(huffman.encodingResult()); //将编码后写入到文本
		System.out.println("已写入文件encodinghuffman.txt");
	}
	
	// 输入辅助类
	public static String readString() { 
		int len = 0;
		StringBuffer str = new StringBuffer("");
		File file = new File("huffman.txt");
		try {
			FileInputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr);
			String line = null;
			while ((line = in.readLine()) != null) {
				if (len != 0) // 处理换行符的问题
				{
					str.append("\r\n" + line);
				} else {
					str.append(line);
				}
				len++;
			}
			in.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str.toString();
	}

	//输出辅助类
	public static void writeString(String str) {
		
        FileWriter writer;
        try {
            writer = new FileWriter("encodinghuffman.txt");
            writer.write("");//清空原文件内容
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
}