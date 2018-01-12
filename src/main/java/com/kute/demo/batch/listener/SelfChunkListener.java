package com.kute.demo.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

/**
 * Created by kute on 2018/1/12.
 */
@Component
public class SelfChunkListener implements org.springframework.batch.core.ChunkListener {

    private static final Logger logger = LoggerFactory.getLogger(SelfItemWritelistener.class);

    @Override
    public void beforeChunk(ChunkContext chunkContext) {
        logger.info("Chunk beforeChunk:{}", chunkContext.getStepContext().getJobName());
    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {
        logger.info("Chunk afterChunk:{}", chunkContext.getStepContext().getJobName());
    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {
        logger.info("Chunk afterChunkError:{}", chunkContext.getStepContext().getJobName());
    }
}
