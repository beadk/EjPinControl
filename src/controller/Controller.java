package controller;

import DTO.*;
import kode_gen.*;
import gui.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import settings.Settings;
import sun.misc.*;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.edit.*;
import org.apache.pdfbox.pdmodel.font.*;

public class Controller {
	String name = "EjPin Control";
	Kode_gen genKode = new Kode_gen();
	JFileChooser fc = new JFileChooser();
	Settings settings = new Settings();
	List<SingleCodeDTO> systems = new ArrayList<>();
	List<BrugtePinsDTO> bPins = new ArrayList<>();
	GUI gui = new GUI();
	PDDocument pd;
	JFileChooser chooser;
	ErrorHandler err = new ErrorHandler();
	private String fileLoc, defaultLoc, ownLoc, errorTitle, errorMessage,
			comfirmTitle, comfirmMessage, codeType;
	private int codeLength;
	private float fWriteLocX, fWriteLocY;

	public void run() {
		try {
			ownLoc = getOwnLoc();
			readSettings();
			loadSettings();
			if (defaultLoc != null) {
				start();
				gui.mainGUI(name);
				ready();
				try {
					gui.getbSFrame().toFront();
				} catch (Exception e) {
					err.printError(ownLoc, e);
				}
			}
		} catch (Exception e) {
			err.printError(ownLoc, e);
		}
	}

	class CodeSettings implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gui.codeSettings();
			gui.getCodeLengthF().setText(codeLength + "");
			switch (codeType) {
			case "tal":
				gui.getCodeType().setSelectedItem("Tal");
				break;
			case "bogtal":
				gui.getCodeType().setSelectedItem("Bogstaver og tal");
				break;
			case "BoGtal":
				gui.getCodeType().setSelectedItem(
						"Store og små bogstaver og tal");
				break;
			default:
				break;
			}

