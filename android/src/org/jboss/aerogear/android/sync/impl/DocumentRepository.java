package org.jboss.aerogear.android.sync.impl;

import com.google.common.base.Strings;
import org.jboss.aerogear.android.datamanager.Store;
import org.jboss.aerogear.android.sync.Repository;
import org.jboss.aerogear.android.sync.SynchronizationException;
import org.jboss.aerogear.android.sync.document.Document;

/**
 * This repository manages data which has been wrapped in a "Document" object.
 *
 */
public class DocumentRepository<T> implements Repository<Document<T>> {

    private final Store<Document<T>> store;

    public DocumentRepository(Store<Document<T>> store) {
        this.store = store;
    }

    @Override
    /**
     * In order to merge a document it must have an ID, a Revision, Content, and
     * the ParentRevision must match the Revision of the currently stored
     * document (if any)
     *
     */
    public boolean canMerge(Document<T> remoteDocument) {

        if (Strings.isNullOrEmpty(remoteDocument.getRevision())) {
            return false;
        }

        if (remoteDocument.getContent() == null) {
            return false;
        }

        if (Strings.isNullOrEmpty(remoteDocument.getId())) {
            return false;
        }

        Document localDocument = store.read(remoteDocument.getId());
        if (localDocument == null) {
            return true;
        } else if (localDocument.getRevision().equals(remoteDocument.getParentRevision())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasChanges() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void merge(Document<T> remoteDocument) throws SynchronizationException {
        if (canMerge(remoteDocument)) {
            Document<T> copyDoc = new Document<T>(remoteDocument.getContent());
            copyDoc.setId(remoteDocument.getId());
            copyDoc.setParentRevision(remoteDocument.getParentRevision());
            copyDoc.setRevision(remoteDocument.getRevision());
            store.save(copyDoc);
        } else {
            if (Strings.isNullOrEmpty(remoteDocument.getId())) {
                throw new SynchronizationException("Could not complete merge.  Document is missing ID field", null, remoteDocument);
            } else {
                throw new SynchronizationException("Could not complete merge", store.read(remoteDocument.getId()), remoteDocument);
            }
        }
    }

    @Override
    public Document<T> getDelta(Document<T> remoteData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void force(Document<T> remoteData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
