
/*
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * Empty cells are indicated by the character '.'.
 * You may assume that there will be only one unique solution.
*/
public class Solution
{
	public void solveSudoku(char[][] board)
	{
		if (board == null || board.length == 0)
			return;
		solve(board);
	}

	public boolean solve(char[][] board)
	{
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[0].length; j++)
			{
				if (board[i][j] == '.')
				{
					for (char c = '1'; c <= '9'; c++)
					{// trial. Try 1 through 9
						if (isValid(board, i, j, c))
						{
							board[i][j] = c; // Put c for this cell

							if (solve(board))
								return true; // If it's the solution return true
							else
								board[i][j] = '.'; // Otherwise go back
						}
					}

					return false;
				}
			}
		}
		return true;
	}

	private boolean isValid(char[][] board, int row, int col, char c)
	{
		for (int i = 0; i < 9; i++)
		{
			if (board[i][col] != '.' && board[i][col] == c)
				return false; // check row
			if (board[row][i] != '.' && board[row][i] == c)
				return false; // check column
			if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.'
					&& board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)
				return false; // check 3*3 block
		}
		return true;
	}
	public static void main(String[] args)
	{
		String[] a = { "..9748...", "7........", ".2.1.9...", "..7...24.", ".64.1.59.", ".98...3..", "...8.3.2.",
				"........6", "...2759.." };
		char[][] carray = new char[9][9];
		for (int i = 0; i < a.length; i++)
		{
			carray[i] = a[i].toCharArray();
		}
		for (int i = 0; i < carray.length; i++)
		{
			for (int j = 0; j < carray[i].length; j++)
			{
				System.out.print(carray[i][j] + " ");
			}
			System.out.println();
		}
		double time = System.currentTimeMillis();
		new Solution().solveSudoku(carray);
		time = System.currentTimeMillis() - time;
		System.out.println("*******************************************************");
		for (int i = 0; i < carray.length; i++)
		{
			for (int j = 0; j < carray[i].length; j++)
			{
				System.out.print(carray[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("*******************************************************");
		System.out.println(time + "ms");
	}
}