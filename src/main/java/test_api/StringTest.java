package test_api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName String
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/7 21:01
 * @Version 1.0
 */
public class StringTest {

    public void testString() {

        // 使用字面值创建字符串
        String str1 = "Hello, World!";

        // 使用 new 关键字创建字符串
        String str2 = new String("Hello, World!");

        // 从字符数组创建字符串
        char[] chars = {'H', 'e', 'l', 'l', 'o'};
        String str3 = new String(chars);

        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);

        // 2. 字符串拼接
        String str4 = str1 + " How are you?";
        System.out.println(str4);

// 或者使用 concat 方法
        String str5 = str1.concat(" How are you?");
        System.out.println(str5);

        // 4. 字符串搜索
        // 查找字符位置
        int index = str1.indexOf('o');
        System.out.println("First occurrence of 'o': " + index);

// 查找子字符串位置
        int substringIndex = str1.indexOf("World");
        System.out.println("Index of 'World': " + substringIndex);


        // 5. 字符串替换
        String replacedStr = str1.replace('o', 'a');
        System.out.println("Replaced string: " + replacedStr);

// 替换子字符串
        String replacedSubStr = str1.replace("World", "Java");
        System.out.println("Replaced substring: " + replacedSubStr);

        // 6. 字符串拆分
        String[] words = str1.split(", ");
        for (String word : words) {
            System.out.println(word);
        }

        // 7.子字符串
        String subStr = str1.substring(7);
        System.out.println("Substring from index 7: " + subStr);

        String subStr2 = str1.substring(7, 12);
        System.out.println("Substring from index 7 to 12: " + subStr2);


        String str6 = "   Hello, World!   ";
        String trimmedStr = str6.trim();
        System.out.println("Trimmed string: '" + trimmedStr + "'");


        // 转换为大写
        String upperStr = str1.toUpperCase();
        System.out.println("Upper case: " + upperStr);

// 转换为小写
        String lowerStr = str1.toLowerCase();
        System.out.println("Lower case: " + lowerStr);


        /**
         * 包装类型相互转化可以用.valueOf();
         */
        // String -> Long / Integer
        long m = Long.parseLong("15445");
        System.out.println(m);
        String numberAsString2 = "123456789";
        Long number2 = Long.valueOf(numberAsString2);

        // long -> String
        long number = 123456789L;
        String numberAsString = String.valueOf(number);
        long number1 = 123456789L;
        String numberAsString1 = Long.toString(number);

        // 查看是否以某个字符开头
        boolean is_start = "abc".startsWith("0");

