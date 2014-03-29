package cz.martinbayer.analyser.processor.logbackinput.processor;

import java.io.File;

import cz.martinbayer.analyser.impl.ConcreteData;
import cz.martinbayer.analyser.processors.types.InputProcessor;
import cz.martinbayer.logparser.logback.handler.LogBackHandler;

public class LogbackInputProcessor extends InputProcessor<ConcreteData> {

	private File[] logFiles;
	private String pattern;

	public LogbackInputProcessor() {

	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void read() {
		LogBackHandler handler = LogBackHandler.getInstance(logFiles, pattern,
				"UTF-8");
	}

	/**
	 * Set the log files directory location
	 * 
	 * @param directory
	 *            - log files location
	 */

	public final void setLogFiles(File[] logFiles) {
		this.logFiles = logFiles;
	}

	public final void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
