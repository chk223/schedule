package AfterLv4.util;

import AfterLv4.exception.ApiException;
import AfterLv4.exception.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

@Slf4j
public class EntityFinder {

    public static <T, ID> T findByIdOrThrowException(ID id, JpaRepository<T, ID> repository, String entityName) {
        return repository.findById(id)
                .orElseThrow(()-> {
                    log.warn("해당 id를 가진 {} 객체가 없음! id= {}",entityName ,id);
                    ErrorMessage errorMessage = ErrorMessage.ENTITY_NOT_FOUND;
                    return new ApiException(errorMessage.getMessage(),errorMessage.getStatus());
                });
    }

}
