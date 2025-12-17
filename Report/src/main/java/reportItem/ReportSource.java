package reportItem;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReportSource {
    LOG("log"),
    REPORT("report"),
    OTHER("other");

    private final String reportSource;
}

