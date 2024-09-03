package org.marquardt.model.jpa;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DateRepository implements PanacheRepository<Date> {
}
