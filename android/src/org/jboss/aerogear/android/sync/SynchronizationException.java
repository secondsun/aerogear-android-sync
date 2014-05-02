package org.jboss.aerogear.android.sync;

/**
 * If a merge operation fails, this will include the error and the data involved.
 */
public class SynchronizationException extends Exception {
    
    private final Object localData;
    private final Object remoteData;

    /**
     * 
     * Simple constructor.
     * 
     * @param message a helpful message
     * @param localData data which is being merged into
     * @param remoteData data which could not be merged.
     */
    public SynchronizationException(String message, Object localData, Object remoteData) {
        super(message);
        this.localData = localData;
        this.remoteData = remoteData;
    }

    /**
     * Local data is the data which is persisted and being merged into.
     * 
     * @return the local data
     */
    public Object getLocalData() {
        return localData;
    }

    /**
     * Remote data is the data that could not be merged.
     * 
     * @return the remote data
     */
    public Object getRemoteData() {
        return remoteData;
    }
    
    
    
}
