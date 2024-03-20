package leetcode;

import org.apache.kafka.common.protocol.types.Field;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class LoadBalanceTest {


    public static void main(String[] args) {
        List<Element> elements = Arrays.asList(new Element("A", 30), new Element("B", 30), new Element("C", 30));
        Robin robin = new Robin();
        Random random = new Random();
        robin.setElements(elements);
        random.setElements(elements);

        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            map.compute(random.next().name, (k, v) -> v = v == null ? 1 : v+1);
            map2.compute(robin.next().name, (k, v) -> v = v == null ? 1 : v+1);
        }
        System.out.println(map);
        System.out.println(map2);
    }

    private static class Robin extends LoadBalance {

        private AtomicLong current = new AtomicLong(0);

        @Override
        Element next() {
            int total = totalWeight();
            long cur = current.getAndIncrement() % total;
            for (int i = 0; i < elements.size(); i++) {
                cur -= elements.get(i).weight;
                if (cur < 0) {
                    return elements.get(i);
                }
            }
            return elements.get(0);
        }
    }


    private static class Random extends LoadBalance {

        @Override
        Element next() {
            int total = totalWeight();
            int cur = ThreadLocalRandom.current().nextInt(total);
            int temp = total;
            for (int i = 0; i < elements.size(); i++) {
                cur -= elements.get(i).weight;
                if (cur < 0) {
                    return elements.get(i);
                }
            }
            return elements.get(0);
        }
    }


    private abstract static class LoadBalance {
        List<Element> elements;


        public void setElements(List<Element> elements) {
            this.elements = elements;
        }


        abstract Element next();

        public int totalWeight() {
            return elements.stream().mapToInt(o -> o.weight).sum();
        }

    }

    private static class Element {
        public String name;
        public int weight;

        public Element(String name, int weight) {
            this.name = name;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