        // String.join()
        // String.join 是 Java 8 引入的一个便利的字符串方法，它用于将多个字符串元素连接成一个字符串，元素之间可以插入一个指定的分隔符。
        // 这个方法非常适合用来拼接数组或集合中的字符串元素，避免了使用循环或者构建器（如StringBuilder）来手动拼接字符串的麻烦。
        //String.join 方法有两个主要的重载形式：
        //String.join(CharSequence delimiter, CharSequence... elements)
        //这个方法接受一个分隔符和一个可变长参数列表，返回一个由分隔符分隔的字符串。
        //举个例子：String.join("-", "2022", "04", "22") 会返回 "2022-04-22"。
        //String.join(CharSequence delimiter, Iterable<? extends CharSequence> elements)
        //这个方法接受一个分隔符和一个实现了Iterable接口的集合，返回一个由分隔符分隔的字符串。
        //举个例子：如果有一个列表 List<String> dates = Arrays.asList("2022", "04", "22");，
        // 那么 String.join("-", dates) 会返回 "2022-04-22"。
        List<String> cur = new ArrayList<>();
        cur.add("你");
        cur.add("好");
        String hello = String.join("",cur);
        System.out.println(hello);



    }
    public static void testStringBuilder() {
        // StringBuilder 是 Java 中的一个可变字符序列类。与 String 类不同，它可以被多次修改而不产生新的未使用对象。
        // StringBuilder 类在进行字符串拼接操作时比 String 类更高效，因为它不会为每次拼接创建新的对象
        //StringBuilder(): 创建一个没有字符的字符串构建器，初始容量为 16 个字符。
        //StringBuilder(int capacity): 创建一个没有字符的字符串构建器，并指定初始容量。
        //StringBuilder(String str): 创建一个字符串构建器，内容初始化为指定字符串内容。
        StringBuilder sb = new StringBuilder("abc");
        //append(...): 将参数的字符串表示形式追加到序列。
        //insert(int offset, ...): 将参数的字符串表示形式插入到序列中的指定位置。
        sb.append("d");
        sb.insert(4,"e");
        //delete(int start, int end): 移除序列中的子字符串。删除[)内的所有字符
        //deleteCharAt(int index): 移除序列中指定位置的字符。
        //replace(int start, int end, String str): 使用指定的字符串替换序列中的子字符串。
        sb.deleteCharAt(4);
        System.out.println(sb.toString());
        sb.delete(3,4);
        System.out.println(sb.toString());
        sb.replace(2,3,"d");
        System.out.println(sb.toString());
        // substring(int start, int end): 返回一个新的 String，它包含从指定的 start 到 end 位置的字符
        System.out.println(sb.substring(0,2));


    }

    public static void testChar() {
        // isLetter(char ch): 判断指定的字符是否是一个字母。
        char ch = 'a';
        boolean isLetter = Character.isLetter(ch); // 返回 true
        // isDigit(char ch): 判断指定的字符是否是一个数字。
        char ch1 = '5';
        boolean isDigit = Character.isDigit(ch); // 返回 true

        // isUpperCase(char ch): 判断指定的字符是否是大写字母。
        char ch2 = 'A';
        boolean isUpperCase = Character.isUpperCase(ch); // 返回 true

        // toLowerCase(char ch): 将给定的字符转换为小写。
        char ch3 = 'A';
        char lowerCh = Character.toLowerCase(ch); // 返回 'a'

        // 大小写转换 ^=
        // 预备知识
        //0与0异或是0，0与1异或是1，所以0与x异或还是x。
        //1与0异或是1，1与1异或是0，所以1与x异或是x取反。
        //A~Z ascii 为 x0xxxxx
        //a~z ascii 为 x1xxxxx
        //A~Z 与 a~z相差32，就是相差 0100000
        //结论
        //将字母异或32就可以从大写转为小写，或者从小写转为大写
        //A ^ 32 = a
        //a ^ 32 = A
        //
        int toUpper = 'a' ^ 32;
        System.out.println(toUpper);
        int toLower = 'A' ^ 32;
        System.out.println(toLower);
        //解释
        //由于32是0100000，根据预备知识2，他会把另一个操作对象的第二高位从0变1，或从1变0，其他不变。而这一位的位权是32，也就是会加32或减32。恰好是大写字母到小写字母的ascii码的距离。
        //
        /**
         *  ascii码是8位二进制,将每个字符映射到一个介于0到127之间的数字，共128个不同的编码。这些编码包括英文字母（大写和小写）、数字0-9、标点符号，以及控制字符（如换行符、制表符等）。
         * 以下是一些ASCII码的基本分类：
         * 控制字符（0-31 & 127）：这些字符没有图形表示，用于控制像打印机或终端这样的设备。例如，ASCII码为9的字符是水平制表符，ASCII码为10的字符是换行符。
         * 打印字符：
         * 数字（48-57）：'0'-'9' 的 ASCII 码分别是 48 到 57。
         * 大写字母（65-90）：'A'-'Z' 的 ASCII 码分别是 65 到 90。
         * 小写字母（97-122）：'a'-'z' 的 ASCII 码分别是 97 到 122。
         * 标点符号和特殊字符：包括空格（ASCII码为32）、标点符号（如逗号、句号等）和其他特殊字符（如@、#、$等）。
         * ASCII 码的设计允许简单的数字操作来转换大小写字母（如通过加上或减去32），并且它的简单性使得它在计算机科学和网络通信中非常广泛地应用。
         * 尽管如今更复杂的编码系统（如UTF-8和UTF-16）已经被开发出来以支持全球各种语言和符号，ASCII 码仍然是这些系统的基础，
         * 并且在许多电脑系统和设备中依然被广泛使用。
         */

        // char -> int

        char a = '9';
        int b =  a-'0';
        System.out.println(b);


        // 英文字母数组，通过字母矩a的ASCII码成为下标
        String s = "abababba";
        int[] last = new int[26];
        int length = s.length();
        for (int i = 0; i < length; i++) {
            last[s.charAt(i) - 'a'] = i;
        }


    }

    




}
