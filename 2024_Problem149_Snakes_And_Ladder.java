//909. Snakes and Ladder - https://leetcode.com/problems/snakes-and-ladders/description/
//Time Complexity: O(n*n)
//Space Complexity: O(1)

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        //flatten 2D Matrix into 1D array
        //using boolean visited flag with direction
        int[] arr = new int[n*n];
        boolean flag=true;
        int r = n-1, c = 0; //row becomes n-1, as the game starts from bottom row
        for(int i=0; i<n*n; i++){
            if(board[r][c] == -1){
                arr[i] = board[r][c];
            }else{
                arr[i] = board[r][c] - 1; //idx-1 as board starts from 1, arr starts from 0
            }
            //when flag is true: moving left->right
            if(flag){ //left->right
                c++; //column++
                if(c == n){ //last column
                    flag = false; //change direction
                    c = n-1; //right->left; starts from n-1
                    r--;
                }
            }else{
                c--;
                if(c == -1){
                    flag = true;
                    r--;
                    c=0;
                }
            }
        }

        //traverse over 1D array
        //bfs with level for returning min moves
        Queue<Integer> q = new LinkedList<>();
        q.add(0); //add initial value of the board
        int level=0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                int currIdx = q.poll(); //initially we are at 0th idx
                //roll the dice
                for(int d=1; d<=6; d++){
                    int newIdx = currIdx + d;
                    //if reached last idx or has a ladder to reach destination
                    if(newIdx == n*n-1 || arr[newIdx] == n*n-1) return level+1;
                    if(arr[newIdx] != -2){
                        if(arr[newIdx] == -1){ //normal traversal, add to q
                            q.add(newIdx);
                        } else{
                            q.add(arr[newIdx]);
                        }
                        arr[newIdx] = -2; //mark visited
                    }
                }
            }
            level++;
        }
        return -1;
    }
}