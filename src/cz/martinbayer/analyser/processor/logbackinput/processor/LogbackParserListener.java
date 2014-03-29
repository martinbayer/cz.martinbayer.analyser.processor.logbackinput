package cz.martinbayer.analyser.processor.logbackinput.processor;

import java.util.List;

import cz.martinbayer.analyser.impl.ConcreteData;
import cz.martinbayer.analyser.processors.model.ELogLevel;
import cz.martinbayer.logparser.logback.pattern.TypedPatternFactory;
import cz.martinbayer.logparser.logic.ILogParserEvent;
import cz.martinbayer.logparser.logic.ILogParserListener;
import cz.martinbayer.logparser.logic.LogParserPhase;

public class LogbackParserListener implements ILogParserListener {
	private ConcreteData object;
	private List<ConcreteData> objects;

	@Override
	public void parsed(ILogParserEvent event) {
		if (event.getPhase() == LogParserPhase.START) {
			object = new ConcreteData();
		} else if (event.getPhase() == LogParserPhase.FINISH) {
			objects.add(object);
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
				object.setEventDateTime(event.getGroupValue());
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

}
