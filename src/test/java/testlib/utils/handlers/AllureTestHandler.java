package testlib.utils.handlers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class AllureTestHandler implements AfterTestExecutionCallback {
    private static final Logger log= LoggerFactory.getLogger(AllureTestHandler.class);

    @Override
    public void afterTestExecution(ExtensionContext context){
        if(context.getExecutionException().isPresent()){
            log.error("Тест '{}' провален. Получаем скриншот...", context.getDisplayName());
            takeScreenshot(context);
        }
    }

    private void takeScreenshot(ExtensionContext context){
        try{
            String screen=context.getDisplayName()
                    .replaceAll("[^a-zA-Z0-9-]", "_");

            byte[] screenBytes= Selenide.screenshot(screen).getBytes();

            Allure.addAttachment("Screenshot on Failure",
                    new ByteArrayInputStream(screenBytes));

        } catch (Exception e){
            log.error("Не удалось получить скриншот",e);
        }
    }
}
