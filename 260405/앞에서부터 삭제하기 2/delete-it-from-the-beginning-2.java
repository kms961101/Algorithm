import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	PriorityQueue<Integer> pq = new PriorityQueue<>();
    	int[] arr = new int[N];
    	for(int i = 0; i < N; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	// 앞에 i 번까지 빼면 뒤에는 i + 1 ~ N까지 이므로 뒤에서부터 더함
    	double ans = 0;
    	int sum = 0;
    	pq.add(arr[N - 1]);
    	sum += arr[N - 1];
    	
    	for(int i = N - 2; i >= 1; i--) {
    		pq.add(arr[i]);
    		sum += arr[i];
    		
    		double d = (double)(sum - pq.peek()) / (N - i - 1);
    		
    		if(ans < d)
    			ans = d;
    	}
    	
    	System.out.printf("%.2f",ans);
    }
}