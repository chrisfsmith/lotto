package lotto

class EventController {

    static scaffold = Event

    def search = {
        flash.message = "Search results for: ${params.q}"
        def resultsMap = Event.search(params.q, params)
        render(view: 'list',
                model: [
                        eventInstanceList: resultsMap.results,
                        eventInstanceTotal: Event.countHits(params.q)
                ])
    }
}
