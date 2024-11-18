package pt.psoft.g1.psoftg1.lendingmanagement.infrastructure.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.lendingmanagement.dataModel.sql.LendingDataModel;
import pt.psoft.g1.psoftg1.lendingmanagement.mappers.LendingSQLMapper;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Lending;
import pt.psoft.g1.psoftg1.lendingmanagement.repositories.LendingRepository;
import pt.psoft.g1.psoftg1.shared.services.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("sql")
@AllArgsConstructor
public class LendingSQLRepo implements LendingRepository {

        @PersistenceContext
        private EntityManager entityManager;

        private final LendingSQLMapper lendingSQLMapper;


        @Override
        public Optional<Lending> findByLendingNumber(String lendingNumber) {
            TypedQuery<LendingDataModel> query = entityManager.createQuery(
                    "SELECT l FROM LendingDataModel l WHERE l.lendingNumber = :lendingNumber", LendingDataModel.class);
            query.setParameter("lendingNumber", lendingNumber);

            return query.getResultStream()
                    .findFirst()
                    .map(lendingSQLMapper::convertToDomainLending);
        }

        @Override
        public List<Lending> listByReaderNumberAndIsbn(String readerNumber, String isbn) {
            TypedQuery<LendingDataModel> query = entityManager.createQuery(
                    "SELECT l FROM LendingDataModel l WHERE l.readerNumber = :readerNumber AND l.isbn = :isbn", LendingDataModel.class);
            query.setParameter("readerNumber", readerNumber);
            query.setParameter("isbn", isbn);

            return query.getResultStream()
                    .map(lendingSQLMapper::convertToDomainLending)
                    .collect(Collectors.toList());
        }

        @Override
        public int getCountFromCurrentYear() {
            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT COUNT(l) FROM LendingDataModel l WHERE YEAR(l.startDate) = :currentYear", Long.class);
            query.setParameter("currentYear", LocalDate.now().getYear());

            return query.getSingleResult().intValue();
        }

        @Override
        public List<Lending> listOutstandingByReaderNumber(String readerNumber) {
            TypedQuery<LendingDataModel> query = entityManager.createQuery(
                    "SELECT l FROM LendingDataModel l WHERE l.readerNumber = :readerNumber AND l.returnedDate IS NULL", LendingDataModel.class);
            query.setParameter("readerNumber", readerNumber);

            return query.getResultStream()
                    .map(lendingSQLMapper::convertToDomainLending)
                    .collect(Collectors.toList());
        }

        @Override
        public Double getAverageDuration() {
            /*
            TypedQuery<Double> query = entityManager.createQuery(
                    "SELECT AVG(DATEDIFF(day, l.startDate, l.limitDate)) FROM LendingDataModel l", Double.class);
            return query.getSingleResult();

             */
            return null;
        }

        @Override
        public Double getAvgLendingDurationByIsbn(String isbn) {
            /*

            TypedQuery<Double> query = entityManager.createQuery(
                    "SELECT AVG(DATEDIFF(day, l.startDate, l.returnedDate)) FROM LendingDataModel l WHERE l.isbn = :isbn", Double.class);
            query.setParameter("isbn", isbn);
            return query.getSingleResult();

             */
            return null;
        }

        @Override
        public List<Lending> getOverdue(Page page) {
            /*

            TypedQuery<LendingDataModel> query = entityManager.createQuery(
                    "SELECT l FROM LendingDataModel l WHERE l.limitDate < CURRENT_DATE AND l.returnedDate IS NULL", LendingDataModel.class);
            query.setFirstResult(page.getOffset());
            query.setMaxResults(page.getSize());

            return query.getResultStream()
                    .map(lendingSQLMapper::convertToDomainLending)
                    .collect(Collectors.toList());

             */
            return null;
        }

        @Override
        public List<Lending> searchLendings(Page page, String readerNumber, String isbn, Boolean returned, LocalDate startDate, LocalDate endDate) {
            /*
            StringBuilder queryBuilder = new StringBuilder("SELECT l FROM LendingDataModel l WHERE 1=1 ");
            if (readerNumber != null) queryBuilder.append("AND l.readerNumber = :readerNumber ");
            if (isbn != null) queryBuilder.append("AND l.isbn = :isbn ");
            if (returned != null) queryBuilder.append("AND (l.returnedDate IS " + (returned ? "NOT NULL" : "NULL") + ") ");
            if (startDate != null) queryBuilder.append("AND l.startDate >= :startDate ");
            if (endDate != null) queryBuilder.append("AND l.limitDate <= :endDate ");

            TypedQuery<LendingDataModel> query = entityManager.createQuery(queryBuilder.toString(), LendingDataModel.class);
            if (readerNumber != null) query.setParameter("readerNumber", readerNumber);
            if (isbn != null) query.setParameter("isbn", isbn);
            if (startDate != null) query.setParameter("startDate", startDate);
            if (endDate != null) query.setParameter("endDate", endDate);

            query.setFirstResult(page.getOffset());
            query.setMaxResults(page.getSize());

            return query.getResultStream()
                    .map(lendingSQLMapper::convertToDomainLending)
                    .collect(Collectors.toList());

             */
            return null;
        }

        @Override
        public Lending save(Lending lending) {
            LendingDataModel dataModel = lendingSQLMapper.convertToDataModelLending(lending);
            if (dataModel.getPk() == null) {
                entityManager.persist(dataModel);
            } else {
                dataModel = entityManager.merge(dataModel);
            }
            return lendingSQLMapper.convertToDomainLending(dataModel);
        }

        @Override
        public void delete(Lending lending) {
            LendingDataModel dataModel = entityManager.find(LendingDataModel.class, lending.getLendingNumber());
            if (dataModel != null) {
                entityManager.remove(dataModel);
            }
        }

}
