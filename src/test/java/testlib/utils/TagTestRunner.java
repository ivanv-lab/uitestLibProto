package testlib.utils;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class TagTestRunner {

    public static void main(String... args){
        List<String> tags= Arrays.asList("pb-ui-1","pb-ui-2");
        executeTestsByTags(tags);
    }

    public static void executeTestsByTags(List<String> tags){
        for(String tag:tags){
            LauncherDiscoveryRequest request= LauncherDiscoveryRequestBuilder
                    .request().selectors(DiscoverySelectors.selectPackage("tests"))
                    .filters(org.junit.platform.launcher.TagFilter.includeTags(tag))
                    .build();

            Launcher launcher=LauncherFactory.create();
            SummaryGeneratingListener listener=new SummaryGeneratingListener();
            launcher.registerTestExecutionListeners(listener);
            launcher.execute(request);

            System.out.println("\nResults for tag: "+tag);
            listener.getSummary().printTo(new PrintWriter(System.out));
        }
    }

    static class SummaryGeneratingListener implements org.junit.platform.launcher.TestExecutionListener{
        private final org.junit.platform.launcher.listeners.SummaryGeneratingListener delegate
                =new org.junit.platform.launcher.listeners.SummaryGeneratingListener();

        @Override
        public void executionStarted(TestIdentifier testIdentifier){
            delegate.executionStarted(testIdentifier);
        }

        @Override
        public void executionFinished(TestIdentifier testIdentifier,
                                      TestExecutionResult testExecutionResult){
            delegate.executionFinished(testIdentifier,testExecutionResult);
        }

        public TestExecutionSummary getSummary(){
            return delegate.getSummary();
        }
    }
}
