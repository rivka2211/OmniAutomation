package publisher;

import lombok.Synchronized;
import observer.Observer;
import reportItem.ReportEntry;

import java.util.ArrayList;
import java.util.List;

public final class ReportPublisher implements Publisher{
    private static ReportPublisher instance;
    private final List<Observer> observers;


    private ReportPublisher() {
        this.observers = new ArrayList<>();
    }

    @Synchronized
    public static ReportPublisher getInstance() {
       if (instance==null)
           instance=new ReportPublisher();
        return instance;
    }

    @Override
    public void register(Observer o) {
        if(observers.contains(o))
            return;
        this.observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        if(!this.observers.contains(o))
            return;
        this.observers.remove(o);
    }

    @Override
    public void notifyObserver(ReportEntry reportEntry) {
        for(Observer observer:observers)
            observer.update(reportEntry);
    }
}
