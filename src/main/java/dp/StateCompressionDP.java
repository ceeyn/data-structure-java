package dp;

import java.util.Arrays;

/**
 * @ClassName StateCompressionDP
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/29 16:29
 * @Version 1.0
 */
// 表示已经排列好的元素（下标）集合为s,且上一个填的元素（下标）为i,和题目有关的最优值。通过枚举当前位置要填的元素j来转移
public class StateCompressionDP {
    int[] nums;
    int n;
    public int numSquarefulPerms(int[] nums) {
        Arrays.sort(nums); // 排序以方便处理重复元素
        this.nums = nums;
        this.n = nums.length;
        int ans = 0;
        for(int i = 0; i < n; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) { // 避免重复计算
                ans += dfs(1 << i, i);
            }
        }
        return ans;
    }
    // 表示已经排列好的元素（下标）集合为s,且上一个填的元素（下标）为i,和题目有关的最优值。通过枚举当前位置要填的元素j来转移
    public int dfs(int s ,int i){
        if(s == (1 << n) - 1) {
            return 1;
        }
        int cur_res = 0;
        // 枚举当前填的元素下标
        for (int j = 0; j < n; j++) {
            if ((s & (1 << j)) == 0 && isSquare(nums[i], nums[j])) {
                if (j == 0 || nums[j] != nums[j - 1] || (s & (1 << (j - 1))) > 0) { // 同样避免重复
                    cur_res += dfs(s | (1 << j), j);
                }
            }
        }
        return cur_res;
    }

    public boolean isSquare(int a, int b) {
        double sqrt = Math.sqrt(a + b);
        return sqrt == (int) sqrt;
    }
}
