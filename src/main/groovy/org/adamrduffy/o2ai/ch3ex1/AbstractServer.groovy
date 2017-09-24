package org.adamrduffy.o2ai.ch3ex1

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer

abstract class AbstractServer {
    int port = 8680
    def server

    void instantiate(int port, Map<String, Closure> apis) {
        this.port = port

        server = HttpServer.create(new InetSocketAddress(port), 0)
        server.createContext("/", { HttpExchange exchange ->
            try {
                if (!"GET".equalsIgnoreCase(exchange.requestMethod)) {
                    exchange.sendResponseHeaders(405, 0)
                    exchange.responseBody.close()
                    return
                }

                def path = exchange.requestURI.path
                println "GET $path"

                if (apis.containsKey(path)) {
                    Closure closure = apis.get(path)
                    exchange.responseHeaders.set("Content-Type","text/html")
                    exchange.sendResponseHeaders(200, 0)
                    exchange.responseBody << closure.call()
                    exchange.responseBody.close()
                } else {
                    exchange.sendResponseHeaders(404, 0)
                    exchange.responseBody.close()
                }

                /*
                // path starts with /
                def file = new File(root, path.substring(1))
                if (file.isDirectory()) {
                    file = new File(file, "index.html")
                }
                if (file.exists()) {
                    exchange.responseHeaders.set("Content-Type",
                            TYPES[file.name.split(/\./)[-1]] ?: "text/plain")
                    exchange.sendResponseHeaders(200, 0)
                    file.withInputStream {
                        exchange.responseBody << it
                    }
                    exchange.responseBody.close()
                } else {
                    exchange.sendResponseHeaders(404, 0)
                    exchange.responseBody.close()
                }
                */
            } catch (e) {
                e.printStackTrace()
            }
        } as HttpHandler)
    }

    void start() {
        server.start()
        println "started simple web server on port ${port}"
    }
}
