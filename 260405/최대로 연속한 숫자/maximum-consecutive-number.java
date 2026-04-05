import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
		int len, s, e;
		
		Node(int len, int s, int e){
			this.len = len;
			this.s = s;
			this.e = e;
		}
		
		@Override
		public int compareTo(Node n) {
			if(this.len != n.len) return n.len - this.len;
			else if(this.s != n.s) return this.s - n.s;
			return this.e - n.e;
		}
	}
	
	static TreeSet<Node> tSet = new TreeSet<>();
	static TreeSet<Integer> nSet = new TreeSet<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        // 초기값
        nSet.add(-1);
        nSet.add(N + 1);
        tSet.add(new Node(N + 1, -1, N + 1));
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
        	int y = Integer.parseInt(st.nextToken());
        	// y 바로 밑에 값
        	int x = nSet.lower(y);
        	// y 바로 위에 값
        	int z = nSet.higher(y);
        	nSet.add(y);
        	// x ~ z 범위가 지워지고 x ~ y, y ~ z 2개로 추가됨
        	tSet.remove(new Node(z - x - 1, x, z));
        	tSet.add(new Node(y - x - 1, x, y));
        	tSet.add(new Node(z - y - 1, y, z));
        	System.out.println(tSet.first().len);
        }
    }
}