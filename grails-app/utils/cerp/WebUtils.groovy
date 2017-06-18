package cerp

import org.springframework.validation.Errors

/**
 * Created by 黄争 on 2016/10/6.
 */
class WebUtils {

    def messageSource

    def simplifyErrors(Object domain) {
        def result = [:]

        if (domain.errors) {
            domain.errors.getFieldErrors().each { fieldError ->
                def errorMessage = messageSource.getMessage(fieldError.code, null, null)
                if (!result.containsKey(fieldError.field)) {
                    result.put(fieldError.field, errorMessage)
                } else {
                    result.put(fieldError.field, result.get(fieldError.field) + ', ' + errorMessage)
                }
            }
        }

        return result
    }

}
