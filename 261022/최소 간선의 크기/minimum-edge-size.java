import java.util.*;
import java.io.*;

public class Main {
    static class Edge implements Comparable<Edge>{
        int x, y, n;

        public Edge(int x, int y, int n){
            this.x = x;
            this.y = y;
            this.n = n;
        }
        
        @Override
        public int compareTo(Edge e) {
            return e.n - this.n;
        }
    }
    static int N, M, A, B;
    static ArrayList<Edge> edge;
    static int[] uf;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        uf = new int[N + 1];
        edge = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= N; i++) uf[i] = i;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            edge.add(new Edge(x, y, n));
        }
        
        Collections.sort(edge);
        
        for(int i = 0; i < edge.size(); i++) {
            Edge now = edge.get(i);
            union(now.x, now.y);
            
            if(find(A) == find(B)) {
                System.out.println(now.n);
                break;
            }
                
        }
        
    }

    static void union(int x, int y){
        int X = find(x);
        int Y = find(y);
        uf[X] = Y;
    }

    static int find(int x){
        if(uf[x] == x)
            return x;
        return uf[x] = find(uf[x]);
    }
}