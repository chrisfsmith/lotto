package lotto

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_ADMIN'])
class RegistrationController {

    static scaffold = Registration

    def springSecurityService

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index() {
        redirect(action: "list", params: params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        if (SpringSecurityUtils.ifAllGranted("ROLE_ADMIN")) {
            [registrationInstanceList: Registration.list(params), registrationInstanceTotal: Lottery.count()]
        } else {
            def results = Registration.findAllByAttendee((User) springSecurityService.getCurrentUser(), params)
            [registrationInstanceList: results, registrationInstanceTotal: results.size()]
        }
    }

}
