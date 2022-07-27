import java.util.Comparator;

public class Song implements Comparable<Song>{
	
	int id;
	String title;
	int likes;
	
	//Constructors
	
	public Song(){
		
		id = 0;
		title = null;
		likes = 0;
	}
	
	public Song(int id, String title, int likes){
		
		this.id = id;
		this.title = title;
		this.likes = likes;
	}
	
	//Setters & Getters
	
	int getID(){
		
		return id;
	}
	
	String getTitle(){
		
		return title;
	}
	
	int getLikes(){
		
		return likes;
	}
	
	void setID(int id){
		
		this.id = id;
	}
	
	void setTitle(String title){
		
		this.title = title;
	}
	
	void setLikes(int likes){
		
		this.likes = likes;
	}
	
	public int compareTo(Song song){
		
		if(this.likes > song.likes){
			
			return 1;
			
		}else if(this.likes < song.likes){
			
			return 0;
			
		}else{
			
			if(this.title.length() < song.title.length()){
				
				return 1;
				
			}else if(this.title.length() > song.title.length()){
				
				return 0;
				
			}else{
				
				return 2;
			}
			
		}
	}
	
}
