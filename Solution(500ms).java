import java.util.HashSet;

/*
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * Empty cells are indicated by the character '.'.
 * You may assume that there will be only one unique solution.
*/
public class Solution
{
	//检查当前不完整数独是不是一个有效数独，规则：每行每列每个九宫格为1-9且不重复
	public boolean isValidSudoku(char[][] board)
	{
		HashSet<Character> set = new HashSet<>();
		// check each row
		for (int i = 0; i < 9; i++)
		{
			set.clear();
			for (int j = 0; j < 9; j++)
			{
				if (board[i][j] != '.')
				{
					if (set.contains(board[i][j]))
					{
						return false;
					}
					else
					{
						set.add(board[i][j]);
					}
				}
			}
		}

		// check each col
		for (int j = 0; j < 9; j++)
		{
			set.clear();
			for (int i = 0; i < 9; i++)
			{
				if (board[i][j] != '.')
				{
					if (set.contains(board[i][j]))
					{
						return false;
					}
					else
					{
						set.add(board[i][j]);
					}
				}
			}
		}

		// check each jiugongge
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				set.clear();
				for (int m = 0; m < 3; m++)
				{
					for (int n = 0; n < 3; n++)
					{
						if (board[i * 3 + m][j * 3 + n] != '.')
						{
							if (set.contains(board[i * 3 + m][j * 3 + n]))
							{
								return false;
							}
							else
							{
								set.add(board[i * 3 + m][j * 3 + n]);
							}
						}
					}
				}
			}
		}
		return true;
	}

	public boolean solve(char[][] board)
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{

				if (board[i][j] == '.')
				{
					for (Character c = '1'; c <= '9'; c++)
					{
						board[i][j] = c;
						if (isValidSudoku(board))
						{
							if (solve(board))
							{
								return true;
							}
							else
							{
								board[i][j] = '.';
							}
						}
						else
						{
							board[i][j] = '.';
						}

					}

					return false;
				}
			}
		}
		//终止条件
		if (isValidSudoku(board))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void solveSudoku(char[][] board)
	{
		solve(board);
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