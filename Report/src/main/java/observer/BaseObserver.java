package observer;

import publisher.ReportPublisher;

public abstract class BaseObserver implements Observer {

    public BaseObserver() {
        ReportPublisher.getInstance().register(this);
    }
}
