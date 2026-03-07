import java.io.*;
import java.util.*;

public class Main {
    static class Node{
		int x, y, dir;
		
		Node(int x, int y, int dir){
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	static int n, ans = 1;
	static char[][] map;
	// 이동 방향
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	// 이동 방향 기준 오른쪽 벽 위치
	static int[] rx = {1, 0, -1, 0};
	static int[] ry = {0, -1, 0, 1};
	static Node now;
	static boolean isEnd = false;
	static boolean[][][] visited;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        map = new char[n][n];
        visited = new boolean[n][n][4];
        for(int i = 0; i < n; i++) {
        	map[i] = br.readLine().toCharArray();
        }
        now = new Node(x, y, 0);
        while(true) {
        	visited[now.x][now.y][now.dir] = true;
        	// Step 1 -  바라 보는 방향으로 이동 못 하는 경우
        	now = canMove(now.x, now.y, now.dir);
        	// 갈 수 없으면 끝내기
        	if(now.dir == -1) {
        		if(!isEnd) ans = -1;
        		break;
        	}
        	ans++;
        	// Step 2 - 간 곳에 오른쪽 벽이 없으면 시계 방향으로 회전 후 한칸 전진
        	int rightX = now.x + rx[now.dir];
        	int rightY = now.y + ry[now.dir];
        	if(map[rightX][rightY] != '#') {
        		visited[now.x][now.y][now.dir] = true;
        		int dir = (now.dir + 1) % 4; 
        		now.x += dx[dir];
        		now.y += dy[dir];
        		now.dir = dir;
        		ans++;
        	}
        	
        }
        
        System.out.println(ans);
    }
	
	static Node canMove(int x, int y, int dir) {
		Node next = new Node(x, y, -1);
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			// 진행 방향이 나가는 길이고, 오른쪽에 벽이 있는지?
			if(!isIn(nx, ny) && map[x + rx[dir]][y + ry[dir]] == '#') {
				isEnd = true;
				return new Node(x, y, -1);
			}
			// 벽이 있으면 반시계 회전
			if(isIn(nx, ny) && map[nx][ny] == '#') {
				dir = dir - 1 == -1 ? 3 : dir - 1;
			}
			else {
				if(visited[nx][ny][dir]) continue;
				next = new Node(nx, ny, dir);
				break;
			}
		}
		return next;
	}
	
	static boolean isIn(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}
}