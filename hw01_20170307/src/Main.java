import java.util.ArrayList;

public class Main {
	public static void main(String arg[]){

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
		
//		int place_mount = 10000;
//		ArrayList<Place> point_arr = Map.makePlace(place_mount);
		
		ArrayList<Place> point_arr = Map.addPlace(obj_arr);
		System.out.println("Your have "+point_arr.size()+" places");
		System.out.println("-----------------------------------------------------");
		
//------暴力破解------------------------------------------
		long startTime = System.currentTimeMillis();
		
		Place place01 = null, place02 = null;
		int leng = point_arr.size();
		double dist_min = 1000000000d;
		for(int i=0 ; i<leng ; i++){
			Place p1 = point_arr.get(i);
			for(int j=0 ; j<leng ; j++){
				Place p2 = point_arr.get(j);
				double dist = Map.dist(p1,p2);
				if(i!=j && dist<dist_min){
					dist_min = dist;
					place01 = p1;
					place02 = p2;
				}
			}	
		}
			
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		place01.show();
		place02.show();
//		System.out.println("dist: "+Point.dist(place01, place02)+" / "+dist+" km");
		System.out.println("dist: "+dist_min+" km");
		System.out.println("totalTime: "+totalTime/1000d+"sec");
		System.out.println("-----------------------------------------------------");
		
//------ Divide and Conquer -----------------------------------------------		
		long startTime2 = System.currentTimeMillis();
		
		ArrayList<Place> groupA = new ArrayList<>();
		ArrayList<Place> groupB = new ArrayList<>();
		
		//分類
		for(Place p:point_arr){
			if(p.getLon()>120){
				groupA.add(p);
			}else{
				groupB.add(p);
			}
		}
		
		//找出兩邊最小距離點
		Place[] ga = Map.findNearest(groupA);
		Place[] gb = Map.findNearest(groupB);
		Place[] nearestPlace = (Map.dist(ga)< Map.dist(gb))?ga:gb;
		double nearestDist = Map.dist(nearestPlace);
		
		//中界線
		ArrayList<Place> groupC = new ArrayList<>();
		for(Place p:point_arr){
			if((120-nearestDist)<p.getLon() && p.getLon()<(120+nearestDist)){
				groupC.add(p);
			}
		}
		
		Place[] gc = Map.findNearest(groupC);
		try{
			nearestPlace = (Map.dist(gc)< nearestDist)?gc:nearestPlace;
		}catch(Exception ex){
			System.out.println("group3 no found!");
		}
				
		long endTime2   = System.currentTimeMillis();
		long totalTime2 = endTime2 - startTime2;
		
		
		nearestPlace[0].show();
		nearestPlace[1].show();
		System.out.println("dist: "+Map.dist(nearestPlace)+" km");
		System.out.println("totalTime2: "+totalTime2/1000d+"sec");
	}
}
