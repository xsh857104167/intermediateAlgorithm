package additional;

import java.util.*;

/**
 * 拼车
 * @author Murphy Xu
 * @create 2021-09-12 22:44
 */
public class CarPooling {
    /**
     * HashMap
     * 错误
     * 原因在于没考虑先下后上
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int len = trips.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int passengersNum = trips[i][0];
            for (int j = trips[i][1]; j <= trips[i][2]; j++) {
                map.put(j, map.getOrDefault(j, 0) + passengersNum);
            }
        }
        for (int key : map.keySet()) {
            if (map.get(key) > capacity){
                return false;
            }
        }
        return true;
    }

    /**
     * 堆，优先队列
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling2(int[][] trips, int capacity) {
        // 小根堆
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        // 对上车顺序进行排序
        Arrays.sort(trips, Comparator.comparingInt(o -> o[1]));
        for (int[] trip : trips){
            // 先上车
            capacity -= trip[0];
            // 位置不够再下车
            if (capacity < 0){
                while (!heap.isEmpty() && heap.peek()[2] <= trip[1]){
                    capacity += heap.poll()[0];
                }
                // 如果可以下车的都下了，还坐不开就返回false
                if (capacity < 0)
                    return false;
            }
            heap.offer(trip);
        }
        return true;
    }

    /**
     * 差分数组 + 前缀和
     * 1ms,96.50%; 38.2MB, 60.18%
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling3(int[][] trips, int capacity) {
        final int max = 1000; // 最远距离处
        int[] diff = new int[max + 1];
        for (int[] trip : trips) {
            diff[trip[1]] += trip[0];
            diff[trip[2]] -= trip[0];
        }
        int prefixSum = 0;
        for (int i = 0; i <= max; i++) {
            prefixSum += diff[i];
            if (prefixSum > capacity){
                return false;
            }
        }
        return true;
    }
}
