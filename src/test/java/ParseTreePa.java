import converter.classTree.ParsedTree;
import org.junit.Test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseTreePa {

    @Test
    public void ParaseTreee() {
        Domain domain = new Domain();
        ParsedTree parsedTree = new ParsedTree(domain);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long m = sc.nextInt();
        if (m == 1) {
            System.out.println(2);
            return;
        }
        for (long i = m; i > 1; i--) {
            if (isPrime(i)) {
                long b = 2 * i;
                System.out.println(b);
                return;
            }
        }
    }

    public static boolean isPrime(long a) {
        boolean flag = true;
        if (a < 2) {// 素数不小于2
            return false;
        } else {
            for (int i = 2; i <= Math.sqrt(a); i++) {

                if (a % i == 0) {// 若能被整除，则说明不是素数，返回false

                    flag = false;
                    break;// 跳出循环
                }
            }
        }
        return flag;
    }

    public int myAtoi(String str) {
        str = str.trim();
        Pattern pattern = Pattern.compile("^(\\+|\\-)?\\d+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            str = matcher.group();
        } else {
            return 0;
        }
        int result = 0;
        boolean negative = false;
        int i = 0, len = str.length();
        int limit = -Integer.MAX_VALUE;
        int multmin;
        int digit;

        if (len > 0) {
            char firstChar = str.charAt(0);
            if (firstChar < '0') { // Possible leading "+" or "-"
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != '+')
                    return 0;
                if (len == 1) // Cannot have lone "+" or "-"
                    return 0;
                i++;
            }
            multmin = limit / 10;
            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                digit = Character.digit(str.charAt(i++), 10);
                if (digit < 0) {
                    return 0;
                }
                if (result < multmin) {
                    return limit;
                }
                result *= 10;
                if (result < limit + digit) {
                    return limit;
                }
                result -= digit;
            }
        } else {
            return 0;
        }
        return negative ? result : -result;
    }

    @Test
    public void print() {
        int a = 22;
        for (int i = 1; i <= a; i++) {
            System.out.print(i + ",");
        }
    }

    @Test
    public void mvAtoiTest() {
//        int a = myAtoi("-91283472332 with words");
        int a = myAtoi("42");
        System.out.println(a);
    }
}
