package io.quind.technicaltesthexagonal.modules.account.domain.ports.in;

public interface DeleteAccountUseCase {
    void deleteAccount(String accountNumber);
}
