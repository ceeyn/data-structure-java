import java.util.Scanner;

/**
 * @ClassName solution
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/24 16:34
 * @Version 1.0
 */
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(countValidSubstrings(str));
        sc.close();
    }

    public static int countValidSubstrings(String str) {
        int n = str.length();
        int left = 0;
        int r_count = 0;
        int e_count = 0;
        int ans = 0;

        for (int right = 0; right < n; right++) {
            char currentChar = str.charAt(right);

            if (currentChar == 'r') {
                r_count++;
            } else if (currentChar == 'e') {
                e_count++;
            } else if (currentChar == 'd') {

                r_count = 0;
                e_count = 0;
                left = right + 1;
                continue;
            }


            while (r_count >= 1 && e_count >= 1) {
                ans += (n - right);

                char leftChar = str.charAt(left);
                if (leftChar == 'r') {
                    r_count--;
                } else if (leftChar == 'e') {
                    e_count--;
                }
                left++;
            }
        }

        return ans;
    }
}

