package testlib.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class RetryUtils {
    private static final Logger log= LoggerFactory.getLogger(RetryUtils.class);

    public static <T> T retry(Callable<T> action, int maxAttempts, long delayMs){
        Exception lastError=null;

        for(int i=1;i<=maxAttempts;i++){
            try{
                return action.call();
            } catch (Exception e){
                lastError=e;
                log.warn("Попытка {} провалена {}",i,e.getMessage());
                if(i<maxAttempts){
                    try{
                        Thread.sleep(delayMs);
                    } catch (InterruptedException ie){
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(ie);
                    }
                }
            }
        }
        throw new RuntimeException("Операция провалена после " + maxAttempts + " попыток", lastError);
    }
}
