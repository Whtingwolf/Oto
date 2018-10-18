package Java8;

import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeClass {

    @Test
    public void primeTest() {
        int i = 0;
        long fast = Long.MAX_VALUE;
        ArrayList<Long> data = new ArrayList<>();
        while (i < 10) {
            Long start = System.nanoTime();
            Map map = IntStream.rangeClosed(2, 1_000_000).boxed().collect(new PrimeCollector());
            long duration = (System.nanoTime()-start)/1_000_000;
            if(duration<fast) fast = duration;
            data.add(duration);
            i++;
        }
        LongSummaryStatistics summary = data.stream().collect(Collectors.summarizingLong(x->x));
        System.out.println(summary);

        fast = Long.MAX_VALUE;
        i = 0;
        data.clear();
        while (i < 10) {
            Long start = System.nanoTime();
            Map map = IntStream.rangeClosed(2, 1_000_000).boxed().collect(Collectors.partitioningBy(this::isPrime2));
            long duration = (System.nanoTime()-start)/1_000_000;
            if(duration<fast) fast = duration;
            data.add(duration);
            i++;
        }
        LongSummaryStatistics summary_2 = data.stream().collect(Collectors.summarizingLong(x->x));
        System.out.println(summary_2);
    }

    boolean isPrime2(int candicate) {
        int cadicateRoot = (int) Math.sqrt(candicate);
        return IntStream.rangeClosed(2, cadicateRoot).noneMatch(i -> candicate % i == 0);
    }

    boolean isPrime(List<Integer> primeList, int candicate) {
        int candicateRoot = (int) Math.sqrt(candicate);
        return takeWhile(primeList, i -> i <= candicateRoot).stream().noneMatch(i -> candicate % i == 0);
    }

    <R> List<R> takeWhile(List<R> list, Predicate<R> p) {
        int i = 0;
        for (R item : list) {
            if (!p.test(item)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }

    private class PrimeCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

        @Override
        public Supplier<Map<Boolean, List<Integer>>> supplier() {
            return () -> {
                HashMap map = new HashMap<>();
                map.put(true, new ArrayList<>());
                map.put(false, new ArrayList<>());
                return map;
            };
        }

        @Override
        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
            return (map, num) -> {
                map.get(isPrime(map.get(true),num)).add(num);
            };
        }

        @Override
        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
            return (old, newed) -> {
                old.get(true).addAll(newed.get(true));
                old.get(false).addAll(newed.get(false));
                return old;
            };
        }

        @Override
        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        }
    }
}
