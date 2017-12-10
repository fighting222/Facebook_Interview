//!!!!!!说method2！！！！！

/**思路：
这道题利用的是HashSet的唯一性来帮助check。
1. 先按每行check，如果是'.'说明还没填字，是合法的，往下走，如果没在set中存过就加一下，如果便利过程中出现了在set中存在的key值，说明有重复的数字在一行，不合法，return false。
2. 再按照这个方法check列。
3. 最后按照这个方法check小方块。check for each sub-grid
注意小方块的ij取法。对于当前这块板子来说，总共有9个小方格，按0~8从左到右依次编号。
按编号求'/'就是求得当前小方格的第一行横坐标，因为每个小方格有3行，所以循环3次。
按编号求'%'就是求得当前小方格的第一列纵坐标，因为每个小方格有3列，所以循环3次。 
对9个小方格依次走一边，就完成了检查小方格的工作。
*/
//Method1:
class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character> set = new HashSet<>();
        //check for each row
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    continue;
                } 
                if (set.contains(board[i][j])) {
                    return false;
                }
                set.add(board[i][j]);
            }
            set.clear();  //clear() method is used to remove all of the elements from this set.
        }
        //check for each column
        for (int j = 0; j < 9; ++j) {
            for (int i = 0; i < 9; ++i) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (set.contains(board[i][j])) {
                        return false;
                }
                set.add(board[i][j]);
            }
            set.clear();
        }
    
        //check for each sub-grid
        for (int k = 0; k < 9; ++k) {
            for (int i = k / 3 * 3; i < k / 3 * 3 + 3; ++i) {  //each row in sub-grid
                for (int j = (k % 3) * 3; j < (k % 3) * 3 + 3; ++j) {
                    if (board[i][j] == '.') {
                        continue;
                    }
                    if (set.contains(board[i][j])) {
                        return false;
                    }
                    set.add(board[i][j]);
                }
            }
            set.clear();
        }
        return true;
    }
}


//Method2:简单一些的写法，思路一样
class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i<9; i++){
            HashSet<Character> rows = new HashSet<Character>();
            HashSet<Character> columns = new HashSet<Character>();
            HashSet<Character> cube = new HashSet<Character>();
            for (int j = 0; j < 9;j++){
                if(board[i][j]!='.' && !rows.add(board[i][j]))
                    return false;
                if(board[j][i]!='.' && !columns.add(board[j][i]))
                    return false;
                int RowIndex = 3 * (i / 3);
                int ColIndex = 3 * (i % 3);
                if(board[RowIndex + j / 3][ColIndex + j % 3]!='.' && !cube.add(board[RowIndex + j / 3][ColIndex + j % 3]))
                    return false;
            }
        }
        return true;
    }
}
