import java.util.*;
import java.io.*;

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

    static int n, m, r, max;
    static int[] items, dist;
    static boolean[] visited;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n + 1];
        graph = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) graph[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) items[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        for(int i = 1; i <= n; i++){
            dist = new int[n + 1];
            visited = new boolean[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            dist[i] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(i, 0));

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

            int cnt = 0;
            for(int j = 1; j <= n; j++){
                if(dist[j] <= m) cnt += items[j];
            }
            max = Math.max(max, cnt);
        }

        System.out.println(max);
    }
}