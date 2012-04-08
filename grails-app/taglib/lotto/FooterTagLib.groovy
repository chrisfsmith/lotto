package lotto

class FooterTagLib {

    static namespace = 'lotto'

    def thisYear = {
        out << new Date().format("yyyy")
    }

    def copyright = {attrs, body ->
        out << "&copy; " + attrs.startYear + " - "
        out << thisYear() + " " + body()
    }
}
