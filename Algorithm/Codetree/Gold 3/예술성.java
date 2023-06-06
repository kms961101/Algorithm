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
	static int n, groupNum, score, center;
	static int[] groupCnt;
	static Pair[] groupStart;
	static int[][] map, group, copy;
	static boolean[][] visited;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		center = n / 2;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int N = 0;
		while(N++ != 4) {
			// 그룹 찾고 시작점 저장
			visited = new boolean[n][n];
			group = new int[n][n];
			groupStart = new Pair[n * n];
			groupNum = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(!visited[i][j]) {
						findGroup(i, j, map[i][j]);
						groupStart[groupNum] = new Pair(i, j);
						groupNum++;
					}
				}
			}
			// 각 그룹 개수 저장
			groupCnt = new int[groupNum];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					groupCnt[group[i][j]]++;
				}
			}
			
			// 두 그룹씩 조화로움 값 찾기
			for(int i = 0; i < groupNum; i++) {
				for(int j = i + 1; j < groupNum; j++) {
					harmony(i, j);
				}
			}
			// 십자모양
			rotation();
		}
		
		System.out.println(score);
	}
	
	static void findGroup(int x, int y, int num){
		// 방문처리, 그룹 번호 써주기
		visited[x][y] = true;
		group[x][y] = groupNum;
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(isIn(nx, ny) || visited[nx][ny] || map[nx][ny] != num) continue;
			findGroup(nx, ny, num);
		}
	}
	
	static void harmony(int a, int b) {
		Pair groupA = groupStart[a];
		Pair groupB = groupStart[b];
		int adjacent = findAdjacent(groupA, groupB); 
		// (그룹 a에 속한 칸의 수 + 그룹 b에 속한 칸의 수 ) x 그룹 a를 이루고 있는 숫자 값 x 그룹 b를 이루고 있는 숫자 값 x 그룹 a와 그룹 b가 서로 맞닿아 있는 변의 수
		int num = (groupCnt[a] + groupCnt[b]) * map[groupA.x][groupA.y] * map[groupB.x][groupB.y] * adjacent;
		if(num < 0) return;
		score += num;
	}
	
	static int findAdjacent(Pair groupA, Pair groupB) {
		// 인접한 변 찾기
		int adjacent = 0;
		int aNum = group[groupA.x][groupA.y];
		int bNum = group[groupB.x][groupB.y];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(group[i][j] != aNum) continue;
				for(int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(isIn(nx, ny) || group[nx][ny] != bNum) continue;
					adjacent++;
				}
			}
		}
		return adjacent;
	}
	
	static void rotation() {
		copy = new int[n][n];
		copy[center][center] = map[center][center];
		// 십자모양
		for(int i = 0; i < center; i++) {
			// 좌하우상
			copy[center][i] = map[i][center];
			copy[n - i - 1][center] = map[center][i];
			copy[center][n - i - 1] = map[n - i - 1][center];
			copy[i][center] = map[center][n - i - 1];
		}
		// 나머지 사각형 네개
		// 위왼쪽
		subRorate(0, 0);
		// 위오른쪽
		subRorate(0, center + 1);
		// 아래 왼쪽
		subRorate(center + 1, 0);
		// 아래 오른쪽
		subRorate(center + 1, center + 1);
		// map이랑 바꿔주기
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				map[i][j] = copy[i][j];
			}
		}
	}
	
	static void subRorate(int x, int y) {
		int[][] temp = new int[center][center];
		int[][] reverse = new int[center][center];
		// 돌리기 쉽게 0,0부터 시작하게 만들기
		for(int i = 0; i < center; i++) {
			for(int j = 0; j < center; j++) {
				temp[i][j] = map[x + i][y + j];
			}
		}
		// 회전
		for(int i = 0; i < center; i++) {
			for(int j = 0; j < center; j++) {
				reverse[j][center - i - 1] = temp[i][j];
			}
		}
		// 복사
		for(int i = 0; i < center; i++) {
			for(int j = 0; j < center; j++) {
				copy[x + i][y + j] = reverse[i][j];
			}
		}
	}
	
	static boolean isIn(int x, int y){
		if(x < 0 || y < 0 || x >= n || y >= n) return true;
		return false;
	}
}
