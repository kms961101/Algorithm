import java.io.*;
import java.util.*;

public class Main {
    static class Node{
		Node prev, next;
		int num;
		
		Node(int num){
			this.num = num;
			this.prev = this.next = null;
		}
	}
	
	static Node[] head, tail, node;
	static int N, K, Q;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	Q = Integer.parseInt(br.readLine());
    	
    	head = new Node[K + 1];
    	tail = new Node[K + 1];
    	node = new Node[N + 1];
    	
    	for(int i = 1; i <= N; i++) node[i] = new Node(i);
    	for(int i = 1; i < N; i++) connect(node[i], node[i + 1]);
    	head[1] = node[1];
    	tail[1] = node[N];
    	
    	while(Q-- > 0) {
    		st = new StringTokenizer(br.readLine());
    		int cmd = Integer.parseInt(st.nextToken());
    		int i = Integer.parseInt(st.nextToken());
    		int j = Integer.parseInt(st.nextToken());
    		
    		if(cmd == 1) {
    			Node node = pop_front(i);
    			
    			if(node != null)
    				push_back(j, node);
    		}
    		else if(cmd == 2) {
    			Node node = pop_back(i);
    			
    			if(node != null)
    				push_front(j, node);
    		}
    		else if(cmd == 3) {
    			move_all_front(i, j);
    		}
    		else {
    			move_all_back(i, j);
    		}
    	}
    	
    	for(int i = 1; i <= K; i++) {
    		StringBuilder sb = new StringBuilder();
    		int cnt = 0;
    		Node h = head[i];
    		if(h == null) System.out.println(cnt);
    		else {
    			while(h != null) {
    				cnt++;
    				sb.append(h.num + " ");
    				h = h.next;
    			}
    			System.out.println(cnt + " " + sb.toString());
    		}
    	}
    	
    }
    
    static boolean empty(int num) {
    	return head[num] == null;
    }
    
    static void connect(Node a, Node b) {
    	if(a != null) a.next = b;
    	if(b != null) b.prev = a;
    }
    
    static Node pop_front(int i) {
    	Node ret = head[i];
    	head[i] = head[i].next;
    	ret.next = null;
    	
    	if(head[i] != null)
    		head[i].prev = null;
    	else
    		tail[i] = null;
    	
    	return ret;
    }
    
    static Node pop_back(int i) {
    	Node ret = tail[i];
    	tail[i] = tail[i].prev;
    	ret.prev = null;
    	
    	if(tail[i] != null)
    		tail[i].next = null;
    	else
    		head[i] = null;
    	
    	return ret;
    }
    
    static void push_front(int i, Node node) {
    	if(head[i] == null)
    		head[i] = tail[i] = node;
    	else {
    		connect(node, head[i]);
    		head[i] = node;
    	}
    }
    
    static void push_back(int i, Node node) {
    	if(tail[i] == null)
    		head[i] = tail[i] = node;
    	else {
    		connect(tail[i], node);
    		tail[i] = node;
    	}
    }
    
    static void move_all_front(int i, int j) {
    	if(i == j || empty(i)) return;
    	
    	if(empty(j)) {
    		head[j] = head[i];
    		tail[j] = tail[j];
    	}
    	else {
    		connect(tail[i], head[j]);
    		head[j] = head[i];
    	}
    	
    	head[i] = tail[i] = null;
    }
    
    static void move_all_back(int i, int j) {
    	if(i == j || empty(i)) return;
    	
    	
    	if(empty(j)) {
    		head[j] = head[i];
    		tail[j] = tail[i];
    	}
    	else {
    		connect(tail[j], head[i]);
    		tail[j] = tail[i];
    	}
    	
    	head[i] = tail[i] = null;
    }
}