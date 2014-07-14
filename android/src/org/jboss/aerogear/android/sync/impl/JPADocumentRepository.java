/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jboss.aerogear.android.sync.impl;

import com.google.common.base.Strings;
import org.jboss.aerogear.android.datamanager.Store;
import org.jboss.aerogear.android.sync.Repository;
import org.jboss.aerogear.android.sync.SynchronizationException;
import org.jboss.aerogear.android.sync.document.JPADocument;

/**
 *
 * @author summers
 * @param <T> an extension of JPA Document
 */
public class JPADocumentRepository<T extends JPADocument> implements Repository<T>{

    private final Store<T> store;

    public JPADocumentRepository(Store<T> store) {
        this.store = store;
    }

    /**
     * In order to merge a document it must have an ID, a Revision, Content, and
     * the ParentRevision must match the Revision of the currently stored
     * document (if any)
     *
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean canMerge(T remoteDocument) {

        if (Strings.isNullOrEmpty(remoteDocument.getId())) {
            return false;
        }

        T localDocument = store.read(remoteDocument.getId());
        if (localDocument == null) {
            return true;
        } else if (localDocument.getVersion() < (remoteDocument.getVersion())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void merge(T remoteDocument) throws SynchronizationException {
        if (canMerge(remoteDocument)) {
            store.save(remoteDocument);
        } else {
            if (Strings.isNullOrEmpty(remoteDocument.getId())) {
                throw new SynchronizationException("Could not complete merge.  Document is missing ID field", null, remoteDocument);
            } else {
                throw new SynchronizationException("Could not complete merge", store.read(remoteDocument.getId()), remoteDocument);
            }
        }
    }

    @Override
    public void force(T remoteData) {
        store.save(remoteData);
    }

    
}
