package presum;

import java.util.TreeMap;

/**
 * @ClassName presum.Diff
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/17 18:05
 * @Version 1.0
 */
public class Diff {

    class TwoDiff{
        int n;
        // 多一个最后一行最后一列，方便不用判断 [if(d2+1 < n) d[r2+1][c2+1] += 1; // 对应右下角点增加以抵消之前的减法]
        int[][] d = new int[n+1][n+1];
        // 多一个一行一列，方便求前缀和还原数组,不写就不能用i+1，d[i + 1][j + 1] += d[i + 1][j] + d[i][j + 1] - d[i][j];
        int[][] d1 = new int[n+2][n+2];
        public int[][] rangeAddQueries(int n, int[][] queries) {
            // 多一个最后一行最后一列
            int[][] d = new int[n+1][n+1]; // 使用 n+1 x n+1 的差分数组

            for (int[] q : queries) {
                int r1 = q[0], c1 = q[1], r2 = q[2], c2 = q[3];
                d[r1][c1] += 1; // 对应起始点增加
                d[r1][c2+1] -= 1; // 对应起始点右边界外减少
                d[r2+1][c1] -= 1; // 对应起始点下边界外减少
                d[r2+1][c2+1] += 1; // 对应右下角点增加以抵消之前的减法
            }

            // 使用前缀和原地更新差分数组来还原最终的矩阵
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 因为我们是从 0 开始遍历的，所以更新公式需要做相应的调整
                    if (i > 0) d[i][j] += d[i-1][j];
                    if (j > 0) d[i][j] += d[i][j-1];
                    if (i > 0 && j > 0) d[i][j] -= d[i-1][j-1];
                }
            }

            // 这里我们创建一个新的数组来储存最终结果
            int[][] result = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result[i][j] = d[i][j];
                }
            }

            return result;
        }

        public int[][] rangeAddQueries1(int n, int[][] queries) {
            int[][] d = new int[n+2][n+2]; // 使用 n+2 x n+2 的差分数组来处理边界情况

            for (int[] q : queries) {
                int r1 = q[0], c1 = q[1], r2 = q[2], c2 = q[3];
                d[r1+1][c1+1] += 1; // 对应起始点增加
                d[r1+1][c2+2] -= 1; // 对应起始点右边界外减少
                d[r2+2][c1+1] -= 1; // 对应起始点下边界外减少
                d[r2+2][c2+2] += 1; // 对应右下角点增加以抵消之前的减法
            }

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    d[i + 1][j + 1] += d[i + 1][j] + d[i][j + 1] - d[i][j];
                }
            }


            // 从差分数组d中提取出原矩阵的值
            int[][] result = new int[n][n];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    result[i-1][j-1] = d[i][j];
                }
            }

            return result;
        }
    }

    static int[] a;
