import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] a = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] b = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] c = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] d = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        
        HashMap<Long, Integer> ab = new HashMap<>();
        HashMap<Long, Integer> cd = new HashMap<>();
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		int abCnt = ab.getOrDefault(a[i] + b[j], 0);
        		ab.put(a[i] + b[j], abCnt + 1);
        		int cdCnt = cd.getOrDefault(c[i] + d[j], 0);
        		cd.put(c[i] + d[j], cdCnt + 1);
        	}
        }
        
        int ans = 0;
        for(long key : ab.keySet()) {
        	if(cd.containsKey(key * -1)) {
        		ans += ab.get(key) * cd.get(key * -1);
        	}
        }
        
        System.out.println(ans);
    }
}
