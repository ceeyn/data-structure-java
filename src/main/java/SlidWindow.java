import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SlidWindow
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/19 11:22
 * @Version 1.0
 */
public class SlidWindow {
    // 本质：固定左端点，不断枚举右端点， 不满足条件时缩短左端点。
    //  题目条件：满足 单调性（数组元素都是正数） 的 子数组（数组元素连续）。

    // 一。滑动窗口最大值 / 固定窗口大小维护前k个小的数的和
        //对于不固定窗口：
            //求最小的思路：
            //外层循环 for right, x in enumerate(nums)for\ right,\ x\ in\ enumerate(nums)for right, x in enumerate(nums) 固定左端点，枚举右端点。
            //内层循环的进入条件就是先找到最长的条件成立的窗口，然后不断缩短左端点，并不断更新答案，直到不满足题目条件，那么就找到了最小的。
            //注意：内层循环统计答案，因为内层循环才是满足题目要求的子数组
            //求最大的思路：
            //外层循环 for right, x in enumerate(nums)for\ right,\ x\ in\ enumerate(nums)for right, x in enumerate(nums) 固定左端点，枚举右端点。
            //内层循环的进入条件就是窗口无法再长了，再长就不满足条件了，那么此时就需要不断缩短左端点，直到满足题目条件，那么外层循环就又可以统计答案了。
            //注意：外层循环统计答案，因为外层循环都是满足题目要求的子数组

    // 二。解决子数组个数问题：至多、至少、恰好三种类型,求和至多/至少/恰好为k的子数组个数
        //第一种题目是 答案在整个数组中间某段区间内才能是合法的 ，一般都是 求至多、小于 ，这种情况下枚举右端点的同时，去看对应的合法的左端点个数，也就是 rightrightright 不断加 111 枚举时，每次保证 [left:right][left:right][left:right] 区间都是合法的，那么这段区间的子数组个数答案就是 right−left+1right-left+1right−left+1个。
        //第二种题目是 答案是整个数组时都是合法的 ，一般是 求至少、大于 ，这种情况下只需在枚举右端点的同时，保证 leftleftleft 左侧是合法的就可以，这时候区间 [[0,1,2,...,left−1]:right] [[0,1,2,...,left-1]:right][[0,1,2,...,left−1]:right] 其实都是合法的，那么以该 rightrightright 为右端点的子数组个数答案为 leftleftleft 个。
        //第三种题目是 答案在整个数组中间某段区间内才能是合法的 ，但与第一种情况不同的是一般都是 求恰好 ，这种情况下需要把「求恰好」 转换成为 「求至多、小于」。 具体思路为：例如求恰好 kkk 个，就用最多 kkk 个 - 最多 k−1k-1k−1 个 = 恰好 kkk 个

    // 三。解决字符串内各种字符出现次数都大于等于 k的问题
        //窗口需要保证「各种字母出现次数都大于等于 k」这一性质。
        //分析：正常滑窗时，当右端点向右移动，扩大窗口时，如果 s[right]s[right]s[right] 是第一次出现的元素，窗口内的字母种类数量必然会增加，此时缩小 s[left]s[left]s[left] 也不一定满足窗口内「各种字母出现次数都大于等于 k」这一性质。那么我们就无法通过这种方式来进行滑动窗口。但是我们可以通过固定窗口内的字母种类数的方式进行滑动窗口

    // 四。本质：通过固定窗口内的字母种类数的方式进行滑动窗口。
        //因为给定字符串 s 仅有小写字母构成，则字母种类数目，最少为 1 种，最多为 26 种。
        //我们通过枚举可能出现的字母种类数目，从而固定窗口中出现的字母种类数目 i（1<=i<=26）i （1 <= i <= 26）i（1<=i<=26），再进行正常滑动窗口。
        //注意：窗口内需要保证出现的字母种类数目等于 iii 。
        //这时滑窗向右移动 right，扩大窗口时，记录窗口内各种字母数量。
        //当窗口内出现字符数量大于 i时，则不断右移 left ，保证窗口内出现字母种类等于 iii 。
        //而当窗口内出现字符数量等于 i时，枚举窗口内出现的各个字母的出现数量，如果都满足条件就更新答案


    //作者：小姜可
    //链接：https://leetcode.cn/problems/count-subarrays-with-score-less-than-k/solutions/2568769/hua-chuang-jie-jue-zi-shu-zu-ge-shu-de-s-h6kt/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    // lc 面试题 17.18. 最短超串
    Map<Integer,Integer> need = new HashMap();
    Map<Integer,Integer> have = new HashMap();
    public int[] shortestSeq(int[] big, int[] small) {
        for(int i = 0; i < small.length; i++){
            need.put(small[i],need.getOrDefault(small[i],0)+1);
        }
        int left = 0;
        int n = big.length;
        int m = small.length;
        int ans = Integer.MAX_VALUE;
        int[] res = new int[2];
        int need_count = need.size();
        int cur_count = 0;
        for(int i = 0; i < n; i++){
            // 入
            if(need.containsKey(big[i])){
                have.put(big[i],have.getOrDefault(big[i],0)+1);
                if(need.get(big[i]) == have.get(big[i])){
                    cur_count++;
                }
            }
            // 出
            while(need_count == cur_count){
                if(i - left + 1 <= ans){
                    if(i - left+1 < ans){
                        res[0] = left;
                        res[1] = i;
                    }else{
                        if(left < res[0]){
                            res[0] = left;
                            res[1] = i;
                        }
                    }
                    ans = i - left +1;
                }
                if(have.containsKey(big[left])){
                    have.put(big[left],have.getOrDefault(big[left],0)-1);
                    if(need.get(big[left]) > have.get(big[left])){
                        cur_count--;
                    }
                }
                left++;
            }
        }
        return ans == Integer.MAX_VALUE ? new int[0] : res;
    }
}
