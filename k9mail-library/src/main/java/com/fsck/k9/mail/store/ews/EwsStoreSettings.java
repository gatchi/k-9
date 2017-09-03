package com.fsck.k9.mail.store.ews;

import com.fsck.k9.mail.AuthType;
import com.fsck.k9.mail.ConnectionSecurity;
import com.fsck.k9.mail.ServerSettings;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;


public class EwsStoreSettings extends ServerSettings {
    final String path;
    final ExchangeVersion exchangeVersion;

    public EwsStoreSettings(String host, int port, String path, ConnectionSecurity connectionSecurity,
                            AuthType authenticationType, String username, String password,
                            String clientCertificateAlias, ExchangeVersion exchangeVersion) {
        super(Type.EWS, host, port, connectionSecurity, authenticationType, username, password, clientCertificateAlias);
        this.path = path;
        this.exchangeVersion = exchangeVersion;
    }
}
