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
	static Node[] node;
	static int N, Q;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = null;
    	N = Integer.parseInt(br.readLine());
    	Q = Integer.parseInt(br.readLine());
    	
    	node = new Node[N + 1];
    	for(int i = 1; i <= N; i++) node[i] = new Node(i);
    	for(int i = 1; i < N; i++) {
			connect(node[i], node[i + 1]);
		}
    	for(int q = 0; q < Q; q++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());
    		int d = Integer.parseInt(st.nextToken());
    		
    		change(node[a], node[b], node[c], node[d]);
    	}
    		
		Node cur = node[1];
		while(cur.prev != null) {
			cur = cur.prev;
		}
		
		while(cur.next != null) {
			System.out.print(cur.num + " ");
			cur = cur.next;
		}
		System.out.println(cur.num);
}
    
    static void change(Node a, Node b, Node c, Node d) {
    	Node prevA = c.prev;
    	Node nextB = d.next;
    	
    	Node prevC = a.prev;
    	Node nextD = b.next;
    	
    	// 인접해 주면 예외처리
    	if(b.next == c) {
    		prevA = d;
    		nextD = a;
    	}
    	
    	if(d.next == a) {
    		prevC = b;
    		nextB = c;
    	}
    	
    	connect(prevA, a);
    	connect(b, nextB);
    	
    	connect(prevC, c);
    	connect(d, nextD);
    }
    
    static void connect(Node a, Node b) {
    	if(b != null)
    		b.prev = a;
    	
    	if(a != null)
    		a.next = b;
    }
}