package collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
    }
}
