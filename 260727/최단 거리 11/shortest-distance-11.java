import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_N = 1000;
    static int n, m, A, B;
    static int[][] dist = new int[MAX_N + 1][MAX_N + 1];;
    static int[] dp = new int[MAX_N + 1];
    static int[] path = new int[MAX_N + 1];
    static boolean[] visited = new boolean[MAX_N];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            
            dist[a][b] = dist[b][a] = d;
        }
        
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        
        Arrays.fill(dp, (int)1e9);
        dp[B] = 0;
        
        for(int i = 1; i <= n; i++) {
            // 아직 방문하지 않은 정점 중
            // 거리가 최솟값인 정점 찾기
            int minDist = -1;
            
            for(int j = 1; j <= n; j++) {
                if(visited[j]) continue;
                
                if(minDist == -1 || dp[minDist] > dp[j]) {
                    minDist = j;
                }
            }
            
            visited[minDist] = true;
            
            for(int j = 1; j <= n; j++) {
                // 간선이 없으면 패스
                if(dist[minDist][j] == 0) continue;
                
                if(dp[j] > dp[minDist] + dist[minDist][j]) {
                    dp[j] = dp[minDist] + dist[minDist][j];
                    path[j] = minDist;
                }
            }
        }
        
        System.out.println(dp[A]);
        int x = A;
        System.out.print(x + " ");
        while(x != B) {
            for(int i = 1; i <= n; i++) {
                if(dist[i][x] == 0) continue;
                
                if(dp[i] + dist[i][x] == dp[x]) {
                    x = i;
                    break;
                }
            }
            System.out.print(x + " ");
        }
    }
}