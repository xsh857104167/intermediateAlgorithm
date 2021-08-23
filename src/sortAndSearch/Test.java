package sortAndSearch;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Murphy Xu
 * @create 2021-08-20 21:06
 */
public class Test {

    @org.junit.Test
    public void test(){
        int[] stones = {2, 7, 4, 1, 8, 1};
        
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i : stones) {
            heap.add(i);
        }

//        for (int i = 0; i < stones.length; i++) {
//            System.out.println(heap.poll());
//        }
    }
}
