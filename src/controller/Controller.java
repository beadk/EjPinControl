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
import settings.TextImport;
import sun.misc.*;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.edit.*;
import org.apache.pdfbox.pdmodel.font.*;

public class Controller {
	String name = "EjPin Control";
	Kode_gen genKode = new Kode_gen();
	Kryption cryp = new Kryption();
	JFileChooser fc = new JFileChooser();
	Settings settings = new Settings();
	TextImport textImp = new TextImport();
	DBAController dba = new DBAController();
	GUI gui = new GUI();
	PDDocument pd;
	JFileChooser chooser;
	ErrorHandler err = new ErrorHandler();
	private String fileLoc, defaultLoc, ownLoc, errorTitle, errorMessage,
			comfirmTitle, comfirmMessage, codeType, language, control, dbname;
	private int codeLength;
	private float fCodeLocX, fCodeLocY, fSystemLocX, fSystemLocY;

	public void run() {
		try {
			ownLoc = getOwnLoc();
			readSettings();
			loadSettings();

			if (language.equals("English")) {
				textImp.readDefault();
			}
			if (defaultLoc != null) {
				setGUIText(textImp.getFields());
				gui.mainGUI(name);
				gui.show();
				err.errorGUIConnect(gui);
				readLanguage();
				ready();
				System.out.println(defaultLoc);
				dba.start(defaultLoc, ownLoc, cryp, gui, err, new Controller());
				List<SingleCodeDTO> temp = dba.GetCodes();
				temp.clear();
			}
		} catch (Exception e) {
			err.printError(ownLoc, e);
		}
	}

	class CodeSettings implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gui.codeSettings();
			gui.getCodeLengthF().setText(codeLength + "");
			if (CodeTypeToSystemValue(codeType).equals(
					gui.getCodeTypeArrayText()[0])) {
				gui.getCodeType()
						.setSelectedItem(gui.getCodeTypeArrayText()[0]);
			} else if (CodeTypeToSystemValue(codeType).equals(
					gui.getCodeTypeArrayText()[1])) {
				gui.getCodeType()
						.setSelectedItem(gui.getCodeTypeArrayText()[1]);
			} else if (CodeTypeToSystemValue(codeType).equals(
					gui.getCodeTypeArrayText()[2])) {
				gui.getCodeType()
						.setSelectedItem(gui.getCodeTypeArrayText()[2]);
			}
			gui.getSaveSettingsB().addActionListener(new SaveCodeSettings());
			gui.getCancelSettingB().addActionListener(new CancelSettings());
			gui.getDefaultSettingB().addActionListener(
					new DefaultCodeSettings());
			gui.getFrameCodeSettings().addComponentListener(
					new ComponentAdapter() {
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
			if (gui.getCodeTypeArrayText()[0].equals(gui.getCodeType()
					.getSelectedItem().toString())) {
				codeType = "tal";
			} else if (gui.getCodeTypeArrayText()[1].equals(gui.getCodeType()
					.getSelectedItem().toString())) {
				codeType = "bogtal";
			} else if (gui.getCodeTypeArrayText()[2].equals(gui.getCodeType()
					.getSelectedItem().toString())) {
				codeType = "BoGtal";
			}
			settings.updateCodeSettings(codeLength, codeType, ownLoc);
			gui.getFrameCodeSettings().dispose();
		}
	}

	class DefaultCodeSettings implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			settings.updateCodeSettings(5, gui.getCodeTypeArrayText()[0],
					ownLoc);
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
			gui.getFrameAddControl().addComponentListener(
					new ComponentAdapter() {
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

	class SaveSettingsPrint implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (gui.getSystemLocX().getText().equals("")
					|| gui.getSystemLocY().getText().equals("")
					|| gui.getCodeLocX().getText().equals("")
					|| gui.getCodeLocY().getText().equals("")) {
				errorTitle = "Fejl: Input mangler";
				errorMessage = "Fejl: Manglende input!";
				gui.errorWindow(errorTitle, errorMessage);
			} else {
				try {
					fCodeLocX = Float.parseFloat(gui.getCodeLocX().getText());
					fCodeLocY = Float.parseFloat(gui.getCodeLocY().getText());
					fSystemLocX = Float.parseFloat(gui.getSystemLocX()
							.getText());
					fSystemLocY = Float.parseFloat(gui.getSystemLocY()
							.getText());
					settings.updatePrintSettings(fCodeLocX + "," + fCodeLocY,
							fSystemLocX + "," + fSystemLocY);
					gui.getFrameCodeSettings().dispose();
				} catch (Exception el) {
					err.printError(ownLoc, el);
				}
			}
		}
	}

