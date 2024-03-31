package io.quind.technicaltesthexagonal.modules.account.infrastructure.repositories;

import io.quind.technicaltesthexagonal.modules.account.domain.models.AccountStatus;
import io.quind.technicaltesthexagonal.modules.account.infrastructure.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {
    boolean existsByAccountNumber(String accountNumber);
    List<AccountEntity> findByCustomer_CustomerId(Long id);
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
    List<AccountEntity> findByAccountStatusNot(AccountStatus accountStatus);
    void deleteByAccountNumber(String accountNumber);

}
