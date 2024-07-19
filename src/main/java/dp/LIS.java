package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LIS
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/25 09:26
 * @Version 1.0
 */
public class LIS {

    // 1.子问题：枚举选哪个i为结尾的子序列长度最长【一定选，一定以i为结尾】,i = -1 时的递归结束条件不用写
    // 2.子问题：选或不选以i为结尾的递增子序列长度最长【不一定以i结尾】
    class LIS1{
        int[] nums;
        int[] memo;
        // 子问题：枚举选哪个i为结尾的子序列长度最长【一定选，一定以i为结尾】
        public int lengthOfLIS(int[] nums) {
            this.nums = nums;
            int n = nums.length;
            memo = new int[n];
            Arrays.fill(memo,-1);
            int ans = 0;
            for(int i = n-1; i >= 0; i--){
                // 不确定以哪个元素为结尾时，子序列最长
                ans = Math.max(ans,dfs(i));
            }
            return ans;

        }
        // 基于答案，选哪个，当前操作：选一个j<=i
        // 子问题：以i为结尾构造最长递增子序列
        // 下一个子问题：

        public int dfs(int i ){
            if(i < 0) return 0;
            if(memo[i] != -1) return memo[i];
            // res代表以j为结尾子序列中最长
            int res = 0;

            for(int j = i-1; j >= 0; j--){
                if(nums[j] < nums[i]){
                    res= Math.max(res,dfs(j));
                }
            }
            // 当前长度为以j为结尾子序列中最长+1
            memo[i] =  res+1;
            return memo[i];
        }
        // lc 673. 最长递增子序列的个数
        class Solution {
            int max_len = 1;
            int[] nums;
            int[][] memo;
            public int findNumberOfLIS(int[] nums) {
                this.nums = nums;
                int ans = 0;
                int max_len = 0;
                memo = new int[nums.length][2];
                for(int i = 0; i < nums.length; i++){
                    Arrays.fill(memo[i],-1);
                }

                for(int i = nums.length-1; i >= 0; i--){
                    int[] cur = dfs(i);

                    if(cur[0] == max_len) {
                        ans += cur[1];
                    }else if(cur[0] > max_len){
                        ans = cur[1];
                        max_len =cur[0];
                    }
                }
                return ans;
            }
            // 以i为结尾（一定选）返回的最长递增子序列的长度，数量
            public int[] dfs(int i){
                // if(i < 0) {
                //     return new int[]{0,1};
                // }
                if(memo[i][0] != -1 && memo[i][1] != -1) return new int[]{memo[i][0],memo[i][1]};
                int cur_num = 1;
                int cur_length = 1;
                for(int j = i - 1; j >= 0; j--){
                    if(nums[j] < nums[i]){
                        int[] cur = dfs(j);
                        if(cur[0] + 1 > cur_length){
                            cur_length = Math.max(cur_length,cur[0]+1);
                            cur_num = cur[1];
                        }else if(cur[0] + 1 == cur_length){
                            cur_num += cur[1];
                        }

                    }
                }
                memo[i][0] = cur_length;
                memo[i][1] = cur_num;
                return new int[]{cur_length,cur_num};
            }
        }


        public int lengthOfLIS1(int[] nums) {
            int n = nums.length;
            // g[i]表示长度为i+1的is的最小末尾元素
            List<Integer> g = new ArrayList();
            // 更新的位置是g中第一个大于num[i]的位置上的元素
            for(int i = 0; i < n; i++){
                int j = left_bound(g,nums[i]);
                if(j == g.size()) g.add(nums[i]);
                else g.set(j,nums[i]);
            }

            return g.size();

        }
        // 返回第一个大于等于target的位置
        public int left_bound(List<Integer> nums, int target){
            int l = 0, r = nums.size()-1;
            while(l <= r ){
                int mid = l + (r - l) / 2;
                if(nums.get(mid) < target){
                    l = mid + 1;
                }else{
                    r = mid - 1;
                }
            }
            return r+1;
        }
    }
    // 2.子问题：选或不选以i为结尾的递增子序列长度最长【不一定以i结尾】
    class LIS2{
        int[] nums;
        public int lengthOfLIS(int[] nums) {
            this.nums = nums;
            int n = nums.length;
            return dfs(n-1,Integer.MAX_VALUE);

        }
        // 选或不选，需获得上一次选的值才能判断当前是否能选
        // 返回以i结尾的最长子序列
        public int dfs(int i,int last_i){
            if(i < 0) return 0;
            int cur_res = -1;
            // 错误：不选，等于0一定选，否则一直递归下去
            // if(i > 0){
            cur_res = Math.max(cur_res,dfs(i-1,last_i));
            //}

            // 选
            if(nums[i] < last_i){

                cur_res = Math.max(cur_res, dfs(i-1,nums[i]) + 1);
            }

            return cur_res;

        }
    }
}
