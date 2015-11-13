/**
 * Applic_Mail
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

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import utils.PropertiesLauncher;

/**
 * Manage a {@link Applic_Mail}
 * @author Sh1fT
 */
public class Applic_Mail extends JFrame {
    private PropertiesLauncher propertiesLauncher;
    private Properties properties;
    private Session session;
    private MimeMessage mimeMessage;
    private Store store;
    private Login login;
    private MailSender mailSender;
    private MailWatcher mailWatcher;

    /**
     * Create a new {@link Applic_Mail} instance
     */
    public Applic_Mail() {
        try {
            this.initComponents();
            this.setPropertiesLauncher(new PropertiesLauncher(
                System.getProperty("file.separator") + "properties" +
                System.getProperty("file.separator") + "Applic_Mail.properties"));
            this.setProperties(new Properties());
            this.getProperties().put("mail.smtp.host", this.getSmtpHost());
            this.getProperties().put("file.encoding", this.getCharset());
            this.setSession(Session.getDefaultInstance(this.getProperties(), null));
            this.setMimeMessage(new MimeMessage(this.getSession()));
            this.setStore(this.getSession().getStore(this.getProtocol()));
            this.setLogin(new Login(this, true));
            this.setMailSender(new MailSender(this, true));
            this.setMailWatcher(new MailWatcher(this, true));
        } catch (NoSuchProviderException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public PropertiesLauncher getPropertiesLauncher() {
        return this.propertiesLauncher;
    }

    public void setPropertiesLauncher(PropertiesLauncher propertiesLauncher) {
        this.propertiesLauncher = propertiesLauncher;
    }

    public Properties getProperties() {
        return this.properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Session getSession() {
        return this.session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public MimeMessage getMimeMessage() {
        return this.mimeMessage;
    }

    public void setMimeMessage(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
    }

    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Login getLogin() {
        return this.login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public MailSender getMailSender() {
        return this.mailSender;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public MailWatcher getMailWatcher() {
        return this.mailWatcher;
    }

    public void setMailWatcher(MailWatcher mailWatcher) {
        this.mailWatcher = mailWatcher;
    }

    public Properties getProps() {
        return this.getPropertiesLauncher().getProperties();
    }

    public String getSmtpHost() {
        return this.getProps().getProperty("smtpHost");
    }

    public String getPopHost() {
        return this.getProps().getProperty("popHost");
    }

    public String getPopUser() {
        return this.getLogin().getUsername();
    }

    public String getPopPassword() {
        return this.getLogin().getPassword();
    }

    public String getFrom() {
        return this.getProps().getProperty("from");
    }

    public String getCharset() {
        return this.getProps().getProperty("charset");
    }

    public String getProtocol() {
        return this.getProps().getProperty("protocol");
    }

    public String getFolder() {
        return this.getProps().getProperty("folder");
    }

    public String getSave() {
        return this.getProps().getProperty("save");
    }

    public JMenuItem getRecupererMailMenuItem() {
        return this.recupererMailMenuItem;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        quitterMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        envoyerMailMenuItem = new javax.swing.JMenuItem();
        recupererMailMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Applic_Mail");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jMenu1.setText("Fichier");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Se connecter");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Se déconnecter");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator1);

        quitterMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        quitterMenuItem.setText("Quitter");
        quitterMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitterMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(quitterMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Mail");

        envoyerMailMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        envoyerMailMenuItem.setText("Envoyer des mail");
        envoyerMailMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envoyerMailMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(envoyerMailMenuItem);

        recupererMailMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        recupererMailMenuItem.setText("Récupérer des mail");
        recupererMailMenuItem.setEnabled(false);
        recupererMailMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recupererMailMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(recupererMailMenuItem);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void quitterMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitterMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitterMenuItemActionPerformed

    private void envoyerMailMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_envoyerMailMenuItemActionPerformed
        this.getMailSender().setVisible(true);
    }//GEN-LAST:event_envoyerMailMenuItemActionPerformed

    private void recupererMailMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recupererMailMenuItemActionPerformed
        this.getMailWatcher().setVisible(true);
    }//GEN-LAST:event_recupererMailMenuItemActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.jMenuItem1ActionPerformed(null);
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.getLogin().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            this.getStore().close();
            this.getRecupererMailMenuItem().setEnabled(false);
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(),
                "Aïe Aïe Aïe !", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
            System.exit(1);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Applic_Mail().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem envoyerMailMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem quitterMenuItem;
    private javax.swing.JMenuItem recupererMailMenuItem;
    // End of variables declaration//GEN-END:variables
}