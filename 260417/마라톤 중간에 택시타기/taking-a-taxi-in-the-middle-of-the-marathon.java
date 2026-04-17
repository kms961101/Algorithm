import java.io.*;
import java.util.*;

public class Main {
    static class Node{
		int x, y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	StringTokenizer st = null;
    	
    	Node[] node = new Node[N + 1];
    	int[] distL = new int[N + 1];
    	int[] distR = new int[N + 1];
    	for(int i = 1; i <= N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int x = Integer.parseInt(st.nextToken());
    		int y = Integer.parseInt(st.nextToken());
    		node[i] = new Node(x, y);
    	}
    	
    	for(int i = 2; i < N; i++) {
    		distL[i] = distL[i - 1] + Math.abs(node[i].x - node[i - 1].x) + Math.abs(node[i].y - node[i - 1].y);
    	}
    	
    	for(int i = N - 1; i > 0; i--) {
    		distR[i] = distR[i + 1] + Math.abs(node[i + 1].x - node[i].x) + Math.abs(node[i + 1].y - node[i].y);
    	}
    	int min = Integer.MAX_VALUE;
    	for(int i = 2; i < N; i++) {
    		Node prev = node[i - 1];
    		Node next = node[i + 1];
    		
    		int diff = Math.abs(prev.x - next.x) + Math.abs(prev.y - next.y);
    		min = Math.min(min, distL[i - 1] + distR[i + 1] + diff);
    	}
    	System.out.println(min);
    }
}