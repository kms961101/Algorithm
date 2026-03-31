import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];
        HashMap<Integer, Integer> map = new HashMap<>();
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        	if(!map.containsKey(arr[i])) {
        		map.put(arr[i], 1);
        	}
        	else {
        		map.put(arr[i], map.get(arr[i]) + 1);
        	}
        }
        
        int ans = 0;
        
        for(int i = 0; i < N; i++) {
        	if(!map.containsKey(arr[i])) {
        		map.put(arr[i], -1);
        	}
        	else {
        		map.put(arr[i], map.get(arr[i]) - 1);
        	}
        	for(int j = 0; j < i; j++) {
        		if(map.containsKey(K - arr[i] - arr[j])) {
        			ans += map.get(K - arr[i] - arr[j]);
        		}
        	}
        }
        
        System.out.println(ans);
	}
}