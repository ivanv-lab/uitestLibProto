package testlib.utils;

import testlib.base.UIHandler;
import testlib.base.UIOperationsInterface;

public class AuthKeeper {
    private static volatile long lastActivityTime=0;
    private static final int SESSION_TIMEOUT=25*60*1000;
    private static UIOperationsInterface ui=new UIHandler();

    public static void touchSession(String url,
                                    String login,String address){
        if(System.currentTimeMillis()-lastActivityTime>SESSION_TIMEOUT/2)
            refreshSession( url,
                     login, address);
        lastActivityTime=System.currentTimeMillis();
    }

    private static synchronized void refreshSession(String url,
                                                    String login,String password){
        ui
                .login(url, login, password)
                .openSidebar();
    }

}
