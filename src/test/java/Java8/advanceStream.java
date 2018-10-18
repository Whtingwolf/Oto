package Java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class advanceStream {


    @Test
    public void groupingByTest() {
        HomeWork homeWork = new HomeWork();
        Map<String, Long> map = homeWork.transcations.stream()
                .collect(
                        Collectors.groupingBy(
                                t -> t.getTrader().getCity(),
                                Collectors.counting()
                        )
                );
        System.out.println();
    }

    @Test
    public void rangeTest() {
        List list = IntStream
                .iterate(0, x -> x)
                .limit(10)
                .collect(
                        ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll
                );
        System.out.println(list);
    }

    @Test
    public void partitionTest() {
        HomeWork homeWork = new HomeWork();
        Map map = homeWork.transcations
                .stream()
                .collect(
                        Collectors.partitioningBy(
                                x -> "Cambridge".equals(x.getTrader().getCity()),
                                Collectors.mapping(x -> x.getTrader().getName(), Collectors.toList())
                        )
                );
        System.out.println(map);
    }
}
