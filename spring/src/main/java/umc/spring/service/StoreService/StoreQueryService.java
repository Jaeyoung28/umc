package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    Page<Review> getReviewList(Long storeId, Integer page);
    Page<Review> getMyReviewList(Long storeId, Long memberId, Integer page);
}
