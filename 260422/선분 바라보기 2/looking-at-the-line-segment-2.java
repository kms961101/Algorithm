import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
		int x, y, v;
		
		Node(int x, int y, int v){
			this.x = x;
			this.y = y;
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
    	int N = Integer.parseInt(br.readLine());
    	StringTokenizer st = null;
    	
    	PriorityQueue<Node> node = new PriorityQueue<>();
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int y = Integer.parseInt(st.nextToken());
    		int x1 = Integer.parseInt(st.nextToken());
    		int x2 = Integer.parseInt(st.nextToken());
    		node.add(new Node(x1, y, 1));
    		node.add(new Node(x2, y, -1));
    	}
    	
    	TreeSet<Integer> colors = new TreeSet<>();
    	int ans = 1;
    	Node first = node.poll();
    	int prevColor = first.y;
    	colors.add(first.y);
    	int cnt = 1;
    	
    	while(!node.isEmpty()) {
    		Node now = node.poll();
    		
    		cnt += now.v;
    		if(now.v == 1) colors.add(now.y);
    		
    		if(cnt >= 1 && colors.first() > now.y) {
    			prevColor = now.y;
    		}
    		else if(now.v == -1) {
    			colors.remove(now.y);
    			if(cnt >= 1 && prevColor != colors.first()) {
    				ans++;
    				prevColor = colors.first();
    			}
    		}
    		
    		
    	}
    	
    	System.out.println(ans);
    }
}