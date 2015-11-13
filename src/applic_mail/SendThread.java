/**
 * SendThread
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

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 * Manage a {@link SendThread}
 * @author Sh1fT
 */
public class SendThread extends Thread {
    private MailSender parent;

    /**
     * Create a new {@link SendThread} instance
     * @param parent 
     */
    public SendThread(MailSender parent) {
        this.setParent(parent);
    }

    public MailSender getParent() {
        return this.parent;
    }

    public void setParent(MailSender parent) {
        this.parent = parent;
    }

    @Override
    public void run() {
        try {
            this.getParent().getSendProgressBar().setVisible(true);
            this.getParent().pack();
            this.getParent().getSendButton().setEnabled(false);
            this.getParent().getApplic_Mail().getMimeMessage().setFrom(
                new InternetAddress(this.getParent().getApplic_Mail().getPopUser() +
                    "@" + this.getParent().getApplic_Mail().getPopHost()));
            this.getParent().getSendProgressBar().setValue(20);
            this.getParent().getApplic_Mail().getMimeMessage().setRecipient(
                RecipientType.TO, new InternetAddress(this.getParent().getAString()));
            this.getParent().getSendProgressBar().setValue(40);
            this.getParent().getApplic_Mail().getMimeMessage().setSubject(
                this.getParent().getObjet());
            this.getParent().getSendProgressBar().setValue(60);
            Multipart mp = new MimeMultipart();
            MimeBodyPart mbpText = new MimeBodyPart();
            mbpText.setText(this.getParent().getMessage());
            mp.addBodyPart(mbpText);
            this.getParent().getSendProgressBar().setValue(80);
            if (this.getParent().getFileAttachment() != null) {
                MimeBodyPart mbpAtt = new MimeBodyPart();
                mbpAtt.setDataHandler(new DataHandler(new FileDataSource(
                    this.getParent().getFileAttachment())));
                mbpAtt.setFileName(this.getParent().getFileAttachment().getName());
                mp.addBodyPart(mbpAtt);
            }
            this.getParent().getApplic_Mail().getMimeMessage().setContent(mp);
            this.getParent().getSendProgressBar().setValue(100);
            Transport.send(this.getParent().getApplic_Mail().getMimeMessage());
            this.getParent().getSendButton().setEnabled(true);
            this.getParent().getSendProgressBar().setVisible(false);
            this.getParent().getSendProgressBar().setValue(0);
            this.getParent().pack();
            JOptionPane.showMessageDialog(this.getParent(), "Message envoyé avec succès !",
                "Youpie !", JOptionPane.INFORMATION_MESSAGE);
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(this.getParent(), ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
        }
    }
}