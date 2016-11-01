package com.fsck.k9.mail.store.ews;

import com.fsck.k9.mail.Folder;
import com.fsck.k9.mail.MessagingException;
import com.fsck.k9.mail.ServerSettings;
import com.fsck.k9.mail.ssl.TrustedSocketFactory;
import com.fsck.k9.mail.store.RemoteStore;
import com.fsck.k9.mail.store.StoreConfig;
import com.fsck.k9.mail.store.ews.EwsStoreSettings;
import com.fsck.k9.mail.store.ews.EwsStoreUriCreator;
import com.fsck.k9.mail.store.ews.EwsStoreUriDecoder;

import java.util.List;

public class EwsStore extends RemoteStore {
    public static EwsStoreSettings decodeUri(String uri) {
        return EwsStoreUriDecoder.decode(uri);
    }

    public static String createUri(ServerSettings server) {
        return EwsStoreUriCreator.create(server);
    }


    public EwsStore(StoreConfig storeConfig, TrustedSocketFactory trustedSocketFactory) {
        super(storeConfig, trustedSocketFactory);
    }

    @Override
    public EwsFolder getFolder(String name) {
        return null;
    }

    @Override
    public List<? extends Folder> getPersonalNamespaces(boolean forceListAll) throws MessagingException {
        return null;
    }

    @Override
    public void checkSettings() throws MessagingException {

    }
}
