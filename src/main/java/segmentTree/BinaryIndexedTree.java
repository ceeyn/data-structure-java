package segmentTree;

/**
 * @ClassName BinaryIndexedTree
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/21 11:02
 * @Version 1.0
 */
// 110 lowbit(10) -> 2 [1,4] [5,6]
    // 11 lowbit(1) -> 1 [1,2] [3,3] 1000
    // 8 [1] [7]
    // 线段树所有层第偶数个数字都是没用的，删去线段树所有层第偶数个数字得到
public  class BinaryIndexedTree {
    public static class NumArray {
        private int[] nums;
        private int[] tree;

        public NumArray(int[] nums) {
            int n = nums.length;
            this.nums = new int[n]; // 使 update 中算出的 delta = nums[i]
            // 树状数组中每个位置 i 的元素代表了原始数组中一段区间的累加和，
            // 这个区间的长度由 i 的最低位的二进制表示的1所决定。
            // tree[i]是前lowbit(i)个元素的和
            // tree[i]是长度为lowbit(i),且以i结尾的区间，例如tree[12] 是 长度为4:[9,12]以nums[12]为结尾的和
            tree = new int[n + 1];
            for (int i = 0; i < n; i++) {
                update(i, nums[i]);
            }
        }

        /**
         * 。这里 i & -i 是获取 i 的二进制表示中最低位的1所代表的值，
         * 它指示了当前索引覆盖的区间长度。通过加上这个值，我们可以向上移动到树状数组中负责更大区间的节点，
         * 确保所有相关的前缀和都得到更新，
         * @param index
         * @param val
         */
        // tree[i]的上方刚好是tree[i+lowbit(i)]，因此修改nums[i]，会影响tree[i+1],影响tree[i+1+lowbit(i)]
        public void update(int index, int val) {
            int delta = val - nums[index];
            nums[index] = val;
            for (int i = index + 1; i < tree.length; i += i & -i) {
                tree[i] += delta;
            }
        }

        private int prefixSum(int i) {
            int s = 0;
            for (; i > 0; i -= i & -i) { // i -= i & -i 的另一种写法
                s += tree[i];
            }
            return s;
        }

        public int sumRange(int left, int right) {
            return prefixSum(right + 1) - prefixSum(left);
        }
    }

    public static void main(String[] args) {
//        NumArray nm = new NumArray(new int[] {3,2,4,3,1});
        NumArray nm = new NumArray(new int[] {1,2,3,4,5});
        //      15        15
        //    6           6
        //  3       9    3
        // 1 2 3  4 5    1 3 5
        //
        int[] tree = nm.tree;
        // 1,2,3,4,5 -> 6个区间[1,4]，[5,5],[1,2] [3,3] [1,2] [1,1]
        // tree[i] 1.lowbit(i) == 1 ,tree[i] = nums[i-1];2.lowbit(i)!=1 前i个元素和
        // 3,2,4,3,1 -> 0 3 5 4 12 1 前5个累加和 ，tree[5]+tree[4]

        for (int i = 0; i < tree.length; i++) {
            System.out.println(tree[i]);
        }
        System.out.println(nm.prefixSum(3));
    }
}
