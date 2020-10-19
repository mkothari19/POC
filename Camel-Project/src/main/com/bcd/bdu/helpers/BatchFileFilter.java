package com.bcd.bdu.helpers;

import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;

public class BatchFileFilter<T> implements GenericFileFilter<T> {
    private int limit = 1;

    public BatchFileFilter(int limit){
        this.limit = limit;
    }

    public boolean accept(GenericFile<T> files){
        if(files.isDirectory()){
            //if(files.list().length >= limit) return true;
            //else return false;
            return false;
        }else{
            return true;
        }
    }
}

