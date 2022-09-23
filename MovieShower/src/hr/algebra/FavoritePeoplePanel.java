/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.Person;
import hr.algebra.model.Person.TypeOfPerson;
import hr.algebra.model.PersonEditable;
import hr.algebra.model.PersonTransferable;
import hr.algebra.utils.MessageUtils;
import java.awt.Frame;
import java.awt.Window;
import java.awt.datatransfer.Transferable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

/**
 *
 * @author asim2
 */
public class FavoritePeoplePanel extends javax.swing.JPanel implements PersonEditable {

    private static final String WRONG_OPERATION_ACTOR_MESSAGE = "Please choose an actor.";
    private static final String WRONG_OPERATION = "Wrong operation";
    private static final String WRONG_OPERATION_DIRECTOR_MESSAGE = "Please choose a director.";

    private Repository repository;

    private DefaultListModel<Person> actorsModel;
    private DefaultListModel<Person> directorsModel;

    private DefaultListModel<Person> favoriteActorsModel;
    private DefaultListModel<Person> favoriteDirectorsModel;

    private Person selectedFavoriteActor = null;
    private Person selectedFavoriteDirector = null;

    private Person selectedPerson = null;

    /**
     * Creates new form FavoritePeoplePanel
     */
    public FavoritePeoplePanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lsAllActors = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lsFavoriteActors = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lsFavoriteDirectors = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lsAllDirectors = new javax.swing.JList<>();
        btnRemoveFavoriteDirector = new javax.swing.JButton();
        btnEditActor = new javax.swing.JButton();
        btnEditDirector = new javax.swing.JButton();
        btnRemoveFavoriteActor = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1192, 855));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel1.setText("Actors:");

        lsAllActors.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lsAllActors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lsAllActorsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lsAllActors);

        jLabel2.setText("Favorite actors;");

        lsFavoriteActors.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(lsFavoriteActors);

        jLabel3.setText("Favorite directors:");

        lsFavoriteDirectors.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(lsFavoriteDirectors);

        jLabel4.setText("Directors:");

        lsAllDirectors.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lsAllDirectors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lsAllDirectorsMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(lsAllDirectors);

        btnRemoveFavoriteDirector.setText("Remove favorite director");
        btnRemoveFavoriteDirector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFavoriteDirectorActionPerformed(evt);
            }
        });

        btnEditActor.setText("Edit");
        btnEditActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActorActionPerformed(evt);
            }
        });

        btnEditDirector.setText("Edit");
        btnEditDirector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditDirectorActionPerformed(evt);
            }
        });

        btnRemoveFavoriteActor.setText("Remove favorite actor");
        btnRemoveFavoriteActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFavoriteActorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEditActor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(btnRemoveFavoriteActor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .addComponent(btnRemoveFavoriteDirector, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEditDirector, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditActor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRemoveFavoriteDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemoveFavoriteActor, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        init();
    }//GEN-LAST:event_formComponentShown

    private void btnRemoveFavoriteActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFavoriteActorActionPerformed
        selectedFavoriteActor = lsFavoriteActors.getSelectedValue();
        if (selectedFavoriteActor == null) {
            MessageUtils.showInformationMessage(WRONG_OPERATION, WRONG_OPERATION_ACTOR_MESSAGE);
            return;
        }
        try {
            repository.removeFromFavoriteActors(selectedFavoriteActor.getId());
            loadListModels();
        } catch (Exception ex) {
            Logger.getLogger(FavoritePeoplePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRemoveFavoriteActorActionPerformed


    private void btnRemoveFavoriteDirectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFavoriteDirectorActionPerformed
        selectedFavoriteDirector = lsFavoriteDirectors.getSelectedValue();
        if (selectedFavoriteDirector == null) {
            MessageUtils.showInformationMessage(WRONG_OPERATION, WRONG_OPERATION_DIRECTOR_MESSAGE);
            return;
        }
        try {
            repository.removeFromFavoriteDirectors(selectedFavoriteDirector.getId());
            loadListModels();
        } catch (Exception ex) {
            Logger.getLogger(FavoritePeoplePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRemoveFavoriteDirectorActionPerformed


    private void btnEditActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActorActionPerformed
        Window parent = SwingUtilities.windowForComponent(this);
        selectedPerson = lsAllActors.getSelectedValue();
        if (selectedPerson == null) {
            MessageUtils.showInformationMessage(WRONG_OPERATION, WRONG_OPERATION_ACTOR_MESSAGE);
            return;
        }
        new EditPersonDialog((Frame) parent, false, this, selectedPerson, TypeOfPerson.ACTOR).setVisible(true);

    }//GEN-LAST:event_btnEditActorActionPerformed

    private void btnEditDirectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditDirectorActionPerformed
        Window parent = SwingUtilities.windowForComponent(this);
        selectedPerson = lsAllDirectors.getSelectedValue();
        if (selectedPerson == null) {
            MessageUtils.showInformationMessage(WRONG_OPERATION, WRONG_OPERATION_DIRECTOR_MESSAGE);
            return;
        }
        new EditPersonDialog((Frame) parent, false, this, selectedPerson, TypeOfPerson.DIRECTOR).setVisible(true);
    }//GEN-LAST:event_btnEditDirectorActionPerformed

    private void lsAllActorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lsAllActorsMouseClicked
        lsAllDirectors.clearSelection();
    }//GEN-LAST:event_lsAllActorsMouseClicked

    private void lsAllDirectorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lsAllDirectorsMouseClicked
        lsAllActors.clearSelection();
    }//GEN-LAST:event_lsAllDirectorsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditActor;
    private javax.swing.JButton btnEditDirector;
    private javax.swing.JButton btnRemoveFavoriteActor;
    private javax.swing.JButton btnRemoveFavoriteDirector;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<Person> lsAllActors;
    private javax.swing.JList<Person> lsAllDirectors;
    private javax.swing.JList<Person> lsFavoriteActors;
    private javax.swing.JList<Person> lsFavoriteDirectors;
    // End of variables declaration//GEN-END:variables

    private void init() {
        try {
            initRepository();
            initModels();
            loadListModels();
            initDragNDrop();
        } catch (Exception ex) {
            Logger.getLogger(FavoritePeoplePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initRepository() throws Exception {
        repository = RepositoryFactory.getRepository();
    }

    private void initModels() {
        actorsModel = new DefaultListModel<>();
        directorsModel = new DefaultListModel<>();
        favoriteActorsModel = new DefaultListModel<>();
        favoriteDirectorsModel = new DefaultListModel<>();
    }

    private void loadListModels() throws Exception {
        loadListModel(repository.getActors().stream().sorted().collect(Collectors.toList()), actorsModel, lsAllActors);
        loadListModel(repository.getDirectors().stream().sorted().collect(Collectors.toList()), directorsModel, lsAllDirectors);
        loadListModel(repository.getFavoriteActors().stream().sorted().collect(Collectors.toList()), favoriteActorsModel, lsFavoriteActors);
        loadListModel(repository.getFavoriteDirectors().stream().sorted().collect(Collectors.toList()), favoriteDirectorsModel, lsFavoriteDirectors);
    }

    private <T> void loadListModel(List<T> data, DefaultListModel<T> model, JList<T> lsData) {
        model.clear();
        data.forEach(model::addElement);
        lsData.setModel(model);
    }

    private void initDragNDrop() {
        initDragNDropActors();
        initDragNDropDirectors();
    }

    private void initDragNDropActors() {
        lsAllActors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsAllActors.setDragEnabled(true);
        lsAllActors.setTransferHandler(new ExportActorsHandler());

        lsFavoriteActors.setDropMode(DropMode.ON);
        lsFavoriteActors.setTransferHandler(new ImportActorsHandler());
    }

    private void initDragNDropDirectors() {
        lsAllDirectors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsAllDirectors.setDragEnabled(true);
        lsAllDirectors.setTransferHandler(new ExportDirectorsHandler());

        lsFavoriteDirectors.setDropMode(DropMode.ON);
        lsFavoriteDirectors.setTransferHandler(new ImportDirectorsHandler());
    }

    @Override
    public boolean editPerson(Person person, TypeOfPerson personType, Person newDetails) throws Exception {

        switch (personType) {
            case ACTOR:
                if (!repository.checkIfActorNameTaken(newDetails)) {
                    person.setFirstName(newDetails.getFirstName());
                    person.setLastName(newDetails.getLastName());
                    repository.editActor(person);
                    loadListModels();
                    return true;
                }
                return false;
            case DIRECTOR:
                if (!repository.checkIfDirectorNameTaken(newDetails)) {
                    person.setFirstName(newDetails.getFirstName());
                    person.setLastName(newDetails.getLastName());
                    repository.editDirector(person);
                    loadListModels();
                    return true;
                }
                return false;

        }
        return false;
    }

    private class ExportDirectorsHandler extends TransferHandler {

        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new PersonTransferable(lsAllDirectors.getSelectedValue());
        }
    }

    private class ImportDirectorsHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(PersonTransferable.PERSON_FLAVOR);
        }

        @Override
        public boolean importData(TransferSupport support) {
            Transferable transferable = support.getTransferable();
            try {
                Person data = (Person) transferable.getTransferData(PersonTransferable.PERSON_FLAVOR);
                if (!favoriteDirectorsModel.contains(data) && !actorsModel.contains(data)) {
                    repository.addFavoriteDirector(data.getId());
                    loadListModels();
                    return true;
                }
            } catch (Exception ex) {
                Logger.getLogger(FavoritePeoplePanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            return false;
        }
    }

    private class ImportActorsHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(PersonTransferable.PERSON_FLAVOR);
        }

        @Override
        public boolean importData(TransferSupport support) {
            Transferable transferable = support.getTransferable();
            try {
                Person data = (Person) transferable.getTransferData(PersonTransferable.PERSON_FLAVOR);
                if (!favoriteActorsModel.contains(data) && !directorsModel.contains(data)) {
                    repository.addFavoriteActor(data.getId());
                    loadListModels();
                    return true;
                }
            } catch (Exception ex) {
                Logger.getLogger(FavoritePeoplePanel.class.getName()).log(Level.SEVERE, null, ex);
            }

            return false;
        }
    }

    private class ExportActorsHandler extends TransferHandler {

        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new PersonTransferable(lsAllActors.getSelectedValue());
        }
    }
}
