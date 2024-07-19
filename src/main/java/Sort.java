import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @ClassName Sort
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/6 08:53
 * @Version 1.0
 */
public class Sort {

    // 用于辅助合并有序数组
    private static int[] temp;


    public void mergeSort(int[] nums,int low, int high) {
        if(low >= high) return;
        int mid = low + (high - low) / 2;
        // 先对左半部分数组 nums[lo..mid] 排序
        mergeSort(nums,low,mid);
        // 再对右半部分数组 nums[mid+1..hi] 排序
        mergeSort(nums,mid+1,high);
        // 将两部分有序数组合并成一个有序数组
        merge(nums,low,mid,high);
    }
    // 将[low,mid] [mid+1,high]合并
    public void merge(int[] nums, int low, int mid, int high){
        // 先把 nums[lo..hi] 复制到辅助数组中
        // 以便合并后的结果能够直接存入 nums
        for(int i = low; i <= high; i++){
            temp[i] = nums[i];
        }
        // 数组双指针技巧，合并两个有序数组
        int i = low, j = mid;
        for(int p = low; p <= high; p++){
            if(i == mid+1) {
                // 左半边数组已全部被合并
                nums[p] = temp[j++];
            }else if(j == high+1){
                nums[p] = temp[i++];
            }else if(temp[j] > temp[i]){
                nums[p] = temp[i++];
            }else {
                nums[p] = temp[j++];
            }
        }
    }

    // 堆排序，703. 数据流中的第 K 大元素
    class KthLargest {
        private PriorityQueue<Integer> queue;
        private int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.queue = new PriorityQueue<>(); // 使用小顶堆
            for (int num : nums) {
                add(num); // 直接使用下面定义的add方法
            }
        }
        // 使用自带排序功能的数据结构——堆。
        // 小根堆顶是第k大元素，堆内元素个数达到 k 个：
        // 加入项小于等于堆顶元素：加入项排在第 k 大元素的后面。直接忽略
        // 加入项大于堆顶元素：将堆顶元素弹出，加入项加入优先队列，调整堆
        // 堆内元素个数不足 k 个，将加入项加入优先队列

        public int add(int val) {
            if (queue.size() < k) {
                queue.offer(val);
            } else if (val > queue.peek()) {
                queue.poll();
                queue.offer(val);
            }
            return queue.peek();
        }
    }

    class QuickSort{
        int[] nums;
        public void quickSort(int[] nums, int left, int right){
            if(left < right){
                int p = partition(nums,left,right);
                quickSort(nums,left,p-1);
                quickSort(nums,p+1,right);
            }
        }
        // pivot_val是第一个元素的值，以第一个元素为分割，将nums分为小于等于pivot_val，大于等于pivot_val两部分，返回分割后的下标
        public int partition(int[] nums, int left, int right){
            int pivot_val = nums[left];
            // left先指向pivot,然后开始移动right，找到第一个小于pivot_val的值，然后移动left，依次循环，最后将pivot放在left和right相遇的位置
            // 大于等于pivot_val不移动
            while(left < right){
                while(left < right && nums[right] >= pivot_val) right--;
                nums[left] = nums[right];
                while(left < right && nums[left] <= pivot_val) left++;
                nums[right] = nums[left];
            }
            nums[left] = pivot_val;
            return left;
        }
     }
        int[] nums;
     // 第二种快排写法：假设有以下数组和分区过程：
    //nums = [3, 2, 1, 5, 6, 4]
    //pivot = 3 (nums[0])
    //如果在分区过程中使用 nums[l] = pivot;，则可能导致 pivot 放在不正确的位置
     public int partition(int l, int r) {
         int pivot = nums[l];
         int originalLeft = l; // 记住最初的左边界
         while (l < r) {
             while (l < r && nums[r] >= pivot) r--;
             while (l < r && nums[l] <= pivot) l++;
             if (l < r) {
                 swap(l, r);
             }
         }
         // 将pivot放到正确位置
         swap(originalLeft, r);
         return r;
     }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
