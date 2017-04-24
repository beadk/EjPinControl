package settings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

import DTO.FieldsDTO;

public class TextImport {

	List<FieldsDTO> fields = new ArrayList<>();
	private String control = "";
	private String dbname = "";
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
	private String codeLengthLabelText;
	private String codeLengthFText;
	private String codeLengthFTextTooltipText;
	private String codeStrengthLText;
	private String codeTypeArrayText;
	private String codeTypeTooltipText;
	private String saveSettingsBText;
	private String defaultSettingBText;
	private String errorLText;
	private String emailErrLText;
	private String defaultEmailErrFText;
	private String emailErrFTooltipText;
	private String emailErrDesLText;
	private String emailErrBText;
	private String emailErrCText;
	private String comfirmRemovalBText;
	private String noButtonText;
	private String dbNotFoundText;
	private String dbNotFoundLText;
	private String comfirmCreateBText;
	private String comfirmCreateBTooltipText;
	private String dbErrorBCreateBackupText;
	private String dbErrorBCreateBackupTooltipText;
	private String labelDefaultText;
	private String defaultLocTFText;
	private String filePlaceLText;
	private String fileLocTFDefaultValue;
	private String fileLocTFTooltipText;
	private String defaultLocBText;
	private String getFileBText;
	private String printLTextCode;
	private String printLTextSystem;
	private String writeLocXTooltipText;
	private String writeLocYTooltipText;
	private String pdfPreviewBText;
	private String cancelSettingBText;
	private String mSubSettingText;
	private String mPrintSettingsText;
	private String mPrintSettingsToolTipText;
	private String languageText;
	private String inputError;
	String[] tryA;

