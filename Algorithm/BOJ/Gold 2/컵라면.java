import java.util.*;
import java.io.*;

public class Main{
    public static class Problem implements Comparable<Problem>{
        int deadLine;
        int cnt;

        public Problem(int deadLine, int cnt){
            this.deadLine = deadLine;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Problem p){
            if(this.deadLine != p.deadLine) return this.deadLine - p.deadLine;
            return p.cnt - this.cnt;
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Problem> pq = new PriorityQueue<>();
        PriorityQueue<Integer> answer = new PriorityQueue<>();

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            pq.add(new Problem(deadLine, cnt));
        }

        while(!pq.isEmpty()){
            Problem problem = pq.poll();
            int size = answer.size();
            // 데드라인보다 저장한 개수가 적으면 넣기
            if(size < problem.deadLine) answer.add(problem.cnt);
                // 개수가 같다면 현재 값이랑 젤 작은값 비교해서 큰값 넣기
            else if(size == problem.deadLine){
                Integer peek = answer.peek();
                if(peek < problem.cnt){
                    answer.poll();
                    answer.add(problem.cnt);
                }
            }
        }

        int cnt = 0;
        while(!answer.isEmpty()){
            cnt += answer.poll();
        }

        System.out.println(cnt);
    }
}