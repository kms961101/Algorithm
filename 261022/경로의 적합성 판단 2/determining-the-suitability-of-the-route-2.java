import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        arr = new int[n + 1];

        for(int i = 1; i <= n; i++) arr[i] = i;

        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x, y);
        }

        st = new StringTokenizer(br.readLine());
        int[] step = new int[k + 1];
        for(int i = 0; i < k; i++){
            step[i] = Integer.parseInt(st.nextToken());
        }
        int now = find(step[0]);
        int ans = 1;
        for(int i = 1; i < k; i++){
            if(find(step[i]) != now) ans = 0;
        }

        System.out.println(ans);

    }

    static void union(int x, int y){
        int ufX = find(x);
        int ufY = find(y);
        arr[ufX] = ufY;
    }

    static int find(int x){
        if(arr[x] == x)
            return x;
        return arr[x] = find(arr[x]);
    }
}