package com.appgate.metrics.modules.logs.helpers;

import com.appgate.metrics.modules.logs.constants.EntryTypeEnum;
import static com.appgate.metrics.modules.logs.constants.EntryTypeEnum.*;

/**
 * Entries parsing support helper
 *
 * @author jmunoz
 * @version 1.0
 * @since 2021-04-11
 */
public class parseHelper {
    public static EntryTypeEnum identifyEntry(String entry) {
        if (entry.matches("^.*Failed.*for invalid user.*from.*$"))
            return CONNECTION_FAIL;
        else if (entry.substring(42).matches("^login.*from.*$"))
            return CONNECTION_SUCCESS;
        else if(entry.contains("Client attribute: 'login.client_ip'"))
            return LOGIN_CLIENT_IP;
        else if (entry.contains("Client attribute: 'login.server_ip'"))
            return LOGIN_SERVER_IP;
        else if (entry.contains("Client attribute: 'login.server_name'"))
            return LOGIN_SERVER_NAME;
        else if (entry.contains("Client attribute: 'login.authentication'"))
            return LOGIN_AUTHENTICATION_TYPE;
        else if (entry.contains("Client attribute: 'client.type'"))
            return CLIENT;
        else
            return null;
    }

    public static String getAttribute(String newLine) {
        return newLine.split(" = ")[1]
                .substring(1, newLine.split(" = ")[1].length() - 1);
    }
}
