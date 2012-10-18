package sud.exmp1;

public class Printing_Matrix_in_Spiral_Order {

	/*
	 * iven a matrix (2D array) of m x n elements (m rows, n columns), 
	 * write a function that prints the elements in the array in a spiral manner.
	 * For example:
	 * int[] test={
	 * 1, 2, 3, 4,
	 * 5, 6, 7, 8,
	 * 9,10,11,12,
	 * 13,14,15,16
	 * }
	 * 
	 * output: 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
	 */
	public static void showSpiralOrder(int[][] test){
		int TopLeftRow=0;
		int TopLeftCol=0;
		int BottomRightRow=test.length-1;
		int BottomRightCol=test[0].length-1;
		while(TopLeftRow <= BottomRightRow && TopLeftCol <= BottomRightCol){
			int beginX=TopLeftRow;
			int beginY=TopLeftCol;
			int endX=BottomRightRow;
			int endY=BottomRightCol;
			if(beginX == endX){
				for(int i = beginY; i <= endY; i++){
					System.out.print(test[beginX][i]+" ");
				}
			}
			else if(beginY == endY){
				for(int i = beginX; i <= endX; i++){
					System.out.print(test[i][beginY]+" ");
				}
			}
			else{
				int curCol = beginY;
				while(curCol != endY){
					System.out.print(test[beginX][curCol]+" ");
					curCol++;
				}
				int curRow = beginX; 
				while(curRow != endX){
					System.out.print(test[curRow][endY]+" ");
					curRow++;
				}
				while(curCol != beginY){
					System.out.print(test[endX][curCol]+" ");
					curCol--;
				}
				while(curRow != beginX){
					System.out.print(test[curRow][beginY]+" ");
					curRow--;
				}
			}
			TopLeftRow++;
			TopLeftCol++;
			BottomRightRow--;
			BottomRightCol--;
		}
	}
	public static void main(String[] args) {
		int[][] test={
				{ 1, 2, 3, 4},
				{ 5, 6, 7, 8},
				{ 9,10,11,12},
				{13,14,15,16}
		};
		showSpiralOrder(test);

	}

}