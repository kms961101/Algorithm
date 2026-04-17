import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	int Q = Integer.parseInt(st.nextToken());
    	
    	TreeSet<Integer> treeSet = new TreeSet<>();
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < N; i++) {
    		int num = Integer.parseInt(st.nextToken());
    		treeSet.add(num);
    	}
    	
    	HashMap<Integer, Integer> map = new HashMap<>();
    	int cnt = 1;
    	for(Integer set : treeSet) {
    		map.put(set, cnt);
    		cnt++;
    	}
    	
    	
    	for(int i = 1; i <= Q; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int aCnt = map.containsKey(a) ? map.get(a) : map.get(treeSet.higher(a));
    		int bCnt = map.containsKey(b) ? map.get(b) : map.get(treeSet.lower(b));
    		System.out.println(bCnt - aCnt + 1);
    	}
    }
}