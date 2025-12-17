import org.junit.jupiter.api.Test;
import reportItem.ReportSource;

public class SampleObserverClass {

    @Test
    public void log(){
        System.out.println("8888888888888888");
        Report.info(ReportSource.REPORT,"i am working");
        Report.info("**********************");
    }
}
