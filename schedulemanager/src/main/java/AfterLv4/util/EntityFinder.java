package AfterLv4.util;

import AfterLv4.exception.ApiException;
import AfterLv4.exception.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

@Slf4j
public class EntityFinder {

    /**
     * 해당 객체가 존재하는지 id를 통해 검색 후 없다면 예외를 던짐
     * @param id findById 실행을 위한 id
     * @param repository 검색 할 저장소
     * @param entityName 예외 처리 구분을 위한 엔티티 이름
     * @return 찾은 객체
     * @param <T> 객체
     * @param <ID> id(타입 상관 x)
     */
    public static <T, ID> T findByIdOrThrowException(ID id, JpaRepository<T, ID> repository, String entityName) {
        return repository.findById(id)
                .orElseThrow(()-> {
                    log.warn("해당 id를 가진 {} 객체가 없음! id= {}",entityName ,id);
                    ErrorMessage errorMessage = ErrorMessage.ENTITY_NOT_FOUND;
                    return new ApiException(errorMessage.getMessage(),errorMessage.getStatus());
                });
    }

}
