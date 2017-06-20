
public class Main {
	public static void main(String[] args) {
		
		/*int D [][] = {
				{0		,9999	,187	,9999	,1258	,867	,9999	,2704	,9999},
				{9999	,0		,144	,9999	,9999	,849	,9999	,9999	,9999},
				{187	,144	,0		,184	,1090	,740	,1391	,9999	,999},
				{9999	,9999	,184	,0		,946	,621	,9999	,9999	,9999},
				{1258	,9999	,1090	,946	,0		,9999	,1121	,9999	,2342},
				{867	,849	,740	,621	,9999	,0		,802	,1846	,9999},
				{9999	,9999	,1391	,9999	,1121	,802	,0		,1464	,1235},
				{2704	,9999	,9999	,9999	,9999	,1846	,1464	,0		,337},
				{9999	,9999	,9999	,9999	,2342	,9999	,1235	,337	,0}};*/
		double D [][] = {
				{0		,9999	,9999	,9999	,40		,9999	,9999},
				{9999	,0		,20		,9999	,9999	,9999	,50},
				{9999	,15		,0		,9999	,90		,9999	,9999},
				{9999	,9999	,30		,0		,9999	,9999	,9999},
				{25		,9999	,25		,75		,0		,10		,95},
				{9999	,9999	,9999	,60		,5		,0		,9999},
				{50		,9999	,9999	,9999	,9999	,9999	,0}};
		FloydWarshall ff = new FloydWarshall(D);
		ff.graph = D;
		ff.floydWarshall();
		
//		int[][] G = D;
/*		int[][] G = initMap(9);
		System.out.println("<--init-->");
		showMap(G);
		System.out.println("\n<--Reslut-->");
		showMap(AllPair(G));*/
	}
	
	public static int[][] AllPair(int[][] G){
		int city_num = G.length;
		int[][][] D  = new int[city_num+1][city_num][city_num];
		for(int i=0 ; i<city_num ; i++){
			for(int j=0 ; j<city_num ; j++){
				if(i == j){
					D[0][i][j] = 0;
				}else if(G[i][j] != 0){
					D[0][i][j] = G[i][j];
				}else{
					D[0][i][j] = 9999;
				}
			}
		}
		
		for(int k=1 ; k<=city_num ; k++){
			for(int i=0 ; i<city_num ; i++){
				for(int j=0 ; j<city_num ; j++){
					D[k][i][j] = Math.min(D[k-1][i][j],D[k-1][i][k-1]+D[k-1][k-1][j]);
				}
			}
//			System.out.println("\n--"+k+"--");
//			showMap(D[k]);
		}
		return D[city_num];
	}
	
	public static int[][] initMap(int city_num){
		int[][] G = new int[city_num][city_num];
		int road = (int)(Math.random()*((city_num*(city_num-1))/2))+1;
		int city_A,city_B,dist;
		
		for(int i=0 ; i<road ; i++){
			city_A = (int)(Math.random()*city_num);
			do{
				city_B = (int)(Math.random()*city_num);
			}while(city_B == city_A);
			dist = (int)(Math.random()*1000)+1;
			
			if(G[city_A][city_B] != -1){
				G[city_A][city_B] = dist;
				G[city_B][city_A] = dist;
			}else{
				i--;
			}
		}
		return G;
	}
	
	public static void showMap(int[][] G){
		for(int[] i:G){
			for(int j:i) System.out.print(j+"\t");
			System.out.println();
		}
	}
}
