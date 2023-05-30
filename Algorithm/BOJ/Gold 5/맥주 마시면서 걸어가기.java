import java.util.*;
import java.io.*;

public class Main{
    static class Node{
        int x, y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int T;
    static int n, nowX, nowY, cvsX, cvsY;
    static ArrayList<Node> cvsList;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            nowX = Integer.parseInt(st.nextToken());
            nowY = Integer.parseInt(st.nextToken());
            cvsList = new ArrayList<>();
            // 편의점 위치 저장
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                cvsList.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            st = new StringTokenizer(br.readLine());
            cvsX = Integer.parseInt(st.nextToken());
            cvsY = Integer.parseInt(st.nextToken());

            System.out.println(bfs() ? "sad" : "happy");
        }
    }

    static boolean bfs(){
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        q.add(new Node(nowX, nowY));

        while(!q.isEmpty()){
            Node node = q.poll();
            // 현재 위치에서 페스티벌 갈 수 있으면 끝내기
            if(canGo(node.x, node.y, cvsX, cvsY)) return false;

            for(int i = 0; i < n; i++){
                if(visited[i]) continue;
                Node next = cvsList.get(i);
                // 현재 위치에서 편의점을 갈 수 있는지 체크
                if(!canGo(node.x, node.y, next.x, next.y)) continue;
                visited[i] = true;
                q.add(next);
            }
        }
        return true;
    }

    static boolean canGo(int x, int y, int goalX, int goalY){
        return Math.abs(x - goalX) + Math.abs(y - goalY) <= 1000;
    }
}