import java.util.*;
import java.io.*;

public class Main{
    static class Pair{
        int floor, x, y;

        public Pair(int floor, int x, int y){
            this.floor = floor;
            this.x = x;
            this.y = y;
        }
    }
    static int L, R, C;
    static Pair start, end;
    static char[][][] bilding;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if(L == 0 && R == 0 && C == 0) break;
            bilding = new char[L][R][C];

            for(int i = L - 1; i >= 0; i--){
                for(int j = 0; j < R; j++){
                    String line = br.readLine();
                    if(line.equals("")) line = br.readLine();
                    for(int k = 0; k < C; k++){
                        bilding[i][j][k] = line.charAt(k);
                        if(bilding[i][j][k] == 'S'){
                            start = new Pair(i, j, k);
                        }else if(bilding[i][j][k] == 'E'){
                            end = new Pair(i, j, k);
                        }
                    }
                }
            }
            int time = bfs();
            if(time == 1) System.out.println("Trapped!");
            else{
                System.out.println("Escaped in " + time + " minute(s).");
            }
            br.readLine();
        }
    }

    static int bfs(){
        Queue<Pair> q = new LinkedList<>();
        boolean[][][] visited = new boolean[L][R][C];
        q.add(start);
        visited[start.floor][start.x][start.y] = true;
        int cnt = 0;
        while(!q.isEmpty()){
            int size = q.size();
            while(size-- > 0){
                Pair now = q.poll();
                if(now.floor == end.floor && now.x == end.x && now.y == end.y) return cnt;
                // 상하좌우 탐색
                for(int i = 0; i < 4; i++){
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];
                    // 범위를 넘어가거나, 방문 했거나, 금이면 못감
                    if(!isIn(nx, ny) || visited[now.floor][nx][ny] || bilding[now.floor][nx][ny] == '#') continue;
                    q.add(new Pair(now.floor, nx, ny));
                    visited[now.floor][nx][ny] = true;
                }

                // 위에층 아래층 확인
                if(now.floor + 1 != L && !visited[now.floor + 1][now.x][now.y] && bilding[now.floor + 1][now.x][now.y] != '#'){
                    q.add(new Pair(now.floor + 1, now.x, now.y));
                    visited[now.floor + 1][now.x][now.y] = true;
                }

                if(now.floor - 1 != -1 && !visited[now.floor - 1][now.x][now.y] && bilding[now.floor - 1][now.x][now.y] != '#'){
                    q.add(new Pair(now.floor - 1, now.x, now.y));
                    visited[now.floor - 1][now.x][now.y] = true;
                }
            }
            cnt++;
        }
        return cnt;
    }

    static boolean isIn(int x, int y){
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}