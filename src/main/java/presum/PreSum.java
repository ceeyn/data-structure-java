package presum;

/**
 * @ClassName presum.PreSum
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/17 15:55
 * @Version 1.0
 */
// 下标代表前i，前i行，前j列
public class PreSum {
    // 一维
    // preSum[i] 代表前i个元素的和
    class PreSum1 {
        int[] nums;
        int n = nums.length;

        int[] preSum = new int[n + 1];

        public PreSum1() {
            for (int i = 0; i < n; i++) {
                preSum[i + 1] = preSum[i] + nums[i];
            }
        }
    }

    // 二维
    class MatrixSum {

        private int[][] sum;

        // MatrixSum[i][j] 代表前i行,前j列的元素和
        // MatrixSum[i+1][j+1] 代表左上角为matrix[0][0] 右下角为matrix[i][j]子矩阵的元素和
        public MatrixSum(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            sum = new int[m + 1][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + matrix[i][j];
                }
            }
        }
        // 0 1
        // 2 3

        // 0 0 0
        // 0 0 1
        // 0 2 3
        // [1,1]和[0,0]之间 = sum[2,2] - sum[2][1] - sum[1][2] + sum[1][1]
        // 返回[r2，c2] 和 [r1,c1]之间
        // 坐标比个数差一，返回前r2+1行，前c2+1列和前r1行，前c1列之间的差
        public int query(int r1, int c1, int r2, int c2) {
            return sum[r2 + 1][c2 + 1] - sum[r2 + 1][c1] - sum[r1][c2 + 1] + sum[r1][c1];
        }

    }


}
