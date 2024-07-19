package dp;

import java.util.Arrays;

/**
 * @ClassName NumBitDP
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/12 20:05
 * @Version 1.0
 */
public class NumBitDP {
    String nums;
    int memo[][];
    // lc.2376. 统计特殊整数
    public int countSpecialNumbers(int n) {
        this.nums = String.valueOf(n);
        memo = new int[nums.length()][1 << 10];
        for (int i = 0; i < nums.length(); i++)
            Arrays.fill(memo[i], -1); // -1 表示没有计算过
        return dfs(0,0,true,false);

    }
    // 表示构造第 i 位及其之后数位的合法方案数
    // mask之前填的数的集合
    /// isLimit 当前位是否受限制，true的话，当前位[1,n[i]]，下一位【若当前位选num[i]，下一位依然为ture】flase的话1-9，下一位false
    // isNum 之前是否填数字，ture有，当前位可从0开始，false无，则当前位可以跳过（不填数字）或者要填入的数字至少为 1，
    // 为了排除010这种因为前导0不合法的,
    // 例如 n=123，在 i=0时跳过的话，相当于后面要构造的是一个 99以内的数字了，
    // 如果 i=1不跳过，那么相当于构造一个10到99的两位数，如果 i=1 跳过，相当于构造的是一个 9 以内的数字
    public int dfs(int i, int mask, boolean isLimit,boolean isNum){

        if(i == nums.length()) return isNum? 1:0;
        if (!isLimit && isNum && memo[i][mask] != -1)
            return memo[i][mask];
        int cur_res = 0;
        if(!isNum) cur_res = dfs(i+1,mask,false,false);
        // 枚举当前位可选的数
        int top = isLimit? nums.charAt(i)-'0' : 9;
        for(int j = isNum?0:1;j <= top; j++){
            if((mask >> j & 1 ) == 0){
                cur_res += dfs(i+1,mask | (1 << j),isLimit && j == top, true);
            }

        }
        // 可以只记录不受到 isLimit 或 isNum 约束时的状态 (i,mask)。比如 n=234，第一位（最高位）填的 1，
        // 那么继续递归，后面就可以随便填，所以状态 (1,2)就表示前面填了一个 1，从第二位往后随便填的方案数
        if (!isLimit && isNum)
            memo[i][mask] = cur_res;
        return cur_res;
    }
}
