import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
		int x, v;
		
		Node(int x, int v){
			this.x = x;
			this.v = v;
		}
		
		@Override
		public int compareTo(Node n){
			return this.x - n.x;
		}
	}
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	for(int i = 0; i < N; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		int x1 = Integer.parseInt(st.nextToken());
    		int x2 = Integer.parseInt(st.nextToken());
    		
    		pq.add(new Node(x1, 1));
    		pq.add(new Node(x2, -1));
    		
    	}
    	int max = 0;
    	int sum = 0;
    	while(!pq.isEmpty()) {
    		Node now = pq.poll();
    		sum += now.v;
    		max = Math.max(max, sum);
    	}
    	
    	System.out.println(max);
    }
}