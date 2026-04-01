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
	static int n, m;
	static Node[] node;
	static int[] indegree;
	static Queue<Integer> q = new LinkedList<>();
	static boolean[] visited;
	static ArrayList<Integer>[] edge;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        node = new Node[m + 1];
        indegree = new int[n + 1];
        visited = new boolean[n + 1];
        edge = new ArrayList[n + 1];
        for(int i = 1; i <= m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	node[i] = new Node(x, y);
        }
        
        int start = 0;
        int end = m;
        int ans = 0;
        while(start <= end) {
        	int mid = (start + end) / 2;
        	
        	if(isCorrect(mid)) {
        		end = mid - 1;
        		ans = mid;
        	}
        	else {
        		start = mid + 1;
        	}
        }
        if(ans == 0) {
        	System.out.println("Consistent");
        }
        else {
        	System.out.println(ans);
        }
    }
    
    static boolean isCorrect(int mid) {
    	for(int i = 0; i <= n; i++) {
    		indegree[i] = 0;
    		visited[i] = false;
    		edge[i] = new ArrayList<>();
    	}
    	
    	for(int i = 1; i <= mid; i++) {
    		Node now = node[i];
    		edge[now.x].add(now.y);
    		indegree[now.y]++;
    	}
    	
    	for(int i = 1; i <= n; i++) {
    		if(indegree[i] == 0) q.add(i);
    	}
    	
    	while(!q.isEmpty()) {
    		int idx = q.poll();
    		visited[idx] = true;
    		
    		for(int i = 0; i < edge[idx].size(); i++) {
    			int next = edge[idx].get(i);
    			if(visited[next]) continue;
    			
    			indegree[next]--;
    			
    			if(indegree[next] == 0) q.add(next);
    		}
    	}
    	boolean flag = false;
    	for(int i = 1; i <= n; i++) {
    		if(!visited[i]) flag = true;
    	}
    	return flag;
    }
}