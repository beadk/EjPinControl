package langauge;
import java.util.ArrayList;
import java.util.List;

import DTO.GUIText;
public class DanishGUI {
	
	public List<GUIText> getDanishGUI(){
		List<GUIText> fields = new ArrayList<>();
		fields.add(new GUIText("mFileText"," File"));
		fields.add(new GUIText("mSettingsText"," Indstillinger"));
		fields.add(new GUIText("mCodeSettingsText"," Kodeindstillinger"));
		fields.add(new GUIText("mCodeSettingsToolTipText"," �bner indstillingsmuligheder for kodegenerering."));
		fields.add(new GUIText("mExitTooltipText"," Luk Program"));
		fields.add(new GUIText("mGSettingsText"," Generelle indstillinger"));
		fields.add(new GUIText("mGSettingsToolTipText"," �bner generelle indstillinger"));
		fields.add(new GUIText("mPrintPreviewText"," Udskriv forh�ndsvisning"));
		fields.add(new GUIText("mPrintPreviewTooltipText"," Viser layoutet af print"));
		fields.add(new GUIText("mRestoDBText"," Gendan database"));
		fields.add(new GUIText("mRestoDBTooltipText"," Gendan databasen fra backup"));
		fields.add(new GUIText("dbNameText"," !dbname!"));
		fields.add(new GUIText("codeLabelText"," Kode"));
		fields.add(new GUIText("controlItemLabelText"," !control!"));
		fields.add(new GUIText("addSystemBText"," Tilf�j !control!"));
		fields.add(new GUIText("newCodeBText"," Ny Kode"));
		fields.add(new GUIText("saveCodeBText"," Gem Kode"));
		fields.add(new GUIText("searchControlBText"," Find !control!"));
		fields.add(new GUIText("removeControlBText"," Fjern !control!"));
		fields.add(new GUIText("replaceCodeBText"," Erstat kode"));
		fields.add(new GUIText("searchControlTFTooltipText"," S�ger i databasen for det givne !control!"));
		fields.add(new GUIText("removeControlTFTooltipText"," Fjerner !control!et fra databasen"));
		fields.add(new GUIText("replaceCodeTFTooltipText"," Erstatter koden til et given !control! i databasen"));
		fields.add(new GUIText("defaultCodeValue"," 00000"));
		fields.add(new GUIText("codeTFTooltipText"," Koden, der skal gemmes til !control!et<br> Denne kode kan ikke �ndres i en h�nd<br> efter at en ny er blevet genereret."));
		fields.add(new GUIText("controlDefaultValue"," "));
		fields.add(new GUIText("controlTFTooltipText"," Det navn, nummer eller v�rdi givet til !control!et"));
		fields.add(new GUIText("frameAddControlText"," Tilf�j ny !control!"));
		fields.add(new GUIText("cancelBText"," Annullerer"));
		fields.add(new GUIText("frameCodeSettingsText"," Kodeindstillinger"));
		fields.add(new GUIText("codeLengthLabelText"," L�ngde af koden"));
		fields.add(new GUIText("codeLengthFText"," 5"));
		fields.add(new GUIText("codeLengthFTextTooltipText"," Definerer l�ngden af en ny kode. <br>Jo l�ngere koden er, desto st�rkere er den.<br> L�ngden skal v�re mellem 4-30 symboler"));
		fields.add(new GUIText("codeStrengthLText"," Type af koden"));
		fields.add(new GUIText("codeTypeArrayText"," Tal, tal og bogstaver, tal og sm� og store bogstaver"));
		fields.add(new GUIText("codeTypeTooltipText"," Bestemmer styrken af koden.<br> Den st�rkeste kode vil v�re en kombination af l�ngde og<br> blandingen af tal og sm� og store bogstaver"));
		fields.add(new GUIText("saveSettingsBText"," Gem indstillinger"));
		fields.add(new GUIText("defaultSettingBText"," Standardindstillinger"));
		fields.add(new GUIText("errorLText"," En fejl, !errorEX! , er opst�et"));
		fields.add(new GUIText("emailErrLText"," Din email"));
		fields.add(new GUIText("defaultEmailErrFText"," "));
		fields.add(new GUIText("emailErrFTooltipText"," Lad dette felt v�re tomt, hvis du ikke vil have et svar med mulig hj�lp"));
		fields.add(new GUIText("emailErrDesLText"," Hvad skete der?"));
		fields.add(new GUIText("emailErrBText"," Send fejlmeddelelse"));
		fields.add(new GUIText("emailErrCText"," Send ikke"));
		fields.add(new GUIText("confirmRemovalBText"," Bekr�fte"));
		fields.add(new GUIText("noButtonText"," Nej"));
		fields.add(new GUIText("dbNotFoundText"," Database ikke fundet"));
		fields.add(new GUIText("dbNotFoundLText"," Kan ikke finde databasen, eller databasen eksisterer muligvis ikke"));
		fields.add(new GUIText("confirmCreateBText"," Bekr�fte oprettelse"));
		fields.add(new GUIText("confirmCreateBTooltipText"," Bekr�fter oprettelse af ny database"));
		fields.add(new GUIText("dbErrorBCreateBackupText"," Opret fra backup"));
		fields.add(new GUIText("dbErrorBCreateBackupTooltipText"," Creates database from backup"));
		fields.add(new GUIText("labelDefaultText"," Mappeplacering"));
		fields.add(new GUIText("defaultLocTFText"," "));
		fields.add(new GUIText("filePlaceLText"," File Placement"));
		fields.add(new GUIText("fileLocTFDefaultValue"," "));
		fields.add(new GUIText("fileLocTFTooltipText"," If you don't want to write to file, leave this field empty"));
		fields.add(new GUIText("defaultLocBText"," Find mappe"));
		fields.add(new GUIText("getFileBText"," Find fil"));
		fields.add(new GUIText("printLTextCode"," Kodeplacering"));
		fields.add(new GUIText("printLTextSystem"," Systemplacering"));
		fields.add(new GUIText("writeLocXTooltipText"," Koordinater 0,0 er placeret i nederste venstre side af dokumentet"));
		fields.add(new GUIText("writeLocYTooltipText"," Koordinater 0,0 er placeret i nederste venstre side af dokumentet"));
		fields.add(new GUIText("pdfPreviewBText"," Eksempel udskrift"));
		fields.add(new GUIText("cancelSettingBText"," Annullerer"));
		fields.add(new GUIText("mSubSettingText"," Indstillinger"));
		fields.add(new GUIText("mPrintSettingsText"," Udskriftsindstillinger"));
		fields.add(new GUIText("mPrintSettingsToolTipText"," Indstillinger for hvor du skal udskrive systemet og kode, hvis de skal udskrives."));
		fields.add(new GUIText("languageText"," Sprog."));
		fields.add(new GUIText("inputError"," Manglende input."));
		fields.add(new GUIText("inputErrorMS"," Ingen input givet."));
		fields.add(new GUIText("findSystem"," Find !control!."));
		fields.add(new GUIText("systemFoundMS"," !control! !gui.getSearchControlTF().getText() har pin: !fSPin."));
		fields.add(new GUIText("systemNotFound"," !control! ikke fundet."));
		fields.add(new GUIText("systemNotFoundMS"," !control! !gui.getSearchControlTF().getText() findes ikke i databasen."));
		fields.add(new GUIText("errorUsedNumber"," !control!number er allerede brugt."));
		fields.add(new GUIText("errorUsedNumberMS"," !control! !gui.getControlTF().getText() er allerede i brug."));
		fields.add(new GUIText("controlAdded"," !control! Tilf�jet."));
		fields.add(new GUIText("controlAddedMS"," !control! !gui.getControlTF().getText() er blevet tilf�jet med pin:"));
		fields.add(new GUIText("confirmRemoval"," Bekr�ft fjernelse."));
		fields.add(new GUIText("confirmRemovalMS"," Bekr�ft fjernelse af:"));
		fields.add(new GUIText("pinReplacementConfirm"," Bekr�ft udskiftning af pin."));
		fields.add(new GUIText("pinReplacementConfirmMS"," Bekr�ft udskiftning af pin til !control!:"));
		fields.add(new GUIText("pinReplaced","Pin Ersattet"));
		fields.add(new GUIText("pinReplacedMS",
				"Pin ersattet for !gui.gui.getReplaceCodeTF().getText() med pin: "));
		fields.add(new GUIText("noSettingsError"," Ingen indstillinger fundet."));
		fields.add(new GUIText("noSettingsErrorMS"," Der er ikke blevet fundet nogen indstilling.<br> Indstillinger er sat til default.<br> Det anbefales du �ndre disse f�r du fors�tter."));

		
		return fields;
	}

}
