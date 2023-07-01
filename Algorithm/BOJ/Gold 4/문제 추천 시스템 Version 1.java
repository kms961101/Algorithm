import java.util.*;
import java.io.*;

public class Main{
    public static class Easy implements Comparable<Easy>{
        int num, diff;

        public Easy(int num, int diff){
            this.num = num;
            this.diff = diff;
        }

        @Override
        public int compareTo(Easy e){
            if(this.diff != e.diff) return this.diff - e.diff;
            return this.num - e.num;
        }
    }

    public static class Hard implements Comparable<Hard>{
        int num, diff;

        public Hard(int num, int diff){
            this.num = num;
            this.diff = diff;
        }

        @Override
        public int compareTo(Hard h){
            if(this.diff != h.diff) return h.diff - this.diff;
            return h.num - this.num;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());
        
        PriorityQueue<Easy> easy = new PriorityQueue<>();
        PriorityQueue<Hard> hard = new PriorityQueue<>();
        // 풀었던 문제인지 체크
        HashMap<Integer, Integer> solved = new HashMap<>();
        // 문제별 난이도 저장
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int diff = Integer.parseInt(st.nextToken());
            map.put(num, diff);
            easy.add(new Easy(num, diff));
            hard.add(new Hard(num, diff));
        }

        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if(cmd.equals("add")){
                int num = Integer.parseInt(st.nextToken());
                int diff = Integer.parseInt(st.nextToken());
                map.put(num, diff);
                easy.add(new Easy(num, diff));
                hard.add(new Hard(num, diff));
            }else if(cmd.equals("solved")){
                int num = Integer.parseInt(st.nextToken());
                solved.put(num, map.get(num));
            }else{
                int x = Integer.parseInt(st.nextToken());
                if(x == 1){
                    boolean flag = false;
                    while(true){
                        Hard h = hard.poll();
                        // 풀었던 번호이면 패스
                        int isSolved = solved.getOrDefault(h.num, -1);
                        if(h.diff != isSolved){
                            System.out.println(h.num);
                            hard.add(h);
                            flag = true;
                        }
                        if(flag) break;
                    }
                }else{
                    boolean flag = false;
                    while(true){
                        Easy e = easy.poll();
                        int isSolved = solved.getOrDefault(e.num, -1);
                        if(e.diff != isSolved){
                            System.out.println(e.num);
                            easy.add(e);
                            flag = true;
                        }
                        if(flag) break;
                    }
                }
            }
        }
    }
}