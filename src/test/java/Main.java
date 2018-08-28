import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        String[][] strs = new String[N][];
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(sc.nextLine());
            strs[i] = new String[n];
            for (int j = 0; j < n; j++) {
                strs[i][j] = sc.nextLine();
            }
        }
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            int n = strs[i].length;
            for (int j = 0; j < n; j++) {
                int k;
                if (flag) {
                    break;
                }
                for (k = j; k < n; k++) {
                    if (flag) {
                        break;
                    }
                    String a = strs[i][j];
                    String b = strs[i][k];
                    for (int l = 0; l < a.length(); l++) {
                        if (flag) {
                            break;
                        }
                        if (a.length() != b.length()) {
                            break;
                        }
                        if (a.substring(0, l).equals(b.substring(b.length() - l, b.length()))) {
                            if (a.substring(l, a.length()).equals(b.substring(0, l))) {
                                System.out.println("Yeah");
                                flag = true;
                                break;
                            }
                        }
                    }
                    a = new StringBuilder(a).reverse().toString();
                    b = new StringBuilder(b).reverse().toString();
                    for (int l = 0; l < a.length(); l++) {
                        if (flag) {
                            break;
                        }
                        if (a.length() != b.length()) {
                            break;
                        }
                        if (a.substring(0, l).equals(b.substring(b.length() - l, b.length()))) {
                            if (a.substring(l, a.length()).equals(b.substring(0, l))) {
                                System.out.println("Yeah");
                                flag = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (flag) {
                flag = false;
            } else {
                System.out.println("Sad");
            }
        }

    }

}
