import java.io.*;
import java.util.*;

public class Top_k {
	
	public static void main(String[] args){
		
		//Initializations & Declarations
		int id;
		int likes;
		int k = Integer.parseInt(args[0]);
		int counter = 0;
		
		String title;
		String line;
		String a;
		String filename = args[1];
		
		Song[] songs = new Song[100];
		
		//Opening File
		BufferedReader reader = null;
		
		try {
			
			reader = new BufferedReader(new FileReader(filename));
				
			line= reader.readLine();
			
			while(line!=null){
				
				a = line.trim().substring(0, line.indexOf(' '));
				id = Integer.parseInt(a);
				title = line.trim().substring(a.length(), line.lastIndexOf(' '));
				likes = Integer.parseInt(line.trim().substring(line.trim().lastIndexOf(' ') + 1));
				
				Song s = new Song(id, title, likes);
				songs[counter] = s;
				
				counter++;
				
				line = reader.readLine();
			}
			
		}
		
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		catch (IOException e) {
			e.printStackTrace();
		} 
			
		finally {	
		
			try {
					
				if (reader != null) {
					
					reader.close();
				}
				
			} 
			
			catch (IOException e) {
				
				System.err.println("Error closing file.");
			}
			
		}
		
		counter--;
		QuickSort(songs, 0, counter); 
		for(int i = counter; i > counter - k; i--){
			
			System.out.print(songs[i].id);
			System.out.print(" ");
			System.out.print(songs[i].title);
			System.out.print(" ");
			System.out.print(songs[i].likes);
			System.out.println();
			
		}

	}


	public static void QuickSort(Song songs[], int begin, int end) {
		
		if (begin < end) {
			
			int partitionIndex = partition(songs, begin, end);
	 
			QuickSort(songs, begin, partitionIndex - 1);
			QuickSort(songs, partitionIndex + 1, end);
		}
	}
	
	private static int partition(Song songs[], int begin, int end) {
		
		Song pivot = songs[end];
		int i = (begin - 1);
		int result;
	 
		for (int j = begin; j < end; j++) {
			
			result = songs[j].compareTo(pivot);
			
			if ((result == 0) || (result == 2)) {
				
				i++;
	 
				Song swapTemp = songs[i];
				songs[i] = songs[j];
				songs[j] = swapTemp;
			}
		}
	 
		Song swapTemp = songs[i + 1];
		songs[i + 1] = songs[end];
		songs[end] = swapTemp;
	 
		return i + 1;
	}
}
