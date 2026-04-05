import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashSet<Integer> set = new HashSet<>();
        set.add(N + 1);
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
        	int prev = 0;
        	int cnt = 0;
        	set.add(Integer.parseInt(st.nextToken()));
        	
        	for(Integer next : set) {
        		int num = prev == 0 ? next - prev : next - prev - 1;
        		cnt = Math.max(cnt, num);
        		prev = next;
        	}
        	System.out.println(cnt);
        }
    }
}