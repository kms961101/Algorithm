import java.util.*;
import java.io.*;

public class Main{
    static int K, V, E;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] team;
    static String answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        K = Integer.parseInt(br.readLine());

        for(int T = 0; T < K; T++){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            answer = "YES";
            // 인접 리스트 초기화
            graph = new ArrayList<>();
            for(int i = 0; i < V; i++) graph.add(new ArrayList<>());

            for(int i = 0; i < E; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                graph.get(from).add(to);
                graph.get(to).add(from);
            }

            team = new int[V];

            for(int i = 0; i < V; i++){
                if(team[i] == 0){
                    if(findTeam(i)) break;
                }
            }

            System.out.println(answer);
        }
    }

    static boolean findTeam(int num){
        Queue<Integer> q = new LinkedList<>();
        q.add(num);
        team[num] = 1;

        while(!q.isEmpty()){
            int now = q.poll();

            for(Integer next : graph.get(now)){
                // 인접한 곳이 나와 같은 팀인지 체크
                if(team[now] == team[next]){
                    answer = "NO";
                    return true;
                }
                if(team[next] == 0){
                    // 반대편으로 색칠
                    team[next] = team[now] * -1;
                    q.add(next);
                }
            }
        }

        return false;
    }
}