import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class WelcomeController {

    /**
     * Dependency injection for the springSecurityService.
     */
    def springSecurityService

    def index() {
        if (springSecurityService.isLoggedIn()) {
            if (SpringSecurityUtils.ifAllGranted("ROLE_ADMIN")) {
                redirect controller: "admin"
            } else {
                redirect controller: "lottery", action: "list"
            }
        } else {
            redirect uri: "/welcome"
        }
    }
}
