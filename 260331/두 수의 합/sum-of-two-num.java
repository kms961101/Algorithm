import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        long[] arr = new long[N];
        HashMap<Long, Long> map = new HashMap<>();
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	arr[i] = Long.parseLong(st.nextToken());
        	long num = map.getOrDefault(arr[i], 0L);
        	map.put(arr[i], num + 1);
        }
        
        long ans = 0;
        for(int i = 0; i < N; i++) {
        	long num = arr[i];
        	long cnt = map.getOrDefault(K - num, 0L);
        	if(K - num == num) cnt--;
        	if(cnt != 0) {
        		ans += cnt;
        		map.put(num, map.get(num) - 1);
        	}
        }
        
        System.out.println(ans);
	}
}