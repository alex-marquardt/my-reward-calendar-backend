package org.marquardt.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.marquardt.api.model.CreateDateRequest;
import org.marquardt.api.model.DateResponse;
import org.marquardt.api.model.UpdateDateRequest;
import org.marquardt.model.jpa.Date;
import org.marquardt.model.jpa.DateRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ApplicationScoped
public class DateService {

    @Inject
    DateRepository dateRepository;

    public DateResponse getDate(LocalDate date) {
        Date dateFromDB = getDateFromDB(date);
        return new DateResponse(dateFromDB.getDate(), dateFromDB.getState());
    }

    public List<DateResponse> getAllDates() {
        List<Date> datesFromDB = dateRepository.listAll();
        List<DateResponse> allDates = new ArrayList<>();
        for (Date date : datesFromDB) {
                allDates.add(new DateResponse(date.getDate(), date.getState()));
        }
        return allDates;
    }

    @Transactional
    public DateResponse createDate(CreateDateRequest request) {
        Date newDate = new Date(request.getDate(), request.getState());
        dateRepository.persist(newDate);
        return new DateResponse(newDate.getDate(), newDate.getState());
    }

    @Transactional
    public DateResponse updateDate(LocalDate date, UpdateDateRequest request) {
        Date dateFromDB = getDateFromDB(date);
        dateFromDB.setState(request.getState());
        return new DateResponse(date, request.getState());
    }

    @Transactional
    public void deleteDate(LocalDate id) {
        dateRepository.delete("id", id);
    }

    private Date getDateFromDB(LocalDate date) {
        Date dateFromDB = dateRepository.find("id", date).firstResult();
        if (dateFromDB == null) {
            throw new NoSuchElementException("Date with id " + date + " does not exist");
        }
        return dateFromDB;
    }
}
