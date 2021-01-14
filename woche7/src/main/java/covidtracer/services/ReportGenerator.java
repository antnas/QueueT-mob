package covidtracer.services;

import covidtracer.controller.Webpage;
import covidtracer.model.KontaktListe;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class ReportGenerator {

    private int alter(KontaktListe liste) {
        LocalDate erstbefund = liste.getIndex().getErstbefund();
        LocalDate now = LocalDate.now();
        return Period.between(erstbefund, now).getDays();
    }

    public SortedMap<Integer, Long> generateReport(List<KontaktListe> listen) {
        Map<Integer, Long> alter =
                listen.stream().collect(Collectors.groupingBy(this::alter, Collectors.counting()));
        SortedMap<Integer, Long> sortedByAge = new TreeMap<>();
        sortedByAge.putAll(alter);
        return sortedByAge;
    }
}
