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
    	treeSet.add((int)(1e9 + 1));
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
    		
    		int aCnt = map.get(treeSet.ceiling(a));
    		int bCnt = map.get(treeSet.higher(b));
    		
    		System.out.println(bCnt - aCnt);
    	}
    }
}