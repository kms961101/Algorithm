import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine());
    	while(T-- > 0) {
    		int M = Integer.parseInt(br.readLine());
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		PriorityQueue<Integer> minHeep = new PriorityQueue<>();
    		PriorityQueue<Integer> maxHeep = new PriorityQueue<>(Comparator.reverseOrder());
    		
    		for(int i = 0; i < M; i++) {
    			int num = Integer.parseInt(st.nextToken());
    			if(minHeep.size() == maxHeep.size()) {
    				maxHeep.add(num);
    			}
    			else {
    				minHeep.add(num);
    			}
    			
    			if(!minHeep.isEmpty()) {
    				if(maxHeep.peek() > minHeep.peek()) {
    					int max = maxHeep.poll();
    					int min = minHeep.poll();
    					
    					maxHeep.add(min);
    					minHeep.add(max);
    				}
    			}
    			
    			if(i % 2 == 0) {
    				System.out.print(maxHeep.peek() + " ");
    			}
    		}
            System.out.println();
    	}
    }
}