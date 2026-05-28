package library.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import library.handler.BorrowHandler;
import library.handler.BorrowLimitHandler;
import library.handler.ItemIndexHandler;
import library.listener.EventManager;
import library.listener.FineAlertListener;
import library.listener.LogListener;
import library.service.LibraryService;
import library.service.LibraryServiceImpl;

public class LibraryModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(LibraryService.class)
                .to(LibraryServiceImpl.class)
                .in(Scopes.SINGLETON);
    }

    @Provides
    @Singleton
    EventManager provideEventManager() {

        EventManager em = new EventManager();

        em.addListener(new LogListener());
        em.addListener(new FineAlertListener(10));

        return em;
    }

    @Provides
    @Singleton
    BorrowHandler provideBorrowHandlerChain() {

        BorrowHandler limit = new BorrowLimitHandler(null);
        return new ItemIndexHandler(limit);
    }
}