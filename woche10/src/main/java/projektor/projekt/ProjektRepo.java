package projektor.projekt;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.beans.PersistenceDelegate;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjektRepo extends CrudRepository<Projekt,Long> {


    @Query("SELECT * FROM projekt")
    List<Projekt> findAll();

    @Query("select * from projekt where zeitraum in (select id from zeitraum where von = :zeitraum_von and bis = :zeitraum_bis);")
    List<Projekt> findProjektByZeitraum_VonAndZeitraum_Bis(@Param("zeitraum_von") LocalDate zeitraum_von,
                                                           @Param("zeitraum_bis") LocalDate zeitraum_bis);

    @Query("select * from projekt where id = :id")
    Projekt findProjektById(@Param("id") Long id);

    @Query("select * from projekt where id in (select projekt from person where name = :name)")
    Person findProjektByPerson(@Param("name") String name );


}
