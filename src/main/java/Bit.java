/**
 * @ClassName Bit
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/6 17:06
 * @Version 1.0
 */
public class Bit {
    public void testYouYi(){
        /**
         * 在Java中，表达式n >>>= 1;使用了无符号右移位运算符(>>>)和赋值运算符(=)的组合。这个操作执行了以下步骤：
         * 无符号右移位操作(>>>): 将n的二进制表示向右移动一位。无符号右移位与普通右移位(>>)的区别在于，无论原始数值的符号位是什么，无符号右移位总是在左侧补0。这意味着无符号右移位总是产生一个非负结果。
         * 赋值操作(=): 将无符号右移位操作的结果重新赋值给n。
         * 综上所述，n >>>= 1;实际上将n的值更新为原来的一半（对于非负整数），并且处理方式保证了结果总是非负的。如果n是偶数，这相当于简单地除以2。如果n是奇数，由于在右移时左侧补0，结果相当于n除以2后向下取整。
         * 这种操作常用于快速计算除以2的结果，或者在处理二进制数据时，将位向右移动特定的位数。
         */

        // lowbit，lowbit() 函数是一种常用于二进制操作的函数，它用来获取一个整数在二进制表示下最低位的1所对应的值。
        // 也就是说，它返回的是这个整数最右边的1及其后面所有0构成的数值。
        //举个例子，如果有一个整数 x = 18，其二进制表示为 10010，那么 lowbit(x) 的结果就是 10（即二进制的 10，对应十进制中的 2）
        int x = 10;
        int lowbit = x & -x;

        // 两数相加：1.进位（ a & b）<< 1,2.当前位之和：a ^ b
        // b保存结果进位，a保存结果的数,将a看做当前和，b看做进位和
        int a=1,b =1;
        while(b != 0){
            int temp = a ^ b;
            b = (a & b) << 1;
            a = temp;
        }
        // return a;

        // 删除最小元素,s = 101100
        //    s-1 = 101011 // 最低位的 1 变成 0，同时 1 右边的 0 都取反，变成 1
        //s&(s-1) = 101000
        int s = 4 & (4-1);


        // 判断是否是2的幂
        int n = 2;
        boolean is = n > 0 && (n & (n - 1)) == 0;

        // lowbit判断是否是2的幂
        boolean is2 = n > 2 && (n & -n) == n;

        // a^a = 0;利用异或运算 a⊕a=0的性质，我们可以用异或来「消除」所有出现了两次的元素，lc136题
        // 最后剩下的一定是只出现一次的元素

        // 右移i位与1，就可以查看某个数第i位是什么

        // 集合大小,计算二进制中 1 的个数
        int i = Integer.bitCount(2);

        // 在这个整数的二进制形式的左边，数3,任何非零的负数调用此方法都会返回0
        int result1 = Integer.numberOfLeadingZeros(1); // 返回31，因为1的二进制表示是000...0001
        int result2 = Integer.numberOfLeadingZeros(0); // 返回32，因为0的二进制表示是000...0000，全部是零
        int result3 = Integer.numberOfLeadingZeros(16); // 返回27，因为16的二进制表示是000...010000
        int result4 = Integer.numberOfLeadingZeros(-1); // 返回0，因为-1的二进制表示是111...1111，没有前导零


        // 计算二进制长度,
        int length = 32-Integer.numberOfLeadingZeros(1);


        // 集合最大元素,计算二进制长度，减一后得到集合最大元素；
        // 这样一来，对于任何非负整数i，
        // 31 - Integer.numberOfLeadingZeros(i)的结果就是它在二进制表示中最高位的1出现在第几位（从右往左数，最右边的位为0位）
        // 如果集合中的最大元素是8（二进制为0000 0000 0000 0000 0000 0000 0000 1000），那么31 - Integer.numberOfLeadingZeros(8)的结果是3，
        // 表示8的最高位1位于第4位，因此在这个集合中31 - Integer.numberOfLeadingZeros(s)的结果就是3
        int max_ele = 31-Integer.numberOfLeadingZeros(1);

        // 计算的是在整数的二进制形式的右边，从右往左数，连续0的个数直到遇到的第一个1。这个方法对处理与2的幂次有关的问题非常有用，
        // 因为2的幂次在二进制表示中只有一个1，其余都是0。因此，这个方法可以直接告诉我们这个1后面有多少个0，也就是这个数是2的多少次幂
        int result5 = Integer.numberOfTrailingZeros(16); // 返回4，因为16的二进制表示是10000
        int result6 = Integer.numberOfTrailingZeros(0);  // 返回32，因为0的二进制表示是000...000，整个数都是零
        int result7 = Integer.numberOfTrailingZeros(3);  // 返回0，因为3的二进制表示是11，最低位是1，没有后缀零
        int result8 = Integer.numberOfTrailingZeros(1024); // 返回10，因为1024的二进制表示是100 0000 0000，有10个后缀零

        // 集合最小元素
        int min_ele = Integer.numberOfTrailingZeros(s);

        // 遍历集合,设元素范围从0 到 n−1，挨个判断每个元素是否在集合 s 中：
        int s1 = 100010101;
        int n1 = 10;
        for(int j = 0; j < n1; j++){
            // (s1 & (1 << j) == 1) 左移判断不行，必须右移判断，
            // 左移 ：(n &(1 << i) ) > 0 说明n的第i位为1
            if(((s1 >> j) & 1) == 1){
                // i 在 s 中
            }
        }

        int num = 5;    // 二进制表示为 00000000 00000000 00000000 00000101
        int result = ~num;  // 结果的二进制为 11111111 11111111 11111111 11111010，十进制表示为 -6

// 对于负数
        int negNum = -5;  // 二进制表示为 11111111 11111111 11111111 11111011 （补码表示）
        int negResult = ~negNum;  // 结果的二进制为 00000000 00000000 00000000 00000100，十进制表示为 4

        // 枚举集合
        for (int s2 = 0; s2 < (1 << n); s2++) {
            // 处理 s 的逻辑
        }

        // 设集合s，从大到小枚举s 的所有非空子集sub：
        for (int sub = s; sub > 0; sub = (sub - 1) & s) {
            // 处理 sub 的逻辑
        }

        // 从大到小枚举s 的所有子集sub,包括空集，https://leetcode.cn/circle/discuss/CaOJ45/
        int sub = s;
        do {
            // 处理 sub 的逻辑
            sub = (sub - 1) & s;
        } while (sub != s);


    }

