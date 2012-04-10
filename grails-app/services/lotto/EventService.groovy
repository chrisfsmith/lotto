package lotto

import grails.orm.PagedResultList

class EventService {

    /**
     * Get the Events associated with the provided Lottery.
     * Return type is grails.orm.PagedResultList so that the
     * pagination count is returned too.
     *
     * @param lottery
     * @param params Pagination parameters such as offset, max etc.
     * @return PagedResultList
     */
    PagedResultList findEventsByLottery(lottery, params) {
        def criteria = Event.createCriteria()
        def results = criteria.list(params) {
            // could add check to eliminate "full" events
            eq("lottery", lottery)
            order "startDate", "asc"
        }

        results
    }
}
