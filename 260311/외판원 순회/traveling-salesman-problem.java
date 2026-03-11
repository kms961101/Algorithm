import java.util.*;
import java.io.*;

public class Main {
    static int n, min = 987654321;
	static int[][] map;
	static boolean[] visited;
	static ArrayList<Integer> picked = new ArrayList<>();
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n];
        
        for(int i = 0; i < n; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < n; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        visited[0] = true;
        picked.add(0);
        find(1);
        System.out.println(min);
    }
	
	static void find(int cnt) {
		if(cnt == n) {
			int totalCost = 0;
			for(int i = 0; i < picked.size() - 1; i++) {
				int cost = map[picked.get(i)][picked.get(i + 1)];
				// 0 이면 못가므로 패스
				if(cost == 0) return;
				totalCost += cost;
			}
			
			// 마지막 번호에서 0번으로 다시 가는 최소 비용
			int lastIdx = picked.get(picked.size() - 1);
			int nextCost = map[lastIdx][0];
			// 마지막 인덱스에서 0번으로 못가면 패스
			if(nextCost == 0) return;
			
			min = Math.min(min, totalCost + nextCost);
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			picked.add(i);
			find(cnt + 1);
			picked.remove(picked.size() - 1);
			visited[i] = false;
		}
	}
}