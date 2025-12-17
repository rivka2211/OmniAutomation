package publisher;

import observer.Observer;
import reportItem.ReportEntry;

public interface Publisher {
    void register(Observer o);

    void unregister(Observer o);

    void notifyObserver(ReportEntry reportEntry);
}
