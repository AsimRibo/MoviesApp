/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.movie.Cinema;
import hr.algebra.utils.FileUtils;
import hr.algebra.utils.JAXBUtils;
import hr.algebra.utils.MessageUtils;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author asim2
 */
public class UserContainerForm extends javax.swing.JFrame {

    private Repository repository;
    
    private static final String MOVIES_XML = "Movies.xml";

    private static final String EDIT_MOVIES = "Edit movies";
    private static final String FAVORITE_PEOPLE = "Favorite people";
    
    private static final String CONTACT = "Contact information";
    private static final String CONTACT_MESSAGE = "For any further questions contact me at my email: asim.ribo21@gmail.com";

    private static final String ERROR_DOWNLOAD_MESSAGE = "Unable to download movies currently.";
    private static final String ERROR_DOWNLOAD = "Error";
    
    /**
     * Creates new form MoviesUserContainerForm
     */
    public UserContainerForm() {
        initComponents();
        configureUserPanels();
        handleThemes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpUserContent = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        miDownload = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        miExit = new javax.swing.JMenuItem();
        menuView = new javax.swing.JMenu();
        menuThemes = new javax.swing.JMenu();
        menuHelp = new javax.swing.JMenu();
        miContact = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Movies");
        setPreferredSize(new java.awt.Dimension(1200, 860));

        menuFile.setMnemonic('F');
        menuFile.setText("File");

        miDownload.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        miDownload.setText("Download movies");
        miDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miDownloadActionPerformed(evt);
            }
        });
        menuFile.add(miDownload);
        menuFile.add(jSeparator1);

        miExit.setText("Exit");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        menuFile.add(miExit);

        jMenuBar1.add(menuFile);

        menuView.setMnemonic('V');
        menuView.setText("View");

        menuThemes.setText("Themes");
        menuView.add(menuThemes);

        jMenuBar1.add(menuView);

        menuHelp.setMnemonic('H');
        menuHelp.setText("Help");

        miContact.setText("Contact");
        miContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miContactActionPerformed(evt);
            }
        });
        menuHelp.add(miContact);

        jMenuBar1.add(menuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpUserContent, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpUserContent, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void miDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miDownloadActionPerformed
        try {
            if (repository == null) {
                initRepository();

            }
            FileUtils.saveToXml(new Cinema(repository.getMovies()));
        } catch (Exception ex) {
            Logger.getLogger(UserContainerForm.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage(ERROR_DOWNLOAD, ERROR_DOWNLOAD_MESSAGE);
        }
    }//GEN-LAST:event_miDownloadActionPerformed
    

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
        dispose();
    }//GEN-LAST:event_miExitActionPerformed

    private void miContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miContactActionPerformed
        MessageUtils.showInformationMessage(CONTACT, CONTACT_MESSAGE);
    }//GEN-LAST:event_miContactActionPerformed
    

    /**
     * @param args the command line arguments
     */
    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenu menuThemes;
    private javax.swing.JMenu menuView;
    private javax.swing.JMenuItem miContact;
    private javax.swing.JMenuItem miDownload;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JTabbedPane tpUserContent;
    // End of variables declaration//GEN-END:variables

    private void configureUserPanels() {
        tpUserContent.add(EDIT_MOVIES, new EditMoviesPanel());
        tpUserContent.add(FAVORITE_PEOPLE, new FavoritePeoplePanel());
    }

    private void initRepository() throws Exception {
        repository = RepositoryFactory.getRepository();
    }

    private void handleThemes() {
        ButtonGroup themes = new ButtonGroup();
        Arrays.asList(UIManager.getInstalledLookAndFeels()).forEach(theme -> {
            JRadioButtonMenuItem mi = new JRadioButtonMenuItem(theme.getName());
            menuThemes.add(mi);
            mi.setSelected("Nimbus".equals(theme.getName()));
            themes.add(mi);
            mi.addActionListener(e -> {
                try {
                    UIManager.setLookAndFeel(theme.getClassName());
                    SwingUtilities.updateComponentTreeUI(UserContainerForm.this);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(UserContainerForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        });
    }

}
