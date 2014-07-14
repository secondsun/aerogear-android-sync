/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jboss.aerogear.android.sync.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.http.HttpStatus;
import org.jboss.aerogear.android.Callback;
import org.jboss.aerogear.android.http.HeaderAndBody;
import org.jboss.aerogear.android.http.HttpException;
import org.jboss.aerogear.android.impl.pipeline.GsonResponseParser;
import org.jboss.aerogear.android.pipeline.ResponseParser;
import org.jboss.aerogear.android.sync.document.JPADocument;

/**
 *
 * @author summers
 */
public abstract class AbstractJPAConflictAwareCallback<T extends JPADocument> implements Callback<T>{

    private final GsonResponseParser<T> responseHandler;
    private final Class<T> klass;

    public AbstractJPAConflictAwareCallback(GsonResponseParser<T> responseHandler, Class<T> klass) {
        this.responseHandler = responseHandler;
        this.klass = klass;
    }
    
    @Override
    public final void onFailure(Exception e) {
        if (e instanceof HttpException) {
            HttpException httpFailure = (HttpException) e;
            if (HttpStatus.SC_CONFLICT == httpFailure.getStatusCode() ) {
                byte[] data = httpFailure.getData();
                
                List<T> remoteObject = responseHandler.handleResponse(new HeaderAndBody(data, new HashMap<String, Object>()), klass);
                onConflictFailure(remoteObject.get(0));
            } else {
                onOtherFailure(e);
            }
            
        } else {
            onOtherFailure(e);
        }
    }
    
    abstract void onConflictFailure(T remoteData);
    
    abstract void onOtherFailure(Exception e);
    
    
}
