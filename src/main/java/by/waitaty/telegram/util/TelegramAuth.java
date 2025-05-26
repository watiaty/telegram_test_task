package by.waitaty.telegram.util;

import by.waitaty.telegram.exception.InvalidInitDataException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.apache.commons.codec.digest.HmacUtils;

@UtilityClass
public class TelegramAuth {

    public static String getValidatedData(String telegramInitData, String botToken, Long tokenFreshTime) {
        Pair<String, String> result = parseInitData(telegramInitData);
        String hash = result.getFirst();
        String initData = result.getSecond();
        byte[] secretKey = new HmacUtils("HmacSHA256", "WebAppData").hmac(botToken);
        String initDataHash = new HmacUtils("HmacSHA256", secretKey).hmacHex(initData);

        if (!initDataHash.equals(hash)) {
            throw new InvalidInitDataException("Невалидные данные");
        }

        Map<String, String> initDataMap = parseQueryString(telegramInitData);
        long authDate = Long.parseLong(initDataMap.get("auth_date"));

        if (!isFresh(authDate, tokenFreshTime)) {
            throw new InvalidInitDataException("Данные устарели");
        }

        return initDataMap.get("user");
    }

    private static boolean isFresh(long authDate, Long tokenFreshTime) {
        long now = System.currentTimeMillis() / 1000;
        return (now - authDate) < (60 * tokenFreshTime);
    }

    private static Pair<String, String> parseInitData(String telegramInitData) {
        Map<String, String> initData = parseQueryString(telegramInitData);
        initData = sortMap(initData);
        String hash = initData.remove("hash");

        List<String> separatedData = initData.entrySet().stream()
            .map(v -> v.getKey() + "=" + v.getValue())
            .toList();
        return new Pair<>(hash, String.join("\n", separatedData));
    }

    private static Map<String, String> parseQueryString(String queryString) {
        Map<String, String> parameters = new TreeMap<>();
        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8) : pair;
            String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8) : null;
            parameters.put(key, value);
        }
        return parameters;
    }

    private static Map<String, String> sortMap(Map<String, String> map) {
        return new TreeMap<>(map);
    }

    @Getter
    @AllArgsConstructor
    public static class Pair<F, S> {
        private final F first;
        private final S second;
    }
}