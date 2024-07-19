// 文件: src/StockProfitCalculator.java
package unionFind;
import java.util.*;

public class StockProfitCalculator {

//    public static int maxProfit(int[][] prices) {
//        if (prices == null || prices.length == 0 || prices[0].length == 0) {
//            return 0;
//        }
//
//        int N = prices.length; // 股票数量
//        int M = prices[0].length; // 天数
//
//        // dp[i][j] 表示第 j 天持有第 i 支股票的最大收益
//        int[][] dp = new int[N + 1][M];
//
//        // 初始化第一天的数据
//        for (int i = 0; i < N; i++) {
//            dp[i][0] = -prices[i][0]; // 第一天买入第 i 支股票后的收益
//        }
//        dp[N][0] = 0; // 第一天不持有任何股票的收益
//
//        // 动态规划状态转移
//        for (int j = 1; j < M; j++) {
//            for (int i = 0; i < N; i++) {
//                // 枚举第j天持有，位于 i 的 for 循环中，是为了确保针对每一支股票 i，计算并更新在第 j 天持有该股票的最大收益。
//                // 具体的逻辑是考虑从不同状态转换到持有第 i 支股票的状态，并找出所有这些可能状态中的最大值
//                // 每支股票的状态需要独立计算，因为每支股票的价格和交易策略可能不同。
//                // 将这段代码放在 i 的循环中可以确保为每一支股票 i 计算其在每一天 j 的所有可能状态和相应的最大收益
//                dp[i][j] = dp[i][j - 1]; // 默认持有第 i 支股票，不进行交易
//                for (int k = 0; k <= N; k++) { // 枚举第j-1天的状态
//                    if (k == N) { // 不持有任何股票
//                        dp[i][j] = Math.max(dp[i][j], dp[k][j - 1] - prices[i][j]);
//                    } else if (k != i) { // 持有其他股票
//                        dp[i][j] = Math.max(dp[i][j], dp[k][j - 1] + prices[k][j] - prices[i][j]);
//                    }
//                }
//            }
//             // 枚举第j天不持有，不放在i的循环中是因为N不持有任何股票与哪只股票的i无关
//            dp[N][j] = dp[N][j - 1]; // 默认不持有任何股票
//            for (int i = 0; i < N; i++) {
//                dp[N][j] = Math.max(dp[N][j], dp[i][j - 1] + prices[i][j]);
//            }
//        }
//
//        // 寻找最大收益
//        int maxProfit = 0;
//        for (int i = 0; i <= N; i++) {
//            maxProfit = Math.max(maxProfit, dp[i][M - 1]);
//        }
//
//        return maxProfit;
//    }

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
//        int ans = 0;
//        for(int i = 0; i <= N; i++){
//            ans = Math.max(ans,dp[i][M-1]);
//        }
        return dp[N][M-1];
//        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入股票数量 N 和天数 M：");
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[][] prices = new int[N][M];

        System.out.println("请输入股票价格矩阵：");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                prices[i][j] = scanner.nextInt();
            }
        }

//        int result = maxProfit(prices);
        int result = getStockMax(prices);
        System.out.println("最大收益：" + result + " 元");
    }
//    static int days;
//    static int stock_num;
//    static int[][] price;
//    // 第i天开始，k=1持有第j个股票，k=0什么都不持有
//    public static int dfs(int i, int j, int k) {
//        if(i >= days) return 0;
//        if(i == days-1) if(k == 1) return price[i][j];
//        int cur_res = 0;
//        if(k == 1){
//            // 卖
//            for(int nxt = 0; nxt < stock_num; nxt++){
//                cur_res = Math.max(cur_res,dfs(i+1,nxt,0)-price[i+1][nxt]+price[i+1][j]);
//            }
//            // 不卖
//            cur_res = Math.max(cur_res,dfs(i+1,j,1));
//
//        }else{
//            for(int nxt = 0; nxt < stock_num; nxt++){
//                cur_res = Math.max(cur_res,dfs(i+1,nxt,0));
//            }
//        }
//        return cur_res;
//
//    }
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入股票数量 N 和天数 M：");
//        int N = scanner.nextInt();
//        int M = scanner.nextInt();
//        int[][] prices = new int[N][M];
//
//        System.out.println("请输入股票价格矩阵：");
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                prices[i][j] = scanner.nextInt();
//            }
//        }
//
//        int result = maxProfit(prices);
//        System.out.println("最大收益：" + result + " 元");
//
////        price = new int[][]{
////                {3,4 ,6, 8, 2, 4 ,6},
////                { 7 ,1, 2, 5, 6, 1, 2},
////                { 4, 6 ,1 ,7, 3, 4, 5}
////        };
////        days = 7;
////        stock_num = 3;
////        for (int i = 0; i < 3; i++){
////            dfs(0,i,)
////        }
//    }
}
