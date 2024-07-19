package dp;

import java.util.Arrays;

/**
 * @ClassName SequenceDP
 * @Description
 *
 * 划分型dp，dfs(i):以第i个数为结尾是否能有效划分，一般定义f[i]表示长为i的前缀a[：i]是否能划分，
 * 枚举最后一个子数组的左端点L，从f[L]转移到f[i]，考虑a[L:j]是否满足要求
 * 序列 DP
 * 将字符串 s 长度记为 n，wordDict 长度记为 m。为了方便，我们调整字符串 s 以及将要用到的动规数组的下标从 1 开始。
 *
 * 定义 【f[i]为考虑前 i 个字符】，能否使用 wordDict 拼凑出来：当 f[i]=true 代表 s[1...i]能够使用 wordDict 所拼凑，反之则不能。
 *
 * 不失一般性考虑 f[i]该如何转移：由于 f[i] 需要考虑 s[1...i] 范围内的字符，若 f[i] 为 True
 * 说明整个 s[1...i]都能够使用 wordDict 拼凑，自然也包括最后一个字符 s[i] 所在字符串 sub。
 *
 * 我们可以【枚举最后一个字符所在字符串的左端点 j】，若 sub=s[j...i]在 wordDict 中出现过，并且 f[j−1]=True，
 * 说明 s[0...(j−1)]能够被拼凑，并且子串 sub 也在 wordDict，可得 f[i] = True。
 * 为了快速判断某个字符是否在 wordDict 中出现，我们可以使用 Set 结构对 wordDict[i]进行转存。
 *
 *
 * 这里简单说下「线性 DP」和「序列 DP」的区别。
 *
 * 线性 DP 通常强调「状态转移所依赖的前驱状态」是由给定数组所提供的，即拓扑序是由原数组直接给出。更大白话来说就是通常有 f[i][...]依赖于 f[i−1][...]。
 *
 * 这就限定了线性 DP 的复杂度是简单由「状态数量（或者说是维度数）」所决定。
 *
 * 序列 DP 通常需要结合题意来寻找前驱状态，即需要自身寻找拓扑序关系（例如本题，需要自己通过枚举的方式来找左端点，从而找到可转移的前驱状态 f[j−1]）。
 *
 * 这就限定了序列 DP 的复杂度是由「状态数 + 找前驱」的复杂度所共同决定。也直接导致了序列 DP 有很多玩法，
 * 往往能够结合其他知识点出题，来优化找前驱这一操作，通常是利用某些性质，或是利用数据结构进行优化。
 *
 * 作者：宫水三叶
 * 链接：https://leetcode.cn/problems/word-break/solutions/1945055/by-ac_oier-gh00/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @Author @hzp
 * @Date 2024/5/26 10:31
 * @Version 1.0
 */
public class SequenceDP {

    // lc:2369. 检查数组是否存在有效划分
    public class Partition{
        int[] nums;
        Boolean[] memo;
        public boolean validPartition(int[] nums) {
            this.nums = nums;
            int n = nums.length;
            this.memo = new Boolean[n];
            return dfs(n - 1);
        }
        // 以第i个数为结尾是否能有效划分
        public boolean dfs(int i) {
            if (i < 0) return true;
            if (memo[i] != null) return memo[i];

            boolean cur_res = false;
            if (i >= 1 && nums[i - 1] == nums[i]) {
                cur_res |= dfs(i - 2);
            }
            if (i >= 2 && (nums[i] == nums[i - 1] && nums[i - 1] == nums[i - 2])) {
                cur_res |= dfs(i - 3);
            }
            if (i >= 2 && (nums[i] == nums[i - 1] + 1 && nums[i - 1] == nums[i - 2] + 1)) {
                cur_res |= dfs(i - 3);
            }

            memo[i] = cur_res;
            return cur_res;
        }

        /**
         * 132. 分割回文串 II
         */
        String s;
        public int minCut(String s) {
            this.s = s;
            return dfs1(s.length() -1);
        }
        // 以i为结尾的最小划分次数
        public int dfs1(int i){
            if(i <= 0) return 0;
            if(check(s.substring(0,i+1))) return 0;
            int cur_res = Integer.MAX_VALUE;
            // 枚举下一个结尾
            for(int j = i-1; j >= -1; j--){
                String cur_s = s.substring(j+1,i+1);
                if(check(cur_s)){
                    cur_res = Math.min(cur_res,dfs1(j));
                }
            }
            return cur_res + 1;
        }

        public boolean check(String s){
            for(int i = 0, j = s.length() - 1; i < j; i++,j--){
                if(s.charAt(i) != s.charAt(j)) return false;
            }
            return true;

        }
    }

    class PartitionNum{
        /**
         * 将数组分成（恰好/至多）k 个连续子数组，计算与这些子数组有关的最优值。
         * 一般定义f[i][j]表示长为j的前缀a[:j]分成i个连续子数组所得最优解
         * 枚举最后一个子数组的左端点L，从f[i-1][L]转移到f[i][j]，考虑a[L:j]对最优解影响
         *  我的做法：原问题：以i为结尾的划分，子问题：枚举以j为结尾的划分
         *
         */
        int[] arr;
        int k;
        int[] memo;
        public int maxSumAfterPartitioning(int[] arr, int k) {
            this.arr = arr;
            this.k = k;
            int n = arr.length;
            memo = new int[n];
            Arrays.fill(memo,-1);
            return dfs(n-1);
        }
        // 以i为结尾的划分的最大和
        public int dfs(int i){
            if(i < 0) return 0;
            if(memo[i] != -1) return memo[i];
            int cur_res = Integer.MIN_VALUE/2;
            // 枚举下一个结尾j
            for(int j = i - 1; j >= -1 && j >= i-k; j--){
                cur_res = Math.max(returnMax(j+1,i)*(i - j)+dfs(j), cur_res);
                System.out.println("curres:"+cur_res);
            }
            memo[i] =  cur_res;
            return cur_res;
        }

        public int returnMax(int l, int r){
            int res = Integer.MIN_VALUE/2;
            for(int i = l; i <= r; i++){
                res = Math.max(res,arr[i]);
            }
            return res;
        }

        // lc.813. 最大平均值和的分组,dfs(i,k)代表以i为结尾的划分最多为k个的最大平均值和
        int[] arr1;
        int k1;
        double[][] memo1;
        double[] prefixSum;

        public double largestSumOfAverages(int[] nums, int k) {
            this.arr1 = nums;
            this.k1 = k;
            int n = arr1.length;
            memo1 = new double[n][k + 1];
            for (double[] row : memo1) {
                Arrays.fill(row, -1);
            }
            prefixSum = new double[n + 1];

            // 计算前缀和
            for (int i = 0; i < n; i++) {
                prefixSum[i + 1] = prefixSum[i] + arr[i];
            }

            return dfs(n - 1, k);
        }

        // 以i为结尾的划分最多为k个的最大平均值和
        public double dfs(int i, int k) {
            if (i < 0) return 0;
            if (k == 1) return returnAvg(0, i);
            if (memo1[i][k] != -1) return memo1[i][k];

            double cur_res = Double.NEGATIVE_INFINITY;

            // 不划分
            cur_res = Math.max(cur_res, returnAvg(0, i));

            // 划分,枚举下一个结尾j
            for (int j = i - 1; j >= -1; j--) {
                cur_res = Math.max(cur_res, returnAvg(j + 1, i) + dfs(j, k - 1));
            }

            memo1[i][k] = cur_res;
            return cur_res;
        }

        public double returnAvg(int l, int r) {
            return (prefixSum[r + 1] - prefixSum[l]) / (r - l + 1);
        }
    }
}
