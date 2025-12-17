package observer;

import reportItem.ReportEntry;

public interface Observer {
    void update(ReportEntry reportEntry);
}
