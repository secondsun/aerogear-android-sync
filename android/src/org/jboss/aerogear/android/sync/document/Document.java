package org.jboss.aerogear.android.sync.document;

import org.jboss.aerogear.android.RecordId;
import org.jboss.aerogear.android.sync.impl.DocumentRepository;

/**
 *
 * A document is a collection of versioned meta-data for some object. Many of
 * these fields will be provided by a third party source (a sync server for
 * instance).
 *
 * For a usage example see {@link DocumentRepository}
 *
 * @param <T> the wrapped data for the document.
 */
public class Document<T> {

    private final T content;

    @RecordId
    private String id;
    private String revision;
    private String parentRevision;

    public Document(T object) {
        this.content = object;
    }

    /**
     * The ID is unique global identifier for the document and its content.
     *
     * @return the current id.
     */
    public String getId() {
        return id;
    }

    /**
     * The ID is unique global identifier for the document and its content.
     *
     * @param id a new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * The revision is a String which identifies the revision of the document
     * and content. This will be generated and provided by a sync server.
     *
     * @return the current revision
     */
    public String getRevision() {
        return revision;
    }

    /**
     * The revision is a String which identifies the revision of the document
     * and content. This will be generated and provided by a sync server.
     *
     * @param revision a new revision
     */
    public void setRevision(String revision) {
        this.revision = revision;
    }

    /**
     * The parent revision is the revision of the content before it was edited.
     * This can be cloned on the client side from the revision field of the
     * current document.
     *
     * @return the parent revision
     */
    public String getParentRevision() {
        return parentRevision;
    }

    
    /**
     * The parent revision is the revision of the content before it was edited.
     * This can be cloned on the client side from the revision field of the
     * current document.
     *
     * @param parentRevision a new parentRevision
     */ 
    public void setParentRevision(String parentRevision) {
        this.parentRevision = parentRevision;
    }

    /**
     * The content is the content which is managed by the document and Repository.
     * 
     * 
     * @return the current content.
     */ 
    public T getContent() {
        return content;
    }

}
