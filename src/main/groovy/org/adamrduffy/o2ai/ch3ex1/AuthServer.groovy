package org.adamrduffy.o2ai.ch3ex1

import groovy.transform.Canonical

@Canonical
class AuthServer {
    String authorizationEndpoint = 'http://localhost:9001/authorize'
    String tokenEndpoint = 'http://localhost:9001/token'
}
