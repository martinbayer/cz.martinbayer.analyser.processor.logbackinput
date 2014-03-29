package cz.martinbayer.analyser.processor.logbackinput.processor;

import java.io.File;

import cz.martinbayer.analyser.impl.ConcreteData;
import cz.martinbayer.analyser.processors.IProcessorLogic;
import cz.martinbayer.analyser.processors.types.LogProcessor;

public class LogbackInputProcLogic implements IProcessorLogic<ConcreteData> {

	private LogbackInputProcessor processor;

	@Override
	public LogProcessor<ConcreteData> getProcessor() {
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
}