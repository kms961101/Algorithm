import java.util.*;
import java.io.*;

public class Main {
    static int n, people;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int group = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					people = 0;
					dfs(i, j);
					group++;
					pq.add(people);
				}
			}
		}
		System.out.println(group);
		while(!pq.isEmpty()) System.out.println(pq.poll());
	}
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		people++;
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1) {
				dfs(nx, ny);
			}
		}
	}
	
	static boolean isIn(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}
}