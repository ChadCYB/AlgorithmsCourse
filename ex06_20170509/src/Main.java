
public class Main {

	public static void main(String[] args) {
		String X = "BDCABA", Y = "ABCBDAB";
		int m = X.length(), n = Y.length();
		int[][] c = new int[m+1][n+1];
		String[][] b= new String[m+1][n+1];
		
		lcs(X,Y,c,b);
		
		for(int[] i: c){
			for(int j: i) System.out.print(j+"\t");
			System.out.println();
		}
		System.out.println();
		for(String[] i: b){
			for(String j: i) System.out.print(j+"\t");
			System.out.println();
		}

		System.out.println("\nAns: ");
		print_lcs(7-1,8-1,b,X);
	}
	public static void lcs(String X, String Y, int[][] c, String[][] b){
		int m = X.length(), n = Y.length();
		
		for(int i=0 ; i<=m ; i++) c[i][0] = 0;
		for(int j=0 ; j<=n ; j++) c[0][j] = 0;
		for(int i=1 ; i<=m ; i++) b[i][0] = X.charAt(i-1)+"";
		for(int j=1 ; j<=n ; j++) b[0][j] = Y.charAt(j-1)+"";
		b[0][0] = "X\\Y";
		
		for(int i=1 ; i<=m ; i++){
			for(int j=1 ; j<=n ; j++){
				if(X.charAt(i-1) == Y.charAt(j-1)){	//(X[i-1] == Y[j-1])
					c[i][j] = c[i-1][j-1]+1;
					b[i][j] = "¡ø";
				}else if(c[i-1][j] >= c[i][j-1]){
					c[i][j] = c[i-1][j];
					b[i][j] = "¡ô";
				}else{
					c[i][j] = c[i][j-1];
					b[i][j] = "¡ö";
				}
			}
		}
	}
	public static void print_lcs(int i, int j, String[][] b, String X){
		if(i==0 || j==0) return;
		if(b[i][j].equals("¡ø")){
			print_lcs(i-1, j-1, b, X);
//			System.out.println("i:j¡ø "+i+":"+j+" "+X.charAt(i-1)+"<<");
			System.out.print(X.charAt(i-1)+"");
		}else if(b[i][j].equals("¡ö")){
//			System.out.println("i:j¡ö "+i+":"+j);
			print_lcs(i, j-1, b, X);
		}else if(b[i][j].equals("¡ô")){
//			System.out.println("i:j¡ô "+i+":"+j);
			print_lcs(i-1, j, b, X);
		}
		
	}
}
