package lotto



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(AdminController)
class AdminControllerTests {

    void testIndex() {
        controller.index()
        assert '' == response.text
        assert null == response.redirectedUrl
    }
}
