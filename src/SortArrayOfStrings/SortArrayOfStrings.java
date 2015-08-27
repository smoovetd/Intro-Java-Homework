import java.util.Scanner;

public class SortArrayOfStrings {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int maximumWords = scanner.nextInt();
		String[] inputWords = new String[maximumWords];
		
		// Filling Array of Words
		for ( int i = 0 ; i < maximumWords ; i ++){
			inputWords[i] = scanner.next();
		}
		
		// Optional - verify that Array Works with empty lines
		/*for ( int i = 0 ; i < n ; i ++){
			System.out.println(String.format("%d. %s",i,inputWords[i]));
		}*/
		
		// Sort Array
		while(true){
			boolean isSortApplied = false;
			for( int i = 0; i < maximumWords - 1 ; i++){
				if(!isFirstStringBeforeSecond(inputWords[i],inputWords[i+1])){
					String swapPlacesHelper = inputWords[i];
					inputWords[i] = inputWords[i+1];
					inputWords[i+1] = swapPlacesHelper;
					isSortApplied = true;
				}
			}
			if(!isSortApplied){
				break;
			}
		}
		
		
		for ( int i = 0 ; i < maximumWords ; i ++){
			System.out.println(String.format("%d. %s",i + 1,inputWords[i]));
		}
		
	}
	// Sort is checking Alphabetically and in case of matching words the smallest word is taken first.
	public static boolean isFirstStringBeforeSecond(String firstString, String secondString){
		char[] firstStringChar = firstString.toCharArray();
		char[] secondStringChar = secondString.toCharArray();
		
		int firstLenght = firstStringChar.length;
		int secondLenght = secondStringChar.length;
		
		int sortLoopLenght = firstLenght < secondLenght?firstLenght:secondLenght;
		
		for(int i = 0 ; i<sortLoopLenght ; i++){
			if(firstStringChar[i] == secondStringChar[i]){
				continue;
			}
			
			if(firstStringChar[i] > secondStringChar[i]){
				return false;
			} else{
				return true;
			}
		}
		
		return firstLenght < secondLenght ? true : false;
	}
}
