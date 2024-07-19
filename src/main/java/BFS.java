import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @ClassName BFS
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/3 15:39
 * @Version 1.0
 */
public class BFS {
    int[][] grid = new int[][]{{1,0,0},{1,1,0},{1,0,1}};
    int m;
    int n;
    // visited:1：岛屿未访问过，2：岛屿已经访问过，0：胡泊
    int[][] visited = new int[m][n];

    Queue<int[]> queue = new ArrayDeque<>();

    public void bfs() {

        while(!queue.isEmpty()){

            int sz = queue.size();
            for(int i = 0; i < sz; i++){
                int[] cur = queue.poll();
                int cur_x = cur[0];
                int cur_y = cur[1];
                // 标记为访问过
                grid[cur_x][cur_y] = 2;

                if(cur_x + 1 < m && grid[cur_x+1][cur_y] == 1){
                    queue.offer(new int[]{cur_x+1,cur_y});
                }

                if(cur_x - 1 >= 0 && grid[cur_x-1][cur_y] == 1){
                    queue.offer(new int[]{cur_x-1,cur_y});
                }

                if(cur_y + 1 < n && grid[cur_x][cur_y+1] == 1){
                    queue.offer(new int[]{cur_x,cur_y+1});
                }

                if(cur_y - 1 >= 0 && grid[cur_x][cur_y-1] == 1){
                    queue.offer(new int[]{cur_x,cur_y-1});
                }
            }

        }
    }
}
