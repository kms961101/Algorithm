import java.io.*;
import java.util.*;

public class Main {
    static class People implements Comparable<People>{
		int x, v;
		
		People(int x, int v){
			this.x = x;
			this.v = v;
		}
		
		@Override
		public int compareTo(People p) {
			return this.x - p.x;
		}
	}
	
	static class Event implements Comparable<Event>{
		double diff;
		int x, v;
		
		Event(double diff, int x, int v){
			this.diff = diff;
			this.x = x;
			this.v = v;
		}
		
		@Override
		public int compareTo(Event e) {
			double d = this.diff - e.diff;
			if(d < 0) return -1;
			else if(d == 0) return this.x - e.x;
			else return 1;
		}
	}
	
	static int N, T;
	static TreeSet<People> peoples = new TreeSet<>();
	static TreeSet<Event> events = new TreeSet<>();
	static People[] tracks;
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	T = Integer.parseInt(st.nextToken());
    	tracks = new People[N];
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int x = Integer.parseInt(st.nextToken());
    		int v = Integer.parseInt(st.nextToken());
    		tracks[i] = new People(x, v);
    		peoples.add(new People(x, v));
    	}
    	
    	for(int i = 0; i < N - 1; i++) {
    		addEvent(tracks[i].x, tracks[i].v, tracks[i + 1].x, tracks[i + 1].v);
    	}
    	
    	while(!events.isEmpty()) {
    		Event now = events.first();
    		int x = now.x; int v = now.v; double t = now.diff;
    		// 도달하는 시점에 이미 시간이 끝났으면 패스
    		if(T < t) break;
    		
    		// 현재 이벤트 지우기
    		peoples.remove(new People(x, v));
    		events.remove(new Event(t, x, v));
    		
    		// 현재 번호 다음번 번호
    		People next = peoples.higher(new People(x, v));
    		int nx = next.x; int nv = next.v;
    		
    		// 현재 번호 이전 번호가 있는지 체크
    		if(peoples.lower(new People(x, v)) != null) {
    			People prev = peoples.lower(new People(x, v));
    			int px = prev.x; int pv = prev.v;
    			
    			removeEvent(px, pv, x, v);
    			addEvent(px, pv, nx, nv);
    		}
    	}
    	
    	System.out.println(peoples.size());
    }
    
    static void addEvent(int x1, int v1, int x2, int v2) {
    	if(v1 <= v2)
    		return;
    	
    	events.add(new Event(1.0 * (x2 - x1) / (v1 - v2), x1, v1));
    }
    
    static void removeEvent(int x1, int v1, int x2, int v2) {
    	if(v1 <= v2)
    		return;
    	
    	events.remove(new Event(1.0 * (x2 - x1) / (v1 - v2), x1, v1));
    }
}