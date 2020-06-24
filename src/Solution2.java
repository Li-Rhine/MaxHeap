import java.util.*;
import java.util.PriorityQueue;

/**
 * 使用Java自带的PriorityQueue优先队列， 默认是最小堆
 */
public class Solution2 {

    private class Freq {
        int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

    }


    private class FreqComparator implements Comparator<Freq> {

        @Override
        public int compare(Freq a, Freq b) {
            return a.freq - b.freq;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num));
            }else {
                map.put(num, 1);
            }
        }

        PriorityQueue<Freq> pq = new PriorityQueue<>(new FreqComparator());
        for (int key: map.keySet()) {
            if (pq.size() < k) {
                pq.add(new Freq(key, map.get(key)));
            }else if (map.get(key) > pq.peek().freq){
                // 前k个元素频次最小的元素还大的话，就出队一个（最小堆）
                pq.remove();
                pq.add(new Freq(key, map.get(key)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove().e);
        }
        return res;
    }
}
