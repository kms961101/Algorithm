import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
		int x, y, v, idx;
		
		Node(int x, int y, int v, int idx){
			this.x = x;
			this.y = y;
			this.v = v;
			this.idx = idx;
		}
		
		@Override
		public int compareTo(Node n){
			if(this.x == n.x) return this.v - n.v;
			return this.x - n.x;
		}
	}
	
	static class Element implements Comparable<Element>{
		int y, idx;
		
		Element(int y, int idx){
			this.y = y;
			this.idx = idx;
		}
		
		@Override
		public int compareTo(Element e){
			return this.y - e.y;
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
    		node.add(new Node(x1, y, 1, i));
    		node.add(new Node(x2, y, -1, i));
    	}
    	
    	TreeSet<Element> colors = new TreeSet<>();
    	boolean[] visited = new boolean[50000];
    	while(!node.isEmpty()) {
    		Node now = node.poll();
    		
    		if(now.v == 1) {
    			colors.add(new Element(now.y, now.idx));
    		}
    		else {
    			colors.remove(new Element(now.y, now.idx));
    		}
    		
    		if(colors.isEmpty()) continue;
    		
    		visited[colors.first().idx] = true;
    	}
    	int ans = 0;
    	for(int i = 0; i <= 2 * N; i++) {
    		if(visited[i]) ans++;
    	}
    	
    	System.out.println(ans);
    }
}