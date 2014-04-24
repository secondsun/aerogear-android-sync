package org.jboss.aerogear.android.sync;

public class SynchronizationException extends Exception {
    
    private final Object localData;
    private final Object remoteData;

    public SynchronizationException(String message, Object localData, Object remoteData) {
        super(message);
        this.localData = localData;
        this.remoteData = remoteData;
    }

    public Object getLocalData() {
        return localData;
    }

    public Object getRemoteData() {
        return remoteData;
    }
    
    
    
}
