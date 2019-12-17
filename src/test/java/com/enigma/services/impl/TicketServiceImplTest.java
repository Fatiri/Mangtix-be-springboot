//package com.enigma.services.impl;
//
//import com.enigma.entity.Category;
//import com.enigma.entity.Event;
//import com.enigma.entity.Ticket;
//import com.enigma.repositories.TicketRepository;
//import com.enigma.services.CategoryService;
//import com.enigma.services.EventService;
//import com.enigma.services.TicketService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.math.BigDecimal;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class TicketServiceImplTest {
//
//    @Autowired
//    TicketService ticketService;
//    @MockBean
//    TicketRepository ticketRepository;
//    @MockBean
//    CategoryService categoryService;
//    @MockBean
//    EventService eventService;
//
//    @Test
//    public void saveTicket_should_callTicketRepository_save_once(){
//        String categoryName="VIP";
//        String eventName="Ayu Ting-ting Concert";
//        String description="the first concert is Ayu Ting-ting";
//        BigDecimal price = new BigDecimal(750000);
//        Integer quantity=2;
//        Date eventDate=new Date();
//        String eventLocation="JCC";
//        Integer mockitoTimes=1;
//        Category category = new Category(categoryName);
//        categoryService.saveCategory(category);
//        Event event = new Event(eventName,description,eventDate,eventLocation,true);
//        eventService.saveEvent(event);
//        Ticket ticket= new Ticket();
//        ticket.setPrice(price);
//        ticket.setQuantity(quantity);
//        ticket.setCategoryIdTransient(category.getId());
//        ticket.setEventIdTransient(event.getEventId());
//        ticket.setCreateAt(Calendar.getInstance());
//        ticketService.saveTicket(ticket);
//        Mockito.verify(ticketRepository, Mockito.times(mockitoTimes)).save(ticket);
//    }
//
//    @Test
//    public void saveTicket_should_callCategoryService_getCategoryById_once(){
//        String categoryName="VIP";
//        String eventName="Ayu Ting-ting Concert";
//        String description="the first concert is Ayu Ting-ting";
//        BigDecimal price = new BigDecimal(750000);
//        Integer quantity=2;
//        Date eventDate=new Date();
//        String eventLocation="JCC";
//        Integer mockitoTimes=1;
//        Category category = new Category(categoryName);
//        categoryService.saveCategory(category);
//        Event event = new Event(eventName,description,eventDate,eventLocation,true);
//        eventService.saveEvent(event);
//        Ticket ticket= new Ticket();
//        ticket.setPrice(price);
//        ticket.setQuantity(quantity);
//        ticket.setCategoryIdTransient(category.getId());
//        ticket.setEventIdTransient(event.getEventId());
//        ticket.setCreateAt(Calendar.getInstance());
//        ticketService.saveTicket(ticket);
//        Mockito.verify(categoryService, Mockito.times(mockitoTimes)).getCategoryById(ticket.getCategoryIdTransient());
//    }
//    @Test
//    public void saveTicket_should_callEventService_getEventById_once(){
//        String categoryName="VIP";
//        String eventName="Ayu Ting-ting Concert";
//        String description="the first concert is Ayu Ting-ting";
//        BigDecimal price = new BigDecimal(750000);
//        Integer quantity=2;
//        Date eventDate=new Date();
//        String eventLocation="JCC";
//        Integer mockitoTimes=1;
//        Category category = new Category(categoryName);
//        categoryService.saveCategory(category);
//        Event event = new Event(eventName,description,eventDate,eventLocation,true);
//        eventService.saveEvent(event);
//        Ticket ticket= new Ticket();
//        ticket.setPrice(price);
//        ticket.setQuantity(quantity);
//        ticket.setCategoryIdTransient(category.getId());
//        ticket.setEventIdTransient(event.getEventId());
//        ticket.setCreateAt(Calendar.getInstance());
//        ticketService.saveTicket(ticket);
//        Mockito.verify(eventService, Mockito.times(mockitoTimes)).getEventId(ticket.getEventIdTransient());
//    }
//
//    @Test
//    void getTicketById_should_callTicketRepository_findById_once() {
//        String categoryName="VIP";
//        String eventName="Ayu Ting-ting Concert";
//        String description="the first concert is Ayu Ting-ting";
//        BigDecimal price = new BigDecimal(750000);
//        Integer quantity=2;
//        Date eventDate=new Date();
//        String eventLocation="JCC";
//        Integer mockitoTimes=2;
//        String ticketId="815cabfc-f935-4453-8dc3-0534d8c0171b";
//        Category category = new Category(categoryName);
//        categoryService.saveCategory(category);
//        Event event = new Event(eventName,description,eventDate,eventLocation,true);
//        eventService.saveEvent(event);
//        Ticket ticket= new Ticket();
//        ticket.setPrice(price);
//        ticket.setQuantity(quantity);
//        ticket.setCategoryIdTransient(category.getId());
//        ticket.setEventIdTransient(event.getEventId());
//        ticket.setCreateAt(Calendar.getInstance());
//
//        Mockito.when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));
//        ticketService.getTicketById(ticketId);
//        Mockito.verify(ticketRepository, Mockito.times(mockitoTimes)).findById(ticketId);
//    }
//
//    @Test
//    void getAllTicket() {
//    }
//
//    @Test
//    void delete() {
//    }
//}
