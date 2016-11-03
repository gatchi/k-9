package com.fsck.k9.mail.store.ews;

import com.fsck.k9.mail.Folder;
import com.fsck.k9.mail.Message;
import com.fsck.k9.mail.MessagingException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.filter.ElementFilter;
import org.jdom2.util.IteratorIterable;

public abstract class EwsFolder extends Folder<Message> {
    private String folderId;

    @Override
    public int getUnreadMessageCount() throws MessagingException {
        return Integer.parseInt(getFolderData().next().getChild("").getText());
    }

    private IteratorIterable<Element> getFolderData() {
        String folderData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
                "   xmlns:t=\"http://schemas.microsoft.com/exchange/services/2006/types\">\n" +
                "<soap:Header>\n" +
                "    <t:RequestServerVersion Version=\"Exchange2007_SP1\" />\n" +
                "  </soap:Header>\n" +
                "  <soap:Body>\n" +
                "    <GetFolder xmlns=\"http://schemas.microsoft.com/exchange/services/2006/messages\"\n" +
                "               xmlns:t=\"http://schemas.microsoft.com/exchange/services/2006/types\">\n" +
                "      <FolderShape>\n" +
                "        <t:BaseShape>Default</t:BaseShape>\n" +
                "      </FolderShape>\n" +
                "      <FolderIds>\n" +
                "        <t:DistinguishedFolderId Id=\""+this.folderId+"\"/>\n" +
                "      </FolderIds>\n" +
                "    </GetFolder>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>\n";

        return doSoapQuery(folderData).getDescendants(new ElementFilter("Folders",
                Namespace.getNamespace("http://schemas.microsoft.com/exchange/services/2006/messages")));
    }

    protected abstract Document doSoapQuery(String folderData);
}
