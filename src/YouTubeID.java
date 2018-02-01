import java.io.*;
import java.util.*;
import acm.program.*;

public class YouTubeID extends Program {

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
		Scanner code = new Scanner(file);
		String currentLine = "";
		currentLine = code.nextLine();

		for (int i = 0; i <= NUM_LINKS; i++) {
			while (code.hasNextLine() && !currentLine.contains("<!-- ID" + i + " -->")) {
				currentLine = code.nextLine();
			}

			for (int j = 0; j < NUM_CHAR; j++) {
				links[i] += currentLine.charAt(14 + j);
			}
		}
		code.close();
	}

	public String[] showLinks() {
		return links;
	}

	public String change(String num, String url) {
		if (num == "Main") {
			num = NUM_LINKS - 1 + "";
		}
		int number = Integer.parseInt(num);

		Scanner code = new Scanner(file);
		String currentLine = code.nextLine();
		while (code.hasNextLine() && !currentLine.contains("<!-- ID" + number + " -->")) {
			currentLine = code.nextLine();
		}

		String newLine = "    videoId: '" + getID(url) + "',<!-- ID" + number + " -->";
		code.close();
		file = file.replace(currentLine, newLine);
		links[number] = getID(url);
		update();
		return links[number];
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
