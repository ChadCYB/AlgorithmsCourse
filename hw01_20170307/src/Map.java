import java.util.ArrayList;

public class Map {
	
	public Map(){	}
	
	public static double dist(Place p1, Place p2){
		double x1,x2,y1,y2;
		x1=p1.getLat();
		x2=p2.getLat();
		y1=p1.getLon();
		y2=p2.getLon();
		return distance(x1,y1,x2,y2,"K");
	}
	public static double dist(Place[] p){
		double x1,x2,y1,y2;
		x1=p[0].getLat();
		x2=p[1].getLat();
		y1=p[0].getLon();
		y2=p[1].getLon();
//		return Math.pow((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2), 0.5);
		return distance(x1,y1,x2,y2,"K");
	}
	
	public static ArrayList<Place> makePlace(long mount){
		ArrayList<Place> p_arr = new ArrayList<>();
		long count = 0;
		while(++count <= mount){
			double lon = Math.random()*4+118; //118-122
			double lat = Math.random()*3+23;  //24-26
			p_arr.add(new Place("p"+count, lat ,lon));
		}
		return p_arr;
	}
	
	public static ArrayList<Place> addPlace(Object[][] obj_arr) {
		ArrayList<Place> p_arr = new ArrayList<>();
		for(Object[] obj: obj_arr){
			p_arr.add(new Place((String)obj[0], (double)obj[1] , (double)obj[2]));
		}
		return p_arr;
	}
	
	public static boolean isSame(Place p1, Place p2){
		return (p1.getLat()==p2.getLat() && p1.getLon()==p2.getLon());
	}
	
	public static Place[] findNearest(ArrayList<Place> arr){
//		double min = dist(arr.get(0),arr.get(1));
		Place[] result = new Place[2];
		double min = 1000000d;
		int arr_len = arr.size();
		for(int i=0 ; i<arr_len ; i++){
			Place p1 = arr.get(i);
			for(int j=0 ; j<arr_len ; j++){
				Place p2 = arr.get(j);
				if(i!=j && Map.dist(p1,p2)<min){
					min = Map.dist(p1,p2);
					result[0] = p1;
					result[1] = p2;
				}
			}	
		}
//		for(Place p1:arr){
//			for(Place p2:arr){
//				if(!(isSame(p1,p2)) && dist(p1,p2)<min){
//					min = dist(p1,p2);
//					result[0] = p1;
//					result[1] = p2;
//				}
//			}
//		}
		return result;
	}
	
	//DistanceCalculator
	private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = rad2deg(Math.acos(dist)) * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}
		return (dist);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}

