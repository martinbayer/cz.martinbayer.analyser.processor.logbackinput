package cz.martinbayer.analyser.processor.logbackinput.processor;

import java.io.File;

import cz.martinbayer.analyser.impl.ConcreteData;
import cz.martinbayer.analyser.processors.types.InputProcessor;
import cz.martinbayer.logparser.logback.handler.LogBackHandler;

public class LogbackInputProcessor extends InputProcessor<ConcreteData> {

	private File[] logFiles;
	private String pattern;
	private String dateTimeFormat;
	private LogbackParserListener parserListener;

	public LogbackInputProcessor() {
		super();
		parserListener = new LogbackParserListener(this, logData);
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void read() {
		LogBackHandler handler = LogBackHandler.getInstance(logFiles, pattern,
				"UTF-8");
		handler.doParse(parserListener);
		System.out.println("size:"
				+ parserListener.getData().getLogRecords().size());
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

	public final void setDateTimeFormat(String dateTimeFormat) {
		this.dateTimeFormat = dateTimeFormat;
	}

	public final File[] getLogFiles() {
		return logFiles;
	}

	public final String getPattern() {
		return pattern;
	}

	public final String getDateTimeFormat() {
		return dateTimeFormat;
	}
}
