package pt.psoft.g1.psoftg1.readermanagement.infraestructure.repositories.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.psoftg1.readermanagement.dto.request.ReaderBookCountDTO;
import pt.psoft.g1.psoftg1.readermanagement.dto.request.SearchReadersQuery;
import pt.psoft.g1.psoftg1.readermanagement.model.ReaderDetails;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;
import pt.psoft.g1.psoftg1.readermanagement.repositories.ReaderRepository;
import pt.psoft.g1.psoftg1.usermanagement.model.Reader;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Profile("mongo")
public class ReaderMongoRepository implements ReaderRepository {

    private final List<ReaderDetails> staticReaders;
    private final Map<String, Integer> lendingCounts;

    public ReaderMongoRepository() {
        this.staticReaders = new ArrayList<>();
        this.lendingCounts = new HashMap<>();

        staticReaders.add(new ReaderDetails(1, new Reader("user1", "Password1?"), "1985-05-15", "912345678", true, true, false, null, List.of(new Genre("Fantasy"))));
        staticReaders.add(new ReaderDetails(2, new Reader("user2", "Password2?"), "1990-08-22", "913456789", true, false, true, null, List.of(new Genre("Science Fiction"))));
        staticReaders.add(new ReaderDetails(3, new Reader("user3", "Password3?"), "2000-02-10", "914567890", true, true, true, null, List.of(new Genre("Romance"))));

        lendingCounts.put("1", 15);
        lendingCounts.put("2", 7);
        lendingCounts.put("3", 20);
    }

    @Override
    public Optional<ReaderDetails> findByReaderNumber(String readerNumber) {
        return staticReaders.stream()
                .filter(reader -> reader.getReaderNumber().equals(readerNumber))
                .findFirst();
    }

    @Override
    public List<ReaderDetails> findByPhoneNumber(String phoneNumber) {
        return staticReaders.stream()
                .filter(reader -> reader.getPhoneNumber().equals(phoneNumber))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReaderDetails> findByUsername(String username) {
        return staticReaders.stream()
                .filter(reader -> reader.getReader().getUsername().equals(username))
                .findFirst();
    }

    @Override
    public Optional<ReaderDetails> findByUserId(Long userId) {
        return staticReaders.stream()
                .filter(reader -> reader.getReader().getId().equals(userId))
                .findFirst();
    }

    @Override
    public int getCountFromCurrentYear() {
        return (int) staticReaders.stream()
                .filter(reader -> reader.getReaderNumber().startsWith(String.valueOf(LocalDate.now().getYear())))
                .count();
    }

    @Override
    public ReaderDetails save(ReaderDetails readerDetails) {
        staticReaders.add(readerDetails);
        return readerDetails;
    }

    @Override
    public Iterable<ReaderDetails> findAll() {
        return staticReaders;
    }

    @Override
    public Page<ReaderDetails> findTopReaders(Pageable pageable) {
        List<ReaderDetails> sortedReaders = staticReaders.stream()
                .sorted(Comparator.comparingInt(reader -> lendingCounts.getOrDefault(reader.getReaderNumber(), 0)))
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), sortedReaders.size());

        return new PageImpl<>(sortedReaders.subList(start, end), pageable, sortedReaders.size());
    }

    @Override
    public Page<ReaderBookCountDTO> findTopByGenre(Pageable pageable, String genre, LocalDate startDate, LocalDate endDate) {
        return Page.empty(); // Implementação adicional se necessário
    }

    @Override
    public void delete(ReaderDetails readerDetails) {
        staticReaders.removeIf(reader -> reader.getReaderNumber().equals(readerDetails.getReaderNumber()));
    }

    @Override
    public List<ReaderDetails> searchReaderDetails(pt.psoft.g1.psoftg1.shared.services.Page page, SearchReadersQuery query) {
        return List.of();
    }
}
