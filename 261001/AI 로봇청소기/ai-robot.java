import java.io.*;
import java.util.*;


public class Main {
    static class Robot{
        int x, y, id;
        
        public Robot(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }
    }
    
    
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int[] sx  = { 0, -1,  0,  1,  0}; // 청소 계산용(자기칸 포함)
    static int[] sy  = {-1,  0,  1,  0,  0};
    // 2. 청소할때 제외할 방향
    static int[] cleanDir = {1, 0, 2, 3};
    static int N, K, L;
    static int[][] map;
    static ArrayList<Robot> robots = new ArrayList<>();
    static int[][] isInRobot;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        isInRobot = new int[N][N];
        for(int i = 0; i < N; i++) Arrays.fill(isInRobot[i], -1);
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            robots.add(new Robot(x, y, k + 1));
            isInRobot[x][y] = k;
        }
        
        
        for(int l = 0; l < L; l++) {
            // 1. 청소기 이동
            for(int i = 0; i < robots.size(); i++) {
                moveRobot(robots.get(i));
            }
            
            // 2. 청소
            for(int i = 0; i < robots.size(); i++) 
                cleanRobot(robots.get(i));
            
            
            // 3. 먼지 축적
            saveDust();
            // 4. 먼지 확산
            spreadDust();
            
            // 5. 먼지 출력
            sumDust();
            
        }
    }
    
    static void saveDust() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] > 0) map[i][j] += 5;
            }
        }
    }
    
    
    static void moveRobot(Robot rb) {
        if (map[rb.x][rb.y] > 0) return;

        ArrayDeque<int[]> q = new ArrayDeque<>();
        int[][] dist = new int[N][N];
        for (int[] row : dist) Arrays.fill(row, -1);

        q.add(new int[]{rb.x, rb.y});
        dist[rb.x][rb.y] = 0;

        int bestDist = -1;
        int bestR = -1, bestC = -1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            if (bestDist != -1 && dist[r][c] > bestDist) break;

            for (int d = 0; d < 4; ++d) {
                int nr = r + dx[d], nc = c + dy[d];
                if (!isIn(nr, nc)) continue;
                if (dist[nr][nc] != -1) continue;
                if (map[nr][nc] < 0) continue;    // 물건
                if (isInRobot[nr][nc] != -1) continue;  // 다른 로봇

                dist[nr][nc] = dist[r][c] + 1;
                q.add(new int[]{nr, nc});

                if (map[nr][nc] > 0) {
                    if (bestDist == -1 || bestDist == dist[nr][nc]) {
                        if (bestDist == -1) {
                            bestDist = dist[nr][nc];
                            bestR = nr; bestC = nc;
                        } else {
                            // (행,열) 사전순 최소 유지
                            if (nr < bestR || (nr == bestR && nc < bestC)) {
                                bestR = nr; bestC = nc;
                            }
                        }
                    }
                }
            }
        }

        if (bestDist != -1) {
            isInRobot[rb.x][rb.y] = -1;
            rb.x = bestR; rb.y = bestC;
            isInRobot[rb.x][rb.y] = rb.id;
        }
    }
    
    
    static void startClean(Robot robot) {
        int maxDust = 0;
        int dir = 0;
        for(int i = 0; i < 4; i++) {
            int dustCnt = map[robot.x][robot.y];
            for(int j = 0; j < 4; j++) {
                if(cleanDir[i] == j) continue;
                int nx = robot.x + dx[j];
                int ny = robot.y + dy[j];
                if(!isIn(nx, ny) || map[nx][ny] == -1 || map[nx][ny] == 0) continue;
                dustCnt += map[nx][ny];
            }
            if(maxDust < dustCnt) {
                maxDust = dustCnt;
                dir = i;
            }
        }
        maxDust = Math.min(maxDust, 20);
        for(int i = 0; i < 4; i++) {
            if(cleanDir[dir] == i) continue;
            int nx = robot.x + dx[i];
            int ny = robot.y + dy[i];
            if(!isIn(nx, ny) || map[nx][ny] == -1 || map[nx][ny] == 0) continue;
            map[nx][ny] = Math.max(0, map[nx][ny] - maxDust);
        }
        
        map[robot.x][robot.y] = Math.max(0, map[robot.x][robot.y] - maxDust);
    }
    
    // 청소: 4방 중 1방향 제외 → 자기칸+나머지 3방의 먼지를 각 칸 최대 20만큼 제거
    static void cleanRobot(Robot rb) {
        int bestNoDir = -1;
        int bestSum = 0;

        for (int no = 0; no < 4; ++no) {
            int s = 0;
            for (int k = 0; k < 5; ++k) {
                int rr = rb.x + sx[k], cc = rb.y + sy[k];
                if (!isIn(rr, cc)) continue;
                if (k < 4 && k == no) continue; // 제외 방향
                if (map[rr][cc] > 0) s += Math.min(20, map[rr][cc]);
            }
            if (bestNoDir == -1 || s > bestSum) {
                bestNoDir = no; bestSum = s;
            }
        }

        if (bestSum > 0 && bestNoDir != -1) {
            for (int k = 0; k < 5; ++k) {
                int rr = rb.x + sx[k], cc = rb.y + sy[k];
                if (!isIn(rr, cc)) continue;
                if (k < 4 && k == bestNoDir) continue;
                if (map[rr][cc] > 0) map[rr][cc] = Math.max(0, map[rr][cc] - 20);
            }
        }
    }
    
    static void spreadDust() {
        int[][] copy = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] != 0) copy[i][j] = map[i][j];
                else {
                    int cnt = 0;
                    for(int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(!isIn(nx, ny) || map[nx][ny] < 0) continue;
                        cnt += map[nx][ny];
                    }
                    copy[i][j] = cnt / 10;
                }
            }
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                map[i][j] = copy[i][j];
            }
        }
    }
    
    static void sumDust() {
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] > 0) cnt += map[i][j];
            }
        }
        
        System.out.println(cnt);
    }
    
    static boolean isIn(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

}
