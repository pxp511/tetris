package tetris;
////////
import java.util.*;
import java.io.*;

////////
public class HW2_0123456789 extends Tetris {
// enter your student id here
public String id = new String("1800012955");

// ####
public PieceOperator robotPlay() {
	if(piece_y<=18)
		return PieceOperator.Keep;
	if(detect()) {
		int minvalue=100000000;
		int expx=0;
		for(int i=-3;i<=9;i++)
		{
			for(int j=0;j<=23;j++)
			{
				if((!collision(i,j,piece))&&(!collision2(i, j,piece) ))
				{

					if(score(i,j,piece))
					{
						expx=i;
						if(piece_x<expx)
						{
							return PieceOperator.ShiftRight;
						}
						if(piece_x>expx)
						{
							return PieceOperator.ShiftLeft;
						}
						return PieceOperator.Keep;
					}
				
					int this_height=newheight(i,j,piece);
					int this_blank=newblank(i,j,piece);
					int this_value=100*this_blank+this_height;

					if(this_value<minvalue)
					{
						minvalue=this_value;
						expx=i;
					}
				}
				
			}
		}

		//rotate
		boolean [][]new_piece=new boolean[4][4];
		for (int y = 0; y < 4; y++)
			for (int x = 0; x < 4; x++)
				new_piece[y][x] = piece[x][3-y];
		
		for(int i=-3;i<=9;i++)
		{
			for(int j=0;j<=23;j++)
			{
				if((!collision(i,j,new_piece))&&(!collision2(i, j,new_piece) ))
				{
					if(score(i,j,new_piece))
					{
						
						return PieceOperator.Rotate;
					}
					int this_height=newheight(i,j,new_piece);
					int this_blank=newblank(i,j,new_piece);
					int this_value=100*this_blank+this_height;

					if(this_value<minvalue)
					{
						return PieceOperator.Rotate;
					}
				}
				
			}
		}
		//rotate
		
		if(piece_x<expx)
		{
			return PieceOperator.ShiftRight;
		}
		if(piece_x>expx)
		{
			return PieceOperator.ShiftLeft;
		}
		return PieceOperator.Keep;
		
	}
	
	return PieceOperator.Keep;
}
boolean detect() {
	boolean exist=false;
	for(int i=0;i<=9;i++)
	{
		for(int j=0;j<=23;j++)
		{
			if(!collision(i,j,piece))
			{
				exist=true;
			}
			
		}
	}
	return exist;
}
boolean collision(int i,int j,boolean [][]ipiece) 
{
	boolean [][] new_board=getBoard();
	//clear piece
	for (int y = 0; y < 4; y++)
	{
		for (int x = 0; x < 4; x++) 
		{
			if (piece[y][x]) 
			{
				new_board[piece_y-y][piece_x+x]=false;
			}
			
		}
	}
	//clear piece
	for (int y = 0; y < 4; y++)
	{
		for (int x = 0; x < 4; x++) 
		{
			
			if (!ipiece[y][x]) continue;
			if ((i+x<0 || i+x >= w ||j-y < 0 || j-y >= h || new_board[j-y][i+x])) 
			{ 
				return true;
			}
		}
	}
	return false;
}
boolean collision2(int i,int j,boolean [][]ipiece) 
{
	boolean [][] new_board=getBoard();
	//clear piece
	for (int y = 0; y < 4; y++)
	{
		for (int x = 0; x < 4; x++) 
		{
			if (piece[y][x]) 
			{
				new_board[piece_y-y][piece_x+x]=false;
			}
			
		}
	}
	//clear piece
	for (int y = 0; y < 4; y++)
	{
		for (int x = 0; x < 4; x++) 
		{

			if (!ipiece[y][x]) continue;
			
			for(int z=j-y+1;z<=19;z++)
			{
				if(new_board[z][i+x]==true)
					return true;
			}
		}
	}
	return false;
}
int newheight(int i,int j,boolean [][]ipiece)
{
	boolean [][] new_board=getBoard();
	int height=0;
	//clear piece
	for (int y = 0; y < 4; y++)
	{
		for (int x = 0; x < 4; x++) 
		{
			if (piece[y][x]) 
			{
				new_board[piece_y-y][piece_x+x]=false;
			}
			
		}
	}
	//clear piece
	
	//put newpiece
	for (int y = 0; y < 4; y++)
	{
		for (int x = 0; x < 4; x++) 
		{
			if (!ipiece[y][x]) continue;
			new_board[j-y][i+x]=ipiece[y][x];
				
			
		}
	}
	//put newpiece
	
	for(int a=0;a<=23;a++)
	{
		for(int b=0;b<=9;b++)
		{
			if(new_board[a][b])
			{
				height+=a;
			}
		}
	}
	for(int a=16;a<=23;a++)
	{
		for(int b=0;b<=9;b++)
		{
			if(new_board[a][b])
			{
				height+=30*(a-15);
			}
		}
	}
	for(int a=20;a<=23;a++)
	{
		for(int b=0;b<=9;b++)
		{
			if(new_board[a][b])
			{
				height+=10000*(a-15);
			}
		}
	}
	return height;
}
boolean score(int i,int j,boolean [][]ipiece) {
	boolean [][] new_board=getBoard();
	//clear piece
	for (int y = 0; y < 4; y++)
	{
		for (int x = 0; x < 4; x++) 
		{
			if (piece[y][x]) 
			{
				new_board[piece_y-y][piece_x+x]=false;
			}
			
		}
	}
	//clear piece
	
	//put expect piece
	for (int y = 0; y < 4; y++)
	{
		for (int x = 0; x < 4; x++) 
		{
			if (!ipiece[y][x]) continue;
			new_board[j-y][i+x]=ipiece[y][x];
				
			
		}
	}
	//put expect piece

	for(int a=0;a<=23;a++)
	{
		boolean noblank=true;
		for(int b=0;b<=9;b++)
		{
			if(!new_board[a][b])
				noblank=false;
		}
		if(noblank==true)
			return true;
	}
	return false;
}
int newblank(int i,int j,boolean [][]ipiece) {
	int blank=0;
	boolean [][] new_board=getBoard();
	//clear piece
	for (int y = 0; y < 4; y++)
	{
		for (int x = 0; x < 4; x++) 
		{
			if (piece[y][x]) 
			{
				new_board[piece_y-y][piece_x+x]=false;
			}
			
		}
	}
	//clear piece
	
	//put expect piece
	for (int y = 0; y < 4; y++)
	{
		for (int x = 0; x < 4; x++) 
		{
			if (!ipiece[y][x]) continue;
			new_board[j-y][i+x]=ipiece[y][x];
				
			
		}
	}
	//put expect piece
	for(int a=0;a<=9;a++)
	{
		int store=0;
		for(int b=23;b>=0;b--)
		{
			if(new_board[b][a])
			{
				store=b;
				break;
			}
		}
		for(int bb=store-1;bb>=0;bb--)
		{
			if(!new_board[bb][a])
				blank++;
		}
	}
	return blank;
}
}