	public void readDefault() {
		try {
			FileInputStream file = new FileInputStream("res/EnglishGUI.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					file));
			String line = reader.readLine();
			while (line != null) {
				tryA = line.split(";");
				if (tryA[1].contains("!control!")) {
					tryA[1] = tryA[1].replace("!control!", control);
					setText(tryA);
					fields.add(new FieldsDTO(tryA[0]));
				} else if (tryA[1].contains("!dbname!")) {
					tryA[1] = tryA[1].replace("!dbname!", dbname);
					setText(tryA);
					fields.add(new FieldsDTO(tryA[0]));
				} else {
					setText(tryA);
					fields.add(new FieldsDTO(tryA[0]));
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// public void readEnglishText(String path) {
	// try {
	// FileInputStream file = new FileInputStream(path + "/EnglishGUI.txt");
	// BufferedReader reader = new BufferedReader(new InputStreamReader(
	// file));
	// String line = reader.readLine();
	// while (line != null) {
	// tryA = line.split(";");
	// setText(tryA);
	// if (tryA[1].contains("!control!")) {
	// System.out.println(tryA[1].replace("!control!", control));
	// }
	// line = reader.readLine();
	// }
	// reader.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	public void writeSwtich(String[] text) {
		String s1 = text[0].substring(0, 1).toUpperCase();
		String nameCapitalized = s1 + text[0].substring(1);
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("switch.txt", true)));
			out.write("case \"" + text[0] + "\":\ngui.set" + nameCapitalized
					+ "(textImp.get" + text[0] + "()); \nbreak;\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setText(String[] text) {
		switch (text[0]) {
		case "mFileText":
			mFileText = text[1];
			break;
		case "mSettingsText":
			mSettingsText = text[1];
			break;
		case "mCodeSettingsText":
			mCodeSettingsText = text[1];
			break;
		case "mCodeSettingsToolTipText":
			mCodeSettingsToolTipText = text[1];
			break;
		case "mExitTooltipText":
			mExitTooltipText = text[1];
			break;
		case "mGSettingsText":
			mGSettingsText = text[1];
			break;
		case "mGSettingsToolTipText":
			mGSettingsToolTipText = text[1];
			break;
		case "mPrintPreviewText":
			mPrintPreviewText = text[1];
			break;
		case "mPrintPreviewTooltipText":
			mPrintPreviewTooltipText = text[1];
			break;
		case "mRestoDBText":
			mRestoDBText = text[1];
			break;
		case "mRestoDBTooltipText":
			mRestoDBTooltipText = text[1];
			break;
		case "dbNameText":
			dbNameText = text[1];
			break;
		case "codeLabelText":
			codeLabelText = text[1];
			break;
		case "controlItemLabelText":
			controlItemLabelText = text[1];
			break;
		case "addSystemBText":
			addSystemBText = text[1];
			break;
		case "newCodeBText":
			newCodeBText = text[1];
			break;
		case "saveCodeBText":
			saveCodeBText = text[1];
			break;
		case "searchControlBText":
			searchControlBText = text[1];
			break;
		case "removeControlBText":
			removeControlBText = text[1];
			break;
		case "replaceCodeBText":
			replaceCodeBText = text[1];
			break;
		case "searchControlTFTooltipText":
			searchControlTFTooltipText = text[1];
			break;
		case "removeControlTFTooltipText":
			removeControlTFTooltipText = text[1];
			break;
		case "replaceCodeTFTooltipText":
			replaceCodeTFTooltipText = text[1];
			break;
		case "defaultCodeValue":
			defaultCodeValue = text[1];
			break;
		case "codeTFTooltipText":
			codeTFTooltipText = text[1];
			break;
		case "controlDefaultValue":
			controlDefaultValue = text[1];
			break;
		case "controlTFTooltipText":
			controlTFTooltipText = text[1];
			break;
		case "frameAddControlText":
			frameAddControlText = text[1];
			break;
		case "cancelBText":
			cancelBText = text[1];
			break;
		case "frameCodeSettingsText":
			frameCodeSettingsText = text[1];
			break;
		case "codeLengthLabelText":
			codeLengthLabelText = text[1];
			break;
		case "codeLengthFText":
			codeLengthFText = text[1];
			break;
		case "codeLengthFTextTooltipText":
			codeLengthFTextTooltipText = text[1];
			break;
		case "codeStrengthLText":
			codeStrengthLText = text[1];
			break;
		case "codeTypeArrayText":
			codeTypeArrayText = text[1];
			break;
		case "codeTypeTooltipText":
			codeTypeTooltipText = text[1];
			break;
		case "saveSettingsBText":
			saveSettingsBText = text[1];
			break;
		case "defaultSettingBText":
			defaultSettingBText = text[1];
			break;
		case "errorLText":
			errorLText = text[1];
			break;
		case "emailErrLText":
			emailErrLText = text[1];
			break;
		case "defaultEmailErrFText":
			defaultEmailErrFText = text[1];
			break;
		case "emailErrFTooltipText":
			emailErrFTooltipText = text[1];
			break;
		case "emailErrDesLText":
			emailErrDesLText = text[1];
			break;
		case "emailErrBText":
			emailErrBText = text[1];
			break;
		case "emailErrCText":
			emailErrCText = text[1];
			break;
		case "comfirmRemovalBText":
			comfirmRemovalBText = text[1];
			break;
		case "noButtonText":
			noButtonText = text[1];
			break;
		case "dbNotFoundText":
			dbNotFoundText = text[1];
			break;
		case "dbNotFoundLText":
			dbNotFoundLText = text[1];
			break;
		case "comfirmCreateBText":
			comfirmCreateBText = text[1];
			break;
		case "comfirmCreateBTooltipText":
			comfirmCreateBTooltipText = text[1];
			break;
		case "dbErrorBCreateBackupText":
			dbErrorBCreateBackupText = text[1];
			break;
		case "dbErrorBCreateBackupTooltipText":
			dbErrorBCreateBackupTooltipText = text[1];
			break;
		case "labelDefaultText":
			labelDefaultText = text[1];
			break;
		case "defaultLocTFText":
			defaultLocTFText = text[1];
			break;
		case "filePlaceLText":
			filePlaceLText = text[1];
			break;
		case "fileLocTFDefaultValue":
			fileLocTFDefaultValue = text[1];
			break;
		case "fileLocTFTooltipText":
			fileLocTFTooltipText = text[1];
			break;
		case "defaultLocBText":
			defaultLocBText = text[1];
			break;
		case "getFileBText":
			getFileBText = text[1];
			break;
		case "printLTextCode":
			printLTextCode = text[1];
			break;
		case "printLTextSystem":
			printLTextSystem = text[1];
		case "writeLocXTooltipText":
			writeLocXTooltipText = text[1];
			break;
		case "writeLocYTooltipText":
			writeLocYTooltipText = text[1];
			break;
		case "pdfPreviewBText":
			pdfPreviewBText = text[1];
			break;
		case "cancelSettingBText":
			cancelSettingBText = text[1];
			break;
		case "mSubSettingText":
			mSubSettingText = text[1];
			break;
		case "mPrintSettingsText":
			mPrintSettingsText = text[1];
			break;
		case "mPrintSettingsToolTipText":
			mPrintSettingsToolTipText = text[1];
			break;
		case "languageText":
			languageText = text[1];
			break;
		case "inputError":
			inputError = text[1];
			break;
		default:
		}
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
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

	public String getCodeStrengthLText() {
		return codeStrengthLText;
	}

	public void setCodeStrengthLText(String codeStrengthLText) {
		this.codeStrengthLText = codeStrengthLText;
	}

	public String getCodeTypeArrayText() {
		return codeTypeArrayText;
	}

	public void setCodeTypeArrayText(String codeTypeArrayText) {
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

	public String getComfirmRemovalBText() {
		return comfirmRemovalBText;
	}

	public void setComfirmRemovalBText(String comfirmRemovalBText) {
		this.comfirmRemovalBText = comfirmRemovalBText;
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

	public String getDbNotFoundLText() {
		return dbNotFoundLText;
	}

	public void setDbNotFoundLText(String dbNotFoundLText) {
		this.dbNotFoundLText = dbNotFoundLText;
	}

	public String getComfirmCreateBText() {
		return comfirmCreateBText;
	}

	public void setComfirmCreateBText(String comfirmCreateBText) {
		this.comfirmCreateBText = comfirmCreateBText;
	}

	public String getComfirmCreateBTooltipText() {
		return comfirmCreateBTooltipText;
	}

	public void setComfirmCreateBTooltipText(String comfirmCreateBTooltipText) {
		this.comfirmCreateBTooltipText = comfirmCreateBTooltipText;
	}

	public String getDbErrorBCreateBackupText() {
		return dbErrorBCreateBackupText;
	}

	public void setDbErrorBCreateBackupText(String dbErrorBCreateBackupText) {
		this.dbErrorBCreateBackupText = dbErrorBCreateBackupText;
	}

	public String getDbErrorBCreateBackupTooltipText() {
		return dbErrorBCreateBackupTooltipText;
	}

	public void setDbErrorBCreateBackupTooltipText(
			String dbErrorBCreateBackupTooltipText) {
		this.dbErrorBCreateBackupTooltipText = dbErrorBCreateBackupTooltipText;
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

	public String getPrintLTextCode() {
		return printLTextCode;
	}

	public void setPrintLTextCode(String printLText) {
		this.printLTextCode = printLText;
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

	public String[] getTryA() {
		return tryA;
	}

	public void setTryA(String[] tryA) {
		this.tryA = tryA;
	}

	public List<FieldsDTO> getFields() {
		return fields;
	}

	public void setFields(List<FieldsDTO> fields) {
		this.fields = fields;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
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

	public String getLanguageText() {
		return languageText;
	}

	public void setLanguageText(String languageText) {
		this.languageText = languageText;
	}

	public String getPrintLTextSystem() {
		return printLTextSystem;
	}

	public void setPrintLTextSystem(String printLTextSystem) {
		this.printLTextSystem = printLTextSystem;
	}

	public String getInputError() {
		return inputError;
	}

	public void setInputError(String inputError) {
		this.inputError = inputError;
	}

}
