import java.util.*;
import java.io.*;

public class Main{
    public static class Node{
        HashMap<Character, Node> childNode = new HashMap<>();
        boolean isEndOf;
    }
    
    public static class Trie{
        Node rootNode = new Node();
        
        boolean insert(String str){
            Node node = this.rootNode;
            
            for(int i = 0; i < str.length(); i++){
                node = node.childNode.computeIfAbsent(str.charAt(i), key -> new Node());
                // 넣는데 접두어가 있으면 끝내기
                if(node.isEndOf) return true;
            }
            node.isEndOf = true;
            return false;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for(int T = 0; T < t; T++){
            int n = Integer.parseInt(br.readLine());
            Trie trie = new Trie();
            String answer = "YES";
            ArrayList<String> list = new ArrayList<>();
            // 작은순서대로 넣기위해 정렬
            for(int i = 0; i < n; i++) list.add(br.readLine());
            Collections.sort(list);
            // 접두어가 있으면 "NO"
            for(int i = 0; i < list.size(); i++){
                if(trie.insert(list.get(i))){
                    answer = "NO";
                    break;
                }
            }
            sb.append(answer);
            if(T != t - 1) sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}