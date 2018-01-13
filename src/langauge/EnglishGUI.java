package langauge;

import java.util.ArrayList;
import java.util.List;

import DTO.GUIText;

public class EnglishGUI {

	public List<GUIText> getEnglishGUI() {
		List<GUIText> fields = new ArrayList<>();
		fields.add(new GUIText("mFileText", " File"));
		fields.add(new GUIText("mSettingsText", " Settings"));
		fields.add(new GUIText("mCodeSettingsText", " Code Settings"));
		fields.add(new GUIText("mCodeSettingsToolTipText",
				" Opens the settings options for code generation."));
		fields.add(new GUIText("mExitTooltipText", " Close Program"));
		fields.add(new GUIText("mGSettingsText", " General Settings"));
		fields.add(new GUIText("mGSettingsToolTipText",
				" Opens up general settings"));
		fields.add(new GUIText("mPrintPreviewText", " Print Preview"));
		fields.add(new GUIText("mPrintPreviewTooltipText",
				" Shows the layout of the print"));
		fields.add(new GUIText("mRestoDBText", " Restore Database"));
		fields.add(new GUIText("mRestoDBTooltipText",
				" Restore the database from backup"));
		fields.add(new GUIText("dbNameText", " !dbname!"));
		fields.add(new GUIText("codeLabelText", " Code"));
		fields.add(new GUIText("controlItemLabelText", " !control!"));
		fields.add(new GUIText("addSystemBText", " Add!control!"));
		fields.add(new GUIText("newCodeBText", " New Code"));
		fields.add(new GUIText("saveCodeBText", " Save Code"));
		fields.add(new GUIText("searchControlBText", " Search !control!"));
		fields.add(new GUIText("removeControlBText", " Remove !control!"));
		fields.add(new GUIText("replaceCodeBText", " Replace code"));
		fields.add(new GUIText("searchControlTFTooltipText",
				" Searches the database for the given !control!"));
		fields.add(new GUIText("removeControlTFTooltipText",
				" Removes the !control! from the database"));
		fields.add(new GUIText("replaceCodeTFTooltipText",
				" Replaces the code of a given !control! in the database"));
		fields.add(new GUIText("defaultCodeValue", " 00000"));
		fields.add(new GUIText(
				"codeTFTooltipText",
				" The code to be saved to the !control!<br> This code can't be changed in a hand<br> after a new one has been generated."));
		fields.add(new GUIText("controlDefaultValue", " "));
		fields.add(new GUIText("controlTFTooltipText",
				" The name, number or value given to the !control!"));
		fields.add(new GUIText("frameAddControlText", " Add new !control!"));
		fields.add(new GUIText("cancelBText", " Cancel"));
		fields.add(new GUIText("frameCodeSettingsText", " Code Settings"));
		fields.add(new GUIText("codeLengthLabelText", " Length of the code"));
		fields.add(new GUIText("codeLengthFText", " 5"));
		fields.add(new GUIText(
				"codeLengthFTextTooltipText",
				" Defines the length of a new code. <br>The longer the code, the stronger it is.<br> The length should be between 4-30 symbols"));
		fields.add(new GUIText("codeStrengthLText", " Type of the code"));
		fields.add(new GUIText("codeTypeArrayText",
				" Numbers,Numbers and letters,Numbers and small and big letters"));
		fields.add(new GUIText(
				"codeTypeTooltipText",
				" Decides the strength of the code.<br> The strongst code will be a combination of length and<br> the mixture of numbers and small and big letters"));
		fields.add(new GUIText("saveSettingsBText", " Save Settings"));
		fields.add(new GUIText("defaultSettingBText", " Default Settings"));
		fields.add(new GUIText("errorLText",
				" An exception, !errorEX! , has occured"));
		fields.add(new GUIText("emailErrLText", " Your email"));
		fields.add(new GUIText("defaultEmailErrFText", " "));
		fields.add(new GUIText("emailErrFTooltipText",
				" Leave this field empty, if you don't want a reply with possible help"));
		fields.add(new GUIText("emailErrDesLText", " What happened?"));
		fields.add(new GUIText("emailErrBText", " Send Error Message"));
		fields.add(new GUIText("emailErrCText", " Dont Send"));
		fields.add(new GUIText("confirmRemovalBText", " Confirm"));
		fields.add(new GUIText("noButtonText", " No"));
		fields.add(new GUIText("dbNotFoundText", " Database not found"));
		fields.add(new GUIText("dbNotFoundLText",
				" Unable to find the database, or the database might not exist"));
		fields.add(new GUIText("confirmCreateBText", " Confirm"));
		fields.add(new GUIText("confirmCreateBTooltipText",
				" Confirms creation of new database"));
		fields.add(new GUIText("dbErrorBCreateBackupText",
				" Create from backup"));
		fields.add(new GUIText("dbErrorBCreateBackupTooltipText",
				" Creates database from backup"));
		fields.add(new GUIText("labelDefaultText", " Folder Placement"));
		fields.add(new GUIText("defaultLocTFText", " "));
		fields.add(new GUIText("filePlaceLText", " File Placement"));
		fields.add(new GUIText("fileLocTFDefaultValue", " "));
		fields.add(new GUIText("fileLocTFTooltipText",
				" If you don't want to write to file, leave this field empty"));
		fields.add(new GUIText("defaultLocBText", " Find Folder  "));
		fields.add(new GUIText("getFileBText", " Find File"));
		fields.add(new GUIText("printLTextCode", " Code Placement"));
		fields.add(new GUIText("printLTextSystem", " System Placement"));
		fields.add(new GUIText("writeLocXTooltipText",
				" Coordinates 0,0 is located in the bottom left side of the document"));
		fields.add(new GUIText("writeLocYTooltipText",
				" Coordinates 0,0 is located in the bottom left side of the document"));
		fields.add(new GUIText("pdfPreviewBText", " Preview print out"));
		fields.add(new GUIText("cancelSettingBText", " Cancel"));
		fields.add(new GUIText("mSubSettingText", " Settings"));
		fields.add(new GUIText("mPrintSettingsText", " Print Settings"));
		fields.add(new GUIText("mPrintSettingsToolTipText",
				" Settings for where to print the system and code if they are to be printed."));
		fields.add(new GUIText("languageText", " Language."));
		fields.add(new GUIText("inputError", " Missing input."));
		fields.add(new GUIText("inputErrorMS", " No input given."));
		fields.add(new GUIText("findSystem", " Find System."));
		fields.add(new GUIText("systemFoundMS",
				" System !gui.getSearchControlTF().getText() has pin: !fSPin."));
		fields.add(new GUIText("systemNotFound", " System not found."));
		fields.add(new GUIText("systemNotFoundMS",
				" System !gui.getSearchControlTF().getText() does not exist in the database."));
		fields.add(new GUIText("errorUsedNumber",
				" Systemnumber is already used."));
		fields.add(new GUIText("errorUsedNumberMS",
				" System !gui.getControlTF().getText() is already in used."));
		fields.add(new GUIText("controlAdded", " !control! Added."));
		fields.add(new GUIText("controlAddedMS",
				" !control! !gui.getControlTF().getText() has been added with pin:"));
		fields.add(new GUIText("confirmRemoval", " Confirm Removal."));
		fields.add(new GUIText("confirmRemovalMS", " Confirm removal of:"));
		fields.add(new GUIText("pinReplacementConfirm",
				" Confirm replacement of Pin."));
		fields.add(new GUIText("pinReplacementConfirmMS",
				" Confirm replacement of pin for !control!:"));
		fields.add(new GUIText("noSettingsError", " No settings found."));
		fields.add(new GUIText(
				"noSettingsErrorMS",
				" No Settings has been found.<br> Settings has been sat to default.<br> It is recommended you change these before moving on."));
		fields.add(new GUIText("pinReplaced", "Pin Replaced"));
		fields.add(new GUIText("pinReplacedMS",
				"Pin replaced for !gui.gui.getReplaceCodeTF().getText() with pin: "));
		return fields;
	}
}
