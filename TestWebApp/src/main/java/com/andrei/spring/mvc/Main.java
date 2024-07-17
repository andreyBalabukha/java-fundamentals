package com.andrei.spring.mvc;

import com.andrei.spring.mvc.config.AppConfig;
import com.andrei.spring.mvc.fasade.BookingFacade;
import com.andrei.spring.mvc.model.Event;
import com.andrei.spring.mvc.model.impl.EventImpl;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BookingFacade bookingFacade = context.getBean(BookingFacade.class);

        System.out.println(bookingFacade.getEventById(1721132891));
    }
}
