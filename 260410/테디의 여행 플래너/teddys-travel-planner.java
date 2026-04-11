import java.io.*;
import java.util.*;

public class Main {
    static class Node{
		Node prev, next;
		String city;
		
		Node(String city){
			this.city = city;
			this.prev = this.next = null;
		}
	}
	
	static int N, Q;
	static Node head, tail, pinset;
	static String[] city;
	static Node[] node;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	Q = Integer.parseInt(st.nextToken());
    	
    	city = br.readLine().split(" ");
    	node = new Node[N];
    	for(int i = 0; i < N; i++) node[i] = new Node(city[i]);
    	for(int i = 0; i < N - 1; i++) {
    		connect(node[i], node[i + 1]);
    	}
    	
    	head = pinset = node[0];
    	tail = node[N - 1];
    	
    	while(Q-- > 0) {
    		st = new StringTokenizer(br.readLine());
    		int type = Integer.parseInt(st.nextToken());
    		if(type == 1) {
    			pinset = pinset.next;
    			if(pinset == null) pinset = head;
    		}
    		else if(type == 2) {
    			pinset = pinset.prev;
    			if(pinset == null) pinset = tail;
    		}
    		else if(type == 3) {
    			if(pinset.next == null || head == null) continue;
    			connect(pinset, pinset.next.next);
    		}
    		else if(type == 4) {
    			String a = st.nextToken();
    			Node nodeA = new Node(a);
    			if(pinset == tail) {
    				connect(pinset, nodeA);
    				tail = nodeA;
    			}
    			else {
    				connect(nodeA, pinset.next);
    				connect(pinset, nodeA);
    			}
    		}
    		String cityA = pinset.prev == null ? tail.city : pinset.prev.city;
    		String cityB = pinset.next == null ? head.city : pinset.next.city;
    		if(cityA.equals(cityB))
    			System.out.println(-1);
    		else
    			System.out.println(cityA + " " + cityB);
    	}
    }
    
    static void connect(Node a, Node b) {
    	if(a != null) a.next = b;
    	if(b != null) b.prev = a;
    }
}