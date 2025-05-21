package ait.cohort55.accounting.dao;

import ait.cohort55.accounting.model.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAccountRepository extends MongoRepository<UserAccount, String> {
}
