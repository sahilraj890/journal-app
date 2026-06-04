package net.sahil.journalApp.controller;


import net.sahil.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {

    private Map<String, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry entry) {
        journalEntries.put(entry.getId(), entry);
        return true;
    }

    @GetMapping("/id/{id}")
    public JournalEntry findJournalById(@PathVariable String id) {
        return journalEntries.get(id);
    }

    @DeleteMapping("delete/{id}")
    public boolean deleteEntry(@PathVariable String id) {
        journalEntries.remove(id);
        return true;
    }

    @PutMapping("update/{id}")
    public JournalEntry updateEntry(@PathVariable String id, @RequestBody JournalEntry entry) {
        return journalEntries.put(id, entry);
    }
}