    /**
     *
     * @param x
     * @param n
     * @return
     */
    public double fastPow(double x, int n){
        if(x == 0.0f) return 0.0d;
        long b = n;
        double res = 1;
        if(b < 0){
            x = 1 / x;
            b= -b;
        }
        while(b > 0){
            if((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }
    // 模拟相加：lcr002
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1; // 标记遍历到 a 的位置
        int j = b.length() - 1; // 标记遍历到 b 的位置
        int carry = 0; // 进位
        while(i >= 0 || j >= 0 || carry != 0){// a 没遍历完，或 b 没遍历完，或进位不为 0
            int digitA = i >= 0 ? a.charAt(i) - '0' : 0;// 当前 a 的取值
            int digitB = j >= 0 ? b.charAt(j) - '0' : 0;// 当前 b 的取值
            int sum = digitA + digitB +carry;// 当前位置相加的结果
            carry = sum >= 2? 1 : 0;// 是否有进位
            sum = sum >= 2 ? sum-2 :sum;// 去除进位后留下的数字
            res.append(sum); // 把去除进位后留下的数字拼接到结果中
            i --;  // 遍历到 a 的位置向左移动
            j --;  // 遍历到 b 的位置向左移动
        }
        return res.reverse().toString(); // 把结果反转并返回
    }
}
