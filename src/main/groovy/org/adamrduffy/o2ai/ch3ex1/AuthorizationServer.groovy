package org.adamrduffy.o2ai.ch3ex1

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.text.SimpleTemplateEngine

class AuthorizationServer {
    static void main(String[] args) {
        def templateFile = new File("src/main/resources/ch3ex1/as/index.html")
        def engine = new SimpleTemplateEngine()
        def map = [:]
        map['authServer'] = new JsonSlurper().parseText(new JsonBuilder(new AuthServer()).toString())
        map['client'] = new JsonSlurper().parseText(new JsonBuilder(new Client()).toString())
        def template = engine.createTemplate(templateFile).make(map)
        println template.toString()
    }
}
