# Working with Server Commands in Redis

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 
The following operations allow you to work with server. Click an operation name to see details on how to use it.

For a sample proxy service that illustrates how to work with server commands, see [sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |:-------------|
| [flushAll](#deleting-all-keys-from-all-databases)    | Deletes all the keys from all existing databases |
| [flushDB](#deleting-all-keys-from-the-current-database)    | Deletes all the keys from the currently selected database |

### Operation details
This section provides more details on the operations.

#### Deleting all keys from all databases
The flushAll operation deletes all the keys from all existing databases.

**flushAll**
```xml
<redis.flushAll/>
```

**Sample request**

A sample request with an empty body can be handled by the flushAll operation.

**Related redis documentation**

[https://redis.io/commands/flushall](https://redis.io/commands/flushall)

#### Deleting all keys from the current database
The flushDB operation deletes all the keys from the currently selected database.

**flushDB**
```xml
<redis.flushDB/>
```

**Sample request**

A sample request with an empty body can be handled by the flushDB operation.

**Related redis documentation**

[https://redis.io/commands/flushdb](https://redis.io/commands/flushdb)

### Sample configuration
Following is a sample proxy service that illustrates how to connect to Redis with the init operation, and then use the flushAll operation. The sample request for this proxy can be found in [flushAll sample request](#request). You can use this sample as a template for using other operations in this category.

**Sample Proxy**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="flushAll"
       startOnLoad="true"
       statistics="disable"
       trace="disable"
       transports="http,https">
   <target>
      <inSequence>
         <property expression="json-eval($.redisHost)" name="redisHost"/>
         <property expression="json-eval($.redisPort)" name="redisPort"/>
         <property expression="json-eval($.redisTimeout)" name="redisTimeout"/>
         <property expression="json-eval($.redisKey)" name="redisKey"/>
         <redis.init>
            <redisHost>{$ctx:redisHost}</redisHost>
            <redisPort>{$ctx:redisPort}</redisPort>
            <redisTimeout>{$ctx:redisTimeout}</redisTimeout>
         </redis.init>
         <redis.flushAll/>
         <respond/>
      </inSequence>
   </target>
   <description/>
</proxy>                                
```
