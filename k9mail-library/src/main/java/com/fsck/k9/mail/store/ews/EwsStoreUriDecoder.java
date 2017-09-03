package com.fsck.k9.mail.store.ews;


import java.net.URI;
import java.net.URISyntaxException;

import com.fsck.k9.mail.AuthType;
import com.fsck.k9.mail.ConnectionSecurity;
import com.fsck.k9.mail.ServerSettings;
import com.fsck.k9.mail.ServerSettings.Type;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;


public class EwsStoreUriDecoder {
    /**
     * Decodes an EwsStore URI.
     *
     * <p>Possible forms:</p>
     * <pre>
     * ews://auth:user:password@server:port/ ConnectionSecurity.NONE
     * ews+https+://auth:user:password@server:port ConnectionSecurity.SSL_TLS_REQUIRED
     * </pre>
     *
     * Example encoded URL
     *
     * ews+https://abad@outlook.com:fuzzyBoots:outlook.office365.com:443/EWS/Exchange.asmx
     *
     * Resulting Ews Store details
     * Endpoint: https://outlook.office365.com/EWS/Exchange.asmx
     * User: abad@outlook.com
     * Password: fuzzyBoots
     *
     * @param uri the store uri.
     */
    public static EwsStoreSettings decode(String uri) {

        URI ewsUri;
        ConnectionSecurity connectionSecurity;
        String host;
        int port;
        String username;
        String password;
        String path;
        ExchangeVersion exchangeVersion;

        try {
            ewsUri = new URI(uri);
        } catch (URISyntaxException use) {
            throw new IllegalArgumentException("Invalid ImapStore URI", use);
        }

        String scheme = ewsUri.getScheme();
        if (scheme.equals("ews")) {
            connectionSecurity = ConnectionSecurity.NONE;
            port = Type.EWS.defaultPort;
        } else if (scheme.startsWith("imap+tls")) {
            connectionSecurity = ConnectionSecurity.SSL_TLS_REQUIRED;
            port = Type.EWS.defaultTlsPort;
        } else {
            throw new IllegalArgumentException("Unsupported protocol (" + scheme + ")");
        }

        host = ewsUri.getHost();

        if (ewsUri.getPort() != -1) {
            port = ewsUri.getPort();
        }

        String[] userInfoParts = ewsUri.getUserInfo().split(":");

        username = userInfoParts[0];
        password = userInfoParts[1];

        path = ewsUri.getPath();
        exchangeVersion = ExchangeVersion.Exchange2010_SP2;


        return new EwsStoreSettings(host, port, path, ConnectionSecurity.SSL_TLS_REQUIRED, AuthType.AUTOMATIC,
                username, password, null, exchangeVersion);

    }
}
