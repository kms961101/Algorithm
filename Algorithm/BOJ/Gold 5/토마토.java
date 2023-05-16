import java.util.*;
import java.io.*;

public class Main{
    static int M, N, H, cnt, tomato;
    static int[][][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<int[]> q;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][N][M];
        q = new LinkedList<>();
        visited = new boolean[H][N][M];

        for(int t = 0; t < H; t++){
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M; j++){
                    map[t][i][j] = Integer.parseInt(st.nextToken());
                    // 총 익어야할 토마토 개수
                    if(map[t][i][j] != -1) cnt++;
                }
            }
        }
        // 전부다 익었으면 0 리턴
        if(cnt == 0){
            System.out.println(0);
            System.exit(0);
        }
        // 모든 익은 토마토가 한번에 진행되야 되므로 q에 넣기
        for(int t = 0; t < H; t++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(map[t][i][j] == 1){
                        q.add(new int[] {i, j, t});
                    }
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs(){
        int answer = 0;
        while(!q.isEmpty()){
            int size = q.size();

            while(size-- > 0){
                int[] node = q.poll();
                int x = node[0];
                int y = node[1];
                int floor = node[2];
                visited[floor][x][y] = true;
                // 다 익었으면 종료
                if(++tomato == cnt) return answer;
                for(int i = 0; i < 4; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    // 범위 초과, 익은 토마토, 벽, 방문한곳 이면 패스
                    if(isIn(nx, ny) || map[floor][nx][ny] == 1 || map[floor][nx][ny] == -1 || visited[floor][nx][ny]) continue;
                    map[floor][nx][ny] = 1;
                    q.add(new int[] {nx, ny, floor});
                }
                // 위에층 가능한지 체크
                if(floor + 1 != H && map[floor + 1][x][y] == 0 && !visited[floor + 1][x][y]){
                    q.add(new int[] {node[0], node[1], floor + 1});
                    map[floor + 1][x][y] = 1;
                }
                // 아래층 가능한지 체크
                if(floor - 1 != -1 && map[floor - 1][x][y] == 0 && !visited[floor - 1][x][y]){
                    q.add(new int[] {node[0], node[1], floor - 1});
                    map[floor - 1][x][y] = 1;
                }
            }
            answer++;
        }
        return -1;
    }

    static boolean isIn(int x, int y){
        if(x < 0 || y < 0 || x >= N || y >= M) return true;
        return false;
    }
}