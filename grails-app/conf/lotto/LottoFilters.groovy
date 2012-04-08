package lotto

class LottoFilters {

    def filters = {
        debug(controller:'*', action:'*') {
            before = {
                log.debug "${actionUri} called."
                log.debug "params=${params}"
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
