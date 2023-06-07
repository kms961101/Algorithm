package jail;

import java.util.*;
import java.io.*;

public class Main {
	static class Attack{
		int d, cnt;
		
		public Attack(int d, int cnt) {
			this.d = d;
			this.cnt = cnt;
		}
	}
	
	static class Pair{
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
		
	}
	static int n, m, towerX, towerY, score;
	static int[][] miro;
	static Pair[][] nextMove;
	static Attack[] attack;
	// → ↓ ← ↑
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static ArrayList<Integer> monsterList;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		miro = new int[n][n];
		nextMove = new Pair[n][n];
		attack = new Attack[m];
		towerX = n / 2;
		towerY = n/ 2;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				miro[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			attack[i] = new Attack(d, cnt);
		}
		
		int cnt = 0;
		init(); // 초기 이동 방향 저장
		while(cnt != m) {
			delete(attack[cnt]);     // 1. 몬스터 없애기
			move();                  // 2. 빈 공간 채우기
			findInARow();            // 3. 연속되는 몬스터 제거
			add();                   // 4. 짝 맞춰서 넣기
			cnt++;
		}
		
		System.out.println(score);
	}
	
	static void init() {
		// 좌하우상 순
		int[] dx = {0, 1, 0, -1};
		int[] dy = {-1, 0, 1, 0};
		int moveCnt = 1; int moveD = 0;
		int x = towerX; int y = towerY;
		while(towerX > 0 || towerY > 0) {
			for(int i = 0; i < moveCnt; i++) {
				int nx = x + dx[moveD];
				int ny = y + dy[moveD];
				nextMove[x][y] = new Pair(nx, ny);
				x = nx; y = ny;
				if(x == 0 && y == 0) return;
			}
			
			moveD = (moveD + 1) % 4;
			// 다음 이동방향이 좌,우 둘중 하나면 1칸씩 증가
			if(moveD == 0 || moveD == 2) moveCnt++;
		}
	}
	
	
	static void delete(Attack a) {
		int x = towerX; int y = towerY;
		for(int i = 0; i < a.cnt; i++) {
			x += dx[a.d];
			y += dy[a.d];
			if(!isIn(x, y)) return;
			score += miro[x][y];
			miro[x][y] = 0;
		}
	}
	
	static void move() {
		monsterList = new ArrayList<>();
		
		int x = towerX; int y = towerY;
		while(x > 0 || y > 0) {
			Pair next = nextMove[x][y];
			if(miro[next.x][next.y] != 0) monsterList.add(miro[next.x][next.y]);
			x = next.x; y = next.y;
			if(x == 0 && y == 0) break;
		}
	}
	
	static void findInARow() {
		ArrayList<Integer> moveList;
		
		while(true) {
			int num = monsterList.get(0); int cnt = 1;
			moveList = new ArrayList<>();
			boolean flag = true;
			for(int i = 1; i < monsterList.size(); i++) {
				int now = monsterList.get(i);
				if(num == now) cnt++;
				else {
					// 4개보다 작으면 넣어주면 된다
					if(cnt < 4) {
						for(int j = 0; j < cnt; j++) moveList.add(num);
						num = now;
						cnt = 1;
					}else {
						// 연속된 개수 만큼 지우기
						for(int j = 0; j < cnt; j++) monsterList.remove(i - cnt);
						// 점수 더하기
						score += num * cnt;
						// 처음부터 다시 확인
						i = i - cnt - 1; 
						num = monsterList.get(i + 1);
						cnt = 0;
						flag = false;
					}
				}
			}
			
			// 마지막 숫자 넣어주기
			if(cnt < 4) for(int i = 0; i < cnt; i++) moveList.add(monsterList.get(monsterList.size() - 1));
			else score += num * cnt;
			monsterList = moveList;
			
			if(flag) break;
		}
	}
	
	static void add() {
		miro = new int[n][n];
		ArrayList<Integer> moveList = new ArrayList<>();
		int num = monsterList.get(0); int cnt = 1;
		for(int i = 1; i < monsterList.size(); i++) {
			int now = monsterList.get(i);
			if(now == num) cnt++;
			else {
				// 개수, 숫자 넣어주기
				moveList.add(cnt);
				moveList.add(num);
				num = now; cnt = 1;
			}
		}
		moveList.add(cnt);
		moveList.add(num);
		int x = towerX; int y = towerY;
		for(int i = 0; i < moveList.size(); i++) {
			num = moveList.get(i);
			Pair next = nextMove[x][y];
			miro[next.x][next.y] = num;
			x = next.x; y = next.y;
			if(x == 0 && y == 0) return;
		}
		
	}
	
	static boolean isIn(int x, int y) {
		return 0 <= n && x < n && 0 <= y && y < n;
	}
	
}
