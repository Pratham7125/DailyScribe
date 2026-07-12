package com.pc.journalApp.controller;

import com.pc.journalApp.dto.JournalRequest;
import com.pc.journalApp.entity.JournalEntry;
import com.pc.journalApp.entity.User;
import com.pc.journalApp.service.JournalEntryService;
import com.pc.journalApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(
        name = "3. Journal APIs",
        description = "Create, Read, Update and Delete Journal Entries"
)
@SecurityRequirement(name = "basicAuth")
@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @Operation(
            summary = "1. Get All Journal Entries",
            description = "Returns all journal entries of the authenticated user."
    )
    @GetMapping
    public ResponseEntity<?> getAllEntriesForUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> journalEntry = user.getJournalEntries();
        if(journalEntry != null && !journalEntry.isEmpty()){
            return new ResponseEntity<>(journalEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "2. Create Journal Entry",
            description = "Creates a new journal entry."
    )
    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            JournalEntry entry = new JournalEntry();
            entry.setTitle(request.getTitle());
            entry.setContent(request.getContent());
            entry.setSentiment(request.getSentiment());

            journalEntryService.saveEntry(entry, userName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "3. Get Journal Entry By Id",
            description = "Returns a journal entry by its id."
    )
    @GetMapping("/{myId}")
    public ResponseEntity<?> getEntryById(@PathVariable String myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        if (!ObjectId.isValid(myId)) {
            return ResponseEntity.badRequest().body("Invalid ObjectId");
        }
        ObjectId objectId = new ObjectId(myId);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(objectId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            JournalEntry journalEntry = journalEntryService.findById(objectId).orElse(null);
            if(journalEntry != null){
                return new ResponseEntity<>(journalEntry, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "4. Update Journal Entry",
            description = "Updates title, content and sentiment."
    )
    @PutMapping("/{myId}")
    public ResponseEntity<?> modifyEntryById(
            @PathVariable String myId,
            @RequestBody JournalRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user = userService.findByUserName(userName);

        if (!ObjectId.isValid(myId)) {
            return ResponseEntity.badRequest().body("Invalid ObjectId");
        }

        ObjectId objectId = new ObjectId(myId);

        List<JournalEntry> collect = user.getJournalEntries()
                .stream()
                .filter(x -> x.getId().equals(objectId))
                .collect(Collectors.toList());

        if (!collect.isEmpty()) {

            JournalEntry oldEntry = journalEntryService.findById(objectId).orElse(null);

            if (oldEntry != null) {

                if (request.getTitle() != null && !request.getTitle().isBlank()) {
                    oldEntry.setTitle(request.getTitle());
                }

                if (request.getContent() != null && !request.getContent().isBlank()) {
                    oldEntry.setContent(request.getContent());
                }

                if (request.getSentiment() != null) {
                    oldEntry.setSentiment(request.getSentiment());
                }

                journalEntryService.saveEntry(oldEntry);

                return ResponseEntity.ok(oldEntry);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(
            summary = "5. Delete Journal Entry",
            description = "Deletes a journal entry by its ID."
    )
    @DeleteMapping("/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable String myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        if (!ObjectId.isValid(myId)) {
            return ResponseEntity.badRequest().body("Invalid ObjectId");
        }

        ObjectId objectId = new ObjectId(myId);
        boolean removed = journalEntryService.deleteEntryById(objectId, userName);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
