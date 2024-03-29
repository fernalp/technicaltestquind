package io.quind.technicaltesthexagonal.modules.transaction.infrastructure.repositories;

import io.quind.technicaltesthexagonal.modules.transaction.infrastructure.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTransactionRepository extends JpaRepository<TransactionEntity, Long> {



}
