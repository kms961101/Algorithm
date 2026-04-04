import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String, String> papers = new HashMap<>();
        
        for(int i = 1; i <= N * 2; i++) {
        	if(i <= N) papers.put("A" + i, br.readLine());
        	else papers.put("B" + (i - N), br.readLine());
        }
        
        HashSet<String> aGroups = new HashSet<>();
        HashSet<String> bGroups = new HashSet<>();
        int cnt = 0;
        for(int i = 0; i < M; i++) {
        	for(int j = i + 1; j < M; j++) {
        		for(int k = j + 1; k < M; k++) {
        			for(int a = 1; a <= N; a++) {
        				String paper = papers.get("A" + a);
        				String aLine = (paper.charAt(i) + "") + (paper.charAt(j) + "") + (paper.charAt(k) + "");
        				aGroups.add(aLine);
        			}
        			// b 그룹에 a로 만든 조합이 있는지 체크
        			for(int b = 1; b <= N; b++) {
        				String paper = papers.get("B" + b);
        				String bLine = (paper.charAt(i) + "") + (paper.charAt(j) + "") + (paper.charAt(k) + "");
        				bGroups.add(bLine);
        			}
        			
        			boolean duplicate = Collections.disjoint(aGroups, bGroups);
        			if(duplicate) cnt++;
        			aGroups.clear();
        			bGroups.clear();
        		}
        	}
        }
        
        System.out.println(cnt);
    }
}