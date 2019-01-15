package utils;

import java.util.ResourceBundle;

public class OMConfig {
    private static final String transferUrl;
    private static final int listenPort;

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("om_config");

    static {
        transferUrl = resourceBundle.getString("om.transferUrl");
        listenPort = Integer.valueOf(resourceBundle.getString("om.listenPort"));
    }

    public static String getTransferUrl(){
        return transferUrl;
    }

    public static int getListenPort(){
        return listenPort;
    }

}
