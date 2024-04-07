package ch.zhaw.iwi.devops.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class WeinController {
    Logger logger = Logger.getLogger(getClass().getName());
    private Map<Integer, Wein> wines = new HashMap<Integer, Wein>();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        this.wines.put(1,new Wein(1, "Barolo", "Ein roter kräftiger Wein"));
        this.wines.put(2,new Wein(2, "Chardonnay", "Leicht süsslicher Weisswein"));
        this.wines.put(3,new Wein(3, "Riesling", "Leicht säuerlicher Weisswein"));
        this.wines.put(4,new Wein(4, "Sicus", "Ein Orange-Wein"));
        this.wines.put(5,new Wein(5, "Fendant", "Geeignet für Fondue"));
        logger.info("Init Wine Data");
    }

    @GetMapping("/services/wein")
    public List<PathListEntry<Integer>> wein() {
        var result = new ArrayList<PathListEntry<Integer>>();
        for (var wein : this.wines.values()) {
            var entry = new PathListEntry<Integer>();
            entry.setKey(wein.getId(), "wineKey");
            entry.setName(wein.getTitle());
            entry.getDetails().add(wein.getDescription());
            entry.setTooltip(wein.getDescription());
            result.add(entry);
        }
        return result;
    }

    @GetMapping("/services/wein/{key}")
    public Wein getWein(@PathVariable Integer key) {
        return this.wines.get(key);
    }

    @PostMapping("/services/wein")
    public void createWein(@RequestBody Wein wein) {
        var newId = this.wines.keySet().stream().max(Comparator.naturalOrder()).orElse(0) + 1;
        wein.setId(newId);
        this.wines.put(newId, wein);
    }

    @PutMapping("/services/wein/{id}")
    public void createWein(@PathVariable Integer id, @RequestBody Wein wein) {
        wein.setId(id);
        this.wines.put(id, wein);
    }

    @DeleteMapping("/services/wein/{key}")
    public Wein deleteWein(@PathVariable Integer key) {
        return this.wines.remove(key);
    }


}