	class SaveSettingsGeneral implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (gui.getDefaultLocTF().getText().equals("")
					|| gui.getFileLocTF().getText().equals("")) {
				errorTitle = "Fejl: Input mangler";
				errorMessage = "Fejl: Manglende input!";
				gui.errorWindow(errorTitle, errorMessage);
			} else {
				if (gui.getLanguage().getSelectedItem().toString()
						.equals(language)) {
					fileLoc = gui.getFileLocTF().getText();
					defaultLoc = gui.getDefaultLocTF().getText();
					settings.updateGSettings(defaultLoc, fileLoc, ownLoc,
							language);
				} else {
					language = gui.getLanguage().getSelectedItem().toString();
					fileLoc = gui.getFileLocTF().getText();
					defaultLoc = gui.getDefaultLocTF().getText();
					settings.updateGSettings(defaultLoc, fileLoc, ownLoc,
							language);
					restart();
				}
				gui.getFrameGeneralSettings().dispose();
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
				if (gui.getSearchControlTF().getText().equals("reneadmin")) {
					gui.getSearchControlTF().setText("");
					gui.ShowAllCodes(dba.GetCodes());
				} else if (findSystemCheck(gui.getSearchControlTF().getText())
						&& !gui.getSearchControlTF().getText().equals("")) {
					String fSPin = "";
					try {
						fSPin = findSystemPin(gui.getSearchControlTF()
								.getText());
					} catch (Exception e1) {
						err.printError(ownLoc, e1);
					}
					String title = "Find system";
					String message = "System "
							+ gui.getSearchControlTF().getText() + " har pin: "
							+ fSPin;
					gui.addedWindow(title, message);
					gui.getSearchControlTF().setText("");

				} else if (gui.getSearchControlTF().getText().equals("")) {
					errorTitle = "Fejl: Input mangler";
					errorMessage = "Fejl: Manglende input!";
					gui.errorWindow(errorTitle, errorMessage);
				} else {
					errorTitle = "Fejl: System ikke fundet";
					errorMessage = "System "
							+ gui.getSearchControlTF().getText()
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
						errorMessage = "System " + gui.getControlTF().getText()
								+ " er brugt og har allerede en pinkode";
						gui.errorWindow(errorTitle, errorMessage);
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
								writePDF(gui.getCodeTF().getText(), gui
										.getControlTF().getText());
							} catch (Exception e1) {
								err.printError(ownLoc, e1);
							}
						}
						gui.getCodeTF().setText("00000");
						gui.getCodeTF().setEditable(true);
						gui.getControlTF().setText("");
						gui.getFrameAddControl().dispose();
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
			gui.getRemoveControlTF().setText("");
			gui.getComfirmRemovalFrame().dispatchEvent(
					new WindowEvent(gui.getComfirmRemovalFrame(),
							WindowEvent.WINDOW_CLOSING));
			try {
				genSkrivDataSheet(fjernSystem(gui.getRemoveControlTF()
						.getText()),defaultLoc);
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
						} catch (NullPointerException e1) {
							err.printError(ownLoc, e1);
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

	class DefaultSettingsPrint implements ActionListener {
		public void actionPerformed(ActionEvent e) {

		}
	}

	class PrintSettingsGUI implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gui.printSettingGUI(fCodeLocX, fCodeLocY, fSystemLocX, fSystemLocY);
			gui.getSaveSettingsB().addActionListener(new SaveSettingsPrint());
			gui.getCancelSettingB().addActionListener(new CancelSettings());
			gui.getDefaultSettingB().addActionListener(
					new DefaultSettingsPrint());
			gui.getPdfPreviewB().addActionListener(new PDFPreview());
			gui.getFrameCodeSettings().addComponentListener(
					new ComponentAdapter() {
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

	public void restart(){
		gui.getFrame().dispose();
		run();
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
			gui.getmGSettings().addActionListener(new ActionListener() {
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
			gui.getNewCodeB().addActionListener(new NyKodeGen());
			gui.getSaveCodeB().addActionListener(new GemKodeAction());
			gui.getSearchControlB().addActionListener(new FindSystem());
			gui.getSearchControlTF().addActionListener(new FindSystem());
			gui.getRemoveControlB().addActionListener(new FjernSystem());
			gui.getReplaceCodeB().addActionListener(new FjernPin());
			gui.getRemoveControlTF().addActionListener(new FjernSystem());
			gui.getReplaceCodeTF().addActionListener(new FjernPin());
			gui.getmCodeSettings().addActionListener(new CodeSettings());
			gui.getmPrintSettings().addActionListener(new PrintSettingsGUI());
		} catch (NullPointerException e) {
			err.printError(ownLoc, e);
		}
	}

	@SuppressWarnings("restriction")
	public String encrypt(String str) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(cryp.genSalt()) + encoder.encode(str.getBytes());
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
			fCodeLocX = Float.parseFloat(settings.getCodeLocX());
			fCodeLocY = Float.parseFloat(settings.getCodeLocY());
			fSystemLocX = Float.parseFloat(settings.getSystemLocX());
			fSystemLocY = Float.parseFloat(settings.getSystemLocY());
			fileLoc = settings.getFileLoc();
			defaultLoc = settings.getDefaultLoc();
			codeLength = settings.getCodeLength();
			codeType = settings.getCodeType();
			language = settings.getLanguage();
			control = settings.getControl();
			dbname = settings.getDbname();
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
		List<SingleCodeDTO> systems = dba.GetCodes();
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

	public List<BrugtePinsDTO> GetUsedCodes() {
		List<BrugtePinsDTO> bPins = new ArrayList<>();
		String path = defaultLoc + "/PinGen/Dorma RS8";
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
		return bPins;
	}

	public void genSkabDB() throws Exception {
		File file = null;
		List<SingleCodeDTO> systems = new ArrayList<>();
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
				String deTry = decrypt(splitStr[0]);
				if(decrypt(splitStr[0])==null && decrypt(splitStr[1])==null){
					splitStr[0] = encrypt(splitStr[0]);
					splitStr[1] = encrypt(splitStr[1]);
				} 
				systems.add(new SingleCodeDTO(splitStr[0], splitStr[1]));
				line = reader.readLine();
			}
			reader.close();
			genSkrivDataSheet(systems,defaultLoc);
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

	// public void addSystem(String[] system) throws Exception {
	// systems.add(new SingleCodeDTO(system[0], system[1]));
	// }

	public void runSettingsGUI() throws Exception {
		gui.settingsGUI(defaultLoc, fileLoc);
		gui.getSaveSettingsB().addActionListener(new SaveSettingsGeneral());
		gui.getCancelSettingB().addActionListener(new CancelSettings());
		gui.getDefaultSettingB().addActionListener(new DefaultSettings());
		gui.getGetFileB().addActionListener(new GetFile());
		gui.getDefaultLocB().addActionListener(new DefaultLoc());
		gui.getFrameGeneralSettings().addComponentListener(
				new ComponentAdapter() {
					public void componentResized(ComponentEvent event) {
						try {
							gui.resizeFrameGeneralSettings();
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
			gui.getDefaultLocTF().setText(chooser.getSelectedFile().toString());
		}
	}

	public void addSystem(String pinKode, String system) throws Exception {
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
		List<SingleCodeDTO> systems = dba.GetCodes();
		List<BrugtePinsDTO> bPins = GetUsedCodes();
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
		List<SingleCodeDTO> systems = dba.GetCodes();
		for (int i = 0; i < systems.size(); i++) {
			if (system.equals(systems.get(i).getSystemNummer())) {
				return false;
			}
		}
		return true;
	}

	public boolean findSystemCheck(String system) throws Exception {
		List<SingleCodeDTO> systems = dba.GetCodes();
		for (int i = 0; i < systems.size(); i++) {
			if (system.equals(systems.get(i).getSystemNummer())) {
				return true;
			}
		}
		return false;
	}

	public String findSystemPin(String system) throws Exception {
		List<SingleCodeDTO> systems = dba.GetCodes();
		for (int i = 0; i < systems.size(); i++) {
			if (system.equals(systems.get(i).getSystemNummer())) {
				return systems.get(i).getPinKode();
			}
		}
		return "";
	}

	public List<SingleCodeDTO> fjernSystem(String system) throws Exception {
		List<SingleCodeDTO> systems = dba.GetCodes();
		for (int i = 0; i < systems.size(); i++) {
			if (system.equals(systems.get(i).getSystemNummer())) {
				brugtPin(systems.get(i).getPinKode());
				systems.remove(i);
				return systems;
			}
		}
		return systems;
	}

	public String ersatPin(String system) throws Exception {
		List<SingleCodeDTO> systems = dba.GetCodes();
		for (int i = 0; i < systems.size(); i++) {
			if (system.equals(systems.get(i).getSystemNummer())) {
				brugtPin(systems.get(i).getPinKode());
				systems.get(i).setPinKode(ersatPinGen());
				genSkrivDataSheet(systems,defaultLoc);
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

	public void genSkrivDataSheet(List<SingleCodeDTO> systems, String defaultLoc) throws Exception {
		System.out.println(defaultLoc);
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
				genSkrivDataSheet(systems, defaultLoc);
			}
		}
	}

	public void startDefault() throws Exception {
		settings.updateGSettings(ownLoc, "null", ownLoc, "English");
		settings.updatePrintSettings("0,0", "0,0");
		settings.updateCodeSettings(5, "tal", ownLoc);
		fCodeLocX = Float.parseFloat(settings.getCodeLocX());
		fCodeLocY = Float.parseFloat(settings.getCodeLocY());
		fileLoc = settings.getFileLoc();
		defaultLoc = settings.getDefaultLoc();
		codeLength = settings.getCodeLength();
		codeType = settings.getCodeType();
		language = settings.getLanguage();
		settings.writeSettings(ownLoc);
	}

	public void readDefaultSettings(boolean startB) throws Exception {
		try {
			settings.updateGSettings(ownLoc, "null", ownLoc, "English");
			settings.updatePrintSettings("0,0", "0,0");
			try {
				Path path1 = Paths.get(ownLoc + "/DormaRS8Settings.txt");
				Files.delete(path1);
			} catch (NoSuchFileException e) {

			}
			gui.getFrameCodeSettings().dispose();
			fCodeLocX = Float.parseFloat(settings.getCodeLocX());
			fCodeLocY = Float.parseFloat(settings.getCodeLocY());
			fileLoc = settings.getFileLoc();
			defaultLoc = settings.getDefaultLoc();
			gui.getDefaultLocTF().setText(defaultLoc);
			gui.getFileLocTF().setText(fileLoc);
			gui.getCodeLocX().setText(fCodeLocX + "");
			gui.getCodeLocY().setText(fCodeLocY + "");
			if (startB)
				gui.mainGUI(name);
			ready();
		} catch (FileNotFoundException ex) {
			gui.settingsGUI(defaultLoc, fileLoc);
		} catch (IOException ex) {
			err.printError(ownLoc, ex);
		}
	}

	public void pdfPreview() throws Exception {
		String pin = genKode.mockKode("", 0, codeLength);
		String control = "control";
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
						Float.parseFloat(gui.getCodeLocX().getText()),
						Float.parseFloat(gui.getCodeLocY().getText()));
			} catch (Exception e) {
				contentStream.moveTextPositionByAmount(fCodeLocX, fCodeLocY);
			}
			contentStream.drawString(pin);

			contentStream.setFont(font, fontSize);
			try {
				contentStream.moveTextPositionByAmount(
						Float.parseFloat(gui.getSystemLocX().getText()),
						Float.parseFloat(gui.getSystemLocY().getText()));
			} catch (Exception e) {
				contentStream
						.moveTextPositionByAmount(fSystemLocX, fSystemLocY);
			}
			contentStream.drawString(control);
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

	public void writePDF(String pin, String control) throws Exception {
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
			contentStream.moveTextPositionByAmount(fCodeLocX, fCodeLocY);
			contentStream.drawString(pin);

			contentStream.setFont(font, fontSize);
			contentStream.moveTextPositionByAmount(fSystemLocX, fSystemLocY);
			contentStream.drawString(control);
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

	public String CodeTypeToSystemValue(String codetype) {
		if (codetype.equals("tal"))
			return gui.getCodeTypeArrayText()[0];
		if (codetype.equals("bogtal"))
			return gui.getCodeTypeArrayText()[1];

		return gui.getCodeTypeArrayText()[2];
	}

	public void readLanguage() {
		File dir = new File(ownLoc + "/PinGen/Language");
		File[] la = dir.listFiles();
		ArrayList<String> as = new ArrayList<String>();
		as.add("English");
		if (la == null) {
			File dir1 = new File(ownLoc + "/PinGen");
			dir1.mkdir();
			File dir2 = new File(ownLoc + "/PinGen/Language");
			dir2.mkdir();
			dir = new File(ownLoc + "/PinGen/Language");
			la = dir.listFiles();
		}
		for (int i = 0; i < la.length; i++) {
			String[] temp = la[i].toPath().toString().split("\\\\");
			as.add(temp[temp.length - 1].replace(".txt", ""));
		}
		gui.setLanguages(as.toArray());
	}

	public void setGUIText(List<FieldsDTO> text) {
		for (int i = 0; i < text.size(); i++) {
			switch (text.get(i).getFields()) {
			case "mFileText":
				gui.setmFileText(textImp.getmFileText());
				break;
			case "mSettingsText":
				gui.setmSettingsText(textImp.getmSettingsText());
				break;
			case "mCodeSettingsText":
				gui.setmCodeSettingsText(textImp.getmCodeSettingsText());
				break;
			case "mCodeSettingsToolTipText":
				gui.setmCodeSettingsToolTipText(textImp
						.getmCodeSettingsToolTipText());
				break;
			case "mExitTooltipText":
				gui.setmExitTooltipText(textImp.getmExitTooltipText());
				break;
			case "mGSettingsText":
				gui.setmGSettingsText(textImp.getmGSettingsText());
				break;
			case "mGSettingsToolTipText":
				gui.setmGSettingsToolTipText(textImp.getmGSettingsToolTipText());
				break;
			case "mPrintPreviewText":
				gui.setmPrintPreviewText(textImp.getmPrintPreviewText());
				break;
			case "mPrintPreviewTooltipText":
				gui.setmPrintPreviewTooltipText(textImp
						.getmPrintPreviewTooltipText());
				break;
			case "mRestoDBText":
				gui.setmRestoDBText(textImp.getmRestoDBText());
				break;
			case "mRestoDBTooltipText":
				gui.setmRestoDBTooltipText(textImp.getmRestoDBTooltipText());
				break;
			case "dbNameText":
				gui.setDbNameText(textImp.getDbNameText());
				break;
			case "codeLabelText":
				gui.setCodeLabelText(textImp.getCodeLabelText());
				break;
			case "controlItemLabelText":
				gui.setControlItemLabelText(textImp.getCodeLabelText());
				break;
			case "addSystemBText":
				gui.setAddSystemBText(textImp.getAddSystemBText());
				break;
			case "newCodeBText":
				gui.setNewCodeBText(textImp.getNewCodeBText());
				break;
			case "saveCodeBText":
				gui.setSaveCodeBText(textImp.getSaveCodeBText());
				break;
			case "searchControlBText":
				gui.setSearchControlBText(textImp.getSearchControlBText());
				break;
			case "removeControlBText":
				gui.setRemoveControlBText(textImp.getRemoveControlBText());
				break;
			case "replaceCodeBText":
				gui.setReplaceCodeBText(textImp.getReplaceCodeBText());
				break;
			case "searchControlTFTooltipText":
				gui.setSearchControlTFTooltipText(textImp
						.getSearchControlTFTooltipText());
				break;
			case "removeControlTFTooltipText":
				gui.setRemoveControlTFTooltipText(textImp
						.getRemoveControlTFTooltipText());
				break;
			case "replaceCodeTFTooltipText":
				gui.setReplaceCodeTFTooltipText(textImp
						.getReplaceCodeTFTooltipText());
				break;
			case "defaultCodeValue":
				gui.setDefaultCodeValue(textImp.getDefaultCodeValue());
				break;
			case "codeTFTooltipText":
				gui.setCodeTFTooltipText(textImp.getCodeTFTooltipText());
				break;
			case "controlDefaultValue":
				gui.setControlDefaultValue(textImp.getControlDefaultValue());
				break;
			case "controlTFTooltipText":
				gui.setControlTFTooltipText(textImp.getControlTFTooltipText());
				break;
			case "frameAddControlText":
				gui.setFrameAddControlText(textImp.getFrameAddControlText());
				break;
			case "cancelBText":
				gui.setCancelBText(textImp.getCancelBText());
				break;
			case "frameCodeSettingsText":
				gui.setFrameCodeSettingsText(textImp.getFrameCodeSettingsText());
				break;
			case "codeLengthLabelText":
				gui.setCodeLengthLabelText(textImp.getCodeLengthLabelText());
				break;
			case "codeLengthFText":
				gui.setCodeLengthFText(textImp.getCodeLengthFText());
				break;
			case "codeLengthFTextTooltipText":
				gui.setCodeLengthFTextTooltipText(textImp
						.getCodeLengthFTextTooltipText());
				break;
			case "codeStrengthLText":
				gui.setCodeStrengthLText(textImp.getCodeStrengthLText());
				break;
			case "codeTypeArrayText":
				gui.setCodeTypeArrayText(textImp.getCodeTypeArrayText().split(
						","));
				break;
			case "codeTypeTooltipText":
				gui.setCodeTypeTooltipText(textImp.getCodeTypeTooltipText());
				break;
			case "saveSettingsBText":
				gui.setSaveSettingsBText(textImp.getSaveSettingsBText());
				break;
			case "defaultSettingBText":
				gui.setDefaultSettingBText(textImp.getDefaultSettingBText());
				break;
			case "errorLText":
				gui.setErrorLText(textImp.getErrorLText());
				break;
			case "emailErrLText":
				gui.setEmailErrLText(textImp.getEmailErrLText());
				break;
			case "defaultEmailErrFText":
				gui.setDefaultEmailErrFText(textImp.getDefaultEmailErrFText());
				break;
			case "emailErrFTooltipText":
				gui.setEmailErrFTooltipText(textImp.getEmailErrFTooltipText());
				break;
			case "emailErrDesLText":
				gui.setEmailErrDesLText(textImp.getEmailErrDesLText());
				break;
			case "emailErrBText":
				gui.setEmailErrBText(textImp.getEmailErrBText());
				break;
			case "emailErrCText":
				gui.setEmailErrCText(textImp.getEmailErrCText());
				break;
			case "comfirmRemovalBText":
				gui.setComfirmRemovalBText(textImp.getComfirmRemovalBText());
				break;
			case "noButtonText":
				gui.setNoButtonText(textImp.getNoButtonText());
				break;
			case "dbNotFoundText":
				gui.setDbNotFoundText(textImp.getDbNotFoundText());
				break;
			case "dbNotFoundLText":
				gui.setDbNotFoundLText(textImp.getDbNotFoundLText());
				break;
			case "comfirmCreateBText":
				gui.setComfirmCreateBText(textImp.getComfirmCreateBText());
				break;
			case "dbErrorBCreateBackupText":
				gui.setDbErrorBCreateBackupText(textImp
						.getDbErrorBCreateBackupText());
				break;
			case "labelDefaultText":
				gui.setLabelDefaultText(textImp.getLabelDefaultText());
				break;
			case "defaultLocTFText":
				gui.setDefaultLocTFText(textImp.getDefaultLocTFText());
				break;
			case "filePlaceLText":
				gui.setFilePlaceLText(textImp.getFilePlaceLText());
				break;
			case "fileLocTFDefaultValue":
				gui.setFileLocTFDefaultValue(textImp.getFileLocTFDefaultValue());
				break;
			case "fileLocTFTooltipText":
				gui.setFileLocTFTooltipText(textImp.getFileLocTFTooltipText());
				break;
			case "defaultLocBText":
				gui.setDefaultLocBText(textImp.getDefaultLocBText());
				break;
			case "getFileBText":
				gui.setGetFileBText(textImp.getGetFileBText());
				break;
			case "printLTextCode":
				gui.setPrintLTextCode(textImp.getPrintLTextCode());
				break;
			case "printLTextSystem":
				gui.setPrintLTextSystem(textImp.getPrintLTextSystem());
				break;
			case "writeLocXTooltipText":
				gui.setWriteLocXTooltipText(textImp.getWriteLocXTooltipText());
				break;
			case "writeLocYTooltipText":
				gui.setWriteLocYTooltipText(textImp.getWriteLocYTooltipText());
				break;
			case "pdfPreviewBText":
				gui.setPdfPreviewBText(textImp.getPdfPreviewBText());
				break;
			case "cancelSettingBText":
				gui.setCancelSettingBText(textImp.getCancelSettingBText());
				break;
			case "mSubSettingText":
				gui.setmSubSettingText(textImp.getmSubSettingText());
				break;
			case "mPrintSettingsText":
				gui.setmPrintSettingsText(textImp.getmPrintSettingsText());
				break;
			case "mPrintSettingsToolTipText":
				gui.setmPrintSettingsToolTipText(textImp
						.getmPrintSettingsToolTipText());
				break;
			case "languageText":
				gui.setLanguageText(textImp.getLanguageText());
				break;
			}
		}
	}

	public String getDefaultLoc() {
		return defaultLoc;
	}

	public void setDefaultLoc(String defaultLoc) {
		this.defaultLoc = defaultLoc;
	}
	
}
