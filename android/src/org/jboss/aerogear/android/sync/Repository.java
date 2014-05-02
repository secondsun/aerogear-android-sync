package org.jboss.aerogear.android.sync;

/**
 * A repository is a class which manages the state of synchronizable objects.
 * Repositories should check objects sync status and make sure that operations
 * won't lead to conflicts or incorrect data. They do not perform storage or
 * retrieval operations on their own, but delegate to Stores, Pipes, etc where
 * appropriate.
 *
 *
 * @param <T> The Object type that this repository manages.
 */
public interface Repository<T> {

    /**
     * Will check the local data against the supplied remote data and determine
     * if a clean merge is possible
     *
     * @param remoteData the remote data to test
     * @return true if a successful merge is possible.
     */
    public boolean canMerge(T remoteData);

    /**
     *
     * This will combine the remote data and the local data.
     *
     * @param remoteData remote data to apply locally
     * @throws SynchronizationException if the data can not be cleanly merged
     */
    public void merge(T remoteData) throws SynchronizationException;

    /**
     * This will do a forced merge of the data. Synchronization errors will be
     * suppressed and the remoteData will overwrite the local data.
     *
     * @param remoteData the remote data
     */
    public void force(T remoteData);

}
