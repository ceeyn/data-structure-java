package segmentTree;

/**
 * @ClassName BinaryIndexedTree1
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/23 15:00
 * @Version 1.0
 */
public class BinaryIndexedTree1 {
    int[] nums;
    int[] tree;
    public BinaryIndexedTree1(int[] nums){
        int n = nums.length;
        this.nums = new int[n]; // 使 update 中算出的 delta = nums[i]
        // 树状数组中每个位置 i 的元素代表了原始数组中一段区间的累加和，
        // 这个区间的长度由 i 的最低位的二进制表示的1所决定。
        // tree[i]是前lowbit(i)个元素的和
        // tree[i]是长度为lowbit(i),且以i结尾的区间，例如tree[12] 是 长度为4:[9,12]以nums[12]为结尾的和
        tree = new int[n + 1];
        for (int i = 0; i < n; i++){
            update(i,nums[i]);
        }
    }

    public void update(int index, int val) {
        int change_val = val - nums[index];
        for(int i = index + 1; i < nums.length; i += i & -i){
            tree[i] += change_val;
        }
    }

    public int preSum(int i) {
        int sum = 0;
        for(; i >= 0; i -= i & -i){
            sum += tree[i];
        }
        return sum;
    }
    public int sumRange(int left, int right) {
        return preSum(right + 1) - preSum(left);
    }


}
