package math;

/**
 * @ClassName Math
 * @Description TODO
 * @Author @hzp
 * @Date 2024/6/11 22:28
 * @Version 1.0
 */
public class Math {
    // 每次将a替换为b，b替换为a%b
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(gcd(3,6));
    }


}
