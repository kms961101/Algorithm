import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] list;
	static ArrayList<Integer> ans = new ArrayList<>();
	static Queue<Integer> q = new LinkedList<>();
	static int N, M;
	static int[] inDegree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[N + 1];
        inDegree = new int[N + 1];
        for(int i = 0; i <= N; i++) list[i] = new ArrayList<>();
        
        while(M-- > 0) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int k = Integer.parseInt(st.nextToken());
        	
        	st = new StringTokenizer(br.readLine());
        	while(k-- > 0) {
        		int idx = Integer.parseInt(st.nextToken());
        		list[idx].add(a);
        		inDegree[a]++;
        	}
        }
        
        int cnt = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        boolean[] visited = new boolean[N + 1];
        for(int i = 0; i < cnt; i++) {
        	int num = Integer.parseInt(st.nextToken());
        	q.add(num);
        	visited[num] = true;
        }
        
        while(!q.isEmpty()) {
        	int now = q.poll();
        	ans.add(now);
        	
        	for(int i = 0; i < list[now].size(); i++) {
        		int next = list[now].get(i);
        		if(visited[next]) continue;
        		
        		inDegree[next]--;
        		
        		if(inDegree[next] == 0) {
        			q.add(next);
        			visited[next] = true;
        		}
        	}
        }
        
        Collections.sort(ans);
        System.out.println(ans.size());
        for(int i = 0; i < ans.size(); i++) {
        	System.out.print(ans.get(i) + " ");
        }
	}
}