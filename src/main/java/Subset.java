import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName subset
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/3 11:58
 * @Version 1.0
 */
public class Subset {


    int[] nums = new int[]{1,2,3};
    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList();

    // 选或不选
    // 当前操作：枚举第i个数选或不选
    // 子问题：从下标>=i的元素中构造子集
    // 下一个子问题：从下标>=i+1的数字中构造子集
    public void dfs1(int i){
        if(i == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 不选
        // 注意什么时候一定选 什么时候可以不选
        // 131.回文子串 ：这里的【选】指的是把 i 当作子串的右端点。因为 n-1 一定是子串的右端点，所以一定要选。
        dfs1(i+1);

        // 选
        path.add(nums[i]);
        dfs1(i+1);
        path.remove(path.size()-1);
    }

    // 枚举选哪个[基于答案角度一定选]
    // 当前操作：枚举一个下标j>=i的数字，加入path
    // 子问题：从下标>=i的数字中构造子集
    // 下一个子问题：从下标>=j+1的数字中构造子集
    // j是待候选元素的下标，只是刚好在子集问题中可以代表i的下标，i才是代表构造答案的哪一位
    public void dfs2(int i){

        // 答案角度每个元素都是答案
        res.add(new ArrayList(path));
        if(i == nums.length) {

            return;
        }
        // 当前操作：枚举一个下标j大于等于i的元素构造子集
        for(int j = i; j < nums.length; j++){
            path.add(nums[j]);
            dfs2(j+1);
            path.remove(path.size()-1);
        }
    }
    //======================================================================================================================================





    // 基于字符型
    //======================================================================================================================================
    private final List<List<String>> string_ans = new ArrayList<>();
    private final List<String> string_path = new ArrayList<>();
    private String s;

    // 站在答案视角，每一次答案中选一个
    // 当前操作：选择回文子串s[i..j],加入path
    // 子问题：从下标>=i的后缀中构造回文分割
    // 下一个子问题：从下标>=j+1的后缀中构造回文分割
    public void dfs_s(int i) {
        // 没有每次都添加答案，答案需要每个元素都在其中
        if(i == s.length()){
            string_ans.add(new ArrayList(string_path));
        }
        // 当前操作，选一个j大于等于i，加入path
        for(int j = i; j < s.length();j++){
            String child = s.substring(i,j+1);
            if(isHui(child)){
                string_path.add(child);
                // 下一个子问题：枚举下标大于等于j的元素
                dfs_s(j+1);
                string_path.remove(string_path.size()-1);
            }

        }
    }


    // start 表示当前这段回文子串的开始位置
    // 表示下标大于等于i的元素中，是否选择i作为最后一个元素，选择的话[start,i]加入集合,下次从[i+1,i+1]去判断
    // 不选择的话,下次从[start,i+1]开始
    // 当前操作，选或不选下标为i的元素
    // 子问题：下标大于等于i的元素，分割构造回文
    // 下一个子问题：分情况讨论，选择i的话，从i+1分割构造，不选择的话,下次从[start,i+1]开始

    // start 表示当前这段回文子串的开始位置
    private void dfs_s1(int i, int start) {
        if (i == s.length()) {
            string_ans.add(new ArrayList<>(string_path)); // 复制 path
            return;
        }

        // 不选 i 和 i+1 之间的逗号（i=n-1 时一定要选）
        if (i < s.length() - 1)
            dfs_s1(i + 1, start);

        // 选 i 和 i+1 之间的逗号（把 s[i] 作为子串的最后一个字符）
        String cur_s = s.substring(start, i + 1);
        if (isHui(cur_s)) {
            string_path.add(cur_s);
            dfs_s1(i + 1, i + 1); // 下一个子串从 i+1 开始
            string_path.remove(string_path.size() - 1); // 恢复现场
        }
    }


    public boolean isHui(String s) {
        char[] chars = s.toCharArray();
        for(int i = 0, j = chars.length-1; i < j; i++,j--){
            if(chars[i] != chars[j]) return false;
        }
        return true;

    }

}
class Permutations{
    // lc 46
    int[] nums;
    List<List<Integer>> res;
    List<Integer> path;
    boolean[] visited;
    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        res = new ArrayList();
        path = new ArrayList();
        visited = new boolean[nums.length];
        dfs(0);
        return res;

    }
    //当前操作：枚举一个没选过的j填入path的i位置
    // 子问题，构造path下标大于等于i选什么
    // 下一个子问题：构造i+1的部分
    public void dfs(int i){
        if(i == nums.length){
            res.add(new ArrayList(path));
            return;
        }
        for(int j = 0; j < nums.length; j++){
            if(!visited[j]){
                visited[j] = true;
                path.add(nums[j]);
                dfs(i+1);
                path.remove(path.size()-1);
                visited[j] = false;
            }
        }

    }
}