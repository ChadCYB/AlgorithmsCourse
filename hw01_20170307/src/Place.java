
public class Place {
	private String name="";
	private double lat,lon;
	
	public Place(String name, double lat, double lon){
		this.name = name;
		this.lat = lat;
		this.lon = lon;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * @return the lon
	 */
	public double getLon() {
		return lon;
	}

	/**
	 * @param lon the lon to set
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}

	public void show() {
		System.out.println("Place: "+name+"("+lat+":"+lon+")");
	}
	
	
}
