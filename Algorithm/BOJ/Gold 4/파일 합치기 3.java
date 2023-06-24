import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] agrs) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            int K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            for(int i = 0; i < K; i++) pq.add(Long.parseLong(st.nextToken()));

            long num = 0L;
            while(pq.size() > 1){
                long tmp1 = pq.poll();
                long tmp2 = pq.poll();
                num += tmp1 + tmp2;
                pq.add(tmp1 + tmp2);
            }

            System.out.println(num);
        }
    }
}
