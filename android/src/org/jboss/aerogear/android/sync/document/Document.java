package org.jboss.aerogear.android.sync.document;

import org.jboss.aerogear.android.sync.impl.DocumentRepository;

/**
 * 
 * A document is a collection of versioned meta-data for some object.
 * 
 * For a usagge example see {@link DocumentRepository}
 * 
 * @author summers
 * @param <T> the wrapped data for the document.
 */
public class Document<T> {
    
    private final T content;
    
    private String id;
    private String revision;
    private String parentRevision;

    public Document(T object) {
        this.content = object;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getParentRevision() {
        return parentRevision;
    }

    public void setParentRevision(String parentRevision) {
        this.parentRevision = parentRevision;
    }

    public T getContent() {
        return content;
    }
    
    
    
    
}
