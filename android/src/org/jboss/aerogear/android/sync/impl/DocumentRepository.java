package org.jboss.aerogear.android.sync.impl;


import org.jboss.aerogear.android.datamanager.Store;
import org.jboss.aerogear.android.sync.Repository;
import org.jboss.aerogear.android.sync.SynchronizationException;
import org.jboss.aerogear.android.sync.document.Document;

/**
 * This repository manages data which has been wrapped in a "Document" object.
 * 
 */
public class DocumentRepository<T> implements Repository<Document<T>>{

    private final Store<Document<T>> store;

    public DocumentRepository(Store<Document<T>> store) {
        this.store = store;
    }
    
    @Override
    public boolean canMerge(Document<T> remoteDocument) {
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
    public void merge(Document<T> remoteData) throws SynchronizationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
