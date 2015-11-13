/**
 * WatchThread
 *
 * Copyright (C) 2012 Sh1fT
 *
 * This file is part of Applic_Mail.
 *
 * Applic_Mail is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * Applic_Mail is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Applic_Mail; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */

package applic_mail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

/**
 * Manage a {@link WatchThread}
 * @author Sh1fT
 */
public class WatchThread extends Thread {
    private MailWatcher parent;

    /**
     * Create a new {@link WatchThread} instance
     * @param parent 
     */
    public WatchThread(MailWatcher parent) {
        this.setParent(parent);
    }

    public MailWatcher getParent() {
        return this.parent;
    }

    public void setParent(MailWatcher parent) {
        this.parent = parent;
    }

    @Override
    public void run() {
        Integer msgCount = 0;
        while(!this.isInterrupted())
        {
            try {
                this.getParent().getWatchProgressBar().setVisible(true);
                this.getParent().pack();
                this.getParent().setFolder(this.getParent().getApplic_Mail()
                    .getStore().getFolder(this.getParent().getApplic_Mail().getFolder()));
                this.getParent().getFolder().open(Folder.READ_ONLY);
                this.getParent().getWatchProgressBar().setValue(20);
                this.getParent().setMessages(this.getParent().getFolder().getMessages());
                this.getParent().getWatchProgressBar().setValue(40);
                this.getParent().getMessageCountLabel().setText(
                    String.valueOf(this.getParent().getFolder().getMessageCount()));
                this.getParent().getWatchProgressBar().setValue(60);
                this.getParent().getMessageNewLabel().setText(
                String.valueOf(this.getParent().getFolder().getMessageCount() - msgCount));
                this.getParent().getWatchProgressBar().setValue(80);
                this.getParent().getMessagesListModel().removeAllElements();
                DateFormat df = SimpleDateFormat.getDateTimeInstance(
                DateFormat.SHORT, DateFormat.SHORT, Locale.FRENCH);
                for (Message m : this.getParent().getMessages())
                    this.getParent().getMessagesListModel().addElement(
                        df.format(m.getSentDate()) + " - " + m.getFrom()[0] +
                        " - " + m.getSubject());
                this.getParent().getWatchProgressBar().setValue(100);
                this.getParent().getWatchProgressBar().setVisible(false);
                this.getParent().getWatchProgressBar().setValue(0);
                this.getParent().pack();
                if (Integer.parseInt(this.getParent().getMessageNewLabel().getText()) > 0)
                    JOptionPane.showMessageDialog(this.getParent().getApplic_Mail(),
                        "De nouveaux messages sont disponibles !",
                        "Youpie !", JOptionPane.INFORMATION_MESSAGE);
                msgCount = this.getParent().getFolder().getMessageCount();
                this.sleep(300000); // vas pioncer 5 minutes
            } catch (MessagingException ex) {
                JOptionPane.showMessageDialog(this.getParent().getApplic_Mail(),
                    ex.getLocalizedMessage(), "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
                this.interrupt();
            } catch (InterruptedException ex) {
            }
        }
    }
}