package net.sahil.journalApp.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import net.sahil.journalApp.enums.Sentiment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Getter
@Setter
public class JournalEntry {

    @Id
    private String id;

    @NonNull
    private String title;

    private LocalDateTime date;

    private String content;

    private Sentiment sentiment;
}
