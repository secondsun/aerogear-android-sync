package org.jboss.aerogear.android.sync.impl;

import org.jboss.aerogear.android.impl.datamanager.DefaultIdGenerator;
import org.jboss.aerogear.android.impl.datamanager.MemoryStorage;
import org.jboss.aerogear.android.sync.document.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DocumentRepositoryTest {

    DocumentRepository<String> repo;
    
    @Before
    public void setUp() {
        repo = new DocumentRepository<String>(new MemoryStorage<Document<String>>(new DefaultIdGenerator()));
    }
 
    /**
     * Test that an empty repository will take any document.
     */
    @Test
    public void testEmptyCanMerge() {
        boolean result = repo.canMerge(new Document<String>("Test String"));
        Assert.assertTrue(result);
    }
    
}
