
public class Main {
	public static void main( String[] args){
		int a[][] = new int[2][3];
		int b[][] = new int[3][2];
		a = arrRnd(a);
		b = arrRnd(b);
		int[][] c = clac(a,b);
		printArr(a);
		printArr(b);
		printArr(c);
	}
	
	public static int[][] arrRnd(int[][] arr){
		for(int i=0 ; i<arr.length; i++){
			for(int j=0 ; j<arr[0].length; j++){
				arr[i][j] = (int)(Math.random()*5)+1;
			}
		}
		return arr;
	}
	
	public static int[][] clac(int[][] a, int[][]b){
		int[][] c = new int[2][2];
		for(int i=0; i<c.length ; i++){
			for(int j=0; j<c[0].length ; j++){
				int sum=0;
				for(int k=0 ; k<a.length ; k++){
					sum += a[i][k] * b[k][j];
				}
				c[i][j] = sum;
			}
		}
		return c;
	}
	
	public static void printArr(int[][] arr){
		for(int[] i:arr){
			for(int j: i){
				System.out.print(j+"\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
