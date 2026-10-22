import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node>{
        int n, cnt;
        
        public Node(int n, int cnt) {
            this.n = n;
            this.cnt = cnt;
        }
        
        @Override
        public int compareTo(Node n) {
            return n.cnt - this.cnt;
        }
    }
    static int n, m, A, B, K;
    static int[] uf, cnt;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        uf = new int[n + 1];
        cnt = new int[n + 1];
        for(int i = 1; i <= n; i++) uf[i] = i;
        
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x, y);
        }
        
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        for(int i = 1; i <= n; i++) 
            cnt[find(i)]++;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 1; i <= n; i++) {
            if(cnt[i] > 0) pq.add(new Node(i, cnt[i]));
        }
        
        int ufA = find(A);
        int ufB = find(B);
        int ans = cnt[ufA];
        while(!pq.isEmpty()) {
            if(K == 0) break;
            Node next = pq.poll();
            if(ufA == next.n || ufB == next.n) continue;
            //ufA = next.n;
            ans += next.cnt;
            K--;
            
        }
        
        System.out.println(ans);
        
    }
    
    static void union(int x, int y) {
        int X = find(x);
        int Y = find(y);
        
        uf[X] = Y;
    }
    
    static int find(int x) {
        if(uf[x] == x)
            return x;
        return uf[x] = find(uf[x]);
    }
}