package listeners;

import Utilities.FileManager;
import Utilities.Logs;
import Utilities.WebDriverProvider;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;

public class AllureListeners implements TestLifecycleListener {

    @Override
    public void beforeTestStop(TestResult result) {
        Logs.debug("Before test stop de allure");
        final var resultType = result.getStatus();

        switch (resultType){
            case BROKEN, FAILED -> {
                if (new WebDriverProvider().get() != null){
                    FileManager.getScreenshot();
                    FileManager.getPageSource();
                }
            }
        }
    }
}
