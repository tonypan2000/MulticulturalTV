
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.*;
import javax.swing.*;
import org.json.JSONObject;
import acm.program.GraphicsProgram;

public class Validator extends GraphicsProgram {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SEPARATION = 7;
	private static final int NUM_LINKS = 7;
	private static final int TEXT_FIELD_SIZE = 40;
	private static final int Y_OFFSET = 100;
	private static final int NUM_CHAR = 11;

	private JLabel intro;
	private JButton click;
	private JLabel link1;
	private JLabel link2;
	private JLabel link3;
	private JLabel link4;
	private JLabel link5;
	private JLabel link6;
	private JLabel main;
	private JLabel valid1;
	private JLabel valid2;
	private JLabel valid3;
	private JLabel valid4;
	private JLabel valid5;
	private JLabel valid6;
	private JLabel validMain;
	private JTextField newLink;
	private JLabel newLinkName;
	private JButton checkNewLink;
	private JButton change1;
	private JButton change2;
	private JButton change3;
	private JButton change4;
	private JButton change5;
	private JButton change6;
	private JButton changeMain;

	private YouTubeID YouTubeID;

	private String[] IDs;

	public static void main(String[] args) {
		(new Validator()).start(args);
	}

	public void init() {
		intro = new JLabel("YouTube Link Validator");
		click = new JButton("Validate");
		link1 = new JLabel("Player 1");
		link2 = new JLabel("Player 2");
		link3 = new JLabel("Player 3");
		link4 = new JLabel("Player 4");
		link5 = new JLabel("Player 5");
		link6 = new JLabel("Player 6");
		valid1 = new JLabel("");
		valid2 = new JLabel("");
		valid3 = new JLabel("");
		valid4 = new JLabel("");
		valid5 = new JLabel("");
		valid6 = new JLabel("");
		validMain = new JLabel("");
		main = new JLabel("Main Player");
		IDs = new String[NUM_LINKS];
		newLink = new JTextField(TEXT_FIELD_SIZE);
		newLink.addActionListener(this);
		newLink.setActionCommand("Validate");
		checkNewLink = new JButton("Validate");
		newLinkName = new JLabel("");
		change1 = new JButton("Change Player1");
		change2 = new JButton("Change Player2");
		change3 = new JButton("Change Player3");
		change4 = new JButton("Change Player4");
		change5 = new JButton("Change Player5");
		change6 = new JButton("Change Player6");
		changeMain = new JButton("Change Main Player");

		add(intro, NORTH);
		add(click, NORTH);
		add(newLink, SOUTH);
		add(checkNewLink, SOUTH);
		add(newLinkName, SOUTH);
		add(change1, WEST);
		add(change2, WEST);
		add(change3, WEST);
		add(change4, WEST);
		add(change5, WEST);
		add(change6, WEST);
		add(changeMain, WEST);

		addActionListeners();

		YouTubeID = new YouTubeID();
	}

	public void run() {
		YouTubeID.run();
		IDs = YouTubeID.showLinks();
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == click) {
			try {
				validate(IDs);
			} catch (Exception e) {
				System.out.println(e);
			}
			update();
		} else if (event.getSource() == checkNewLink
				|| event.getActionCommand().equals("Validate") && !newLink.getText().equals("")) {
			validateNewLink(newLink.getText());
		} else if (event.getSource() == change1) {
			try {
				valid1.setText(existence(YouTubeID.change1(newLink.getText())));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			update();
		} else if (event.getSource() == change2) {
			try {
				valid2.setText(existence(YouTubeID.change2(newLink.getText())));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			update();
		} else if (event.getSource() == change3) {
			try {
				valid3.setText(existence(YouTubeID.change3(newLink.getText())));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			update();
		} else if (event.getSource() == change4) {
			try {
				valid4.setText(existence(YouTubeID.change4(newLink.getText())));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			update();
		} else if (event.getSource() == change5) {
			try {
				valid5.setText(existence(YouTubeID.change5(newLink.getText())));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			update();
		} else if (event.getSource() == change6) {
			try {
				valid6.setText(existence(YouTubeID.change6(newLink.getText())));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			update();
		} else if (event.getSource() == changeMain) {
			try {
				validMain.setText(existence(YouTubeID.changeMain(newLink.getText())));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			update();
		}
	}

	public void validate(String[] urls) throws Exception {
		IDs = YouTubeID.showLinks();
		valid1.setText(existence(IDs[0]));
		valid2.setText(existence(IDs[1]));
		valid3.setText(existence(IDs[2]));
		valid4.setText(existence(IDs[3]));
		valid5.setText(existence(IDs[4]));
		valid6.setText(existence(IDs[5]));
		validMain.setText(existence(IDs[6]));
	}

	private String existence(String ID) throws Exception {
		String check = "";
		check = "http://www.youtube.com/oembed?url=http%3A//www.youtube.com/watch%3Fv%3D" + ID + "&format=json";
		System.out.println(check);
		try {
			URL link1 = new URL(check);
			URLConnection connectLink1 = link1.openConnection();
			BufferedReader inputLink1 = new BufferedReader(new InputStreamReader(connectLink1.getInputStream()));
			String inputLine = "";
			while ((inputLine = inputLink1.readLine()) != null) {
				System.out.println(inputLine);
				JSONObject obj = new JSONObject(inputLine);
				return obj.getString("author_name");
			}
			inputLink1.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return "invalid";
	}

	private void update() {
		removeAll();
		add(link1, SEPARATION, SEPARATION + Y_OFFSET);
		double labelX = link1.getWidth() + SEPARATION * 2;
		double labelY = link1.getHeight() + SEPARATION * 2;
		add(valid1, labelX, SEPARATION + Y_OFFSET);
		add(link2, SEPARATION, labelY + SEPARATION + Y_OFFSET);
		add(valid2, labelX, labelY + SEPARATION + Y_OFFSET);
		add(link3, SEPARATION, labelY * 2 + SEPARATION + Y_OFFSET);
		add(valid3, labelX, labelY * 2 + SEPARATION + Y_OFFSET);
		add(link4, SEPARATION, labelY * 3 + SEPARATION + Y_OFFSET);
		add(valid4, labelX, labelY * 3 + SEPARATION + Y_OFFSET);
		add(link5, SEPARATION, labelY * 4 + SEPARATION + Y_OFFSET);
		add(valid5, labelX, labelY * 4 + SEPARATION + Y_OFFSET);
		add(link6, SEPARATION, labelY * 5 + SEPARATION + Y_OFFSET);
		add(valid6, labelX, labelY * 5 + SEPARATION + Y_OFFSET);
		add(main, SEPARATION, labelY * 6 + SEPARATION + Y_OFFSET);
		add(validMain, main.getWidth() + SEPARATION * 2, labelY * 6 + SEPARATION + Y_OFFSET);
	}

	private void validateNewLink(String html) {
		String ID = "";
		for (int i = 0; i < NUM_CHAR; i++) {
			ID += html.charAt(32 + i);
		}
		System.out.println(ID);
		try {
			newLinkName.setText(existence(ID));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
