package covidtracer.services;

import covidtracer.model.KontaktListe;
import covidtracer.persistence.KontaktListeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DBService {

    @Autowired
    KontaktListeRepository kontaktListeRepository;

    public List<KontaktListe> findAll() {
        return kontaktListeRepository.findAll();
    }

    public Optional<KontaktListe> findById(long id) {
        return kontaktListeRepository.findById(id);
    }

    public void save(KontaktListe liste) {
        kontaktListeRepository.save(liste);
    }
}
