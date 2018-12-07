package global;

import entity.ExtLoginEntity;

import java.util.HashMap;
import java.util.Map;

public class GlobalWebExtTokens {

    /**
     * 使用过的token和其相对应的extEntity类
     */
    private static Map<String, ExtLoginEntity> loginTokensMap = new HashMap<>();

    /**
     * 当前在线（已登录）的token和其相对应的extEntity类
     */
    private static Map<String, ExtLoginEntity> onlineTokensMap = new HashMap<>();

    public static Map<String, ExtLoginEntity> getLoginTokensMap() {
        return loginTokensMap;
    }

    public static void setLoginTokensMap(Map<String, ExtLoginEntity> loginTokensMap) {
        GlobalWebExtTokens.loginTokensMap = loginTokensMap;
    }

    public static Map<String, ExtLoginEntity> getOnlineTokensMap() {
        return onlineTokensMap;
    }

    public static void setOnlineTokensMap(Map<String, ExtLoginEntity> onlineTokensMap) {
        GlobalWebExtTokens.onlineTokensMap = onlineTokensMap;
    }
}
