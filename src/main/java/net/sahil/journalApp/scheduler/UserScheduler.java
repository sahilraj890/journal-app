package net.sahil.journalApp.scheduler;

import lombok.extern.slf4j.Slf4j;
import net.sahil.journalApp.cache.AppCache;
import net.sahil.journalApp.entity.JournalEntry;
import net.sahil.journalApp.entity.User;
import net.sahil.journalApp.enums.Sentiment;
import net.sahil.journalApp.model.SentimentData;
import net.sahil.journalApp.repository.UserRepoImpl;
import net.sahil.journalApp.service.EmailService;
import net.sahil.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepoImpl userRepo;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;

    @Autowired
    private KafkaTemplate<String, SentimentData> kafkaTemplate;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail() {
        List<User> users = userRepo.getUsersForSA();
        for (User user: users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).toList();
            Map<Sentiment, Integer> sentimentCounts= new HashMap<>();
            for (Sentiment sentiment: sentiments) {
                if (sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }
            Sentiment mostFrequestSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequestSentiment = entry.getKey();
                }
            }
            if (mostFrequestSentiment != null) {
                SentimentData sentimentData = SentimentData.builder().email(user.getEmail()).sentiment("Sentiment for last 7 days " + mostFrequestSentiment).build();
                try {
                    kafkaTemplate.send("weekly_sentiments", sentimentData.getEmail(), sentimentData);
                } catch (Exception e) {
                    emailService.sendMail(sentimentData.getEmail(), "Sentiment for previous week ", sentimentData.getSentiment());
//                    log.error("Error while publishing the data to kafka", e);
                }
            }
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache() {
        appCache.init();
    }
}
