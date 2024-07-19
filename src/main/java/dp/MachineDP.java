package dp;

import java.util.Arrays;

/**
 * @ClassName MachineDP
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/25 23:09
 * @Version 1.0
 */
public class MachineDP {


    class Stock{
        int[] prices;
        int[][] memo;
        public int maxProfit(int[] prices) {
            this.prices = prices;
            int n = prices.length;
            memo = new int[n][2];
            for (int i = 0; i < n; i++)
                Arrays.fill(memo[i], -1); // -1 表示还没有计算过
            return dp(n-1,0);
        }
        // 子问题：到第i天结束时，isHold【true为持有，false为卖出】股票所获利润,
        // 基于选与不选
        // 选prices[i]:1.买入dp[i-1,0]- prices[i]; 2.卖出dp[i-1,1]+ prices[i]
        // 不选prices[i]：1.dp[i-1,0]; 2.dp[i-1,1]
        // 因此：dp(i,0) = Math.max(dp(i-1,0),dp(i-1,1) + prices[i]);

        // 当前操作；分情况讨论：持有股票有两种可能：1.i-1结束时持有且什么不做；2.i-1结束时买入 没有持有股票则：1.什么不做，2,买入
        // 下一个子问题：i-1结束时【i-1的结束就是i的开始】所获最大利润
        public int dp(int i, int isHold){
            if(i < 0)  return isHold == 1? Integer.MIN_VALUE/2:0;
            if(memo[i][isHold] != -1) return memo[i][isHold];
            // 第i天结束有股票，则是第i天开始【i-1结束】有股票什么也不做或第i天开始买入了股票
            if(isHold == 1) {
                return memo[i][1] = Math.max(dp(i-1,1),dp(i-1,0) - prices[i]);
            }
            else return memo[i][0] = Math.max(dp(i-1,0),dp(i-1,1) + prices[i]);

        }
    }
    // 前一天的状态+今天的价格买入/卖出 = 今天的状态
    // 考虑股票问题，假如第i天持有，应当考虑第 i-1 天不持有任何股票的情况，并从第 i 天的价格购入，因此应该是减去第 i 天的价格，而不是第i-1天的
    private static int getStockMax(int[][] price) {
        // N是股票数量，M是天数
        int N = price.length;
        int M = price[0].length;
        // i = 0 是第1支股票，j=0是第1天
        int[][] dp = new int[N+1][M];
        for(int i = 0; i < N; i++) {
            dp[i][0] = -price[i][0];
        }
        dp[N][0] = 0;
        for(int j = 1; j < M; j++) {
            for(int i = 0; i < N; i++) {
                // 1.枚举第j天持有股票
                // 1.1 是前一天本来就持有
                dp[i][j] = dp[i][j-1];
                for(int k = 0; k <= N; k++) {
                    // 1.2 是前一天没有，买入了
                    if(k == N) {
                        dp[i][j] = Math.max(dp[i][j], dp[N][j-1] - price[i][j]);
                    }else{
                        // 1.3 是前一天有别的，卖出别的，买入了当前i
                        dp[i][j] = Math.max(dp[i][j],dp[k][j-1] + price[k][j] - price[i][j]);
                    }
                }
            }
            // 2.枚举第j天不持有股票
            // 2.1 是前一天本来就没有
            dp[N][j] = dp[N][j-1];
            // 2.2 前一天有其他，卖出了
            for(int k = 0; k < N; k++) {
                dp[N][j] = Math.max(dp[N][j],dp[k][j - 1] + price[k][j]);
            }
        }
        int ans = 0;
        for(int i = 0; i <= N; i++){
            ans = Math.max(ans,dp[i][M-1]);
        }
        return ans;
    }
}
