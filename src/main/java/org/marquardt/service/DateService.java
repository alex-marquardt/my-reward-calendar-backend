package org.marquardt.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.marquardt.api.model.CreateDateRequest;
import org.marquardt.api.model.DateResponse;
import org.marquardt.api.model.UpdateDateRequest;
import org.marquardt.model.jpa.Date;
import org.marquardt.model.jpa.DateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ApplicationScoped
public class DateService {

    @Inject
    DateRepository dateRepository;

    public DateResponse getDate(String id) {
        Date dateFromDB = getDateFromDB(id);
        return new DateResponse(dateFromDB.getId(), dateFromDB.getDate(), dateFromDB.getState());
    }

    public List<DateResponse> getAllDates() {
        List<Date> datesFromDB = dateRepository.listAll();
        List<DateResponse> allDates = new ArrayList<>();
        for (Date date : datesFromDB) {
                allDates.add(new DateResponse(date.getId(), date.getDate(), date.getState()));
        }
        return allDates;
    }

    @Transactional
    public DateResponse createDate(CreateDateRequest request) {
        Date newDate = new Date(request.getDate(), request.getState());
        dateRepository.persist(newDate);
        return new DateResponse(newDate.getId(), newDate.getDate(), newDate.getState());
    }

    @Transactional
    public DateResponse updateDate(String id, UpdateDateRequest request) {
        Date dateFromDB = getDateFromDB(id);
        dateFromDB.setState(request.getState());
        return new DateResponse(id, dateFromDB.getDate(), dateFromDB.getState());
    }

    @Transactional
    public void deleteDate(String id) {
        dateRepository.delete("id", id);
    }

    private Date getDateFromDB(String id) {
        Date dateFromDB = dateRepository.find("id", id).firstResult();
        if (dateFromDB == null) {
            throw new NoSuchElementException("Date with id " + id + " does not exist");
        }
        return dateFromDB;
    }
}
