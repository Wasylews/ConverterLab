package com.genius.wasylews.domain.repository;

public interface ProgressListener {
    void update(long bytesRead, long contentLength, boolean done);

}