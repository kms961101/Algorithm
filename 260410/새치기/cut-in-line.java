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
	static int N, M, Q;
	static Node[] nodes;
	static int[] lines;
	
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	Q = Integer.parseInt(st.nextToken());
    	
    	nodes = new Node[N + 1];
    	lines = new int[N + 1];
    	for(int i = 1; i <= N; i++) nodes[i] = new Node(i);
    	
    	for(int i = 1; i <= M; i++) {
    		String[] arr = br.readLine().split(" ");
    		int cnt = Integer.parseInt(arr[0]);
    		
    		for(int j = 1; j < cnt; j++) {
    			int a = Integer.parseInt(arr[j]);
    			int b = Integer.parseInt(arr[j + 1]);
    			connect(nodes[a], nodes[b]);
    			lines[a] = i;
    			lines[b] = i;
    		}
    	}
    	
    	for(int j = 0; j < Q; j++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			if(type == 1) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				Node nodeA = pop(a);
				insertFront(nodeA, b);
			}
			else if(type == 2) {
				int a = Integer.parseInt(st.nextToken());
				pop(a);
			}
			else if(type == 3) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				insertIndexFront(nodes[a], nodes[b], nodes[c]);
			}
		}
		
		for(int i = 1; i <= M; i++) {
			Node node = null;
			for(int j = 1; j <= N; j++) {
				if(lines[j] == i) {
					node = nodes[j];
					j = N;
				}
			}
			
			if(node == null) {
				System.out.println(-1);
				continue;
			}
			while(node.prev != null)
				node = node.prev;
			
			while(node != null) {
				System.out.print(node.num + " ");
				node = node.next;
			}
			
			System.out.println();
				
		}
    }
    
    static void insertIndexFront(Node a, Node b, Node c) {
    	int cLine = lines[c.num];
    	if(cLine == 0) return;
    	
    	connect(a.prev, b.next);
    	connect(c.prev, a);
    	connect(b, c);
    	
    	Node next = a;
    	while(next != c) {
    		lines[next.num] = cLine;
    		next = next.next;
    	}
    }
    
    static void insertFront(Node node, int num) {
    	int numLine = lines[num];
    	if(numLine == 0) return;
    	
    	lines[node.num] = numLine;
    	connect(nodes[num].prev, node);
    	connect(node, nodes[num]);
    }
    
    static Node pop(int num) {
    	Node node = nodes[num];
    	connect(node.prev, node.next);
    	
    	node.prev = node.next = null;
    	lines[num] = 0;
    	return node;
    }
    
    static void connect(Node a, Node b) {
    	if(a != null) a.next = b;
    	if(b != null) b.prev = a;
    }
}