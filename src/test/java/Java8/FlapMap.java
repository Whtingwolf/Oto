package Java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FlapMap {

    int[][] p = {{1, 2, 3}, {3, 4}};

    @Test
    public void flapTest() {
        List<int[]> s = Arrays.stream(p[0])
                .mapToObj(
                        x -> Arrays.stream(p[1]).mapToObj(y -> new int[]{x, y}))
                .flatMap(x -> x)
                .collect(toList());
        System.out.println(Arrays.toString(s.toArray()));
    }
}
