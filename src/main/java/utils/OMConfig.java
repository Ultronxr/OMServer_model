package utils;

import java.util.ResourceBundle;

public class OMConfig {
    private static final String transferUrl;
    private static final int listenPort;
    private static final String apiPwdReceive;
    private static final String apiPwdSend;

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("om_config");

    static {
        transferUrl = resourceBundle.getString("om.transferUrl");
        listenPort = Integer.valueOf(resourceBundle.getString("om.listenPort"));
        apiPwdReceive = resourceBundle.getString("om.apiPwdReceive");
        apiPwdSend = resourceBundle.getString("om.apiPwdSend");
    }

    public static String getTransferUrl(){
        return transferUrl;
    }

    public static int getListenPort(){
        return listenPort;
    }

    public static String getApiPwdReceive() { return apiPwdReceive; }

    public static String getApiPwdSend() { return apiPwdSend; }

}
