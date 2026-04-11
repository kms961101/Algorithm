import java.io.*;
import java.util.*;

public class Main {
    static class Node{
		int num;
		Node prev, next;
		
		Node(int num){
			this.num = num;
			this.prev = prev;
			this.next = next;
		}
	}
	static int Q;
	static HashMap<Integer, Node> nodes = new HashMap<>();
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	Q = Integer.parseInt(br.readLine());
    	nodes.put(1, new Node(1));
    	int num = 1;
    	for(int i = 0; i < Q; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		int type = Integer.parseInt(st.nextToken());
    		
    		if(type == 1) {
    			int a = Integer.parseInt(st.nextToken());
    			int b = Integer.parseInt(st.nextToken());
    			
    			if(b == 1) insertEnd(nodes.get(a), ++num);
    			else {
    				//num부터 b개만큼 만들어서 a 뒤에 넣기
    				for(int j = 0; j < b; j++) {
    					num++;
    					nodes.put(num, new Node(num));
    				}
    				for(int j = num - b + 1; j < num; j++)
    					connect(nodes.get(j), nodes.get(j + 1));
    				
    				insertIndexEnd(nodes.get(num - b + 1), nodes.get(num), nodes.get(a));
    			}
    		}
    		else if(type == 2) {
    			int a = Integer.parseInt(st.nextToken());
    			int b = Integer.parseInt(st.nextToken());
    			
    			if(b == 1) insertFront(nodes.get(a), ++num);
    			else {
    				//num부터 b개만큼 만들어서 a 뒤에 넣기
    				for(int j = 0; j < b; j++) {
    					num++;
    					nodes.put(num, new Node(num));
    				}
    				for(int j = num - b + 1; j < num; j++)
    					connect(nodes.get(j), nodes.get(j + 1));
    				
    				insertIndexFront(nodes.get(num - b + 1), nodes.get(num), nodes.get(a));
    			}
    		}
    		else if(type == 3) {
    			int a = Integer.parseInt(st.nextToken());
    			Node node = nodes.get(a);
    			if(node.prev == null || node.next == null)
    				System.out.println(-1);
    			else
    				System.out.println(node.prev.num + " " + node.next.num);
    		}
    	}
    }
    
    static void insertFront(Node node, int num) {
    	Node newNode = new Node(num);
    	nodes.put(num, newNode);
    	
    	connect(node.prev, newNode);
    	connect(newNode, node);
    }
    
    static void insertEnd(Node node, int num) {
    	Node newNode = new Node(num);
    	nodes.put(num, newNode);
    	
    	connect(newNode, node.next);
    	connect(node, newNode);
    }

    static void insertIndexFront(Node a, Node b, Node c) {
    	connect(c.prev, a);
    	connect(b, c);
    }
    
    static void insertIndexEnd(Node a, Node b, Node c) {
    	connect(b, c.next);
    	connect(c, a);
    }
    
    static void connect(Node a, Node b) {
    	if(a != null) a.next =b;
    	if(b != null) b.prev = a;
    }
}