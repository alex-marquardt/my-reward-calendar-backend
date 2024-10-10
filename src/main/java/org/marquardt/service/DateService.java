package org.marquardt.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.marquardt.model.ingoing.DateRequest;
import org.marquardt.model.outgoing.DateResponse;
import org.marquardt.jpa.Date;
import org.marquardt.jpa.DateRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ApplicationScoped
public class DateService {

    @Inject
    DateRepository dateRepository;

    public DateResponse getDate(LocalDate date) {
        Date dateFromDB = getDateFromDB(date);
        return new DateResponse(dateFromDB);
    }

    public List<DateResponse> getAllDates() {
        List<Date> datesFromDB = dateRepository.listAll();
        List<DateResponse> allDates = new ArrayList<>();
        for (Date date : datesFromDB) {
            allDates.add(new DateResponse(date));
        }
        return allDates;
    }

    @Transactional
    public DateResponse upsertDate(DateRequest request) {
        Optional<Date> optionalDate = dateRepository.find("date", request.getDate()).firstResultOptional();

        Date date = new Date();
        if (optionalDate.isEmpty()) {
            date = new Date(request);
            dateRepository.persist(date);
        } else {
            date = optionalDate.get();
            date.setState(request.getState());
        }
        return new DateResponse(date);
    }

    @Transactional
    public void deleteDate(LocalDate date) {
        dateRepository.delete("date", date);
    }

    private Date getDateFromDB(LocalDate date) {
        Optional<Date> dateFromDB = dateRepository.find("date", date).firstResultOptional();
        if (dateFromDB.isEmpty()) {
            throw new NoSuchElementException("Date " + date + " does not exist");
        }
        return dateFromDB.get();
    }
}
