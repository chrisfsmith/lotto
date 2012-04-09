package lotto

import grails.orm.PagedResultList

class EventService {

    PagedResultList getEvents(params) {
        def lotteryId = params.long('lottery')
        def criteria = Event.createCriteria()
        def results = criteria.list(params) {
            eq("lottery.id", lotteryId)
            order "startDate", "asc"
        }
        results
    }
}
