package com.fsck.k9.mail.store.ews;

import com.fsck.k9.mail.AuthType;
import com.fsck.k9.mail.ConnectionSecurity;
import com.fsck.k9.mail.ServerSettings;

public class EwsStoreSettings extends ServerSettings {
    public EwsStoreSettings(Type type, String host, int port, ConnectionSecurity connectionSecurity,
                            AuthType authenticationType, String username, String password,
                            String clientCertificateAlias) {
        super(type, host, port, connectionSecurity, authenticationType, username, password, clientCertificateAlias);
    }
}