			gui.getSaveSettingsB().addActionListener(
					new SaveCodeSettings());
			gui.getCancelSettingB()
					.addActionListener(new CancelSettings());
			gui.getDefaultSettingB().addActionListener(
					new DefaultCodeSettings());
			gui.getFrameCodeSettings().addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent event) {
					try {
						gui.resizeFrameCodeSettings();
					} catch (Exception e) {
						err.printError(ownLoc, e);
					}
				}
			});
		}
	}

	class SaveCodeSettings implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			codeLength = Integer.parseInt(gui.getCodeLengthF().getText());
			switch (gui.getCodeType().getSelectedItem().toString()) {
			case "Tal":
				codeType = "tal";
				break;
			case "Bogstaver og tal":
				codeType = "bogtal";
				break;
			case "Store og små bogstaver og tal":
				codeType = "BoGtal";
				break;
			default:
				break;
			}
			settings.updateCodeSettings(codeLength, codeType, ownLoc);
			gui.getFrameCodeSettings().dispose();
		}
	}

	class DefaultCodeSettings implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			settings.updateCodeSettings(5, "tal", ownLoc);
		}
	}

	class RestoDB implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				genSkabDB();
			} catch (Exception e1) {
				err.printError(ownLoc, e1);
			}
		}
	}

	class AddSystemGUI implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gui.addControl();
			gui.getCancelB().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gui.getFrameAddControl().dispose();
					gui.getCodeTF().setText("00000");
					gui.getCodeTF().setEditable(true);
				}
			});
			gui.getFrameAddControl().addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent event) {
					try {
						gui.resizeFrameAddControl();
					} catch (Exception e) {
						err.printError(ownLoc, e);
					}
				}
			});
		}
	}

	class EncryptDB implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String path = defaultLoc + "/PinGen/Dorma RS8";
			try {
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new FileWriter(path + "/DormaRS8Pins.txt")));
				for (int i = 0; i < systems.size(); i++) {
					if (i == 0) {
						out.write(encrypt("pin") + "," + encrypt("system")
								+ "\n");
					}
					String system = "";
					String kode = "";
					if (decrypt(systems.get(i).getPinKode()) == null) {
						kode = encrypt(systems.get(i).getPinKode());
					} else {
						kode = systems.get(i).getPinKode();
					}
					if (decrypt(systems.get(i).getSystemNummer()) == null) {
						system = encrypt(systems.get(i).getSystemNummer());
					} else {
						system = systems.get(i).getSystemNummer();
					}
					systems.get(i).setSystemNummer(system);
					systems.get(i).setPinKode(kode);
					out.write(kode + "," + system + "\n");

				}
				out.close();
				PrintWriter out2 = new PrintWriter(new BufferedWriter(
						new FileWriter(path + "/DormaRS8BrugtePins.txt")));
				for (int i = 0; i < bPins.size(); i++) {
					String kode = "";
					if (decrypt(bPins.get(i).getBrugtPin()) == null) {
						kode = encrypt(bPins.get(i).getBrugtPin());
					} else {
						kode = bPins.get(i).getBrugtPin();
					}
					bPins.get(i).setBrugtPin(kode);
					out2.write(kode + "\n");

				}
				out2.close();
			} catch (IOException ex) {
				String prePath = defaultLoc + "/PinGen/Dorma RS8";
				Path path2 = Paths.get(prePath);
				if (Files.exists(path2)) {

				} else {
					File dir = new File(defaultLoc + "/PinGen");
					dir.mkdir();
					File dir2 = new File(defaultLoc + "/PinGen/Dorma RS8");
					dir2.mkdir();
				}
			}
		}
	}

	class SaveSettings implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (gui.getDefaultLocTF().getText().equals("")
					|| gui.getWriteLocX().getText().equals("")
					|| gui.getWriteLocY().getText().equals("")) {
				errorTitle = "Fejl: Input mangler";
				errorMessage = "Fejl: Manglende input!";
				gui.errorWindow(errorTitle, errorMessage);
			} else {
				if (gui.getFileLocTF().getText().equals(""))
					gui.getFileLocTF().setText("null");
				if (defaultLoc == null) {
					fWriteLocX = Float.parseFloat(gui.getWriteLocX().getText());
					fWriteLocY = Float.parseFloat(gui.getWriteLocY().getText());
					settings.updateSettings(gui.getDefaultLocTF().getText(),
							gui.getFileLocTF().getText(), gui.getWriteLocX()
									.getText()
									+ ","
									+ gui.getWriteLocY().getText(), ownLoc);
					run();
				} else {
					fWriteLocX = Float.parseFloat(gui.getWriteLocX().getText());
					fWriteLocY = Float.parseFloat(gui.getWriteLocY().getText());
					settings.updateSettings(gui.getDefaultLocTF().getText(),
							gui.getFileLocTF().getText(), gui.getWriteLocX()
									.getText()
									+ ","
									+ gui.getWriteLocY().getText(), ownLoc);
					try {
						loadSettings();
					} catch (Exception e1) {
						err.printError(ownLoc, e1);
					}
				}
				gui.getFrameCodeSettings().dispose();
			}
		}
	}

	class DefaultLoc implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				selectFolder();
			} catch (Exception e1) {
				err.printError(ownLoc, e1);
			}
		}
	}

	class CancelSettings implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gui.getFrameCodeSettings().dispose();
		}
	}

	class DefaultSettings implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				if (gui.getDefaultLocTF().getText().equals("")) {
					readDefaultSettings(true);
				} else {
					readDefaultSettings(false);
				}
			} catch (Exception e1) {
				err.printError(ownLoc, e1);
			}
		}
	}

	class GetFile implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				selectFile();
			} catch (Exception e1) {
				err.printError(ownLoc, e1);
			}
		}
	}

	class PDFPreview implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				pdfPreview();
			} catch (Exception e1) {
				err.printError(ownLoc, e1);
			}
		}
	}

	class FindSystem implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				if (findSystemCheck(gui.getSearchControlTF().getText())
						&& !gui.getSearchControlTF().getText().equals("")) {
					String fSPin = "";
					try {
						fSPin = findSystemPin(gui.getSearchControlTF().getText());
					} catch (Exception e1) {
						err.printError(ownLoc, e1);
					}
					String title = "Find system";
					String message = "System " + gui.getSearchControlTF().getText()
							+ " har pin: " + decrypt(fSPin);
					gui.addedWindow(title, message);
					gui.getSearchControlTF().setText("");
				} else if (gui.getSearchControlTF().getText().equals("")) {
					errorTitle = "Fejl: Input mangler";
					errorMessage = "Fejl: Manglende input!";
					gui.errorWindow(errorTitle, errorMessage);
				} else {
					errorTitle = "Fejl: System ikke fundet";
					errorMessage = "System " + gui.getSearchControlTF().getText()
							+ " findes ikke i datafilen";
					gui.errorWindow(errorTitle, errorMessage);
				}
			} catch (Exception e1) {
				err.printError(ownLoc, e1);
			}
		}
	}

	class NyKodeGen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String ny = "";
			switch (codeType) {
			case "tal":
				try {
					do {
						ny = genKode.kode("", 0, codeLength);
					} while (!checkKode(ny));
				} catch (Exception e1) {
					err.printError(ownLoc, e1);
				}
				break;
			case "bogTal":
				try {
					do {
						ny = genKode.kodeBogTal("", 0, codeLength);
					} while (!checkKode(ny));
				} catch (Exception e1) {
					err.printError(ownLoc, e1);
				}
				break;
			case "BoGtal":
				try {
					do {
						ny = genKode.kodeSSBogTal("", 0, codeLength);
					} while (!checkKode(ny));
				} catch (Exception e1) {
					err.printError(ownLoc, e1);
				}
				break;
			}
			gui.getCodeTF().setText(ny);
			gui.getCodeTF().setEditable(false);
		}
	}

	class GemKodeAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!gui.getCodeTF().getText().equals("")
					&& !gui.getControlTF().getText().equals("")) {
				try {
					if (!checkSystem(gui.getControlTF().getText())) {
						errorTitle = "Fejl: Brurgt system nummer";
						errorMessage = "System "
								+ gui.getControlTF().getText()
								+ " er brugt og har allerede en pinkode";
					} else {
						try {
							addSystem(encrypt(gui.getCodeTF().getText()),
									encrypt(gui.getControlTF().getText()));
							brugtPin(gui.getCodeTF().getText());
						} catch (Exception e2) {
							err.printError(ownLoc, e2);
						}
						String title = "System tilføjet";
						String message = "System "
								+ gui.getControlTF().getText()
								+ " er blevet tilføjet med pin: "
								+ gui.getCodeTF().getText();
						gui.addedWindow(title, message);
						if (fileLoc != null) {
							try {
								writePDF(gui.getCodeTF().getText());
							} catch (Exception e1) {
								err.printError(ownLoc, e1);
							}
						}
						gui.getCodeTF().setText("00000");
						gui.getControlTF().setText("");
					}
				} catch (Exception e1) {
					err.printError(ownLoc, e1);
				}
			} else {
				errorTitle = "Fejl: Input mangler";
				errorMessage = "Fejl: Manglende input!";
				gui.errorWindow(errorTitle, errorMessage);
			}
		}
	}

	class bekæftFjernelse implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				fjernSystem(gui.getRemoveControlTF().getText());
			} catch (Exception e1) {
				err.printError(ownLoc, e1);
			}
			gui.getRemoveControlTF().setText("");
			gui.getComfirmRemovalFrame().dispatchEvent(
					new WindowEvent(gui.getComfirmRemovalFrame(),
							WindowEvent.WINDOW_CLOSING));
			try {
				genSkrivDataSheet();
			} catch (Exception e1) {
				err.printError(ownLoc, e1);
			}
		}
	}

	class nej implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gui.getComfirmRemovalFrame().dispatchEvent(
					new WindowEvent(gui.getComfirmRemovalFrame(),
							WindowEvent.WINDOW_CLOSING));
			gui.getRemoveControlTF().setText("");
		}
	}

	class FjernSystem implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!gui.getRemoveControlTF().getText().equals("")) {
				try {
					if (!findSystemCheck(gui.getRemoveControlTF().getText())) {
						errorTitle = "Fejl: System ikke fundet";
						errorMessage = "Fejl: Det opgivet system findes ikke i databasen.";
						gui.errorWindow(errorTitle, errorMessage);
					} else {
						comfirmTitle = "Bekæft fjernelse";
						comfirmMessage = "Vi du fjerne system: "
								+ gui.getRemoveControlTF().getText();
						gui.comfirmFrame(comfirmTitle, comfirmMessage);
						try {
							gui.getComfirmRemovalB().addActionListener(
									new bekæftFjernelse());
							gui.getNoButton().addActionListener(new nej());
						} catch (NullPointerException ex) {
							System.out.println(ex);
						}
					}
				} catch (Exception e1) {
					err.printError(ownLoc, e1);
				}
			} else {
				errorTitle = "Fejl: Input mangler";
				errorMessage = "Fejl: Manglende input!";
				gui.errorWindow(errorTitle, errorMessage);
			}
		}
	}

	class FjernPin implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!gui.getReplaceCodeTF().getText().equals("")) {
				comfirmTitle = "Bekæft fjernelse";
				comfirmMessage = "Vi du ersatte pin til system: "
						+ gui.getReplaceCodeTF().getText();
				gui.comfirmFrame(comfirmTitle, comfirmMessage);
				try {
					gui.getComfirmRemovalB().addActionListener(
							new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									try {
										ersatPin(gui.getReplaceCodeTF()
												.getText());
									} catch (Exception e1) {
										err.printError(ownLoc, e1);
									}
									gui.getReplaceCodeTF().setText("");
									gui.getComfirmRemovalFrame().dispose();
								}
							});
					gui.getNoButton().addActionListener(new nej());
				} catch (NullPointerException ex) {
					err.printError(ownLoc, ex);
				}

			} else {
				errorTitle = "Fejl: Input mangler";
				errorMessage = "Fejl: Manglende input!";
				gui.errorWindow(errorTitle, errorMessage);
			}
		}
	}

	public void ready() {
		try {
			gui.getFrame().addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent event) {
					try {
						close();
					} catch (Exception e) {
						err.printError(ownLoc, e);
					}
				}
			});
			gui.getFrame().addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent event) {
					try {
						gui.resizeFrame();
					} catch (Exception e) {
						err.printError(ownLoc, e);
					}
				}
			});
			gui.getmPrintSettings().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						runSettingsGUI();
					} catch (Exception e1) {
						err.printError(ownLoc, e1);
					}
				}
			});
			gui.getmPrintPreview().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						pdfPreview();
					} catch (Exception e1) {
						err.printError(ownLoc, e1);
					}
				}
			});
			gui.getmExit().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						close();
					} catch (Exception e1) {
						err.printError(ownLoc, e1);
					}
				}
			});
			gui.getAddSystemB().addActionListener(new AddSystemGUI());
			gui.getmRestoDB().addActionListener(new RestoDB());
			gui.getEncryptDB().addActionListener(new EncryptDB());
			gui.getNewCodeB().addActionListener(new NyKodeGen());
			gui.getSaveCodeB().addActionListener(new GemKodeAction());
			gui.getSearchControlB().addActionListener(new FindSystem());
			gui.getAddSystemB().addActionListener(new GemKodeAction());
			gui.getSearchControlTF().addActionListener(new FindSystem());
			gui.getRemoveControlB().addActionListener(new FjernSystem());
			gui.getReplaceCodeB().addActionListener(new FjernPin());
			gui.getRemoveControlTF().addActionListener(new FjernSystem());
			gui.getReplaceCodeTF().addActionListener(new FjernPin());
			gui.getmCodeSettings().addActionListener(new CodeSettings());
		} catch (NullPointerException e) {
			err.printError(ownLoc, e);
		}
	}

	@SuppressWarnings("restriction")
	public String encrypt(String str) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(genKode.genSalt())
				+ encoder.encode(str.getBytes());
	}

	@SuppressWarnings("restriction")
	public static String decrypt(String encstr) {
		if (encstr.length() > 12) {
			String cipher = encstr.substring(12);
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				return new String(decoder.decodeBuffer(cipher));
			} catch (IOException e) {
			}
		}
		return null;
	}

	public String getOwnLoc() throws Exception {
		try {
			File tryFile;
			URL url = Kode_gen.class.getProtectionDomain().getCodeSource()
					.getLocation();
			try {
				tryFile = new File(url.toURI());
				return tryFile.getAbsolutePath().replace("\\" + name + ".jar",
						"");
			} catch (URISyntaxException e) {
				err.printError(ownLoc, e);
			}
		} catch (NullPointerException e) {
			err.printError(ownLoc, e);
		}

		return "";
	}

	public void loadSettings() throws Exception {
		try {
			fWriteLocX = Float.parseFloat(settings.getWriteLocX());
			fWriteLocY = Float.parseFloat(settings.getWriteLocY());
			fileLoc = settings.getFileLoc();
			defaultLoc = settings.getDefaultLoc();
			codeLength = settings.getCodeLength();
			codeType = settings.getCodeType();
		} catch (NullPointerException e) {
			errorTitle = "Ingen Indstillinger fundet";
			errorMessage = "<html>Der er ikke blevet fundet nogen indstilling.<br> "
					+ "Indstillinger er sat til default."
					+ "<br> Det anbefales du ændre disse før du forsætter.</html>";
			gui.noSettings(errorTitle, errorMessage);
			startDefault();
		}
	}

	public void close() throws Exception {
		try {
			try {
				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
						.format(Calendar.getInstance().getTime());
				String prePath = defaultLoc
						+ "/PinGen/Dorma RS8/Backup Dorma RS8";
				Path path = Paths.get(prePath);
				if (Files.exists(path)) {

				} else {
					File dir = new File(defaultLoc
							+ "/PinGen/Dorma RS8/Backup Dorma RS8");
					dir.mkdir();
				}
				try {
					for (int i = 0; i < systems.size(); i++) {
						PrintWriter out = new PrintWriter(
								new BufferedWriter(new FileWriter(
										path.toString() + "/" + timeStamp
												+ "DormaRS8Pins.txt", true)));
						out.write(systems.get(i).getPinKode() + ","
								+ systems.get(i).getSystemNummer() + "\n");
						out.close();
					}
				} catch (IOException e) {
					err.printError(ownLoc, e);
				}
			} catch (NullPointerException ex) {
				err.printError(ownLoc, ex);
			}
		} catch (Exception e) {
			err.printError(ownLoc, e);
		}
		System.exit(0);
	}

	public void start() throws Exception {
		String path = defaultLoc + "/PinGen/Dorma RS8";
		try {

			FileInputStream file = new FileInputStream(path
					+ "/DormaRS8Pins.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					file));
			String line = reader.readLine();
			while (line != null) {
				if (decrypt(line.split(",")[0]).equals("pin")) {
					line = reader.readLine();
				} else {
					addSystem(line.split(","));
					line = reader.readLine();
				}
			}
			reader.close();

		} catch (FileNotFoundException ex) {
			gui.dbError();
			gui.getComfirmRemovalB().addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								newDB();
								gui.getComfirmRemovalFrame().dispose();
							} catch (Exception e1) {
								err.printError(ownLoc, e1);
							}
						}
					});
			gui.getNoButton().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						genSkabDB();
					} catch (Exception e1) {
						err.printError(ownLoc, e1);
					}
				}
			});
		} catch (IOException ex) {
			err.printError(ownLoc, ex);
		}
		try {
			FileInputStream file2 = new FileInputStream(path
					+ "/DormaRS8BrugtePins.txt");
			BufferedReader reader2 = new BufferedReader(new InputStreamReader(
					file2));
			String line2 = reader2.readLine();
			while (line2 != null) {
				bPins.add(new BrugtePinsDTO(line2));
				line2 = reader2.readLine();
			}
			reader2.close();
		} catch (FileNotFoundException ex) {

		} catch (IOException ex) {
			err.printError(ownLoc, ex);
		}
	}

	public void genSkabDB() throws Exception {
		File file = null;
		systems.removeAll(systems);
		System.out.println(systems.size());
		if (defaultLoc != null) {
			chooser = new JFileChooser(defaultLoc
					+ "/PinGen/Dorma RS8/Backup Dorma RS8");
		} else {
			chooser = new JFileChooser(ownLoc);
		}
		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt",
				"txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}
		FileInputStream fileInput;
		try {
			fileInput = new FileInputStream(file);

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					fileInput));
			String line = reader.readLine();
			while (line != null) {
				String[] splitStr = line.split(",");
				if (decrypt(splitStr[0]) == null) {
					splitStr[0] = encrypt(splitStr[0]);
					addSystem(splitStr);
				} else {
					addSystem(splitStr);
				}
				line = reader.readLine();
			}
			reader.close();
			genSkrivDataSheet();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			err.printError(ownLoc, e1);
		}
	}

	public void newDB() throws Exception {
		String path = defaultLoc + "/PinGen/Dorma RS8";
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(path + "/DormaRS8Pins.txt", true)));
			out.write(encrypt("pin") + "," + encrypt("systems") + "\n");
			out.close();
		} catch (IOException e) {
			String prePath = defaultLoc + "/PinGen/Dorma RS8";
			Path path2 = Paths.get(prePath);
			if (Files.exists(path2)) {

			} else {
				File dir = new File(defaultLoc + "/PinGen");
				dir.mkdir();
				File dir2 = new File(defaultLoc + "/PinGen/Dorma RS8");
				dir2.mkdir();
				newDB();
			}
		}
	}

	public void addSystem(String[] system) throws Exception {
		systems.add(new SingleCodeDTO(system[0], system[1]));
	}

	public void runSettingsGUI() throws Exception {
		gui.settingsGUI(defaultLoc, fileLoc, fWriteLocX, fWriteLocY);
		gui.getSaveSettingsB().addActionListener(new SaveSettings());
		gui.getCancelSettingB().addActionListener(new CancelSettings());
		gui.getDefaultSettingB().addActionListener(new DefaultSettings());
		gui.getGetFileB().addActionListener(new GetFile());
		gui.getDefaultLocB().addActionListener(new DefaultLoc());
		gui.getPdfPreviewB().addActionListener(new PDFPreview());
		gui.getFrameCodeSettings().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent event) {
				try {
					gui.resizeFrameCodeSettings();
				} catch (Exception e) {
					err.printError(ownLoc, e);
				}
			}
		});
	}

	public void readSettings() throws Exception {
		String path = ownLoc + "/PinGen/Dorma RS8";
		try {
			FileInputStream file = new FileInputStream(path
					+ "/DormaRS8Settings.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					file));
			String line = reader.readLine();
			while (line != null) {
				settings.readSettings(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException ex) {
			errorTitle = "Ingen Indstillinger fundet";
			errorMessage = "<html>Der er ikke blevet fundet nogen indstilling.<br> "
					+ "Indstillinger er sat til default."
					+ "<br> Det anbefales du ændre disse før du forsætter.</html>";
			gui.noSettings(errorTitle, errorMessage);
			startDefault();
		} catch (IOException ex) {
			err.printError(ownLoc, ex);
		}
	}

	public void selectFile() throws Exception {
		if (defaultLoc != null) {
			System.out.println(defaultLoc);
			chooser = new JFileChooser(defaultLoc);
		} else {
			chooser = new JFileChooser(ownLoc);
		}
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF",
				"pdf");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			gui.getFileLocTF().setText(
					chooser.getSelectedFile().getAbsolutePath());
		}
	}

	public void selectFolder() throws Exception {
		if (defaultLoc != null) {
			System.out.println(defaultLoc);
			chooser = new JFileChooser(defaultLoc);
		} else {
			chooser = new JFileChooser(ownLoc);
		}
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Folder",
				"folder");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			gui.getDefaultLocTF().setText(
					chooser.getSelectedFile().toString());
		}
	}

	public void addSystem(String pinKode, String system) throws Exception {
		systems.add(new SingleCodeDTO(pinKode, system));
		String path = defaultLoc + "/PinGen/Dorma RS8";
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(path + "/DormaRS8Pins.txt", true)));
			out.write(pinKode + "," + system + "\n");
			out.close();
		} catch (IOException e) {
			String prePath = defaultLoc + "/PinGen/Dorma RS8";
			Path path2 = Paths.get(prePath);
			if (Files.exists(path2)) {

			} else {
				File dir = new File(defaultLoc + "/PinGen");
				dir.mkdir();
				File dir2 = new File(defaultLoc + "/PinGen/Dorma RS8");
				dir2.mkdir();
				addSystem(pinKode, system);
			}
		}
	}

	public boolean checkKode(String kode) throws Exception {
		for (int i = 0; i < systems.size(); i++) {
			if (kode.equals(decrypt(systems.get(i).getPinKode()))) {
				return false;
			}
		}
		for (int i = 0; i < bPins.size(); i++) {
			if (kode.equals(decrypt(bPins.get(i).getBrugtPin()))) {
				return false;
			}
		}
		return true;
	}

	public boolean checkSystem(String system) throws Exception {
		for (int i = 0; i < systems.size(); i++) {
			if (system.equals(systems.get(i).getSystemNummer())) {
				return false;
			}
		}
		return true;
	}

	public boolean findSystemCheck(String system) throws Exception {
		for (int i = 0; i < systems.size(); i++) {
			if (system.equals(decrypt(systems.get(i).getSystemNummer()))) {
				return true;
			}
		}
		return false;
	}

	public String findSystemPin(String system) throws Exception {
		for (int i = 0; i < systems.size(); i++) {
			if (system.equals(decrypt(systems.get(i).getSystemNummer()))) {
				return systems.get(i).getPinKode();
			}
		}
		return "";
	}

	public boolean fjernSystem(String system) throws Exception {
		for (int i = 0; i < systems.size(); i++) {
			if (system.equals(systems.get(i).getSystemNummer())) {
				brugtPin(systems.get(i).getPinKode());
				systems.remove(i);
				return true;
			}
		}
		return false;
	}

	public String ersatPin(String system) throws Exception {
		for (int i = 0; i < systems.size(); i++) {
			if (system.equals(systems.get(i).getSystemNummer())) {
				brugtPin(systems.get(i).getPinKode());
				systems.get(i).setPinKode(ersatPinGen());
				genSkrivDataSheet();
				return systems.get(i).getPinKode();
			}
		}
		return "";
	}

	public String ersatPinGen() throws Exception {
		String ny;
		do {
			ny = genKode.kode("", 0, codeLength);
		} while (!checkKode(ny));

		return ny;
	}

	public void brugtPin(String pin) throws Exception {
		bPins.add(new BrugtePinsDTO(encrypt(pin)));
		String path = defaultLoc + "/PinGen/Dorma RS8";
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(path + "/DormaRS8BrugtePins.txt", true)));
			out.write(encrypt(pin) + "\n");
			out.close();
		} catch (IOException e) {
			String prePath = defaultLoc + "/PinGen/Dorma RS8";
			Path path2 = Paths.get(prePath);
			if (Files.exists(path2)) {

			} else {
				File dir = new File(defaultLoc + "/PinGen");
				dir.mkdir();
				File dir2 = new File(defaultLoc + "/PinGen/Dorma RS8");
				dir2.mkdir();
				brugtPin(pin);
			}
		}
	}

	public void genSkrivDataSheet() throws Exception {
		String path = defaultLoc + "/PinGen/Dorma RS8";
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(path + "/DormaRS8Pins.txt")));
			for (int i = 0; i < systems.size(); i++) {
				if (i == 0) {
					out.write(encrypt("pin") + "," + encrypt("system") + "\n");
				}
				out.write(systems.get(i).getPinKode() + ","
						+ systems.get(i).getSystemNummer() + "\n");
			}
			out.close();
		} catch (IOException e) {
			String prePath = defaultLoc + "/PinGen/Dorma RS8";
			Path path2 = Paths.get(prePath);
			if (Files.exists(path2)) {

			} else {
				File dir = new File(defaultLoc + "/PinGen");
				dir.mkdir();
				File dir2 = new File(defaultLoc + "/PinGen/Dorma RS8");
				dir2.mkdir();
				genSkrivDataSheet();
			}
		}
	}

	public void startDefault() throws Exception {
		settings.updateSettings(ownLoc, "null", "0,0", ownLoc);
		settings.updateCodeSettings(5, "tal", ownLoc);
		fWriteLocX = Float.parseFloat(settings.getWriteLocX());
		fWriteLocY = Float.parseFloat(settings.getWriteLocY());
		fileLoc = settings.getFileLoc();
		defaultLoc = settings.getDefaultLoc();
		codeLength = settings.getCodeLength();
		codeType = settings.getCodeType();
		settings.writeSettings(ownLoc);
	}

	public void readDefaultSettings(boolean startB) throws Exception {
		try {
			settings.updateSettings(ownLoc, "null", "0,0", ownLoc);
			try {
				Path path1 = Paths.get(ownLoc + "/DormaRS8Settings.txt");
				Files.delete(path1);
			} catch (NoSuchFileException e) {

			}
			gui.getFrameCodeSettings().dispose();
			fWriteLocX = Float.parseFloat(settings.getWriteLocX());
			fWriteLocY = Float.parseFloat(settings.getWriteLocY());
			fileLoc = settings.getFileLoc();
			defaultLoc = settings.getDefaultLoc();
			gui.getDefaultLocTF().setText(defaultLoc);
			gui.getFileLocTF().setText(fileLoc);
			gui.getWriteLocX().setText(fWriteLocX + "");
			gui.getWriteLocY().setText(fWriteLocY + "");
			if (startB)
				start();
			gui.mainGUI(name);
			ready();
		} catch (FileNotFoundException ex) {
			gui.settingsGUI(defaultLoc, fileLoc, fWriteLocX, fWriteLocY);
		} catch (IOException ex) {
			err.printError(ownLoc, ex);
		}
	}

	public void pdfPreview() throws Exception {
		String pin = genKode.mockKode("", 0, codeLength);
		PDFont font = PDType1Font.COURIER_BOLD;
		float fontSize = 12.0f;
		PDDocument pdoc = null;
		String path = defaultLoc + "/PinGen/Dorma RS8";
		File input = null;
		try {
			input = new File(fileLoc);
		} catch (NullPointerException e) {
			input = new File(gui.getFileLocTF().getText());
		}
		File output = new File(path + "/DormaRS8TestfileGen.pdf");

		try {
			pdoc = PDDocument.load(input);
			@SuppressWarnings("rawtypes")
			List pages = pdoc.getDocumentCatalog().getAllPages();
			PDPage page = (PDPage) pages.get(0);

			PDPageContentStream contentStream = new PDPageContentStream(pdoc,
					page, true, true, true);

			contentStream.beginText();
			contentStream.setFont(font, fontSize);
			try {
				contentStream.moveTextPositionByAmount(
						Float.parseFloat(gui.getWriteLocX().getText()),
						Float.parseFloat(gui.getWriteLocY().getText()));
			} catch (Exception e) {
				contentStream.moveTextPositionByAmount(fWriteLocX, fWriteLocY);
			}
			contentStream.drawString(pin);
			contentStream.endText();
			contentStream.close();

			File resultFile = new File(input.getParentFile(), output.getName());
			pdoc.save(resultFile.getAbsolutePath());
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(resultFile);
			}
			pdoc.close();
		} catch (NullPointerException e) {
			err.printError(ownLoc, e);
		} catch (IOException e) {
			err.printError(ownLoc, e);
		} catch (COSVisitorException e) {
			err.printError(ownLoc, e);
		}

	}

	public void writePDF(String pin) throws Exception {
		PDFont font = PDType1Font.COURIER_BOLD;
		float fontSize = 12.0f;
		PDDocument pdoc = null;
		String path = defaultLoc + "/PinGen/Dorma RS8";
		File input = new File(fileLoc);
		File output = new File(path + "/DormaRS8TestfileGen.pdf");

		try {
			pdoc = PDDocument.load(input);
			@SuppressWarnings("rawtypes")
			List pages = pdoc.getDocumentCatalog().getAllPages();
			PDPage page = (PDPage) pages.get(0);
			PDPageContentStream contentStream = new PDPageContentStream(pdoc,
					page, true, true, true);

			contentStream.beginText();
			contentStream.setFont(font, fontSize);
			contentStream.moveTextPositionByAmount(fWriteLocX, fWriteLocY);
			contentStream.drawString(pin);
			contentStream.endText();
			contentStream.close();

			File resultFile = new File(input.getParentFile(), output.getName());
			pdoc.save(resultFile.getAbsolutePath());
			pdoc.print();
			pdoc.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			err.printError(ownLoc, e);
		} catch (COSVisitorException e) {
			err.printError(ownLoc, e);
		} catch (PrinterException e) {
			err.printError(ownLoc, e);
		}

	}
}
