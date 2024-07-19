package test_api;

import java.math.BigInteger;

/**
 * @ClassName test_api.NumberTest
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/19 20:08
 * @Version 1.0
 */
public class NumberTest {
    public void testInt() {
        // 创建BigInteger实例的一种常见方法是使用其构造函数，它接受一个字符串参数：
        // import java.math.BigInteger;
        BigInteger bigInteger = new BigInteger("12345678901234567890");
        BigInteger a = new BigInteger("10");
        BigInteger b = new BigInteger("20");

        BigInteger sum = a.add(b); // 结果是30
        BigInteger diff = a.subtract(b); // 结果是-10
        BigInteger product = a.multiply(b); // 结果是200
        BigInteger quotient = a.divide(b); // 结果是0
        BigInteger a4 = new BigInteger("-100");
        BigInteger absValue = a.abs(); // 结果是100

        BigInteger a1 = new BigInteger("100");
        BigInteger b1 = new BigInteger("200");
        BigInteger max = a.max(b); // 结果是200
        BigInteger min = a.min(b); // 结果是100

        BigInteger a2 = new BigInteger("10");
        BigInteger b2 = new BigInteger("3");
        BigInteger mod = a.mod(b); // 结果是1

        BigInteger a3 = new BigInteger("2");
        BigInteger pow = a.pow(10); // 结果是1024

        // 要判断BigInteger的值是否大于某个特定的数，例如25，可以使用BigInteger的compareTo方法。这个方法接收另一个BigInteger作为参数，并返回一个整数，表示两个数的比较结果：
        //如果返回值为0，表示这两个数相等。
        //如果返回值为正数，表示原数（即调用compareTo方法的数）大于参数数。
        //如果返回值为负数，表示原数小于参数数。
        //首先，你需要创建一个表示25的BigInteger实例，然后调用compareTo方法进行比较。
        BigInteger number = new BigInteger("30");
        BigInteger valueToCompare = new BigInteger("25");

        if (number.compareTo(valueToCompare) > 0) {
            System.out.println("Number is greater than 25");
        } else {
            System.out.println("Number is not greater than 25");
        }
    }

    public class DoubleTest{
        public void testDouble(){
            double a = 1.0;
            // 转换为整数会舍去小数部分
           double res = Math.pow((double)a, 1.0/3);
           int res1 = (int)res;
           // Math.round四舍五入，返回long
           long res2 =  Math.round(res);

        }
    }


}
