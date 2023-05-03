import java.util.*;
import java.io.*;

public class Main
{
    static int n, m, S, T;
    static boolean[] from, to, rFrom, rTo;
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 정점 n, 간선 m
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        // 인접 리스트 생성  - 원래방향, 반대방향 두개
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        ArrayList<Integer>[] adjGraph = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++){
            graph[i] = new ArrayList<>();
            adjGraph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            adjGraph[y].add(x);
        }
        
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        // 시작점에서 종점으로 원래방향 이동
        from = new boolean[n + 1];
        // 종점은 한번만 방문가능
        from[T] = true;
        dfs(S, graph, from);
        
        // 종점에서 시작점으로 원래방향 이동
        to = new boolean[n + 1];
        // 시작점은 한번만 방문가능
        to[S] = true;
        dfs(T, graph, to);

        // 시작점에서 종점으로 반대방향 이동
        rFrom = new boolean[n + 1];
        dfs(S, adjGraph, rFrom);
        
        // 종점에서 시작점으로 원래방향 이동
        rTo = new boolean[n + 1];
        dfs(T, adjGraph, rTo);
        int cnt = 0;
        // 모든방향에 똑같이 방문한곳 찾기
        for(int i = 1; i < n + 1; i++){
            if(from[i] && to[i] && rFrom[i] && rTo[i]) cnt++;
        }
        
        // 출발점 종점 두개 제외
        System.out.println(cnt - 2);
    }

    static void dfs(int now, ArrayList<Integer>[] graph, boolean[] visited){
        if(visited[now]) return;
        visited[now] = true;

        for(Integer n : graph[now]){
            dfs(n, graph, visited);
        }
        return;
    }
}