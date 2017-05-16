
public class Main {
	public static void main(String args[]){
//		int d[] = {10,5,10,5,10};
		int d[] = {10,5,2,20,12,4,60};
		int n=d.length-1;
		int N[][]= new int[n][n];
		
		for(int i=0 ; i<n ; i++){
			N[i][i] = 0;
		}
		for(int b=1 ; b<n ; b++){
			for(int i=0 ; i<n-b ; i++){
				int j = i+b;
				N[i][j] = Integer.MAX_VALUE;
				for(int k=i ; k<j ; k++){
					System.out.println(N[i][j]+" vs "+(N[i][k]+N[k+1][j]+d[i]*d[k+1]*d[j+1]));
					N[i][j] = Math.min(N[i][j], N[i][k]+N[k+1][j]+d[i]*d[k+1]*d[j+1]);
				}
			}
		}
		System.out.println("\n=== result ===");
		for(int[] i: N){
			for(int j: i)	System.out.print(j+"\t");
			System.out.println();
		}
	}
}
