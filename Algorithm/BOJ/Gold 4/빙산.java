import java.util.*;
import java.io.*;

public class Main{
    static int N, M;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int year = 0;
        while(true){
            year++;
            // 깊은 복사
            int[][] temp = copy();
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(map[i][j] == 0) continue;
                    find(i, j, temp);
                }
            }
            boolean[][] visited = new boolean[N][M];
            int cnt = 0;
            for(int i = 1; i < N; i++){
                for(int j = 1; j < M; j++){
                    if(visited[i][j] || map[i][j] == 0) continue;
                    dfs(i, j, visited);
                    cnt++;
                }
            }
            // 2블럭 넘으면 끝내기
            if(cnt > 1) break;
            else if(cnt == 0){
                year = 0;
                break;
            }
        }
        System.out.println(year);
    }
    
    // 연결된 블럭 찾기
    static void dfs(int x, int y, boolean[][] visited){
        visited[x][y] = true;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(isIn(nx, ny) || visited[nx][ny]) continue;
            if(map[nx][ny] != 0) dfs(nx, ny, visited);
        }
    }

    // 상하좌우 0인것 만큼 temp배열 기준으로 map배열 줄이기
    static void find(int x, int y, int[][] temp){
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(isIn(nx, ny)) continue;
            if(temp[nx][ny] == 0) map[x][y]--;
        }
        if(map[x][y] < 0) map[x][y] = 0;
    }

    static boolean isIn(int x, int y){
        if(x < 0 || y < 0 || x >= N || y >= M) return true;
        return false;
    }

    static int[][] copy(){
        int[][] temp = new int[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++) temp[i][j] = map[i][j];
        }

        return temp;
    }
}