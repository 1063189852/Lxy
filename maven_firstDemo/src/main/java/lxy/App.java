package lxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"lxy.*"})
public class App {

        public static void main(String[] args) {
            SpringApplication.run(App.class, args);
        }

//    public static void main(String[] args) {
//        String s = "12345678abcdefghijk";
//        List<String> ss = new ArrayList<>();
//        int fast = s.length() % 8;
//        for (int i = s.length(); i >= 0; i--) {
//            int begin;
//            int end;
//            if ((i-fast) % 8 == 0) {
//                begin = Math.max(0,i-8);;
//                end = i;
//                String substring = s.substring(begin, end);
//                ss.add(substring);
//            }
//        }
//        Collections.reverse(ss);
//
//        System.out.println(ss);
//    }
}
