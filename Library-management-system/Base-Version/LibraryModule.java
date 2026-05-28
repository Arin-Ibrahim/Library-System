package library.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import library.handler.BorrowHandler;
import library.handler.BorrowLimitHandler;
import library.handler.ItemIndexHandler;
import library.listener.EventManager;
import library.listener.FineAlertListener;
import library.listener.LogListener;
import library.service.LibraryService;
import library.service.LibraryServiceImpl;

/**
 * Google Guice module — declares every dependency binding in one place.
 *
 * DESIGN PATTERN: Dependency Injection (via Guice)
 * No class calls "new" on its dependencies; Guice resolves and injects them.
 */
public class LibraryModule extends AbstractModule {

    @Override
    protected void configure() {
        // Bind the service interface to its implementation
        bind(LibraryService.class).to(LibraryServiceImpl.class).in(com.google.inject.Scopes.SINGLETON);
    }

    /**
     * Builds the EventManager and registers all listeners.
     * Adding a new listener only requires a line here — nothing else changes.
     */
    @Provides
    @Singleton
    EventManager provideEventManager() {
        EventManager em = new EventManager();
        em.addListener(new LogListener());
        em.addListener(new FineAlertListener(10)); // alert when fine > $10
        return em;
    }

    /**
     * Assembles the Chain-of-Responsibility for borrow validation.
     * Chain order: ItemIndex → BorrowLimit → (end / approve)
     *
     * To add a new rule, create a new BorrowHandler subclass and insert it here.
     */
    @Provides
    @Singleton
    BorrowHandler provideBorrowHandlerChain() {
        BorrowHandler limitHandler = new BorrowLimitHandler(null);
        return new ItemIndexHandler(limitHandler);
    }
}