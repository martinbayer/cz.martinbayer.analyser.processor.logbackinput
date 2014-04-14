package cz.martinbayer.analyser.processor.logbackinput.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import cz.martinbayer.analyser.impl.ConcreteXMLog;
import cz.martinbayer.analyser.processors.model.ELogLevel;
import cz.martinbayer.analyser.processors.model.XMLogData;
import cz.martinbayer.logparser.logback.pattern.TypedPatternFactory;
import cz.martinbayer.logparser.logic.ILogParserEvent;
import cz.martinbayer.logparser.logic.ILogParserListener;
import cz.martinbayer.logparser.logic.LogParserPhase;

public class LogbackParserListener implements ILogParserListener {
	private ConcreteXMLog object;
	private XMLogData<ConcreteXMLog> logData;
	private LogbackInputProcessor inputProcessor;

	public LogbackParserListener(LogbackInputProcessor inputProcessor,
			XMLogData<ConcreteXMLog> logData) {
		this.inputProcessor = inputProcessor;
		this.logData = logData;
	}

	@Override
	public void parsed(ILogParserEvent event) {
		if (event.getPhase() == LogParserPhase.START) {
			object = new ConcreteXMLog();
		} else if (event.getPhase() == LogParserPhase.FINISH) {
			logData.addLogRecord(object);
			object = null;
		} else {
			if (object == null || event.getGroupName() == null) {
				throw new NullPointerException("Log event haven't started yet");
			}

			switch (TypedPatternFactory.getConversionWordByGroupName(event
					.getGroupName())) {
			case CALLER_STACK:
				break;
			case CONTEXT_NAME:
				break;
			case DATE_TIME_OF_EVENT:
				if (inputProcessor.getDateTimeFormat() != null) {
					try {
						object.setEventDateTime(new SimpleDateFormat(
								inputProcessor.getDateTimeFormat()).parse(event
								.getGroupValue()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case EXCEPTION:
				object.setErrorMessage(event.getGroupValue());
				break;
			case EXTENDED_EXCEPTION:
				object.setErrorMessage(event.getGroupValue());
				break;
			case FILE_OF_REQUEST:
				object.setFileName(event.getGroupValue());
				break;
			case LEVEL_OF_EVENT:
				object.setLogLevel(ELogLevel.valueOf(event.getGroupValue()));
				break;
			case LINE_OF_REQUEST:
				object.setLine(Long.parseLong(event.getGroupValue().trim()));
				break;
			case LOGGER_CLASS_CONVERSION:
				break;
			case MARKER:
				break;
			case MESSAGE:
				object.setMessage(event.getGroupValue());
				break;
			case METHOD_NAME:
				break;
			case NEW_LINE:
				break;
			case NO_EXCEPTION:
				break;
			case PROPERTY:
				break;
			case THREAD_NAME:
				object.setThreadName(event.getGroupValue());
				break;
			case TIME_SINCE_APP_START:
				break;
			default:
				break;
			}
		}

	}

	public XMLogData<ConcreteXMLog> getData() {
		return logData;
	}
}
