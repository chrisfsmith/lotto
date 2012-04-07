package lotto

class LoginTagLib {

    /**
     * Dependency injection for the springSecurityService.
     */
    def springSecurityService

    def loginControl = {
        if (springSecurityService.isLoggedIn()) {
            def user = springSecurityService.currentUser
            out << "${user?.getFirstName()} ${user?.getLastName()} "
            out << """[${
                link(controller: "logout") {"Logout"}
            }]"""
        } else {
            out << """[${
                link(controller: "login") {"Login"}
            }]"""
        }
    }
}