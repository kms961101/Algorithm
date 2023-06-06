import java.util.*;
import java.io.*;

// 벽 = -12, 제초제 = -11 ~ -1, 나무 = 양수
public class Main {
	static int n, m, k, c, score, answer;
	static int[][] tree;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int[] ax = {-1, 1, -1, 1};
	static int[] ay = {-1, -1, 1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		tree = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				tree[i][j] = Integer.parseInt(st.nextToken());
				if(tree[i][j] == -1) tree[i][j] = -12;
			}
		}
		
		while(m-- > 0) {
			grow();        // 1. 나무 성장
			breeding();    // 2. 번식
			kill();        // 3. 제초제 뿌리기
		}
		
		System.out.println(answer);
	}
	
	static void grow() { // 1. 나무 성장
		int[][] copy = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				copy[i][j] = tree[i][j];
				if(tree[i][j] <= 0) continue;
				for(int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(!isIn(nx, ny)) continue;
					if(tree[nx][ny] > 0) copy[i][j]++;
				}
			}
		}
		
		tree = copy(copy);
	}
	
	static void breeding() { // 2. 번식
		int[][] copy = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(copy[i][j] == 0) copy[i][j] = tree[i][j];
				if(tree[i][j] <= 0) continue;
				int cnt = 0;
				// 주변 빈 나무 개수 세기
				for(int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(!isIn(nx, ny)) continue;
					if(tree[nx][ny] == 0) cnt++;
				}
				// 번식
				for(int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(!isIn(nx, ny) || tree[nx][ny] != 0) continue;
					copy[nx][ny] += tree[i][j] / cnt;
				}
			}
		}
		
		tree = copy(copy);
	}
	
	static void kill() { // 3. 제초제 뿌리기
		// 제초제 제거
		remove();
		
		// 제초제 뿌릴 위치 찾기
		int maxTree = 0; int maxX = -1; int maxY = -1;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(tree[i][j] <= 0) continue;
				score = tree[i][j];
				for(int k = 0; k < 4; k++) subMove(i, j, k, 0);
				if(maxTree < score) {
					maxTree = score; maxX = i; maxY = j;
				}
			}
		}
		// 뿌릴곳 없으면 패스
		if(maxX == -1 && maxY == -1) return;
		tree[maxX][maxY] = -c;
		answer += maxTree;
		// 제초제 뿌리기
		for(int i = 0; i < 4; i++) spread(maxX, maxY, i, 0);
		
	}

	static void subMove(int x, int y, int d, int num) {
		int nx = x + ax[d];
		int ny = y + ay[d];
		if(!isIn(nx, ny) || tree[nx][ny] <= 0) return;
		score += tree[nx][ny];
		if(num + 1 != k) subMove(nx, ny, d, num + 1);
	}
	
	static void spread(int x, int y, int d, int num) {
		int nx = x + ax[d];
		int ny = y + ay[d];
		// 빈곳이거나, 벽이면 못뿌림
		if(!isIn(nx, ny) || tree[nx][ny] == -12) return;
		// 가는 곳이 제초제 거나, 빈곳일 경우 해당 칸에만 제초제 뿌리고 확산 x
		if(-12 < tree[nx][ny] && tree[nx][ny] <= 0){
			tree[nx][ny] = -c;
			return;
		}
		tree[nx][ny] = -c;
		if(num + 1 != k) spread(nx, ny, d, num + 1);
	}
	
	static void remove() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(tree[i][j] >= 0 || tree[i][j] == -12) continue;
				tree[i][j]++;
			}
		}
	}
	
	static boolean isIn(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}
	
	static int[][] copy(int[][] temp){
		int[][] copy = new int[n][n];
		
		for(int i = 0; i < n; i++) copy[i] = temp[i].clone();
		return copy;
	}
}
