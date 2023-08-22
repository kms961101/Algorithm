import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        String[] strings = Arrays.stream(numbers)
            .mapToObj(String::valueOf)
            .toArray(String[]::new);
        Arrays.sort(strings, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        
        if(strings[0].equals("0")) return "0";
        String collect = Arrays.stream(strings).collect(Collectors.joining());
        return collect;
    }
}