package org.marquardt.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.marquardt.api.model.CreateDateRequest;
import org.marquardt.api.model.DateResponse;
import org.marquardt.api.model.UpdateDateRequest;
import org.marquardt.model.Date;
import org.marquardt.model.DateRepository;
import org.marquardt.model.DateState;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DateService {

    @Inject
    DateRepository dateRepository;

    @Transactional
    public DateResponse getCurrentDate() {
        Optional<Date> optionalDate = dateRepository.find("id", LocalDate.now()).firstResultOptional();
        if (optionalDate.isPresent()) {
            Date existingDate = optionalDate.get();
            return new DateResponse(existingDate.getDate(), existingDate.getState());
        }
        Date date = new Date();
        dateRepository.persist(date);
        return new DateResponse(date.getDate(), date.getState());
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
        dateRepository.persist(getNewDateOrExistDate(request.getDate(), request.getState()));
        return new DateResponse(request.getDate(), request.getState());
    }

    @Transactional
    public DateResponse updateDate(LocalDate date, UpdateDateRequest request) {
        dateRepository.persist(getNewDateOrExistDate(date, request.getState()));
        return new DateResponse(date, request.getState());
    }

    @Transactional
    public void deleteDate(LocalDate id) {
        dateRepository.delete("id", id);
    }

    private Date getNewDateOrExistDate(LocalDate date, DateState state) {
        Optional<Date> optionalDate = dateRepository.find("id", date).firstResultOptional();
        if (optionalDate.isPresent()) {
            Date existingDate = optionalDate.get();
            existingDate.setState(state);
            return existingDate;
        }
        return new Date(date, state);
    }
}
