import java.io.*;
import java.util.*;

public class Main {
    static class Node{
		int num;
		Node prev, next;
		
		Node(int num){
			this.num = num;
			this.prev = this.next = null;
		}
	}
	static int N, Q;
	static Node node;
	static boolean[] alive;
	static HashMap<Integer, Node> map = new HashMap<>();
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = null;
    	N = Integer.parseInt(br.readLine());
    	Q = Integer.parseInt(br.readLine());
    	alive = new boolean[N + 1];
    	for(int q = 0; q < Q; q++) {
    		st = new StringTokenizer(br.readLine());
    		int cmd = Integer.parseInt(st.nextToken());
    		int i = Integer.parseInt(st.nextToken());
    		if(cmd == 1) {
    			delete(i);
    		}
    		else if(cmd == 2) {
    			int j = Integer.parseInt(st.nextToken());
    			insertPrev(i, j);
    		}
    		else if(cmd == 3) {
    			int j = Integer.parseInt(st.nextToken());
    			insertNext(i, j);
    		}
    		else {
    			printPrevAndNext(i);
    		}
    	}
    	for(int i = 1; i <= N; i++) {
    		if(!alive[i]) {
    			System.out.print("0 ");
    			continue;
    		}
    		Node node = map.getOrDefault(i, new Node(i));
    		System.out.print(node.next == null ? "0 " : node.next.num + " " );
    	}
    }
    
    static void delete(int num) {
    	Node node = map.getOrDefault(num, new Node(num));
    	map.remove(num);
    	alive[num] = false;
    	if(node.prev != null) {
    		if(node.next == null) node.prev.next = null;
    		else node.prev.next = node.next;
    		map.put(node.prev.num, node.prev);
    	}
    	
    	if(node.next != null) {
    		if(node.prev == null) node.next.prev = null;
    		else node.next.prev = node.prev;
    		map.put(node.next.num, node.next);
    	}
    }
    
    static void insertPrev(int i, int j) {
    	Node iNode = map.getOrDefault(i, new Node(i));
    	Node jNode = new Node(j);
    	
    	jNode.next = iNode;
    	if(iNode.prev != null) {
    		iNode.prev.next = jNode;
    		jNode.prev = iNode.prev;
    	}
    	
    	iNode.prev = jNode;
    	map.put(i, iNode);
    	map.put(j, jNode);
    	alive[i] = true;
    	alive[j] = true;
    }
    
    static void insertNext(int i, int j) {
    	Node iNode = map.getOrDefault(i, new Node(i));
    	Node jNode = new Node(j);
    	
    	jNode.prev = iNode;
    	if(iNode.next != null) {
    		iNode.next.prev = jNode;
    		jNode.next = iNode.next;
    	}
    	
    	iNode.next = jNode;
    	map.put(i, iNode);
    	map.put(j, jNode);
    	alive[i] = true;
    	alive[j] = true;
    }
    
    static void printPrevAndNext(int num) {
    	Node node = map.getOrDefault(num, new Node(num));
    	int prev = node.prev == null ? 0 : node.prev.num;
    	int next = node.next == null ? 0 : node.next.num;
    	
    	System.out.println(prev + " " + next);
    }
}