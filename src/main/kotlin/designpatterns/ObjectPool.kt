package designpatterns

import java.sql.Connection
import java.sql.DriverManager

// Object to be pooled
class PooledObject {
    // Additional properties and methods of the object
    // ...
}

/**
 * The Object Pool design pattern is a creational pattern that aims to improve performance and resource utilization by
 * reusing objects rather than creating and destroying them repeatedly. It maintains a pool of initialized objects that
 * can be borrowed and returned by clients.
 *
 * By reusing objects from the pool, the Object Pool design pattern can help reduce the overhead of object creation and
 * destruction, especially in situations where object creation is expensive or resource-intensive.
 *
 * Please note that the example provided is a basic implementation to demonstrate the concept of the Object Pool design
 * pattern. In a real-world scenario, you may need to consider thread-safety, object lifecycle management, and additional
 * optimizations based on your specific requirements.
 */
class ObjectPool(private val maxPoolSize: Int) {
    private val pool: MutableList<PooledObject> = mutableListOf()

    init {
        // Initialize the object pool with a fixed number of objects
        for (i in 0 until maxPoolSize) {
            pool.add(createObject())
        }
    }

    // Create a new object
    private fun createObject(): PooledObject {
        // Object creation logic
        return PooledObject()
    }

    // Borrow an object from the pool
    fun borrowObject(): PooledObject? {
        if (pool.isNotEmpty()) {
            return pool.removeAt(pool.lastIndex)
        }
        return null
    }

    // Return an object back to the pool
    fun returnObject(obj: PooledObject) {
        if (pool.size < maxPoolSize) {
            pool.add(obj)
        }
    }
}


/**
 * A real-world example where the Object Pool pattern is commonly used is in database connection pooling.
 * In database-driven applications, establishing a connection to a database can be an expensive operation in terms of
 * time and resources. Creating and tearing down connections for each database operation can lead to performance issues.
 *
 * To address this, an object pool can be implemented to manage a set of pre-initialized database connections that can be
 * borrowed and returned by the application as needed. This approach helps reduce the overhead of creating and destroying
 * database connections, resulting in improved performance and resource utilization.
 *
 * Here's a simplified example of how an object pool can be used for database connection pooling:
 */
class ConnectionPool(private val maxPoolSize: Int) {
    private val pool: MutableList<Connection> = mutableListOf()
    private val usedConnections: MutableSet<Connection> = mutableSetOf()

    init {
        // Initialize the connection pool with a fixed number of database connections
        for (i in 0 until maxPoolSize) {
            pool.add(createConnection())
        }
    }

    // Create a new database connection
    private fun createConnection(): Connection {
        // Database connection setup logic
        val connectionUrl = "jdbc:mysql://localhost:3306/mydatabase"
        val username = "myuser"
        val password = "mypassword"
        return DriverManager.getConnection(connectionUrl, username, password)
    }

    // Borrow a database connection from the pool
    fun borrowConnection(): Connection? {
        synchronized(pool) {
            val availableConnections = pool.filterNot { usedConnections.contains(it) }
            if (availableConnections.isNotEmpty()) {
                val connection = availableConnections.first()
                usedConnections.add(connection)
                return connection
            }
            return null
        }
    }

    // Return a database connection back to the pool
    fun returnConnection(connection: Connection) {
        synchronized(pool) {
            usedConnections.remove(connection)
            if (pool.size < maxPoolSize) {
                pool.add(connection)
            } else {
                connection.close()
            }
        }
    }
}
