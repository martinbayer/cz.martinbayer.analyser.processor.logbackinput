package cz.martinbayer.analyser.processor.logbackinput.processor;

import java.io.File;

import cz.martinbayer.analyser.impl.ConcreteXMLog;
import cz.martinbayer.analyser.processors.IProcessorLogic;
import cz.martinbayer.analyser.processors.types.LogProcessor;

public class LogbackInputProcLogic implements IProcessorLogic<ConcreteXMLog> {

	private LogbackInputProcessor processor;

	@Override
	public LogProcessor<ConcreteXMLog> getProcessor() {
		if (processor == null) {
			processor = new LogbackInputProcessor();
		}
		return processor;
	}

	public void setLogFiles(File[] logFiles) {
		((LogbackInputProcessor) getProcessor()).setLogFiles(logFiles);
	}

	public void setPattern(String pattern) {
		((LogbackInputProcessor) getProcessor()).setPattern(pattern);
	}

	public void setDateTimeFormat(String dateTimeFormat) {
		((LogbackInputProcessor) getProcessor())
				.setDateTimeFormat(dateTimeFormat);
	}
}
