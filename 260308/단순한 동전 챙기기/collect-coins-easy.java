import java.util.*;
import java.io.*;

public class Main {
    static class Node{
		int x, y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static boolean[] selected, visited;
	static char[][] map;
	static int n, answer = Integer.MAX_VALUE;
	static Node[] number = new Node[21];
	static ArrayList<Node> list = new ArrayList<>();
	static Node start, end;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        for(int i = 0; i < n; i++) map[i] = br.readLine().toCharArray();
        boolean[] checkNum = new boolean[21];
        for(int i = 0; i < n; i++) {
        	for(int j = 0; j < n; j++) {
        		if(map[i][j] == 'S') {
        			start = new Node(i, j);
        		}
        		else if(map[i][j] == 'E') {
        			end = new Node(i, j);
        		}
        		else if(map[i][j] != '.') {
        			int num = map[i][j] - '0';
        			number[num] = new Node(i, j);
        			checkNum[num] = true;
        		}
        	}
        }
        list.add(new Node(0, 0));
        for(int i = 0; i < 21; i++) {
        	if(checkNum[i]) list.add(new Node(number[i].x, number[i].y));
        }
        if(list.size() <= 3) {
        	System.out.println(-1);
        	System.exit(0);
        }
        visited = new boolean[list.size()];
        comb(1, 3);
        System.out.println(answer);
	}
	
	static void comb(int idx, int r) {
		if(r == 0) {
			int cnt = 1;
			int min = 0;
			Node prev = null;
			for(int i = 0; i <= n; i++) {
				if(!visited[i]) continue;
				Node now = list.get(i);
				if(cnt == 1) {
					min += Math.abs(start.x - now.x) + Math.abs(start.y - now.y);
				}
				else {
					min += Math.abs(prev.x - now.x) + Math.abs(prev.y - now.y);
				}
				prev = now;
				cnt++;
			}
			min += Math.abs(end.x - prev.x) + Math.abs(end.y - prev.y);
			answer = Math.min(min, answer);
			
		}
		
		
		for(int i = idx; i <= n; i++) {
			visited[i] = true;
			comb(i + 1, r - 1);
			visited[i] = false;
		}
	
	}
}