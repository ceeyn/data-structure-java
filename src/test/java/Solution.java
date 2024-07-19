import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LC
 * @Description TODO
 * @Author @hzp
 * @Date 2024/7/17 09:52
 * @Version 1.0
 */
class Solution {
    /**
     * 输入：s = "QWER"
     * 输出：0
     * 解释：s 已经是平衡的了。
     * 示例 2：
     *
     * 输入：s = "QQWE"
     * 输出：1
     * 解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
     * 示例 3：
     *
     * 输入：s = "QQQW"
     * 输出：2
     * 解释：我们可以把前面的 "QQ" 替换成 "ER"。
     * 示例 4：
     *
     * 输入：s = "QQQQ"
     * 输出：3
     * 解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。
     * @param s
     * @return
     */
    public int balancedString(String s) {
        HashMap<Character,Integer> map = new HashMap();
        for(int i = 0; i < s.length();i ++ ){
            char c = s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }
        int n = s.length();
        int char_freq = n/4;
        HashMap<Character,Integer> need = new HashMap<>();
        for(Map.Entry entry : map.entrySet()) {
            if ((int)entry.getValue() > char_freq){
                need.put((char)entry.getKey(),(int)entry.getValue()-char_freq);
            }
        }
        int left = 0;
        int ans = Integer.MAX_VALUE;
        HashMap<Character,Integer> window = new HashMap<>();
        int cur_count = 0;
        for(int i = 0;i < n; i++){
            char c = s.charAt(i);
            if(need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(need.get(c) == window.get(c)) cur_count++;
            }
            while(cur_count == need.size() && left <= i){
                char c_left = s.charAt(left);
                ans = Math.min(ans,i-left+1);
                if(need.containsKey(c_left)) {
                    window.put(c_left, window.get(c_left) - 1);
                    if (need.get(c_left) != window.get(c_left)) {
                        cur_count--;
                    }
                }
                left++;
            }
        }
        return ans;
    }
}
