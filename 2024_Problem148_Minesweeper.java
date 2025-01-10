//529. Minesweeper - https://leetcode.com/problems/minesweeper/description/
//Time Complexity: O(m*n)
//Space Complexity: O(m*n)

class Solution {
    int[][] dirs;
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        this.dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0},{-1,-1},{-1,1},{1,-1},{1,1}};
        this.n = board.length;
        this.m = board[0].length;

        //if the clicked position is a mine
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        //BFS - explore all neighbors together
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B'; //revealed Blank

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int count = countMines(board, curr[0], curr[1]);
            if(count != 0){
                board[curr[0]][curr[1]] = (char)(count+'0');
            } else{
                for(int[] dir : dirs){
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];

                    if(r>=0 && c>=0 && r<m && c<n && board[r][c] =='E'){
                        board[r][c] = 'B';
                        q.add(new int[]{r,c});
                    }
                }
            }
        }
        return board;
    }

    private int countMines(char[][] board, int i, int j){
        int count = 0;
        for(int[] dir : dirs){
            int r = dir[0] + i;
            int c = dir[1] + j;
            if(r>=0 && c>=0 && r<m && c<n && board[r][c] == 'M'){
                count++;
            }
        }
        return count;
    }
}