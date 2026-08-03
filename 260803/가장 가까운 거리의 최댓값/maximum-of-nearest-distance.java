import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int end, dist;
        
        Node(int end, int dist){
            this.dist = dist;
            this.end = end;
        }
        
        @Override
        public int compareTo(Node n) {
            return this.dist - n.dist;
        }
    }
    
    final static int MAX_N = 100000;
    final static int MAX_M = 100000;
    static int N, M;
    static int A, B, C;
    static ArrayList<Node>[] edge = new ArrayList[MAX_N];
    static int[] dp = new int[MAX_N];
    static int[] abcDist = new int[MAX_N];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        for(int i = 1; i <= N; i++) {
            edge[i] = new ArrayList<>();
            abcDist[i] = (int)1e9;
        }
            
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            
            edge[start].add(new Node(end, dist));
            edge[end].add(new Node(start, dist));
        }
        
        dijk(A);
        dijk(B);
        dijk(C);
        
        int answer = 0;
        for(int i = 1; i <= N; i++) {
            answer = Math.max(answer, abcDist[i]);
        }
        
        System.out.println(answer);
    }
    
    static void dijk(int start) {
        int max = (int)1e9;
        for(int j = 1; j <= N; j++)
            dp[j] = (int)1e9;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dp[start] = 0;
        pq.add(new Node(start, 0));
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if(now.dist != dp[now.end]) continue;
            
            for(Node next : edge[now.end]){
                if(dp[next.end] > dp[now.end] + next.dist) {
                    dp[next.end] = dp[now.end] + next.dist;
                    pq.add(new Node(next.end, dp[next.end]));
                }
            }
            
        }
        for(int i = 1; i <= N; i++) {
            abcDist[i] = Math.min(abcDist[i], dp[i]);
        }
    }
}