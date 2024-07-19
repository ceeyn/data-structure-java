package dp;

/**
 * @ClassName ZeroOneBag
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/14 22:58
 * @Version 1.0
 */
public class ZeroOneBag {
    int[] coins;
    int[][] memo;
    // 0/1背包问题：代表前i个硬币组成amount的最小数量
    public int zeroOneBagPro(int i, int capicity ){
        if(i < 0){
            if(capicity == 0) return 0;
            else return Integer.MAX_VALUE / 2;
        }
        if(capicity < coins[i]){
            memo[i][capicity] = zeroOneBagPro(i-1,capicity);
            return memo[i][capicity];
        }
        if(memo[i][capicity] != -1) return memo[i][capicity];

        memo[i][capicity] = Math.min(zeroOneBagPro(i-1,capicity),zeroOneBagPro(i,capicity-coins[i])+1);

        return memo[i][capicity];
    }
    // // 0/1背包问题：代表前i个物品能组成的最大价值
    // public int zeroOneBagPro(int i, int capicity ){
    //     if(i < 0) return 0;
    //     if(capicity < nums[i]) return zeroOneBagPro(i-1,capicity);
    //     return Math.max(zeroOneBagPro(i,capicity),zeroOneBagPro(i,capicity-nums[i]));
    //     +values[i];
    // }

    int[] nums;
    // 代表前i个数字能够组合出capacity的方案数
    public int bagProblem(int i, int capicity) {
        if(i < 0) {
            // 倒着减，若capicity为0说明是合法方案返回1
            return capicity == 0? 1:0;
        }
        if(memo[i][capicity]!=-1) return memo[i][capicity];
        // 当前背包容量不够放下nums[i]，返回
        if(capicity < nums[i]) {
            memo[i][capicity] = bagProblem(i-1,capicity);
            return memo[i][capicity];
        }

        memo[i][capicity] = bagProblem(i-1,capicity)+bagProblem(i-1,capicity-nums[i]);
        return memo[i][capicity];
    }
    // // 代表前i件物品的最大价值，i是下标
    // public int bagProblem(int i, int capicity) {
    //     if(i < 0) return 0;
    //     // 当前背包容量不够放下nums[i]，返回
    //     if(capicity < nums[i]) return bagProblem(i-1,capicity);
    //     return Math.max(bagProblem(i-1,capicity),bagProblem(i-1,capicity-nums[i])+values[i]);
    // }

}
