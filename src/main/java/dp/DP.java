package dp;

import java.util.Arrays;

/**
 * @ClassName dp.DP
 * @Description
 * 递归问题：若能找到和原问题1.相似的子问题，2.并建立起联系，可用递归解决
 *
 * 【dp的定义要求无后效性：为了保证计算子问题能够按照顺序、不重复地进行，动态规划要求【已经求解的子问题不受后续阶段的影响】。】
 * lc 53. 最大子数组和
 *      如何定义子问题（如何定义状态），把不确定的因素确定下来，进而把子问题定义清楚，把子问题定义得简单。
 *      动态规划的思想通过解决了一个一个简单的问题，进而把简单的问题的解组成了复杂的问题的解
 *      我们 不知道和最大的连续子数组一定会选哪一个数，那么我们可以求出 所有 经过输入数组的某一个数的连续子数组的最大和
 *      例如，示例 1 输入数组是 [-2,1,-3,4,-1,2,1,-5,4] ，我们可以求出以下子问题
 *      子问题 1：经过 −2-的连续子数组的最大和是多少；
 *      子问题 2：经过 1的连续子数组的最大和是多少；
 *      子问题 3：经过 −3的连续子数组的最大和是多少；
 *      子问题 4：经过 4 的连续子数组的最大和是多少；
 *      子问题 5：经过 −1的连续子数组的最大和是多少；
 *      子问题 6：经过 2的连续子数组的最大和是多少；
 *      子问题 7：经过 1 的连续子数组的最大和是多少；
 *      子问题 8：经过 −5-的连续子数组的最大和是多少；
 *      子问题 9：经过 4的连续子数组的最大和是多少。
 *      一共 9 个子问题，这些子问题之间的联系并没有那么好看出来，这是因为 子问题的描述还有不确定的地方（这件事情叫做「有后效性」，
 *      我们在本文的最后会讲解什么是「无后效性」）。
 *      例如「子问题 3」：经过 −3 的连续子数组的最大和是多少。
 *      「经过 −3的连续子数组」我们任意举出几个：
 *      [-2,1,-3,4] ，−3 是这个连续子数组的第 3 个元素；
 *      [1,-3,4,-1] ，−3-是这个连续子数组的第 2 个元素；
 *      我们不确定的是：−3-3−3 是连续子数组的第几个元素。那么我们就把 −3-3−3 定义成连续子数组的最后一个元素。在新的定义下，我们列出子问题如下：
 *      子问题 1：以 −2 结尾的连续子数组的最大和是多少；
 *      子问题 2：以 1结尾的连续子数组的最大和是多少；
 *      我们加上了「结尾的」，这些子问题之间就有了联系。我们单独看子问题 1 和子问题 2：
 *      子问题 1：以 −2结尾的连续子数组的最大和是多少；
 *      以 −2结尾的连续子数组是 [-2]，因此最大和就是 −2。
 *      子问题 2：以 1结尾的连续子数组的最大和是多少；
 *      以 1结尾的连续子数组有 [-2,1] 和 [1] ，其中 [-2,1] 就是在「子问题 1」的后面加上 1 得到。−2+1=−1<1-2 + 1 = -1 < 1−2+1=−1<1
 *      ，因此「子问题 2」 的答案是 111。
 *      大家发现了吗，如果编号为 i 的子问题的结果是负数或者 0 ，那么编号为 i + 1 的子问题就可以把编号为 i 的子问题的结果舍弃掉
 *      （这里 i 为整数，最小值为 1 ，最大值为 8），这是因为：
 *      一个数 a 加上负数的结果比 a 更小；
 *      一个数 a 加上 0的结果不会比 a 更大；
 *      而子问题的定义必须以一个数结尾，因此如果子问题 i 的结果是负数或者 0，那么子问题 i + 1 的答案就是以 nums[i] 结尾的那个数
 *
 * 这个条件也被叫做「无后效性」。换言之，动态规划对状态空间的遍历构成一张有向无环图，遍历就是该有向无环图的一个拓扑序。
 * 有向无环图中的节点对应问题中的「状态」，图中的边则对应状态之间的「转移」，转移的选取就是动态规划中的「决策」
 * 「有向无环图」「拓扑序」表示了每一个子问题只求解一次，以后求解问题的过程不会修改以前求解的子问题的结果；
 * 换句话说：如果之前的阶段求解的子问题的结果包含了一些不确定的信息，导致了后面的阶段求解的子问题无法得到，或者很难得到，
 * 这叫「有后效性」，我们在当前这个问题第 1 次拆分的子问题就是「有后效性」的
 *
 * 解决「有后效性」的办法是固定住需要分类讨论的地方，记录下更多的结果。在代码层面上表现为：
 * 状态数组增加维度，例如：「力扣」的股票系列问题；
 * 把状态定义得更细致、准确，例如：前天推送的第 124 题：状态定义只解决路径来自左右子树的其中一个子树。
 *
 *
 *
 * 作者：liweiwei1419
 * 链接：https://leetcode.cn/problems/maximum-subarray/solutions/9058/dong-tai-gui-hua-fen-zhi-fa-python-dai-ma-java-dai/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 *
 *
 * @Author @hzp
 * @Date 2024/5/8 13:52
 * @Version 1.0
 */
