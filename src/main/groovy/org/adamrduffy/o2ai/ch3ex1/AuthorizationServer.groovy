package org.adamrduffy.o2ai.ch3ex1

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.text.SimpleTemplateEngine

class AuthorizationServer extends AbstractServer {
    static def root = {
        def templateFile = new File("src/main/resources/ch3ex1/as/index.html")
        def engine = new SimpleTemplateEngine()
        def map = [:]
        map['authServer'] = new JsonSlurper().parseText(new JsonBuilder(new AuthServer()).toString())
        map['client'] = new JsonSlurper().parseText(new JsonBuilder(new Client()).toString())
        def template = engine.createTemplate(templateFile).make(map)
        return template.toString()
    }

    static void main(String[] args) {
        def server = new AuthorizationServer()

        def apis = new HashMap<String, Closure>()
        apis.put("/", root)

        server.instantiate(9001, apis)
        server.start()

//        def templateFile = new File("src/main/resources/ch3ex1/as/index.html")
//        def engine = new SimpleTemplateEngine()
//        def map = [:]
//        map['authServer'] = new JsonSlurper().parseText(new JsonBuilder(new AuthServer()).toString())
//        map['client'] = new JsonSlurper().parseText(new JsonBuilder(new Client()).toString())
//        def template = engine.createTemplate(templateFile).make(map)
//        println template.toString()
    }
}
