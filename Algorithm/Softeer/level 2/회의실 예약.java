import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<String> list = new ArrayList<>();
        HashMap<String, boolean[]> map = new HashMap<>();
        for(int i = 0; i < N; i++){
            String room = br.readLine();
            map.put(room, new boolean[25]);
            list.add(room);
        }

        outer:for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            String room = st.nextToken();
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 방문처리
            boolean[] visited = map.get(room);
            for(int j = start; j < end; j++) visited[j] = true;
            
            // 룸 방문처리 갱신
            map.put(room, visited);
        }

        StringBuilder sb = new StringBuilder();
        // 오름차순 정렬
        Collections.sort(list);
        for(String room : list){
            // 가능한 시간 담을 리스트
            ArrayList<String> temp = new ArrayList<>();
            sb.append("Room " + room + ":").append("\n");
            
            // 아직 시작 안한곳부터 방문한곳까지
            boolean[] visited = map.get(room);
            for(int i = 9; i < 18; i++){
            	int end = 18;
                if(visited[i]) continue;
                for(int j = i + 1; j < 18; j++){
                    if(visited[j]){
                        end = j;
                        break;
                    }
                }
                // 10보다 작으면 "0" 붙혀주기
                String start = i < 10 ? "0" + i : i + "";
                String tmp = end < 10 ? "0" + end : end + "";
                temp.add(start + "-" + tmp);
                i = end;
                if(end == 18) break;
            }

            if(temp.size() == 0) sb.append("Not available").append("\n");
            else{
                sb.append(temp.size() + " available:").append("\n");
                for(String answer : temp) sb.append(answer).append("\n");
            }
            if(!room.equals(list.get(list.size() - 1))) sb.append("-----").append("\n");
        }
        System.out.println(sb.toString());
    }
}