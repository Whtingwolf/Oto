package Java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

public class HomeWork {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transcation> transcations = Arrays.asList(
            new Transcation(brian, 2011, 300),
            new Transcation(raoul, 2012, 1000),
            new Transcation(raoul, 2011, 400),
            new Transcation(mario, 2012, 710),
            new Transcation(mario, 2012, 700),
            new Transcation(alan, 2012, 950)
    );

    @Test
    public void Test1() {
        List<Transcation> list = transcations.stream()
                .filter(x -> x.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transcation::getValue))
                .collect(Collectors.toList());
        System.out.println(Arrays.toString(list.toArray()));
    }

    @Test
    public void Test2() {
        System.out.println(Arrays.toString(transcations.stream()
                .map(x -> x.getTrader().getCity())
                .collect(toSet()).toArray()));
    }

    @Test
    public void Test3() {
        List<Trader> traders = transcations.stream()
                .map(Transcation::getTrader)
                .distinct()
                .filter(x -> x.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(Arrays.toString(traders.toArray()));
    }

    @Test
    public void Test4() {
        String traders = transcations.stream()
                .map(x -> x.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());
//        System.out.println(Arrays.toString(traders.toArray()));
        System.out.println(traders);
    }

    @Test
    public void Test5() {
        System.out.println(
                transcations.stream()
                        .anyMatch(x -> x.getTrader().getCity().equals("Milan"))
        );
    }

    @Test
    public void Test6() {
        transcations.stream()
                .filter(x -> x.getTrader().getCity().equals("Cambridge"))
                .map(Transcation::getValue)
                .forEach(System.out::println);
    }

    @Test
    public void Test7() {
        transcations.stream()
                .map(Transcation::getValue)
                .max(Comparator.naturalOrder())
                .ifPresent(System.out::println);
    }

    @Test
    public void Test8() {
        transcations.stream()
                .min(Comparator.comparing(Transcation::getValue))
                .ifPresent(System.out::println);
    }

}
