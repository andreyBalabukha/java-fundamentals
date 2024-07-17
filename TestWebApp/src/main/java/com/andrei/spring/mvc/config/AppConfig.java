package com.andrei.spring.mvc.config;

import com.andrei.spring.mvc.dao.EventDao;
import com.andrei.spring.mvc.dao.TicketDao;
import com.andrei.spring.mvc.dao.UserDao;
import com.andrei.spring.mvc.fasade.BookingFacade;
import com.andrei.spring.mvc.fasade.BookingFacadeImpl;
import com.andrei.spring.mvc.repository.EventStorage;
import com.andrei.spring.mvc.repository.TicketStorage;
import com.andrei.spring.mvc.repository.UserStorage;
import com.andrei.spring.mvc.service.EventService;
import com.andrei.spring.mvc.service.TicketService;
import com.andrei.spring.mvc.service.UserService;
import com.andrei.spring.mvc.service.impl.EventServiceImpl;
import com.andrei.spring.mvc.service.impl.TicketServiceImpl;
import com.andrei.spring.mvc.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    public AppConfig(
            @Value("${user.path}") String userFilePath,
            @Value("${ticket.path}") String ticketFilePath,
            @Value("${event.path}") String eventFilePath
    ) {
        this.userFilePath = userFilePath;
        this.ticketFilePath = ticketFilePath;
        this.eventFilePath = eventFilePath;

    }

    private final String userFilePath;

    private final String ticketFilePath;

    private final String eventFilePath;

    @Bean
    public EventService eventService() {
        return new EventServiceImpl();
    }

    @Bean
    public TicketService ticketService() {
        return new TicketServiceImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public EventDao eventDao() {
        return new EventDao();
    }

    @Bean
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean
    public TicketDao ticketDao() {
        return new TicketDao();
    }

    @Bean
    public EventStorage eventStorage() {
        return new EventStorage(eventFilePath);
    }

    @Bean
    public UserStorage userStorage() {
        return new UserStorage(userFilePath);
    }

    @Bean
    public TicketStorage ticketStorage() {
        return new TicketStorage(ticketFilePath);
    }

    @Bean
    public BookingFacadeImpl bookingFacade() {
        return new BookingFacadeImpl(eventService(), ticketService(), userService());
    }
}
