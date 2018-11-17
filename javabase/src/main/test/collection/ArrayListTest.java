package collection;

import jdk.Factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * arraylist test
 */
public class ArrayListTest {

    public static List<String> list = Arrays.asList("a", "b", "c", "d");

    public static void main(String[] args) {

        testGetAll(list);
    }

    /**
     * get ALL elements ways
     * @param list
     */
    private static void testGetAll(List<String> list) {
        //1st iterator

        Iterator iter = list.iterator();
        while (iter.hasNext())
        {
            System.out.println(iter.next());
        }

        //2nd random get

        for(int i = 0; i < list.size(); i ++) {
            System.out.println(list.get(i));
        }

        //3rd for get

        for (String s: list) {
            System.out.println(s);
        }

        Factory.print();

        list.stream().map(String::toUpperCase).collect(Collectors.toList());

        list.stream().peek(s -> {
            System.out.println("it is " + s);
        }).collect(Collectors.toList());

        list.stream().map(String::toUpperCase);
    }

    public void testLambda() {
        List<Integer> numList = Arrays.asList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        List<Integer> numsWithoutNullList = numList.stream()
                .filter(num -> num != null)
                .collect(() -> new ArrayList<Integer>(),
                        (list, item) -> list.add(item),
                        (list1, list2) -> list1.addAll(list2));


        "foobar:foo:bar"
                .chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char)c))
                .sorted()
                .collect(Collectors.joining());
    }
}