public class DP {
    // lc 64 最小路径和
    class GirdDP{
        int[][] grid1;
        int[][] memo1;
        public int dfs1(int i, int j){
            // i <0 或者 j < 0出界没价值
            if(i < 0 || j < 0) return Integer.MAX_VALUE;
            if( i == 0 && j == 0 ) return  grid[0][0];
            if(memo1[i][j] != -1) return memo1[i][j];
            memo1[i][j]  = Math.min(dfs1(i-1,j), dfs1(i,j-1)) + grid1[i][j];
            return memo1[i][j];
        }

        // lc 1594
        int[][] grid;
        long[][][] memo; // 修改为三维数组，用于存储最小值和最大值
        public long[] dfs(int i, int j) {
            if (i >= grid.length || j >= grid[0].length) return new long[]{1, -1}; // 超出边界时返回1和-1
            if (memo[i][j][0] != -1 && memo[i][j][1] != -1) return memo[i][j]; // 如果已经计算过，则直接返回memo中的值
            if (i == grid.length - 1 && j == grid[0].length - 1) return new long[]{grid[i][j], grid[i][j]}; // 到达终点

            long[] down = dfs(i + 1, j);
            long[] right = dfs(i, j + 1);
            long[] res = new long[2];

            long minVal = Math.min(down[0], right[0]) * grid[i][j];
            long maxVal = Math.max(down[1], right[1]) * grid[i][j];

            if (grid[i][j] < 0) {
                res[0] = Math.max(down[1], right[1]) * grid[i][j]; // 最小值
                res[1] = Math.min(down[0], right[0]) * grid[i][j]; // 最大值
            } else {
                res[0] = minVal; // 最小值
                res[1] = maxVal; // 最大值
            }

            memo[i][j] = res; // 将结果存储在memo中
            return res;
        }
    }



    // lc122. 买卖股票的最佳时机 II
    int[] prices;
    // 两个状态，一共4种转移,表示状态之间转移关系叫做状态机
    // 一个i天，一个hold，
    // 子问题：到第i天结束时，isHold【true为持有，false为卖出】股票所获利润,
    // 基于选与不选
    // 选prices[i]:1.买入dp[i-1,0]- prices[i]; 2.卖出dp[i-1,1]+ prices[i]
    // 不选prices[i]：1.dp[i-1,0]; 2.dp[i-1,1]
    // 因此：dp(i,0) = Math.max(dp(i-1,0),dp(i-1,1) + prices[i]);
    // 当前操作；分情况讨论：持有股票有两种可能：1.i-1结束时持有且什么不做；2.i-1结束时买入 没有持有股票则：1.什么不做，2,买入
    // 下一个子问题：i-1结束时【i-1的结束就是i的开始】所获最大利润
    public int dpMachine(int i, int is_hold) {
        if(i < 0) return is_hold == 1?Integer.MIN_VALUE : 0;
        if(is_hold == 1){
            return Math.max(dpMachine(i-1,is_hold),dpMachine(i-1,0) - prices[i]);
        }else{
            return Math.max(dpMachine(i-1,is_hold),dpMachine(i-1,1) + prices[i]);
        }
    }



    char[] high_s;
    char[] low_s;
    int limit;
    String s;
    int diff;
    long[] memo;
//    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
//        this.high_s = String.valueOf(finish).toCharArray();
//        this.limit = limit;
//        this.s = s;
//        String oder_low = "0".repeat(high_s.length - String.valueOf(start).length()) + String.valueOf(start);
//        this.low_s = oder_low.toCharArray();
//        this.diff = high_s.length - s.length();
//        memo = new long[high_s.length];
//        Arrays.fill(memo, -1);
//        return dfs(0,true,true);
//    }

    public long dfs(int i, boolean lowLimit, boolean highLimit) {
        if(i == high_s.length) return 1;
        if (!lowLimit && !highLimit && memo[i] != -1) {
            return memo[i]; // 之前计算过
        }
        int high = highLimit? high_s[i]-'0':9;
        int low = lowLimit? low_s[i]-'0':0;
        long cur_res = 0;
        if(i < diff){
            for(int d = low; d <= Math.min(high,limit); d++){
                cur_res += dfs(i+1,lowLimit && d == low, highLimit && d == high);
            }
        }else{
            int x = s.charAt(i-diff)-'0';
            if(x >= low && x <= Math.min(high,limit)){
                cur_res = dfs(i+1,lowLimit && x == low, highLimit && x == high);
            }

        }
        if (!lowLimit && !highLimit) {
            memo[i] = cur_res; // 记忆化 (i,false,false)
        }
        return cur_res;

    }


}
