import java.util.*;
import java.io.*;

public class Main {
    static class Pair{
        int x, y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, K, move;
    static int[][] miro;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        miro = new int[N][N];

        // 도착지점은 = -10, 벽은 -9 ~ -1, 현재 위치에 사람들 수만 넣기
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int num = Integer.parseInt(st.nextToken());
                miro[i][j] = -num;
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            miro[x][y]++;
        }

        st = new StringTokenizer(br.readLine());
        int goalX = Integer.parseInt(st.nextToken()) - 1;
        int goalY = Integer.parseInt(st.nextToken()) - 1;
        miro[goalX][goalY] = -10;

        while(K-- > 0){
            // 움직이기
            moveAll();
            // 모두 탈출했는지 체크
            if(isFinished()) break;
            // 회전하기
            rotate();
        }

        Pair ex = findExit();
        System.out.println(move);
        int x = ex.x + 1;
        int y = ex.y + 1;
        System.out.println(x + " " + y);
    }

    static Pair findExit(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(miro[i][j] == -10) return new Pair(i, j);
            }
        }
        return new Pair(0, 0);
    }

    static boolean isFinished(){
        // 한명이라도 있으면 계속 돌려야된다.
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(miro[i][j] > 0) return false;
            }
        }

        return true;
    }

    static void moveAll(){
        // 담아둘 배열 생성
        int[][] temp = new int[N][N];
        Pair ex = findExit();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                // 벽이거나 출구는 그냥 옮겨주면 된다.
                if(miro[i][j] < 0){
                    temp[i][j] = miro[i][j];
                    continue;
                }
                // 빈곳은 패스
                if(miro[i][j] == 0) continue;

                // 사람이 있는곳, 4방향 중에서 현재 출구까지 거리가 가장 작으곳 찾기

                int curDist = (Math.abs(i - ex.x) + Math.abs(j - ex.y));
                int minDist = curDist;
                int minX = 0;
                int minY = 0;
                for(int k = 0; k < 4; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(isIn(nx, ny)) continue;
                    if(-9 <= miro[nx][ny] && miro[nx][ny] <= -1) continue;
                    int nowDist = (Math.abs(nx - ex.x) + Math.abs(ny - ex.y));
                    if(nowDist < minDist){
                        minDist = nowDist;
                        minX = nx;
                        minY = ny;
                    }
                }
                // 아무곳도 움직일 수 없다면
                if(minDist == curDist){
                    temp[i][j] += miro[i][j];
                    continue;
                }
                // 참가자들 수 만큼 이동
                move += miro[i][j];

                // 탈출에 성공한 경우
                if(miro[minX][minY] == -10) continue;

                // 다른 방향에서도 올 수 있으니 더해줘야 한다.
                temp[minX][minY] += miro[i][j];
            }
        }

        // temp 배열 다시 miro로 옮기기
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                miro[i][j] = temp[i][j];
            }
        }
    }

    static void rotate(){
        Pair ex = findExit();
        // 조건을 만족하는 가장 작은 정사각형 크기 찾기
        int minD = 1000;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(miro[i][j] <= 0) continue;
                // 가장 작은 크기는 행, 열 중에 가장 큰 크기가 정사각형이 된다.
                minD = Math.min(minD, Math.max(Math.abs(i - ex.x), Math.abs(j - ex.y)));
            }
        }

        // 찾은 정사각형 크기에 맞는 출발 점 찾기
        int startX = -1;
        int startY = -1;
        for(int i = 0; i < N - minD; i++){
            for(int j = 0; j < N - minD; j++){
                boolean isInPerson = false;
                boolean isInExit = false;
                for(int a = i; a <= i + minD; a++){
                    for(int b = j; b <= j + minD; b++){
                        // 정사각형 안에 출구가 있고, 사람이 한명이라도 있으면 끝내기
                        if(miro[a][b] == -10) isInExit = true;
                        if(miro[a][b] > 0) isInPerson = true;
                    }
                }
                if(isInPerson && isInExit){
                    startX = i;
                    startY = j;
                    break;
                }
            }
            if(startX != -1) break;
        }
        // 회전
        changeRotate(startX, startY, minD);
    }

    static void changeRotate(int startX, int startY, int minD){
        int[][] a = new int[N][N];
        int[][] b = new int[N][N];
        // 이동하는 좌표의 위치를 0, 0부터 시작하게 이동
        for(int i = startX; i <= startX + minD; i++){
            for(int j = startY; j <= startY + minD; j++){
                a[i - startX][j - startY] = miro[i][j];
            }
        }

        int d = minD + 1;
        // 90도 회전
        for(int i = 0; i <= minD; i++){
            for(int j = 0; j <= minD; j++){
                // 벽이면 내구도 1 감소
                if(-9 <= a[i][j] && a[i][j] <= -1) a[i][j]++;
                b[j][d - i - 1] = a[i][j];
            }
        }

        // miro에 복구
        for(int i = startX; i < startX + d; i++){
            for(int j = startY; j < startY + d; j++){
                miro[i][j] = b[i - startX][j - startY];
            }
        }
    }

    static boolean isIn(int x, int y){
        if(x < 0 || y < 0 || x >= N || y >= N) return true;
        return false;
    }
}