import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
		int sum, idx1, idx2;
		
		Node(int sum, int idx1, int idx2){
			this.sum = sum;
			this.idx1 = idx1;
			this.idx2 = idx2;
		}
		
		@Override
		public int compareTo(Node n) {
			if(this.sum != n.sum) return this.sum - n.sum;
			else if(this.idx1 != n.idx1) return this.idx1 - n.idx1;
			return this.idx2 - n.idx2;
		}
	}
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	int K = Integer.parseInt(st.nextToken());
    	
    	int[] nArr = new int[N];
    	int[] mArr = new int[M];
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < N; i++) {
    		nArr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < M; i++) {
    		mArr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	Arrays.sort(nArr);
    	Arrays.sort(mArr);
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	// 처음 N에 있는 원소들은 M에 있는 처음 원소랑 매칭됨
    	for(int i = 0; i < N; i++) {
    		pq.add(new Node(nArr[i] + mArr[0], i, 0));
    	}
    	
    	for(int i = 0; i < K - 1; i++) {
    		Node next = pq.poll();
    		int idx1 = next.idx1;
    		int idx2 = next.idx2;
    		// M에 있는 다음 숫자가 남아 있으면 다음으로 넣어주기
    		idx2++;
    		if(idx2 < M) {
    			pq.add(new Node(nArr[idx1] + mArr[idx2], idx1, idx2));
    		}
    	}
    	
    	System.out.println(pq.peek().sum);
    }
}