import java.util.*;
import java.io.*;

public class Main {
    static int n, m, ans;
    static int[][] map, copy;
    static int[] dx = {1, -1, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        copy = new int[n][m];
        ans = (int)2e9;
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                copy[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int state = 0; state < (1 << m); state++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    map[i][j] = copy[i][j];
                }
            }
            
            
            for(int y = 0; y < m; y++) {
                if((state >> y & 1) == 1) {
                    int x = 0;
                    
                    for(int i = 0; i < 5; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        if(!isIn(nx, ny)) continue;
                        
                        map[nx][ny] = 1 - map[nx][ny];
                    }
                }
            }
            
            // 첫번째줄 누른 횟수 구하기
            int num = 0;
            for(int i = 0; i < m; i++) {
                if((state >> i & 1) == 1) num++;
            }
            
            for(int i = 1; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(map[i - 1][j] == 0) {
                        num++;
                        
                        for(int k = 0; k < 5; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if(!isIn(nx, ny)) continue;
                            
                            map[nx][ny] = 1 - map[nx][ny];
                        }
                    }
                }
            }
            
            boolean isFull = true;
            for(int i = 0; i < m; i++) {
                if(map[n - 1][i] != 1) isFull = false;
            }
            
            if(isFull)
                ans = Math.min(ans, num);
        }
        
        if(ans == (int)2e9)
            System.out.println(-1);
        else
            System.out.println(ans);
        
        
    }
    
    static boolean isIn(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}