//    static int[] diff;
    // 你有一个长为 n 的数组 a，一开始所有元素均为 0。
    // 给定一些区间操作，其中 queries[i] = [left, right, x]，
    // 你需要把子数组 a[left], a[left+1], ... a[right] 都加上 x。
    // 下标[0,n]区间的数组，现在想让[left,right]都加x
    // left 加 x，即[left,n]所有元素都加x,为了消除对后面元素的影响，[right+1,n]需都减x,即right+1需减x
    // 返回所有操作执行完后的数组 a。
    static int[] solve(int n, int[][] queries) {
        int[] diff = new int[n]; // 差分数组
        for (int[] q : queries) {
            int left = q[0], right = q[1], x = q[2];
            diff[left] += x;
            if (right + 1 < n) {
                diff[right + 1] -= x;
            }
        }
        for(int i : diff) System.out.println("标记后，diff数组："+i);

        // 写法一：
//        for (int i = 1; i < n; i++) {
//            //
//            diff[i] += diff[i - 1]; // 直接在差分数组上复原数组 a
//            //
//        }

        // 写法二：
        int sum = 0;
        for(int i = 0; i < n; i++){
//            System.out.println("=============================");
//            System.out.println("i："+i);
//            System.out.println("diff[i-1]："+diff[i-1]);
            sum += diff[i];
            diff[i] = sum;
//            System.out.println("sum："+sum);

//            System.out.println("从diff数组还原数组："+diff[i]);
//            System.out.println("=============================");
        }

//        for(int i : diff) System.out.println("从diff数组还原数组："+i);
        // 先不管原数组，或假设原数组都为0，计算出diff，再还原原数组
//        int sum = 0;
//        for(int i = 0; i < n; i++){
//            sum += diff[i];
//            a[i] = a[i] + sum;
//        }
        return diff;
    }



    public void testTwoDiff(int[][] data,int i1, int j1, int i2, int j2, int ele){
        int m = data.length , n = data[0].length;
        int[][] d = new int[m+1][n+1];
        // 加标记
        d[i1][j1] += ele;
        d[i2+1][j1] -=ele;
        d[i1][j2+1] -= ele;
        d[i2+1][j2+1] += ele;
    }

    public static int[] testSumDiff(int[] nums,int left, int right,int ele){
        int n = nums.length;
        // 存储标记,最后一个长为right-left+1的数组的标记刚好是n+1
        int[] diff = new int[n+1];

//        for(int i = 1; i < n; i++){
//            diff[i] = nums[i] - nums[i-1];
//        }
        int sum = 0;
        diff[left] += ele;
        diff[right+1] -= ele;
        for(int i = 0 ; i < nums.length; i++){
            sum += diff[i];
            nums[i] += sum;
        }
        return nums;

    }

    public void testTreeMapDiff(int left, int right,int ele){
        TreeMap<Integer, Integer> diff = new TreeMap<>();
        diff.put(left, diff.getOrDefault(left, 0 )+ele);
        diff.put(right+1, diff.getOrDefault(right, 0 )-ele);
    }
    // 1,3,5,7
    // 1,-1,0,0
    // sum: 1 0 5 7
    //      2 3 5 7
    // a[n]
    // 必须
    // 写法一：创建diff[n]，diff[0] = a[0],当i大于等于1时，diff[i] = a[i] - a[i-1]
    // 写法二：创建diff
    // 1 2 3 4 5 -> {1,2,1}
    // 0 1 0 -1 0
    // 1 3 4 4 5
    public static void main(String[] args) {
        a = new int[]{1,2,3,4,5};
//        diff = new int[]{1,1,1,1,1};
        int[] a1 = solve(5,new int[][]{{1,2,1}});
        for(int i : a1) System.out.println(i);



//        int[] a2 = testSumDiff(a,4,4,1);
//        for(int i : a2) System.out.println(i);

    }

    // lc 2772，https://www.bilibili.com/video/BV1XW4y1f7Wv/，
    // 1.计算原数组+标记sum【当前数组的值】，2.计算当前标记和sum，3.加标记【diff】
    // sum 依赖于 diff 依赖于 数组的值，因此先算 数组的值x【第一次已知】  再算 diff,再算sum
    // 数组值-> diff -> sum -> 数组值..... 循环依赖，先算sum -> 数组值
    public boolean checkArray(int[] nums, int k) {
        // [2,2,3,1,1,0], k = 3
        // 1.sum+原数组：0 0 1 1 1 0 标记和+原数组，就是对原数组操作后的值
        // 1.标记d  -2 0 0 2 0 0 0
        //2.标记和sum-2 -2 -2 0 0 0
        //是的，在处理涉及差分数组和累加值的算法时，确实存在一个计算顺序的规律。这种算法的核心在于正确地维护和更新差分数组以及累加值，以便在遍历过程中正确地反映每个元素的最终状态。以下是一般的计算顺序规律：
        //更新累加值：在处理当前元素之前，首先更新累加值 sum。累加值是一个动态的变量，
        // 【它随着遍历的进行而累积之前所有的差分变化。这一步是必要的，因为它保证了当前元素能够反映之前所有操作的总效果。】
        //【应用累加值到当前元素：使用更新后的累加值来更新当前元素。这时，当前元素的值 x 应该加上累加值 sum，即 x += sum】。
        // 这样，我们得到的 x 就是考虑了之前所有操作后的实际值。
        //根据当前元素的值更新差分数组：如果当前元素 x 需要操作来达到目标状态（例如变为0），那么这个操作会影响从当前元素开始的连续 k 个元素。
        // 这时，我们需要在差分数组 diff 中记录这个操作，通常是在当前位置做减法，在 k 个元素后做加法，以此来维持操作的连续性。
        //将当前操作的影响累加到累加值：在更新了差分数组后，【将当前操作的影响即时地累加到累加值 sum 中】。
        // 这是为了在下一个迭代中使用，确保累加值能够反映所有到目前为止的操作。
        //遵循这个顺序，我们就能够保证在遍历每个元素时，都能正确地计算其应有的值，并且差分数组和累加值也能够正确地更新。
        // 这种方法有效地减少了直接修改数组元素带来的复杂性和性能开销，尤其是在需要频繁、大量地修改连续多个元素的值时。
        // 2.0 0 0 0 0 0 0 0
        // 2.d -2 0 -1 2 0 1 0
        //2.sum -2 -2 -3 -1 -1 0 0
        int n = nums.length;
        // 存储标记,最后一个长为k的数组的标记刚好是n+1
        int[] diff = new int[n+1];
        // 存储标记和
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += diff[i];
            int x = nums[i];
            // sum+原数组
            x += sum;
            if(x == 0) continue;
            if(x < 0) return false;
            if(i > n-k) return false;
            if(x > 0) {
                // 加标记
                sum += -x;
                diff[i+k] -= -x;
            }
        }
        return true;
    }
}
