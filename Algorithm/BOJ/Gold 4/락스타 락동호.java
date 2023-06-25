import java.util.*;
import java.io.*;

public class Main{
    static int FF, FS, SF, SS;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        FF = Integer.parseInt(st.nextToken());
        FS = Integer.parseInt(st.nextToken());
        SF = Integer.parseInt(st.nextToken());
        SS = Integer.parseInt(st.nextToken());

        System.out.println(find());
    }

    static int find(){
        if (FF == 0 && FS == 0) return SS + Math.min(SF, 1);
        if (FS == 0) return FF;
        int changeTempo;
        if (FS > SF) changeTempo = 2 * SF + 1;
        else changeTempo = 2 * FS;
        return FF + SS + changeTempo;
    }
}