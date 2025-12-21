import org.junit.jupiter.api.Test;
import reportItem.ReportSource;

public class SampleObserverTest {

    @Test
    public void log(){
        System.out.println("8888888888888888");
        Report.info(ReportSource.LOG,"i am working");
        Report.info("**********************");
        Report.error("_________________________");


    }
}
