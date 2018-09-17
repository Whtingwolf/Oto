import java.util.Scanner;

public class Main {

    public static boolean[][] gragh;
    public static boolean[][] keda;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        gragh = new boolean[n][n];
        keda = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            gragh[u - 1][v - 1] = true;
        }
        TransitiveClosure(gragh, keda, n);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int ru = 0;
            int chu = 0;
            for (int j = 0; j < n; j++) {
                if (keda[i][j]) {
                    chu++;
                }
                if (keda[j][i]) {
                    ru++;
                }
            }
            if (ru > chu) {
                sum++;
            }
        }
        System.out.println(sum);
    }

    public static void TransitiveClosure(boolean dist[][], boolean t[][], int N)//寻找可达矩阵
    {
        for (int i = 0; i < N; i++) {//进行遍历
            for (int j = 0; j < N; j++) {
                if ((i == j) || (dist[i][j]))//发现可达
                    t[i][j] = true;
                else
                    t[i][j] = false;
            }
        }
        boolean x[][] = new boolean[N][N];
        for (int k = 0; k < N; k++) {

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    t[i][j] = t[i][j] || (t[i][k] && t[k][j]);//由文中公式可得
                }
            }
        }
    }
}
