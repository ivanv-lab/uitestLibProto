package testlib.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class RetryUtils {
    private static final Logger log= LoggerFactory.getLogger(RetryUtils.class);
    private static final int MAX_ATTEMPTS=3;
    private static final long DELAY_MS = 1000;

    public static <T> T retry(Callable<T> action){
        Exception lastError=null;

        for(int i=1;i<=MAX_ATTEMPTS;i++){
            try{
                return action.call();
            } catch (Exception e){
                lastError=e;
                log.warn("Попытка {} провалена {}",i,e.getMessage());
                if(i<MAX_ATTEMPTS){
                    try{
                        Thread.sleep(DELAY_MS);
                    } catch (InterruptedException ie){
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(ie);
                    }
                }
            }
        }
        throw new RuntimeException("Операция провалена после " + MAX_ATTEMPTS + " попыток", lastError);
    }
}
