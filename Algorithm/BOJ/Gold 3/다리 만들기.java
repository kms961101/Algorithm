import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, ans = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visited;
	static boolean[] check;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		check = new boolean[10002]; 
		int idx = 1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					dfs(i, j, idx);
					idx++;
				}
			}
		}
		
		for (int i = 1; i <= idx; i++) {
			if(check[i]) continue;
			bfs(i);
		}
		System.out.println(ans);
	}

	private static void bfs(int idx) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] zeroVisited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == idx) {
					if((j - 1 >= 0 && map[i][j - 1] == 0) || (j + 1 < N && map[i][j + 1] == 0)) q.add(new int[] {i, j});
				}
			}
		}
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				int[] t = q.poll();
				int x = t[0];
				int y = t[1];
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(nx < 0 || ny < 0 || nx >= N || ny >= N || zeroVisited[nx][ny]) continue;
					if(map[nx][ny] != idx && map[nx][ny] != 0) {
						check[map[nx][ny]] = true;
						ans = Math.min(ans, cnt);
						return;
					}
					zeroVisited[nx][ny] = true;
					q.add(new int[] {nx, ny});
				}
				
			}
			cnt++;
		}
	}

	private static void dfs(int x, int y, int idx) {
		visited[x][y] = true;
		map[x][y] = idx;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] == 0) continue;
			dfs(nx, ny, idx);
		}
	}
}