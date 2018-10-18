package Java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.LongStream;

public class ParallelStream {

    public void parallel(int n) {
        long sum = LongStream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);

        List<String> strings = new ArrayList<>();
        strings.stream().parallel();
        Properties propertie = System.getProperties();
        System.out.println(propertie);

    }

    @Test
    public void parallelTest(){
        parallel(1);
    }
}
