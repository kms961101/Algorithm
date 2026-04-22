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
			if(this.x == n.x) return this.v - n.v;
			return this.x - n.x;
		}
	}
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int N = Integer.parseInt(st.nextToken());
    	int K = Integer.parseInt(st.nextToken());
    	
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	int idx = 0;
    	for(int i = 1; i <= N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int num = Integer.parseInt(st.nextToken());
    		char dir = st.nextToken().charAt(0);
    		
    		if(dir == 'R') {
    			pq.add(new Node(idx, 1));
    			pq.add(new Node(idx + num, -1));
    			idx += num;
    		}
    		else {
    			pq.add(new Node(idx, -1));
    			pq.add(new Node(idx - num, 1));
    			idx -= num;
    		}
    	}
    	int ans = 0;
    	int cnt = 0;
    	int prev = 0;
    	boolean check = false;
    	while(!pq.isEmpty()) {
    		Node now = pq.poll();
    		
    		if(cnt >= K) {
    			ans += now.x - prev;
    		}
    		cnt += now.v;
    		prev = now.x;
    	}
    	
    	System.out.println(ans);
    }
}