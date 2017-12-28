package controller;

import gui.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import DTO.SingleCodeDTO;

public class DBAController {
	private String defaultLoc;
	private String ownLoc;
	Kryption cryp;
	GUI gui;
	ErrorHandler err;
	Controller cont;

	public void start(String defaultLoc, String ownLoc, Kryption cryp, GUI gui,
			ErrorHandler err, Controller cont) {
		this.defaultLoc = defaultLoc;
		this.ownLoc = ownLoc;
		this.gui = gui;
		this.cryp = cryp;
		this.err = err;
		this.cont = cont;
	}

	public List<SingleCodeDTO> GetCodes() throws Exception {
		List<SingleCodeDTO> systems = new ArrayList<>();
		cont.setDefaultLoc(defaultLoc);
		String path = defaultLoc + "/PinGen/Dorma RS8";
		try {

			FileInputStream file = new FileInputStream(path
					+ "/DormaRS8Pins.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					file));
			String line = reader.readLine();
			while (line != null) {
				if (cryp.decrypt(line.split(",")[0]).equals("pin")) {
					line = reader.readLine();
				} else {
					if (line.split(",").length == 2) {
						systems.add(new SingleCodeDTO(cryp.decrypt(line
								.split(",")[0]), cryp.decrypt(
								line.split(",")[1]).replace(" ", "")));
					} else if (line.split(",").length == 3) {
						systems.add(new SingleCodeDTO(cryp.decrypt(line
								.split(",")[0]), cryp.decrypt(
								line.split(",")[1]).replace(" ", ""), cryp
								.decrypt(line.split(",")[2]).replace(" ", "")));
					}
					line = reader.readLine();
				}
			}
			reader.close();
		} catch (FileNotFoundException ex) {
			gui.dbError();
			gui.getConfirmCreateB().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						cont.newDB();
						gui.getConfirmRemovalFrame().dispose();
					} catch (Exception e1) {
						err.printError(ownLoc, e1);
					}
				}
			});
			gui.getDbErrorBCreateBackup().addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								cont.genSkabDB();
							} catch (Exception e1) {
								err.printError(ownLoc, e1);
							}
						}
					});
		} catch (IOException ex) {
			err.printError(ownLoc, ex);
		}
		return systems;
	}
}
