package observer;

import lombok.Getter;
import reportItem.ReportEntry;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SampleObserver extends BaseObserver {

    private final List<String> entries;

    public SampleObserver() {
        super();
        entries = new ArrayList<>();
    }

    @Override
    public void update(ReportEntry reportEntry) {
        entries.add("SampleObserver data is " + reportEntry.toString());
    }

}
