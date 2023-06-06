import java.util.*;
import java.io.*;

public class Main {
	static class Monster{
		int x, y, d;
		boolean isAlive;
		
		public Monster(int x, int y, int d, boolean isAlive) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.isAlive = isAlive;
		}
	}
	static int m, t, r, c, maxCatch, d1, d2, d3;
	static int[][] map = new int[4][4];
	static int[][] gost = new int[4][4];
	static ArrayList<Monster> monster = new ArrayList<>();
	static ArrayList<Monster> copyMonster;
	// ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] ax = {-1, 0, 1, 0};
	static int[] ay = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			monster.add(new Monster(x, y, d, true));
			map[x][y]++;
		}
		
		while(t-- > 0) {
			copyMonster = new ArrayList<>();
			// 1. 몬스터 복제
			for(int i = 0; i < monster.size(); i++) {
				Monster m = monster.get(i);
				copyMonster.add(new Monster(m.x, m.y, m.d, m.isAlive));
			}
			// 2. 몬스터 이동
			moveAll();
			// 3. 팩맨 이동
			movePacman();
			// 4. 시체 소멸
			remove();
			// 5. 알 부화
			hatching();
		}
		
		int score = 0;
		// 살아남은 몬스터 수
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				score += map[i][j];
			}
		}
		
		System.out.println(score);
	}
	
	static void moveAll() {
		for(int i = 0; i < monster.size(); i++) {
			Monster m = monster.get(i);
			int d = m.d;
			// 8방향 찾기
			for(int j = 0; j < 8; j++) {
				int nx = m.x + dx[d];
				int ny = m.y + dy[d];
				// 범위를 벗어나고, 몬스터가 있거나, 유령이 있으면 패스
				if(!isIn(nx, ny) || (nx == r && ny == c) || gost[nx][ny] > 0) {
					// 반시계 45도 회전
					d = (d + 1) % 8;
					continue;
				}
				map[m.x][m.y]--;
				m.x = nx; m.y = ny; m.d = d;
				map[m.x][m.y]++;
				break;
			}
		}
	}
	
	static void movePacman() {
		// 총 3칸 움직이는데 상좌하우 우선순위로 이동
		maxCatch = 0; d1 = -1; d2 = -1; d3 = -1;
		// 이동할 방향 찾기
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				for(int k = 0; k < 4; k++) {
					threeMovePacman(i, j, k);
				}
			}
		}
		
		// 한마리도 못잡을때 가장 우선순위 높은곳으로 이동
		if(d1 == -1 && d2 == -1 && d3 == -1) {
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					for(int k = 0; k < 4; k++) {
						int nx = r + ax[i]; int ny = c + ay[i];
						if(isIn(nx, ny) && isIn(nx + ax[j], ny + ay[j]) 
								&& isIn(nx + ax[j] + ax[k], ny + ay[j] + ay[k])) {
							d1 = i; d2 = j; d3 = k;
							subMove();
							return;
						}
					}
				}
			}
		}
		
		// 찾음 방향으로 이동
		subMove();
	}
	
	static void remove() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				gost[i][j] = Math.max(0, gost[i][j] - 1);
			}
		}
	}
	
	static void hatching() {
		ArrayList<Monster> temp = new ArrayList<>();
		
		// 기존 살아있는 몬스터 저장
		for(int i = 0; i < monster.size(); i++) {
			Monster m = monster.get(i);
			if(!m.isAlive) continue;
			temp.add(m);
		}
		
		// 알 부화
		for(int i = 0; i < copyMonster.size(); i++) {
			Monster m = copyMonster.get(i);
			map[m.x][m.y]++;
			temp.add(m);
		}
		
		monster = temp;
	}
	
	static boolean isIn(int x, int y) {
		if(x < 0 || y < 0 || x >= 4 || y >= 4) return false;
		return true;
	}
	
	static void threeMovePacman(int D1, int D2, int D3) {
		int find = 0;
		boolean[][] visited = new boolean[4][4];
		int nx = r + ax[D1];
		int ny = c + ay[D1];
		if(!isIn(nx, ny)) return;
		visited[nx][ny] = true;
		find += map[nx][ny];
		
		nx += ax[D2];
		ny += ay[D2];
		if(!isIn(nx, ny)) return;
		if(!visited[nx][ny]) find += map[nx][ny];
		visited[nx][ny] = true;
		
		nx += ax[D3];
		ny += ay[D3];
		if(!isIn(nx, ny)) return;
		if(!visited[nx][ny]) find += map[nx][ny];
		
		if(maxCatch < find) {
			maxCatch = find; d1 = D1; d2 = D2; d3 = D3;
		}
	}
	
	static void subMove() {
		r += ax[d1];
		c += ay[d1];
		if(map[r][c] > 0) removeMonster(r, c, d1);
		
		r += ax[d2];
		c += ay[d2];
		if(map[r][c] > 0) removeMonster(r, c, d2);
		
		r += ax[d3];
		c += ay[d3];
		if(map[r][c] > 0) removeMonster(r, c, d3);

	}
	
	static void removeMonster(int x, int y, int d) {
		// 몬스터 잡고, 귀신 만들기
		map[x][y] = 0;
		gost[x][y] = 3;
		// 몬스터 죽은거 표시
		for(int i = 0; i < monster.size(); i++) {
			Monster m = monster.get(i);
			if(m.x == x && m.y == y) m.isAlive = false;
		}
	}
	
}
