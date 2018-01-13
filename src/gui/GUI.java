package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

import DTO.SingleCodeDTO;

public class GUI {
	private JFrame confirmRemovalFrame;
	private JFrame frame, frameCodeSettings, frameAddControl, bSFrame,
			frameGeneralSettings;
	private JPanel panel, panelSettings, panelAddControl, bSPanel, systemPanel;
	private JComboBox<Object> codeType, language;
	private JButton newCodeB, saveCodeB, searchControlB, removeControlB,
			replaceCodeB, confirmRemovalB, noButton, pdfPreviewB, getFileB,
			saveSettingsB, cancelSettingB, defaultSettingB, defaultLocB,
			addSystemB, cancelB, emailErrB, emailErrC;
	private JTextField codeTF, controlTF, searchControlTF, removeControlTF,
			replaceCodeTF, fileLocTF, codeLocX, codeLocY, defaultLocTF,
			codeLengthF, emailErrF, systemLocX, systemLocY;
	private JLabel dbName, codeLabel, controlItemLabel, emailErrL,
			emailErrDesL, languageLabel;
	private JTextArea emailErrDesF;
	private Object[] languages;
	JMenuBar menu;
	JFileChooser chooser;
	JMenu mFile, mHelp, mSettings, mSubSettings;
	JMenuItem mExit, mGSettings, mPrintPreview, mRestoDB, mCodeSettings,
			mPrintSettings;
	private String mFileText;
	private String mSettingsText;
	private String mCodeSettingsText;
	private String mCodeSettingsToolTipText;
	private String mExitTooltipText;
	private String mGSettingsText;
	private String mGSettingsToolTipText;
	private String mPrintPreviewText;
	private String mPrintPreviewTooltipText;
	private String mRestoDBText;
	private String mRestoDBTooltipText;
	private String dbNameText;
	private String codeLabelText;
	private String controlItemLabelText;
	private String addSystemBText;
	private String newCodeBText;
	private String saveCodeBText;
	private String searchControlBText;
	private String removeControlBText;
	private String replaceCodeBText;
	private String searchControlTFTooltipText;
	private String removeControlTFTooltipText;
	private String replaceCodeTFTooltipText;
	private String defaultCodeValue;
	private String codeTFTooltipText;
	private String controlDefaultValue;
	private String controlTFTooltipText;
	private String frameAddControlText;
	private String cancelBText;
	private String frameCodeSettingsText;
	private JLabel codeLengthL;
	private String codeLengthLabelText;
	private String codeLengthFText;
	private String codeLengthFTextTooltipText;
	private JLabel codeStrengthL;
	private String codeStrengthLText;
	private String[] codeTypeArrayText;
	private String codeTypeTooltipText;
	private String saveSettingsBText;
	private String defaultSettingBText;
	private JLabel errorL;
	private String errorLText;
	private String emailErrLText;
	private String defaultEmailErrFText;
	private String emailErrFTooltipText;
	private String emailErrDesLText;
	private String emailErrBText;
	private String emailErrCText;
	private String confirmRemovalBText;
	private String noButtonText;
	private String dbNotFoundText;
	private JLabel dbNotFoundL;
	private String dbNotFoundLText;
	private JButton confirmCreateB;
	private String confirmCreateBText;
	private JButton dbErrorBCreateBackup;
	private String dbErrorBCreateBackupText;
	private JLabel labelDefault;
	private String labelDefaultText;
	private String defaultLocTFText;
	private String filePlaceLText;
	private JLabel filePlaceL;
	private String fileLocTFDefaultValue;
	private String fileLocTFTooltipText;
	private String defaultLocBText;
	private String getFileBText;
	private String printLTextCode;
	private String printLTextSystem;
	private JLabel printLCode;
	private JLabel printLSystem;
	private String writeLocXTooltipText;
	private String writeLocYTooltipText;
	private String pdfPreviewBText;
	private String cancelSettingBText;
	private String mSubSettingText;
	private String mPrintSettingsText;
	private String mPrintSettingsToolTipText;
	private String languageText;
	private String frameGeneralSettingsText;
	private String inputError;
	private String inputErrorMS;
	private String findSystem;
	private String systemFoundMS;
	private String systemNotFound;
	private String systemNotFoundMS;
	private String errorUsedNumber;
	private String errorUsedNumberMS;
	private String controlAdded;
	private String controlAddedMS;
	private String confirmRemoval;
	private String confirmRemovalMS;
	private String pinReplacementConfirm;
	private String pinReplacementConfirmMS;
	private String noSettingsError;
	private String noSettingsErrorMS;
	private String pinReplaced;
	private String pinReplacedMS;

