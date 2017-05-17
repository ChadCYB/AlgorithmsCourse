import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		
		Object[][] obj_arr = {
				{"八卦山", 24.015465, 120.115154},
				{"古寧頭戰史館",24.482986, 118.320937},
				{"國立自然科學博物館",24.156744, 120.665553},
				{"勝興車站",24.388101, 120.782056},
				{"阿里山",23.508918, 120.802188},
				{"愛河",22.652549, 120.302710},
				{"奇美博物館",22.934540, 120.226017},
				{"跨海大橋",23.651176, 119.548552},
				{"北港朝天宮",23.567659, 120.304667},
				{"國立臺灣史前文化博物館",22.760701, 121.091366},
				{"太魯閣峽谷",24.158726, 121.621630},
				{"羅東夜市",24.676870, 121.769641},
				{"九份老街",25.110410, 121.845793},
				{"基隆港",25.138645, 121.750666},
				{"石門水庫",24.812305, 121.245784},
				{"合歡山森林遊樂區",24.181390, 121.281405},
				{"六福村主題遊樂園",24.825177, 121.180457},
				{"一零一大樓",25.033976, 121.564756},
				{"城隍廟",24.804502, 120.965475},
				{"國立海洋生物博物館",22.046018, 120.698904}};
			
//			int place_mount = 10000;
//			ArrayList<Place> point_arr = Map.makePlace(place_mount);	
			ArrayList<Place> point_arr = Map.addPlace(obj_arr);
			int point_mount = point_arr.size();
			System.out.println("Your have "+point_mount+" places");
			System.out.println("-----------------------------------------------------");
			
			double[][] distArr = Map.dist(point_arr);
/*			for(double[] i: distArr){
				for(double j: i){
					System.out.print(j+"\t");
				}
				System.out.print("\n");
			}*/
			
			ArrayList<Place> result_path = new ArrayList<Place>();
			boolean[] visited = new boolean[point_mount];
			int cur_index = 0, next_index = 0, visited_count = 0;
			double total_dist = 0;
			
			while(++visited_count < point_mount){
				double[] cur_dist = distArr[cur_index];
				result_path.add(point_arr.get(cur_index));
				visited[cur_index] = true;
				
				double min = Double.MAX_VALUE;
				for(int i=0 ; i<cur_dist.length ; i++){
					if(cur_dist[i] < min && i!=cur_index && !visited[i]){
						System.out.println("<"+i+">"+"c_d: "+cur_dist[i]+"\tmin: "+min);
						min = cur_dist[i];
						next_index = i;
					}
				}
				System.out.println("---");
				cur_index = next_index;
				total_dist += min;
			}
			result_path.add(point_arr.get(cur_index));	//add the last place;
			
			int count = 0;
			for(Place i: result_path){
				System.out.print("<"+ ++count +">");
				i.show();
			}
			System.out.println("Total Distance:"+total_dist+"km");
			
	}
}
