package umc.spring.service.StoreService;

import umc.spring.domain.Store;

import java.util.Optional;

public interface StoreQueryService {
    public Optional<Store> findStore(Long id);
}