	public void mainGUI(String name) {
		frame = new JFrame(name);
		frame.setMinimumSize(new Dimension(585, 310));
		panel = new JPanel();
		panel.setBackground(new Color(250, 250, 250));
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		try {
			frame.setIconImage(ImageIO.read(new File("res/key.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		menu = new JMenuBar();
		menu.setBounds(0, 0, panel.getWidth(), 20);
		panel.add(menu);

		mFile = new JMenu(mFileText);
		mFile.setMnemonic(KeyEvent.VK_E);

		mSettings = new JMenu(mSettingsText);
		mSettings.setMnemonic(KeyEvent.VK_I);

		mSubSettings = new JMenu(mSubSettingText);

		mCodeSettings = new JMenuItem(mCodeSettingsText);
		mCodeSettings.setMnemonic(KeyEvent.VK_O);
		mCodeSettings.setToolTipText(mCodeSettingsToolTipText);

		mExit = new JMenuItem("Exit");
		mExit.setMnemonic(KeyEvent.VK_E);
		mExit.setToolTipText(mExitTooltipText);

		KeyStroke printKeyStroke = KeyStroke.getKeyStroke("crtl S");
		mGSettings = new JMenuItem(mGSettingsText);
		mGSettings.setMnemonic(KeyEvent.VK_P);
		mGSettings.setAccelerator(printKeyStroke);
		mGSettings.setToolTipText(mGSettingsToolTipText);

		KeyStroke printKeyStroke2 = KeyStroke.getKeyStroke("crtl P");
		mPrintSettings = new JMenuItem(mPrintSettingsText);
		mPrintSettings.setMnemonic(KeyEvent.VK_P);
		mPrintSettings.setAccelerator(printKeyStroke2);
		mPrintSettings.setToolTipText(mPrintSettingsToolTipText);

		mPrintPreview = new JMenuItem(mPrintPreviewText);
		mPrintPreview.setMnemonic(KeyEvent.VK_P);
		mPrintPreview.setToolTipText(mPrintPreviewTooltipText);

		mRestoDB = new JMenuItem(mRestoDBText);
		mRestoDB.setMnemonic(KeyEvent.VK_R);
		mRestoDB.setToolTipText(mRestoDBTooltipText);

		mFile.add(mPrintPreview);
		mFile.add(mRestoDB);
		mFile.add(mExit);
		mSettings.add(mSubSettings);
		mSubSettings.add(mGSettings);
		mSubSettings.add(mPrintSettings);
		mSettings.add(mCodeSettings);
		menu.add(mFile);
		menu.add(mSettings);

		dbName = new JLabel(dbNameText);
		dbName.setHorizontalAlignment(SwingConstants.CENTER);
		dbName.setBounds(15, 30, 130, 20);
		panel.add(dbName);

		codeLabel = new JLabel(codeLabelText);

		controlItemLabel = new JLabel(controlItemLabelText);

		addSystemB = new JButton(addSystemBText);
		addSystemB.setBounds(10, 50, 130, 20);
		panel.add(addSystemB);

		newCodeB = new JButton(newCodeBText);

		saveCodeB = new JButton(saveCodeBText);

		searchControlB = new JButton(searchControlBText);
		searchControlB.setBounds(175, 100, 150, 20);
		panel.add(searchControlB);

		removeControlB = new JButton(removeControlBText);
		removeControlB.setBounds(175, 140, 150, 20);
		panel.add(removeControlB);

		replaceCodeB = new JButton(replaceCodeBText);
		replaceCodeB.setBounds(175, 180, 150, 20);
		panel.add(replaceCodeB);

		searchControlTF = new JTextField("");
		searchControlTF.setBounds(10, 100, 150, 20);
		searchControlTF.setToolTipText(searchControlTFTooltipText);
		panel.add(searchControlTF);

		removeControlTF = new JTextField("");
		removeControlTF.setBounds(10, 140, 150, 20);
		removeControlTF.setToolTipText(removeControlTFTooltipText);
		panel.add(removeControlTF);

		replaceCodeTF = new JTextField("");
		replaceCodeTF.setBounds(10, 180, 150, 20);
		replaceCodeTF.setToolTipText(replaceCodeTFTooltipText);
		panel.add(replaceCodeTF);

		codeTF = new JTextField(defaultCodeValue);
		codeTF.setToolTipText("<html>" + codeTFTooltipText + "</html>");

		controlTF = new JTextField(controlDefaultValue);
		controlTF.setToolTipText(controlTFTooltipText);

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.pack();
	}

	public void show() {
		frame.revalidate();
		frame.setVisible(true);
	}

	public void resizeFrame() {
		panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		menu.setBounds(0, 0, panel.getWidth(), 20);
		panel.revalidate();
	}

	public void resizeErrorFrame() {
		bSPanel.setBounds(0, 0, bSFrame.getWidth(), bSFrame.getHeight());
		bSPanel.revalidate();
	}

	public void resizeFrameCodeSettings() {
		panelSettings.setBounds(0, 0, frameCodeSettings.getWidth(),
				frameCodeSettings.getHeight());
		panelSettings.revalidate();
	}

	public void resizeFrameGeneralSettings() {
		panelSettings.setBounds(0, 0, frameGeneralSettings.getWidth(),
				frameGeneralSettings.getHeight());
		panelSettings.revalidate();
	}

	public void resizeFrameAddControl() {
		panelAddControl.setBounds(10, 10, frameAddControl.getWidth() - 35,
				frameAddControl.getHeight() - 60);
		panelAddControl.revalidate();
	}

	public void ShowAllCodes(List<SingleCodeDTO> systems) {
		JFrame frameShowAll = new JFrame(frameAddControlText);
		frameShowAll.setMinimumSize(new Dimension(500, 300));

		JPanel panelShowAll = new JPanel();
		panelShowAll.setBounds(10, 10, frameShowAll.getWidth() - 35,
				frameShowAll.getHeight() - 60);
		panelShowAll.setBackground(new Color(250, 250, 250));
		panelShowAll.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		panelShowAll.setLayout(null);
		frameShowAll.getContentPane().add(panelShowAll);

		String header[] = { "System", "Code", "Placement" };

		Object[][] codesO = new Object[systems.size()][3];

		for (int i = 0; i < systems.size(); i++) {
			codesO[i][0] = systems.get(i).getSystemNummer();
			codesO[i][1] = systems.get(i).getPinKode();
			if (!systems.get(i).getPosition().equals("")) {
				codesO[i][2] = systems.get(i).getPosition();
			}
		}

		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(20, 20, panelShowAll.getWidth() - 20,
				panelShowAll.getHeight() - 20);
		panelShowAll.add(tablePanel);

		JTable codes = new JTable(codesO, header);
		tablePanel.add(codes.getTableHeader(), BorderLayout.NORTH);
		codes.setFillsViewportHeight(true);
		codes.setVisible(true);
		codes.getTableHeader().setVisible(true);
		tablePanel.add(codes);
		codes.revalidate();
		tablePanel.revalidate();
		codes.getColumnModel().getColumn(0)
				.setPreferredWidth((tablePanel.getWidth() - 10) / 3);
		codes.getColumnModel().getColumn(1)
				.setPreferredWidth((tablePanel.getWidth() - 10) / 3);

		panelShowAll.revalidate();
		frameShowAll.revalidate();
		frameShowAll.validate();

		frameShowAll.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameShowAll.getContentPane().setLayout(null);
		frameShowAll.setLocationRelativeTo(null);
		frameShowAll.setVisible(true);
		frameShowAll.pack();
	}

	public void addControl() {
		frameAddControl = new JFrame(frameAddControlText);
		frameAddControl.setMinimumSize(new Dimension(500, 300));

		panelAddControl = new JPanel();
		panelAddControl.setBounds(10, 10, frameAddControl.getWidth() - 35,
				frameAddControl.getHeight() - 60);
		panelAddControl.setBackground(new Color(250, 250, 250));
		panelAddControl.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		panelAddControl.setLayout(null);
		frameAddControl.getContentPane().add(panelAddControl);

		codeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		codeLabel.setBounds(10, 20, 80, 20);

		codeTF.setText("00000");
		codeTF.setBounds(10, 60, 80, 20);

		controlItemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		controlItemLabel.setBounds(110, 20, 130, 20);

		controlTF.setText("");
		controlTF.setBounds(110, 60, 130, 20);

		newCodeB.setBounds(10, 100, 130, 20);

		saveCodeB.setBounds(10, 140, 100, 20);

		cancelB = new JButton(cancelBText);
		cancelB.setBounds(150, 140, 100, 20);

		panelAddControl.add(codeLabel);
		panelAddControl.add(controlItemLabel);
		panelAddControl.add(codeTF);
		panelAddControl.add(controlTF);
		panelAddControl.add(newCodeB);
		panelAddControl.add(saveCodeB);
		panelAddControl.add(cancelB);

		frameAddControl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameAddControl.getContentPane().setLayout(null);
		frameAddControl.setLocationRelativeTo(null);
		frameAddControl.setVisible(true);
		frameAddControl.pack();
	}

	public void codeSettings() {
		frameCodeSettings = new JFrame(frameCodeSettingsText);
		frameCodeSettings.setMinimumSize(new Dimension(585, 360));
		panelSettings = new JPanel();
		panelSettings.setBackground(new Color(250, 250, 250));
		panelSettings.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		panelSettings.setBounds(0, 0, frameCodeSettings.getWidth(),
				frameCodeSettings.getHeight());
		frameCodeSettings.getContentPane().add(panelSettings);
		panelSettings.setLayout(null);

		codeLengthL = new JLabel(codeLengthLabelText);
		codeLengthL.setBounds(10, 10, 100, 20);

		codeLengthF = new JTextField(codeLengthFText);
		codeLengthF.setBounds(130, 10, 100, 20);
		codeLengthF.setToolTipText("<html>" + codeLengthFTextTooltipText
				+ "</html>");

		codeStrengthL = new JLabel(codeStrengthLText);
		codeStrengthL.setBounds(10, 50, 100, 20);

		codeType = new JComboBox<Object>();
		codeType.setModel(new DefaultComboBoxModel<Object>(codeTypeArrayText));
		codeType.setBounds(130, 50, 200, 20);
		codeType.setToolTipText("<html>" + codeTypeTooltipText + "</html>");

		panelSettings.add(codeLengthL);
		panelSettings.add(codeLengthF);
		panelSettings.add(codeStrengthL);
		panelSettings.add(codeType);

		saveSettingsB = new JButton(saveSettingsBText);
		saveSettingsB.setBounds(15, 190, 145, 20);
		panelSettings.add(saveSettingsB);

		cancelSettingB = new JButton(cancelBText);
		cancelSettingB.setBounds(180, 190, 145, 20);
		panelSettings.add(cancelSettingB);

		defaultSettingB = new JButton(defaultSettingBText);
		defaultSettingB.setBounds(345, 190, 145, 20);
		panelSettings.add(defaultSettingB);

		frameCodeSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameCodeSettings.getContentPane().setLayout(null);
		frameCodeSettings.setLocationRelativeTo(null);
		frameCodeSettings.setVisible(true);
		frameCodeSettings.pack();
	}

	public void errorWindow(String errorTitle, String errorMessage) {
		bSFrame = new JFrame(errorTitle);
		bSFrame.setMinimumSize(new Dimension(385, 110));
		JPanel bSPanel = new JPanel();
		bSPanel.setBackground(new Color(250, 250, 250));
		bSPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		bSPanel.setBounds(10, 10, 350, 50);
		bSFrame.add(bSPanel);
		JLabel bSLabel = new JLabel(errorMessage);
		bSLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bSLabel.setBounds(bSFrame.getWidth() / 2, bSFrame.getHeight() / 2, 200,
				20);
		bSPanel.add(bSLabel);
		bSFrame.getContentPane().setLayout(null);
		bSFrame.setLocationRelativeTo(null);
		bSFrame.setVisible(true);
		bSFrame.pack();
	}

	public void noSettings(String errorTitle, String errorMessage) {
		bSFrame = new JFrame(errorTitle);
		bSFrame.setMinimumSize(new Dimension(385, 150));
		JPanel bSPanel = new JPanel();
		bSPanel.setBackground(new Color(250, 250, 250));
		bSPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		bSPanel.setBounds(10, 10, bSFrame.getWidth() - 40,
				bSFrame.getHeight() - 60);
		bSFrame.add(bSPanel);
		JLabel bSLabel = new JLabel("<html>" + errorMessage + "</html>");
		bSLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bSLabel.setBounds(bSFrame.getWidth() / 2, bSFrame.getHeight() / 2, 200,
				90);
		bSPanel.add(bSLabel);
		bSFrame.getContentPane().setLayout(null);
		bSFrame.setLocationRelativeTo(null);
		bSFrame.setVisible(true);
		bSFrame.pack();
	}

	public void errorExWindow(String errorEX, String errorLoc) {
		bSFrame = new JFrame("Error: An exception has occured.");
		bSFrame.setMinimumSize(new Dimension(500, 360));
		bSPanel = new JPanel();
		bSPanel.setBackground(new Color(245, 245, 245));
		bSPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		bSPanel.setBounds(10, 10, bSFrame.getWidth() - 40,
				bSFrame.getHeight() - 60);
		bSFrame.add(bSPanel);
		errorL = new JLabel(errorLText.replace("!errorEX!", errorEX));
		errorL.setHorizontalAlignment(SwingConstants.CENTER);
		errorL.setBounds(10, 10, 400, 40);

		emailErrL = new JLabel(emailErrLText);
		emailErrL.setBounds(10, 70, 600, 20);

		emailErrF = new JTextField(defaultEmailErrFText);
		emailErrF.setBounds(80, 70, 250, 20);
		emailErrF.setToolTipText(emailErrFTooltipText);

		Border bord = BorderFactory.createLineBorder(Color.BLACK, 1);

		emailErrDesL = new JLabel(emailErrDesLText);
		emailErrDesL.setBounds(10, 110, 300, 20);

		emailErrDesF = new JTextArea(10, 30);
		emailErrDesF.setBounds(10, 130, 300, 100);
		emailErrDesF.setBackground(Color.WHITE);

		emailErrDesF.setBorder(bord);

		emailErrB = new JButton(emailErrBText);
		emailErrB.setBounds(10, 270, 175, 20);

		emailErrC = new JButton(emailErrCText);
		emailErrC.setBounds(195, 270, 100, 20);

		bSPanel.add(errorL);
		bSPanel.add(emailErrL);
		bSPanel.add(emailErrF);
		bSPanel.add(emailErrDesL);
		bSPanel.add(emailErrDesF);
		bSPanel.add(emailErrB);
		bSPanel.add(emailErrC);
		bSPanel.setLayout(null);
		bSFrame.getContentPane().setLayout(null);
		bSFrame.setLocationRelativeTo(null);
		bSFrame.setVisible(true);
		bSFrame.pack();
	}

	public void addedWindow(String title, String message) {
		JFrame addFrame = new JFrame(title);
		addFrame.setMinimumSize(new Dimension(385, 110));
		JPanel addPanel = new JPanel();
		addPanel.setBackground(new Color(250, 250, 250));
		addPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		addPanel.setBounds(10, 10, 350, 50);
		addFrame.add(addPanel);
		JLabel addLabel = new JLabel(message);
		addLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addLabel.setBounds(addFrame.getWidth() / 2, addFrame.getHeight() / 2,
				200, 20);
		addPanel.add(addLabel);
		addFrame.getContentPane().setLayout(null);
		addFrame.setLocationRelativeTo(null);
		addFrame.setVisible(true);
		addFrame.pack();
	}

	public void confirmFrame(String confirmTitle, String confirmMessage) {
		confirmRemovalFrame = new JFrame(confirmTitle);
		confirmRemovalFrame.setMinimumSize(new Dimension(385, 150));
		JPanel bekæftFjernelsePanel = new JPanel();
		bekæftFjernelsePanel.setBackground(new Color(250, 250, 250));
		bekæftFjernelsePanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		bekæftFjernelsePanel.setBounds(10, 10,
				confirmRemovalFrame.getWidth() - 35,
				confirmRemovalFrame.getHeight() - 40);
		confirmRemovalFrame.add(bekæftFjernelsePanel);
		JLabel bFLabel = new JLabel(confirmMessage);
		bFLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bFLabel.setBounds(confirmRemovalFrame.getWidth() / 2,
				(confirmRemovalFrame.getHeight() - 40) / 2, 200, 20);
		bekæftFjernelsePanel.add(bFLabel);

		confirmRemovalB = new JButton(confirmRemovalBText);
		confirmRemovalB.setBounds((confirmRemovalFrame.getWidth() - 40) / 2,
				200, 60, 20);
		bekæftFjernelsePanel.add(confirmRemovalB);

		noButton = new JButton(noButtonText);
		noButton.setBounds((confirmRemovalFrame.getWidth() + 40) / 2, 200, 60,
				20);
		bekæftFjernelsePanel.add(noButton);

		confirmRemovalFrame.getContentPane().setLayout(null);
		confirmRemovalFrame.setLocationRelativeTo(null);
		confirmRemovalFrame.setVisible(true);
		confirmRemovalFrame.pack();
	}

	public void dbError() {
		confirmRemovalFrame = new JFrame(dbNotFoundText);
		confirmRemovalFrame.setMinimumSize(new Dimension(385, 150));
		JPanel bekæftFjernelsePanel = new JPanel();
		bekæftFjernelsePanel.setBackground(new Color(250, 250, 250));
		bekæftFjernelsePanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		bekæftFjernelsePanel.setBounds(10, 10,
				confirmRemovalFrame.getWidth() - 35,
				confirmRemovalFrame.getHeight() - 40);
		confirmRemovalFrame.add(bekæftFjernelsePanel);
		dbNotFoundL = new JLabel(dbNotFoundLText);
		dbNotFoundL.setHorizontalAlignment(SwingConstants.CENTER);
		dbNotFoundL.setBounds(confirmRemovalFrame.getWidth() / 2,
				(confirmRemovalFrame.getHeight() - 40) / 2, 200, 20);
		bekæftFjernelsePanel.add(dbNotFoundL);

		confirmCreateB = new JButton(confirmCreateBText);
		confirmCreateB.setBounds((confirmRemovalFrame.getWidth() - 40) / 2,
				200, 60, 20);
		bekæftFjernelsePanel.add(confirmCreateB);

		dbErrorBCreateBackup = new JButton(dbErrorBCreateBackupText);
		dbErrorBCreateBackup.setBounds(
				(confirmRemovalFrame.getWidth() + 40) / 2, 200, 60, 20);
		bekæftFjernelsePanel.add(dbErrorBCreateBackup);

		confirmRemovalFrame.getContentPane().setLayout(null);
		confirmRemovalFrame.setLocationRelativeTo(null);
		confirmRemovalFrame.setVisible(true);
		confirmRemovalFrame.pack();
	}

	public void exceptionFrame(String confirmTitle, String confirmMessage) {
		confirmRemovalFrame = new JFrame(confirmTitle);
		confirmRemovalFrame.setMinimumSize(new Dimension(1500, 500));
		JPanel bekæftFjernelsePanel = new JPanel();
		bekæftFjernelsePanel.setBackground(new Color(250, 250, 250));
		bekæftFjernelsePanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		bekæftFjernelsePanel.setBounds(10, 10,
				confirmRemovalFrame.getWidth() - 35,
				confirmRemovalFrame.getHeight() - 40);
		confirmRemovalFrame.add(bekæftFjernelsePanel);
		JLabel bFLabel = new JLabel(confirmMessage);
		bFLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bFLabel.setBounds(confirmRemovalFrame.getWidth() / 2,
				(confirmRemovalFrame.getHeight() - 40) / 2, 200, 20);
		bekæftFjernelsePanel.add(bFLabel);

		confirmRemovalB = new JButton(confirmCreateBText);
		confirmRemovalB.setBounds((confirmRemovalFrame.getWidth() - 40) / 2,
				200, 60, 20);
		bekæftFjernelsePanel.add(confirmRemovalB);

		noButton = new JButton(noButtonText);
		noButton.setBounds((confirmRemovalFrame.getWidth() + 40) / 2, 200, 60,
				20);
		bekæftFjernelsePanel.add(noButton);

		confirmRemovalFrame.getContentPane().setLayout(null);
		confirmRemovalFrame.setLocationRelativeTo(null);
		confirmRemovalFrame.setVisible(true);
		confirmRemovalFrame.pack();
	}

	public void settingsGUI(String defaultLoc, String fileLoc,
			String selectedLanguage) {
		frameGeneralSettings = new JFrame(frameGeneralSettingsText);
		frameGeneralSettings.setMinimumSize(new Dimension(585, 360));
		panelSettings = new JPanel();
		panelSettings.setBackground(new Color(250, 250, 250));
		panelSettings.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		panelSettings.setBounds(0, 0, frameGeneralSettings.getWidth(),
				frameGeneralSettings.getHeight());
		panelSettings.setLayout(null);

		labelDefault = new JLabel(labelDefaultText);
		labelDefault.setBounds(15, 30, 100, 20);
		panelSettings.add(labelDefault);

		defaultLocTF = new JTextField(defaultLocTFText);
		defaultLocTF.setBounds(130, 30, 250, 20);
		panelSettings.add(defaultLocTF);

		filePlaceL = new JLabel(filePlaceLText);
		filePlaceL.setHorizontalAlignment(SwingConstants.CENTER);
		filePlaceL.setBounds(15, 70, 100, 20);
		panelSettings.add(filePlaceL);

		fileLocTF = new JTextField(fileLocTFDefaultValue);
		fileLocTF.setBounds(130, 70, 250, 20);
		fileLocTF.setToolTipText(fileLocTFTooltipText);
		panelSettings.add(fileLocTF);

		languageLabel = new JLabel(languageText);
		languageLabel.setBounds(15, 110, 100, 20);
		panelSettings.add(languageLabel);

		language = new JComboBox<Object>();
		language.setModel(new DefaultComboBoxModel<Object>(languages));
		for (int i = 0; i < languages.length; i++) {
			if (languages[i].toString().equals(selectedLanguage)) {
				language.setSelectedIndex(i);
				;
				break;
			}
		}
		language.setBounds(130, 110, 250, 20);
		panelSettings.add(language);

		defaultLocB = new JButton(defaultLocBText);
		defaultLocB.setBounds(400, 30, 100, 20);
		panelSettings.add(defaultLocB);

		getFileB = new JButton(getFileBText);
		getFileB.setBounds(400, 70, 100, 20);
		panelSettings.add(getFileB);

		saveSettingsB = new JButton(saveSettingsBText);
		saveSettingsB.setBounds(15, 190, 145, 20);
		panelSettings.add(saveSettingsB);

		cancelSettingB = new JButton(cancelSettingBText);
		cancelSettingB.setBounds(180, 190, 145, 20);
		panelSettings.add(cancelSettingB);

		defaultSettingB = new JButton(defaultSettingBText);
		defaultSettingB.setBounds(345, 190, 145, 20);
		panelSettings.add(defaultSettingB);

		if (defaultLoc == null) {
			fileLocTF.setText("");
			defaultLocTF.setText("");
		} else {
			fileLocTF.setText(fileLoc);
			defaultLocTF.setText(defaultLoc + "");
		}

		frameGeneralSettings.getContentPane().add(panelSettings);
		frameGeneralSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameGeneralSettings.getContentPane().setLayout(null);
		frameGeneralSettings.setLocationRelativeTo(null);
		frameGeneralSettings.setVisible(true);
		frameGeneralSettings.pack();
	}

	public void printSettingGUI(float fCodeLocX, float fCodeLocY,
			float fSystemLocX, float fSystemLocY) {
		frameCodeSettings = new JFrame(frameCodeSettingsText);
		frameCodeSettings.setMinimumSize(new Dimension(585, 360));
		systemPanel = new JPanel();
		systemPanel.setBackground(new Color(250, 250, 250));
		systemPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		systemPanel.setBounds(0, 0, frameCodeSettings.getWidth(),
				frameCodeSettings.getHeight());
		systemPanel.setLayout(null);

		printLSystem = new JLabel(printLTextSystem);
		printLSystem.setBounds(15, 30, 120, 20);
		systemPanel.add(printLSystem);

		JLabel labelSX = new JLabel("X: ");
		labelSX.setBounds(150, 30, 20, 20);
		systemPanel.add(labelSX);

		systemLocX = new JTextField(fSystemLocX + "");
		systemLocX.setBounds(180, 30, 100, 20);
		systemLocX.setToolTipText(writeLocXTooltipText);
		systemPanel.add(systemLocX);

		JLabel labelSY = new JLabel("Y: ");
		labelSY.setBounds(150, 70, 20, 20);
		systemPanel.add(labelSY);

		systemLocY = new JTextField(fSystemLocY + "");
		systemLocY.setBounds(180, 70, 100, 20);
		systemLocY.setToolTipText(writeLocYTooltipText);
		systemPanel.add(systemLocY);

		printLCode = new JLabel(printLTextCode);
		printLCode.setBounds(15, 110, 120, 20);
		systemPanel.add(printLCode);

		JLabel labelX = new JLabel("X:");
		labelX.setBounds(150, 110, 20, 20);
		systemPanel.add(labelX);

		codeLocX = new JTextField(fCodeLocX + "");
		codeLocX.setBounds(180, 110, 100, 20);
		codeLocX.setToolTipText(writeLocXTooltipText);
		systemPanel.add(codeLocX);

		JLabel labelY = new JLabel("Y:");
		labelY.setBounds(150, 150, 20, 20);
		systemPanel.add(labelY);

		codeLocY = new JTextField(fCodeLocY + "");
		codeLocY.setBounds(180, 150, 100, 20);
		codeLocY.setToolTipText(writeLocYTooltipText);
		systemPanel.add(codeLocY);

		pdfPreviewB = new JButton(pdfPreviewBText);
		pdfPreviewB.setBounds(345, 150, 145, 20);
		systemPanel.add(pdfPreviewB);

		saveSettingsB = new JButton(saveSettingsBText);
		saveSettingsB.setBounds(15, 190, 145, 20);
		systemPanel.add(saveSettingsB);

		cancelSettingB = new JButton(cancelSettingBText);
		cancelSettingB.setBounds(180, 190, 145, 20);
		systemPanel.add(cancelSettingB);

		defaultSettingB = new JButton(defaultSettingBText);
		defaultSettingB.setBounds(345, 190, 145, 20);
		systemPanel.add(defaultSettingB);

		frameCodeSettings.getContentPane().add(systemPanel);
		frameCodeSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameCodeSettings.getContentPane().setLayout(null);
		frameCodeSettings.setLocationRelativeTo(null);
		frameCodeSettings.setVisible(true);
		frameCodeSettings.pack();
	}

	public JFrame getConfirmRemovalFrame() {
		return confirmRemovalFrame;
	}

	public void setConfirmRemovalFrame(JFrame confirmRemovalFrame) {
		this.confirmRemovalFrame = confirmRemovalFrame;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JFrame getFrameCodeSettings() {
		return frameCodeSettings;
	}

	public void setFrameCodeSettings(JFrame frameCodeSettings) {
		this.frameCodeSettings = frameCodeSettings;
	}

	public JFrame getFrameAddControl() {
		return frameAddControl;
	}

	public void setFrameAddControl(JFrame frameAddControl) {
		this.frameAddControl = frameAddControl;
	}

	public JFrame getbSFrame() {
		return bSFrame;
	}

	public void setbSFrame(JFrame bSFrame) {
		this.bSFrame = bSFrame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JPanel getPanelSettings() {
		return panelSettings;
	}

	public void setPanelSettings(JPanel panelSettings) {
		this.panelSettings = panelSettings;
	}

	public JPanel getPanelAddControl() {
		return panelAddControl;
	}

	public void setPanelAddControl(JPanel panelAddControl) {
		this.panelAddControl = panelAddControl;
	}

	public JComboBox<Object> getCodeType() {
		return codeType;
	}

	public void setCodeType(JComboBox<Object> codeType) {
		this.codeType = codeType;
	}

	public JButton getNewCodeB() {
		return newCodeB;
	}

	public void setNewCodeB(JButton newCodeB) {
		this.newCodeB = newCodeB;
	}

	public JButton getSaveCodeB() {
		return saveCodeB;
	}

	public void setSaveCodeB(JButton saveCodeB) {
		this.saveCodeB = saveCodeB;
	}

	public JButton getSearchControlB() {
		return searchControlB;
	}

	public void setSearchControlB(JButton searchControlB) {
		this.searchControlB = searchControlB;
	}

	public JButton getRemoveControlB() {
		return removeControlB;
	}

	public void setRemoveControlB(JButton removeControlB) {
		this.removeControlB = removeControlB;
	}

	public JButton getReplaceCodeB() {
		return replaceCodeB;
	}

	public void setReplaceCodeB(JButton replaceCodeB) {
		this.replaceCodeB = replaceCodeB;
	}

	public JButton getConfirmRemovalB() {
		return confirmRemovalB;
	}

	public void setConfirmRemovalB(JButton confirmRemovalB) {
		this.confirmRemovalB = confirmRemovalB;
	}

	public JButton getNoButton() {
		return noButton;
	}

	public void setNoButton(JButton noButton) {
		this.noButton = noButton;
	}

	public JButton getPdfPreviewB() {
		return pdfPreviewB;
	}

	public void setPdfPreviewB(JButton pdfPreviewB) {
		this.pdfPreviewB = pdfPreviewB;
	}

	public JButton getGetFileB() {
		return getFileB;
	}

	public void setGetFileB(JButton getFileB) {
		this.getFileB = getFileB;
	}

	public JButton getSaveSettingsB() {
		return saveSettingsB;
	}

	public void setSaveSettingsB(JButton saveSettingsB) {
		this.saveSettingsB = saveSettingsB;
	}

	public JButton getCancelSettingB() {
		return cancelSettingB;
	}

	public void setCancelSettingB(JButton cancelSettingB) {
		this.cancelSettingB = cancelSettingB;
	}

	public JButton getDefaultSettingB() {
		return defaultSettingB;
	}

	public void setDefaultSettingB(JButton defaultSettingB) {
		this.defaultSettingB = defaultSettingB;
	}

	public JButton getDefaultLocB() {
		return defaultLocB;
	}

	public void setDefaultLocB(JButton defaultLocB) {
		this.defaultLocB = defaultLocB;
	}

	public JButton getAddSystemB() {
		return addSystemB;
	}

	public void setAddSystemB(JButton addSystemB) {
		this.addSystemB = addSystemB;
	}

	public JButton getCancelB() {
		return cancelB;
	}

	public void setCancelB(JButton cancelB) {
		this.cancelB = cancelB;
	}

	public JButton getEmailErrB() {
		return emailErrB;
	}

	public void setEmailErrB(JButton emailErrB) {
		this.emailErrB = emailErrB;
	}

	public JButton getEmailErrC() {
		return emailErrC;
	}

	public void setEmailErrC(JButton emailErrC) {
		this.emailErrC = emailErrC;
	}

	public JTextField getCodeTF() {
		return codeTF;
	}

	public void setCodeTF(JTextField codeTF) {
		this.codeTF = codeTF;
	}

	public JTextField getControlTF() {
		return controlTF;
	}

	public void setControlTF(JTextField controlTF) {
		this.controlTF = controlTF;
	}

	public JTextField getSearchControlTF() {
		return searchControlTF;
	}

	public void setSearchControlTF(JTextField searchControlTF) {
		this.searchControlTF = searchControlTF;
	}

	public JTextField getRemoveControlTF() {
		return removeControlTF;
	}

	public void setRemoveControlTF(JTextField removeControlTF) {
		this.removeControlTF = removeControlTF;
	}

	public JTextField getReplaceCodeTF() {
		return replaceCodeTF;
	}

	public void setReplaceCodeTF(JTextField replaceCodeTF) {
		this.replaceCodeTF = replaceCodeTF;
	}

	public JTextField getFileLocTF() {
		return fileLocTF;
	}

	public void setFileLocTF(JTextField fileLocTF) {
		this.fileLocTF = fileLocTF;
	}

	public JTextField getDefaultLocTF() {
		return defaultLocTF;
	}

	public void setDefaultLocTF(JTextField defaultLocTF) {
		this.defaultLocTF = defaultLocTF;
	}

	public JTextField getCodeLengthF() {
		return codeLengthF;
	}

	public void setCodeLengthF(JTextField codeLengthF) {
		this.codeLengthF = codeLengthF;
	}

	public JTextField getEmailErrF() {
		return emailErrF;
	}

	public void setEmailErrF(JTextField emailErrF) {
		this.emailErrF = emailErrF;
	}

	public JLabel getDbName() {
		return dbName;
	}

	public void setDbName(JLabel dbName) {
		this.dbName = dbName;
	}

	public JLabel getCodeLabel() {
		return codeLabel;
	}

	public void setCodeLabel(JLabel codeLabel) {
		this.codeLabel = codeLabel;
	}

	public JLabel getControlItemLabel() {
		return controlItemLabel;
	}

	public void setControlItemLabel(JLabel controlItemLabel) {
		this.controlItemLabel = controlItemLabel;
	}

	public JLabel getEmailErrL() {
		return emailErrL;
	}

	public void setEmailErrL(JLabel emailErrL) {
		this.emailErrL = emailErrL;
	}

	public JLabel getEmailErrDesL() {
		return emailErrDesL;
	}

	public void setEmailErrDesL(JLabel emailErrDesL) {
		this.emailErrDesL = emailErrDesL;
	}

	public JTextArea getEmailErrDesF() {
		return emailErrDesF;
	}

	public void setEmailErrDesF(JTextArea emailErrDesF) {
		this.emailErrDesF = emailErrDesF;
	}

	public JMenuBar getMenu() {
		return menu;
	}

	public void setMenu(JMenuBar menu) {
		this.menu = menu;
	}

	public JFileChooser getChooser() {
		return chooser;
	}

	public void setChooser(JFileChooser chooser) {
		this.chooser = chooser;
	}

	public JMenu getmFile() {
		return mFile;
	}

	public void setmFile(JMenu mFile) {
		this.mFile = mFile;
	}

	public JMenu getmHelp() {
		return mHelp;
	}

	public void setmHelp(JMenu mHelp) {
		this.mHelp = mHelp;
	}

	public JMenu getmSettings() {
		return mSettings;
	}

	public void setmSettings(JMenu mSettings) {
		this.mSettings = mSettings;
	}

	public JMenuItem getmExit() {
		return mExit;
	}

	public void setmExit(JMenuItem mExit) {
		this.mExit = mExit;
	}

	public JMenuItem getmGSettings() {
		return mGSettings;
	}

	public void setmGSettings(JMenuItem mGSettings) {
		this.mGSettings = mGSettings;
	}

	public JMenuItem getmPrintPreview() {
		return mPrintPreview;
	}

	public void setmPrintPreview(JMenuItem mPrintPreview) {
		this.mPrintPreview = mPrintPreview;
	}

	public JMenuItem getmRestoDB() {
		return mRestoDB;
	}

	public void setmRestoDB(JMenuItem mRestoDB) {
		this.mRestoDB = mRestoDB;
	}

	public JMenuItem getmCodeSettings() {
		return mCodeSettings;
	}

	public void setmCodeSettings(JMenuItem mCodeSettings) {
		this.mCodeSettings = mCodeSettings;
	}

	public String getmFileText() {
		return mFileText;
	}

	public void setmFileText(String mFileText) {
		this.mFileText = mFileText;
	}

	public String getmSettingsText() {
		return mSettingsText;
	}

	public void setmSettingsText(String mSettingsText) {
		this.mSettingsText = mSettingsText;
	}

	public String getmCodeSettingsText() {
		return mCodeSettingsText;
	}

	public void setmCodeSettingsText(String mCodeSettingsText) {
		this.mCodeSettingsText = mCodeSettingsText;
	}

	public String getmCodeSettingsToolTipText() {
		return mCodeSettingsToolTipText;
	}

	public void setmCodeSettingsToolTipText(String mCodeSettingsToolTipText) {
		this.mCodeSettingsToolTipText = mCodeSettingsToolTipText;
	}

	public String getmExitTooltipText() {
		return mExitTooltipText;
	}

	public void setmExitTooltipText(String mExitTooltipText) {
		this.mExitTooltipText = mExitTooltipText;
	}

	public String getmGSettingsText() {
		return mGSettingsText;
	}

	public void setmGSettingsText(String mGSettingsText) {
		this.mGSettingsText = mGSettingsText;
	}

	public String getmGSettingsToolTipText() {
		return mGSettingsToolTipText;
	}

	public void setmGSettingsToolTipText(String mGSettingsToolTipText) {
		this.mGSettingsToolTipText = mGSettingsToolTipText;
	}

	public String getmPrintPreviewText() {
		return mPrintPreviewText;
	}

	public void setmPrintPreviewText(String mPrintPreviewText) {
		this.mPrintPreviewText = mPrintPreviewText;
	}

	public String getmPrintPreviewTooltipText() {
		return mPrintPreviewTooltipText;
	}

	public void setmPrintPreviewTooltipText(String mPrintPreviewTooltipText) {
		this.mPrintPreviewTooltipText = mPrintPreviewTooltipText;
	}

	public String getmRestoDBText() {
		return mRestoDBText;
	}

	public void setmRestoDBText(String mRestoDBText) {
		this.mRestoDBText = mRestoDBText;
	}

	public String getmRestoDBTooltipText() {
		return mRestoDBTooltipText;
	}

	public void setmRestoDBTooltipText(String mRestoDBTooltipText) {
		this.mRestoDBTooltipText = mRestoDBTooltipText;
	}

	public String getDbNameText() {
		return dbNameText;
	}

	public void setDbNameText(String dbNameText) {
		this.dbNameText = dbNameText;
	}

	public String getCodeLabelText() {
		return codeLabelText;
	}

	public void setCodeLabelText(String codeLabelText) {
		this.codeLabelText = codeLabelText;
	}

	public String getControlItemLabelText() {
		return controlItemLabelText;
	}

	public void setControlItemLabelText(String controlItemLabelText) {
		this.controlItemLabelText = controlItemLabelText;
	}

	public String getAddSystemBText() {
		return addSystemBText;
	}

	public void setAddSystemBText(String addSystemBText) {
		this.addSystemBText = addSystemBText;
	}

	public String getNewCodeBText() {
		return newCodeBText;
	}

	public void setNewCodeBText(String newCodeBText) {
		this.newCodeBText = newCodeBText;
	}

	public String getSaveCodeBText() {
		return saveCodeBText;
	}

	public void setSaveCodeBText(String saveCodeBText) {
		this.saveCodeBText = saveCodeBText;
	}

	public String getSearchControlBText() {
		return searchControlBText;
	}

	public void setSearchControlBText(String searchControlBText) {
		this.searchControlBText = searchControlBText;
	}

	public String getRemoveControlBText() {
		return removeControlBText;
	}

	public void setRemoveControlBText(String removeControlBText) {
		this.removeControlBText = removeControlBText;
	}

	public String getReplaceCodeBText() {
		return replaceCodeBText;
	}

	public void setReplaceCodeBText(String replaceCodeBText) {
		this.replaceCodeBText = replaceCodeBText;
	}

	public String getSearchControlTFTooltipText() {
		return searchControlTFTooltipText;
	}

	public void setSearchControlTFTooltipText(String searchControlTFTooltipText) {
		this.searchControlTFTooltipText = searchControlTFTooltipText;
	}

	public String getRemoveControlTFTooltipText() {
		return removeControlTFTooltipText;
	}

	public void setRemoveControlTFTooltipText(String removeControlTFTooltipText) {
		this.removeControlTFTooltipText = removeControlTFTooltipText;
	}

	public String getReplaceCodeTFTooltipText() {
		return replaceCodeTFTooltipText;
	}

	public void setReplaceCodeTFTooltipText(String replaceCodeTFTooltipText) {
		this.replaceCodeTFTooltipText = replaceCodeTFTooltipText;
	}

	public String getDefaultCodeValue() {
		return defaultCodeValue;
	}

	public void setDefaultCodeValue(String defaultCodeValue) {
		this.defaultCodeValue = defaultCodeValue;
	}

	public String getCodeTFTooltipText() {
		return codeTFTooltipText;
	}

	public void setCodeTFTooltipText(String codeTFTooltipText) {
		this.codeTFTooltipText = codeTFTooltipText;
	}

	public String getControlDefaultValue() {
		return controlDefaultValue;
	}

	public void setControlDefaultValue(String controlDefaultValue) {
		this.controlDefaultValue = controlDefaultValue;
	}

	public String getControlTFTooltipText() {
		return controlTFTooltipText;
	}

	public void setControlTFTooltipText(String controlTFTooltipText) {
		this.controlTFTooltipText = controlTFTooltipText;
	}

	public String getFrameAddControlText() {
		return frameAddControlText;
	}

	public void setFrameAddControlText(String frameAddControlText) {
		this.frameAddControlText = frameAddControlText;
	}

	public String getCancelBText() {
		return cancelBText;
	}

	public void setCancelBText(String cancelBText) {
		this.cancelBText = cancelBText;
	}

	public String getFrameCodeSettingsText() {
		return frameCodeSettingsText;
	}

	public void setFrameCodeSettingsText(String frameCodeSettingsText) {
		this.frameCodeSettingsText = frameCodeSettingsText;
	}

	public JLabel getCodeLengthL() {
		return codeLengthL;
	}

	public void setCodeLengthL(JLabel codeLengthL) {
		this.codeLengthL = codeLengthL;
	}

	public String getCodeLengthLabelText() {
		return codeLengthLabelText;
	}

	public void setCodeLengthLabelText(String codeLengthLabelText) {
		this.codeLengthLabelText = codeLengthLabelText;
	}

	public String getCodeLengthFText() {
		return codeLengthFText;
	}

	public void setCodeLengthFText(String codeLengthFText) {
		this.codeLengthFText = codeLengthFText;
	}

	public String getCodeLengthFTextTooltipText() {
		return codeLengthFTextTooltipText;
	}

	public void setCodeLengthFTextTooltipText(String codeLengthFTextTooltipText) {
		this.codeLengthFTextTooltipText = codeLengthFTextTooltipText;
	}

	public JLabel getCodeStrengthL() {
		return codeStrengthL;
	}

	public void setCodeStrengthL(JLabel codeStrengthL) {
		this.codeStrengthL = codeStrengthL;
	}

	public String getCodeStrengthLText() {
		return codeStrengthLText;
	}

	public void setCodeStrengthLText(String codeStrengthLText) {
		this.codeStrengthLText = codeStrengthLText;
	}

	public String[] getCodeTypeArrayText() {
		return codeTypeArrayText;
	}

	public void setCodeTypeArrayText(String[] codeTypeArrayText) {
		this.codeTypeArrayText = codeTypeArrayText;
	}

	public String getCodeTypeTooltipText() {
		return codeTypeTooltipText;
	}

	public void setCodeTypeTooltipText(String codeTypeTooltipText) {
		this.codeTypeTooltipText = codeTypeTooltipText;
	}

	public String getSaveSettingsBText() {
		return saveSettingsBText;
	}

	public void setSaveSettingsBText(String saveSettingsBText) {
		this.saveSettingsBText = saveSettingsBText;
	}

	public String getDefaultSettingBText() {
		return defaultSettingBText;
	}

	public void setDefaultSettingBText(String defaultSettingBText) {
		this.defaultSettingBText = defaultSettingBText;
	}

	public JLabel getErrorL() {
		return errorL;
	}

	public void setErrorL(JLabel errorL) {
		this.errorL = errorL;
	}

	public String getErrorLText() {
		return errorLText;
	}

	public void setErrorLText(String errorLText) {
		this.errorLText = errorLText;
	}

	public String getEmailErrLText() {
		return emailErrLText;
	}

	public void setEmailErrLText(String emailErrLText) {
		this.emailErrLText = emailErrLText;
	}

	public String getDefaultEmailErrFText() {
		return defaultEmailErrFText;
	}

	public void setDefaultEmailErrFText(String defaultEmailErrFText) {
		this.defaultEmailErrFText = defaultEmailErrFText;
	}

	public String getEmailErrFTooltipText() {
		return emailErrFTooltipText;
	}

	public void setEmailErrFTooltipText(String emailErrFTooltipText) {
		this.emailErrFTooltipText = emailErrFTooltipText;
	}

	public String getEmailErrDesLText() {
		return emailErrDesLText;
	}

	public void setEmailErrDesLText(String emailErrDesLText) {
		this.emailErrDesLText = emailErrDesLText;
	}

	public String getEmailErrBText() {
		return emailErrBText;
	}

	public void setEmailErrBText(String emailErrBText) {
		this.emailErrBText = emailErrBText;
	}

	public String getEmailErrCText() {
		return emailErrCText;
	}

	public void setEmailErrCText(String emailErrCText) {
		this.emailErrCText = emailErrCText;
	}

	public String getConfirmRemovalBText() {
		return confirmRemovalBText;
	}

	public void setConfirmRemovalBText(String confirmRemovalBText) {
		this.confirmRemovalBText = confirmRemovalBText;
	}

	public String getNoButtonText() {
		return noButtonText;
	}

	public void setNoButtonText(String noButtonText) {
		this.noButtonText = noButtonText;
	}

	public String getDbNotFoundText() {
		return dbNotFoundText;
	}

	public void setDbNotFoundText(String dbNotFoundText) {
		this.dbNotFoundText = dbNotFoundText;
	}

	public JLabel getDbNotFoundL() {
		return dbNotFoundL;
	}

	public void setDbNotFoundL(JLabel dbNotFoundL) {
		this.dbNotFoundL = dbNotFoundL;
	}

	public String getDbNotFoundLText() {
		return dbNotFoundLText;
	}

	public void setDbNotFoundLText(String dbNotFoundLText) {
		this.dbNotFoundLText = dbNotFoundLText;
	}

	public JButton getConfirmCreateB() {
		return confirmCreateB;
	}

	public void setConfirmCreateB(JButton confirmCreateB) {
		this.confirmCreateB = confirmCreateB;
	}

	public String getConfirmCreateBText() {
		return confirmCreateBText;
	}

	public void setConfirmCreateBText(String confirmCreateBText) {
		this.confirmCreateBText = confirmCreateBText;
	}

	public JButton getDbErrorBCreateBackup() {
		return dbErrorBCreateBackup;
	}

	public void setDbErrorBCreateBackup(JButton dbErrorBCreateBackup) {
		this.dbErrorBCreateBackup = dbErrorBCreateBackup;
	}

	public String getDbErrorBCreateBackupText() {
		return dbErrorBCreateBackupText;
	}

	public void setDbErrorBCreateBackupText(String dbErrorBCreateBackupText) {
		this.dbErrorBCreateBackupText = dbErrorBCreateBackupText;
	}

	public JLabel getLabelDefault() {
		return labelDefault;
	}

	public void setLabelDefault(JLabel labelDefault) {
		this.labelDefault = labelDefault;
	}

	public String getLabelDefaultText() {
		return labelDefaultText;
	}

	public void setLabelDefaultText(String labelDefaultText) {
		this.labelDefaultText = labelDefaultText;
	}

	public String getDefaultLocTFText() {
		return defaultLocTFText;
	}

	public void setDefaultLocTFText(String defaultLocTFText) {
		this.defaultLocTFText = defaultLocTFText;
	}

	public String getFilePlaceLText() {
		return filePlaceLText;
	}

	public void setFilePlaceLText(String filePlaceLText) {
		this.filePlaceLText = filePlaceLText;
	}

	public JLabel getFilePlaceL() {
		return filePlaceL;
	}

	public void setFilePlaceL(JLabel filePlaceL) {
		this.filePlaceL = filePlaceL;
	}

	public String getFileLocTFDefaultValue() {
		return fileLocTFDefaultValue;
	}

	public void setFileLocTFDefaultValue(String fileLocTFDefaultValue) {
		this.fileLocTFDefaultValue = fileLocTFDefaultValue;
	}

	public String getFileLocTFTooltipText() {
		return fileLocTFTooltipText;
	}

	public void setFileLocTFTooltipText(String fileLocTFTooltipText) {
		this.fileLocTFTooltipText = fileLocTFTooltipText;
	}

	public String getDefaultLocBText() {
		return defaultLocBText;
	}

	public void setDefaultLocBText(String defaultLocBText) {
		this.defaultLocBText = defaultLocBText;
	}

	public String getGetFileBText() {
		return getFileBText;
	}

	public void setGetFileBText(String getFileBText) {
		this.getFileBText = getFileBText;
	}

	public JLabel getPrintL() {
		return printLCode;
	}

	public void setPrintL(JLabel printL) {
		this.printLCode = printL;
	}

	public String getWriteLocXTooltipText() {
		return writeLocXTooltipText;
	}

	public void setWriteLocXTooltipText(String writeLocXTooltipText) {
		this.writeLocXTooltipText = writeLocXTooltipText;
	}

	public String getWriteLocYTooltipText() {
		return writeLocYTooltipText;
	}

	public void setWriteLocYTooltipText(String writeLocYTooltipText) {
		this.writeLocYTooltipText = writeLocYTooltipText;
	}

	public String getPdfPreviewBText() {
		return pdfPreviewBText;
	}

	public void setPdfPreviewBText(String pdfPreviewBText) {
		this.pdfPreviewBText = pdfPreviewBText;
	}

	public String getCancelSettingBText() {
		return cancelSettingBText;
	}

	public void setCancelSettingBText(String cancelSettingBText) {
		this.cancelSettingBText = cancelSettingBText;
	}

	public JTextField getCodeLocX() {
		return codeLocX;
	}

	public void setCodeLocX(JTextField codeLocX) {
		this.codeLocX = codeLocX;
	}

	public JTextField getCodeLocY() {
		return codeLocY;
	}

	public void setCodeLocY(JTextField codeLocY) {
		this.codeLocY = codeLocY;
	}

	public JTextField getSystemLocX() {
		return systemLocX;
	}

	public void setSystemLocX(JTextField systemLocX) {
		this.systemLocX = systemLocX;
	}

	public JTextField getSystemLocY() {
		return systemLocY;
	}

	public void setSystemLocY(JTextField systemLocY) {
		this.systemLocY = systemLocY;
	}

	public JPanel getSystemPanel() {
		return systemPanel;
	}

	public void setSystemPanel(JPanel systemPanel) {
		this.systemPanel = systemPanel;
	}

	public JMenu getmSubSettings() {
		return mSubSettings;
	}

	public void setmSubSettings(JMenu mSubSettings) {
		this.mSubSettings = mSubSettings;
	}

	public JMenuItem getmPrintSettings() {
		return mPrintSettings;
	}

	public void setmPrintSettings(JMenuItem mPrintSettings) {
		this.mPrintSettings = mPrintSettings;
	}

	public String getmSubSettingText() {
		return mSubSettingText;
	}

	public void setmSubSettingText(String mSubSettingText) {
		this.mSubSettingText = mSubSettingText;
	}

	public String getmPrintSettingsText() {
		return mPrintSettingsText;
	}

	public void setmPrintSettingsText(String mPrintSettingsText) {
		this.mPrintSettingsText = mPrintSettingsText;
	}

	public String getmPrintSettingsToolTipText() {
		return mPrintSettingsToolTipText;
	}

	public void setmPrintSettingsToolTipText(String mPrintSettingsToolTipText) {
		this.mPrintSettingsToolTipText = mPrintSettingsToolTipText;
	}

	public JComboBox<Object> getLanguage() {
		return language;
	}

	public void setLanguage(JComboBox<Object> language) {
		this.language = language;
	}

	public Object[] getLanguages() {
		return languages;
	}

	public void setLanguages(Object[] objects) {
		this.languages = objects;
	}

	public JLabel getLanguageLabel() {
		return languageLabel;
	}

	public void setLanguageLabel(JLabel languageLabel) {
		this.languageLabel = languageLabel;
	}

	public String getLanguageText() {
		return languageText;
	}

	public void setLanguageText(String languageText) {
		this.languageText = languageText;
	}

	public String getPrintLTextCode() {
		return printLTextCode;
	}

	public void setPrintLTextCode(String printLTextCode) {
		this.printLTextCode = printLTextCode;
	}

	public String getPrintLTextSystem() {
		return printLTextSystem;
	}

	public void setPrintLTextSystem(String printLTextSystem) {
		this.printLTextSystem = printLTextSystem;
	}

	public JFrame getFrameGeneralSettings() {
		return frameGeneralSettings;
	}

	public void setFrameGeneralSettings(JFrame frameGeneralSettings) {
		this.frameGeneralSettings = frameGeneralSettings;
	}

	public JLabel getPrintLCode() {
		return printLCode;
	}

	public void setPrintLCode(JLabel printLCode) {
		this.printLCode = printLCode;
	}

	public JLabel getPrintLSystem() {
		return printLSystem;
	}

	public void setPrintLSystem(JLabel printLSystem) {
		this.printLSystem = printLSystem;
	}

	public String getFrameGeneralSettingsText() {
		return frameGeneralSettingsText;
	}

	public void setFrameGeneralSettingsText(String frameGeneralSettingsText) {
		this.frameGeneralSettingsText = frameGeneralSettingsText;
	}

	public String getInputError() {
		return inputError;
	}

	public void setInputError(String inputError) {
		this.inputError = inputError;
	}

	public String getFindSystem() {
		return findSystem;
	}

	public void setFindSystem(String findSystem) {
		this.findSystem = findSystem;
	}

	public String getSystemFoundMS() {
		return systemFoundMS;
	}

	public void setSystemFoundMS(String systemFoundMS) {
		this.systemFoundMS = systemFoundMS;
	}

	public String getSystemNotFound() {
		return systemNotFound;
	}

	public void setSystemNotFound(String systemNotFound) {
		this.systemNotFound = systemNotFound;
	}

	public String getSystemNotFoundMS() {
		return systemNotFoundMS;
	}

	public void setSystemNotFoundMS(String systemNotFoundMS) {
		this.systemNotFoundMS = systemNotFoundMS;
	}

	public String getErrorUsedNumber() {
		return errorUsedNumber;
	}

	public void setErrorUsedNumber(String errorUsedNumber) {
		this.errorUsedNumber = errorUsedNumber;
	}

	public String getErrorUsedNumberMS() {
		return errorUsedNumberMS;
	}

	public void setErrorUsedNumberMS(String errorUsedNumberMS) {
		this.errorUsedNumberMS = errorUsedNumberMS;
	}

	public String getControlAdded() {
		return controlAdded;
	}

	public void setControlAdded(String controlAdded) {
		this.controlAdded = controlAdded;
	}

	public String getControlAddedMS() {
		return controlAddedMS;
	}

	public void setControlAddedMS(String controlAddedMS) {
		this.controlAddedMS = controlAddedMS;
	}

	public String getConfirmRemoval() {
		return confirmRemoval;
	}

	public void setConfirmRemoval(String confirmRemoval) {
		this.confirmRemoval = confirmRemoval;
	}

	public String getConfirmRemovalMS() {
		return confirmRemovalMS;
	}

	public void setConfirmRemovalMS(String confirmRemovalMS) {
		this.confirmRemovalMS = confirmRemovalMS;
	}

	public String getInputErrorMS() {
		return inputErrorMS;
	}

	public void setInputErrorMS(String inputErrorMS) {
		this.inputErrorMS = inputErrorMS;
	}

	public String getPinReplacementConfirm() {
		return pinReplacementConfirm;
	}

	public void setPinReplacementConfirm(String pinReplacementConfirm) {
		this.pinReplacementConfirm = pinReplacementConfirm;
	}

	public String getPinReplacementConfirmMS() {
		return pinReplacementConfirmMS;
	}

	public void setPinReplacementConfirmMS(String pinReplacementConfirmMS) {
		this.pinReplacementConfirmMS = pinReplacementConfirmMS;
	}

	public String getNoSettingsError() {
		return noSettingsError;
	}

	public void setNoSettingsError(String noSettingsError) {
		this.noSettingsError = noSettingsError;
	}

	public String getNoSettingsErrorMS() {
		return noSettingsErrorMS;
	}

	public void setNoSettingsErrorMS(String noSettingsErrorMS) {
		this.noSettingsErrorMS = noSettingsErrorMS;
	}

	public String getPinReplaced() {
		return pinReplaced;
	}

	public void setPinReplaced(String pinReplaced) {
		this.pinReplaced = pinReplaced;
	}

	public String getPinReplacedMS() {
		return pinReplacedMS;
	}

	public void setPinReplacedMS(String pinReplacedMS) {
		this.pinReplacedMS = pinReplacedMS;
	}

}
