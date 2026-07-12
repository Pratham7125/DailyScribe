package com.pc.journalApp.respository;

import com.pc.journalApp.entity.JournalEntry;
import com.pc.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
     User findByUserName(String username);

     void deleteByUserName(String username);
}
