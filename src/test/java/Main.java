import java.util.Scanner;

public class Main {

    static long[][] c = new long[105][105];

    private static int mod = 1000000007;

    static void init()//建一个杨辉三角  至于为什么要建一个杨辉三角下面我会详细说明

    {


        c[0][0] = 1L;

        for (int i = 1; i < 105; i++) {

            c[i][0] = 1L;

            for (int j = 1; j < 105; j++) {

                c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % mod;

            }

        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k, a, b, x, y;

        Long ans = 0L;

        int i;

        init();

        k = sc.nextInt();
        // sc.nextLine();

        a = sc.nextInt();
        x = sc.nextInt();
        b = sc.nextInt();
        y = sc.nextInt();

        for (i = 0; i <= k; i++) {

            if (i * a < k && (k - i * a) % b == 0 && (k - a * i) / b < y)//判断组合

            {

                ans = (ans + (c[x][i] * c[y][(k - a * i) / b]) % mod) % mod;//为什么是*呢，因为是排列

                break;//进入到if里其实就可以break了，这样避免往后的判断能降低时间复杂度，增加效率

            }
        }
        System.out.println(ans);
    }
}
