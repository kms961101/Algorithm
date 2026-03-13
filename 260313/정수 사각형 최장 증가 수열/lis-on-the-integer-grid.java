import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node>{
		int x, y, num;
		
		Node(int x, int y, int num){
			this.x = x;
			this.y = y;
			this.num = num;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.num - n.num;
		}
	}
	static int N;
	static int[][] map, dp;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static ArrayList<Node> list = new ArrayList<>();
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		list.add(new Node(i, j, map[i][j]));
        		dp[i][j] = 1;
        	}
        }
        
        Collections.sort(list);
        for(int i = 0; i < list.size(); i++) {
        	int x = list.get(i).x;
        	int y = list.get(i).y;
        	
        	for(int j = 0; j < 4; j++) {
        		int nx = x + dx[j];
        		int ny = y + dy[j];
        		if(isIn(nx, ny) && map[nx][ny] > map[x][y]) {
        			dp[nx][ny] = Math.max(dp[nx][ny], dp[x][y] + 1);
        		}
        	}
        }
        
        int ans = 0;
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		ans = Math.max(ans, dp[i][j]);
        	}
        }
        
        System.out.println(ans);
        
    }
	
	static boolean isIn(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}