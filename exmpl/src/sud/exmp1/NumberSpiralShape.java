package sud.exmp1;

public class NumberSpiralShape {

	/**

	 * @param args

	 */
	public static void main(String[] args) {
		int i = 7, x=0, y=0;
		int tab [][]= new int [i][i];
		while(i>=1){
			for(int j=0; j<i; j++)
				tab[x][y++] = i;
			x++;
			i--;
			y--;
			if(i>=1){
				for(int j=0; j<i; j++)
					tab[x++][y] = i;
				x--;
				i--;
				y--;
			}
			if(i>=1){
				for(int j=0; j<i; j++)
					tab[x][y--] = i;
				x--;
				i--;
				y++;
			}
			if(i>=1){
				for(int j=0; j<i; j++)
					tab[x--][y] = i;
				y++;
				i--;
				x++;
			}
		}
		for(i=6; i>=0; i--){
			for(int j=6; j>=0; j--)
				if(tab[i][j] == 0) 
					System.out.print("  ");
				else
					System.out.print(tab[i][j] + " ");
			System.out.println();
		}
	}
}