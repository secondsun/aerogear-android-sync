package org.jboss.aerogear.android.sync.impl;

import org.jboss.aerogear.android.impl.datamanager.DefaultIdGenerator;
import org.jboss.aerogear.android.impl.datamanager.MemoryStorage;
import org.jboss.aerogear.android.sync.SynchronizationException;
import org.jboss.aerogear.android.sync.document.Document;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class DocumentRepositoryTest {

    private DocumentRepository<String> repo;
    private MemoryStorage<Document<String>> store;

    @Before
    public void setUp() {
        store = new MemoryStorage<Document<String>>(new DefaultIdGenerator());
        repo = new DocumentRepository<String>(store);
    }

    /**
     * Test that an empty repository will take any document.
     */
    @Test
    public void testEmptyCanMerge() {
        boolean result = repo.canMerge(new Document<String>("Test String"));
        Assert.assertTrue(result);
    }

    /**
     * Test merge
     *
     * @throws SynchronizationException if the merge fails. Which is won't since
     * this is a test
     */
    @Test
    public void testMerge() throws SynchronizationException {
        Document<String> document = new Document<String>("Test String");
        document.setId("1");
        
        repo.merge(document);

        assertEquals(document, store.read("1"));
        
    }

    /**
     * Test that an trying to merge the same document twice will fail.
     */
    @Test
    public void testWontMergeCurrentRevision() throws SynchronizationException {
        Document<String> document = new Document<String>("Test String");
        document.setId("1");
        
        repo.canMerge(document);
        repo.merge(document);
        boolean result = repo.canMerge(document);
        Assert.assertFalse(result);
    }
    
}
