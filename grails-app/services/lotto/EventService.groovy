package lotto

import grails.orm.PagedResultList

class EventService {

    PagedResultList getEvents(lottery, params) {
        def criteria = Event.createCriteria()
        def results = criteria.list(params) {
            eq("lottery", lottery)
            order "startDate", "asc"
        }
        results
    }
}
