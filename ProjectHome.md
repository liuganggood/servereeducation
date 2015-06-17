This project is a J2EE-based backend server module for eEducation infrastructure

UPDATED.
The project is going to be split in two parts

>Transactions requiring one-way request/response communication with no need to keep connection alive while user stays online, are handled by means of J2EE/Servlets running on Apache Tomcat instance (port 8081 for now). These transactions are going to be of HTTP request/response type.
Server-side scripting for response generation is supposed to handle and sync multiple simultaneous user connections, letting developers to forget about issues like user thread synchronization and concentrate solely on the business layer implementation, hence it is reasonable to user it whenever possible.
One-way transaction type includes:

1.information retrieval based on DB query

2.information submission for further storing in DB

3.(to be continued)

>Transactions requiring two-way communication with need to keep connection alive while user stays online, are handled by means of "hardcoded" Java Server Sockets accepting incoming user connections and creating a separate thread per user where every connection is further processed and not lost like in the passage above.
In case of web-based server-side scriping two-way (push) communication between server and client (browser or app) might still be implemented by means of WebSocket (examples are FB messages/notifications), but this solution is infeasible due to the lack of support for WebSocket in Android.
Two-way transaction type includes:

1.groupchat message and information exchange

2.dynamic broadcast of 7-stage coursework process

3.(to be continued)


UPDATED.
In order to get rid of complications related to thread management i.e. explicitly preparing a separate thread per each client-server connection, it is advisable to make use of Apache Netty framework
(more info at http://en.wikipedia.org/wiki/Netty_(software))
Its primary advantage is that "by using ExecutorService class we reduce the amount of work the JVM has to do when creating new Threads, by caching old Threads and reusing them instead of deallocate/allocate" (quote from wiki page)