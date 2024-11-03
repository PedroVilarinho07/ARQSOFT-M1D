package pt.psoft.g1.psoftg1.authormanagement.infrastructure.repositories.impl;

import jakarta.persistence.*;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import pt.psoft.g1.psoftg1.authormanagement.dto.response.AuthorLendingResponseDTO;
import pt.psoft.g1.psoftg1.authormanagement.factories.AuthorFactory;
import pt.psoft.g1.psoftg1.authormanagement.mappers.AuthorSQLMapper;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.authormanagement.repositories.AuthorRepository;
import pt.psoft.g1.psoftg1.authormanagement.dataModel.sql.AuthorDataModel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("sql")
@RequiredArgsConstructor
public class AuthorSQLRepo implements AuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final AuthorFactory authorFactory;

    @Override
    public Optional<Author> findByAuthorNumber(String authorNumber) {
        TypedQuery<AuthorDataModel> query = entityManager.createQuery(
                "SELECT a FROM AuthorDataModel a WHERE a.authorNumber = :authorNumber", AuthorDataModel.class);
        query.setParameter("authorNumber", authorNumber);
        if (query.getResultList().stream().findFirst().isPresent()) {
            return Optional.of(AuthorSQLMapper.convertToDomainAuthor(authorFactory, query.getResultList().stream().findFirst().get()));
        }
        return Optional.empty();
    }

    @Override
    public List<Author> searchByNameNameStartsWith(String name) {
        TypedQuery<AuthorDataModel> query = entityManager.createQuery(
                "SELECT a FROM AuthorDataModel a WHERE a.name LIKE :namePattern", AuthorDataModel.class);
        query.setParameter("namePattern", name + "%");
        if (query.getResultList().stream().findFirst().isPresent()) {
            return query.getResultList().stream()
                    .map(dataModel -> AuthorSQLMapper.convertToDomainAuthor(authorFactory, dataModel))
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<Author> searchByNameName(String name) {
        TypedQuery<AuthorDataModel> query = entityManager.createQuery(
                "SELECT a FROM AuthorDataModel a WHERE a.name = :name", AuthorDataModel.class);
        query.setParameter("name", name);

        return query.getResultList().stream()
                .map(dataModel -> AuthorSQLMapper.convertToDomainAuthor(authorFactory, dataModel))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Author save(Author author) {
        entityManager.setFlushMode(FlushModeType.AUTO);
        String photo;
        try {
            photo = author.getPhoto().getPhotoFile();
        } catch (Exception ignored) {photo = null;}

        AuthorDataModel dataModel = new AuthorDataModel(
                author.getAuthorNumber().toString(),
                author.getName(),
                author.getBio(),
                photo
        );
        
        entityManager.persist(dataModel);
        entityManager.flush();
        System.out.println("Is transaction active? " + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("Is transaction marked for rollback? " + TransactionSynchronizationManager.isCurrentTransactionReadOnly());

        return author;

    }

    @Override
    public Iterable<Author> findAll() {
        TypedQuery<AuthorDataModel> query = entityManager.createQuery("SELECT a FROM AuthorDataModel a", AuthorDataModel.class);

        return query.getResultList().stream()
                .map(dataModel -> AuthorSQLMapper.convertToDomainAuthor(authorFactory, dataModel))
                .collect(Collectors.toList());
    }

    @Override
    public Page<AuthorLendingResponseDTO> findTopAuthorByLendings(Pageable pageableRules) {
        /*
        TypedQuery<AuthorLendingResponseDTO> query = entityManager.createQuery(
                "SELECT new pt.psoft.g1.psoftg1.authormanagement.dto.response.AuthorLendingResponseDTO(a.authorNumber, COUNT(l)) " +
                        "FROM Lending l JOIN l.author a " +
                        "GROUP BY a.authorNumber ORDER BY COUNT(l) DESC", AuthorLendingResponseDTO.class);
        query.setFirstResult((int) pageableRules.getOffset());
        query.setMaxResults(pageableRules.getPageSize());

        List<AuthorLendingResponseDTO> results = query.getResultList();
        long totalResults = query.getResultStream().count();

        return new PageImpl<>(results, pageableRules, totalResults);
         */
        throw new UnsupportedOperationException("This method is not yet implemented.");
    }

    @Override
    public void delete(Author author) {
        AuthorDataModel dataModel = entityManager.find(AuthorDataModel.class, author.getAuthorNumber());
        if (dataModel != null) {
            entityManager.remove(dataModel);
        }
    }

    @Override
    public List<Author> findCoAuthorsByAuthorNumber(String authorNumber) {
        TypedQuery<AuthorDataModel> query = entityManager.createQuery(
                "SELECT c FROM AuthorDataModel c WHERE c.authorNumber != :authorNumber", AuthorDataModel.class);
        query.setParameter("authorNumber", authorNumber);

        return query.getResultList().stream()
                .map(dataModel -> AuthorSQLMapper.convertToDomainAuthor(authorFactory, dataModel))
                .collect(Collectors.toList());
    }



}
