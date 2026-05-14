import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine());
        int[] move = new int[N];
        int[] cost = new int[N];
        int[] R = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N - 1; i++)
            move[i] = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            cost[i] = Integer.parseInt(st.nextToken());
        R[0] = cost[0];
        for(int i = 1; i < N; i++)
            R[i] = Math.min(R[i - 1], cost[i]);
        
        long ans = 0;
        for(int i = 0; i < N - 1; i++)
            ans += (long)R[i] * move[i];
        
        System.out.println(ans);
    }
}