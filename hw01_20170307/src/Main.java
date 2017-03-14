import java.util.ArrayList;

public class Main {
	public static void main(String arg[]){
//		double[][] point_arr = {{25.033976,121.564756},
//				{25.138645,121.750666},
//				{24.812305,121.245784},
//				{24.804502,120.965475},
//				{24.388101,120.782056},
//				{24.156744,120.665553},
//				{24.482986,118.320937},
//				};
		
		int place_mount = 1000000;
		ArrayList<Place> point_arr = Point.makePlace(place_mount);
		System.out.println("Your have "+place_mount+" places");
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
				double dist = Point.dist(p1,p2);
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
		Place[] ga = Point.findNearest(groupA);
		Place[] gb = Point.findNearest(groupB);
		Place[] nearestPlace = (Point.dist(ga)< Point.dist(gb))?ga:gb;
		double nearestDist = Point.dist(nearestPlace);
		
		//中界線
		ArrayList<Place> groupC = new ArrayList<>();
		for(Place p:point_arr){
			if((120-nearestDist)<p.getLon() && p.getLon()<(120+nearestDist)){
				groupC.add(p);
			}
		}
		
		Place[] gc = Point.findNearest(groupC);
		try{
			nearestPlace = (Point.dist(gc)< nearestDist)?gc:nearestPlace;
		}catch(Exception ex){
			System.out.println("group3 no found!");
		}
				
		long endTime2   = System.currentTimeMillis();
		long totalTime2 = endTime2 - startTime2;
		
		
		nearestPlace[0].show();
		nearestPlace[1].show();
		System.out.println("dist: "+Point.dist(nearestPlace)+" km");
		System.out.println("totalTime2: "+totalTime2/1000d+"sec");
	}
}
