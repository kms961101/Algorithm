import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	char[] arr = new char[N + 1];
    	String line = br.readLine();
    	
    	for(int i = 1; i <= N; i++) {
    		arr[i] = line.charAt(i - 1);
    	}
    	
    	int[][] prefix = new int[3][N + 1];
    	HashMap<Integer, Character> map = new HashMap<>();
    	map.put(0, 'C');
    	map.put(1, 'O');
    	map.put(2, 'W');
    	
    	for(int i = 0; i < 3; i++) {
    		for(int j = 1; j <= N; j++) {
    			char a = arr[j];
    			char b = map.get(i);
    			if(a == b) {
    				if(i == 0) prefix[i][j] = prefix[i][j - 1] + 1;
    				else prefix[i][j] = prefix[i][j - 1] + prefix[i - 1][j];
    			}
    			else prefix[i][j] += prefix[i][j - 1];
    		}
    	}
    	
    	System.out.println(prefix[2][N]);
    }
}