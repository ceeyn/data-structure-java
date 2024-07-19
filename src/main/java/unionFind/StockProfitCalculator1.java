package unionFind;
public class StockProfitCalculator1 {
    static int days;
    static int stock_num;
    static int[][] price;

    // 第i天开始，k=1持有第j个股票，k=0什么都不持有
    public static int dfs(int i, int j, int k) {
        if (i < 0) return k == 1 ? Integer.MIN_VALUE : 0;
        if(i == 0) return k ==1? price[0][j]:0;
        int cur_res = 0;
        if (k == 1) {

            for (int nxt = 0; nxt < stock_num; nxt++) {
                cur_res = Math.max(cur_res, dfs(i - 1, nxt, 1) + price[i - 1][nxt]-price[i-1][j]);
            }

            cur_res = Math.max(cur_res, dfs(i - 1, j, 1));
        } else {
            for (int nxt = 0; nxt < stock_num; nxt++) {
                cur_res = Math.max(cur_res, dfs(i - 1, nxt, 1) + price[i - 1][nxt]);
            }
        }
        return cur_res;
    }

    public static void main(String[] args) {
        price = new int[][]{
                {3, 4, 6, 8, 2, 4, 6},
                {7, 1, 2, 5, 6, 1, 2},
                {4, 6, 1, 7, 3, 4, 5}
        };
        days = 7;
        stock_num = 3;
        int maxProfit = 0;
        for (int i = 0; i < stock_num; i++) {
            maxProfit = Math.max(maxProfit, dfs(6, i, 0));
        }
        System.out.println("最大收益：" + maxProfit + " 元");
    }
}
