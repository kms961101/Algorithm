import java.io.*;
import java.util.*;

public class Main{
    static class Node implements Comparable<Node>{
        int index;
        int cost;

        public Node(int index, int cost){
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n){
            return this.cost - n.cost;
        }
    }

    static int n, m;
    static int[] dist;
    static boolean[] visited;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];
        visited = new boolean[n + 1];
        for(int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        dist[1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while(!pq.isEmpty()){
            int nowIndex = pq.poll().index;
            if(visited[nowIndex]) continue;
            visited[nowIndex] = true;

            for(Node next : graph[nowIndex]){
                if(dist[next.index] > dist[nowIndex] + next.cost){
                    dist[next.index] = dist[nowIndex] + next.cost;
                    pq.add(new Node(next.index, dist[next.index]));
                }
            }
        }

        System.out.println(dist[n]);
    }
}