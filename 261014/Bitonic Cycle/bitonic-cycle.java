import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node>{
        int x, y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int compareTo(Node n) {
            return this.x - n.x;
        }
    }
    static int n;
    static ArrayList<Node> node = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        for(int i = 0 ; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            node.add(new Node(x, y));
        }
        
        Collections.sort(node);

        long[][] dp = new long[n + 1][n + 1];
        for(int i = 0; i < n; i++) Arrays.fill(dp[i], (long)1e16);
        
        dp[0][0] = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int next = Math.max(i, j) + 1;
                if(next == n) continue;
                
                dp[next][j] = Math.min(dp[next][j], dp[i][j] + dist(i, next));
                dp[i][next] = Math.min(dp[i][next], dp[i][j] + dist(j, next));
            }
        }
        long ans = (long)1e16;
        for(int i = 0; i < n - 1; i++) {
            ans = Math.min(dp[i][n - 1] + dist(i, n - 1), ans);
        }
        
        System.out.println(ans);
    }
    
    static int dist(int i, int j) {
        return (node.get(i).x - node.get(j).x) * (node.get(i).x - node.get(j).x) + (node.get(i).y - node.get(j).y) * (node.get(i).y - node.get(j).y);
    }
}