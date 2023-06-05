import java.util.*;
import java.io.*;

public class Main {
	static class Fugitive{
		int x, y, d;
		boolean isAlive;
		
		public Fugitive(int x, int y, int d, boolean isAlive) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.isAlive = isAlive;
		}
	}
	
	static class Pair{
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int n, m, h, k, turn, cnt, score;
	static int[][] seekerNextDir, seekerRevDir;
	static boolean[][] isTree;
	// 도망자들 저장
	static ArrayList<Fugitive> fugitive = new ArrayList<>();
	// 술래
	static Pair seeker;
	static boolean isReverse = false;
	// 상우하좌
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		isTree = new boolean[n][n];
		seekerNextDir = new int[n][n];
		seekerRevDir = new int[n][n];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			fugitive.add(new Fugitive(x, y, d, true));
		}
		
		for(int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			isTree[x][y] = true;
		}
		
		seeker = new Pair(n / 2, n / 2);
		init();
		while(k-- > 0) {
			turn++;
			moveAll(); 		// 도망자 모두 이동
			moveSeeker();   // 술래 이동
			find(); 		// 술래가 도망자 찾기
		}
		
		System.out.println(score);
	}
	
	static void init() {
		// 시작 위치, 방향, 이동횟수
		int nowX = n / 2, nowY = n / 2;
		int moveDir = 0, moveNum = 1;
		
		while(nowX > 0 || nowY > 0) {
			for(int i = 0; i < moveNum; i++) {
				// 정방향
				seekerNextDir[nowX][nowY] = moveDir;
				nowX += dx[moveDir];
				nowY += dy[moveDir];
				// 역방향은 이동 후 방향만 바꾸기
				seekerRevDir[nowX][nowY] = moveDir ^ 2;
				if(nowX == 0 && nowY == 0) break;
			}
			
			// 방향 바꾸기
			moveDir = (moveDir + 1) % 4;
			// 방향이 위 아래이면 이동 횟수 1증가
			if(moveDir == 0 || moveDir == 2) moveNum++;
		}
		
	}
	
	static void moveAll() {
		// 우하좌상
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		
		for(int i = 0; i < m; i++) {
			Fugitive f = fugitive.get(i);
			if(!f.isAlive) continue;
			// 거리가 3 이하인 도망자만 움직일 수 있다.
			int dist = Math.abs(f.x - seeker.x) + Math.abs(f.y - seeker.y);
			if(dist > 3) continue;
			int nx = f.x + dx[f.d];
			int ny = f.y + dy[f.d];
			// 범위가 넘어가면 방향 바꿔서 다시 움직이기
			if(isIn(nx, ny)) {
				f.d = f.d ^ 2;
				nx = f.x + dx[f.d];
				ny = f.y + dy[f.d];
			}
			if(seeker.x == nx && seeker.y == ny) continue;
			f.x = nx; f.y = ny;
		}
	}
	
	static void moveSeeker() {
		int dir = 0;
		// 정방향
		if(!isReverse) dir = seekerNextDir[seeker.x][seeker.y];
		// 반대방향
		else dir = seekerRevDir[seeker.x][seeker.y];

		seeker.x += dx[dir]; seeker.y += dy[dir];
		if(seeker.x == 0 && seeker.y == 0) isReverse = true;
		if(seeker.x == n / 2 && seeker.y == n / 2) isReverse = false;
	}
	
	static void find() {
		cnt = 0;
		// 술래가 바라보는 방향
		int dir = 0;
		if(!isReverse) dir = seekerNextDir[seeker.x][seeker.y];
		// 반대방향
		else dir = seekerRevDir[seeker.x][seeker.y];
		int x = seeker.x;
		int y = seeker.y;
		// 세곳 체크
		for(int i = 0; i < 3; i++) {
			int nx = x + i * dx[dir];
			int ny = y + i * dy[dir];
			if(isIn(nx, ny) || isTree[nx][ny]) continue;
			findCatch(nx, ny);
		}
		score += turn * cnt;
	}
	
	static void findCatch(int x, int y) {
		// 한 좌표에 여러 도망자가 있을 수 있다.
		for(int i = 0; i < m; i++) {
			Fugitive f = fugitive.get(i);
			if(!f.isAlive) continue;
			if(f.x == x && f.y == y) {
				f.isAlive = false;
				cnt++;
			}
		}
	}
	
	static boolean isIn(int x, int y) {
		if(x < 0 || y < 0 || x >= n || y >= n) return true;
		return false;
	}
}
