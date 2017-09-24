package org.adamrduffy.o2ai.ch3ex1

import groovy.transform.Canonical

@Canonical
class Client {
    String client_id = "oauth-client-1"
    String client_secret = "oauth-client-secret-1"
    String redirect_uris = ["http://localhost:9000/callback"]
    String scope = "foo bar"
}
