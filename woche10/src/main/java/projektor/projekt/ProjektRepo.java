package projektor.projekt;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.beans.PersistenceDelegate;

public interface ProjektRepo extends CrudRepository<Projekt,Long> {

    @Query("select * from projekte where id in (select projekt from person where name :name)")
    Person findProjektByPerson(@Param("name") String name );
}
