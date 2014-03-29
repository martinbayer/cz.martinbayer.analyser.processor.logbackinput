package cz.martinbayer.analyser.processor.logbackinput;

import org.eclipse.swt.events.MouseEvent;

import cz.martinbayer.analyser.impl.ConcreteData;
import cz.martinbayer.analyser.processor.logbackinput.paletteitem.LogbackInputPaletteItem;
import cz.martinbayer.analyser.processor.logbackinput.processor.LogbackInputProcLogic;
import cz.martinbayer.analyser.processors.IProcessorItemWrapper;
import cz.martinbayer.analyser.processors.IProcessorLogic;
import cz.martinbayer.analyser.processors.IProcessorsPaletteItem;

public class LogbackInputProcItemWrapper implements
		IProcessorItemWrapper<ConcreteData> {

	private LogbackInputProcLogic logic;
	private LogbackInputPaletteItem item;

	@Override
	public IProcessorLogic<ConcreteData> getProcessorLogic() {
		if (logic == null) {
			logic = new LogbackInputProcLogic();
		}
		return logic;
	}

	@Override
	public IProcessorsPaletteItem getProcessorPaletteItem() {
		if (item == null) {
			item = new LogbackInputPaletteItem();
		}
		return item;
	}

	@Override
	public void mouseDoubleClicked(MouseEvent e) {
		item.openDialog(logic);

	}

	@Override
	public IProcessorItemWrapper<ConcreteData> getInstance() {
		return new LogbackInputProcItemWrapper();
	}

}
