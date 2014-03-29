package cz.martinbayer.analyser.processor.logbackinput.paletteitem;

import org.eclipse.swt.widgets.Display;

import cz.martinbayer.analyser.processor.logbackinput.gui.LogbackConfigDialog;
import cz.martinbayer.analyser.processor.logbackinput.gui.LogbackConfigDialogModel;
import cz.martinbayer.analyser.processor.logbackinput.processor.LogbackInputProcLogic;
import cz.martinbayer.analyser.processors.BasicProcessorPaletteItem;

public class LogbackInputPaletteItem extends BasicProcessorPaletteItem {

	private static final String LABEL = "Logback input";
	private LogbackConfigDialogModel model = new LogbackConfigDialogModel();

	public LogbackInputPaletteItem() {
		imagePath = "images/icon.png";
		disabledImagePath = "images/icon_dis.png";
	}

	@Override
	public String getLabel() {
		return LABEL;
	}

	public void openDialog(LogbackInputProcLogic logic) {
		LogbackConfigDialog dialog = new LogbackConfigDialog(Display
				.getDefault().getActiveShell(), logic, model);
		dialog.open();
	}

}
