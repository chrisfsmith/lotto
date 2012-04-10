package lotto

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_ADMIN'])
class LotteryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def lotteryService
    def springSecurityService

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index() {
        redirect(action: "list", params: params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        if (SpringSecurityUtils.ifAllGranted("ROLE_ADMIN")) {
            [lotteryInstanceList: Lottery.list(params), lotteryInstanceTotal: Lottery.count()]
        } else {
            def results = lotteryService.findLotteriesByUser(springSecurityService.getCurrentUser(), params)
            [lotteryInstanceList: results, lotteryInstanceTotal: results.totalCount]
        }
    }

    def create() {
        [lotteryInstance: new Lottery(params)]
    }

    def save() {
        def lotteryInstance = new Lottery(params)
        lotteryService.setupPickOrder(lotteryInstance)
        if (!lotteryInstance.save(flush: true)) {
            render(view: "create", model: [lotteryInstance: lotteryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'lottery.label', default: 'Lottery'), lotteryInstance.id])
        redirect(action: "show", id: lotteryInstance.id)
    }

    def show() {
        def lotteryInstance = Lottery.get(params.id)
        if (!lotteryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lottery.label', default: 'Lottery'), params.id])
            redirect(action: "list")
            return
        }

        [lotteryInstance: lotteryInstance]
    }

    def edit() {
        def lotteryInstance = Lottery.get(params.id)
        if (!lotteryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lottery.label', default: 'Lottery'), params.id])
            redirect(action: "list")
            return
        }

        [lotteryInstance: lotteryInstance]
    }

    def update() {
        def lotteryInstance = Lottery.get(params.id)
        if (!lotteryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lottery.label', default: 'Lottery'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (lotteryInstance.version > version) {
                lotteryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'lottery.label', default: 'Lottery')] as Object[],
                        "Another user has updated this Lottery while you were editing")
                render(view: "edit", model: [lotteryInstance: lotteryInstance])
                return
            }
        }

        lotteryInstance.properties = params
        if (!params.users) {
            lotteryInstance.users = null;
        }

        lotteryService.setupPickOrder(lotteryInstance)

        if (!lotteryInstance.save(flush: true)) {
            render(view: "edit", model: [lotteryInstance: lotteryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'lottery.label', default: 'Lottery'), lotteryInstance.id])
        redirect(action: "show", id: lotteryInstance.id)
    }

    def delete() {
        def lotteryInstance = Lottery.get(params.id)
        if (!lotteryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lottery.label', default: 'Lottery'), params.id])
            redirect(action: "list")
            return
        }

        try {
            lotteryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'lottery.label', default: 'Lottery'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'lottery.label', default: 'Lottery'), params.id])
            redirect(action: "show", id: params.id)
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def pick() {
        def lotteryInstance = Lottery.get(params.lottery)
        def userInstance = springSecurityService.getCurrentUser()
        def eventInstance = Event.get(params.event)
        if (lotteryService.pick(lotteryInstance, userInstance, eventInstance)) {
            flash.message = message(code: 'default.picked.message', args: [eventInstance?.name])
        } else {
            flash.message = message(code: 'default.not.picked.message', args: [eventInstance?.name ?: message(code: 'event.not.exist', default: 'Nonexistent event')])
        }
        redirect(controller: "event", action: "list", params: params)
    }
}
