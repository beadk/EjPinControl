package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import gui.*;

public class ErrorHandler {
	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(Calendar
			.getInstance().getTime());
	GUI gui;
	private String[] eStr;
	private String fileName;
	Exception e;

	public void errorGUIConnect(GUI gui){
		this.gui=gui;
	}
	
	public void printError(String ownLoc, Exception e) {
		try {
			this.e = e;
			e.printStackTrace();
			eStr = e.toString().split(":");
			fileName = ownLoc + "/PinGenErrors/Error_" + eStr[0] + "_"
					+ timeStamp + ".txt";
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(fileName)));
			e.printStackTrace(out);
			gui.errorExWindow(e.toString(), ownLoc + "/PinGenErrors");
			gui.getbSFrame().addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent event) {
					try {
						gui.resizeErrorFrame();
					} catch (Exception e) {
						printError(ownLoc, e);
					}
				}
			});
			gui.getEmailErrB().addActionListener(new SendErrMess());
			gui.getEmailErrC().addActionListener(new Cancel());
			gui.getbSFrame().addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent event) {
					System.exit(0);
				}
			});
			out.close();

		} catch (FileNotFoundException ex) {
			File dir = new File(ownLoc + "/PinGenErrors");
			dir.mkdir();
			printError(ownLoc, e);
		} catch (IOException ex) {
			String prePath = ownLoc + "/PinGenErrors";
			Path path2 = Paths.get(prePath);
			printError(ownLoc, e);
			if (Files.exists(path2)) {

			} else {
				File dir = new File(ownLoc + "/PinGenErrors");
				dir.mkdir();
			}
			e.printStackTrace();
			System.exit(0);
		} catch (Exception e2) {
			gui.addedWindow("Exception: " + e.toString(), e.toString());
		}
	}

	class Cancel implements ActionListener {
		public void actionPerformed(ActionEvent ex) {
			gui.getbSFrame().dispose();
		}
	}

	class SendErrMess implements ActionListener {
		public void actionPerformed(ActionEvent ex) {
			if (!gui.getEmailErrF().getText().contains("@")
					&& gui.getEmailErrF().getText().split("\\.").length != 2) {
				gui.getEmailErrF().setText(
						"Dette felt skal indeholde en gyldig email");
				gui.getEmailErrF().setForeground(Color.RED);
				gui.getEmailErrF().addFocusListener(new FocusListener() {
					@Override
					public void focusLost(FocusEvent e) {
					}

					@Override
					public void focusGained(FocusEvent e) {
						gui.getEmailErrF().setText("");
						gui.getEmailErrF().setForeground(Color.BLACK);
					}
				});
			} else {
				String eMailto = "ejsensie@gmail.com";
				String eMailpass = "Bea250289";
				String eMailFrom = "ejpincontrol@gmail.com";

				Properties props = new Properties();
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");

				Session session = Session.getInstance(props,
						new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(eMailFrom,
										eMailpass);
							}
						});
				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(eMailFrom));
					message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse(eMailto));
					message.setSubject("Ej Pin Control");
					message.setText(eStr[0] + "\\n " + e.getMessage());

					BodyPart bodyError = new MimeBodyPart();
					bodyError.setText(eStr[0] + "\n"
							+ gui.getEmailErrF().getText() + "\n"
							+ gui.getEmailErrDesF().getText());
					Multipart multipart = new MimeMultipart();
					multipart.addBodyPart(bodyError);
					bodyError = new MimeBodyPart();
					DataSource source = new FileDataSource(fileName);
					bodyError.setDataHandler(new DataHandler(source));
					bodyError.setFileName(fileName);
					multipart.addBodyPart(bodyError);
					message.setContent(multipart);
					Transport.send(message);
					gui.getbSFrame().dispose();
				} catch (MessagingException e1) {
					e1.printStackTrace();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

	}

}
