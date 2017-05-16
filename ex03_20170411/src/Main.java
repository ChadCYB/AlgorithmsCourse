
public class Main {
	public static void main(String arg[]){
		float k = 18f;
		float n = k*2;
				
		System.out.println(bin2(n,k));
		System.out.println(bin(n,k));
	}
	public static float bin(float n, float k){
		if( k ==0 || n == k)
			return 1;
		else
			return bin(n-1, k-1)+bin(n-1,k);
	}
	public static float bin2(float n, float k){
		float B[][] = new float[(int) (n+1)][(int) (k+1)];
		for(int i=0 ; i<=n ; i++){
			for(int j=0 ; j<=Math.min(i, k) ; j++){
				if(j==0 || j==i){
					B[i][j] = 1;
				}else{
					B[i][j] = B[i-1][j-1] + B[i-1][j];
				}
			}
		}
		return B[(int) n][(int) k];
	}
}
