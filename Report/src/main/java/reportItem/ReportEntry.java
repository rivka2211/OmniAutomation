package reportItem;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
public class ReportEntry {
    ReportLevel reportLevel;
    ReportSource reportSource;
    @Nullable
    Object additionalObject;
    String msg;
}
