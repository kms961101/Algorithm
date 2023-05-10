public class Main
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        String answer = "";
        // 오름차순 체크
        if(start == 1){
            answer = "ascending";
            for(int i = 1; i < 8; i++){
                if(Integer.parseInt(st.nextToken()) != ++start){
                    answer = "mixed";
                    break;
                }
            }
            
        // 내림차순 체크
        }else if(start == 8){
            answer = "descending";
            for(int i = 1; i < 8; i++){
                if(Integer.parseInt(st.nextToken()) != --start){
                    answer = "mixed";
                    break;
                }
            }
            
        }
        System.out.println(answer);
    }
}