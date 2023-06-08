import java.util.*;
import java.io.*;

public class Main {
	static class Pair{
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	// 빈칸 = 9, 붉은 폭탄 = 0, 돌 = -1, 나머지 = 1 ~ 10
	static final int Empty = 9;
	static int n, m, cnt, score;
	static int[][] bomb;
	static boolean[][] visited;
	static Pair benchmark;
	static ArrayList<Pair> redBomb;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		bomb = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				bomb[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			benchmark = findBomb(); // 폭탄 더미 찾기
			if(benchmark.x == -1) break; // 더이상 없으면 끝내기
			remove(benchmark.x, benchmark.y, bomb[benchmark.x][benchmark.y]); // 폭탄 제거
			gravity(); // 중력 작용
			rotate();  // 반시계 90도 회전
			gravity(); // 중력 작용
		}
		System.out.println(score);
	}
	
	static Pair findBomb() {
		visited = new boolean[n][n];
		int minD = 0; int minX = -1; int minY = -1; int redCnt = 100000;
		
		for(int i = n - 1; i >= 0; i--) {
			for(int j = 0; j < n; j++) {
				// 색깔 폭탄만 찾기
				if(bomb[i][j] == 0 || bomb[i][j] == -1 || bomb[i][j] == Empty || visited[i][j]) continue;
				cnt = 0;
				redBomb = new ArrayList<>();
				dfs(i, j, bomb[i][j]);
				// 폭탄은 2개 이상
				if(cnt < 2) continue;
				if(minD <= cnt) {
					// 폭탄이 더 많으면 갱신
					if(minD < cnt) {
						minD = cnt; minX = i; minY = j; redCnt = redBomb.size();
					} // 폭탄이 같으면 빨간 폭탄 적은 순
					else if(redBomb.size() < redCnt) {
						minD = cnt; minX = i; minY = j; redCnt = redBomb.size();
					}
				}
				// 빨간 폭탄은 방문처리 풀어주기
				if(redBomb.size() > 0) {
					for(Pair p : redBomb) visited[p.x][p.y] = false; 
				}
			}
		}
		if(minX != -1) score += minD * minD;
		return new Pair(minX, minY);
	}
	
	static void remove(int x, int y, int num) {
		bomb[x][y] = Empty;
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!isIn(nx, ny) || bomb[nx][ny] == Empty) continue;
			if(bomb[nx][ny] == 0 || bomb[nx][ny] == num) remove(nx, ny, num);
		}
	}
	
	static void gravity() {
		for(int i = 0; i < n; i++) {
			for(int j = n - 1; j >= 0; j--) {
				// 현재 칸이 검은돌, 폭탄이면 패스
				if(bomb[j][i] == -1 || bomb[j][i] != Empty) continue;
				for(int k = j - 1; k >= 0; k--) {
					// 다음 폭탄까지 찾기
					if(bomb[k][i] == Empty) continue;
					if(bomb[k][i] == -1) break;
					bomb[j][i] = bomb[k][i];
					bomb[k][i] = Empty;
					break;
				}
			}
		}
	}
	
	static void rotate() {
		int[][] temp = new int[n][n];
		
		for(int j = 0; j < n; j++) {
			for(int i = n - 1; i >= 0; i--) {
				temp[n - j - 1][i] = bomb[i][j];
			}
		}
		bomb = copy(temp);
	}
	
	static void dfs(int x, int y, int num) {
		cnt++;
		visited[x][y] = true;
		if(bomb[x][y] == 0) redBomb.add(new Pair(x, y));
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!isIn(nx, ny) || visited[nx][ny]) continue;
			if(bomb[nx][ny] == 0 || bomb[nx][ny] == num) dfs(nx, ny, num);
		}
	}
	
	static int[][] copy(int[][] copy){
		int[][] temp = new int[n][n];
		for(int i = 0; i < n; i++) temp[i] = copy[i].clone();
		return temp;
	}
	
	static boolean isIn(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}
}
