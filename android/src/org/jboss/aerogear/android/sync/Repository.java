package org.jboss.aerogear.android.sync;

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
     * Are there changes in the local data set which need to be synced.
     *
     * @return true if there are changes, false otherwise.
     */
    public boolean hasChanges();

    /**
     *
     * This will combine the remote data and the local data.
     *
     * @param remoteData remote data to apply locally
     * @throws SynchronizationException if the data can not be cleanly merged
     */
    public void merge(T remoteData) throws SynchronizationException;

    /**
     *
     * This will return the difference between the remote data and the local
     * data.
     *
     * @param remoteData the remote data to compare local data to
     * @return the difference between the data sets. Ideally this will be in a
     * form to post to the server but this is not required.
     */
    public T getDelta(T remoteData);

    /**
     * This will do a forced merge of the data. Synchronization errors will be
     * suppressed and the remoteData will overwrite the local data.
     *
     * @param remoteData the remote data
     */
    public void force(T remoteData);

}
