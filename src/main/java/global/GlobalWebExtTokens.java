package global;

import entity.ExtLoginEntity;

import java.util.HashMap;
import java.util.Map;

public class GlobalWebExtTokens {

    private static Map<String, ExtLoginEntity> globalWebExtLoginTokensMap = new HashMap<>();

    public static Map<String, ExtLoginEntity> getGlobalWebExtLoginTokensMap() {
        return globalWebExtLoginTokensMap;
    }

    public static void setGlobalWebExtLoginTokensMap(Map<String, ExtLoginEntity> globalWebExtLoginTokensMap) {
        GlobalWebExtTokens.globalWebExtLoginTokensMap = globalWebExtLoginTokensMap;
    }
}
