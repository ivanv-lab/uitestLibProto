package testlib.utils;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestStatus {
    private static final ExtensionContext.Namespace NAMESPACE=
            ExtensionContext.Namespace.create(TestStatus.class);

    public static boolean isTestFailed(ExtensionContext context){
        return context.getStore(NAMESPACE)
                .getOrDefault("failed", Boolean.class,false);
    }

    static AfterTestExecutionCallback callback=context -> {
        if(context.getExecutionException().isPresent()){
            context.getStore(NAMESPACE).put("failed",true);
        }
    };
}
