import org.junit.Test;

import java.util.Scanner;

public class pa {


    @Test
    public void test() {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        sc.nextLine();
//        int [][] a = new int[n][n];
//        int i =0;
//        while (i<n){
//            String s = sc.nextLine();
//            String []strs = s.split(" ");
//            int []b = new int[strs.length];
//            for(int j=0;j<strs.length;j++){
//                b[j] = Integer.parseInt(strs[j]);
//            }
//            a[i] = b;
//            i++;
//        }
//        HashSet<Integer> set = new HashSet<>();
//        int result = 0;
//        HashMap<Integer, int[]> map = new HashMap<>();
//        for(int k=0;k<n;k++){
//            map.put(k,a[k]);
//        }
//        while (set.size()<n){
//            for(int k=0;k<n;k++){
//                if(!set.contains(k)){
//                    int []q = map.get(k);
//                    q =
//                }
//            }
//        }
//
    }

    @Test
    public void threeTest() {
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
