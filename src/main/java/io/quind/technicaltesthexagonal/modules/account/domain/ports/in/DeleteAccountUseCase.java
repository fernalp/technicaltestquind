package io.quind.technicaltesthexagonal.modules.account.domain.ports.in;

public interface DeleteAccountUseCase {
    boolean deleteAccount(Long id);
}
