// src/main/java/runner/LoopTestRunner.java  (or put under src/test/java)
package runner;

import org.testng.TestNG;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoopTestRunner {

    // change this if you want N runs instead of infinite
    private static final boolean RUN_FOREVER = true;
    private static final int MAX_RUNS = 12; // ignored if RUN_FOREVER=true
    private static final long SLEEP_MS = 60L * 60L * 1000L; // 1 hour

    public static void main(String[] args) throws InterruptedException {
        int run = 0;

        while (RUN_FOREVER || run < MAX_RUNS) {
            run++;
            String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            System.out.println("===== Run #" + run + " starting at " + stamp + " =====");

            TestNG testng = new TestNG();
            // point to YOUR test class
            testng.setTestClasses(new Class[] {
                    tests.AdobeEsign.Sprint1.Esign_Agreement_PDF_Attachment_Flow.class
            });

            // write each run’s reports to a unique folder (prevents overwriting)
            testng.setOutputDirectory("test-output/run-" + stamp);

            try {
                testng.run();
            } catch (Throwable t) {
                // don’t let one failure kill the loop
                t.printStackTrace();
            }

            System.out.println("===== Run #" + run + " finished. Sleeping 1 hour... =====");
            Thread.sleep(SLEEP_MS);
        }
        System.out.println("All scheduled runs completed.");
    }
}
