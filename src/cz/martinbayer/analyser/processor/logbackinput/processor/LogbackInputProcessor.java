package cz.martinbayer.analyser.processor.logbackinput.processor;

import java.io.File;

import cz.martinbayer.analyser.impl.ConcreteE4LogsisLog;
import cz.martinbayer.analyser.processors.types.InputProcessor;
import cz.martinbayer.logparser.logback.mobile.handler.LogbackMobileReader;
import cz.martinbayer.logparser.logic.handler.LogHandler;
import cz.martinbayer.utils.StringUtils;

public class LogbackInputProcessor extends InputProcessor<ConcreteE4LogsisLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -358055025062680886L;
	private File[] logFiles;
	private String pattern;
	private String dateTimeFormat;
	private transient LogbackParserListener parserListener;

	public LogbackInputProcessor() {
		super();
		init();
	}

	@Override
	protected void process() {
		// no need to do any operation against parsed data
	}

	@Override
	protected void read() {

		LogbackMobileReader reader = new LogbackMobileReader();
		LogHandler handler = LogHandler.getInstance(logFiles, "UTF-8", reader,
				null);
		handler.doParse(parserListener);
	}

	/**
	 * Set the log files directory location
	 * 
	 * @param directory
	 *            - log files location
	 */

	public final void setLogFiles(File[] logFiles) {
		if (this.logFiles != logFiles) {
			logData.clearAll();
			this.logFiles = logFiles;
		}
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

	@Override
	public void init() {
		super.init();
		parserListener = new LogbackParserListener(this, logData);
	}

	/**
	 * validation of the processor properties
	 */
	@Override
	protected StringBuffer isSubProcessorValid() {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isEmtpy(pattern)) {
			sb.append("No pattern specified for processor. ");
		}
		if (StringUtils.isEmtpy(dateTimeFormat)) {
			sb.append("No date time format specified for processor. ");
		}
		if (logFiles == null || logFiles.length == 0) {
			sb.append("No log file selected. ");
		}
		if (sb.length() > 0) {
			sb.insert(0, ": ").insert(0, getName());
		}
		return sb;
	}
}
