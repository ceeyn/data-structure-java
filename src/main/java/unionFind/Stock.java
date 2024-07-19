package unionFind;

/**
 * @ClassName Stock
 * @Description TODO
 * @Author @hzp
 * @Date 2024/7/10 13:18
 * @Version 1.0
 */
public class Stock {
    private int getStockMax(int[][] price) {
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
