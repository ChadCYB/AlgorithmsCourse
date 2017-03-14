import java.util.Arrays;

public class MaxRecursion {
	public static void main(String arg[]){
		int[] arr = {12,58,13,54,1,4,0,5,8,15,6,13,22,46,55,88,45,11,213,41,56,38,50,19,84,21,556,41,733};
		System.out.println(recursiveMax(arr,arr.length));
	}
	public static int recursiveMax(int[] A, int n){
		if(n<=1){
			return A[0];
		}
		return Math.max(recursiveMax(cutArr(A)[0], n/2), recursiveMax(cutArr(A)[1], n-n/2));
	}
	public static int[][] cutArr(int[] A){
		int a[] = Arrays.copyOfRange(A, 0, A.length/2);
		int b[] = Arrays.copyOfRange(A, A.length/2, A.length);
		int c[][] = {a,b};
		return c;
	}
}

