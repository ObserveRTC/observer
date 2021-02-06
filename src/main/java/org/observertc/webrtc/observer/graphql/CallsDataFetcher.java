package org.observertc.webrtc.observer.graphql;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.observertc.webrtc.observer.entities.CallEntity;
import org.observertc.webrtc.observer.repositories.CallEntitiesRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;

@Singleton
public class CallsDataFetcher implements DataFetcher<Collection<CallEntity>> {

    @Inject
    CallEntitiesRepository callEntitiesRepository;

    @Override
    public Collection<CallEntity> get(DataFetchingEnvironment env) {
        return this.callEntitiesRepository.getAllEntries().values();
    }
}


