import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String s = "12345678abcdefghijk";
        List<String> ss = new ArrayList<>();
        int fast = s.length() % 8;
        for (int i = s.length(); i >= 0; i--) {
            int begin;
            int end;
            if ((i-fast) % 8 == 0) {
                begin = Math.max(0,i-8);;
                end = i;
                String substring = s.substring(begin, end);
                ss.add(substring);
            }
        }
        Collections.reverse(ss);

        System.out.println(ss);
    }
}
