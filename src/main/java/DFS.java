import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DFS
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/3 12:37
 * @Version 1.0
 */
public class DFS {
    // 岛屿类
    //======================================================================================================================================
    int[][] grid = new int[][]{{1,0,0},{1,1,0},{1,0,1}};
    int m;
    int n;
    // [回溯的目的是不影响其它路径，这里当前节点出发没环，得把当前节点访问过的标记取消掉，从而不影响其它节点判环，]
    //  [而岛屿类题目，不需回溯，因为当前岛屿被访问过后，其它岛屿出发不应重复访问]
    // visited:1：岛屿未访问过，2：岛屿已经访问过，0：胡泊，
    int[][] visited = new int[m][n];

    public void dfs(int i, int j){
        if(i < 0 || i >= m || j <0 || j >= n || visited[i][j] != 1)
            return;
        visited[i][j] = 2;
        dfs(i+1,j);
        dfs(i-1,j);
        dfs(i,j+1);
        dfs(i,j-1);
    }
    public int numIslands(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(i, j);
                    res++; // 对于每个找到的岛屿，增加计数
                }
            }
        }
        return res;
    }

    //======================================================================================================================================



    // dfs判断是否有环：207.课程表
    //======================================================================================================================================
    List<List<Integer>> edges;
    int[] visited1;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList();
        for(int i = 0; i < numCourses; i++)
            edges.add(new ArrayList<>());
        visited1 = new int[numCourses];
        for(int[] ele : prerequisites){
            edges.get(ele[1]).add(ele[0]);
        }
        boolean res = true;
        for(int[] ele : prerequisites){
            // 只要有一个有环，结果就为false
            res = res & dfs(ele[0]);
        }
        // for(int i = 0; i < numCourses; i++)
        //     if(!dfs(i)) return false;
        return res;
    }

    public boolean dfs(int i){
        // 被当前启动节点访问过
        if(visited1[i] == 1) return false;
        // 被其它启动节点访问过
        if(visited1[i] == -1) return true;
        visited1[i] = 1;
        List<Integer> i_edges = edges.get(i);
        boolean cur_res = true;
        for(int j : i_edges){
            cur_res = cur_res & dfs(j);
        }
        /**
         在深度优先搜索（DFS）中，回溯是一种控制搜索流程的机制，它确保了算法能够探索解空间树的所有可能分支。
         DFS通过递归地深入解空间树的一个分支直到不满足条件或到达终点，然后回退到上一个分支点继续探索其他可能的路径。这个过程中的回溯操作确保了算法不会遗漏任何可能的分支。
         在实现DFS时，我们通常使用递归调用来模拟回溯过程。当DFS探索到一个节点并从该节点出发进一步探索时，
         如果该路径不能达到目标或者已经达到需要的条件，算法就会退回（回溯）到前一个节点，然后尝试其他的路径。这种机制对于以下几个方面至关重要：
         避免重复访问：在搜索过程中，某些节点可能会被多次访问。通过回溯（和适当的标记），DFS确保每个节点和路径只被访问一次，这样可以避免无限循环和不必要的重复计算。
         寻找所有可能的解：[在如数独、图的所有路径、组合问题等需要寻找所有解的问题中，DFS通过回溯可以确保每个选项都被探索过，从而找到所有可能的解。]
         发现最优解：对于需要找到最优解的问题，DFS需要探索所有可能的路径，通过回溯比较不同路径的结果，才能确定哪个是最优的。
         */
        // 在返回前设为-1，对于返回给的那个节点来说，是被其它节点访问过的
        // flags[i] = 0; 回溯的目的是不影响其它路径，这里当前节点出发没环，得把当前节点访问过的标记取消掉，从而不影响其它节点判环，
        // 而岛屿类题目，不需回溯，因为当前岛屿被访问过后，其它岛屿出发不应重复访问
        visited1[i] = -1;
        return cur_res;

    }


}
