import java.util.*;;
import java.io.*;

public class Main{
    static class Node implements Comparable<Node>{
        int index, cost;

        public Node(int index, int cost){
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n){
            if(this.cost == n.cost) return this.index - n.index;
            return this.cost - n.cost;
        }
    }
    static int N, M, maxD, index;
    static int[] distA, distB, distC;
    static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        distA = new int[N + 1];
        distB = new int[N + 1];
        distC = new int[N + 1];

        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        distA = dijkstra(A);
        distB = dijkstra(B);
        distC = dijkstra(C);

        for(int i = 1; i <= N; i++){
            if(i == A || i == B || i == C) continue;
            int min = Math.min(distA[i], Math.min(distB[i], distC[i]));
            if(min > maxD){
                maxD = min;
                index = i;
            }
        }
        System.out.println(index);
    }

    static int[] dijkstra(int num){
        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[num] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(num, 0));

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
        return dist;
    }
}