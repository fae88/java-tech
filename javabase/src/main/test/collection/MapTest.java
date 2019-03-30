package collection;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapTest {

    public static void main(String[] args) {
        testSortedMap();
    }

    public static void testSortedMap() {

        List<String> list = Arrays.asList("B", "A", "A", "C", "B", "A", "D", "E", "F", "E", "G", "G", "G", "G");

        Map<String,Long> result =list.stream().collect(Collectors.groupingBy(String::toString, Collectors.counting()));

        Map<String, Long> map = crunchifySortByKey(result);

        for (Map.Entry entrySet: map.entrySet()) {
            System.out.println(entrySet.getKey() + ": " + entrySet.getValue());
        }

    }

    // Let's sort HashMap by Key
    public static <K extends Comparable<? super K>, V> Map<K, V> crunchifySortByKey(Map<K, V> crunchifyMap) {

        crunchifyMap = crunchifyMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v2, LinkedHashMap::new));

        return crunchifyMap;
    }

    // Let's sort HashMap by Value
    public static <K, V extends Comparable<? super V>> Map<K, V> crunchifySortByValue(Map<K, V> crunchifyMap) {

        Map<K, V> crunchifyResult = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> sequentialStream = crunchifyMap.entrySet().stream();

        // comparingByValue() returns a comparator that compares Map.Entry in natural order on value.
        sequentialStream.sorted(Map.Entry.comparingByValue()).forEachOrdered(c -> crunchifyResult.put(c.getKey(), c.getValue()));
        return crunchifyResult;
    }
}
