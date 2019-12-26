package com.enigma.services.impl;

import com.enigma.constanta.BookingConstant;
import com.enigma.constanta.EventConstanta;
import com.enigma.constanta.MessageConstant;
import com.enigma.constanta.StringConstant;
import com.enigma.entity.*;
import com.enigma.repositories.BookingRepository;
import com.enigma.repositories.UserRepository;
import com.enigma.services.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookingServiceImplTest {

    @Autowired
    BookingService bookingService;

    @MockBean
    BookingRepository bookingRepository;

    @MockBean
    TicketService ticketService;

    @MockBean
    CategoryService categoryService;

    @MockBean
    EventService eventService;

    @MockBean
    CompanyService companyService;

    @MockBean
    UserService userService;

    @MockBean
    RoleService roleService;

    @MockBean
    LocationService locationService;

    @Test
    void booking_should_callBookingRepository_save_once() {
       String roleName = "ADMIN";
       String description = "create as ADMIN";
       Role role = new Role();
       role.setRoleName(roleName);
       role.setDescription(description);
       roleService.saveRole(role);

       String userName = "MangTix";
       String password = "MangTix";
       String fullName = "Mang Ticket";
       String bornPlace = "Jakarta Barat";
       Date birthDate =  new Date();
       Date createAt = new Date();
       User user = new User();
       user.setUserName(userName);
       user.setPassword(password);
       user.setFullName(fullName);
       user.setBornPlace(bornPlace);
       user.setBirthDate(birthDate);
       user.setCreateAt(createAt);
       user.setRoleIdTransient(role.getId());
       userService.saveUser(user);

       String companyName = "MangTix";
       Company company = new Company();
       company.setCompanyName(companyName);
       companyService.saveCompany(company);

        String venue = "loyal garden";
        String descriptionEventDetail = "mangtik";
        String eventDay = "day 1";
        Date eventDate = new Date();
        EventDetail eventDetail = new EventDetail();
        eventDetail.setVenue(venue);
        eventDetail.setDescription(descriptionEventDetail);
        eventDetail.setEventDay(eventDay);
        eventDetail.setEventDate(eventDate);
        List<EventDetail> eventDetails = new ArrayList<>();
        eventDetails.add(eventDetail);

        String eventName = "MangTix Event";
        String descriptionEvent = "Party with fatiri";
        Boolean publishStatus = true;
        Event event = new Event();
        event.setEventName(eventName);
        event.setDescriptionEvent(descriptionEvent);
        event.setPublishStatus(publishStatus);
        event.setEventDetailList(eventDetails);
        event.setCompany(company);
        eventService.saveEvent(event);

        String categoryName = "vip";
        Category category = new Category();
        category.setCategoryName(categoryName);


       BigDecimal price = new BigDecimal(100000);
       Integer quantity = 1;
       Ticket ticket = new Ticket();
       ticket.setPrice(price);
       ticket.setQuantity(quantity);
       ticket.setCategory(category);
       ticketService.saveTicket(ticket);

       Integer quantityBookingDetail = 2;
       BookingDetail bookingDetail = new BookingDetail();
       bookingDetail.setQuantity(quantityBookingDetail);
       bookingDetail.setTicketIdTransient(ticket.getId());
       bookingDetail.setTicket(ticket);
       List<BookingDetail> bookingDetails = new ArrayList<>();

       Date bookDate = new Date();
       Booking booking = new Booking();
       booking.setBookDate(bookDate);
       booking.setBookingDetailList(bookingDetails);
       ticketService.saveTicket(ticket);
       bookingService.booking(booking);

       Integer mockitoTimes = 1;

       Mockito.verify(bookingRepository, Mockito.times(mockitoTimes)).save(booking);
    }

    @Test
    void getAllBookingData() {
        String roleName = "ADMIN";
        String description = "create as ADMIN";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleService.saveRole(role);

        String userName = "MangTix";
        String password = "MangTix";
        String fullName = "Mang Ticket";
        String bornPlace = "Jakarta Barat";
        Date birthDate =  new Date();
        Date createAt = new Date();
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setBornPlace(bornPlace);
        user.setBirthDate(birthDate);
        user.setCreateAt(createAt);
        user.setRoleIdTransient(role.getId());
        userService.saveUser(user);

        String companyName = "MangTix";
        Company company = new Company();
        company.setCompanyName(companyName);
        companyService.saveCompany(company);

        String venue = "loyal garden";
        String descriptionEventDetail = "mangtik";
        String eventDay = "day 1";
        Date eventDate = new Date();
        EventDetail eventDetail = new EventDetail();
        eventDetail.setVenue(venue);
        eventDetail.setDescription(descriptionEventDetail);
        eventDetail.setEventDay(eventDay);
        eventDetail.setEventDate(eventDate);
        List<EventDetail> eventDetails = new ArrayList<>();
        eventDetails.add(eventDetail);

        String eventName = "MangTix Event";
        String descriptionEvent = "Party with fatiri";
        Boolean publishStatus = true;
        Event event = new Event();
        event.setEventName(eventName);
        event.setDescriptionEvent(descriptionEvent);
        event.setPublishStatus(publishStatus);
        event.setEventDetailList(eventDetails);
        event.setCompany(company);
        eventService.saveEvent(event);

        String categoryName = "vip";
        Category category = new Category();
        category.setCategoryName(categoryName);


        BigDecimal price = new BigDecimal(100000);
        Integer quantity = 1;
        Ticket ticket = new Ticket();
        ticket.setPrice(price);
        ticket.setQuantity(quantity);
        ticket.setCategory(category);
        ticketService.saveTicket(ticket);

        Integer quantityBookingDetail = 2;
        BookingDetail bookingDetail = new BookingDetail();
        bookingDetail.setQuantity(quantityBookingDetail);
        bookingDetail.setTicketIdTransient(ticket.getId());
        bookingDetail.setTicket(ticket);
        List<BookingDetail> bookingDetails = new ArrayList<>();

        Date bookDate = new Date();
        Booking booking = new Booking();
        booking.setBookDate(bookDate);
        booking.setBookingDetailList(bookingDetails);
        bookingService.booking(booking);

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().
                withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Booking> bookingExample = Example.of(booking,exampleMatcher);

        Pageable pageable = PageRequest.of(0, 1);

        bookingService.getAllBookingData(bookingExample, pageable);
        Integer mockitoTimes = 1;
        Mockito.verify(bookingRepository, Mockito.times(mockitoTimes)).findAll(bookingExample, pageable);
    }

    @Test
    void getBookingById_should_callBookingRepository_getBookingById_once() {
        String roleName = "ADMIN";
        String description = "create as ADMIN";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleService.saveRole(role);

        String userName = "MangTix";
        String password = "MangTix";
        String fullName = "Mang Ticket";
        String bornPlace = "Jakarta Barat";
        Date birthDate =  new Date();
        Date createAt = new Date();
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setBornPlace(bornPlace);
        user.setBirthDate(birthDate);
        user.setCreateAt(createAt);
        user.setRoleIdTransient(role.getId());
        userService.saveUser(user);

        String companyName = "MangTix";
        Company company = new Company();
        company.setCompanyName(companyName);
        companyService.saveCompany(company);

        String venue = "loyal garden";
        String descriptionEventDetail = "mangtik";
        String eventDay = "day 1";
        Date eventDate = new Date();
        EventDetail eventDetail = new EventDetail();
        eventDetail.setVenue(venue);
        eventDetail.setDescription(descriptionEventDetail);
        eventDetail.setEventDay(eventDay);
        eventDetail.setEventDate(eventDate);
        List<EventDetail> eventDetails = new ArrayList<>();
        eventDetails.add(eventDetail);

        String eventName = "MangTix Event";
        String descriptionEvent = "Party with fatiri";
        Boolean publishStatus = true;
        Event event = new Event();
        event.setEventName(eventName);
        event.setDescriptionEvent(descriptionEvent);
        event.setPublishStatus(publishStatus);
        event.setEventDetailList(eventDetails);
        event.setCompany(company);
        eventService.saveEvent(event);

        String categoryName = "vip";
        Category category = new Category();
        category.setCategoryName(categoryName);


        BigDecimal price = new BigDecimal(100000);
        Integer quantity = 1;
        Ticket ticket = new Ticket();
        ticket.setPrice(price);
        ticket.setQuantity(quantity);
        ticket.setCategory(category);
        ticketService.saveTicket(ticket);

        Integer quantityBookingDetail = 2;
        BookingDetail bookingDetail = new BookingDetail();
        bookingDetail.setQuantity(quantityBookingDetail);
        bookingDetail.setTicketIdTransient(ticket.getId());
        bookingDetail.setTicket(ticket);
        List<BookingDetail> bookingDetails = new ArrayList<>();

        String bookingId = "116957a8-e98c-47b2-b51e-a91dd7ad637b ";
        Date bookDate = new Date();
        Booking booking = new Booking();
        booking.setBookDate(bookDate);
        booking.setBookingDetailList(bookingDetails);
        bookingService.booking(booking);
        Mockito.when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        bookingService.getBookingById(bookingId);

        Integer mockitoTimes = 2;

        Mockito.verify(bookingRepository, Mockito.times(mockitoTimes)).findById(bookingId);
    }

    @Test
    void getBookingById_should_returnThrow_when_dataBooking_notExist() {
        String roleName = "ADMIN";
        String description = "create as ADMIN";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleService.saveRole(role);

        String userName = "MangTix";
        String password = "MangTix";
        String fullName = "Mang Ticket";
        String bornPlace = "Jakarta Barat";
        Date birthDate =  new Date();
        Date createAt = new Date();
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setBornPlace(bornPlace);
        user.setBirthDate(birthDate);
        user.setCreateAt(createAt);
        user.setRoleIdTransient(role.getId());
        userService.saveUser(user);

        String companyName = "MangTix";
        Company company = new Company();
        company.setCompanyName(companyName);
        companyService.saveCompany(company);

        String venue = "loyal garden";
        String descriptionEventDetail = "mangtik";
        String eventDay = "day 1";
        Date eventDate = new Date();
        EventDetail eventDetail = new EventDetail();
        eventDetail.setVenue(venue);
        eventDetail.setDescription(descriptionEventDetail);
        eventDetail.setEventDay(eventDay);
        eventDetail.setEventDate(eventDate);
        List<EventDetail> eventDetails = new ArrayList<>();
        eventDetails.add(eventDetail);

        String eventName = "MangTix Event";
        String descriptionEvent = "Party with fatiri";
        Boolean publishStatus = true;
        Event event = new Event();
        event.setEventName(eventName);
        event.setDescriptionEvent(descriptionEvent);
        event.setPublishStatus(publishStatus);
        event.setEventDetailList(eventDetails);
        event.setCompany(company);
        eventService.saveEvent(event);

        String categoryName = "vip";
        Category category = new Category();
        category.setCategoryName(categoryName);


        BigDecimal price = new BigDecimal(100000);
        Integer quantity = 1;
        Ticket ticket = new Ticket();
        ticket.setPrice(price);
        ticket.setQuantity(quantity);
        ticket.setCategory(category);
        ticketService.saveTicket(ticket);

        Integer quantityBookingDetail = 2;
        BookingDetail bookingDetail = new BookingDetail();
        bookingDetail.setQuantity(quantityBookingDetail);
        bookingDetail.setTicketIdTransient(ticket.getId());
        bookingDetail.setTicket(ticket);
        List<BookingDetail> bookingDetails = new ArrayList<>();

        Date bookDate = new Date();
        Booking booking = new Booking();
        booking.setBookDate(bookDate);
        booking.setBookingDetailList(bookingDetails);
        bookingService.booking(booking);
        String bookingId = "116957a8-e98c-47b2-b51e-a91dd7ad637b ";

        Mockito.when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));


        String dummyBookingId = "116957a8-e98c-47b2-b51e-a91dd7ad637b ";
        String expectedError = String.format(MessageConstant.ID_BOOKING_NOT_FOUND, booking.getId());

        try{

            bookingService.getBookingById(dummyBookingId);
        }catch (Exception e){
            String actualError = e.getLocalizedMessage();
            assertEquals(expectedError, actualError);
        }
    }

    @Test
    void deleteBookingDataById_should_callBookingRepository_deleteById() {
        String roleName = "ADMIN";
        String description = "create as ADMIN";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleService.saveRole(role);

        String userName = "MangTix";
        String password = "MangTix";
        String fullName = "Mang Ticket";
        String bornPlace = "Jakarta Barat";
        Date birthDate =  new Date();
        Date createAt = new Date();
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setBornPlace(bornPlace);
        user.setBirthDate(birthDate);
        user.setCreateAt(createAt);
        user.setRoleIdTransient(role.getId());
        userService.saveUser(user);

        String companyName = "MangTix";
        Company company = new Company();
        company.setCompanyName(companyName);
        companyService.saveCompany(company);

        String venue = "loyal garden";
        String descriptionEventDetail = "mangtik";
        String eventDay = "day 1";
        Date eventDate = new Date();
        EventDetail eventDetail = new EventDetail();
        eventDetail.setVenue(venue);
        eventDetail.setDescription(descriptionEventDetail);
        eventDetail.setEventDay(eventDay);
        eventDetail.setEventDate(eventDate);
        List<EventDetail> eventDetails = new ArrayList<>();
        eventDetails.add(eventDetail);

        String eventName = "MangTix Event";
        String descriptionEvent = "Party with fatiri";
        Boolean publishStatus = true;
        Event event = new Event();
        event.setEventName(eventName);
        event.setDescriptionEvent(descriptionEvent);
        event.setPublishStatus(publishStatus);
        event.setEventDetailList(eventDetails);
        event.setCompany(company);
        eventService.saveEvent(event);

        String categoryName = "vip";
        Category category = new Category();
        category.setCategoryName(categoryName);


        BigDecimal price = new BigDecimal(100000);
        Integer quantity = 1;
        Ticket ticket = new Ticket();
        ticket.setPrice(price);
        ticket.setQuantity(quantity);
        ticket.setCategory(category);
        ticketService.saveTicket(ticket);

        Integer quantityBookingDetail = 2;
        BookingDetail bookingDetail = new BookingDetail();
        bookingDetail.setQuantity(quantityBookingDetail);
        bookingDetail.setTicketIdTransient(ticket.getId());
        bookingDetail.setTicket(ticket);
        List<BookingDetail> bookingDetails = new ArrayList<>();

        Date bookDate = new Date();
        Booking booking = new Booking();
        booking.setBookDate(bookDate);
        booking.setBookingDetailList(bookingDetails);
        bookingService.booking(booking);
        String bookingId = "116957a8-e98c-47b2-b51e-a91dd7ad637b ";

        Mockito.when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        bookingService.deleteBookingDataById(bookingId);

        Integer mockitoTimes = 1;

        Mockito.verify(bookingRepository, Mockito.times(mockitoTimes)).deleteById(bookingId);
    }

    @Test
    void deleteBookingDataById_should_returnThrow_when_dataBooking_notExist() {
        String roleName = "ADMIN";
        String description = "create as ADMIN";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleService.saveRole(role);

        String userName = "MangTix";
        String password = "MangTix";
        String fullName = "Mang Ticket";
        String bornPlace = "Jakarta Barat";
        Date birthDate =  new Date();
        Date createAt = new Date();
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setBornPlace(bornPlace);
        user.setBirthDate(birthDate);
        user.setCreateAt(createAt);
        user.setRoleIdTransient(role.getId());
        userService.saveUser(user);

        String companyName = "MangTix";
        Company company = new Company();
        company.setCompanyName(companyName);
        companyService.saveCompany(company);

        String venue = "loyal garden";
        String descriptionEventDetail = "mangtik";
        String eventDay = "day 1";
        Date eventDate = new Date();
        EventDetail eventDetail = new EventDetail();
        eventDetail.setVenue(venue);
        eventDetail.setDescription(descriptionEventDetail);
        eventDetail.setEventDay(eventDay);
        eventDetail.setEventDate(eventDate);
        List<EventDetail> eventDetails = new ArrayList<>();
        eventDetails.add(eventDetail);

        String eventName = "MangTix Event";
        String descriptionEvent = "Party with fatiri";
        Boolean publishStatus = true;
        Event event = new Event();
        event.setEventName(eventName);
        event.setDescriptionEvent(descriptionEvent);
        event.setPublishStatus(publishStatus);
        event.setEventDetailList(eventDetails);
        event.setCompany(company);
        eventService.saveEvent(event);

        String categoryName = "vip";
        Category category = new Category();
        category.setCategoryName(categoryName);


        BigDecimal price = new BigDecimal(100000);
        Integer quantity = 1;
        Ticket ticket = new Ticket();
        ticket.setPrice(price);
        ticket.setQuantity(quantity);
        ticket.setCategory(category);
        ticketService.saveTicket(ticket);

        Integer quantityBookingDetail = 2;
        BookingDetail bookingDetail = new BookingDetail();
        bookingDetail.setQuantity(quantityBookingDetail);
        bookingDetail.setTicketIdTransient(ticket.getId());
        bookingDetail.setTicket(ticket);
        List<BookingDetail> bookingDetails = new ArrayList<>();

        Date bookDate = new Date();
        Booking booking = new Booking();
        booking.setBookDate(bookDate);
        booking.setBookingDetailList(bookingDetails);
        bookingService.booking(booking);
        String bookingId = "116957a8-e98c-47b2-b51e-a91dd7ad637b ";

        Mockito.when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));


        String dummyBookingId = "116957a8-e98c-47b2-b51e-a91dd7ad637b ";
        String expectedError = String.format(MessageConstant.ID_BOOKING_NOT_FOUND, booking.getId());

        try{
            bookingService.deleteBookingDataById(dummyBookingId);
        }catch (Exception e){
            String actualError = e.getLocalizedMessage();
            assertEquals(expectedError, actualError);
        }
    }
}