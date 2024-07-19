package stack;

import java.util.Stack;

/**
 * @ClassName Stack
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/13 10:28
 * @Version 1.0
 */
public class Stack1 {

    private class MinStack{

        Stack<Integer> st;
        // 栈顶是最小元素，维护这个循环不变量,不用想复杂情况，定义循环不变量且维护这个性质即可
        Stack<Integer> min_st;

        public MinStack() {
            st = new Stack();
            min_st = new Stack();
        }

        public void push(int val) {
            st.push(val);
            if(min_st.isEmpty() || val <= min_st.peek()){
                min_st.push(val);
            }

        }

        public void pop() {
            int cur = st.pop();
            if(cur == min_st.peek()){
                min_st.pop();
            }

        }

        public int top() {
            return st.peek();
        }

        public int getMin() {
            return min_st.peek();
        }
    }

    public class monotonicStack{
        //示例 1:
        //
        //输入: temperatures = [73,74,75,71,69,72,76,73]
        //输出: [1,1,4,2,1,1,0,0]
        //示例 2:
        //
        //输入: temperatures = [30,40,50,60]
        //输出: [1,1,1,0]
        //示例 3:
        //
        //输入: temperatures = [30,60,90]
        //输出: [1,1,0]
        // 维护栈顶元素性质,两种做法，把没有找到要求的元素存入栈顶，或要求元素的候选放入栈顶
        // 1.从右到左，栈中记录下一个更大元素的候选项，如果栈顶元素比入栈元素小则不能成为候选项，弹出
        // 1.从左到右，把没有找到下一个更大的元素存入栈顶
        // 2.单调递增？单调递减
        public int[] dailyTemperatures(int[] temperatures) {
            Stack<Integer> stack = new Stack();
            int[] ans = new int[temperatures.length];
            for(int i = 0; i < temperatures.length; i++){
                // 若i小于等于栈顶了，就无法更新栈顶元素的答案，也无法更新任何栈中元素答案
                // 栈里是没有找到下个更大的元素，维护这个循环不变量
                // 注意是在循环内更新答案，因此题目要求什么，循环条件是什么
                while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                    int index = stack.pop();
                    ans[index] = i-index;
                }
                stack.push(i);
            }
            return ans;
        }


        // lc 1793. 好子数组的最大分数
        // 求左边最小,将没有找到答案的放在栈顶，从右到左，单调递zeng，考虑什么时候栈顶元素能找到答案
        // 求左边最小,将答案的候选项放在栈顶，从左到右，单调递增，考虑什么时候当前元素找到答案
        class Solution {
            public int maximumScore(int[] nums, int k) {
                int n = nums.length;
                int[] left = new int[n];
                int[] right = new int[n];
                Stack<Integer> st = new Stack<>();

                // // 初始化 left 和 right 数组
                // for (int i = 0; i < n; i++) {
                //     left[i] = -1;  // 初始值为 -1，表示没有更小的左边界
                //     right[i] = n;  // 初始值为 n，表示没有更小的右边界
                // }

                // // 计算 left 数组（从右到左）
                // for (int i = n - 1; i >= 0; i--) {
                //     while (!st.isEmpty() && nums[st.peek()] > nums[i]) {
                //         int index = st.pop();
                //         left[index] = i;
                //     }
                //     st.push(i);
                // }
                for(int i = 0; i < n; i++){
                    while(!st.isEmpty() && nums[st.peek()] >= nums[i]){
                        st.pop();
                    }
                    left[i] = st.isEmpty() ? -1 : st.peek();
                    st.push(i);
                }
                st.clear();

                // 计算 right 数组（从左到右）
                for (int i = n-1; i >= 0; i--) {
                    while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
                        st.pop();
                    }
                    right[i] = st.isEmpty()? n : st.peek();
                    st.push(i);
                }

                int ans = 0;
                for (int i = 0; i < n; i++) {
                    int h = nums[i];
                    int l = left[i];
                    int r = right[i];
                    if (l < k && k < r) { // 相比 84 题多了个 if 判断
                        ans = Math.max(ans, h * (r - l - 1));
                    }
                }
                return ans;
            }
        }

    }
    //输入：nums = [3,5,2,6], k = 2
    //输出：[2,6]
    //解释：在所有可能的子序列集合 {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]} 中，[2,6] 最具竞争力。
    //示例 2：
    //
    //输入：nums = [2,4,3,3,5,4,9,6], k = 4
    //输出：[2,3,3,4]
   


}
