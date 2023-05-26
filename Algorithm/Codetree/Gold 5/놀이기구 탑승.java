import java.util.*;
import java.io.*;

public class Main {
    static class Student implements Comparable<Student> {
        int x;
        int y;
        int like;
        int empty;

        public Student(int x, int y, int like, int empty){
            this.x = x;
            this.y = y;
            this.like = like;
            this.empty = empty;
        }

        @Override
        public int compareTo(Student s){
            if(this.like != s.like) return s.like - this.like;
            if(this.empty != s.empty) return s.empty - this.empty;
            if(this.x != s.x) return this.x - s.x;
            return this.y - s.y;
        }
    }
    static int N, answer;
    static HashMap<Integer, ArrayList<Integer>> like;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        map = new int[N][N];
        like = new HashMap<>();
        // 순서대로 돌기 위해 담아주기
        Queue<Integer> order = new LinkedList<>();
        for(int i = 0; i < N * N; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            ArrayList<Integer> list = new ArrayList<>();
            for(int j = 0; j < 4; j++) list.add(Integer.parseInt(st.nextToken()));
            like.put(n, list);
            order.add(n);
        }

        int num = 0;
        for(int k = 0; k < N * N; k++){
            PriorityQueue<Student> pq = new PriorityQueue<>();
            int number = order.poll();
            // 모든 들어갈 수 있는곳 확인 하면서 조건에 따른 우선순위 젤 높은 자리 하나 뽑기
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(map[i][j] != 0) continue;
                    pq.add(find(i, j, number, like.get(number)));
                }
            }
            Student student = pq.poll();
            map[student.x][student.y] = number;
        }

        score();
        System.out.println(answer);
    }

    static void score(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int number = map[i][j];
                int cnt = 0;
                for(int k = 0; k < 4; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(isIn(nx, ny)) continue;
                    if(like.get(number).contains(map[nx][ny])) cnt++;
                }
                if(cnt == 4) answer += 1000;
                else if(cnt == 3) answer += 100;
                else if(cnt == 2) answer += 10;
                else if(cnt == 1) answer += 1;
            }
        }
    }

    static Student find(int x, int y, int number, ArrayList<Integer> list){
        int cnt = 0;
        int empty = 0;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(isIn(nx, ny)) continue;
            if(map[nx][ny] == 0) empty++;
            if(list.contains(map[nx][ny])) cnt++;
        }
        return new Student(x, y, cnt, empty);
    }

    static boolean isIn(int x, int y){
        if(x < 0 || y < 0 || x >= N || y >= N) return true;
        return false;
    }
}