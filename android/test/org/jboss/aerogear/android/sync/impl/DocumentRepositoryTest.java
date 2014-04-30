package org.jboss.aerogear.android.sync.impl;

import org.jboss.aerogear.android.impl.datamanager.DefaultIdGenerator;
import org.jboss.aerogear.android.impl.datamanager.MemoryStorage;
import org.jboss.aerogear.android.sync.SynchronizationException;
import org.jboss.aerogear.android.sync.document.Document;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
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
        
        //null content
        Assert.assertFalse(repo.canMerge(new Document(null)));
        
        
        Document<String> doc = new Document<String>("Test String");
        
        //No revision and no Id
        Assert.assertFalse(repo.canMerge(doc));
        
        //revision but no Id
        doc.setRevision("Revision-1");
        Assert.assertFalse(repo.canMerge(doc));
        
        doc.setId("1");
        Assert.assertTrue(repo.canMerge(doc));
    }

    /**
     * Test merge
     * 
     * To be merged an Document has to have a id, content, and revision.
     *
     * @throws SynchronizationException if the merge fails. Which is won't since
     * this is a test
     */
    @Test
    public void testMerge() throws SynchronizationException {
        Document<String> document = new Document<String>("Test String");
        document.setId("1");
        document.setRevision("42");
        
        repo.merge(document);

        Document<String> storedDocument = store.read("1");
        
        assertFalse(document == storedDocument); //Merge should not change the local document.
        
    }

    /**
     * Test that an trying to merge the same document twice will fail.
     */
    @Test
    public void testWontMergeCurrentRevision() throws SynchronizationException {
        Document<String> document = new Document<String>("Test String");
        document.setId("1");
        document.setRevision("42");
        
        assertTrue(repo.canMerge(document));
        repo.merge(document);
        Assert.assertFalse(repo.canMerge(document));
    }

    /**
     * Test that a merge with a proper parent/child will happen
     */
    @Test
    public void testCanMergeChildDocument() throws SynchronizationException {
        Document<String> document = new Document<String>("Test String");
        document.setId("1");
        document.setRevision("42");
        repo.merge(document);
        document.setParentRevision("42");
        document.setRevision("420");
        assertTrue(repo.canMerge(document));
        repo.merge(document);
        
    }
    
    @Test
    public void testSyncronizationException() {
    }
    
}
