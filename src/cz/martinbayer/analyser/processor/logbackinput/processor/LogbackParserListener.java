package cz.martinbayer.analyser.processor.logbackinput.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import cz.martinbayer.analyser.impl.ConcreteE4LogsisLog;
import cz.martinbayer.analyser.processors.model.E4LogsisLogData;
import cz.martinbayer.analyser.processors.model.ELogLevel;
import cz.martinbayer.logparser.logback.mobile.types.DateTimePattern;
import cz.martinbayer.logparser.logback.mobile.types.ExceptionPattern;
import cz.martinbayer.logparser.logback.mobile.types.FilePattern;
import cz.martinbayer.logparser.logback.mobile.types.LevelPattern;
import cz.martinbayer.logparser.logback.mobile.types.LinePattern;
import cz.martinbayer.logparser.logback.mobile.types.MessagePattern;
import cz.martinbayer.logparser.logback.mobile.types.ThreadPattern;
import cz.martinbayer.logparser.logic.ILogParserEvent;
import cz.martinbayer.logparser.logic.ILogParserListener;
import cz.martinbayer.logparser.logic.LogParserPhase;

public class LogbackParserListener implements ILogParserListener {
	private ConcreteE4LogsisLog object;
	private E4LogsisLogData<ConcreteE4LogsisLog> logData;
	private LogbackInputProcessor inputProcessor;

	public LogbackParserListener(LogbackInputProcessor inputProcessor,
			E4LogsisLogData<ConcreteE4LogsisLog> logData) {
		this.inputProcessor = inputProcessor;
		this.logData = logData;
	}

	@Override
	public void parsed(ILogParserEvent event) {
		if (event.getPhase() == LogParserPhase.START) {
			object = new ConcreteE4LogsisLog();
		} else if (event.getPhase() == LogParserPhase.FINISH) {
			logData.addLogRecord(object);
			object = null;
		} else {
			if (object == null || event.getGroupName() == null) {
				throw new NullPointerException("Log event haven't started yet");
			}

			switch (event.getGroupName()) {
			case DateTimePattern.GROUP_NAME:
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
			case ExceptionPattern.GROUP_NAME_EXCEPTION:
				object.setErrorMessage(event.getGroupValue());
				break;
			case FilePattern.GROUP_NAME:
				object.setFileName(event.getGroupValue());
				break;
			case LevelPattern.GROUP_NAME:
				object.setLogLevel(ELogLevel.valueOf(event.getGroupValue()));
				break;
			case LinePattern.GROUP_NAME:
				object.setLine(Long.parseLong(event.getGroupValue().trim()));
				break;
			case MessagePattern.GROUP_NAME_MESSAGE:
				object.setMessage(event.getGroupValue());
				break;
			case ThreadPattern.GROUP_NAME:
				object.setThreadName(event.getGroupValue());
				break;
			default:
				break;
			}
		}

	}

	public E4LogsisLogData<ConcreteE4LogsisLog> getData() {
		return logData;
	}
}
