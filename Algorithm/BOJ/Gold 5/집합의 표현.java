import java.io.*;
import java.util.*;

public class Main{
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for(int i = 0; i <= N; i++) parent[i] = i;

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if(order == 0) union(from, to);
            else{
                sb.append((find(from) == find(to) ? "YES" : "NO") + "\n");
            }
        }
        System.out.println(sb.toString());
    }

    static void union(int from, int to){
        int a = find(from);
        int b = find(to);
        if(a == b) return;
        parent[b] = a;
    }

    static int find(int num){
        if(parent[num] == num) return num;
        return parent[num] = find(parent[num]);
    }
}