package org.jboss.aerogear.android.sync.document;

import org.jboss.aerogear.android.RecordId;

/**
 * A JPA document uses JPAs @Version annotation on the server side. This keeps
 * track of the versioning on client and server.
 */
public abstract class JPADocument {
    @RecordId
    private String id;
    private long version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
    
    
}
