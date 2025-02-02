package listeners;

import Utilities.FileManager;
import Utilities.Logs;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListeners implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        Logs.info("Suite iniciada %s", suite.getName());
        FileManager.deletePreviousEvidence();

    }

    @Override
    public void onFinish(ISuite suite) {
        Logs.info("Suite finalizada %s", suite.getName());
    }
}
