package cz.martinbayer.analyser.processor.logbackinput;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.events.MouseEvent;

import cz.martinbayer.analyser.impl.ConcreteE4LogsisLog;
import cz.martinbayer.analyser.processor.logbackinput.paletteitem.LogbackInputPaletteItem;
import cz.martinbayer.analyser.processor.logbackinput.processor.LogbackInputProcLogic;
import cz.martinbayer.analyser.processors.IProcessorItemWrapper;
import cz.martinbayer.analyser.processors.IProcessorLogic;
import cz.martinbayer.analyser.processors.IProcessorsPaletteItem;

public class LogbackInputProcItemWrapper implements
		IProcessorItemWrapper<ConcreteE4LogsisLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8804723076311710597L;
	private LogbackInputProcLogic logic;
	private LogbackInputPaletteItem item;

	@Override
	public IProcessorLogic<ConcreteE4LogsisLog> getProcessorLogic() {
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
		item.openDialog((LogbackInputProcLogic) getProcessorLogic());

	}

	@Override
	public IProcessorItemWrapper<ConcreteE4LogsisLog> getInstance() {
		return new LogbackInputProcItemWrapper();
	}

	@Override
	public void setContext(IEclipseContext ctx) {
		Activator.setEclipseContext(ctx);
	}

}
