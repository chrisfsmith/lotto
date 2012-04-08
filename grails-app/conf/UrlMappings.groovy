class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/welcome"(view: 'welcome')

		"/"(controller:"welcome", action:"index")
		"500"(view:'/error')
	}
}
