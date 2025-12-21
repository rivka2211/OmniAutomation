package observer;

import reportItem.ReportEntry;
import reportItem.ReportLevel;

public class LoggerObserver extends BaseObserver {

    private final System.Logger logger;

    public LoggerObserver() {
        super();
        this.logger =  System.getLogger(LoggerObserver.class.getName());;
    }

    @Override
    public void update(ReportEntry re) {
        if (re == null) {
            return;
        }
        System.out.println("LoggerObserver.update called for: " + re.getMsg());

        System.Logger.Level level = getLevel(re);
        Object additional = re.getAdditionalObject();

        if (additional != null) {
            logger.log(level, additional);
        }
        logger.log(level, re.getMsg());
    }

    private System.Logger.Level getLevel(ReportEntry entry) {
        ReportLevel level = entry.getReportLevel();
        if (level == null) {
            return System.Logger.Level.INFO;
        }

        return switch (level) {
            case TRACE -> System.Logger.Level.TRACE;
            case DEBUG -> System.Logger.Level.DEBUG;
            case WARNING -> System.Logger.Level.WARNING;
            case ERROR, FATAL -> System.Logger.Level.ERROR;
            default -> System.Logger.Level.INFO;
        };
    }

}
