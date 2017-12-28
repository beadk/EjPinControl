package settings;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import controller.ErrorHandler;

public class Settings {

	ErrorHandler err = new ErrorHandler();
	private String fileLoc;
	private String codeLocX, codeLocY, systemLocX, systemLocY;
	private String defaultLoc;
	private int codeLength;
	private String codeType;
	private String path = "/PinGen/Dorma RS8";
	private String language = "English";
	private String dbname;
	private String control;

	public void readSettings(String line) {
		String[] lineSplit = line.split(";");
		switch (lineSplit[0]) {
		case "fileLoc":
			fileLoc = lineSplit[1];
			break;
		case "codeLoc":
			String[] lineSplit2 = lineSplit[1].split(",");
			codeLocX = lineSplit2[0];
			codeLocY = lineSplit2[1];
			break;
		case "systemLoc":
			String[] lineSplit3 = lineSplit[1].split(",");
			systemLocX = lineSplit3[0];
			systemLocY = lineSplit3[1];
			break;
		case "defaultLoc":
			defaultLoc = lineSplit[1];
			break;
		case "codeLength":
			codeLength = Integer.parseInt(lineSplit[1]);
			break;
		case "codeType":
			codeType = lineSplit[1];
			break;
		case "language":
			language = lineSplit[1];
			break;
		case "control":
			control = lineSplit[1];
			break;
		case "dbname":
			dbname = lineSplit[1];
			break;
		}
	}

	public void updateGSettings(String newDefaultLoc, String newFileLoc, String ownLoc, String language) {
		defaultLoc = newDefaultLoc;
		fileLoc = newFileLoc;
		writeSettings(ownLoc);
		this.language = language;
	}

	public void updatePrintSettings(String newCodeLoc, String newSystemLoc){
		String[] lineSplit2 = newCodeLoc.split(",");
		codeLocX = lineSplit2[0];
		codeLocY = lineSplit2[1];
		String[] lineSplit3 = newSystemLoc.split(",");
		systemLocX = lineSplit3[0];
		systemLocY = lineSplit3[1];
	}
	
	public void updateCodeSettings(int codeLength, String codeType,
			String ownLoc) {
		this.codeLength = codeLength;
		this.codeType = codeType;
		writeSettings(ownLoc);
	}

	public void writeSettings(String ownLoc) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(ownLoc + path + "/DormaRS8Settings.txt")));
			out.write("defaultLoc" + ";" + defaultLoc + "\n");
			out.write("fileLoc" + ";" + fileLoc + "\n");
			out.write("codeLoc" + ";" + codeLocX + "," + codeLocY + "\n");
			out.write("systemLoc" + ";" + systemLocX + "," + systemLocY + "\n");
			out.write("codeLength" + ";" + codeLength + "\n");
			out.write("codeType" + ";" + codeType + "\n");
			out.write("language" + ";" + language + "\n");
			out.close();
		} catch (FileNotFoundException e) {
			File dir = new File(ownLoc + "/PinGen");
			dir.mkdir();
			File dir2 = new File(ownLoc + "/PinGen/Dorma RS8");
			dir2.mkdir();
		} catch (IOException e) {
			String prePath = ownLoc + "/PinGen/Dorma RS8";
			Path path2 = Paths.get(prePath);
			if (Files.exists(path2)) {

			} else {
				File dir = new File(ownLoc + "/PinGen");
				dir.mkdir();
				File dir2 = new File(ownLoc + "/PinGen/Dorma RS8");
				dir2.mkdir();
			}
			err.printError(ownLoc, e);
			e.printStackTrace();
			System.exit(0);
		} catch (Exception e) {
			err.printError(ownLoc, e);
		}
	}

	public String getFileLoc() {
		return fileLoc;
	}

	public void setFileLoc(String fileLoc) {
		this.fileLoc = fileLoc;
	}

	public String getDefaultLoc() {
		return defaultLoc;
	}

	public void setDefaultLoc(String defaultLoc) {
		this.defaultLoc = defaultLoc;
	}

	public int getCodeLength() {
		return codeLength;
	}

	public void setCodeLength(int codeLength) {
		this.codeLength = codeLength;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public String getCodeLocX() {
		return codeLocX;
	}

	public void setCodeLocX(String codeLocX) {
		this.codeLocX = codeLocX;
	}

	public String getCodeLocY() {
		return codeLocY;
	}

	public void setCodeLocY(String codeLocY) {
		this.codeLocY = codeLocY;
	}

	public String getSystemLocX() {
		return systemLocX;
	}

	public void setSystemLocX(String systemLocX) {
		this.systemLocX = systemLocX;
	}

	public String getSystemLocY() {
		return systemLocY;
	}

	public void setSystemLocY(String systemLocY) {
		this.systemLocY = systemLocY;
	}

}
