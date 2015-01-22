import java.util.ArrayList;
import java.util.List;




public class Hotel {
	private List<Room> rooms;
	private String naam;
	
	public Hotel(String naam){
		this.naam = naam;
		this.rooms = new ArrayList<Room>();
	}
	public Room getFree(){
		Room result = null;
		for(int i =0; i < rooms.size(); i++){
			if(rooms.get(i).isEmpty()){
				result = rooms.get(i);
				break;
			}
		}
		return result;
	}
	public static void main(String[] args){
		Hotel h = new Hotel("Fawlty Towers");
		System.out.println(h.getFree());
	}
	
	
}
