
import java.io.*;
import java.util.*;
import acm.program.*;

public class YouTubeID extends Program {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] links;
	private String file;

	private static final int NUM_LINKS = 7;
	private static final int NUM_CHAR = 11;

	public YouTubeID() {
		links = new String[NUM_LINKS];
		for (int i = 0; i < NUM_LINKS; i++) {
			links[i] = "";
		}
		
		file = "";
	}

	public void run() {
		Scanner HTMLReader = readHTML();
		file = readFile(HTMLReader);
		findLinks();
	}

	private Scanner readHTML() {
		try {
			return new Scanner(new File("res/tv.html"));
		} catch (IOException e) {
			println(e);
		}
		return null;
	}

	private String readFile(Scanner HTMLReader) {
		String file = "";
		file = HTMLReader.nextLine();
		while (HTMLReader.hasNextLine()) {
			file = file + "\n" + HTMLReader.nextLine();
		}
		file = file.replaceAll("https", "http");
		return file;
	}

	private void findLinks() {
		// TODO: Need to replace all this with a loop
		Scanner code = new Scanner(file);
		String currentLine = "";
		currentLine = code.nextLine();
		while (code.hasNextLine() && !currentLine.contains("<!-- ID0 -->")) {
			currentLine = code.nextLine();
		}
		for (int i = 0; i < NUM_CHAR; i++) {
			links[0] += currentLine.charAt(14 + i);
		}
		while (code.hasNextLine() && !currentLine.contains("<!-- ID1 -->")) {
			currentLine = code.nextLine();
		}
		for (int i = 0; i < NUM_CHAR; i++) {
			links[1] += currentLine.charAt(14 + i);
		}
		while (code.hasNextLine() && !currentLine.contains("<!-- ID2 -->")) {
			currentLine = code.nextLine();
		}
		for (int i = 0; i < NUM_CHAR; i++) {
			links[2] += currentLine.charAt(14 + i);
		}
		while (code.hasNextLine() && !currentLine.contains("<!-- ID3 -->")) {
			currentLine = code.nextLine();
		}
		for (int i = 0; i < NUM_CHAR; i++) {
			links[3] += currentLine.charAt(14 + i);
		}
		while (code.hasNextLine() && !currentLine.contains("<!-- ID4 -->")) {
			currentLine = code.nextLine();
		}
		for (int i = 0; i < NUM_CHAR; i++) {
			links[4] += currentLine.charAt(14 + i);
		}
		while (code.hasNextLine() && !currentLine.contains("<!-- ID5 -->")) {
			currentLine = code.nextLine();
		}
		for (int i = 0; i < NUM_CHAR; i++) {
			links[5] += currentLine.charAt(14 + i);
		}
		while (code.hasNextLine() && !currentLine.contains("<!-- ID6 -->")) {
			currentLine = code.nextLine();
		}
		for (int i = 0; i < NUM_CHAR; i++) {
			links[6] += currentLine.charAt(14 + i);
		}
		code.close();
	}

	public String[] showLinks() {
		return links;
	}
	
	// TODO: Need to replace all these functions with a loop (will work with any # of links)
	public String change1(String url) {
		Scanner code = new Scanner(file);
		String currentLine = code.nextLine();
		while (code.hasNextLine() && !currentLine.contains("<!-- ID0 -->")) {
			currentLine = code.nextLine();
		}
		String newLine = "    videoId: '" + getID(url) + "',<!-- ID0 -->";
		code.close();
		file = file.replace(currentLine, newLine);
		links[0] = getID(url);
		update();
		return links[0];
	}

	public String change2(String url) {
		Scanner code = new Scanner(file);
		String currentLine = code.nextLine();
		while (code.hasNextLine() && !currentLine.contains("<!-- ID1 -->")) {
			currentLine = code.nextLine();
		}
		String newLine = "    videoId: '" + getID(url) + "',<!-- ID1 -->";
		code.close();
		file = file.replace(currentLine, newLine);
		links[1] = getID(url);
		update();
		return links[1];
	}

	public String change3(String url) {
		Scanner code = new Scanner(file);
		String currentLine = code.nextLine();
		while (code.hasNextLine() && !currentLine.contains("<!-- ID2 -->")) {
			currentLine = code.nextLine();
		}
		String newLine = "    videoId: '" + getID(url) + "',<!-- ID2 -->";
		code.close();
		file = file.replace(currentLine, newLine);
		links[2] = getID(url);
		update();
		return links[2];
	}

	public String change4(String url) {
		Scanner code = new Scanner(file);
		String thisLine = code.nextLine();
		while (code.hasNextLine() && !thisLine.contains("<!-- ID3 -->")) {
			thisLine = code.nextLine();
		}
		String newLine = "    videoId: '" + getID(url) + "',<!-- ID3 -->";
		code.close();
		file = file.replace(thisLine, newLine);
		links[3] = getID(url);
		update();
		return links[3];
	}

	public String change5(String url) {
		Scanner code = new Scanner(file);
		String currentLine = code.nextLine();
		while (code.hasNextLine() && !currentLine.contains("<!-- ID4 -->")) {
			currentLine = code.nextLine();
		}
		String newLine = "    videoId: '" + getID(url) + "',<!-- ID4 -->";
		code.close();
		file = file.replace(currentLine, newLine);
		links[4] = getID(url);
		update();
		return links[4];
	}

	public String change6(String url) {
		Scanner code = new Scanner(file);
		String currentLine = code.nextLine();
		while (code.hasNextLine() && !currentLine.contains("<!-- ID5 -->")) {
			currentLine = code.nextLine();
		}
		String newLine = "    videoId: '" + getID(url) + "',<!-- ID5 -->";
		code.close();
		file = file.replace(currentLine, newLine);
		links[5] = getID(url);
		update();
		return links[5];
	}

	public String changeMain(String url) {
		Scanner code = new Scanner(file);
		String currentLine = code.nextLine();
		while (code.hasNextLine() && !currentLine.contains("<!-- ID6 -->")) {
			currentLine = code.nextLine();
		}
		String newLine = "    videoId: '" + getID(url) + "',<!-- ID6 -->";
		code.close();
		file = file.replace(currentLine, newLine);
		links[6] = getID(url);
		update();
		return links[6];
	}

	private String getID(String html) {
		String ID = "";
		for (int i = 0; i < NUM_CHAR; i++) {
			ID += html.charAt(32 + i);
		}
		return ID;
	}

	private void update() {
		try {
			File fold = new File("res/tv.html");
			fold.delete();
			File fnew = new File("res/tv.html");
			FileWriter newHTML = new FileWriter(fnew, false);
			newHTML.write(file);
			newHTML.close();
		} catch (IOException e) {
			println(e);
		}
	}

}
