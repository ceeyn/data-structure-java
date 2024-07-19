package jvmTest;

/**
 * @ClassName LocationTest
 * @Description TODO
 * @Author @hzp
 * @Date 2024/7/17 15:48
 * @Version 1.0
 */
public class LocationTest {
    int a = 5; //
    static int b = 1; // 静态变量：常量池
    static final  int c = 2; // 静态常量：常量池
    final int d = 3; //

    public void location() {
        String e = "abc"; // e在局部变量，"abc"在常量池
        Integer f = 3; // f的引用在局部变量，3在堆，常量池缓存了堆中3的引用
        //在 int number = 42; 这行代码中：
        //
        //字面量 42 不会单独存储在运行时常量池中，而是直接内联在字节码中。
        //局部变量 number 存储在方法栈帧的局部变量表中，其值 42 直接存储在这个局部变量表中。
        //所以，int number = 42; 中的 42 是在局部变量表中存储的，而不是在常量池中。下面是各部分的详细存储位置：
        int number = 42;


    }
}
