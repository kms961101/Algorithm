import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] list;
	static ArrayList<Integer> ans = new ArrayList<>();
	static Queue<Integer> q = new LinkedList<>();
	static int n, m1, m2;
	static int[] inDegree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m1 = Integer.parseInt(st.nextToken());
        m2 = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[n + 1];
        inDegree = new int[n + 1];
        for(int i = 0; i <= n; i++) list[i] = new ArrayList<>();
        
        for(int i = 0; i < m1; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	list[from].add(to);
        	inDegree[to]++;
        }
        
        for(int i = 0; i < m2; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        }
        
        boolean[] visited = new boolean[n + 1];
        for(int i = 1; i <= n; i++) {
        	if(inDegree[i] == 0) {
        		q.add(i);
        	}
        }
        
        while(!q.isEmpty()) {
        	int now = q.poll();
        	visited[now] = true;
        	for(int i = 0; i < list[now].size(); i++) {
        		int next = list[now].get(i);
        		if(visited[next]) continue;
        		
        		inDegree[next]--;
        		
        		if(inDegree[next] == 0) {
        			q.add(next);
        		}
        	}
        }
        boolean ans = true;
        for(int i = 1; i <= n; i++) {
        	if(!visited[i]) ans = false;
        }
        
        System.out.println(ans ? "Yes" : "No");
	}
}