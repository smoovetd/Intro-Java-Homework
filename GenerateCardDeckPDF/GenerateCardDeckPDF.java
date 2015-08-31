import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GenerateCardDeckPDF {
	// Should be edited depending on system and needs
	private static String PathToFile ;
	private static String FileName ;
	private static String Font ;
	
	public static void main(String[] args) throws DocumentException, IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter directory name - including backslash at the end");
		PathToFile = scanner.nextLine();
		System.out.println("Enter file name");
		FileName = scanner.nextLine();
		System.out.println("Enter full path to font and font name ( should support unicode characters)");
		Font = scanner.nextLine();
		
		printCardsToPDF();
	}
	
	//♡ U+u2665, ♢ U+u2666, ♧ U+u2663, ♤ U+u2660
	public static String[] generate52CardDeck(){
		char[] cardsBacks = new char[] { '\u2663' , '\u2666' , '\u2665' , '\u2660'};
		char[] cards = new char[] { 'A', '2',  '3',  '4', '5', '6', '7', '8', '9' , '0', 'J','Q', 'K'};
		String[] completedCards = new String[52];
		int cardIndex = 0;
		for ( int card = 0 ; card < cards.length; card++){
			for ( int back = 0; back < cardsBacks.length;back++){
				if(cards[card] == '0'){
					completedCards[cardIndex] = String.format("10" + cardsBacks[back]);
				} else{
					completedCards[cardIndex] = String.format("" + cards[card] + cardsBacks[back]);
				}
				cardIndex++;
			
			}
		}
		return completedCards;
			
	}  
	public static void printCardsToPDF() throws DocumentException, IOException  {
		// Create Pdf Writer
		String fullDestination = PathToFile + FileName;
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(fullDestination));
		BaseFont baseFont;
		
		document.open();
		// Set Font  and Color
		baseFont = BaseFont.createFont(Font,BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
		Font blackFont = new Font(baseFont, 28, 0, BaseColor.BLACK);
		Font redFont = new Font(baseFont, 28, 0, BaseColor.RED);
		document.addTitle("Test Document");
		BaseColor bc = BaseColor.BLACK;
		
		
		String[] cards = generate52CardDeck();

		// Draw table with cards
		PdfPTable table = new PdfPTable(4);
		
		for(int i = 0; i<cards.length;i++){
			if(cards[i].contains("\u2666")||cards[i].contains("\u2665")){
				PdfPCell cell = new PdfPCell(new Phrase(cards[i] + "",redFont));
				cell.setExtraParagraphSpace(1);
				cell.setPadding(2);
				cell.setHorizontalAlignment(1);
				
				table.addCell(cell);
			} else{
				PdfPCell cell = new PdfPCell(new Phrase(cards[i] + "",blackFont));
				cell.setExtraParagraphSpace(3);
				cell.setPadding(2);
				cell.setHorizontalAlignment(1);
				table.addCell(cell);
			}
		}
		table.setPaddingTop(2);
		table.setSpacingBefore(2);
		table.setSpacingAfter(2);
		document.add(table);
		document.close();

	}

}
