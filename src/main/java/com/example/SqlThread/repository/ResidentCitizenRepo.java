package com.example.SqlThread.repository;

import com.example.SqlThread.entity.ResidentCitizen;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.stream.Stream;

import static org.hibernate.annotations.QueryHints.READ_ONLY;
import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;
import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

public interface ResidentCitizenRepo extends JpaRepository<ResidentCitizen, String> {
    @Query(value = "SELECT * FROM resident_citizen limit :limitParam offset :offsetParam", nativeQuery = true)
    List<ResidentCitizen> getAListCitizen(@Param("limitParam") int limit, @Param("offsetParam") int offset);

    @Query(value = "SELECT count(*) FROM ResidentCitizen")
    int getCountCitizen();

    @QueryHints(value ={ @QueryHint(name = HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE),
            @QueryHint(name = HINT_CACHEABLE, value = "false"),
            @QueryHint(name = READ_ONLY, value = "true")}
    )
    @Query(value = "select rc from ResidentCitizen rc")
    Stream<ResidentCitizen> streamAllCitizen();

    Slice<ResidentCitizen> findAllBy(PageRequest request);


}
