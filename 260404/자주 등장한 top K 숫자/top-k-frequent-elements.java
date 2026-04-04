import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
		int num, cnt;
		
		Node(int num, int cnt){
			this.num = num;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Node n) {
			if(this.cnt == n.cnt) return n.num - this.num;
			return n.cnt - this.cnt;
		}
	}
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static HashMap<Integer, Integer> counts = new HashMap<>();
	static HashSet<Integer> duplicates = new HashSet<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	int num = Integer.parseInt(st.nextToken());
        	int cnt = counts.getOrDefault(num, 0);
        	counts.put(num, cnt + 1);
        	duplicates.add(num);
        	
        }
        
        Iterator<Integer> count = duplicates.iterator();
        while(count.hasNext()) {
        	Integer next = count.next();
        	pq.add(new Node(next, counts.get(next)));
        }
        
        while(K-- > 0 && !pq.isEmpty()) {
        	System.out.print(pq.poll().num + " ");
        }
    }
}