package reportItem;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReportLevel {

    TRACE(0),
    DEBUG(1),
    INFO(2),
    WARNING(3),
    ERROR(3),
    FATAL(4);

    private final int reportLevel;
}
