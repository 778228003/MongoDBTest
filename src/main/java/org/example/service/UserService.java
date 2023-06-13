package org.example.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public User createUser(User user) {
        User user1 = mongoTemplate.insert(user);
        return user1;
    }

    public List<User> findUsers() {
        List<User> userList = mongoTemplate.findAll(User.class);
        return userList;
    }

    public User  findUserById(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class, "users");
        return user;
    }

    public List<User> findUsersLikeName(User userInfo) {
        String regex = String.format("%s%s%s", "^.*", userInfo.getName(), ".*$");
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Query query = new Query(Criteria.where("name").regex(pattern));
        List<User> userList = mongoTemplate.find(query, User.class);
        return userList;
    }

    public Map<String, Object>  findUsersPage() {
        int pageNo = 1;
        int pageSize = 10;

        Query query = new Query();
        int totalCount = (int) mongoTemplate.count(query, User.class);
        List<User> userList = mongoTemplate.find(query.skip((pageNo - 1) * pageSize).limit(pageSize), User.class);

        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("list", userList);
        pageMap.put("totalCount",totalCount);
        return pageMap;
    }

    public long updateUser(User userInfo) {
        Query query = new Query(Criteria.where("userId").is(userInfo.getUserId()));
        Update update = new Update();
        update.set("name", userInfo.getName());
        update.set("age", userInfo.getAge());
        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
        long count = result.getModifiedCount();
        return count;
    }

    public long deleteUser(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        DeleteResult result = mongoTemplate.remove(query, User.class);
        long count = result.getDeletedCount();
        return count;
    }
}
