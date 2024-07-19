package queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName Queue
 * @Description 单调队列和单调栈相比适用范围更广，单调栈能做的单调队列也能做，
 * 假设遍历顺序是从左到右，不同是在于若只右段添加，删除元素，则选用栈，
 * 若也要求在左端删除元素【往往出现在滑动窗口出窗口时】就选用单调队列
 * @Author @hzp
 * @Date 2024/5/31 15:32
 * @Version 1.0
 */
public class Queue {
    // 单调队列，及时去除队里无用元素，维护队列性质，例如单调递增队列，当入队元素小于队尾，则队尾一直去除，
    // 直到大于当前入队元素，队头元素是最小的，直到超出滑窗范围，
    // lc.2762. 不间断子数组
    public long continuousSubarrays(int[] nums) {
        // int min_window = Integer.MAX_VALUE;
        // 先入先出
        Deque<Integer> min_queue = new ArrayDeque();
        Deque<Integer> max_queue = new ArrayDeque();
        int left = 0;
        long ans = 0;
        for(int i = 0; i < nums.length; i++){
            // 单调递增
            while(!min_queue.isEmpty() && nums[min_queue.getLast()] > nums[i]){
                min_queue.removeLast();
            }
            min_queue.offer(i);
            while(!max_queue.isEmpty() && nums[max_queue.getLast()] < nums[i]){
                max_queue.removeLast();
            }
            max_queue.offer(i);
            while(!max_queue.isEmpty() && !min_queue.isEmpty() &&
                    nums[max_queue.getFirst()] - nums[min_queue.getFirst()] > 2){
                if(left == max_queue.getFirst()){
                    max_queue.poll();
                }
                if(left == min_queue.getFirst()){
                    min_queue.poll();
                }
                left++;
            }
            ans += i - left + 1;

        }
        return ans;
    }
}
