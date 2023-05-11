import java.util.*;
import java.io.*;

public class Main{
    static class Node{
        int v;
        int cost;
        
        public Node(int v, int cost){
            this.v = v;
            this.cost = cost;
        }
    }
    static int N, E, v1, v2;
    static boolean[] visited;
    static int[] dist;
    static List<Node>[] graph;
    static final int INF = 200000000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        dist = new int[N + 1];
        visited = new boolean[N + 1];
        
        for(int i = 0; i < N + 1; i++){
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }
        
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }
        
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
        
        // 1 -> v1 -> v2 -> N
        int res1 = 0;
        res1 += dijkstra(1, v1);
        res1 += dijkstra(v1, v2);
        res1 += dijkstra(v2, N);
        
        // 1 -> v2 -> v1 -> N
        int res2 = 0;
        res2 += dijkstra(1, v2);
        res2 += dijkstra(v2, v1);
        res2 += dijkstra(v1, N);
        
        int answer = (res1 >= INF && res2 >= INF) ? -1 : Math.min(res1, res2);
        System.out.println(answer);
    }
    
    static int dijkstra(int start, int end){
        Arrays.fill(dist, INF);
        Arrays.fill(visited, false);
        
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> (o1.cost - o2.cost));
        pq.add(new Node(start, 0));
        dist[start] = 0;
        
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(visited[now.v]) continue;
            visited[now.v] = true;
            
            for(Node next : graph[now.v]){
                if(!visited[next.v] && dist[next.v] > now.cost + next.cost){
                    dist[next.v] = now.cost + next.cost;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }
        
        return dist[end];
    }
}